Feature: Student relationships
  
  Scenario: Person gets deleted with student
    Given create a student
    When delete the student
    Then verify there are no person records
