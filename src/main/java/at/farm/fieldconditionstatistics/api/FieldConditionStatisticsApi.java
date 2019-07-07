package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.api.model.FieldConditions;
import at.farm.fieldconditionstatistics.api.model.FieldStatistics;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@RestController()
@RequestMapping("/api")
public interface FieldConditionStatisticsApi {

    @PostMapping(value = "/field-conditions")
    @ResponseStatus(HttpStatus.CREATED)
    void insertFieldConditions(@RequestBody @NotNull @Valid FieldConditions fieldConditions);

    @GetMapping(value = "/field-statistics")
    FieldStatistics getFieldStatistics(@RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime from,
                                       @RequestParam(value = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime to);
}
