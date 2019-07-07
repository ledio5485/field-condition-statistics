package at.farm.fieldconditionstatistics.repository;

public class Statistics {

    private final Double min;
    private final Double max;
    private final Double average;

    public Statistics(Double min, Double max, Double average) {
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getAverage() {
        return average;
    }
}
