package cards;

import process.State;
import process.Turn;

public class Change extends Card {
    public Change(Turn tn, String seed) {
        turn = tn;
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
        turn.GetRoom().GetCalc().SetBuff(turn.GetRoom().GetCalc().GetCount() % 10 * 10 + turn.GetRoom().GetCalc().GetCount() / 10);
        turn.GetRoom().GetCalc().SetCount(true);
        State.SetState(state);
    }
}
