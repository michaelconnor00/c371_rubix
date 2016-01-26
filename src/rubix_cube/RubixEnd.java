package rubix_cube;

import java.util.Arrays;

/**
 * Created by michaelconnor on 2016-01-25.
 */
public class RubixEnd {

    private final Face face;
    private final Tile[] endTiles;
    private final int size;

    public RubixEnd(Face face, int size){
        this.face = face;
        this.size = size;

        this.endTiles = new Tile[this.size*this.size];
        generateTiles();
    }

//    private void generateTiles(){
//        int row;
//        int face1DIndex = this.face.getFaceIndex();
//
//        for (int i=0; i<this.endTiles.length; i++){
//            // Initialize array with Tile
//            this.endTiles[i] = getSelf1DTile(face1DIndex, row, faceIndex);
//
//            // Set CW/CCW translation index
//            this.endTiles[i].setNeighborIndex(
//                    Direction.CW,
//                    getNext1DIndex(Direction.CW, faceIndex, row)
//            );
//            this.endTiles[i].setNeighborIndex(
//                    Direction.CCW,
//                    getNext1DIndex(Direction.CCW, faceIndex, row)
//            );
//        }
//
//    }

    private void generateTiles(){
        int offset = gameArrayOffset(this.face.getFaceIndex());
        int tileIndex = 0;
        switch(this.face){
            case XOrigin:
            case ZOpposite:
            case YOrigin:
                for(int i=this.size-1; i>=0; i--){
                    //where i is the column
                    for(int j=0; j<this.size; j++){
                        //where j is the row
                        this.endTiles[tileIndex] = new Tile(offset + this.size * j + (this.size-1-i));
                        // Rotation function is Sz*dest_col+dest_row
                        // CW dest_col=sz-1-src_row, dest_row=src_col
                        this.endTiles[tileIndex].setNeighborIndex(
                                Direction.CW,
                                offset + this.size * (this.size-1-i) + j
                        );
                        // CCW dest_col=src_row, dest_row=size-1-src_col
                        this.endTiles[tileIndex].setNeighborIndex(
                                Direction.CCW,
                                offset + this.size * i + (this.size-1-j)
                        );
                        tileIndex++;
                    }
                }
                break;
            case XOpposite:
            case ZOrigin:
                for(int i=this.size-1; i>=0; i--){
                    //where i is the column
                    for(int j=this.size-1; j>=0; j--){
                        //where j is the row
                        this.endTiles[tileIndex] = new Tile(offset + this.size * (this.size-1-j) + (this.size-1-i));
                        // Rotation function is Sz*dest_col+dest_row
                        // CW dest_col=sz-1-src_row, dest_row=src_col
                        this.endTiles[tileIndex].setNeighborIndex(
                                Direction.CW,
                                offset + this.size * (this.size-1-i) + j
                        );
                        // CCW dest_col=src_row, dest_row=size-1-src_col
                        this.endTiles[tileIndex].setNeighborIndex(
                                Direction.CCW,
                                offset + this.size * i + (this.size-1-j)
                        );
                        tileIndex++;
                    }
                }
                break;
            case YOpposite:
                for(int i=0; i<this.size; i++){
                    //where i is the row
                    for(int j=0; j<this.size; j++){
                        //where j is the col
                        this.endTiles[tileIndex] = new Tile(offset + this.size * j + i);
                        // Rotation function is Sz*dest_col+dest_row
                        // CW dest_col=sz-1-src_row, dest_row=src_col
                        this.endTiles[tileIndex].setNeighborIndex(
                                Direction.CW,
                                offset + this.size * (this.size-1-i) + j
                        );
                        // CCW dest_col=src_row, dest_row=size-1-src_col
                        this.endTiles[tileIndex].setNeighborIndex(
                                Direction.CCW,
                                offset + this.size * i + (this.size-1-j)
                        );
                        tileIndex++;
                    }
                }
                break;
        }
    }

//    private int getNext1DIndex(Direction direction, int faceIndex, int row){
//        switch(direction) {
//            case CW:
//                if (faceIndex == 0) {
//                    return calculate1DIndex(this.faceList.length - 1, faceIndex, row);
//                } else {
//                    return calculate1DIndex(faceIndex - 1, faceIndex, row);
//                }
//            case CCW:
//                if (faceIndex == this.faceList.length-1) {
//                    return calculate1DIndex(0, faceIndex, row);
//                } else {
//                    return calculate1DIndex(faceIndex + 1, faceIndex, row);
//                }
//            default:
//                return -1; // TODO throw exception
//        }
//    }

    private int gameArrayOffset(int face1DIndex){
        return face1DIndex * (this.size * this.size);
    }

    public int[][] getEndTransitionMap(Direction direction){
        int[][] transMap = new int[this.endTiles.length][2];
        for (int outer=0; outer < this.endTiles.length; outer++){
            transMap[outer] = new int[]{
                    this.endTiles[outer].getIndex(), // Self location
                    this.endTiles[outer].getNeighborIndex(direction) // Next location
            };
        }
        return transMap;
    }

    public static String toString(int[][] transMap){
        String[] outputStr = new String[transMap.length];
        for (int mapPair=0; mapPair < transMap.length; mapPair++){
            outputStr[mapPair] = "(" + transMap[mapPair][0] + ", " + transMap[mapPair][1] + ")";
        }
        return Arrays.toString(outputStr);
    }
}
