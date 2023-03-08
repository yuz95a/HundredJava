package cards;

import process.Calc;
import process.State;
import process.Turn;

public class Times extends Card {
    private int times;
    public Times(String seed, String st, int t) {
        number = 0;
        id = seed;
        skill = State.Skill.TIMES;
        state = st;
        times = t;
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
            Calc.SetBuff(now.GetNumber() * times);
        }
    }
    @Override
    public void UseCard() {
        Turn.pre = Turn.now;
        Turn.now = this;
        State.SetState(state);
    }
}
