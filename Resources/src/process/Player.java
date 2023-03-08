package process;

import cards.Card;
import cards.Number;

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
        main = Turn.main;
        pale = Turn.pale;

    }
    public void SetOrder(int o) {
        order = o > 0 ? o : 0;
    }
    public void FromMain() {
        Card c = main.GetFirstCard();
        main.RemoveCard();
        hand.AddCard(c);
    }
    public void ToPale(Card c) {
        Card card = c;
        hand.RemoveCard(); //=> which card will be removed? Maybe not just index 0.
        hand.AddCard(c);
    }
    public void UseCard(int index) {
        ToPale(new Number(1, "EEFD"));
    }
    public void Pass() {
        //this.disable, next.ennable
    }
}
