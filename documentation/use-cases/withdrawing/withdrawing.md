# Use-Case Specification: Withdrawing

## 1. Withdrawing

### 1.1 Brief Description

This Use-Case, called “Withdrawing”, describes the process which is used every time, when the player has to pay for something.  If the player doesn’t have enough money in his account, he is able to sell some of his property to keep playing. If he doesn’t have any property left, the game displays the message “You lost!”. This means that the player has no money left to participate in the game.

## 2. Flow of Events

### 2.1 Basic Flow

![Withdrwaing Use-Case-Diagram](https://raw.githubusercontent.com/koehler1000/DHpoly/master/documentation/use-cases/withdrawing/Withdrawing_UCD.png)

.feature File: [Link]( https://github.com/koehler1000/DHpoly/blob/master/cucumber-test/withdrawing.feature)



## 3. Precondition

One player has to pay for something in the game. This means that the system has to check if he has enough money left.

## 4. Postcondition

After this Use-Case is finished, there are two possible endings. One is that the player has enough money in his account. He simply pays and the game goes on. Another possibility is that the player hasn't got enough money in his account and he doesn't own property. This means that the player lost the game.

## 5. Visual Output

![](https://raw.githubusercontent.com/koehler1000/DHpoly/master/documentation/use-cases/withdrawing/withdraw1.png)

![](https://raw.githubusercontent.com/koehler1000/DHpoly/master/documentation/use-cases/withdrawing/withdraw2.png)
