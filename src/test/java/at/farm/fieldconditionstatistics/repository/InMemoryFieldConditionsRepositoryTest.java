package at.farm.fieldconditionstatistics.repository;

import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryFieldConditionsRepositoryTest {

    private final FieldConditionsRepository sut = new InMemoryFieldConditionsRepository();

    @Test
    public void getStatistics() {
        ZonedDateTime fixedZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        sut.add(new FieldConditionsEntity(null, 0.4, fixedZonedDateTime));
        sut.add(new FieldConditionsEntity(null, 0.5, fixedZonedDateTime));
        sut.add(new FieldConditionsEntity(null, 0.6, fixedZonedDateTime));

        Statistics actual = sut.getStatistics(ZonedDateTime.from(fixedZonedDateTime).minusSeconds(5), ZonedDateTime.now(ZoneOffset.UTC));

        Statistics expected = new Statistics(0.4, 0.6, 0.5);
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}