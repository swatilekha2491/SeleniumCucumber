@tag
Feature: Register
  I want to use this template for my feature file

  @register
  Scenario: SuccessfulRegister
    Given User is on Login Page
    And Click Register
    And User should enter details
    Then User should land on Successful Registration page
    
  @register
  Scenario: UnlockUser
    Given User is again on Login Page
    And Enter admin username and password
    And admin clicks Login
    Then Admin unlocks the user
    
  @register
  Scenario: New User Login
    Given User is again on Login Page
    And Enter new username and password
    And Enter all mandatory details
    Then User is redirected to homepage
    
