package cards;

import process.State;
import process.Turn;

public class Times extends Card {
    private int times;
    public Times(Turn tn, String seed, String st, int t) {
        turn = tn;
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
            turn.GetRoom().GetCalc().SetBuff(now.GetNumber() * times);
        }
    }
    @Override
    public void Used() {
        turn.SetPre(turn.GetNow());
        turn.SetNow(this);
        State.SetState(state);
    }
}
