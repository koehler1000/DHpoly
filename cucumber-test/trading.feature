Feature: Trading
As a player
I want trade with another player

  Scenario: successful trade
    Given It is my turn
    And The other player will accept my trading offer
    Given It is my turn
    When I choose a player
    And I choose a street
    And I choose the ressources
    And I choose the currency
    Then A trading offer is sent to the player I selected
    Then The other player accepts the trading offer
    Then The ownerships should have transfered
    
  Scenario: unsuccessful trade
    Given It is my turn
    And The other player will deny my trading offer
    Given It is my turn
    When I choose a player
    And I choose a street
    And I choose the ressources
    And I choose the currency
    Then A trading offer is sent to the player I selected
    Then The other player denies the trading offer
    Then There should be a windows displaying "Trading failed"
