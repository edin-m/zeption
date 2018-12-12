package io.leres.controllers;

import io.leres.IntegrationTests;
import io.leres.courses.Course;
import io.leres.courses.CourseFixture;
import io.leres.courses.repo.CourseRepository;
import io.leres.teachers.Teacher;
import io.leres.teachers.TeacherFixture;
import io.leres.teachers.repo.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
public class TeacherControllerIT {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void testRemovingTeacherRemovesItselfFromCourses() throws Exception {
        Course course = CourseFixture.getExampleCourse();
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());
        course.addTeacher(teacher);
        course = courseRepository.save(course);

        mvc.perform(delete(String.format("/teachers/%s", teacher.getId())))
                .andExpect(status().isOk());

        course = courseRepository.getOne(course.getId());
        assertThat(course.getTeachers()).hasSize(0);
    }
}
