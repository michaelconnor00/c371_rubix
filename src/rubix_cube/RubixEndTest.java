package rubix_cube;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by michaelconnor on 2016-01-25.
 */
public class RubixEndTest {

    @Test
    public void testRubixEndSize4() throws Exception {
        int size = 4;
        Face face = Face.YOpposite;

        RubixEnd testEnd = new RubixEnd(face, size);

        assertNotNull(testEnd);

        int[][] transMap = testEnd.getEndTransitionMap(Direction.CW);
        System.out.println(RubixSlice.toString(transMap));

        assertEquals(transMap.length, size*4);

//        assertEquals(checkIndexes(size, transMap)/4, size); TODO
    }



}