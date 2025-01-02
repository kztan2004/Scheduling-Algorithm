import java.util.*;

class Queue{
    private ArrayList<Process> queue;
    private int queueNum;
    private ArrayList<Integer> arrival;
    private int arrivalNum = -1;
    private boolean processCompare = false;
    
    public Queue(){
        this.queue = new ArrayList<Process>();
        this.arrival = new ArrayList<Integer>();
    }
    
    public void add(Process p){
        if(isEmpty()){
            queue.add(p);
            arrival.add(p.getArrivalTime());
        }else if(p.getArrivalTime() > queue.get(queue.size()-1).getArrivalTime()){
            queue.add(p);
            arrival.add(p.getArrivalTime());
        }else{
            for(int i = 0; i < queue.size(); i++){
                if(p.getArrivalTime() < queue.get(i).getArrivalTime()){
                    queue.add(i, p);
                    arrival.add(i, p.getArrivalTime());
                    break;
                }else if(p.getArrivalTime() == queue.get(i).getArrivalTime()){
                    if(p.getPriority() < queue.get(i).getPriority()){
                        queue.add(i, p);
                        arrival.add(i, p.getArrivalTime());
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
                processCompare = false;
            }
        }
    }
    
    public int getNextArrival(){
        arrivalNum++;
        if(arrivalNum >= arrival.size()){
            return -1;
        }
        return arrival.get(arrivalNum);
    }
    
    public Process getProcess(int totalPosition){
        queueNum = 0;
        if(totalPosition == 0){
            return queue.get(0);
        }
        for(int i = 0; i < queue.size(); i++){
            if(queue.get(i).getTimePosition() <= totalPosition){
                if(queue.get(i).getPriority() < queue.get(queueNum).getPriority()){
                    queueNum = i;
                }else if(queue.get(i).getPriority() == queue.get(queueNum).getPriority()){
                    if(processCompare){
                        if(queue.get(i).getArrivalTime() < queue.get(queueNum).getArrivalTime()){
                            queueNum = i;
                        }
                    }else{
                        if(queue.get(i).getTimePosition() < queue.get(queueNum).getTimePosition()){
                            queueNum = i;
                        }
                    }
                }
            }
        }
        if(!processCompare){
            processCompare = true;
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