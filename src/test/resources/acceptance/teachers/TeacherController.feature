Feature: Managing teachers

  Scenario: create a teacher
    Given empty teacher repo
    When create a teacher
    Then verify teacher is created

  Scenario: delete a teacher
    Given empty teacher repo
    And create a teacher
    When delete the teacher
    Then verify teacher is deleted
