package rubix_cube;

/**
 * Created by michaelconnor on 2016-01-27.
 */
public class Move {
    private Axis axis;
    private Direction direction;
    private int sliceIndex;

    public Move(Axis axis, Direction direction, int sliceIndex){
        this.axis = axis;
        this.direction = direction;
        this.sliceIndex = sliceIndex;
    }

    public String toString(){
        String temp = "(";
        temp += this.axis.name() + ",";
        temp += this.direction.name() + ",";
        temp += this.sliceIndex + ")";
        return temp;
    }

    public Axis getAxis() {
        return axis;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSliceIndex() {
        return sliceIndex;
    }
}
