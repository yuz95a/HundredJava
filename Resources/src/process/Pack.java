package process;

import java.util.ArrayList;

import cards.Card;

public class Pack {
    private int size;
    private ArrayList<Card> pack = new ArrayList<Card>();
    public Pack(Card ... cards) {
        if (cards == null) {
            size = -1;
            pack.clear();
        }
        for (Card c : cards) {
            size = cards.length;
            pack.add(c);
        }
    }
    public void SetSize(int s) {
        size = s > 0 ? s : -1;
    }
    public void Shuffle() {
        ArrayList<Card> tmp = new ArrayList<Card>();
        int count = pack.size();
        for (int i = 0; i < count; i++) {
            int j = (int)(Math.random() * 1000) % pack.size();
            tmp.add(pack.get(j));
            pack.remove(j);
        }
        pack = tmp;
    }
    public Card GetFirstCard(int index) {
        return pack.get(index);
    }
    public Card GetFirstCard() {
        return pack.get(0);
    }
    public void RemoveCard() {
        pack.remove(0);
    }
    public void AddCard(Card c) {
        pack.add(c);
    }
}
