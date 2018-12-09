package io.leres.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.leres.entities.Person;
import io.leres.entities.Student;
import io.leres.util.CustomObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class StudentControllerIT {

    @Autowired
    private MockMvc mvc;

    private Student student;

    @Test
    public void testStudentController() throws Exception {
        Person person = new Person();
        person.setFirstName("first name");
        person.setLastName("last name");
        person.setSocialId("01234");

        ObjectMapper mapper = new CustomObjectMapper();
        String body = mapper.writeValueAsString(person);

        mvc.perform(post("/students")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGettingStudent() throws Exception {
        String result = mvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.info(result);

        ObjectMapper mapper = new CustomObjectMapper();
        Student student = mapper.readValue(result, Student.class);

        log.info(student.toString());
    }

//    @Test
//    public void testGettingAllStudent() throws Exception {
//        String result = mvc.perform(get("/students"))
////                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        log.info(result);
//
//        List<Student> students = JsonUtils.fromJsonArray(result);
//        assertThat(students).hasSize(1);
//    }


}
