package rubix_cube.tests;

import static org.junit.Assert.*;
import rubix_cube.Rubix;

import java.util.Arrays;

/**
 * Created by michaelconnor on 2016-01-14.
 */
public class RubixTest {

    @org.junit.Test
    public void testInitSolvedRubix() throws Exception {
        Rubix test_cube = new Rubix(2);
        assertEquals(test_cube.getGameArray().length, 6*(2*2));
        byte[] gameArray = test_cube.getGameArray();
        int start_index_face_2 = (2*2)*2;
        int end_index_face_2 = (2*2)*3;
        byte[] expected = {3,3,3,3};
        byte[] actual = Arrays.copyOfRange(gameArray, start_index_face_2, end_index_face_2);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));

        test_cube = new Rubix(16);
        assertEquals(test_cube.getGameArray().length, 6*(16*16));
        gameArray = test_cube.getGameArray();
        start_index_face_2 = (16*16)*4;
        end_index_face_2 = (16*16)*5;
        actual = Arrays.copyOfRange(gameArray, start_index_face_2, end_index_face_2);
        assertEquals(actual[0], 5);
        assertEquals(actual[255], 5);
    }
}