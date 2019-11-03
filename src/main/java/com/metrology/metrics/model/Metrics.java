package com.metrology.metrics.model;

public class Metrics {

    private int conditionalStatementsCount;

    private int generalOperatorsCount;

    private double programRelativeComplexity;

    private int maxNestingLevel;

    public int getConditionalStatementsCount() {
        return conditionalStatementsCount;
    }

    void setConditionalStatementsCount(int conditionalStatementsCount) {
        this.conditionalStatementsCount = conditionalStatementsCount;
    }

    int getGeneralOperatorsCount() {
        return generalOperatorsCount;
    }

    void setGeneralOperatorsCount(int generalOperatorsCount) {
        this.generalOperatorsCount = generalOperatorsCount;
    }

    public double getProgramRelativeComplexity() {
        return programRelativeComplexity;
    }

    void setProgramRelativeComplexity(double programRelativeComplexity) {
        this.programRelativeComplexity = programRelativeComplexity;
    }

    public int getMaxNestingLevel() {
        return maxNestingLevel;
    }

    void setMaxNestingLevel(int maxNestingLevel) {
        this.maxNestingLevel = maxNestingLevel;
    }

}
