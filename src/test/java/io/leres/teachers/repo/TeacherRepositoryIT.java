package io.leres.teachers.repo;

import io.leres.IntegrationTests;
import io.leres.teachers.Teacher;
import io.leres.teachers.TeacherFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherRepositoryIT {

    @Autowired
    private TeacherRepository teacherRepository;

    private Teacher exampleTeacher;

    @Before
    public void setUp() {
        teacherRepository.deleteAllInBatch();

        exampleTeacher = TeacherFixture.getExampleTeacher();
    }

    @Test
    public void testFindBySocialId() {
        teacherRepository.save(exampleTeacher);

        Teacher teacher = teacherRepository.findByPerson_SocialId(exampleTeacher.getPerson().getSocialId());

        assertThat(teacher.getPerson().getSocialId()).isEqualTo(exampleTeacher.getPerson().getSocialId());
    }

    @Test
    public void testCountBySocialId() {
        teacherRepository.save(exampleTeacher);

        int count = teacherRepository.countByPerson_SocialId(exampleTeacher.getPerson().getSocialId());

        assertThat(count).isEqualTo(1);
    }
}
