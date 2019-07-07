package at.farm.fieldconditionstatistics.repository;

import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.function.Predicate;

@Repository
public class InMemoryFieldConditionsRepository implements FieldConditionsRepository {
    private Collection<FieldConditionsEntity> fieldConditionEntities = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void add(FieldConditionsEntity newFieldConditionsEntity) {
        fieldConditionEntities.add(newFieldConditionsEntity);
    }

    @Override
    public Statistics getStatistics(ZonedDateTime from, ZonedDateTime to) {
        DoubleSummaryStatistics statistics = fieldConditionEntities.parallelStream()
                .filter(isBetween(from, to))
                .mapToDouble(FieldConditionsEntity::getVegetation)
                .summaryStatistics();

        return new Statistics(statistics.getMin(), statistics.getMax(), statistics.getAverage());
    }

    private Predicate<FieldConditionsEntity> isBetween(ZonedDateTime from, ZonedDateTime to) {
        return fc -> fc.getOccurrenceAt().isAfter(from) && fc.getOccurrenceAt().isBefore(to);
    }
}
