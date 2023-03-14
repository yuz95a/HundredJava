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
}
