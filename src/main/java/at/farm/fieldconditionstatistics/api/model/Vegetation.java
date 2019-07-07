package at.farm.fieldconditionstatistics.api.model;

public class Vegetation {
    private Double min;
    private Double max;
    private Double avg;

    public Vegetation() {
    }

    public Vegetation(Double min, Double max, Double avg) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
