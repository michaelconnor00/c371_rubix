package rubix_cube;

/**
 * This Enum provides static data for the predefined faces of the Rubix Cube.
 *
 * Created by michaelconnor on 2016-01-19.
 */
public enum Face {
    XOrigin("X", 0, true),
    XOpposite("X", 1, false), // Two faces perpendicular to the x-axis
    YOrigin("Y", 2, true),
    YOpposite("Y", 3, false), // Two faces perpendicular to the y-axis
    ZOrigin("Z", 4, true),
    ZOpposite("Z", 5, false)  // Two faces perpendicular to the z-axis
    ;

    private String axis;  // The axis the face is perpendicular to
    private int index;  // The 1D index for this face
    private boolean atOrigin;

    /**
     * The constructor for the Face enums
     * @param axis: The axis the face is perpendicular to. This means that for this face,
     *            all coordinates are fixed for axis. It also means:
     *            - Origin faces: the axis coordinate is 0, and for
     *            - Opposite faces: the axis coordinate is size-1.
     * @param index This index is used to calculate the 1D array index of the face tiles.
     */
    private Face (String axis,int index, boolean atOrigin){
        this.atOrigin = atOrigin;
        this.axis = axis;
        this.index = index;
    }


    public String getAxis() {
        return axis;
    }

    public int getFaceIndex() {
        return index;
    }

    public boolean isAtOrigin() {
        return atOrigin;
    }
}
