Feature: (Automation) Login to the portal
  As a user
  I want to login to the portal
  So that I can access the portal

  @wip
  Scenario: Login to the portal with valid credentials
    When user enter valid portal, username and password
    Then user should see be directed to main page and see the projects