package View;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class input_file_2 extends JPanel{
    
    private JButton submitButton;
    private JButton addButton;
    private JPanel panel;
    private int courses;

    private List<JTextField> courseFields;
    private List<JTextField> capacityFields;
    private List<JTextField> preferenceFields;
    public List<JTextField> getCourseFields() {
        return courseFields;
    }

    public List<JTextField> getCapacityFields() {
        return capacityFields;
    }

    public List<JTextField> getPreferenceFields() {
        return preferenceFields;
    }





    public input_file_2(){

            submitButton = new JButton("Next");
            addButton = new JButton("+");
            panel = new JPanel();
            courses=0;

            preferenceFields = new ArrayList<>();
            capacityFields = new ArrayList<>();
            courseFields = new ArrayList<>();
            

            addButton = new JButton("+");

            setLayout(new GridBagLayout());
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.NONE;

            Insets buttonInset = new Insets(0, 0, 20, 0);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(courses<=20){
                        addElement();
                        courses++;
                    }
                    else{
                        JOptionPane.showMessageDialog(addButton, "Maximum Courses Reached");
                    }
                }
            });


            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            
            gridBagConstraints.insets = buttonInset;
            panel.add(addButton);

            //panel.add(submitButton);



            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            add(panel, gridBagConstraints);


    }

    private void addElement() {
        JPanel elementPanel = new JPanel();
            JLabel courseLabel = new JLabel("Course:");
            JTextField courseField = new JTextField(10);

            JLabel capacityLabel = new JLabel("Capacity:");
            JTextField capacityField = new JTextField(10);

            JLabel preferencesLabel = new JLabel("Preferences:");
            JTextField preferencesField = new JTextField(10);
        
            elementPanel.add(courseLabel);
            elementPanel.add(courseField);
            elementPanel.add(capacityLabel);
            elementPanel.add(capacityField);
            elementPanel.add(preferencesLabel);
            elementPanel.add(preferencesField);
            panel.add(elementPanel);
            panel.add(submitButton);
        
        //courseFields.add(courseField);
        revalidate();
        repaint();
    }

    public void setsubmitButtonListener(ActionListener listener) {
        // Assuming you have a JButton named submitButton
        submitButton.addActionListener(listener);
    }
}
