package cards;

import process.Calc;
import process.State;
import process.Turn;

public class Number extends Card {
    public Number(int n, String seed) {
        number = n;
        id = seed;
        skill = State.Skill.NORMAL;
        state = "Normal";
        calced = true;
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
            Calc.SetBuff(now.GetNumber());
        }
    }
    @Override
    public void UseCard() {
        Turn.pre = Turn.now;
        Turn.now = this;
        Turn.pre.SkillEffect(this);
        Calc.SetCount(false);
        State.SetState(state);
    }
}
