package at.farm.fieldconditionstatistics.repository;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

public interface FieldConditionsRepository {

    void add(FieldConditionsEntity fieldConditionsEntity);

    Stream<FieldConditionsEntity> filter(ZonedDateTime from, ZonedDateTime to);
}
