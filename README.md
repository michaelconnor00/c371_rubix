# CPSC 371 Semester Project: Rubix Cube

## Version 0.1 Design notes

### Terms

+ ***Faces***: 
    * Per axis of rotation, there are 2 perpendicular faces.
    * Each face has S rows of tiles and S columns of tiles.
    * Face is a set of tiles, number from top left starting at zero, sequentially left to right.
+ ***Tiles***: 
    * Is a component of a face
    * Has a color, 0-6 (Red, Blue, Yellow, Green, Orange, White)
    * Has a position (Face, row, column)
+ ***Colors***: Red, Blue, Yellow, Green, Orange, White (indexed 0-6)
+ ***Slices***:
    * group of tiles that are connected in a cycle through 4 of 6 faces. 
    * This group is the set of tiles that transition fromo face to face, when a move is made.
    * Slices go "around" a single axis.
+ ***Moves***:
	* A tuple of (axis, direction, slice)
	* Axis: x, y, z
	* directions: CW, CCW
	* Slices around an axis are labelled from closest to the origin being 0.


### Orientation

- The rubix cube will be orientated at all times with an origin at back, bottom, left corner.
- Looking at the rubix cube, you see face 5, opposite is face 4.
- The face perpendicular on the left is face 0, on the right face 1.
- The face on the top is 3, face on the bottom is 2.
- This orientation does change, only slices of the rubix cube change.

### Data Structure: Cube

- Use an array of length S^2, where S is the size of the cube. 
- Likely a byte array to keep the size minimal. 
- Each index of the array is a tile of the cube. 
- The array can be logically thought to be divided into 6 (0-5 faces) sub-arrays that represent an array of tiles for each face. Each of these being size S.
- Each index (tile) of the array will have byte data representing the color of the tile. 

### Moves

- A move is performed on a slice. 
- The move can be either clockwise or counter-clockwise.
- When a move is performed, tiles will be swapped from a (row, col) pair on it's initial face, to a (row, col) pair on it's target face.
- After each move, the cube will be in a new state that will be recorded.

### Data Structure: Results

- Results will be written to memory to start.
- Data to be stored: (cubeArray, initialState, finalState)
- Structure for searching??