@restApiIntegration
Feature: Example managing students

    Scenario: create a student
        Given the web context
        And empty database
        When create a student
        Then verify student id 1 first name is first name

    Scenario: update the student
        Given the web context
        And empty database
        And create a student
        When update the student
        Then verify student id 2 first name is first name modified
        Then verify student id 2 last name is last name

    Scenario: delete the student
        Given the web context
        And empty database
        And create a student
        When delete the student id 3
        Then verify there is no student id 3

