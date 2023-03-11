package process;

import java.util.ArrayList;
import java.util.Random;

import cards.*;
import cards.Number;

public class Turn {
    private static Turn instance;
    private int turn;
    private int processOrder;
    private int playerOrder;
    private final Pack main;
    private final Pack pale;
    private Card pre;
    private Card now;
    private int direction;
    private ArrayList<Player> players;
    public Turn() {
        turn = 1;
        processOrder = 1;
        playerOrder = 1;
        main = new Pack();
        main.SetSize(108);
        pale = new Pack();
        pale.SetSize(108);
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
    public int GetProcessOrder() {
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
    public void SetProcessOrder(int o) {
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
        player.SetOrder(playerOrder);
        players.add(player);
        playerOrder++;
    }
    //region InitalMainCard
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
    //endregion
    public void GameStart() {
        //playerOrder is set player count + 1
        playerOrder--;
        //Ready turn : all players get 2cards.
        for (int i = 0; i < playerOrder; i++) {
            players.get(i).DrawCard();
            players.get(i).DrawCard();
        }
        SetFirstCard();
        Calc.SetBuff(now.GetNumber());
        Calc.SetCount(false);
    }
    public void SetFirstCard() {
        Card c = main.GetCard(0);
        main.RemoveCard(0);
        pale.AddCard(c);
        now = c;
    }
    //After ready turn
    public void GameUpdate(int processOrder) {
        //Player p;
        //p = players.get(i);
        //p.DrawCard();
        //p.UseCard(???); ??? -> input each player's choose
        //p.??? -> SetProcessOrder
        //turn++;
    }
}
