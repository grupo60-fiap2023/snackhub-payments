Feature: Order Qr Code

  Scenario: Creating a merchant order QR code
    Given a user with id "165656656702"
    When the user requests to create a merchant order QR code
    Then the system should generate a QR code successfully
