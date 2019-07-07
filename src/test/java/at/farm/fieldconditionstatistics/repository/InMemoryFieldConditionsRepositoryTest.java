package at.farm.fieldconditionstatistics.repository;

import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryFieldConditionsRepositoryTest {

    private final FieldConditionsRepository sut = new InMemoryFieldConditionsRepository();

    @Test
    public void getStatistics() {
        ZonedDateTime fixedZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        FieldConditionsEntity entity1 = new FieldConditionsEntity(null, 0.4, fixedZonedDateTime);
        sut.add(entity1);
        FieldConditionsEntity entity2 = new FieldConditionsEntity(null, 0.5, fixedZonedDateTime);
        sut.add(entity2);
        FieldConditionsEntity entity3 = new FieldConditionsEntity(null, 0.6, fixedZonedDateTime);
        sut.add(entity3);

        List<FieldConditionsEntity> actual = sut.filter(ZonedDateTime.from(fixedZonedDateTime).minusSeconds(5), ZonedDateTime.now(ZoneOffset.UTC))
                .collect(Collectors.toList());

        assertThat(actual).containsExactlyInAnyOrder(entity1, entity2, entity3);
    }

}