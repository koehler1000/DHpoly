Feature: Withdrawing
  As a player
  I have to withdraw money to pay my debts

  Scenario: The player owns enough money
  	Given I have to pay my debts
  	And I have enough money
  	Then I withdraw money
  	Then I pay my debts
  
  Scenario: The player does not own enough money, but has enough property
	Given I have to pay my debts
	And I do not have enough money
	And I have enough property
	Then I withdraw money
	Then I have to sell property
	Then I pay my debts
		
  Scenario: The player is too poor to pay his debts
	Given I have to pay my debts
	And I do not have enough money
	And I do not have enough property
	Then I withdraw money
	Then I see the message "You lost"
