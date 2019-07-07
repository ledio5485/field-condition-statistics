package at.farm.fieldconditionstatistics.service;

import at.farm.fieldconditionstatistics.repository.FieldConditionsEntity;
import at.farm.fieldconditionstatistics.repository.Statistics;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SummaryStatisticsTest implements SummaryStatistics {

    @Test
    public void getStatistics() {
        ZonedDateTime fixedZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        ArrayList<FieldConditionsEntity> entities = Lists.list(
                new FieldConditionsEntity(null, 0.4, fixedZonedDateTime),
                new FieldConditionsEntity(null, 0.5, fixedZonedDateTime),
                new FieldConditionsEntity(null, 0.6, fixedZonedDateTime)
        );

        Statistics actual = getStatistics(entities.parallelStream());

        Statistics expected = new Statistics(0.4, 0.6, 0.5);
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
