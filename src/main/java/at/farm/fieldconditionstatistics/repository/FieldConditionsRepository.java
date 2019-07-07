package at.farm.fieldconditionstatistics.repository;

import java.time.ZonedDateTime;

public interface FieldConditionsRepository {
    void add(FieldConditionsEntity fieldConditionsEntity);

    Statistics getStatistics(ZonedDateTime from, ZonedDateTime to);
}
