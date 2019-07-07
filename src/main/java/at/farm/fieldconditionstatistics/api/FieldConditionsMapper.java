package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.api.model.FieldConditions;
import at.farm.fieldconditionstatistics.repository.FieldConditionsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class FieldConditionsMapper {
    private final ModelMapper modelMapper;

    FieldConditionsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    FieldConditionsEntity fromDto(FieldConditions fieldConditions) {
        return modelMapper.map(fieldConditions, FieldConditionsEntity.class);
    }
}
