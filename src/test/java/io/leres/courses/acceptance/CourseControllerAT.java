package io.leres.courses.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.leres.AcceptanceTests;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:acceptance/courses")
@Category(AcceptanceTests.class)
public class CourseControllerAT {
}
