package Controller;

import Model.CourseDB;
import Model.Database;
import Model.InputDB;
import Model.InputFileReader;
import Model.RoomDB;
import Model.TimingDB;
import View.Home;
import View.UserDetails;
import View.input_file_2;

import javax.swing.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    // database file
    private String FilePath_ip_1 = "data\\input_file_1.txt";
    private String FilePath_ip_2 = "data\\input_file_2.txt";
    private Database database;
    private Home home;
    private input_file_2 input_file_2;
    private UserDetails userDetails;

    
    public Object[] generateDataFromInputFiles() {
        // Create an instance of InputFileReader
        InputFileReader inputFileReader = new InputFileReader();

        // Read and process data from inputfile1.txt and inputfile2.txt
        Object[] data = new Object[2];
        data[0] = inputFileReader.readInputFile(); // Call the method to read inputfile1.txt
        data[1] = inputFileReader.readInputFile(); // Call the method to read inputfile2.txt
        return data;
    }


    public UserController(Home home, UserDetails userDetails, input_file_2 input_file_2) {
        
        this.database = new Database();
        this.home = home;
        this.userDetails = userDetails;
        this.input_file_2 = input_file_2;


        

        // submit user
        this.input_file_2.submitInput(e -> {
            String course = input_file_2.getCourse().trim();
            String capacity = input_file_2.getCapacity().trim();
            String preferences = input_file_2.getPreferences().trim();
        
            // Simple validations
            if (course.isEmpty() || capacity.isEmpty() || preferences.isEmpty()) {
                JOptionPane.showMessageDialog(input_file_2, "All fields (Course, Capacity, Preferences) are required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Create an InputDB object with the collected data
            InputDB input = new InputDB(course, capacity, preferences);
        
            // Add input to the database
            this.database.addInputCourse(input);
        
            // Save the input to the appropriate file
            File inputFile = new File(FilePath_ip_2);
            this.database.saveInput(inputFile, input);

        
            // Reset input fields
            input_file_2.reset(true);
        });
        
        }
        

        
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

    public void saveInputDetails(InputDB input) {
        // Add the input details to the database
        database.addInputCourse(input);
    
        // Save the input details to the database file for Input File 2
        File databaseFile = new File(FilePath_ip_2);
        database.saveInput(databaseFile, input);
    }
    
     

    

    
    
    
    
    
    

    

}
