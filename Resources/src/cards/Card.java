package cards;

import process.State;
import process.Turn;

public abstract class Card {
    protected Turn turn;
    protected int number;
    protected String id;
    protected State.Skill skill;
    protected String state;
    protected boolean calced;
    public abstract int GetNumber();
    public abstract boolean GetCalced();
    public abstract void SkillEffect(Card pre);
    public abstract void Used();
}
