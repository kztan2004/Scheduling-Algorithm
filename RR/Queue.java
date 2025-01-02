import java.util.*;

class Queue{
    private ArrayList<Process> queue;
    private int queueNum;
    
    public Queue(){
        this.queue = new ArrayList<Process>();
    }
    
    public void add(Process p){
        if(isEmpty()){
            queue.add(p);
        }else if(p.getArrivalTime() > queue.get(queue.size()-1).getArrivalTime()){
            queue.add(p);
        }else{
            for(int i = 0; i < queue.size(); i++){
                if(p.getArrivalTime() < queue.get(i).getArrivalTime()){
                    queue.add(i, p);
                    break;
                }else if(p.getArrivalTime() == queue.get(i).getArrivalTime()){
                    if(p.getPriority() < queue.get(i).getPriority()){
                        queue.add(i, p);
                        break;
                    }
                }
            }
        }
    }
    
    public void rolling(){
        if(!isEmpty()){
            if(getProcess().getRemainedBurstTime() != 0){
                adding(getProcess());
            }
            queue.remove(0);
        }
    }
    
    public void adding(Process p){
        if(queue.size() == 1){
            queue.add(p);
            return;
        }
        for(int i = queue.size() - 1; i > 0; i--){
            if(p.getTimePosition() >= queue.get(i).getTimePosition()){
                queue.add(i+1, p);  
                return;
            }
        }
    }
    
    public Process getProcess(){
        return queue.get(0);
    }
    
    public boolean isEmpty(){
        return queue.size() == 0;
    }
    
    public void displayQueue(){
        for(Process p: queue){
            System.out.print(p.getProcessName()+":"+p.getRemainedBurstTime()+" ");
        }
        System.out.println();
    }
    
    public ArrayList<Process> getQueue(){ return queue; }
}