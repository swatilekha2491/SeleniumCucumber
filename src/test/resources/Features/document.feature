@tag
Feature: Doc
  I want to use this template for my feature file



  @doc
  Scenario: Uploading a document through Registration
    Given upload document for user
    When search for the document
    Then Verify Document is present

  @tag2
  Scenario Outline: UnsuccessfulLogin
    Given Invalid User is on the Login Page
    And Enter username and password 
    And Click Login
    Then User is not logged in
    
   

    