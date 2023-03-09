import process.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("GameStart");
        Player p1 = new Player("First");
        Player p2 = new Player("Second");
        Player p3 = new Player("Third");
        Player p4 = new Player("Fourth");
        Player p5 = new Player("Fifth");
        Player p6 = new Player("Sixth");
        p1.JoinGame();
        p2.JoinGame();
        p3.JoinGame();
        p4.JoinGame();
        p5.JoinGame();
        p6.JoinGame();
        Turn.GetInstance().SetOrderAllPlayers();
        System.out.println("GameEnd");
    }
}