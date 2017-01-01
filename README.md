# recursive-maze
Java program implementing recursion to solve a maze.

Maze is solved utilizing a depth first search, thus the solution to the maze might not be the shortest possible path. 

A text file must be provided as an argument that outlines a maze design. 

Example Text File:

15 15
###############
#             #
#   ###       #
#   #  G      #
#   #         #
#             #
#             #
#  ############
#          ####
#    ###      #
#     #       #
#      #      #
#       #     #
#           S #
###############

This file specifies the size of the maze in the first line. 'S' and 'G' represent start and goal. The maze is solved once 'G' is found. 
