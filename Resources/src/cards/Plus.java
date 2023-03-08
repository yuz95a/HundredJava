package cards;

import process.Calc;
import process.State;
import process.Turn;

public class Plus extends Card {
    private int plus;
    public Plus(String seed, String st, int p) {
        number = 0;
        id = seed;
        skill = State.Skill.PLUS;
        state = st;
        plus = p;
        calced = false;
    }
    @Override
    public int GetNumber() {
        return number;
    }
    @Override
    public boolean GetCalced() {
        return calced;
    }
    @Override
    public void SkillEffect(Card now) {
        if(now.GetCalced()) {
            Calc.SetBuff(now.GetNumber() + plus);
        }
    }
    @Override
    public void UseCard() {
        Turn.pre = Turn.now;
        Turn.now = this;
        State.SetState(state);
    }
}
