package process;

import java.util.ArrayList;
import cards.*;

public class Turn {
    private static Turn instance;
    private int turn;
    private int order;
    private final Pack main;
    private final Pack pale;
    private Card pre;
    private Card now;
    private int direction;
    private ArrayList<Player> players;
    public Turn() {
        turn = 1;
        order = 1;
        main = new Pack();
        pale = new Pack();
        pre = new cards.Number(1, "pre");
        now = new cards.Number(1, "now");
        direction = 1;
        players = new ArrayList<Player>();
    }
    public static Turn GetInstance() {
        if(instance == null) {
            instance = new Turn();
        }
        return instance;
    }
    public int GetTurn() {
        return turn;
    }
    public int GetOrder() {
        return order;
    }
    public Pack GetMain() {
        return main;
    }
    public Pack GetPale() {
        return pale;
    }
    public Card GetPre() {
        return pre;
    }
    public Card GetNow() {
        return now;
    }
    public void SetTurn() {
        turn++;
    }
    //param is Player.order
    public void SetOrder(int o) {
        order = o + 1;
        order = order > players.size() ? 1 : order;
    }
    public void SetPre(Card p) {
        pre = p;
    }
    public void SetNow(Card n) {
        now = n;
    }
    public void SetDirection() {
        direction = direction > 0 ? -1 : 1;
    }
}
