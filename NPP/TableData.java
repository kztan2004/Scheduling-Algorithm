import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer; 
import java.awt.Color;

class TableData{
    private String column[] = {"PROCESS", "ARRIVAL TIME", "BURST TIME","PRIORITY", "FINISHED TIME", "TURNAROUND TIME", "WAITING TIME"};
    private String data[][];
    private JTable jt;
    private JScrollPane sp;
    private int size;
    private ArrayList<String> processName;
    private ArrayList<Integer> arrivalTime;
    private ArrayList<Integer> burstTime;
    private ArrayList<Integer> priority;
    private ArrayList<Integer> turnAroundTime;
    private ArrayList<Integer> finishedTime;
    private ArrayList<Integer> waitingTime;
    private ArrayList<JLabel> arrivalLabel;
    private float totalTurnAroundTime = 0;
    private float totalWaitingTime = 0;
    private float averageTurnAroundTime = 0;
    private float averageWaitingTime = 0;
    
    public TableData(ArrayList<Process> process){
        processName = new ArrayList<String>();
        arrivalTime = new ArrayList<Integer>();
        burstTime = new ArrayList<Integer>();
        priority = new ArrayList<Integer>();
        turnAroundTime = new ArrayList<Integer>();
        finishedTime = new ArrayList<Integer>();
        waitingTime = new ArrayList<Integer>();
        arrivalLabel = new ArrayList<JLabel>();
        
        JLabel arrivalTimeLabel;
        JLabel arrivalNameLabel;
        for(Process p: process){
            processName.add(p.getProcessName());
            arrivalTime.add(p.getArrivalTime());
            burstTime.add(p.getBurstTime());
            priority.add(p.getPriority());
            arrivalTimeLabel = new JLabel(p.getArrivalTime()+"",SwingConstants.CENTER);
            arrivalTimeLabel.setBackground(new Color(0, 0, 0, 0));
            arrivalTimeLabel.setOpaque(true);
            arrivalTimeLabel.setBounds(p.getArrivalTime()*40, 90, 40, 15);
            arrivalNameLabel = new JLabel(p.getProcessName()+"("+p.getBurstTime()+")"+p.getPriority(),SwingConstants.CENTER);
            arrivalNameLabel.setBackground(new Color(0, 0, 0, 0));
            arrivalNameLabel.setOpaque(true);
            arrivalNameLabel.setBounds(p.getArrivalTime()*40, 110, 40, 15);
            arrivalLabel.add(arrivalTimeLabel);
            arrivalLabel.add(arrivalNameLabel);
        }
        
        size = process.size();
        data = new String[size+1][7];
    }
    
    public void updateTable(ArrayList<TimeElement> tL){
        for(String name : processName){
            for(int i = 0; i < tL.size(); i++){
                if(tL.get(i).getProcessName().equals(name)){
                    finishedTime.add(tL.get(i).getTimePosition());
                    break;
                }
            }
        }
        for(int i = 0; i < size; i++){
            turnAroundTime.add(finishedTime.get(i) - arrivalTime.get(i));
            totalTurnAroundTime += finishedTime.get(i) - arrivalTime.get(i);
            waitingTime.add(turnAroundTime.get(i) - burstTime.get(i));
            totalWaitingTime += turnAroundTime.get(i) - burstTime.get(i);
        }
        averageTurnAroundTime = totalTurnAroundTime / size;
        averageWaitingTime = totalWaitingTime / size;
        
        for(int i = 0; i < size; i++){
            data[i][0] = processName.get(i);
            data[i][1] = arrivalTime.get(i)+"";
            data[i][2] = burstTime.get(i)+"";
            data[i][3] = priority.get(i)+"";
            data[i][4] = finishedTime.get(i)+"";
            data[i][5] = turnAroundTime.get(i)+"";
            data[i][6] = waitingTime.get(i)+"";
        }
        data[size][0] = "";
        data[size][1] = "";
        data[size][2] = "";
        data[size][3] = "Total Turnaround Time: "+ String.format("%.0f", totalTurnAroundTime);
        data[size][4] = "Average Turnaround Time: "+ String.format("%.2f", averageTurnAroundTime);
        data[size][5] = "Total Waiting Time: "+ String.format("%.0f", totalWaitingTime);
        data[size][6] = "Average Waiting Time: "+ String.format("%.2f", averageWaitingTime);
        
        jt = new JTable(data, column);
        jt.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jt.getColumnCount(); i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        sp = new JScrollPane(jt); // Add JTable to JScrollPane
        sp.setBounds(20, 170, 1300, (size+2)*30-7);
    }
    
    public JScrollPane getTable(){ return sp; }
    
    public ArrayList<JLabel> getArrivalLabel(){ return arrivalLabel; }
}