package queuesmanagement.model;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private Task currentTask;
    private AtomicInteger waitingPeriod;
    boolean running = true;


    public Server(){
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask){

        //add task to the queue
        tasks.add(newTask);

        //waiting time increases with the service
        //time of the task
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }


    public Task getTasks() {
        //idk ha ide ez kell vagy mas --- de ok :)
        if(!tasks.isEmpty())
        return tasks.element();
        return null;
    }

    public Task[] getListTasks(){
        Task[] tasksArray = new Task[tasks.size()];
        tasks.toArray(tasksArray);
        return tasksArray;
    }

    public BlockingQueue<Task> getTask() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public Task getCurrentTask() {
        return currentTask;
    }


    @Override
    public void run() {

        while(running){
            //take next task from the queue
            try {
                //currentTask = getTasks();
                currentTask = tasks.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(currentTask != null && currentTask.getServiceTime() > 0){
                //stop the thread -- task processing time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                currentTask.setServiceTime(currentTask.getServiceTime() - 1);


                //decrement the waiting period
                waitingPeriod.decrementAndGet();
            }
            currentTask = null;

        }

    }
    public void stop(){
        running = false;
    }
}
