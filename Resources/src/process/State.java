package process;

public class State{
    private static String state;
    public enum  Skill { NORMAL, TIMES, MOD, PLUS, CHANGE, REVERSE }
    public static void SetState(String s) {
        state = s;
    }
}
