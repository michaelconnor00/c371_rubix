package rubix_cube;

import java.util.Arrays;

/**
 * This class represents a slice on a rubix Cube. The slice is a connected set of tiles that
 * goes around the axis of rotation.
 * Created by michaelconnor on 2016-01-18.
 */
public class RubixSlice {

    // Array of all Tiles in the slice. Each Tile knows it's transition
    // from current location to CW or CCW move location.
    private final Tile[] sliceTiles;

    private final Face[] faceList;
    private final Axis axis;
    private final int sliceIndex;
    private final int size;

    public RubixSlice(int size, Axis axis, int sliceIndex){

        // Slice Index will be the slice that goes around the Axis, with an index from the
        // Origin equal to sliceIndex.
        this.size = size;
        this.faceList = axis.getRotationFaces();
        this.axis = axis;
        this.sliceIndex = sliceIndex;
        int numTilesPerSlice = 4*size;  // not including the ends

        // Create array of all Tiles for this slice
        this.sliceTiles = new Tile[numTilesPerSlice];
        generateTiles();

    }

    private void generateTiles(){
        int row;
        int face1DIndex;
        Face currFace;
        int newTileIndex;

        for (int faceIndex=0; faceIndex<faceList.length; faceIndex++){

            currFace = faceList[faceIndex];
            face1DIndex = currFace.getFaceIndex();
            row = 0;

            for(int j=0; j < size; j++){

                newTileIndex = size*faceIndex+j;

                // Initialize array with Tile
                this.sliceTiles[newTileIndex] = getSelf1DTile(face1DIndex, row, faceIndex);

                // Set CW/CCW translation index
                this.sliceTiles[newTileIndex].setNeighborIndex(
                        Direction.CW,
                        getNext1DIndex(Direction.CW, faceIndex, row)
                );
                this.sliceTiles[newTileIndex].setNeighborIndex(
                        Direction.CCW,
                        getNext1DIndex(Direction.CCW, faceIndex, row)
                );

                row++;
            }

        }
    }

    private Tile getSelf1DTile(int face1DIndex, int row, int currFaceIndex){
        switch(this.axis){
            case X:
                return new Tile(gameArrayOffset(face1DIndex) + this.size * sliceIndex + row);
            case Z:
                String faceAxis = this.faceList[currFaceIndex].getAxis();
                if (faceAxis.equals(Axis.Y.name())){
                    return new Tile(gameArrayOffset(face1DIndex) + this.size * row + sliceIndex);
                } else {
                    return new Tile(gameArrayOffset(face1DIndex) + this.size * sliceIndex + row);
                }
            case Y:
                return new Tile(gameArrayOffset(face1DIndex) + this.size * row + sliceIndex);
            default:
                throw new IllegalArgumentException();
        }
    }

    private int getNext1DIndex(Direction direction, int faceIndex, int row){
        switch(direction) {
            case CW:
                if (faceIndex == 0) {
                    return calculate1DIndex(this.faceList.length - 1, faceIndex, row);
                } else {
                    return calculate1DIndex(faceIndex - 1, faceIndex, row);
                }
            case CCW:
                if (faceIndex == this.faceList.length-1) {
                    return calculate1DIndex(0, faceIndex, row);
                } else {
                    return calculate1DIndex(faceIndex + 1, faceIndex, row);
                }
            default:
                throw new IllegalArgumentException();
        }
    }

    private int calculate1DIndex(int nextFaceIndex, int currFaceIndex, int row){

        boolean isCurrFaceAtOrigin = this.faceList[currFaceIndex].isAtOrigin(); // From
        boolean isNextFaceAtOrigin = this.faceList[nextFaceIndex].isAtOrigin(); // To

        // Determine if the transition options
        boolean isOrgToOrg = isCurrFaceAtOrigin && isNextFaceAtOrigin;
        boolean isOppToOpp = !isCurrFaceAtOrigin && !isNextFaceAtOrigin;

        int face1DIndex = this.faceList[nextFaceIndex].getFaceIndex();

        // Different translation based on axis and location of faces
        switch (this.axis){
            case X:
            // Around X axis
            if (isOppToOpp || isOrgToOrg){
                int destRow = getFlippedRow(row);
                return gameArrayOffset(face1DIndex) + this.size * this.sliceIndex + destRow; // Mirrored
            } else {
                return gameArrayOffset(face1DIndex) + this.size * this.sliceIndex + row; // Same
            }
            case Y:
            // Around Y axis
            if (isOppToOpp || isOrgToOrg){
                int destRow = getFlippedRow(row);
                return gameArrayOffset(face1DIndex) + this.size * destRow + this.sliceIndex; // Mirrored
            } else {
                return gameArrayOffset(face1DIndex) + this.size * row + this.sliceIndex; // Same
            }
            case Z:
                String currAxis = this.faceList[currFaceIndex].getAxis();
                String nextAxis = this.faceList[nextFaceIndex].getAxis();
                int destRow = getFlippedRow(row);
                if ((currAxis.equals(Axis.Y.name())) && (nextAxis.equals(Axis.X.name()))){
                    return gameArrayOffset(face1DIndex) + this.size * this.sliceIndex + destRow;
                } else if ((currAxis.equals(Axis.X.name())) && (nextAxis.equals(Axis.Y.name()))){
                    return gameArrayOffset(face1DIndex) + this.size * destRow + this.sliceIndex;
                }
            default:
                throw new IllegalArgumentException();
        }
    }

    private int gameArrayOffset(int face1DIndex){
        return face1DIndex * (this.size * this.size);
    }

    /**
     * This function is used to get the destination row value
     * when transitioning.
     * @param currRow the source row value.
     * @return
     */
    private int getFlippedRow(int currRow){
        return this.size - 1 - currRow;
    }
    

    public Tile[] getSliceTiles() {
        return sliceTiles;
    }

    public int[][] getSliceTransitionMap(Direction direction){
        int[][] transMap = new int[this.sliceTiles.length][2];
        for (int outer=0; outer < this.sliceTiles.length; outer++){
            transMap[outer] = new int[]{
                    this.sliceTiles[outer].getIndex(), // Self location
                    this.sliceTiles[outer].getNeighborIndex(direction) // Next location
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
