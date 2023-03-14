package cards;

import process.State;
import process.Turn;

public class Number extends Card {
    public Number(Turn tn, int n, String seed) {
        turn = tn;
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
    public String GetState() {
        return state;
    }
    @Override
    public String GetSkill() {
        return skill.toString();
    }
    @Override
    public boolean GetCalced() {
        return calced;
    }
    @Override
    public void SkillEffect(Card now) {
        if(now.GetCalced()) {
            turn.GetRoom().GetCalc().SetBuff(now.GetNumber());
        }
    }
    @Override
    public void Used() {
        turn.SetPre(turn.GetNow());
        turn.SetNow(this);
        turn.GetPre().SkillEffect(this);
        turn.GetRoom().GetCalc().SetCount(false);
        State.SetState(state);
    }
}
