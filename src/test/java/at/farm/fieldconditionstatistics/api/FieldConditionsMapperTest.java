package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.api.model.FieldConditions;
import at.farm.fieldconditionstatistics.repository.FieldConditionsEntity;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldConditionsMapperTest {

    private final FieldConditionsMapper sut = new FieldConditionsMapper(new ModelMapper());

    @Test
    public void fromDto() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        FieldConditions dto = new FieldConditions(0.5, now);

        FieldConditionsEntity actual = sut.fromDto(dto);

        FieldConditionsEntity expected = new FieldConditionsEntity(null, 0.5, now);
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}