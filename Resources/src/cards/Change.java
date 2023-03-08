package cards;

import process.Calc;
import process.State;
import process.Turn;

public class Change extends Card {
    public Change(String seed) {
        number = 0;
        id = seed;
        skill = State.Skill.CHANGE;
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
        Calc.SetBuff(Calc.GetCount() % 10 * 10 + Calc.GetCount() / 10);
        Calc.SetCount(true);
        State.SetState(state);
    }
}
