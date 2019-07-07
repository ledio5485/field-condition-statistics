package at.farm.fieldconditionstatistics.repository;

import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DBFieldConditionsRepositoryITest {

    @Autowired
    private DBFieldConditionsRepository sut;

    @After
    public void tearDown() {
        sut.deleteAll();
    }

    @Test
    public void add() {
        ZonedDateTime fixedZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        FieldConditionsEntity entity = new FieldConditionsEntity(null, 0.5, fixedZonedDateTime);

        sut.add(entity);

        FieldConditionsEntity actual = sut.findAll().iterator().next();

        assertThat(actual).isEqualToIgnoringGivenFields(entity, "id");
    }

    @Test
    public void getStatistics() {
        ZonedDateTime fixedZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        ArrayList<FieldConditionsEntity> entities = Lists.list(
                new FieldConditionsEntity(null, 0.4, fixedZonedDateTime),
                new FieldConditionsEntity(null, 0.5, fixedZonedDateTime),
                new FieldConditionsEntity(null, 0.6, fixedZonedDateTime)
        );
        sut.saveAll(entities);

        Statistics actual = sut.getStatistics(ZonedDateTime.from(fixedZonedDateTime).minusSeconds(5), fixedZonedDateTime);

        Statistics expected = new Statistics(0.4, 0.6, 0.5);
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}