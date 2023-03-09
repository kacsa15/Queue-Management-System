package queuesmanagement.logic;

import queuesmanagement.model.Server;
import queuesmanagement.model.Task;

import java.util.ArrayList;
import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    public void addTask(List<Server> servers, Task task) {
        //ahol kisebb a waiting time

        Server serverToBeAddedTo = servers.get(0);
        for(Server server: servers){
            if(serverToBeAddedTo.getWaitingPeriod().get() > server.getWaitingPeriod().get()){
                serverToBeAddedTo = server;
            }
        }

        serverToBeAddedTo.addTask(task);
    }

    public static void main( String[] args ) throws InterruptedException {

        Task task1 = new Task(1,2,3);
        Task task2 = new Task(2,5,1);
        Task task3 = new Task(3,7,9);
        Task task4 = new Task(4,7,4);
        Server server1 = new Server();
        Server server2 = new Server();
        List<Server> serverList = new ArrayList<>();

        serverList.add(server1);
        serverList.add(server2);



        serverList.get(1).addTask(task1);
        serverList.get(1).addTask(task2);
        serverList.get(0).addTask(task3);


        ConcreteStrategyTime concreteStrategyTime = new ConcreteStrategyTime();
        concreteStrategyTime.addTask(serverList,task4);

        System.out.println("alma");

        //print the blocking queue
        Task task = new Task();
        for(Server server: serverList){
            System.out.println("eleje");
            while(!server.getTask().isEmpty()){
                task = server.getTasks();
                System.out.println("(" + task.getID() +
                        "," + task.getArrivalTime() +
                        "," + task.getServiceTime() +
                        "); " + "\n\n");
                server.getTask().remove(task);
            }
            if(server.getTask().isEmpty()){
                Thread.currentThread().interrupt();
                break;}
            System.out.println(server.getWaitingPeriod());
        }
    }


}
