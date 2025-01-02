import java.util.*;

class RR{
    private Queue q;
    private ArrayList<TimeElement> timeLine;
    private int quantum;
    private TableData data;
    
    public RR(ArrayList<Process> addedProcess, int quantum){
        this.q = new Queue();
        this.timeLine = new ArrayList<TimeElement>();
        this.quantum = quantum;
        for(Process p : addedProcess){
            q.add(p);
        }
        logic();
    }

    public void logic(){
        data = new TableData(q.getQueue());
        int totalPosition = 0;
        while(!q.isEmpty()){
            Process p = q.getProcess();
            int remainedBurstTime = p.getRemainedBurstTime();
            if(remainedBurstTime < quantum){
                totalPosition += remainedBurstTime;
                p.updateProcess(totalPosition, remainedBurstTime);
            }else{
                totalPosition += quantum;
                p.updateProcess(totalPosition, quantum);
            }
            timeLine.add(new TimeElement(p));
            q.rolling();
        }
        data.updateTable(timeLine);
    }
    
    public ArrayList<TimeElement> getTimeLine(){return timeLine;}
    
    public TableData getTableData(){return data;}
}