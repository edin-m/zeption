package io.leres.students.acceptance;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.leres.AcceptanceTests;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:students.integration")
@Category(AcceptanceTests.class)
public class StudentControllerAT {
}
