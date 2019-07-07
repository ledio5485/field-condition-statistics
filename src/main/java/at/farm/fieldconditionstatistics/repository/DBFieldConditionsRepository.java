package at.farm.fieldconditionstatistics.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface DBFieldConditionsRepository extends CrudRepository<FieldConditionsEntity, UUID>, FieldConditionsRepository {

    @Override
    default void add(FieldConditionsEntity fieldConditionsEntity) {
        this.save(fieldConditionsEntity);
    }

    @Override
    default Stream<FieldConditionsEntity> filter(ZonedDateTime from, ZonedDateTime to) {
        return findAllByOccurrenceAtBetween(from, to).parallelStream();
    }

    List<FieldConditionsEntity> findAllByOccurrenceAtBetween(ZonedDateTime from, ZonedDateTime to);
}
