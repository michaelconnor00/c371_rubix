package rubix_cube;

/**
 * Created by michaelconnor on 2016-01-18.
 */
public enum Axis {
    X(new Face[]{Face.Z0_4, Face.Y1_3, Face.Z1_5, Face.Y0_2}), // CW order
    Y(new Face[]{Face.X0_0, Face.Z1_5, Face.X1_1, Face.Z0_4}), // CW order
    Z(new Face[]{Face.Y0_2, Face.X1_1, Face.Y1_3, Face.X0_0})  // CW order
    ;

    private Face[] rotationFaces;

    private Axis(Face[] rotationFaces){
        this.rotationFaces = rotationFaces;
    }

    public Face[] getRotationFaces(){
        return this.rotationFaces;
    }
}
