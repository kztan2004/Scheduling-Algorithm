class Process {
    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int timePosition;
    private int remainedBurstTime;
    private int currentBurstTime;
    
    public Process(String processName, int burstTime, int arrivalTime, int priority){
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.timePosition = arrivalTime;
        this.burstTime = burstTime;
        this.remainedBurstTime = burstTime;
        this.priority = priority;
    }
    
    public void updateProcess(int totalPosition, int remainedBurstTime){
        this.timePosition = totalPosition;
        this.remainedBurstTime -= remainedBurstTime;
        this.currentBurstTime = remainedBurstTime;
    }
    
    public String getProcessName(){ return processName; }
    
    public int getArrivalTime(){ return arrivalTime; }
    
    public int getBurstTime(){ return burstTime; }
    
    public int getTimePosition(){ return timePosition; }
    
    public int getRemainedBurstTime(){ return remainedBurstTime; }
    
    public int getPriority(){ return priority; }
    
    public int getCurrentBurstTime(){ return currentBurstTime; }
}

