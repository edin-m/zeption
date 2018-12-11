package io.leres.students.acceptance;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.leres.FirstApplication;
import io.leres.entities.Person;
import io.leres.students.Student;
import io.leres.helpers.SharedStepDefs;
import io.leres.students.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = FirstApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@Slf4j
public class StudentControllerStepDefs extends SharedStepDefs {

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        setUpWebContext();
    }

    @Given("empty student repo")
    public void givenEmptyDb() {
        studentRepository.deleteAllInBatch();
    }

    @When("create a student")
    public void createStudent() throws Exception {
        Person person = new Person();
        person.setFirstName("first name");
        person.setLastName("last name");
        person.setSocialId("01234");

        String body = mapper.writeValueAsString(person);

        mvc.perform(post("/students")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @When("update the student (\\d+)")
    public void updateTheStudent(long studentId) throws Exception {
        Person person = new Person();
        person.setFirstName("first name modified");
        person.setLastName("last name");

        mvc.perform(put(String.format("/students/%s/person", studentId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Then("verify student id (\\d+) first name is (.+)$")
    public void verifyStudentFirstName(long studentId, String firstName) throws Exception {
        Student retrievedStudent = getStudentById(studentId);
        assertThat(retrievedStudent.getPerson().getFirstName()).isEqualTo(firstName);
    }

    @Then("verify student id (\\d+) last name is (.+)$")
    public void verifyStudentLastName(long studentId, String lastName) throws Exception {
        Student retrievedStudent = getStudentById(studentId);
        assertThat(retrievedStudent.getPerson().getLastName()).isEqualTo(lastName);
    }

    private Student getStudentById(long studentId) throws Exception {
        String result = mvc.perform(get(String.format("/students/%s", studentId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return mapper.readValue(result, Student.class);
    }

    @When("delete the student id (\\d+)")
    public void deleteStudent(long studentId) throws Exception {
        mvc.perform(delete(String.format("/students/%s", studentId)))
                .andExpect(status().isOk());
    }

    @Then("verify there is no student id (\\d+)")
    public void verifyNoStudentExists(long studentId) throws Exception {
        mvc.perform(get(String.format("/students/%s", studentId)))
                .andExpect(status().is(404));
    }

}
