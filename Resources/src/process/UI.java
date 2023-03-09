package process;

public class UI {
    private static UI instance;
    public static UI GetInstance() {
        if (instance == null) {
            UI ui = new UI();
            instance = ui;
        }
        return instance;
    }
    public void StatePrint() {
        System.out.println(State.GetState());
    }
    public void TextShuffle() {
        System.out.println("Shuffle");
    }
    public void TextSetOrderAllPlayers(int players) {
        System.out.println("All players(" + Integer.toString(players) + ") get order number each other.");
    }
}
