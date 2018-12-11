package io.leres.students.acceptance;

import cucumber.api.java.en.Then;
import io.leres.FirstApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = FirstApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@Slf4j
public class StudentStepDefs {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Then("verify there are no person records")
    public void verifyThereIsNoPersonRecords() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Person", Integer.class);
        assertThat(count).isEqualTo(0);
    }

}
