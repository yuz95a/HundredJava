package process;

import java.util.ArrayList;
import cards.Card;

public class Turn {
    public static int turn = 1;
    public static Pack main = new Pack();
    public static Pack pale = new Pack();
    public static Card pre;
    public static Card now; // -> TODO at the first turn this is assigned by random card
    private static int direction = 1;
    private ArrayList<Player> players = new ArrayList<Player>();
    public static void Process() {

    }
    public static void SetDirection() {
        direction = direction > 0 ? -1 : 1;
    }
}
