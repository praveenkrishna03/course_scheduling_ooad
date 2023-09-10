package Controller;

import Model.CourseDB;
import Model.Database;
import Model.InputDB;
import Model.InputFileReader;
import Model.RoomDB;
import Model.TimingDB;
import View.Home;
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

    
    public Object[] generateDataFromInputFiles() {
        // Create an instance of InputFileReader
        InputFileReader inputFileReader = new InputFileReader();

        // Read and process data from inputfile1.txt and inputfile2.txt
        Object[] data = new Object[2];
        data[0] = inputFileReader.readInputFile(); // Call the method to read inputfile1.txt
        data[1] = inputFileReader.readInputFile(); // Call the method to read inputfile2.txt
        return data;
    }


    public UserController(Home home, input_file_2 input_file_2) {
        
        this.database = new Database();
        this.home = home;
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
    
    public void Schedule(List<String> inp_1_roomsList, List<Integer> inp_1_capList, List<String> inp_1_coursesList, List<String> inp_1_timingList, List<String> inp_2_coursesList, List<Integer> inp_2_capList, List<List<String>> inp_2_prefList) {
    // Create lists for PG courses with and without preferences
    List<String> PG_with_no_pref_courseList = new ArrayList<>();
    List<Integer> PG_with_no_pref_capList = new ArrayList<>();
    List<String> PG_with_pref_courseList = new ArrayList<>();
    List<Integer> PG_with_pref_capList = new ArrayList<>();
    List<List<String>> PG_with_pref_prefList = new ArrayList<>();

    // Create lists for UG courses with and without preferences
    List<String> UG_with_no_pref_courseList = new ArrayList<>();
    List<Integer> UG_with_no_pref_capList = new ArrayList<>();
    List<String> UG_with_pref_courseList = new ArrayList<>();
    List<Integer> UG_with_pref_capList = new ArrayList<>();
    List<List<String>> UG_with_pref_prefList = new ArrayList<>();
    List<List<Integer>> PG_with_pref_list_num = new ArrayList<>();
    List<List<Integer>> UG_with_pref_list_num = new ArrayList<>();

    for (int i = 0; i < inp_2_coursesList.size(); i++) {
        String course = inp_2_coursesList.get(i);
        List<String> pref = inp_2_prefList.get(i);
        int cap = inp_2_capList.get(i);

        char thirdChar = course.charAt(2);

        if (thirdChar >= '6' && pref.isEmpty()) {
            // PG with no preference
            PG_with_no_pref_courseList.add(course);
            PG_with_no_pref_capList.add(cap);
        } else {
            // PG with preference
            PG_with_pref_courseList.add(course);
            PG_with_pref_capList.add(cap);
            PG_with_pref_prefList.add(pref);
        }

        if (thirdChar < '6' && pref.isEmpty()) {
            // UG with no preference
            UG_with_no_pref_courseList.add(course);
            UG_with_no_pref_capList.add(cap);
        } else {
            // UG with preference
            UG_with_pref_courseList.add(course);
            UG_with_pref_capList.add(cap);
            UG_with_pref_prefList.add(pref);
        }

        

        for (List<String> prefer : PG_with_pref_prefList) {
            List<Integer> prefIndices = new ArrayList<>();
            for (String timing : prefer) {
                int index = inp_1_timingList.indexOf(timing);
                if (index != -1) {
                    prefIndices.add(index);
                }
            }
            PG_with_pref_list_num.add(prefIndices);
        }
        
        for (List<String> prefer : UG_with_pref_prefList) {
            List<Integer> prefIndices = new ArrayList<>();
            for (String timing : prefer) {
                int index = inp_1_timingList.indexOf(timing);
                if (index != -1) {
                    prefIndices.add(index);
                }
            }
            UG_with_pref_list_num.add(prefIndices);
        }

    }

    // Initialize your timetable matrix
        String[][] timetable = new String[inp_1_roomsList.size()][inp_1_timingList.size()];
        boolean[][] isSlotOccupied = new boolean[inp_1_roomsList.size()][inp_1_timingList.size()];



        

        // Loop for PG_with_pref_courses
        for (int i = 0; i < PG_with_pref_courseList.size(); i++) {
            String course = PG_with_pref_courseList.get(i);
            int cap = PG_with_pref_capList.get(i);
            List<Integer> prefIndices = PG_with_pref_list_num.get(i);

            // Loop over inp_1_capList elements to find a room
            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);

                // Check if the course cap is less than or equal to inp_1_cap
                if (cap <= inpCap) {
                    // Loop over inp_1_timingList to find a suitable slot
                    for (int k : prefIndices) {
                        // Check if the slot is not occupied
                        if (!isSlotOccupied[j][k]) {
                            // Assign the course to the timetable and mark the slot as occupied
                            timetable[j][k] = course;
                            isSlotOccupied[j][k] = true;
                            break;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < UG_with_pref_courseList.size(); i++) {
            String course = UG_with_pref_courseList.get(i);
            int cap = UG_with_pref_capList.get(i);
            List<Integer> prefIndices = UG_with_pref_list_num.get(i);

            // Loop over inp_1_capList elements to find a room
            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);

                // Check if the course cap is less than or equal to inp_1_cap
                if (cap <= inpCap) {
                    // Loop over inp_1_timingList to find a suitable slot
                    for (int k : prefIndices) {
                        // Check if the slot is not occupied
                        if (!isSlotOccupied[j][k]) {
                            // Assign the course to the timetable and mark the slot as occupied
                            timetable[j][k] = course;
                            isSlotOccupied[j][k] = true;
                            break;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < PG_with_no_pref_courseList.size(); i++) {
            String course = PG_with_no_pref_courseList.get(i);
            int cap = PG_with_no_pref_capList.get(i);
            //List<Integer> prefIndices = PG_with_pref_list_num.get(i);
        
            // Loop over inp_1_capList elements to find a room
            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);
        
                // Check if the course cap is less than or equal to inp_1_cap
                if (cap <= inpCap) {
                    // Loop over inp_1_timingList to find a suitable slot
                    for (int k = 0; k < inp_1_timingList.size(); k++) {
                        // Check if the slot is not occupied and matches preferences
                        if (!isSlotOccupied[j][k]) {
                            // Assign the course to the timetable and mark the slot as occupied
                            timetable[j][k] = course;
                            isSlotOccupied[j][k] = true;
                            break;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < UG_with_no_pref_courseList.size(); i++) {
            String course = UG_with_no_pref_courseList.get(i);
            int cap = UG_with_no_pref_capList.get(i);
            //List<Integer> prefIndices = PG_with_pref_list_num.get(i);
        
            // Loop over inp_1_capList elements to find a room
            for (int j = 0; j < inp_1_capList.size(); j++) {
                int inpCap = inp_1_capList.get(j);
        
                // Check if the course cap is less than or equal to inp_1_cap
                if (cap <= inpCap) {
                    // Loop over inp_1_timingList to find a suitable slot
                    for (int k = 0; k < inp_1_timingList.size(); k++) {
                        // Check if the slot is not occupied and matches preferences
                        if (!isSlotOccupied[j][k]) {
                            // Assign the course to the timetable and mark the slot as occupied
                            timetable[j][k] = course;
                            isSlotOccupied[j][k] = true;
                            break;
                        }
                    }
                }
            }
        }


// Similar loops for UG_with_pref_courses, PG_with_no_pref_courses, UG_with_no_pref_courses




    // Now, you have populated all the required lists based on conditions
    // You can use these lists as needed in your code.
}


    // Now, you have data split into different lists based on your conditions.
    // You can perform further operations as needed.
}

     

    

    
    
    
    
    
    

    


