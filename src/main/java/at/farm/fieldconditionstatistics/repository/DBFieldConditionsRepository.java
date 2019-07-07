package at.farm.fieldconditionstatistics.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.UUID;

@Repository
public interface DBFieldConditionsRepository extends CrudRepository<FieldConditionsEntity, UUID>, FieldConditionsRepository {

    @Override
    default void add(FieldConditionsEntity fieldConditionsEntity) {
        this.save(fieldConditionsEntity);
    }

    @Override
    default Statistics getStatistics(ZonedDateTime from, ZonedDateTime to) {
        DoubleSummaryStatistics statistics = findAllByOccurrenceAtBetween(from, to).parallelStream()
                .mapToDouble(FieldConditionsEntity::getVegetation)
                .summaryStatistics();

        return new Statistics(statistics.getMin(), statistics.getMax(), statistics.getAverage());
    }

    List<FieldConditionsEntity> findAllByOccurrenceAtBetween(ZonedDateTime from, ZonedDateTime to);
}
