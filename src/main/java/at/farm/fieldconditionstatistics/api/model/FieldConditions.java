package at.farm.fieldconditionstatistics.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.ZonedDateTime;

@Valid
public class FieldConditions {
    @NotNull
    private Double vegetation;
    @PastOrPresent
    private ZonedDateTime occurrenceAt;

    public FieldConditions() {
    }

    public FieldConditions(Double vegetation, ZonedDateTime occurrenceAt) {
        this.vegetation = vegetation;
        this.occurrenceAt = occurrenceAt;
    }

    public Double getVegetation() {
        return vegetation;
    }

    public void setVegetation(Double vegetation) {
        this.vegetation = vegetation;
    }

    public ZonedDateTime getOccurrenceAt() {
        return occurrenceAt;
    }

    public void setOccurrenceAt(ZonedDateTime occurrenceAt) {
        this.occurrenceAt = occurrenceAt;
    }

}
