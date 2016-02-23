package search;

import java.util.*;

/**
 * Created by michaelconnor on 2016-01-20.
 */
public class BreadFirstSearch implements Search {

//    private List<SearchNode> openList = new ArrayList<SearchNode>();
    private List<SearchNode> closedList = new ArrayList<SearchNode>();
    private Queue<SearchNode> openList = new LinkedList<SearchNode>();

    @Override
    public SearchNode search(SearchNode startState) {

        SearchNode currentNode;
        Searchable[] childList;

        // Add first node to list
        openList.add(startState);

        // Algorithm
        while (!openList.isEmpty()){
            currentNode = openList.remove(); //Removes head object
            closedList.add(currentNode);

            // Check for goal state
            if (currentNode.isGoalState()){
                return currentNode;
            }

            childList = currentNode.searchObj.generateChildren();
            for (Searchable child : childList){
                boolean addChild = true;

                for(SearchNode openNode : openList) {
                    // Check if Child is on the openList
                    System.out.println(child.toString1D());
                    System.out.println(openNode.searchObj.toString1D());
                    if (Arrays.equals(child.getKey(), openNode.searchObj.getKey())) {
                        addChild = false;
                        break;
                    }

                    if (addChild) {
                        // Check if child is on the closedList
                        for (SearchNode closedNode : closedList) {
                            if (Arrays.equals(child.getKey(), closedNode.getKey())) {
                                addChild = false;
                                break;
                            }
                        }
                    }
                }

                if(addChild){
                    openList.add(new SearchNode(currentNode, child)); // Adds to end of queue
                }
            }
        }
        return null;
    }


}
