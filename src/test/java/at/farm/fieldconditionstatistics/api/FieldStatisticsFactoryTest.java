package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.api.model.FieldStatistics;
import at.farm.fieldconditionstatistics.api.model.Vegetation;
import at.farm.fieldconditionstatistics.repository.Statistics;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldStatisticsFactoryTest {

    private final FieldStatisticsFactory sut = new FieldStatisticsFactory(new ModelMapper());

    @Test
    public void create() {
        Statistics statistics = new Statistics(0.3, 0.5, 0.4);

        FieldStatistics actual = sut.create(statistics);

        FieldStatistics expected = new FieldStatistics(new Vegetation(0.3, 0.5, 0.4));
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}