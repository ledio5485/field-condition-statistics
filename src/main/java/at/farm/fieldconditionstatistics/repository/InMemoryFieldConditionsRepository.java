package at.farm.fieldconditionstatistics.repository;

import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Repository
public class InMemoryFieldConditionsRepository implements FieldConditionsRepository {
    private Collection<FieldConditionsEntity> fieldConditionEntities = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void add(FieldConditionsEntity newFieldConditionsEntity) {
        fieldConditionEntities.add(newFieldConditionsEntity);
    }

    @Override
    public Stream<FieldConditionsEntity> filter(ZonedDateTime from, ZonedDateTime to) {
        return fieldConditionEntities.parallelStream()
                .filter(isBetween(from, to));
    }

    private Predicate<FieldConditionsEntity> isBetween(ZonedDateTime from, ZonedDateTime to) {
        return fc -> fc.getOccurrenceAt().isAfter(from) && fc.getOccurrenceAt().isBefore(to);
    }
}
