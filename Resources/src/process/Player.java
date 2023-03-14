package process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import cards.Card;

public class Player {
    //region ForPlayerFiled
    private String name;
    private int order;
    private int life;
    private boolean enable;
    private Room room;
    //endregion

    //region ForPlayField
    private Pack hand;
    private Pack main;
    private Pack pale;
    //endregion

    public Player(String n) {
        name = n;
        order = -1;
    }

    public int GetLife() {
        return life;
    }
    public boolean IsEnable() {
        return enable;
    }
    public void SetOrder(int o) {
        order = o > 0 ? o : 0;
    }
    //Crate New Room -> TODO HostGame(String pw) manage same password register
    public void HostGame(String pw) {
        room = Server.GetInstance().CreateNewRoom(pw);
        room.AddPlayer(this);
        System.out.println(name + " created and entered room" + room.GetIndex());// -> TODO UI class
    }
    //Join Room
    public void JoinGame(String pw) {
        room = Server.GetInstance().MatchingRoom(pw);
        room.AddPlayer(this);
        System.out.println(name + " joined room" + room.GetIndex());// -> TODO UI class
    }
    public void GameInit() {
        life = 3;
        enable = false;
        hand = new Pack();
        hand.SetSize(3);
        main = room.GetTurn().GetMain();
        pale = room.GetTurn().GetPale();
    }
    //Card: main -> player's hand
    public void DrawCard() {
        Card c = main.GetCard(0);
        main.RemoveCard(0);
        hand.AddCard(c);
        System.out.println(name + " draw a card.");// -> TODO UI class
    }
    //Card: player's hand -> pale
    public void UseCard(int index) {
        hand.GetCard(index).Used();
        Card c = hand.GetCard(index);
        hand.RemoveCard(index);
        pale.AddCard(c);
        System.out.println(name + " Use Card. { " + c.GetNumber() + ":number, " + c.GetSkill() + ":skill, " + c.GetState() + " :state }");// -> TODO UI class
        System.out.println("Count is " + room.GetCalc().GetCount() + " now.");// -> TODO UI class
    }
    //Card: player's hand -> pale : All Card
    public void ThrowAllCard() {
        while (hand.GetCard(0) != null) {
            Card c = hand.GetCard(0);
            pale.AddCard(c);
            hand.RemoveCard(0);
        }
    }
    public void LifeLoss() {
        life--;
        life = life > 0 ? life : 0;
        System.out.println(name + " lost life.");// -> TODO UI class
    }
    public void OrderInit() throws IOException {
        enable = life > 0 ? true : false;
        if (enable) {
            DrawCard();
            //-> TODO Show all card in hand
            System.out.println("[0] { " + hand.GetCard(0).GetNumber() + ":number, " + hand.GetCard(0).GetSkill() + ":skill, " + hand.GetCard(0).GetState() + ":state }");
            System.out.println("[1] { " + hand.GetCard(1).GetNumber() + ":number, " + hand.GetCard(1).GetSkill() + ":skill, " + hand.GetCard(1).GetState() + ":state }");
            System.out.println("[2] { " + hand.GetCard(2).GetNumber() + ":number, " + hand.GetCard(2).GetSkill() + ":skill, " + hand.GetCard(2).GetState() + ":state }");
            int i = -1;
            while (i < 0 || i > 2) {
                i = Integer.parseInt(Input());
            }

            UseCard(i);// -> TODO Not index 0, have to input in Main class
            if (room.GetCalc().GetCount() % 5 == 0) {
                if (room.GetTurn().GetNow().GetCalced()) {
                    LifeLoss();
                }
            }
            if (room.GetCalc().GetCount() >= 100) {
                LifeLoss();
                room.GetTurn().GameRestore();
            }
        }
    }
    public void OrderPass() {
        enable = false;
        room.GetOrder().SetProcessOrder(order);
    }
    public String Input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        return s;
    } 
}
