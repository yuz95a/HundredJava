package process;

public class Calc {
    private static int count = 0;
    private static int buff = 0;
    public static void SetCount(boolean change) {
        count = change ? buff : count + buff;
    }
    public static int GetCount() {
        return count;
    }
    public static void SetBuff(int num) {
        buff = num;
    }
}
