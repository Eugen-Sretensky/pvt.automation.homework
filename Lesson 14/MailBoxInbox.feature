Feature: Inbox folder functionality

  @Negative
  Scenario: Send inbox message to Spam
    Given I am on main application page
    And   I login as correct user
    And   I open the first inbox letter context menu
    When  I send it to spam
    Then  I see notification message that it was sent to spam

  @Positive
  Scenario: Create New message and send it to 3 receivers
    Given I am on main application page
    And   I login as correct user
    When  I press Create New Message button
    Then  I see New Message creation form
    When  I click "To:" link
    Then  I see Contacts window
    When  I select 3 contacts
    Then  I see the selected contacts are highligted by checkbox icons
    When  I press Add button
    Then  I see New Message creation form with added receivers
    When  I fill Subject field
    And   I fill Message body
    Then  I see added info in the form
    When  I press send button
    Then  I see popup with success message
    And   I check all screenshots

