package queuesmanagement.gui;

import queuesmanagement.logic.SimulationManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class QueueSimulationFrame extends JFrame {

    private JPanel contentPanel;
    private JPanel mazePanel;
    private JPanel titlePanel;
    private JPanel simulationResultsPanel;
    private JPanel timePanel;
    private JPanel waitingQueuePanel;
    private JLabel queuesLabsels[][];
    private JLabel peakHourResult;
    private JLabel avarageWaitingTimeResult;
    private JLabel avarageServiceTimeResult;
    private JLabel titleLabel;
    private JLabel waitingClients;
    private JLabel timeRemaining;
    private JLabel timeLabel;
    public JLabel output;

    private final Font font = new Font("Arial", Font.ITALIC, 16);
    private final Font titleFont = new Font("Arial",Font.HANGING_BASELINE, 24);
    private final Font queueFont = new Font("Arial",Font.HANGING_BASELINE, 16);
    private final Color buttonColor = new Color(108, 138, 161);
    private final Color backgroundColor = new Color(200, 214, 224);
    private final Color queueColor = new Color(	217, 217, 217);

   // SimulationManager simulationManager = new SimulationManager();


    public QueueSimulationFrame(String name) {
        super(name);
        this.prepareGui();
        this.setColorsFonts();
    }

    public void prepareGui(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPanel = new JPanel();

        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(5,5,5,5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.weighty=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.titlePanel = prepareTitlePanel();
        this.contentPanel.add(titlePanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.simulationResultsPanel = prepareSimulationResultsPanel();
        this.contentPanel.add(simulationResultsPanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.timePanel = prepareTimePanel();
        this.contentPanel.add(timePanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 0.9;
        constraints.weighty = 0.9;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.mazePanel = Maze();
        this.contentPanel.add(mazePanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.waitingQueuePanel = prepareWaitingQueuePanel();
        this.contentPanel.add(waitingQueuePanel,constraints);


        this.setContentPane(this.contentPanel);

    }

    private JPanel prepareTitlePanel(){
        this.titlePanel = new JPanel();
        this.titlePanel.setLayout(new GridLayout(1,0));
        this.titleLabel = new JLabel("Simulation", JLabel.CENTER);
        this.titlePanel.add(titleLabel);

        return titlePanel;

    }

    private JPanel prepareSimulationResultsPanel(){
        this.simulationResultsPanel = new JPanel();
        JLabel avarageWaitingTime = new JLabel("Avarage waiting time:", JLabel.RIGHT);
        JLabel avarageServiceTime = new JLabel("Avarage service time:", JLabel.RIGHT);
        JLabel peakHourLabel = new JLabel("Peak hour:", JLabel.RIGHT);
        avarageWaitingTime.setFont(font);
        avarageServiceTime.setFont(font);
        peakHourLabel.setFont(font);
        this.avarageWaitingTimeResult = new JLabel("???", JLabel.LEFT);
        this.avarageServiceTimeResult = new JLabel("???", JLabel.LEFT);
        this.peakHourResult = new JLabel("???", JLabel.LEFT);



        simulationResultsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.simulationResultsPanel.add(avarageWaitingTime,constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.simulationResultsPanel.add(avarageWaitingTimeResult,constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.simulationResultsPanel.add(avarageServiceTime,constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.simulationResultsPanel.add(avarageServiceTimeResult,constraints);

        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.simulationResultsPanel.add(peakHourLabel,constraints);

        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.simulationResultsPanel.add(peakHourResult,constraints);

        return simulationResultsPanel;

    }

    private JPanel prepareTimePanel(){
        this.timePanel = new JPanel();
        this.timeLabel = new JLabel("Time:", JLabel.CENTER);
        this.timeRemaining = new JLabel("???", JLabel.CENTER);
        this.timePanel.add(timeLabel);
        this.timePanel.add(timeRemaining);

        return timePanel;
    }

    private JPanel prepareWaitingQueuePanel(){
        this.waitingQueuePanel = new JPanel();
        this.waitingClients = new JLabel("(?,?,?)", JLabel.CENTER);
        JLabel waitingClientsLabel = new JLabel("Waiting: ", JLabel.CENTER);
        waitingClientsLabel.setFont(font);
        this.waitingQueuePanel.add(waitingClientsLabel);
        this.waitingQueuePanel.add(waitingClients);

        return waitingQueuePanel;
    }

    private void setColorsFonts(){
        this.timePanel.setBackground(backgroundColor);
        this.titlePanel.setBackground(backgroundColor);
        this.simulationResultsPanel.setBackground(backgroundColor);
        this.waitingQueuePanel.setBackground(backgroundColor);
        this.mazePanel.setBackground(backgroundColor);
        this.contentPanel.setBackground(backgroundColor);
        this.titleLabel.setFont(titleFont);
        this.avarageWaitingTimeResult.setFont(font);
        this.avarageServiceTimeResult.setFont(font);
        this.peakHourResult.setFont(font);
        this.timeLabel.setFont(font);
        this.timeRemaining.setFont(font);
        this.waitingClients.setFont(font);

    }

    public JPanel Maze() {
        mazePanel = new JPanel();
//        getContentPane().add(mazePanel);
//
//
//       // Object MazeCreator;
//        int row = 3;//simulationManager.getNumberOfClients();
//        int col = 5;//simulationManager.getNumberOfServers();
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 500, 500);
//        mazePanel.setLayout(new GridLayout(row, col));
//
//        JLabel[][] grid= new JLabel[row][col];
//
//            for (int j = 0; j < col; j++){
//                grid[0][j] = new JLabel("Queue", SwingConstants.CENTER);
//                grid[0][j].setFont(queueFont);
//                grid[0][j].setBorder(new LineBorder(Color.BLACK));
//                grid[0][j].setBackground(buttonColor);
//                grid[0][j].setMinimumSize(new Dimension(20,5));
//                grid[0][j].setPreferredSize(new Dimension(20,5));
//                grid[0][j].setMaximumSize(new Dimension(20,5));
//                grid[0][j].setOpaque(true);
//                mazePanel.add(grid[0][j]);
//            }
//        for (int i = 1; i < row; i++){
//            for (int j = 0; j < col; j++){
//                grid[i][j] = new JLabel("No one", SwingConstants.CENTER);
//                grid[i][j].setBorder(new LineBorder(Color.BLACK));
//                grid[i][j].setBackground(queueColor);
//                grid[i][j].setOpaque(true);
//                mazePanel.add(grid[i][j]);
//            }
//        }
//        return mazePanel;
        output = new JLabel();
        mazePanel.add(output);
        return mazePanel;
    }


    public static void main( String[] args){
        JFrame frame = new QueueSimulationFrame(":)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,525);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
