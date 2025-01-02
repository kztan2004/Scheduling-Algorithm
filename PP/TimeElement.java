import javax.swing.*;
import java.awt.*;

class TimeElement{
    private String processName;
    private int timePosition;
    private int currentBurstTime;
    private int remainedBurstTime;
    private int priority;
    private JLabel processNameLabel;
    private JLabel timePositionLabel;
    public TimeElement(Process p){
        this.processName = p.getProcessName();
        this.timePosition = p.getTimePosition();
        this.currentBurstTime = p.getCurrentBurstTime();
        this.remainedBurstTime = p.getRemainedBurstTime();
        this.priority = p.getPriority();
        processNameLabel = new JLabel(""+ processName,SwingConstants.CENTER);
        processNameLabel.setOpaque(true);
        processNameLabel.setBackground(Color.white);
        processNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timePositionLabel = new JLabel(""+ timePosition,SwingConstants.CENTER);
        timePositionLabel.setOpaque(true);
        timePositionLabel.setBackground(new Color(0, 0, 0, 0));
    }
    
    public int getCurrentBurstTime(){return currentBurstTime; }
    
    public String getProcessName(){return processName;}
    
    public int getTimePosition(){return timePosition;}
    
    public int getRemainedBurstTime(){return remainedBurstTime;}
    
    public int getPriority(){return priority;}
    
    public JLabel getProcessNameLabel(int x){ 
        processNameLabel.setBounds(x, 50, currentBurstTime*40, 40);
        return processNameLabel; 
    }
    
    public JLabel getTimePositionLabel(int x){ 
        timePositionLabel.setBounds(x, 90, 40, 15);
        return timePositionLabel; 
    }
}