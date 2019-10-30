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
        int ifCount = calculateStatementsCount("if");
        int switchCount = calculateStatementsCount("switch");
        int forCount = calculateStatementsCount("for");
        int selectCount = calculateStatementsCount("select");

        int conditionalStatementsCount = ifCount + switchCount + forCount + selectCount;
        metrics.setConditionalStatementsCount(conditionalStatementsCount);
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

    public String getProgramCode() {
        return programCode;
    }

}
