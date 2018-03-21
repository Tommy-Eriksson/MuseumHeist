<H1>YH utbildning systemintegratör</h1>
<a href="https://www.lernia.se/utbildning/yrkeshogskoleutbildning/systemintegrator/">Lernia systemintegratör</a>
<br />
<br />
<h3>Museum Heist</h3>

<p>Frameworks:</p>
<pre>
JavaFX
JUnit 5
</pre>

Name:
MuseumHeist


Objects:
<pre>
GameState
	Start() // When game starts - Reset variables and positions
	Running() // When game is running
    	Points // Game Score
	End() // When game ends - Close, prepare for try again or close
	Points

Input
	Getcurrent pressed button

Board
	Gameboard // Array of boardstate
	Draw gameboard() // Draw the current state of the gameboard
	Treasure positions // Position on the board
	Laser positions // Position on the board
	Wall positions // Position on the board
	Door positions // Position on the board
		Door sends player to next room // Handle level finish
		
Karaktär
	X/Y position // Character position on the board
	Collision() // Check collision
	
Laser
	X/Y position // Position on the board
	On/Off // Boolean if the laser is on or off
	Beam() // Draw the beam on the gameboard
	CheckTime() // Checks if laser need to turn on
	
Treasure
	Point value // Treasure Score value (If we want diffrent treasures to have diffrent values)
</pre>
----

If we want diffrent levels we need to inject gameboard state when starting the game in gamestate.

----
<pre>
while game is on
  check for inputs
  if input
    handle input
      check player collision
      handle collision
  check laser time
  wait
</pre>
