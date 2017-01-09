Tricky Triangle
======

# overview
Program to solve the tricky triangle problem (of arbitrary size and starting position)!
	(for those who are not familiar, a picture of the game is below)

[logo]: http://www.liebcraft.com/uploads/4/6/9/0/4690740/__3709963_orig.jpg "Logo Title Text 2"


# nitty gritty
The program creates a stimulated triangle, and then creates a Trie of possible triangle states by recursively making every possible move in the game. To increased performance, while recursing, new gamestates are ignored if they have been found already 
	
The program then uses a recursive Breadth-first search algorithm to find all the game states at the lowest level of the Trie, which are likely winning game states by merit of the fact that they have made the greatest number of moves, and thus are likely to have only one piece left.
	 
	 
	 