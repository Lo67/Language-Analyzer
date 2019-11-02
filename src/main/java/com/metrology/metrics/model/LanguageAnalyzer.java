package com.metrology.metrics.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageAnalyzer {

    private String programCode;

    private Metrics metrics = new Metrics();

    public LanguageAnalyzer(String programCode) {
        this.programCode = programCode;
    }

    public void parseProgram() {
        deleteAllComments();
        deleteControlCharacters();
        deleteAllTextLiterals();
        keepFuncBodiesInCode();
        calculateConditionalStatementsCount();
        calculateGeneralOperatorsCount();
        calculateRelativeComplexity();
    }

    private void deleteAllComments() {
        final String singleLineRegEx = "\\s*//.*";
        final String multiLineRegEx = "\\s*/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/";

        programCode = Pattern.compile(singleLineRegEx)
                .matcher(programCode).replaceAll("");

        programCode = Pattern.compile(multiLineRegEx)
                .matcher(programCode).replaceAll("");
    }

    private void deleteControlCharacters() {
        programCode = programCode.replaceAll("\t", " ");
        programCode = programCode.replaceAll("\n", " ");
        programCode = programCode.replaceAll("\r", " ");
    }

    private void deleteAllTextLiterals() {
        final String stringLiteralRegEx = "\"[^\"]*\"";
        final String singleStringLiteralRegEx = "`[^`]*`";
        final String runeLiteralRegEx = "'[^']*'";

        programCode = Pattern.compile(stringLiteralRegEx)
                .matcher(programCode).replaceAll(" ");

        programCode = Pattern.compile(singleStringLiteralRegEx)
                .matcher(programCode).replaceAll(" ");

        programCode = Pattern.compile(runeLiteralRegEx)
                .matcher(programCode).replaceAll(" ");
    }

    private void keepFuncBodiesInCode() {
        /* Алгоритм:
            1) Найти объявление функции
            2) Убрать из текста символы до тела найденой функции
            3) Посчитать размер тела функции
            4) Добавить тело функции в буфер
            5) Пока в тексте осталось объявление функции, повторять с шага 1
            6) Перезаписать текст значением из буфера
        */

        StringBuilder codeText = new StringBuilder(programCode);
        StringBuilder codeBuff = new StringBuilder(programCode.length());

        Pattern funcPattern = Pattern.compile("(func [^{]*)");
        Matcher funcMatcher = funcPattern.matcher(programCode);

        while (funcMatcher.find()) {
            codeText.delete(0, funcMatcher.start() + funcMatcher.group().length());
            int funcBodySize = countFuncBodySize(codeText);
            String funcBody = codeText.substring(0, funcBodySize);
            codeBuff.append(funcBody);
            funcMatcher = funcPattern.matcher(codeText.toString());
        }
        programCode = codeBuff.toString();
    }

    private int countFuncBodySize(StringBuilder codeText) {
        int curlyBracesCount = 1;
        int charIndex = 1;

        while (curlyBracesCount != 0) {
            if (codeText.charAt(charIndex) == '{') {
                curlyBracesCount++;
            } else if (codeText.charAt(charIndex) == '}') {
                curlyBracesCount--;
            }

            charIndex++;
        }
        return charIndex;
    }

    private void calculateConditionalStatementsCount() {
        String[] statements = new String[]{
                "if", "switch", "for", "select"
        };

        int statementsCount = 0;
        for (String statement : statements) {
            statementsCount += calculateStatementsCount(statement);
        }

        metrics.setConditionalStatementsCount(statementsCount);
    }

    private int calculateStatementsCount(String statement) {
        Matcher matcher = Pattern
                .compile("(?:^| )" + statement + "(?:$| )",
                        Pattern.CASE_INSENSITIVE)
                .matcher(programCode);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private void calculateGeneralOperatorsCount() {
        String[] operators = new String[]{
                "||", "&&", "==", "!=", "<=",
                ">=", "<<", ">>", "&^", "<-",
                "<", ">", "+", "-", "|", "^",
                "*", "/", "%", "&", "!"
        };

        int operatorsCount = 0;
        for (String operator : operators) {
            operatorsCount += calculateOperatorsCount(operator);
        }

        metrics.setGeneralOperatorsCount(operatorsCount);
    }

    private int calculateOperatorsCount(String operator) {
        int operatorsCount = 0;
        int indexOfOperator = programCode.indexOf(operator);

        while (indexOfOperator != -1) {
            operatorsCount++;
            indexOfOperator = programCode.indexOf(operator, indexOfOperator + 1);
        }

        return operatorsCount;
    }

    private void calculateRelativeComplexity() {
        int conditionalStatementsCount = metrics.getConditionalStatementsCount();
        int statementsAndOperatorsCount =
                conditionalStatementsCount + metrics.getGeneralOperatorsCount();

        if (statementsAndOperatorsCount == 0) {
            metrics.setProgramRelativeComplexity(0);
            return;
        }

        double relativeComplexity =
                (double) conditionalStatementsCount / statementsAndOperatorsCount;

        metrics.setProgramRelativeComplexity(relativeComplexity);
    }

    public Metrics getMetrics() {
        return metrics;
    }

}
