package rubix_cube;

/**
 * Created by michaelconnor on 2016-01-19.
 */
public class Tile {
//    private Tile cwRotation;
//    private Tile ccwRotation;
    private int[] cwCoords;
    private int[] ccwCoords;
    private int[] coords; // [row, col]
    private Face myFace;

    public Tile(Face face, int row, int col){
        this.myFace = face;
        this.coords = new int[]{face.getValue(), row, col};
    }

    public void setNeighborCoords(Direction direction, Face face, int row, int col){
        switch (direction){
            case CW:
                this.cwCoords = new int[]{face.getValue(), row, col};
            case CCW:
                this.ccwCoords = new int[]{face.getValue(), row, col};
        }
    }

    public int[] getNeighborCoords(Direction direction){
        switch (direction){
            case CW:
                return this.cwCoords;
            case CCW:
                return this.ccwCoords;
            default:
                return null;
        }
    }

    public int[] getCoords(){
        return this.coords;
    }

    public Face getFace(){
        return this.myFace;
    }

}
