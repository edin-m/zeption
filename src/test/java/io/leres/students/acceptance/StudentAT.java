package io.leres.students.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.leres.AcceptanceTests;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:acceptance/students")
@Category(AcceptanceTests.class)
public class StudentAT {
}
