package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.api.model.FieldStatistics;
import at.farm.fieldconditionstatistics.api.model.Vegetation;
import at.farm.fieldconditionstatistics.repository.Statistics;
import org.springframework.stereotype.Component;

@Component
class FieldStatisticsFactory {

    FieldStatistics create(Statistics fieldStatistics) {
        return new FieldStatistics(new Vegetation(fieldStatistics.getMin(), fieldStatistics.getMax(), fieldStatistics.getAverage()));
    }
}
