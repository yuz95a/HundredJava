package process;

import java.io.IOException;

import cards.*;
import cards.Number;

public class Turn {
    private final Pack main;
    private final Pack pale;
    private Card pre;
    private Card now;
    private Room room;
    private InitialMainPack initialMainPack;
    
    public Turn(Room r) {
        main = new Pack();
        main.SetSize(108);
        pale = new Pack();
        pale.SetSize(108);
        pre = new Number(this, 1, "pre");
        now = new Number(this, 1, "now");
        room = r;
        initialMainPack = new InitialMainPack(this, main);
        initialMainPack.SetMainCard();
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
    public Room GetRoom() {
        return room;
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
        System.out.println("First Card. { " + c.GetNumber() + ":number, " + c.GetSkill() + ":skill, " + c.GetState() + " :state }");// -> TODO UI class
    }
    public void CallSetDirection() {
        room.GetOrder().SetDirection();
    }
    //Only First Turn
    public void GameStart() {
        main.Shuffle();
        //Before first turn : all players get 2cards.
        for (int i = 0; i < room.GetPlayerCount(); i++) {
            room.GetPlayer(i).GameInit();
            room.GetPlayer(i).DrawCard();
            room.GetPlayer(i).DrawCard();
        }
        SetFirstCard();
        room.GetCalc().SetBuff(now.GetNumber());
        room.GetCalc().SetCount(false);
    }
    //Every turn call except for first turn
    public void GameUpdate(int pro) throws IOException {
        Player p;
        p = room.GetPlayer(pro - 1);
        p.OrderInit();
        p.OrderPass();
    }
    //Call a player whose life is zero
    public void GameRestore() {
        for (int i = 0; i < room.GetPlayerCount(); i++) {
            Player p = room.GetPlayer(i);
            p.ThrowAllCard();
        }
        while (pale.GetCard(0) != null) {
            Card c = pale.GetCard(0);
            main.AddCard(c);
            pale.RemoveCard(0);
        }
        room.GetCalc().SetBuff(0);
        room.GetCalc().SetCount(true);
        System.out.println("------##GAME_RESTORE##------");
        GameStart();
        room.GetOrder().SetProcessOrder(room.GetOrder().GetProcessOrder());
    }
}
