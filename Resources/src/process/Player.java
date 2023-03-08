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
        life = 3;
        enable = false;

        hand = new Pack();
        main = Turn.GetInstance().GetMain();
        pale = Turn.GetInstance().GetPale();
    }
    public void SetOrder(int o) {
        order = o > 0 ? o : 0;
    }
    public int GetLife() {
        return life;
    }
    public void FromMain() {
        Card c = main.GetCard(0);
        main.RemoveCard(0);
        hand.AddCard(c);
    }
    public void ToPale(int index) {
        hand.GetCard(index).UseCard();
        Card c = hand.GetCard(index);
        hand.RemoveCard(index);
        pale.AddCard(c);
    }
    public void Pass() {
        enable = false;
    }
    public void Process(int o) {
        if (order == o && life > 0) {
            enable = true;
        }
    }
    public void LifeLoss() {
        if (--life == 0) {
            enable = false;
            order = 0;
        }
    }
    public String Action() {
        return name + "'s turn";
    }
}
