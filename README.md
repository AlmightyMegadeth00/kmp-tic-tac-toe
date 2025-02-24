This is a Kotlin Multiplatform project targeting Android, iOS.

Basic tic tac toe game with an AI that has multiple difficulty settings and selects moves strategically.  Currently it only outputs the game in terminal and the difficulty needs to be changed in the dependency SkynetCpu (lol), but I will add the UX in compose and add more reactive programming as well as a single player mode when I get the chance to work on it.
<br/> 
easy - randomly generated moves <br/> 
medium - prioritizes winning moves <br/> 
hard - will select moves in the most number of remaining winning combinations, and uses partial transects in a 3D array of winning combos to block opponents from winning in the next round, and prioritizes winning combinations
