package at.farm.fieldconditionstatistics.repository;

public class Statistics {

    private final Double min;
    private final Double max;
    private final Double avg;

    public Statistics(Double min, Double max, Double avg) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getAvg() {
        return avg;
    }
}
