package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.api.model.FieldConditions;
import at.farm.fieldconditionstatistics.api.model.FieldStatistics;
import at.farm.fieldconditionstatistics.service.FieldConditionStatisticsService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class FieldConditionStatisticsController implements FieldConditionStatisticsApi {
    private final FieldConditionStatisticsService fieldConditionStatisticsService;
    private final FieldConditionsMapper fieldConditionsMapper;
    private final FieldStatisticsFactory fieldStatisticsFactory;

    public FieldConditionStatisticsController(FieldConditionStatisticsService fieldConditionStatisticsService, FieldConditionsMapper fieldConditionsMapper, FieldStatisticsFactory fieldStatisticsFactory) {
        this.fieldConditionStatisticsService = fieldConditionStatisticsService;
        this.fieldConditionsMapper = fieldConditionsMapper;
        this.fieldStatisticsFactory = fieldStatisticsFactory;
    }

    @Override
    public void insertFieldConditions(FieldConditions fieldConditions) {
        fieldConditionStatisticsService.add(fieldConditionsMapper.fromDto(fieldConditions));
    }

    @Override
    public FieldStatistics getFieldStatistics(ZonedDateTime from, ZonedDateTime to) {
        return fieldStatisticsFactory.create(fieldConditionStatisticsService.getFieldStatistics(from, to));
    }
}
