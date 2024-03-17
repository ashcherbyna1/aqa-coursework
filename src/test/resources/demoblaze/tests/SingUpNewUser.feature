Feature: [TS] User registration

  @jiralinkid-1
  Scenario: Verify registration for new user
    Given User select Sign up option
    When User enters valid credential
      | username | password |
      | Testuser | Zse45aw3 |
    And Select Registration option
    Then Information message is displayed "Sign up successful."
    And Close modal window

  @jiralinkid-5
  Scenario: Verify invalid registration for new user
    Given User select Sign up option
    When User enters invalid credential
      | username | password |
      | Testuser1 | Zse45aw3 |
    And Select Registration option
    Then Information message is displayed "This user already exist."
    And Close modal window


