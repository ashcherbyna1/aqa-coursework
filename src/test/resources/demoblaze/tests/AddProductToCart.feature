Feature: [TS] Items purchase

  @jiralinkid-6
  Scenario: Verify massage that product added in to basket
    Given Select product from main page
    And Select add option
    Then Verify message "Product added"

  @jiralinkid-7
  Scenario: Verify that product added in to basket
    Given Select product from main page
    And Select add option
    And Select basket option
    Then Verify title "Samsung galaxy s6"