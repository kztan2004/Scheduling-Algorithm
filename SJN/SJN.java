import java.util.*;

class SJN{
    private Queue q;
    private ArrayList<TimeElement> timeLine;
    private TableData data;
    
    public SJN(ArrayList<Process> addedProcess){
        this.q = new Queue();
        this.timeLine = new ArrayList<TimeElement>();
        for(Process p : addedProcess){
            q.add(p);
        }
        logic();
    }

    public void logic(){
        data = new TableData(q.getQueue());
        int totalPosition = 0;
        while(!q.isEmpty()){
            Process p = q.getProcess(totalPosition);
            int remainedBurstTime = p.getRemainedBurstTime();
            totalPosition += remainedBurstTime;
            p.updateProcess(totalPosition, remainedBurstTime);
            timeLine.add(new TimeElement(p));
            q.rolling();
        }
        data.updateTable(timeLine);
    }
    
    public ArrayList<TimeElement> getTimeLine(){return timeLine;}
    
    public TableData getTableData(){return data;}
}