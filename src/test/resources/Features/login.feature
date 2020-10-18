@tag
Feature: Doc
  I want to use this template for my feature file

  @smoke
  Scenario: SuccessfulLogin
    Given User is on the Login Page
    And Enter username and password
    And Click Login
    Then User should land on Homepage

  @smoke
  Scenario: SuccessfulLogout
    Given User is on Homepage
    And Click Logout
    Then User should land on Logout Page
