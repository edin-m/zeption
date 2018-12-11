package io.leres.students.repo;

import io.leres.IntegrationTests;
import io.leres.students.Student;
import io.leres.students.StudentFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepoIT {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Student exampleStudent;

    @Before
    public void setUp() {
        studentRepository.deleteAllInBatch();

        exampleStudent = StudentFixture.getExampleStudent();
    }

    @Test
    public void testFindBySocialId() {
        studentRepository.save(exampleStudent);

        Student student = studentRepository.findByPerson_SocialId(exampleStudent.getPerson().getSocialId());

        assertThat(student.getPerson().getFirstName()).isEqualTo(exampleStudent.getPerson().getFirstName());
    }

}
