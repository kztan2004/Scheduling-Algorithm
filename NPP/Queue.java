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
            if(queue.get(queueNum).getRemainedBurstTime() == 0){
                queue.remove(queueNum);
            }
        }
    }
    
    public Process getProcess(int totalPosition){
        queueNum = 0;
        if(totalPosition == 0){
            return queue.get(0);
        }
        for(int i = 0; i < queue.size(); i++){
            if(queue.get(i).getArrivalTime() <= totalPosition){
                if(queue.get(i).getPriority() < queue.get(queueNum).getPriority()){
                    queueNum = i;
                }else if(queue.get(i).getArrivalTime() == queue.get(queueNum).getArrivalTime()){
                    if(queue.get(i).getPriority() < queue.get(queueNum).getPriority()){
                        queueNum = i;
                    }
                }
            }
        }
        return queue.get(queueNum);
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