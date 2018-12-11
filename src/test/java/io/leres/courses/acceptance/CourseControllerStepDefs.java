package io.leres.courses.acceptance;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.leres.FirstApplication;
import io.leres.courses.Course;
import io.leres.courses.repo.CourseRepository;
import io.leres.helpers.SharedStepDefs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = FirstApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@Slf4j
public class CourseControllerStepDefs extends SharedStepDefs {

    @Autowired
    private CourseRepository courseRepository;

    @Before
    public void setUp() {
        setUpWebContext();
    }

    @Given("empty course repo")
    public void first() {
        courseRepository.deleteAllInBatch();
    }

    @When("create a course")
    public void createCourse() throws Exception {
        Course course = new Course("CS-101");
        String body = mapper.writeValueAsString(course);

        mvc.perform(post("/courses")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Then("verify course id (\\d+)")
    public void verifyCourseIsCreted(long courseId) throws Exception {
        Course course = getCourseById(courseId);
        assertThat(course.getName()).isEqualTo("CS-101");
    }

    private Course getCourseById(long courseId) throws Exception {
        String result = mvc.perform(get(String.format("/courses/%s", courseId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return mapper.readValue(result, Course.class);
    }

    @When("delete the course id (\\d+)")
    public void deleteCourse(long courseId) throws Exception {
        mvc.perform(delete(String.format("/courses/%s", courseId)))
                .andExpect(status().isOk());
    }

    @Then("verify there is no course id (\\d+)")
    public void verifyNoCourseExists(long courseId) throws Exception {
        mvc.perform(get(String.format("/courses/%s", courseId)))
                .andExpect(status().is(404));
    }
}
