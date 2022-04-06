# wordhunt-solver
Solves GamePigeon WordHunt and finds optimal path through board


About WordHunt:
WordHunt is a game included in the iMessage extension GamePigeon. The basic version of the game creates a 4x4 grid of letters, and each player has 80 seconds to find as many words as possible using the letters in the grid. Players can only connect adjacent letters, and the same letter cannot be reused within a single word. Longer words are worth more points, but the socring is not proportional to length. The smallest valid words are three lettters long, and worth 100 points. 

About the Solver:
This solver finds all valid words in a given WordHunt board. First, the solver creates a new WordHunt game with a board. The size of the board can be customized to squares of different sizes. Then, the solver accepts a String of all the letters in the board, starting with the top row in the grid and moving down. Right now, this is hardcoded into the solver's main method, but can easily be configured for a Scanner. Then, the solver finds all valid words by 
