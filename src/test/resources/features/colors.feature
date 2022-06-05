Feature: the colors can be retrieved
  Scenario: client makes call to GET /api/v1/colors
    When the client calls /api/v1/colors
    Then the client receives status code of 200
    And the response has 5 colors