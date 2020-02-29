Feature: Spam folder functionality

  @Positive
  Scenario: Send spam message to Inbox
    Given I am on main application page
    And   I login as correct user
    And   I am in Spam folder
    And   I open the first spam letter context menu
    When  I send it to inbox
    Then  I see notification message that it was sent to inbox