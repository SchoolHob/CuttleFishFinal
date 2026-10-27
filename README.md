# Board Game Player
### By Harrison Peña and Logan DeLoureiro

## Synopsis
For our final project we are creating an application which allows you to play a selection of board games against a cpu or a another player, locally.

## Patterns
### Factory Method Pattern
In our code, we are implementing a factory method pattern to access instances of different games. The game factory pattern object will be called GameLibrary.
### Builder Pattern
In each of these games, we are allowing stylization of your game. You can select the board, background, and piece colors. To implement this we are using a builder pattern for a Style object like setPieceColor() which is used by a game object and passed in through the GameLibrary.
### Observers
Since our game is taking in player inputs, we are using observers to read these clicks and notify the game where it's needed. We also are going to use observers to notify players of game events, for example when its a player's turn or that the game ended. ClickHandler is a singleton pattern which notifies all subscribers of clicks through an onClick method on a input.Clickable interface. 
