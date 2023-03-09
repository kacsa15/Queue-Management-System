package queuesmanagement.logic;

import queuesmanagement.model.Server;
import queuesmanagement.model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Scheduler {

    private List<Server> servers = new LinkedList<>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers){

        this.maxNoServers = maxNoServers;
        //create object and thread with that object
        for(int i = 1; i <= this.maxNoServers; i++){
            Server server = new Server();
            servers.add(server);
            Thread thread = new Thread(server);
            thread.start();
        }

    }

    public void changeStrategy(SelectionPolicy policy){

        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }

        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task task){
        //call strategy -> addTask method
        //changeStrategy(SelectionPolicy.SHORTEST_TIME);
        //strategy.addTask(servers,task);
        Server s = getMinimumQueue();
        s.addTask(task);
    }

    public List<Server> getServers(){
        return servers;
    }

    public boolean checkWorking(){
        for (Server s:servers) {
            if(s.getWaitingPeriod().get()>=0 && s.getCurrentTask() != null)
                return true;
        }
        return false;
    }

    public Server getMinimumQueue(){
        Server server = servers.get(0);
        int min = server.getWaitingPeriod().get();
        for (Server s2: servers) {
            if(s2.getWaitingPeriod().get() < min){
                min = s2.getWaitingPeriod().get();
                server = s2;
            }
            if(min == 0)
                break;
        }
        return server;
    }


    String printQueues(FileWriter fw) throws IOException {
        int i = 1;
        String status = "";
        for (Server s:servers) {
            if(s.getCurrentTask()!=null) {
                fw.write("\n");
                System.out.print("Queue" + i+ ": ");
                fw.write("Queue" + i+ ": ");
                status += "Queue" + i+ ": ";

                status += s.getCurrentTask().printTasks(fw);

                for (Task t: s.getTask()) {
                  status += t.printTasks(fw);
                }
                status += "<br>";
               // System.out.println("\n");

            }

            else {
                System.out.print("Queue" + i + ": closed\n");
                fw.write("\nQueue" + i + ": closed");
                status += "<br>Queue" + i + ": closed";
            }
            i++;
        }
        return status;
    }
    public void stop(){
        for(Server s:servers){
            s.stop();
        }
    }

}
