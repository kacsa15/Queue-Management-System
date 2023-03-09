package queuesmanagement.gui;

import queuesmanagement.logic.SelectionPolicy;
import queuesmanagement.logic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Controller implements ActionListener {

    SimulationFrame simulationFrame;
    QueueSimulationFrame queueSimulationFrame;
    SimulationManager simulationManager;
    QueueSimulationFrame frame;



    public Controller(SimulationFrame simulationFrame, QueueSimulationFrame queueSimulationFrame){
        this.simulationFrame = simulationFrame;
        this.queueSimulationFrame = queueSimulationFrame;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        int numberClients;
        int numberQueues;
        int simulationTime;
        int minArrivalTime;
        int maxArrivalTime;
        int minServiceTime;
        int maxServiceTime;
        SelectionPolicy selectionPolicy;

        switch (command){
            case "START":

                 frame = new QueueSimulationFrame(":)");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(650,525);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                System.out.println("****");


                if(simulationFrame.getTimeStrategy().isSelected() && simulationFrame.getQueueStrategy().isSelected()) {
                        showMessageDialog(null, new JLabel("Please make sure you selected just one strategy"), "Invalid input", JOptionPane.ERROR_MESSAGE);
                }
                if(!simulationFrame.getQueueStrategy().isSelected() && !simulationFrame.getTimeStrategy().isSelected()) {
                showMessageDialog(null, new JLabel("Please make sure you selected a strategy"), "Invalid input", JOptionPane.ERROR_MESSAGE);
                 }
                //validate fields and get text->inputs
                if(simulationFrame.getNumberOfClientsTextField().toString().equals("") || Integer.parseInt(simulationFrame.getNumberOfQueuesTextField().getText().toString()) < 0){
                    showMessageDialog(null,new JLabel("Invalid number of clients"),"Invalid input", JOptionPane.ERROR_MESSAGE );
                }
                if(simulationFrame.getNumberOfQueuesTextField().getText().toString().equals("") || Integer.parseInt(simulationFrame.getNumberOfQueuesTextField().getText().toString()) < 0){
                    showMessageDialog(null,new JLabel("Invalid number of queues"),"Invalid input", JOptionPane.ERROR_MESSAGE );
                }
                if(simulationFrame.getMaxArrivalTimeTextField().getText().toString().equals("")
                        || simulationFrame.getMinArrivalTimeTextField().getText().toString().equals("")
                        || Integer.parseInt(simulationFrame.getMaxArrivalTimeTextField().getText().toString()) < 0
                        || Integer.parseInt(simulationFrame.getMinArrivalTimeTextField().getText().toString()) < 0
                        || Integer.parseInt(simulationFrame.getMaxArrivalTimeTextField().getText()) < Integer.parseInt(simulationFrame.getMinArrivalTimeTextField().getText())){
                    showMessageDialog(null,new JLabel("Invalid arrival time"),"Invalid input", JOptionPane.ERROR_MESSAGE );
                }
                if(simulationFrame.getMinServiceTimeTextField().getText().toString().equals("")
                        || simulationFrame.getMaxServiceTimeTextField().getText().toString().equals("")
                        || Integer.parseInt(simulationFrame.getMaxServiceTimeTextField().getText().toString()) < 0
                        || Integer.parseInt(simulationFrame.getMinServiceTimeTextField().getText().toString()) < 0
                        || Integer.parseInt(simulationFrame.getMaxServiceTimeTextField().getText()) < Integer.parseInt(simulationFrame.getMinServiceTimeTextField().getText())){
                    showMessageDialog(null,new JLabel("Invalid interval of service time"),"Invalid input", JOptionPane.ERROR_MESSAGE );
                }

                if(simulationFrame.getSimulationIntervalTextField().getText().toString().equals("") || Integer.parseInt(simulationFrame.getSimulationIntervalTextField().getText().toString()) < 0) {
                    showMessageDialog(null, new JLabel("Invalid simulation interval"), "Invalid input", JOptionPane.ERROR_MESSAGE);
                }
                    Thread thread;
                    numberClients = Integer.parseInt(simulationFrame.getNumberOfClientsTextField());
                System.out.println(simulationFrame.getNumberOfQueuesTextField().getText());
                    numberQueues = Integer.parseInt(simulationFrame.getNumberOfQueuesTextField().getText());
                    simulationTime = Integer.parseInt(simulationFrame.getSimulationIntervalTextField().getText());
                    minArrivalTime = Integer.parseInt(simulationFrame.getMinArrivalTimeTextField().getText());
                    maxArrivalTime = Integer.parseInt(simulationFrame.getMaxArrivalTimeTextField().getText());
                    minServiceTime = Integer.parseInt(simulationFrame.getMinServiceTimeTextField().getText());
                    maxServiceTime = Integer.parseInt(simulationFrame.getMaxServiceTimeTextField().getText());

                    if(simulationFrame.getQueueStrategy().isSelected())
                        selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
                    else
                        selectionPolicy = SelectionPolicy.SHORTEST_TIME;

                        SwingWorker<Void,Void> swingWorker = new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                                simulationManager =new SimulationManager(numberClients,numberQueues,simulationTime,minArrivalTime,maxArrivalTime,minServiceTime,maxServiceTime,selectionPolicy);

                               Thread thread = new Thread(simulationManager);
                                thread.start();
                                while(simulationManager.running){
                                    frame.output.setText(simulationManager.status);
                                    Thread.sleep(500);
                                }
                                return null;
                            }
                        };
                        swingWorker.execute();
                    System.out.println("**********");

                        //simulationManager =new SimulationManager(5,3,10,1,5,1,3,SelectionPolicy.SHORTEST_QUEUE);

                        //create new thread + start it

                       // simulationManager.run();



                break;
            case "STOP":
                // ide meg kene valami ???
                showMessageDialog(null,new JLabel("The simulation has stopped"),"Message", JOptionPane.ERROR_MESSAGE );
                Thread.currentThread().interrupt();
                break;
        }
    }
}
