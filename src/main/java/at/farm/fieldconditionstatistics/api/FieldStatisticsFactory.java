package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.api.model.FieldStatistics;
import at.farm.fieldconditionstatistics.api.model.Vegetation;
import at.farm.fieldconditionstatistics.repository.Statistics;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class FieldStatisticsFactory {
    private final ModelMapper modelMapper;

    FieldStatisticsFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    FieldStatistics create(Statistics fieldStatistics) {
        Vegetation vegetation = modelMapper.map(fieldStatistics, Vegetation.class);
        return new FieldStatistics(vegetation);
    }
}
