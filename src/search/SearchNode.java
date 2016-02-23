package search;

import rubix_cube.Move;

/**
 * Created by michaelconnor on 2016-01-27.
 */
public class SearchNode {
    private SearchNode parentNode;
    public Searchable searchObj;

    double gOfX;
    double hOfX;

    public SearchNode(SearchNode parent, Searchable searchObj){
//        if (parent == null) {
//            this.parentNode = this;
//        } else {
        this.parentNode = parent;
//        }
        this.searchObj = searchObj;
    }

    /**
     * Function that returns the Searchables key
     * (also known as current state?)
     * @return key as string
     */
    public byte[] getKey(){
        return searchObj.getKey();
    }

    public boolean isGoalState(){
        return searchObj.isGoalState();
    }

    public SearchNode getParentNode() {
        return parentNode;
    }

    public double getGOfX() {
        return gOfX;
    }

    public double getHOfX() {
        return hOfX;
    }

    public Searchable[] generateChildren(){
        return searchObj.generateChildren();
    }

    public Move getLastMoveTaken(){
        return searchObj.getLastMoveTaken();
    }

}
