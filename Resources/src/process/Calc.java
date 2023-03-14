package process;

public class Calc {
    private int count;
    private int buff;

    public Calc() {
        count = 0;
        buff = 0;
    }

    public void SetCount(boolean change) {
        count = change ? buff : count + buff;
    }
    public int GetCount() {
        return count;
    }
    public void SetBuff(int num) {
        buff = num;
    }
}
