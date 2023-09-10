package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Model.InputDB;

public class InputDetailsManager implements Serializable {
    private List<InputDB> inputDetailsList;

    public InputDetailsManager() {
        inputDetailsList = new ArrayList<>();
    }

    public void addInputDetails(InputDB inputDetails) {
        inputDetailsList.add(inputDetails);
    }

    public List<InputDB> getInputDetailsList() {
        return inputDetailsList;
    }
}

