package rubix_cube;

/**
 * Created by michaelconnor on 2016-01-19.
 */
public enum Face {
    X0_0(Axis.X, 0, 0, new Axis[]{Axis.Y, Axis.Z}),
    X1_1(Axis.X, 1, 1, new Axis[]{Axis.Y, Axis.Z}), // Two faces perpendicular to the x-axis
    Y0_2(Axis.Y, 0, 2, new Axis[]{Axis.X, Axis.Z}),
    Y1_3(Axis.Y, 1, 3, new Axis[]{Axis.X, Axis.Z}), // Two faces perpendicular to the y-axis
    Z0_4(Axis.Z, 0, 4, new Axis[]{Axis.Y, Axis.X}),
    Z1_5(Axis.Z, 1, 5, new Axis[]{Axis.Y, Axis.X})  // Two faces perpendicular to the z-axis
    ;

    private Axis axis;
    private int value;
    private int index;
    private Axis[] axisOfRotation;

    private Face (Axis axis, int value, int index, Axis[] axisOfRotation){
        this.axis = axis;
        this.value = value;
        this.index = index;
        this.axisOfRotation = axisOfRotation;
    }


    public Axis getAxis() {
        return axis;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public Axis[] getAxisOfRotation() {
        return axisOfRotation;
    }
}
