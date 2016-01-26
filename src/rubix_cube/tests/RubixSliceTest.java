package rubix_cube.tests;

import org.junit.Test;
import rubix_cube.Axis;
import rubix_cube.Direction;
import rubix_cube.RubixSlice;

import java.util.Arrays;
import static org.junit.Assert.*;

/**
 * Created by michaelconnor on 2016-01-25.
 */
public class RubixSliceTest {

    @Test
    public void testRubixSliceSize4() throws Exception {
        int size = 4;
        Axis axis = Axis.Y;
        int sliceIndex = 1;

        RubixSlice testSlice = new RubixSlice(size, axis, sliceIndex);

        assertNotNull(testSlice);

        int[][] transMap = testSlice.getSliceTransitionMap(Direction.CW);
//        System.out.println(RubixSlice.toString(transMap));

        assertEquals(transMap.length, size*4);

        assertEquals(checkIndexes(size, transMap)/4, size);
    }

    @Test
    public void testRubixSliceSize7() throws Exception {
        int size = 7;
        Axis axis = Axis.Y;
        int sliceIndex = 2;

        RubixSlice testSlice = new RubixSlice(size, axis, sliceIndex);

        assertNotNull(testSlice);

        int[][] transMap = testSlice.getSliceTransitionMap(Direction.CW);

        assertEquals(transMap.length, size*4);

        assertEquals(checkIndexes(size, transMap)/4, size);
    }

    private int checkIndexes(int size, int[][] transMap){
        int[] fromIndexes = new int[transMap.length];
        int[] toIndexes = new int[transMap.length];

        for (int mapPair=0; mapPair < transMap.length; mapPair++){
            fromIndexes[mapPair] = transMap[mapPair][0];
            toIndexes[mapPair] = transMap[mapPair][1];
        }

        int checkComplete = 0;

        for (int j=0; j<4; j++){
            int to = j*size;
            int from = to+size;
            if (from == fromIndexes.length){
                from = 0;
            }
            int testValue;

            for (int i=0; i < size; i++){
                // Test each to index value for this face
                testValue = toIndexes[to+i];
                for (int k=0; k < size; k++){
                    //Check the testValue is in the alloted from indexes.
                    if (testValue == fromIndexes[from+k]){
                        checkComplete++;
                    }
                }
            }

        }
        return checkComplete;
    }
}