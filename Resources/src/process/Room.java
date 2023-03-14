package process;

import java.util.ArrayList;

public class Room {
    private final int PASSWORD_LENGTH = 8;
    private final int MAX = 8;
    private int index;
    private ArrayList<Player> players;
    private String password;
    private int passwordCheck;
    private Calc calc;
    private Order order;
    private Turn turn;

    public Room() {
        players = new ArrayList<Player>();
        passwordCheck = -1;
        calc = new Calc();
        order = new Order(this);
        turn = new Turn(this);
    }

    public int GetIndex() {
        return index;
    }
    public Player GetPlayer(int index) {
        return players.get(index);
    }
    public int GetPlayerCount() {
        return players.size();
    }
    public int GetPasswordCheck() {
        return passwordCheck;
    }
    public Calc GetCalc() {
        return calc;
    }
    public Order GetOrder() {
        return order;
    }
    public Turn GetTurn() {
        return turn;
    }

    public void SetIndex(int i) {
        index = i;
    }
    public void SetPassword(String pw) {
        if (pw.length() == PASSWORD_LENGTH) {
            try {
                passwordCheck = Integer.parseInt(pw);
                if(passwordCheck != -1) {
                    password = pw;
                }
                else {
                    System.out.print("password is wrong.");// -> TODO UI class
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            System.out.print("password is wrong.");// -> TODO UI class
        }
    }
    public void AddPlayer(Player player) {
        if(players.size() < MAX) {
            player.SetOrder(order.GetPlayerOrder());
            players.add(player);
            System.out.println(players.size() + " players in room.");// -> TODO UI class
        }
        else {
            System.out.println("Error: This room is already full");// -> TODO UI class
        }
    }
    public Room FindByPassword(String pw) {
        if (password == pw) {
            return this;
        }
        else {
            return null;
        }
    }
}
