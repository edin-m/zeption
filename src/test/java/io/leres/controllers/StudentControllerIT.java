package io.leres.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.leres.IntegrationTests;
import io.leres.courses.Course;
import io.leres.courses.CourseFixture;
import io.leres.courses.repo.CourseRepository;
import io.leres.entities.Person;
import io.leres.students.Student;
import io.leres.students.StudentFixture;
import io.leres.students.repo.StudentRepository;
import io.leres.util.CustomObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
public class StudentControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    private ObjectMapper mapper = new CustomObjectMapper();

    @Before
    public void setUp() {
        studentRepository.deleteAllInBatch();
    }

    @Test
    public void testCreatingStudent() throws Exception {
        Person person = StudentFixture.getExampleStudent().getPerson();
        String body = mapper.writeValueAsString(person);

        mvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        assertThat(studentRepository.count()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void testDeletingStudentAssignedToCourse() throws Exception {
        Student student = studentRepository.save(StudentFixture.getExampleStudent());
        Course course = CourseFixture.getExampleCourse();
        course.addStudent(student);
        course = courseRepository.save(course);

        mvc.perform(delete(String.format("/students/%s", student.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        course = courseRepository.getOne(course.getId());
        assertThat(course.getStudents()).hasSize(0);
    }
}
