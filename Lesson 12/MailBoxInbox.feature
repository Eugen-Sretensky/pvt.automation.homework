Feature: Inbox folder functionality

  @Positive
  Scenario: Send inbox message to Spam
    Given I am on main application page
    And   I login as correct user
    And   I open the first inbox letter context menu
    When  I send it to spam
    Then  I see notification message that it was sent to spam

