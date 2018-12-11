package io.leres.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.leres.util.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public abstract class SharedStepDefs {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    protected ObjectMapper mapper = new CustomObjectMapper();

    protected void setUpWebContext() {
        mvc = webAppContextSetup(webApplicationContext).build();
    }

}
