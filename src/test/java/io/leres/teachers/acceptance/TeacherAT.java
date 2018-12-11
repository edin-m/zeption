package io.leres.teachers.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.leres.AcceptanceTests;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:acceptance/teachers")
@Category(AcceptanceTests.class)
public class TeacherAT {
}
