package at.farm.fieldconditionstatistics.api.model;

public class FieldStatistics {
    private Vegetation vegetation;

    public FieldStatistics() {
    }

    public FieldStatistics(Vegetation vegetation) {
        this.vegetation = vegetation;
    }

    public Vegetation getVegetation() {
        return vegetation;
    }

    public void setVegetation(Vegetation vegetation) {
        this.vegetation = vegetation;
    }
}
