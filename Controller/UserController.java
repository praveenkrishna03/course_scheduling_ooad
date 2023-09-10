package Controller;

import Model.CourseDB;
import Model.Database;
import Model.InputDB;
import Model.RoomDB;
import Model.TimingDB;
import View.Home;
import View.UserDetails;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    // database file
    private String FilePath_ip_1 = "data\\input_file_1.txt";
    private String FilePath_ip_2 = "data\\input_file_2.txt";
    private Database database;
    private Home home;
    private UserDetails userDetails;

    public UserController(Home home, UserDetails userDetails) {
        this.database = new Database();
        this.home = home;
        this.userDetails = userDetails;

        // submit user


        
        /* 
        this.home.submitUsers(e -> {
            String firstname = this.home.getFirstname().trim();
            String lastname = this.home.getLastname().trim();

            // simple validations
            if(firstname.isEmpty()) {
                JOptionPane.showMessageDialog(this.home, "First Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if(lastname.isEmpty()) {
                JOptionPane.showMessageDialog(this.home, "Last Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            this.database.addUser(new User(firstname, lastname));
            this.database.saveUser(new File(databaseFile));
            this.home.reset(true);
        });
        */

        // load users
        /* 
        this.home.viewUsers(e -> {
            this.userDetails.getUsers(this.database.loadUsers(new File(databaseFile)));
        });
        */
    }
    // Inside UserController.java

    public void saveRoomDetails(List<RoomDB> roomDetailsList) {
        // Create room objects and add them to the database
        for (RoomDB roomDetails : roomDetailsList) {
            RoomDB room = new RoomDB(roomDetails.getRoom(), roomDetails.getCapacity());
            database.addRoom(room);
        }
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_1);
        database.saveRoom(databaseFile);
    }

    public void saveCourseDetails(List<CourseDB> courseDetailsList) {
        // Create room objects and add them to the database
        for (CourseDB courseDetails : courseDetailsList) {
            CourseDB course = new CourseDB(courseDetails.getCourse());
            database.addCourse(course);
        }
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_1);
        database.saveCourse(databaseFile);
    }

    public void saveTimingDetails(List<TimingDB> timingDetailsList) {
        // Create room objects and add them to the database
        for (TimingDB timingDetails : timingDetailsList) {
            TimingDB timing = new TimingDB(timingDetails.getTiming());
            database.addTiming(timing);
        }
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_1);
        database.saveTiming(databaseFile);
    }

    
/* 
    public void saveInputDetails(List<InputDB> courseDetailsList,List<InputDB> capacityDetailsList,List<InputDB> preferencesDetailsList) {
        // Create room objects and add them to the database
    
        for (InputDB courseDetails : courseDetailsList) {
            //TimingDB timing = new TimingDB(timingDetails.getTiming());
            InputDB course= new InputDB(courseDetails.getCourse(),null,null);
            
            

            database.addInputCourse(course);
        }

         
        for (InputDB capacityDetails : capacityDetailsList) {
            //TimingDB timing = new TimingDB(timingDetails.getTiming());
            InputDB capacity= new InputDB(null,capacityDetails.getCapacity(),null);
            
            

            database.addInputCourse(capacity);
        }

        

        for (InputDB preferncesDetails : preferencesDetailsList) {
            //TimingDB timing = new TimingDB(timingDetails.getTiming());
            InputDB preferences= new InputDB(null, null,preferncesDetails.getPreferences());
            
            

            database.addInputCourse(preferences);
        }
        

        
        
        // Save the room details to the database file
        File databaseFile = new File(FilePath_ip_2);
        database.saveTiming(databaseFile);
        database.saveTiming(databaseFile);
        database.saveTiming(databaseFile);

    }
    */

    public void saveInputDetails(List<InputDB> inputDetailsList) {
        // Separate the input details into separate lists
        List<InputDB> courseList = new ArrayList<>();
        List<InputDB> capacityList = new ArrayList<>();
        List<InputDB> preferencesList = new ArrayList<>();
        
    
        for (InputDB inputDetails : inputDetailsList) {
            courseList.add(new InputDB(inputDetails.getInputCourse(), FilePath_ip_1, FilePath_ip_1));
            capacityList.add(new InputDB(FilePath_ip_1,inputDetails.getInputCapacity(), FilePath_ip_1));
            preferencesList.add(new InputDB(FilePath_ip_1,FilePath_ip_1,inputDetails.getInputPreferences()));
        }
    
        // Add the split lists to the database
        for (InputDB course : courseList) {
            database.addInputCourse(course);
        }
    
        for (InputDB capacity : capacityList) {
            database.addInputCapacity(capacity);
        }
    
        for (InputDB preferences : preferencesList) {
            database.addInputPreferences(preferences);
        }
    
        // Save the input details to the database file
        File databaseFile = new File(FilePath_ip_2);
        database.saveInput(databaseFile);
    }
    

    

}
