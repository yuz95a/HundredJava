package process;

import java.util.ArrayList;
import java.util.Random;

import cards.*;
import cards.Number;

public class Turn {
    private static Turn instance;
    private int turn;
    private int processOrder;
    private final Pack main;
    private final Pack pale;
    private Card pre;
    private Card now;
    private int direction;
    private ArrayList<Player> players;
    public Turn() {
        turn = 1;
        processOrder = 1;
        main = new Pack();
        main.SetSize(108);
        pale = new Pack();
        pre = new cards.Number(1, "pre");
        now = new cards.Number(1, "now");
        direction = 1;
        players = new ArrayList<Player>();
        SetMainCard();
        main.Shuffle();
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
        return processOrder;
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
    public Player GetPlayer(int index) {
        return players.get(index);
    }
    public void SetTurn() {
        turn++;
    }
    //param is Player.order
    public void SetOrder(int o) {
        processOrder = o + 1;
        processOrder = processOrder > players.size() ? 1 : processOrder;
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
    public void AddPlayer(Player player) {
        players.add(player);
    }
    public void SetMainCard() {
        //region NumberCard
        for (int i = 1; i < 10; i++) {
            if (i % 5 != 0) {
                SetMainNumberCard(i,4);
            }
        }
        for (int i = 10; i < 30; i++) {
            if (i % 5 != 0 && i % 11 != 0) {
                SetMainNumberCard(i,2);
            }
        }
        for (int i = 1; i < 10; i++) {
            SetMainNumberCard(i * 11,1);
        }
        for (int i = 0; i < 5; i++) {
            SetMainNumberCard(i - 5,2);
        }
        SetMainNumberCard(0,3);
        SetMainNumberCard(-10,2);
        //endregion
        SetMainSpecialCard();
    }
    public void SetMainNumberCard(int n, int count) {
        for (int i = 0; i < count; i++) {
            Number card = new Number(n, GetRandomSeed());
            main.AddCard(card);
        }
    }
    public void SetMainSpecialCard() {
        for (int i = 0; i < 4; i++) {
            Change cc = new Change(GetRandomSeed());
            Reverse cr = new Reverse(GetRandomSeed());
            main.AddCard(cc);
            main.AddCard(cr);
        }
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 2 || i == 3) {
                for (int j = 0; j < 2; j++) {
                    String s = Integer.toString(i) + " times";
                    Times ct = new Times(GetRandomSeed(), s, i);
                    main.AddCard(ct);
                }
            }
            else if (i == 1 || i == 4) {
                for (int j = 0; j < 2; j++) {
                    String s = "plus " + Integer.toString(i);
                    Plus cp = new Plus(GetRandomSeed(), s, i);
                    main.AddCard(cp);
                }
            }
            else {
                for (int j = 0; j < 2; j++) {
                    String s = "remainder divided by " + Integer.toString(i);
                    Mod cm = new Mod(GetRandomSeed(), s, i);
                    main.AddCard(cm);
                }
            }
        }
    }
    public String GetRandomSeed() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
        
        return generatedString;
    }
    public void SetOrderAllPlayers() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).SetOrder(i + 1);
        }
        UI.GetInstance().TextSetOrderAllPlayers(players.size());
    }
    public void GameStart() {
        for (Player player : players) {
            player.Ready(1);
            if (player.IsEnable()) {

            }
        }
    }
}
