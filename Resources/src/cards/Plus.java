package cards;

import process.Calc;
import process.State;
import process.Turn;

public class Plus extends Card {
    private int plus;
    public Plus(Turn tn, String seed, String st, int p) {
        turn = tn;
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
    public void Used() {
        turn.SetPre(turn.GetNow());
        turn.SetNow(this);
        State.SetState(state);
    }
}
