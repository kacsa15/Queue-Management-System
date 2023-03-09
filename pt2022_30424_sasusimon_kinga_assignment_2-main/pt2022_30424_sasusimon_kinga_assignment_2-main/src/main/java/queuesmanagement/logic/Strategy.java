package queuesmanagement.logic;

import queuesmanagement.model.Server;
import queuesmanagement.model.Task;

import java.util.List;

public interface Strategy {

    void addTask(List<Server> servers, Task task);
    //method -> implement it in two different ways ----time and queue
}
