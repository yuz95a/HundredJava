package process;

public class State{
    public enum  Skill { NORMAL, TIMES, MOD, PLUS, CHANGE, REVERSE }
    private static String state;
    public static String GetState() {
        return state;
    }
    public static void SetState(String s) {
        state = s;
    }
}
