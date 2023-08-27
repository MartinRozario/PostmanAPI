Feature: user registration
  @postApi
  Scenario Outline: Post API test
    Given user has API '<path>'
    When user hit '<name>' and '<job>'
    And call the API with body
    Then it will return created data
    Examples:
      | path | name| job |
      |users | name| QA |