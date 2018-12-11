package io.leres.students.repo;

import io.leres.IntegrationTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepoIT {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {

    }

    @Test
    public void testSavingStudentSavesPerson() {
    }

    @Test
    public void testRemovingStudentRemovesPerson() {

    }

}
