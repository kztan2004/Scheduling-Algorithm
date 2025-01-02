import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class View extends JFrame{    
    private ArrayList<TimeElement> timeLine;
    private ArrayList<Process> addedProcess;
    private TableData td;
    private SJN sjn;
    private Color PRIMARY = new Color(249, 245, 246);
    private Color SECONDARY = new Color(248, 232, 238);

    public View(){
        addedProcess = new ArrayList<Process>();
        setTitle("Shortest Job Next (SJN)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayInput();
        setVisible(true);
    } 
    
    private void init(){
        addedProcess.add(new Process("P2", 6, 5, 1));
        addedProcess.add(new Process("P4", 6, 7, 5));
        addedProcess.add(new Process("P5", 6, 8, 6));
        addedProcess.add(new Process("P3", 6, 6, 1));
        addedProcess.add(new Process("P0", 6, 0, 3));
        addedProcess.add(new Process("P1", 4, 1, 3));
    }

    private void displayInput(){
        DefaultTableModel inputTableModel;
        setLayout(new GridLayout(2, 1));
        setSize(350,450);
        JPanel panel = new JPanel();
        panel.setBackground(PRIMARY);
        panel.setBorder(BorderFactory.createLineBorder(SECONDARY, 20));
        panel.setLayout(new GridLayout(6, 2, 1, 9));

        JLabel title1 = new JLabel("Shortest Job",SwingConstants.RIGHT);
        JLabel title2 = new JLabel(" Next (SJN)",SwingConstants.LEFT);

        JLabel label1 = new JLabel("Process Name: ",SwingConstants.CENTER);
        JTextField textField1 = new JTextField();

        JLabel label2 = new JLabel("Burst Time: ",SwingConstants.CENTER);
        JTextField textField2 = new JTextField();

        JLabel label3 = new JLabel("Arrival Time: ",SwingConstants.CENTER);
        JTextField textField3 = new JTextField();

        JLabel label4 = new JLabel("Priority: ",SwingConstants.CENTER);
        JTextField textField4 = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(SECONDARY);
        JButton addButton = new JButton("Add Process");
        addButton.setBackground(SECONDARY);
        
        inputTableModel = new DefaultTableModel(new String[]{"Process Name", "Arrival Time", "Burst Time", "Priority"}, 0);
        JTable inputTable = new JTable(inputTableModel);
        JScrollPane table = new JScrollPane(inputTable);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                table.setVisible(false);
                init();
                sjn = new SJN(addedProcess);
                displayOutput();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(textField1.getText().trim().isEmpty()) && checkTextField(textField2) && checkTextField(textField3) && checkTextField(textField4)){
                    addedProcess.add(new Process(textField1.getText(), Integer.parseInt(textField2.getText()), Integer.parseInt(textField3.getText()), Integer.parseInt(textField4.getText())));
                    inputTableModel.addRow(new Object[]{textField1.getText(), Integer.parseInt(textField2.getText()), Integer.parseInt(textField3.getText()), Integer.parseInt(textField4.getText())});
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                }
            }
        });
    
        panel.add(title1);
        panel.add(title2);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(label4);
        panel.add(textField4);
        panel.add(submitButton);
        panel.add(addButton);
        
        add(panel);
        add(table);
    }
    
    private boolean checkTextField(JTextField textField){
        return !(textField.getText().trim().isEmpty()) && Integer.parseInt(textField.getText()) >= 0;
    }

    private void displayOutput(){
        setLayout(new BorderLayout());
        timeLine = sjn.getTimeLine();
        td = sjn.getTableData();
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY);
        JLabel time0 = new JLabel("0",SwingConstants.CENTER);
        time0.setBackground(new Color(0, 0, 0, 0));
        time0.setOpaque(true);
        time0.setBounds(1, 90, 40, 15);
        panel.setLayout(null);
        int total = 0;
        panel.add(time0);
        JLabel remainedNameLabel;
        for(TimeElement t : timeLine){
            panel.add(t.getProcessNameLabel(total*40+20));
            total += t.getCurrentBurstTime();
            panel.add(t.getTimePositionLabel(total*40));
            remainedNameLabel = new JLabel(t.getProcessName()+"("+t.getRemainedBurstTime()+")"+t.getPriority(),SwingConstants.CENTER);
            remainedNameLabel.setBackground(new Color(0, 0, 0, 0));
            remainedNameLabel.setOpaque(true);
            remainedNameLabel.setBounds(total*40, 30, 40, 15);
            panel.add(remainedNameLabel);
        }
        for(JLabel arrivalLabel : td.getArrivalLabel()){
            panel.add(arrivalLabel);
        }
        panel.add(td.getTable());
        add(panel);
        if((total+1)*40 + 60 < 1400){
            setSize(1400, 500);
        }else{
            setSize((total+1)*40 + 60, 500);
        }
    }

    public static void main(String[] args){
        new View(); 
    }
}