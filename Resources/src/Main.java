import process.*;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("GameStart");
        
        Player p1 = new Player("First");
        Player p2 = new Player("Second");
        Player p3 = new Player("Third");
        Player p4 = new Player("Fourth");
        Player p5 = new Player("Fifth");
        Player p6 = new Player("Sixth");
        Player p7 = new Player("Seventh");
        Player p8 = new Player("Eighth");

        p1.HostGame("01010101");
        p2.JoinGame("01010101");
        p3.JoinGame("01010101");
        p4.JoinGame("01010101");
        p5.HostGame("21642758");
        p6.JoinGame("21642758");
        p7.JoinGame("21642758");
        p8.JoinGame("21642758");

        System.out.println("GameEnd");
    }
}
