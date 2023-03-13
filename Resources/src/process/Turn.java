package process;

import cards.*;
import cards.Number;

public class Turn {
    private int turn;
    private final Pack main;
    private final Pack pale;
    private Card pre;
    private Card now;
    private Room room;
    private Order order;
    private InitialMainPack initialMainPack;
    
    public Turn(Room r) {
        turn = 1;
        main = new Pack();
        main.SetSize(108);
        pale = new Pack();
        pale.SetSize(108);
        pre = new Number(this, 1, "pre");
        now = new Number(this, 1, "now");
        room = r;
        order = room.GetOrder();
        initialMainPack = new InitialMainPack(this, main);
        initialMainPack.SetMainCard();
    }

    public int GetTurn() {
        return turn;
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
    public void SetPre(Card p) {
        pre = p;
    }
    public void SetNow(Card n) {
        now = n;
    }

    public void SetFirstCard() {
        Card c = main.GetCard(0);
        main.RemoveCard(0);
        pale.AddCard(c);
        now = c;
    }
    public void CallSetDirection() {
        order.SetDirection();
    }
    //Only First Turn
    public void GameStart() {
        main.Shuffle();
        //Before first turn : all players get 2cards.
        for (int i = 0; i < room.GetPlayerCount(); i++) {
            room.GetPlayer(i).DrawCard();
            room.GetPlayer(i).DrawCard();
        }
        SetFirstCard();
        Calc.SetBuff(now.GetNumber());
        Calc.SetCount(false);
    }
    //Every turn call except for first turn
    public void GameUpdate(int pro) {
        Player p;
        p = room.GetPlayer(pro - 1);
        p.OrderInit();
        p.OrderPass();
    }
}
