package com.metrology.metrics.model;

import java.util.regex.Pattern;

public class LanguageAnalyzer {

    private String programCode;

    public LanguageAnalyzer(String programCode) {
        this.programCode = programCode;
    }

    public void parseProgram() {
        deleteAllComments();
        deleteControlCharacters();
        deleteAllTextLiterals();
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

    public String getProgramCode() {
        return programCode;
    }

}
