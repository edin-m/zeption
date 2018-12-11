package io.leres.teachers.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.leres.FirstApplication;
import io.leres.entities.Person;
import io.leres.teachers.Teacher;
import io.leres.teachers.TeacherFixture;
import io.leres.teachers.repo.TeacherRepository;
import io.leres.util.CustomObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = FirstApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@Slf4j
@AutoConfigureMockMvc
public class TeacherControllerStepDefs  {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private MockMvc mvc;

    private static ObjectMapper mapper = new CustomObjectMapper();

    private static long teacherId;

    @Given("empty teacher repo")
    public void emptyTeacherRepo() {
        teacherRepository.deleteAllInBatch();
    }

    @When("create a teacher")
    public void createTeacher() throws Exception {
        Person person = TeacherFixture.getExampleTeacher().getPerson();

        String body = mapper.writeValueAsString(person);

        String response = mvc.perform(post("/teachers")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Teacher teacher = mapper.readValue(response, Teacher.class);
        teacherId = teacher.getId();
        log.info(">>>>> {}", teacherId);
    }

    @Then("verify teacher is created")
    public void verifyTeacherIsCreated() throws Exception {
        Teacher teacher = getTeacherById(teacherId);
        assertThat(teacher.getPerson().getSocialId())
                .isEqualTo(TeacherFixture.getExampleTeacher().getPerson().getSocialId());
    }

    private Teacher getTeacherById(long teacherId) throws Exception {
        String result = mvc.perform(get(String.format("/teachers/%s", teacherId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return mapper.readValue(result, Teacher.class);
    }

    @When("delete the teacher")
    public void deleteTeacher() throws Exception {
        mvc.perform(delete(String.format("/teachers/%s", teacherId)))
                .andExpect(status().isOk());
    }

    @Then("verify teacher is deleted")
    public void verifyTeacherIsDeleted() throws Exception {
        mvc.perform(get(String.format("/teachers/%s", teacherId)))
                .andExpect(status().is(404));
    }
}
