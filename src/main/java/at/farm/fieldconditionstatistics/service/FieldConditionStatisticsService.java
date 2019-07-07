package at.farm.fieldconditionstatistics.service;

import at.farm.fieldconditionstatistics.repository.FieldConditionsEntity;
import at.farm.fieldconditionstatistics.repository.FieldConditionsRepository;
import at.farm.fieldconditionstatistics.repository.Statistics;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class FieldConditionStatisticsService implements SummaryStatistics {
    private final FieldConditionsRepository repository;

    public FieldConditionStatisticsService(@Qualifier("DBFieldConditionsRepository") FieldConditionsRepository repository) {
        this.repository = repository;
    }

    public void add(FieldConditionsEntity fieldConditionsEntity) {
        repository.add(fieldConditionsEntity);
    }

    public Statistics getFieldStatistics(ZonedDateTime from, ZonedDateTime to) {
        return getStatistics(repository.filter(from, to));
    }

}
