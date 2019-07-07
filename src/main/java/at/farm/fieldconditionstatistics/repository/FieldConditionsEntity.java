package at.farm.fieldconditionstatistics.repository;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "field_conditions")
public class FieldConditionsEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "vegetation")
    private Double vegetation;
    @Column(name = "occurrence_at")
    private ZonedDateTime occurrenceAt;

    public FieldConditionsEntity() {
    }

    public FieldConditionsEntity(UUID id, Double vegetation, ZonedDateTime occurrenceAt) {
        this.id = id;
        this.vegetation = vegetation;
        this.occurrenceAt = occurrenceAt;
    }

    public UUID getId() {
        return id;
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
