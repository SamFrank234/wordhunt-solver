# wordhunt-solver
Solves GamePigeon WordHunt and finds optimal path through board

Usage: 
1. Set up JavaFX https://openjfx.io/openjfx-docs/
2. Import the packages "solver" and "javafx"
3. Initialize a new WordHunt object. The default empty constructor will create a 4x4 board, or you can add an integer argument to set the side length of the board to a custom value.
4. Call solve("yourletters"), replacing "yourletters" with the letters from the board. Enter each row in order without any spaces all as one string. 
5. The results will print to your terminal.




About WordHunt:
WordHunt is a game included in the iMessage extension GamePigeon. The basic version of the game creates a 4x4 grid of letters, and each player has 80 seconds to find as many words as possible using the letters in the grid. Players can only connect adjacent letters, and the same letter cannot be reused within a single word. Longer words are worth more points, but the socring is not proportional to length. The smallest valid words are three lettters long, and worth 100 points. 

About the Solver:
This solver finds all valid words in a given WordHunt board. First, the solver creates a new WordHunt game with a board. The size of the board can be customized to squares of different sizes. Then, each square in the board is initialized as a new, empty LetterNode, and each node's neighbor list is initialized. Then, the solver accepts a String of all the letters in the board, starting with the top row in the grid and moving down. Then, the solver finds all valid words through a bounded depth-first search, checking against a dictionary to bound any branch (e.g. "QW") that cannot form a valid word. All comparisons to the dictionary are done in logarithmic time. Words are sorted by length, and also grouped by starting letter. 

About the PathFinder:
The Path Finder is based off the premise that optimizing performance in WordHunt means making it easy to score as many points as quickly as possible. Most WordHunt Solvers out there sort words from longest (most points) to shortest (fewest points). However, even with the words listed, it can be hard to find them. I decided to group all the words by the letter they start with. That way, it is easy to find where the next word starts. In addition, the player can recognize patterns that appear in similar words, which also speeds up the process of actually entering the words. Once you've entered all the words from one letter, the next group of words should start with an adjacent letter, so that you can find the new starting point quickly. The problem then becomes to find the optimal path through the board that will put the best starting letters at the beginning.

The first decision I had to make was how to compare letters. I started by simply calculating the total points of each letter's word list divided by the total length of all those words. However, I found this metric was undervaluing letters that had long lists of words, including three-letter words that drove down the average. To compensate, I created a score index for each letter which is just (total points)^1.25/(total letters). Then, I had to figure out how to compare different paths to each other. I realized that what I really wanted was to find the most *unequal* path, where the first letters have the highest score index and the second letters have the lowest score index. Luckily, there is an established method for measuring this sort of inequality in Economics called the Gini Coefficient. Inspired by the Gini, I decided to integrate each path as I constructed it, with each node keeping track of the path's cumulative area. The best path became the path with the greatest area. At the end of the program, I include an option to display a line graph of the optimal path that shows how its area compares to that of the mean path.

Finally, I had to actually find the optimal path. I realized this problem was very similar to the classic task of finding the shortest path between two nodes. In my case, however, all the paths would ultimately be the same length. I implemented a modified Djikstra's algorithm to solve this custom problem. In the final implementation, I ended up actually finding the pat with the smallest area, for two reasons. First, my Path classed used a Last In First Out implementation, so when I displayed the path, the letters came out in reverse order and formed the optimal (greatest area) path. Second, a minimum path made it easier to improve the Djikstra search, by not extending any paths that had already exceeded the area of the minimum path so far. 


Summary:
Overall I really enjoyed this project. I got more practice with Object Oriented Programming, and implemented many of the new data structures I learned in CS102 this semester (Binary Search Tree, Doubly Linked List, Binary Heap, Priority Queue, Stack). I also employed some of the encapsulation techniques I've been learning to organize my project structure and I'm happy with the readability of my code. I'm not sure if my hypothesis about the path is correct, but it gave me a chance to work in some knowledge from my Econ classes and design an algorithm from scratch to solve a problem. I also became comfortable with various search methods, including Djikstra, which will be useful when I work on ML projects. Finally, I added some experience with JavaFX to my toolbelt.
