package rubix_cube;

import search.BreadFirstSearch;
import search.SearchNode;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by michaelconnor on 2016-01-25.
 */
public class RubixGame {

    private static Move[] rubixCubeMoves;

    public static void main(String [] args){

        int gameSize = 3;
        if (args.length > 0) {
            try {
                gameSize = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                error("Argument" + args[0] + " must be an integer.");
            }
        }

        Scanner console = new Scanner(System.in);

        // Get new game size
        print("Enter game size [" + gameSize + "]: ");
        String readLine = console.nextLine();
        if (!readLine.isEmpty()){
            try {
                gameSize = Integer.parseInt(readLine);
            } catch (NumberFormatException e){
                error("Game size must be an integer.");
            }
        }

        // Get number of moves to scramble the cube
        int numMovesFromGoal = 8;
        print("Enter number of moves from goal [8]: ");
        readLine = console.nextLine();
        if (!readLine.isEmpty()){
            try {
                numMovesFromGoal = Integer.parseInt(readLine);
            } catch (NumberFormatException e){
                error("Number of moves must be an integer.");
            }
        }

        Rubix currGame = new Rubix(gameSize);

        RubixGame.rubixCubeMoves = currGame.getRubixCubeMoves();

        RubixGame.scrambleCube(currGame, numMovesFromGoal);

        println(currGame.toString2D());

        Move nextMove;

        String loopMsg = "Enter move tuple (Axis,Direction,SliceIndex) or search[BFS or A*]: ";

        // Game loop
        print(loopMsg);
        while(console.hasNextLine()){
            readLine = console.nextLine();
            if (readLine.toLowerCase().equals("bfs")){
                SearchNode goalNode = RubixGame.searchBFS(currGame);
                currGame = solveGame(goalNode, currGame);
                println(currGame.toString2D());
                print("Game Solved");
                System.exit(0);
            } else if (readLine.toLowerCase().equals("a*")){
//                currGame = RubixGame.searchAStar(currGame);
//                println(currGame.toString2D());
                println("Not Implemented yet");
                print(loopMsg);
                break;
//                System.exit(0);
            } else {
                println("No Search with that name.");
                print(loopMsg);
            }

            if (readLine.equals("exit")){
                System.exit(0);
            }

            nextMove = parseLine(readLine);
            println(nextMove.toString());

            currGame.move(nextMove);
            println(currGame.toString2D());
            print(loopMsg);
        }



    }

    private static SearchNode searchBFS(Rubix currGame) {
        BreadFirstSearch bfs = new BreadFirstSearch();
        SearchNode startState = new SearchNode(null, currGame);
        return bfs.search(startState);
    }

    private static Rubix searchAStar(Rubix currGame) {
        // TODO
        // Get a list of moves from search
        // apply moves
        return currGame;
    }

    private static Rubix solveGame(SearchNode goalNode, Rubix currGame){
        ArrayList<Move> allMoves = new ArrayList<Move>();
        allMoves.add(goalNode.getLastMoveTaken());
        SearchNode nextNode = goalNode;
        while (goalNode.getParentNode() != null){
            nextNode = nextNode.getParentNode();
            allMoves.add(nextNode.getLastMoveTaken());
        }
        // Make moves
        for (Move m : allMoves){
            currGame.move(m);
        }

        return currGame;
    }

    private static Move parseLine(String s) {
        if (s.contains("(") && s.contains(")") && s.contains(",")){
            s = s.replace("(", "").replace(")","");
            String[] parts = s.split(",");
            if (parts.length != 3){
                throw new IllegalArgumentException("Not proper move format");
            }
            Axis axis = Axis.valueOf(parts[0]);
            Direction direction = Direction.valueOf(parts[1]);
            int slice = Integer.parseInt(parts[2]);
            return new Move(axis, direction, slice);
        } else {
            throw new IllegalArgumentException("Not proper move format");
        }
    }

    private static void scrambleCube(Rubix currGame, int numMoves) {
        Random moveIndex = new Random();

        for (int i=0; i < numMoves; i++){
            currGame.move(
                RubixGame.rubixCubeMoves[
                    moveIndex.nextInt(RubixGame.rubixCubeMoves.length)
                ]
            );
        }
    }

    private static void println(String s){System.out.println(s);}

    private static void print(String s){System.out.print(s);}

    private static void error(String s){
        System.err.print(s);
        System.exit(1);
    }

    public static boolean isGoalState(Rubix cube){
        return cube.isGoalState();
    }


}

