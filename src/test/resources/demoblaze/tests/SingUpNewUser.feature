Feature: [TS] User registration

  @jiralinkid-1
  Scenario: Verify registration for new user
    Given User select Sign up option
    When User enters valid credential
      | username | password |
      | Testuser | Zse45aw3 |
    And Select Registration option
    Then Information massage is displayed "Sign up successful."
    And Close modal window


