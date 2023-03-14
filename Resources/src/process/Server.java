package process;

import java.util.ArrayList;

public class Server {
    private static Server instance;
    private ArrayList<Room> rooms = new ArrayList<Room>();

    private Server() {}

    public static Server GetInstance() {
        if(instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public int GetRoomCount() {
        return rooms.size();
    }
    public Room GetRoom(int index) {
        return rooms.get(index);
    }
    public Room CreateNewRoom(String pw) {
        Room room = new Room();
        room.SetPassword(pw);
        rooms.add(room);
        room.SetIndex(rooms.size());
        return room;
    }
    public Room MatchingRoom(String pw) {
        Room room = new Room();
        boolean found = false;
        for (Room r : rooms) {
            if (r.FindByPassword(pw) != null) {
                room = r;
                found = true;
            }
        }
        if(!found) {
            System.out.println("Room is not found Create Room");
            rooms.add(room);
            room.SetIndex(rooms.size());
        }
        return room;
    }
}
