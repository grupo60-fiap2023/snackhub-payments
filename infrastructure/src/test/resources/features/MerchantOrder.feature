Feature: Merchant Order API

  Scenario: Retrieving Merchant Orders by External Reference
    Given the external reference "aWRfcGVkaWRv"
    When the system receives a request to retrieve merchant orders
    Then the response should contain a list of merchant orders
