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
        calculateConditionalStatementsCount();
        calculateGeneralOperatorsCount();
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

    public String getProgramCode() {
        return programCode;
    }

}
