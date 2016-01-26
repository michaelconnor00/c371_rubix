package rubix_cube;

/**
 * This class represents a single tile on a face of the Rubix Cube.
 *
 * Created by michaelconnor on 2016-01-19.
 */
public class Tile {

    private int cwIndex;
    private int ccwIndex;
    private int index;

    public Tile(int index){
        this.index = index;
    }

    public void setNeighborIndex(Direction direction, int nextIndex){
        switch (direction){
            case CW:
                this.cwIndex = nextIndex;
                break;
            case CCW:
                this.ccwIndex = nextIndex;
                break;
        }
    }

    public int getNeighborIndex(Direction direction){
        switch (direction){
            case CW:
                return this.cwIndex;
            case CCW:
                return this.ccwIndex;
            default:
                return -1;
        }
    }

    public int getIndex(){
        return this.index;
    }

}
