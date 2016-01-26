package rubix_cube;

import java.util.Arrays;
import java.util.HashMap;


/**
 * The Rubix class represents the rubix cube and it's structure.
 * Created by michaelconnor on 2016-01-13.
 */
public class Rubix {
    private int size;
    private byte[] game_array = null;
    private HashMap<Axis, RubixSlice[]> game_moves;
    private HashMap<Face, RubixEnd> end_moves;

    /**
     * Rubix constructor.
     * @param size - the size of the game cube. ie - 2 means each face is 2x2.
     */
    public Rubix (int size){

        if(size < 2){
            throw new IllegalArgumentException();
        }

        this.size = size;

        // Colors will be byte data that represent colors:
        // 1 = RED, 2 = BLUE, 3 = YELLOW, 4 = GREEN, 5 = ORANGE, 6 = WHITE
        byte[] color_array = {1, 2, 3, 4, 5, 6};

        // Create solved cube
        this.game_array = initSolvedRubix(color_array, size*size);

        // Create move map
        this.game_moves = initGameMoves();

        this.end_moves = initEndMoves();

    }

    private byte[] initSolvedRubix(byte[] color_array, int num_tiles){
        // Set the game_array to a default saved cube state
        byte[] new_game_array = new byte[6*num_tiles];

        for (int i=0; i < color_array.length; i++){
            for (int j=0; j < num_tiles; j++){
                new_game_array[num_tiles*i+j] = color_array[i];
            }
        }

        return new_game_array;
    }

    private HashMap<Axis, RubixSlice[]> initGameMoves(){
        // Create Map for axis, Slices
        HashMap<Axis, RubixSlice[]> new_game_moves = new HashMap<Axis, RubixSlice[]>();

        // Slice Maps
        RubixSlice[] x_slices = generateSlices(this.size, Axis.X);
        RubixSlice[] y_slices = generateSlices(this.size, Axis.Y);
        RubixSlice[] z_slices = generateSlices(this.size, Axis.Z);

        // Mapping for slices
        new_game_moves.put(Axis.X, x_slices);
        new_game_moves.put(Axis.Y, y_slices);
        new_game_moves.put(Axis.Z, z_slices);


        return new_game_moves;
    }

    private HashMap<Face, RubixEnd> initEndMoves(){
        HashMap<Face, RubixEnd> endMoves = new HashMap<Face, RubixEnd>();

        for (Face face : Face.values()){
            endMoves.put(face, new RubixEnd(face, this.size));
        }

        return endMoves;
    }

    private RubixSlice[] generateSlices(int size, Axis axis){
        RubixSlice[] newSlices = new RubixSlice[size];
        for (int i=0; i<size; i++){
            newSlices[i] = new RubixSlice(size, axis, i);
        }
        return newSlices;
    }

    public void move (Axis axis, Direction direction, int slice_index){
        // A move around the x-axis involves all faces except X0 and X1. Similar for y and z
        RubixSlice slice = game_moves.get(axis)[slice_index];

        int[][] moveTransitionMapping = slice.getSliceTransitionMap(direction);

        //Copy game array
        byte[] tempGameArray = new byte[this.game_array.length];
        System.arraycopy(this.game_array, 0, tempGameArray, 0, this.game_array.length);

//        for (int[] mapPair : moveTransitionMapping){
//            // Copy the color at the from Index[0], and paste it at the to Index[1]
//            this.game_array[mapPair[1]] = tempGameArray[mapPair[0]];
//        }
        applyMoves(moveTransitionMapping, tempGameArray);

        // If slice == 0 || slice == size-1, rotate ends
        if (slice_index == 0 || slice_index == this.size-1){
            int index;
            if (slice_index == 0){
                index = 0;
            } else {
                index = 1;
            }
            Face endFace = axis.getEndFaces()[index];
            moveTransitionMapping = this.end_moves.get(endFace).getEndTransitionMap(direction);
        }

        applyMoves(moveTransitionMapping, tempGameArray);

    }

    private void applyMoves(int[][] mapping, byte[] tempGameArray){
        for (int[] mapPair : mapping){
            // Copy the color at the from Index[0], and paste it at the to Index[1]
            this.game_array[mapPair[1]] = tempGameArray[mapPair[0]];
        }
    }


    private int gameArrayOffset(int face1DIndex){
        return face1DIndex * (this.size * this.size);
    }

    private Integer[][] get2DFaceArray(Face face){
        Integer[][] face2DArray = new Integer[this.size][this.size];
        int offset = gameArrayOffset(face.getFaceIndex());
        switch(face){
            case XOrigin:
            case ZOpposite:
            case YOrigin:
                for(int i=this.size-1; i>=0; i--){
                    //where i is the column
                    for(int j=0; j<this.size; j++){
                        //where j is the row
                        face2DArray[i][j] = (int) this.game_array[offset + this.size * j + (this.size-1-i)];
                    }
                }
                break;
            case XOpposite:
            case ZOrigin:
                for(int i=this.size-1; i>=0; i--){
                    //where i is the column
                    for(int j=this.size-1; j>=0; j--){
                        //where j is the row
                        face2DArray[i][j] = (int) this.game_array[offset + this.size * (this.size-1-j) + (this.size-1-i)];
                    }
                }
                break;
            case YOpposite:
                for(int i=0; i<this.size; i++){
                    //where i is the column
                    for(int j=0; j<this.size; j++){
                        //where j is the row
                        face2DArray[i][j] = (int) this.game_array[offset + this.size * j + i];
                    }
                }
                break;
        }
        return face2DArray;
    }

    ////////////////////////////////////
    ////  HELPERS FOR toString2D  //////
    ////////////////////////////////////

    private String generateFaceFormat(){
        String format = "";
        for (int i=0; i<this.size; i++){
            format += " |%" + (i+1) + "$" + 2 + "s";
        }
        return format;
    }

    private String buildOffset(String faceFormat, Face face){
        // Single face: Y_Opp or Y_Org
        String emptySpace = new String(new char[size*4]).replace('\0', ' ');
        String tempString = "";

        Integer[][] face2DArray = get2DFaceArray(face);

        for (int row=0; row<face2DArray.length; row++){
            tempString += emptySpace;
            tempString += String.format(faceFormat, (Object[]) face2DArray[row]);
            tempString += " |\n";
        }
        return tempString;
    }

    private String buildMiddle(String faceFormat){
        // FourFaces: X_Org, Z_Opp, X_Opp, Z_Org
        Face[] faces = new Face[]{Face.XOrigin, Face.ZOpposite, Face.XOpposite, Face.ZOrigin};
        String tempString = "";

        Integer[][] face2DArrayXorg = get2DFaceArray(faces[0]);
        Integer[][] face2DArrayZopp = get2DFaceArray(faces[1]);
        Integer[][] face2DArrayXopp = get2DFaceArray(faces[2]);
        Integer[][] face2DArrayZorg = get2DFaceArray(faces[3]);

        for (int row=0; row<face2DArrayXorg.length; row++){
            tempString += String.format(faceFormat, (Object[]) face2DArrayXorg[row]);
            tempString += String.format(faceFormat, (Object[]) face2DArrayZopp[row]);
            tempString += String.format(faceFormat, (Object[]) face2DArrayXopp[row]);
            tempString += String.format(faceFormat, (Object[]) face2DArrayZorg[row]);
            tempString += " |\n";
        }
        return tempString;
    }

    ///////////////////////////
    /// GETTERS and SETTERS ///
    ///////////////////////////

    /**
     * Function to serialize the game state into a format for a
     * neural network to train from.
     * @return
     */
    public String serializeGameMoveState(){
        String serialState = "";
        /*
         * Encodings: (from class)
         *  gameState, 6bits for tile, each bit represents a color, per tile
         *  moveTaken, 3bits for axis, 2bits for direction, 3bits for slice
         */

        return serialState;
    }

    public byte[] getGameArray(){
        return game_array;
    }

    public String toString1D(){
        return Arrays.toString(game_array);
    }

    public String toString2D() {
        String returnString = "\n";

        String faceFormat = generateFaceFormat();
        String seperator = new String(new char[4*size-1]).replace('\0', '-');
        seperator = "|" + seperator;
        seperator = seperator+seperator+seperator+seperator+"|";

        returnString += buildOffset(faceFormat, Face.YOpposite);
        returnString += " " + seperator + "\n";
        returnString += buildMiddle(faceFormat);
        returnString += " " + seperator + "\n";
        returnString += buildOffset(faceFormat, Face.YOrigin);

        return returnString;
    }


}
