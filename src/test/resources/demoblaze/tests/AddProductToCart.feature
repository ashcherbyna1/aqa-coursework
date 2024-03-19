Feature: [TS] Items purchase


  Scenario: Verify massage that product added in to basket
    Given Select product from main page
    And Select add option
    Then Verify message "Product added"


  Scenario: Verify that product added in to basket
    Given Select product from main page
    And Select add option
    And Select basket option
    Then Verify title "Samsung galaxy s6"


  Scenario: Verify that product can be removed from basket
    Given Select product from main page
    And Select add option
    And Select basket option
    And Select Delete option
    Then Verify that goods table empty


  Scenario: Verify successful purchase
    Given Select product from main page
    And Select add option
    And Select basket option
    And Select Place order option
    And Field valid data
      | name        | carddata      |
      | TestuserAS2 | Test123456789 |
    And Select option to Purchase
    Then Verify purchase message "Thank you for your purchase!"
