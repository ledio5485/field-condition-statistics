package at.farm.fieldconditionstatistics.service;

import at.farm.fieldconditionstatistics.repository.FieldConditionsEntity;
import at.farm.fieldconditionstatistics.repository.Statistics;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Stream;

public interface SummaryStatistics {

    default Statistics getStatistics(Stream<FieldConditionsEntity> stream) {
        DoubleSummaryStatistics statistics = stream
                .mapToDouble(FieldConditionsEntity::getVegetation)
                .summaryStatistics();

        return new Statistics(statistics.getMin(), statistics.getMax(), statistics.getAverage());
    }
}
