package at.farm.fieldconditionstatistics.api;

import at.farm.fieldconditionstatistics.FieldConditionStatisticsApplication;
import at.farm.fieldconditionstatistics.api.model.FieldConditions;
import at.farm.fieldconditionstatistics.api.model.FieldStatistics;
import at.farm.fieldconditionstatistics.api.model.Vegetation;
import at.farm.fieldconditionstatistics.repository.DBFieldConditionsRepository;
import at.farm.fieldconditionstatistics.repository.FieldConditionsEntity;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FieldConditionStatisticsApplication.class)
public class FieldConditionStatisticsControllerITest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private DBFieldConditionsRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void insertFieldConditions() {
        ZonedDateTime now = ZonedDateTime.now();
        FieldConditions fieldConditions = new FieldConditions(0.5d, now);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/api/field-conditions", fieldConditions, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        FieldConditionsEntity actual = repository.findAll().iterator().next();

        FieldConditionsEntity expected = new FieldConditionsEntity(null, 0.5d, now);
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    @Test
    public void getFieldStatisticsOfTheLast30Days() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ArrayList<FieldConditionsEntity> list = Lists.list(
                new FieldConditionsEntity(null,0.3, now.minusDays(1)),
                new FieldConditionsEntity(null,0.4, now.minusDays(2)),
                new FieldConditionsEntity(null,0.5, now.minusDays(3)),
                new FieldConditionsEntity(null,1.2, now.minusDays(31)),
                new FieldConditionsEntity(null,3.4, now.plusDays(1))
        );
        repository.saveAll(list);

        ResponseEntity<FieldStatistics> actual = restTemplate.getForEntity("/api/field-statistics", FieldStatistics.class);

        FieldStatistics expected = new FieldStatistics(new Vegetation(0.3, 0.5, 0.4));
        assertThat(actual.getBody()).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void getFieldStatisticsOfCustomDateRange() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        ArrayList<FieldConditionsEntity> list = Lists.list(
                new FieldConditionsEntity(null,0.3, now.minusDays(1)),
                new FieldConditionsEntity(null,0.4, now.minusDays(2)),
                new FieldConditionsEntity(null,0.5, now.minusDays(3)),
                new FieldConditionsEntity(null,0.6, now.minusDays(4)),
                new FieldConditionsEntity(null,0.7, now.minusDays(5)),
                new FieldConditionsEntity(null,1.2, now.minusDays(11)),
                new FieldConditionsEntity(null,1.2, now.minusDays(21)),
                new FieldConditionsEntity(null,3.4, now.plusDays(1)),
                new FieldConditionsEntity(null,3.4, now.plusDays(2))
        );
        repository.saveAll(list);

        String dateFrom = now.minusDays(7).format(DateTimeFormatter.ISO_INSTANT);
        String dateTo = now.format(DateTimeFormatter.ISO_INSTANT);
        String url = MessageFormat.format("/api/field-statistics?from={0}&to={1}", dateFrom, dateTo);

        FieldStatistics actual = restTemplate.getForEntity(url, FieldStatistics.class).getBody();

        FieldStatistics expected = new FieldStatistics(new Vegetation(0.3, 0.7, 0.5));
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
