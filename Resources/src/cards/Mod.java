package cards;

import process.Calc;
import process.State;
import process.Turn;

public class Mod extends Card {
    private int divisor;
    public Mod(String seed, String st, int d) {
        number = 0;
        id = seed;
        skill = State.Skill.MOD;
        state = st;
        divisor = d;
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
            Calc.SetBuff(now.GetNumber() % divisor);
        }
    }
    @Override
    public void Used() {
        Turn.GetInstance().SetPre(Turn.GetInstance().GetNow());
        Turn.GetInstance().SetNow(this);
        State.SetState(state);
    }
}
