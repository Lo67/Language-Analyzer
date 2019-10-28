package com.metrology.metrics;

import com.metrology.metrics.model.LanguageAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App {

    public static void main(String[] args) throws IOException {
        StringBuilder programCode = new StringBuilder();

        File file = new File("test.txt");
        Files.readAllLines(file.toPath())
                .forEach(line -> programCode.append(line).append("\n"));

        LanguageAnalyzer languageAnalyzer = new LanguageAnalyzer(programCode.toString());
        languageAnalyzer.parseProgram();

        System.out.println(languageAnalyzer.getProgramCode());
    }

}
