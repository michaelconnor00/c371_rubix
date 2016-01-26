package rubix_cube;

/**
 * This class is used for storing data regarding coordinate values. Note that the orientation
 * of the coordinates is not known. The controller for the Coordinate class must be aware of orientation.
 *
 * Created by michaelconnor on 2016-01-23.
 */
public class Coordinate {

    private int sliceNum; // index of slice from origin
    private int faceCoord;  // use the face to switch on 0 or size -1
    private int faceRow;

    /**
     * Constructor for Corrdinates
     * @param sliceNum: The index of the slice from origin. Fixed for that "col".
     * @param faceCoord: The coordinate of the face. It is either 0 or Size-1. It is
     *                 fixed for all Tiles on the face.
     * @param faceRow: The row coordinate in the slice. From origin.
     */
    public Coordinate(int sliceNum, int faceCoord, int faceRow){
        this.faceCoord = faceCoord;
        this.sliceNum = sliceNum;
        this.faceRow = faceRow;
    }

    public int getSliceNum() {
        return sliceNum;
    }

    public int getFaceCoord() {
        return faceCoord;
    }

    public int getFaceRow() {
        return faceRow;
    }
}
