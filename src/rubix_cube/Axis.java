package rubix_cube;

/**
 * This enum provides dtatic data for the predefined axis of the rubix cube.
 *
 * Created by michaelconnor on 2016-01-18.
 */
public enum Axis {
    X(
        new Face[]{Face.ZOrigin, Face.YOpposite, Face.ZOpposite, Face.YOrigin}, // CCW order
        new Face[]{Face.XOrigin, Face.XOpposite},
        true
    ),
    Y(
        new Face[]{Face.XOrigin, Face.ZOpposite, Face.XOpposite, Face.ZOrigin}, // CCW order
        new Face[]{Face.YOrigin, Face.YOpposite},
        false
    ),
    Z(
        new Face[]{Face.YOrigin, Face.XOpposite, Face.YOpposite, Face.XOrigin}, // CCW order
        new Face[]{Face.ZOrigin, Face.ZOpposite},
        true
    );

    private Face[] rotationFaces;
    private Face[] endFaces;
    private boolean isTranslateBySlice;

    /**
     * Constructor for the Axis enum.
     * @param rotationFaces: an array of all the face that are connected when
     *                     a rotation happens around this axis.
     *                     - Note: the faces are in clockwise rotation order.
     */
    private Axis(Face[] rotationFaces, Face[] endFaces, boolean translateBySlice){
        this.isTranslateBySlice = translateBySlice;
        this.rotationFaces = rotationFaces;
        this.endFaces = endFaces;
    }

    public Face[] getRotationFaces(){
        return this.rotationFaces;
    }

    public Face[] getEndFaces() {
        return this.endFaces;
    }

    public boolean isTranslateBySlice() {
        return isTranslateBySlice;
    }
}
