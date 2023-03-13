package process;

public class Order {
    //dynamic variable while game processes
    private int processOrder;
    //fixed variable contained by player
    private int playerOrder;
    private boolean forward;
    private Room room;
    
    public Order(Room r) {
        processOrder = 1;
        playerOrder = 0;
        forward = true;
        room = r;
    }

    public int GetPlayerOrder() {
        playerOrder++;
        return playerOrder;
    }
    public int GetProcessOrder() {
        return processOrder;
    }
    //param is Player.order
    public void SetProcessOrder(int o) {
        processOrder = forward ? o + 1 : o - 1;
        processOrder = processOrder > room.GetPlayerCount() ? 1 : processOrder;
        processOrder = processOrder < 1 ? room.GetPlayerCount() : processOrder;
    }
    public void SetDirection() {
        forward = forward ? false : true;
    }
}
