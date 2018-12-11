@restApiIntegration
Feature: Example managing students

    Scenario: create a student
        Given empty student repo
        When create a student
        Then verify students first name is first name

    Scenario: update the student
        Given empty student repo
        And create a student
        When update the student
        Then verify students first name is first name modified
        Then verify students last name is last name

    Scenario: delete the student
        Given empty student repo
        And create a student
        When delete the student
        Then verify there is no student

