package search;

import rubix_cube.Move;

/**
 * Created by michaelconnor on 2016-01-26.
 */
public interface Searchable {

    public boolean isGoalState();

    public Searchable[] generateChildren();

    public double getHeuristicValue();

    public byte[] getKey();

    public Move getLastMoveTaken();

    public String toString1D();
}
