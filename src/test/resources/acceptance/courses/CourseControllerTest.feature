@restApiIntegration
Feature: Example managing courses

    Scenario: create a course
      Given empty course repo
      When create a course
      Then verify course id 1

    Scenario: delete a course
      Given empty course repo
      And create a course
      When delete the course id 2
      Then verify there is no course id 2
