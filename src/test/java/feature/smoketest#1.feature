Feature: smoke test #1, web app shows correct name for hello rest service

  Scenario: go through hello rest service

    Given open spring-mvc webapp hello rest service with name parameter "Almas"
    Then loaded page should print out "Hello Almas"