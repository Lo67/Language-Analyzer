package com.metrology.metrics.model;

public class Metrics {

    private int conditionalStatementsCount;

    private int generalOperatorsCount;

    private double programRelativeComplexity;

    private int maxNestingLevel;

    public int getConditionalStatementsCount() {
        return conditionalStatementsCount;
    }

    public void setConditionalStatementsCount(int conditionalStatementsCount) {
        this.conditionalStatementsCount = conditionalStatementsCount;
    }

    public int getGeneralOperatorsCount() {
        return generalOperatorsCount;
    }

    public void setGeneralOperatorsCount(int generalOperatorsCount) {
        this.generalOperatorsCount = generalOperatorsCount;
    }

    public double getProgramRelativeComplexity() {
        return programRelativeComplexity;
    }

    public void setProgramRelativeComplexity(double programRelativeComplexity) {
        this.programRelativeComplexity = programRelativeComplexity;
    }

    public int getMaxNestingLevel() {
        return maxNestingLevel;
    }

    public void setMaxNestingLevel(int maxNestingLevel) {
        this.maxNestingLevel = maxNestingLevel;
    }

}
