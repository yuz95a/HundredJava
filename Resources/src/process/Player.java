package process;

import cards.Card;

public class Player {
    //region ForPlayerFiled
    private String name;
    private int order;
    private int life;
    private boolean enable;
    //endregion

    //region ForPlayField
    private Pack hand;
    private Pack main;
    private Pack pale;
    //endregion

    public Player(String n) {
        name = n;
        order = -1;
        life = 3;
        enable = false;

        hand = new Pack();
        hand.SetSize(3);
        main = Turn.GetInstance().GetMain();
        pale = Turn.GetInstance().GetPale();
    }
    public void SetOrder(int o) {
        order = o > 0 ? o : 0;
    }
    public int GetLife() {
        return life;
    }
    public boolean IsEnable() {
        return enable;
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
    public void JoinGame() {
        Turn.GetInstance().AddPlayer(this);
    }
}
