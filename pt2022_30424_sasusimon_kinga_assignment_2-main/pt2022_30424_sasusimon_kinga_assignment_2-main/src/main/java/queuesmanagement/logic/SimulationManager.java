package queuesmanagement.logic;

import queuesmanagement.gui.SimulationFrame;
import queuesmanagement.model.Server;
import queuesmanagement.model.Task;

import java.io.*;
import java.util.*;

public class SimulationManager implements Runnable{


    //data--guiboool
    public SimulationFrame simulationFrame = new SimulationFrame(":)");

    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;

    //not in the example
    public int minArrivalTime;
    public int maxArrivalTime;


    private Scheduler scheduler;
    private List<Task> generatedTask = new ArrayList<>();
    private ConcreteStrategyQueue concreteStrategyQueue= new ConcreteStrategyQueue();
    private int totalServiceTime = 0;
    private int peakHour = 0;
    public boolean running = true;
    public String status = new String();



    public SimulationManager(int numberOfClients, int numberOfServers,int simulationTime, int minArrivalTime, int maxArrivalTime,int minProcessingTime,int maxProcessingTime, SelectionPolicy selectionPolicy){
        this.numberOfClients = numberOfClients;
        System.out.println(this.numberOfClients);
        this.numberOfServers = numberOfServers;
        this.timeLimit = simulationTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.selectionPolicy = selectionPolicy;
        this.scheduler = new Scheduler(numberOfServers);
        generateRandomTask();
    }

    public int getMaxNoOfClients() {
        int numberClients = 0;
        List<Server> servers = this.scheduler.getServers();
        List<Task> tasksInServer = new ArrayList<>();
        for (Server server : servers) {
            tasksInServer = List.of(server.getListTasks());
            numberClients += tasksInServer.size();
        }
        return numberOfClients;
    }

    public void generateRandomTask(){

        Random random = new Random();
        Task task;
        generatedTask = new ArrayList<>();

        int taskService;
        int taskArrival;
        for(int i = 1; i <= numberOfClients; i++){

            //generate arrival and service time for the next tasks
            taskService = random.nextInt(minProcessingTime, maxProcessingTime+1);
            totalServiceTime += taskService;
            taskArrival = random.nextInt(minArrivalTime, maxArrivalTime+1);

            //create the task and add to the list
            task = new Task(i,taskArrival,taskService);
            generatedTask.add(task);


            Collections.sort(generatedTask, new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                   return Integer.compare(o1.getArrivalTime(),o2.getArrivalTime());
                }
            });
        }

    }

    public void printTasks(FileWriter fw) throws IOException {
        System.out.println("Waiting clients: ");
        status += "Waiting clients: <br>";
        fw.write("Waiting clients: ");
        if(generatedTask.isEmpty()){
            System.out.println("no one is waiting anymore");
            fw.write("no one is waiting anymore\n");
            status += "no one is waiting anymore<br>";
        }
        for(Task task1: generatedTask){
            System.out.println("(" + task1.getID() +
                    "," + task1.getArrivalTime() +
                    "," + task1.getServiceTime() +
                    "); ");
            fw.write("(" + task1.getID() +
                    "," + task1.getArrivalTime() +
                    "," + task1.getServiceTime() +
                    "); ");
            status += "(" + task1.getID() +
                    "," + task1.getArrivalTime() +
                    "," + task1.getServiceTime() +
                    "); ";
        }
        status += "<br>";
    }

    @Override
    public void run() {

        FileWriter fw= null;
        try {
                fw = new FileWriter("C:\\Users\\ASUS\\Desktop\\PT2022_30424_SasuSimon_Kinga_Assignment_2\\src\\main\\resources\\others.txt");


            int currentTime = 0;
            Task task;
            int i = 0;

            while(currentTime <= timeLimit){
                status = "<html>";

                    if(generatedTask != null && !generatedTask.isEmpty()){
                        while(!generatedTask.isEmpty()){
                            task = generatedTask.get(0);
                            if(task.getArrivalTime() == currentTime){
                                scheduler.dispatchTask(task);
                                generatedTask.remove(task);
                            }else{break;}
                        }
                    }else{
                       // if(generatedTask.isEmpty() ){
                            //scheduler.stop();
                       // }

                        if( scheduler != null && !scheduler.checkWorking()) {
                            System.out.print("\nEnd of simulation\n");
                            status = status + "<br>End of simulation<br>";
                            fw.write("\nEnd of simulation\n");
                            final float averageServiceTime = (float) totalServiceTime / numberOfClients;
                            final float peakHour = (float) getMaxNoOfClients() / numberOfClients;
                            fw.write("\n Average service time: " + averageServiceTime + "\n");
                            fw.write("\n Peak hour: " + getMaxNoOfClients() + "/client\n");
                            running = false;
                            fw.close();
                            return;

                        }
                    }
                System.out.print("\n\nTime: " + currentTime + "\n");
                    fw.write("\n\nTime: " + currentTime + "\n");
                    status += "<br><br>Time: " + currentTime + "<br>";
                    /***********/
                    printTasks(fw);
                    status += scheduler.printQueues(fw);

                try {
                    Thread.sleep(1000);
                    currentTime++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                status += "</html>";
            }
            System.out.println("Out of time :(");
            fw.write("\nOut of time :(");

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("******************************************************");


    }

}
