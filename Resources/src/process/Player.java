package process;

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
    //Crate New Room
    public void HostGame(String pw) {
        room = Server.GetInstance().CreateNewRoom(pw);
        System.out.println(name + " created and entered room" + room.GetIndex());
    }
    //Join Room
    public void JoinGame(String pw) {
        room = Server.GetInstance().MatchingRoom(pw);
        System.out.println(name + " joined room" + room.GetIndex());
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
    }
    //Card: player's hand -> pale
    public void UseCard(int index) {
        hand.GetCard(index).Used();
        Card c = hand.GetCard(index);
        hand.RemoveCard(index);
        pale.AddCard(c);
    }
    public void LifeLoss() {
        if (--life == 0) {
            enable = false;
            order = -1;
        }
    }
    public void OrderInit() {
        enable = true;
        DrawCard();
        UseCard(0);
    }
    public void OrderPass() {
        enable = false;
        room.GetOrder().SetProcessOrder(order);
        room.GetTurn().GameUpdate(room.GetOrder().GetProcessOrder());
    }
}
