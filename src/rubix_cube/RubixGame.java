package rubix_cube;

/**
 * Created by michaelconnor on 2016-01-25.
 */
public class RubixGame {

    public static void main(String [] args){
        int gameSize = 0;
        if (args.length > 0) {
            try {
                gameSize = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        Rubix currGame = new Rubix(gameSize);
        System.out.println(currGame.toString2D());

        currGame.move(Axis.Z, Direction.CW, 2);
        System.out.println(currGame.toString2D());

        currGame.move(Axis.Y, Direction.CW, 2);
        System.out.println(currGame.toString2D());

        currGame.move(Axis.Z, Direction.CW, 2);
        System.out.println(currGame.toString2D());
    }

}

