package queuesmanagement.model;


import java.io.FileWriter;
import java.io.IOException;

public class Task {
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Task() {

    }

    public String printTasks(FileWriter fw) throws IOException {
        String status = "";
            System.out.println("(" + getID() +
                    "," + getArrivalTime() +
                    "," + getServiceTime() +
                    "); ");
            fw.write("(" + getID() +
                    "," + getArrivalTime() +
                    "," + getServiceTime() +
                    "); ");
            status += "(" + getID() +
                    "," + getArrivalTime() +
                    "," + getServiceTime() +
                    "); ";
            return status;
        }

    public int getID() {
        return ID;
    }


    public int getArrivalTime() {
        return arrivalTime;
    }


    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

}
