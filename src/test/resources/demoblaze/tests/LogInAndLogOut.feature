Feature: [TS] Log in and Log out functionality

  @jiralinkid-2
  Scenario: Verify Login functionality for existing User
    Given User select Log in option
    When User enters credential
      | username    | password |
      | TestuserAS2 | Zse45aw3 |
    And Click Log in option
    Then Verify welcome message "Welcome TestuserAS2"


  @jiralinkid-3
  Scenario: Verify Login functionality for existing User
    Given User select Log in option
    When User enters credential
      | username    | password |
      | TestuserAS2 | Zse45aw3 |
    And Click Log in option
    And Select Log Out
    Then Verify that "Log in" option is displayed