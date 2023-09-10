package Model;

public class InputDB {
    private String course;
    private String capacity;
    private String preferences;

    public InputDB() {
        // empty constructor
    }

    public InputDB(String course, String capacity,String preferences) {
        this.course = course;
        this.capacity = capacity;
        this.preferences = preferences;
    }

    // getters
    public String getInputCourse() {
        return course;
    }

    public String getInputCapacity() {
        return capacity;
    }
    public String getInputPreferences() {
        return preferences;
    }
}
