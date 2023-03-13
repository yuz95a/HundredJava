package cards;

import process.Calc;
import process.State;
import process.Turn;

public class Reverse extends Card {
    public Reverse(Turn tn, String seed) {
        turn = tn;
        number = 0;
        id = seed;
        skill = State.Skill.REVERSE;
        state = "Normal";
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
            Calc.SetBuff(now.GetNumber());
        }
    }
    @Override
    public void Used() {
        turn.SetPre(turn.GetNow());
        turn.SetNow(this);
        turn.CallSetDirection();
        State.SetState(state);
    }
}
