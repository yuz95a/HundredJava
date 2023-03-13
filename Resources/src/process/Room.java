package process;

import java.util.ArrayList;

public class Room {
    private final int PASSWORD_LENGTH = 8;
    private ArrayList<Player> players;
    private int index;
    private String password;
    private int passwordCheck;
    private Order order;
    private Turn turn;

    public Room() {
        players = new ArrayList<Player>();
        order = new Order(this);
        passwordCheck = -1;
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
    public Order GetOrder() {
        return order;
    }
    public Turn GetTurn() {
        return turn;
    }
    public void AddPlayer(Player player) {
        player.SetOrder(order.GetPlayerOrder());
        players.add(player);
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
                    System.out.print("password is wrong.");
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            System.out.print("password is wrong.");
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
