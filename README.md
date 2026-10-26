#Board Game Player
### By Harrison Pena and Logan DeLoureiro

##Synopsis
For our final project we are creating an application which allows you to play a group of select board games against a cpu or a another player.

##Patterns
###Factory Method Pattern
In our code, we are implementing a factory method pattern to implement methods to do things like play a turn or evaluate a win condition of various games which inherit the game class.
###Builder Pattern
In each of these games, we are allowing stylization of your game. You can select the board, background, and piece colors. To implement this we are using a builder pattern inside the games with methods like setPieceColor().
###Observers
Since our game is taking in player inputs, we are using observers to read these clicks and notify the game where it's needed. We also are going to use observers to notify players of game events, like that it's there turn or that the game ended.
