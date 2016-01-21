package rubix_cube;

/**
 * Created by michaelconnor on 2016-01-18.
 */
public class RubixSlice {
    // Array of all Tiles in the slice. Each Tile knows it's transition
    // from current location to CW or CCW move location.
    private Tile[] sliceTiles;

    public RubixSlice(int size, Axis axis, int sliceIndex){
        // Slice Index will be the slice that goes around the Axis, with an index from the
        // Origin equal to sliceIndex.

        int numTilesPerSlice = 6*size;

        // Create array of all Tiles for this slice
        this.sliceTiles = new Tile[numTilesPerSlice];
        Face[] faceList = axis.getRotationFaces(); // Order of list is CW rotation

        int faceIndex = 0;
        int faceValue;

        for (int i=0; i<numTilesPerSlice; i++){
            if (i % size == 0){faceIndex++;}
            faceValue = faceList[faceIndex].getValue();

            //Initialize array with Tiles and their moves
            this.sliceTiles[i] = new Tile(faceValue, );

            // Figure out Tile coord mapping.

        }

    }

    public int[] generateCoords(){

    }

    public Tile[] getSliceTiles() {
        return sliceTiles;
    }
}
