package rubix_cube;

import java.util.Arrays;
import java.util.HashMap;


/**
 * The Rubix class represents the rubix cube and it's structure.
 * Created by michaelconnor on 2016-01-13.
 */
public class Rubix {

    private byte[] game_array = null;
    private HashMap<Axis, RubixSlice[]> game_moves;

    // Colors will be byte data that represent colors:
    // 1 = RED, 2 = BLUE, 3 = YELLOW, 4 = GREEN, 5 = ORANGE, 6 = WHITE
    private byte[] color_array = {1, 2, 3, 4, 5, 6};

    /**
     * Rubix constructor.
     * @param size - the size of the game cube. ie - 2 means each face is 2x2.
     */
    public Rubix (int size){
        int num_tiles = size*size;
        game_array = initSolvedRubix(num_tiles);

        // Create move map
        game_moves = initGameMoves(size, num_tiles);


    }

    public byte[] initSolvedRubix(int num_tiles){
        // Set the game_array to a default saved cube state
        byte[] new_game_array = new byte[6*num_tiles];

        for (int i=0; i < color_array.length; i++){
            for (int j=0; j < num_tiles; j++){
                new_game_array[num_tiles*i+j] = color_array[i];
            }
        }

        return new_game_array;
    }

    public HashMap<Axis, RubixSlice[]> initGameMoves(int size, int num_tiles){
        // Create Map for axis, Slices
        HashMap<Axis, RubixSlice[]> new_game_moves = new HashMap<Axis, RubixSlice[]>();

        // Slice Maps
        RubixSlice[] x_slices = generateSlices(size, Axis.X);
        RubixSlice[] y_slices = generateSlices(size, Axis.Y);
        RubixSlice[] z_slices = generateSlices(size, Axis.Z);

        // Mapping
        new_game_moves.put(Axis.X, x_slices);
        new_game_moves.put(Axis.Y, y_slices);
        new_game_moves.put(Axis.Z, z_slices);

        return new_game_moves;
    }

    public RubixSlice[] generateSlices(int size, Axis axis){
        RubixSlice[] newSlices = new RubixSlice[size];
        for (int i=0; i<newSlices.length; i++){
            newSlices[i] = new RubixSlice(size, axis, i);
        }
        return newSlices;
    }

    public void move (Axis axis, Direction direction, int slice_index){
        // A move around the x-axis involves all faces except X0 and X1. Similar for y and z
        RubixSlice slice = game_moves.get(axis)[slice_index];
        Tile[] sliceTiles = slice.getSliceTiles();

        // Store all writes in a list, ie (face, row, col, color)
        int[][] writeList = generateWriteList(sliceTiles, direction);

        if (writeList.length != sliceTiles.length){
            // Raise exception.
        }

        // Write the changes to the game state
        writeTransition(writeList);

    }

    private void writeTransition(int[][] writeList){

        for (int j=0; j<writeList.length; j++){
            //Write results to game state.
            // int[j][0-2] is location in game state,
            // int[j][3] is the new color
        }

    }

    private int[][] generateWriteList(Tile[] sliceTiles, Direction direction){

        int[] currCoord;
        int[] nextCoord;
        Tile currTile;
        Tile nextTile;

        // Store all writes in a list, ie (face, row, col, color)
        int[][] writeList = new int[sliceTiles.length][4];

        // Iterate through the tiles, get all transitions (coords, newColor)
        for (int i=0; i<sliceTiles.length; i++){
            // get coords (face, row, col)
            currTile = sliceTiles[i];
            currCoord = currTile.getCoords();

            nextTile = currTile.getRotationTile(direction);
            nextCoord = nextTile.getCoords();

            // 1. Store nextCoord's color, in form: (face, row, col, color)
            // 2. Collect all the writes
        }

        return writeList;
    }

    ///////////////////////////
    /// GETTERS and SETTERS ///
    ///////////////////////////

    public int getGameTileSize(){
        return game_array.length;
    }

    public byte[] getGameArray(){
        return game_array;
    }

    public String toString(){
        return Arrays.toString(game_array);
    }


}
