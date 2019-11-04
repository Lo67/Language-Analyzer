package com.metrology.metrics.controller;

import com.metrology.metrics.model.LanguageAnalyzer;
import com.metrology.metrics.model.Metrics;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AppController {

    @FXML
    private Button chooseFileButton;

    @FXML
    private Button goButton;

    @FXML
    private TextArea codeTextArea;

    @FXML
    private TextField conditionalStatementsCountTextField;

    @FXML
    private TextField generalOperatorsCountTextField;

    @FXML
    private TextField relativeComplexityTextField;

    @FXML
    private TextField maxNestingLevelTextField;

    @FXML
    private void onChooseFileButtonClick() {
        clearGuiFields();

        Window window = codeTextArea.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {
            String codeText = readFile(file);
            codeTextArea.setText(codeText);
        }
    }

    private void clearGuiFields() {
        conditionalStatementsCountTextField.clear();
        generalOperatorsCountTextField.clear();
        relativeComplexityTextField.clear();
        maxNestingLevelTextField.clear();
    }

    private String readFile(File file) {
        StringBuilder fileText = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileText.append(line).append("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileText.toString();
    }

    @FXML
    private void onGoButtonClick() {
        LanguageAnalyzer languageAnalyzer = new LanguageAnalyzer(codeTextArea.getText());
        languageAnalyzer.parseProgram();
        Metrics metrics = languageAnalyzer.getMetrics();
        drawMetrics(metrics);
    }

    private void drawMetrics(Metrics metrics) {
        String value = Integer.toString(metrics.getConditionalStatementsCount());
        conditionalStatementsCountTextField.setText(value);

        value = Integer.toString(metrics.getGeneralOperatorsCount());
        generalOperatorsCountTextField.setText(value);

        double doubleValue = (double) Math.round(metrics.getProgramRelativeComplexity() * 100) / 100;
        value = Double.toString(doubleValue);
        relativeComplexityTextField.setText(value);

        value = Integer.toString(metrics.getMaxNestingLevel());
        maxNestingLevelTextField.setText(value);
    }

}
