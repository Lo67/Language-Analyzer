package com.metrology.metrics.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageAnalyzer {

    private String programCode;

    public LanguageAnalyzer(String programCode) {
        this.programCode = programCode;
    }

    public void deleteAllComments() {
        final String singleLineRegEx = "\\s*//.*";
        final String multiLineRegEx = "\\s*/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/";

        Matcher singleLineMatcher = Pattern.compile(singleLineRegEx).matcher(programCode);
        programCode = singleLineMatcher.replaceAll("");

        Matcher multiLineMatcher = Pattern.compile(multiLineRegEx).matcher(programCode);
        programCode = multiLineMatcher.replaceAll("");
    }

    public String getProgramCode() {
        return programCode;
    }

}
