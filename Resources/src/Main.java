import java.io.IOException;

import process.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("------##GAME_START##------");
        
        Player p1 = new Player("1st");
        Player p2 = new Player("2nd");
        Player p3 = new Player("3rd");
        Player p4 = new Player("4th");
        Player p5 = new Player("5th");
        Player p6 = new Player("6th");
        Player p7 = new Player("7th");
        Player p8 = new Player("8th");

        p1.HostGame("01010101");
        p2.JoinGame("01010101");
        p3.JoinGame("01010101");
        p4.JoinGame("01010101");
        p5.JoinGame("01010101");
        p6.JoinGame("01010101");
        p7.JoinGame("01010101");
        p8.JoinGame("01010101");

        for (int i = 0; i < Server.GetInstance().GetRoomCount(); i++) {
            //System.out.println("room[" + i + "] Set.");
            Server.GetInstance().GetRoom(i).GetTurn().GameStart();
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < Server.GetInstance().GetRoomCount(); j++) {
                // System.out.println("room[" + j + "] Process.");
                Server.GetInstance().GetRoom(j).GetTurn().GameUpdate(Server.GetInstance().GetRoom(j).GetOrder().GetProcessOrder());
            }
        }
        System.out.println("------##GAME_PAUSE##------");
    }
}
