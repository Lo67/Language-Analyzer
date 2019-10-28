package com.metrology.metrics;

import com.metrology.metrics.model.LanguageAnalyzer;

public class App {

    public static void main(String[] args) {
        LanguageAnalyzer test = new LanguageAnalyzer("kdkfj /* adfa\n d*/ //dsfadfa\n asdfas");
        test.deleteAllComments();

        System.out.println(test.getProgramCode());
    }

}
