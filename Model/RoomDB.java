package Model;

public class RoomDB {
    private String room;
    private String capacity;

    public RoomDB() {
        // empty constructor
    }

    public RoomDB(String room, String capacity) {
        this.room = room;
        this.capacity = capacity;
    }

    // getters
    public String getRoom() {
        return room;
    }

    public String getCapacity() {
        return capacity;
    }
}
