Feature: Notification

  Scenario: Successful notification
    Given a merchant order with id 123
    When a notification with topic "payment.success" is received
    Then the payment for the order should be marked as successful
