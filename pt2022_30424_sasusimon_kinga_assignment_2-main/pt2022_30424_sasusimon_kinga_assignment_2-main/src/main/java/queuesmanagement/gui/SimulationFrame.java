package queuesmanagement.gui;



import javax.swing.*;
import java.awt.*;


public class SimulationFrame extends JFrame {
    private JPanel contentPane;
    private JPanel inputData;
    private JPanel buttonsPanel;
    private JLabel titleLabel;
    private JPanel titlePanel;

    private JTextField numberOfClientsTextField;
    private JTextField numberOfQueuesTextField;
    private JTextField simulationIntervalTextField;
    private JTextField minArrivalTimeTextField;
    private JTextField maxArrivalTimeTextField;
    private JTextField minServiceTimeTextField;
    private JTextField maxServiceTimeTextField;
    private JLabel numberOfClientsLabel;
    private JLabel numberOfQueuesLabel;
    private JLabel simulationIntervalLabel;
    private JLabel minArrivalTimeLabel;
    private JLabel maxArrivalTimeLabel;
    private JLabel ServiceTimeLabel;
    private JLabel ArrivalTimeLabel;
    private JLabel strategyLabel;
    private JRadioButton queueStrategy;
    private JRadioButton timeStrategy;
    private JButton startButton;
    private JButton stopButton;
    public JLabel output;

    QueueSimulationFrame queueSimulationFrame = new QueueSimulationFrame(":)");
    Controller controller = new Controller(this, queueSimulationFrame);


    private final Font font = new Font("Arial", Font.ITALIC, 14);
    private final Font titleFont = new Font("Arial",Font.HANGING_BASELINE, 20);
    private final Color buttonColor = new Color(108, 138, 161);
    private final Color backgroundColor = new Color(200, 214, 224);




    public SimulationFrame(String name) {
        super(name);
        this.prepareGui();
        this.setColorsFonts();
    }

    public SimulationFrame() {

    }

    public void prepareGui(){
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPane = new JPanel();

        this.titlePanel = prepareTitlePanel();
        this.inputData = prepareinputData();
        this.buttonsPanel = preparebuttonsPanel();

        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(5,5,5,5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        contentPane.add(this.titlePanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        contentPane.add(this.inputData,constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx=0.5;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        contentPane.add(this.buttonsPanel,constraints);
        
        this.setContentPane(this.contentPane);
    }

    private JPanel prepareTitlePanel(){
        this.titlePanel = new JPanel();
        this.titlePanel.setLayout(new GridLayout(1,0));
        this.titleLabel = new JLabel("Queues Management Application", JLabel.CENTER);
        this.titlePanel.add(titleLabel);
        
        return titlePanel;

    }

    private JPanel preparebuttonsPanel() {
        this.buttonsPanel = new JPanel();
        this.startButton = new JButton("Start simulation");
        this.stopButton = new JButton("Stop simulation");

        this.buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));


        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,5,5,5);
        
        c.weightx = .01;
        c.weighty = .2;
        c.gridx = 0;
        c.gridy = 1;
        this.buttonsPanel.add(startButton, c);
        this.startButton.setActionCommand("START");
        this.startButton.addActionListener(this.controller);

        c.gridx = 1;
        c.gridy = 1;
        this.buttonsPanel.add(stopButton, c);
        this.stopButton.setActionCommand("STOP");
        this.stopButton.addActionListener(this.controller);

        return buttonsPanel;
    }

    private JPanel prepareinputData() {

        this.inputData = new JPanel();
        inputData.setLayout(new GridBagLayout());
        inputData.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5,5,5,5);

        this.numberOfClientsTextField = new JTextField(15);
        this.numberOfQueuesTextField = new JTextField(15);
        this.minArrivalTimeTextField = new JTextField(15);
        this.maxArrivalTimeTextField = new JTextField(15);
        this.minServiceTimeTextField = new JTextField(15);
        this.maxServiceTimeTextField = new JTextField(15);
        this.simulationIntervalTextField = new JTextField(15);
        this.queueStrategy = new JRadioButton("Queue");
        this.timeStrategy = new JRadioButton("Time");
        this.numberOfClientsLabel = new JLabel("Number of clients: ");
        this.numberOfQueuesLabel = new JLabel("Number of queues: ");
        this.ServiceTimeLabel = new JLabel("Service time: ");
        this.ArrivalTimeLabel = new JLabel("Arrival time: ");
        this.minArrivalTimeLabel = new JLabel("    Min  ");
        this.maxArrivalTimeLabel = new JLabel("     Max  ");
        this.simulationIntervalLabel = new JLabel("Maximum simulation time: ");
        this.strategyLabel = new JLabel("Distribute clients by: ");

        ////////////CLIENTS//////////////
        constraints.gridx=0;
        constraints.gridy=0;
        inputData.add(this.numberOfClientsLabel,constraints);

        constraints.gridx=1;
        constraints.gridy=0;
        inputData.add(this.numberOfClientsTextField,constraints);

        ////////////QUEUES//////////////
        constraints.gridx=0;
        constraints.gridy=1;
        inputData.add(this.numberOfQueuesLabel,constraints);

        constraints.gridx=1;
        constraints.gridy=1;
        inputData.add(this.numberOfQueuesTextField,constraints);


        ////////////SIMULATION INTERVAL//////////////
        constraints.gridx=0;
        constraints.gridy=2;
        inputData.add(this.simulationIntervalLabel,constraints);

        constraints.gridx=1;
        constraints.gridy=2;
        inputData.add(this.simulationIntervalTextField,constraints);

        ////////////ARRIVAL TIME//////////////

        JPanel minMaxLabel = new JPanel();
        minMaxLabel.setBackground(backgroundColor);
        //minMaxLabel.setBackground(Color.ORANGE);
        constraints.gridx=1;
        constraints.gridy=3;
        inputData.add(minMaxLabel,constraints);
        minMaxLabel.setLayout(new GridBagLayout());
        inputData.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        GridBagConstraints constraintsLabel = new GridBagConstraints();

        constraintsLabel.insets = new Insets(5,5,5,5);

        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 0;
        constraintsLabel.weightx=0.5;
        constraintsLabel.fill=GridBagConstraints.HORIZONTAL;
        minMaxLabel.add(this.minArrivalTimeLabel,constraintsLabel);

        constraintsLabel.gridx = 1;
        constraintsLabel.gridy = 0;
        constraintsLabel.weightx=0.5;
        constraintsLabel.fill=GridBagConstraints.HORIZONTAL;
        minMaxLabel.add(this.maxArrivalTimeLabel,constraintsLabel);

        constraints.gridx=0;
        constraints.gridy=4;
        inputData.add(this.ArrivalTimeLabel,constraints);

        
        JPanel minMaxTextField = new JPanel();
        minMaxTextField.setBackground(backgroundColor);
        constraints.gridx=1;
        constraints.gridy=4;
        inputData.add(minMaxTextField,constraints);

        minMaxTextField.setLayout(new GridBagLayout());
       
        GridBagConstraints constraintsTextField = new GridBagConstraints();

        constraintsTextField.insets = new Insets(5,5,5,5);

        constraintsTextField.gridx = 0;
        constraintsTextField.gridy = 0;
        constraintsTextField.weightx=0.5;
        constraintsTextField.fill=GridBagConstraints.HORIZONTAL;
        minMaxTextField.add(this.minArrivalTimeTextField,constraintsTextField);

        constraintsTextField.gridx = 1;
        constraintsTextField.gridy = 0;
        constraintsTextField.weightx=0.5;
        constraintsTextField.fill=GridBagConstraints.HORIZONTAL;
        minMaxTextField.add(this.maxArrivalTimeTextField,constraintsTextField);
        


        ////////////SERVICE TIME//////////////
        constraints.gridx=0;
        constraints.gridy=5;
        inputData.add(this.ServiceTimeLabel,constraints);

        JPanel serviceMinMaxTextField = new JPanel();
        serviceMinMaxTextField.setBackground(backgroundColor);
        constraints.gridx=1;
        constraints.gridy=5;
        inputData.add(serviceMinMaxTextField,constraints);

        serviceMinMaxTextField.setLayout(new GridBagLayout());
        GridBagConstraints constraintsService = new GridBagConstraints();

        constraintsService.insets = new Insets(5,5,5,5);

        constraintsService.gridx = 0;
        constraintsService.gridy = 0;
        constraintsService.weightx=0.5;
        constraintsService.fill=GridBagConstraints.HORIZONTAL;
        serviceMinMaxTextField.add(this.minServiceTimeTextField,constraintsService);

        constraintsService.gridx = 1;
        constraintsService.gridy = 0;
        constraintsService.weightx=0.5;
        constraintsService.fill=GridBagConstraints.HORIZONTAL;
        serviceMinMaxTextField.add(this.maxServiceTimeTextField,constraintsService);


        constraints.gridx=0;
        constraints.gridy=6;
        inputData.add(this.strategyLabel,constraints);

        JPanel strategyPanel = new JPanel();
        strategyPanel.setBackground(backgroundColor);
        queueStrategy.setBackground(backgroundColor);
        timeStrategy.setBackground(backgroundColor);
        constraints.gridx=1;
        constraints.gridy=6;
        inputData.add(strategyPanel,constraints);

        strategyPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraintsStrategy = new GridBagConstraints();

        constraintsStrategy.insets = new Insets(5,5,5,5);

        constraintsStrategy.gridx = 0;
        constraintsStrategy.gridy = 0;
        constraintsStrategy.weightx=0.5;
        constraintsStrategy.fill=GridBagConstraints.HORIZONTAL;
        strategyPanel.add(this.queueStrategy,constraintsStrategy);

        constraintsStrategy.gridx = 1;
        constraintsStrategy.gridy = 0;
        constraintsStrategy.weightx=0.5;
        constraintsStrategy.fill=GridBagConstraints.HORIZONTAL;
        strategyPanel.add(this.timeStrategy,constraintsStrategy);


        return inputData;
    }

    private void setColorsFonts(){


        this.timeStrategy.setFont(font);
        this.queueStrategy.setFont(font);
        this.strategyLabel.setFont(font);
        this.numberOfClientsLabel.setFont(font);
        this.numberOfQueuesLabel.setFont(font);
        this.maxArrivalTimeLabel.setFont(font);
        this.ServiceTimeLabel.setFont(font);
        this.ArrivalTimeLabel.setFont(font);
        this.minArrivalTimeLabel.setFont(font);
        this.simulationIntervalLabel.setFont(font);
        this.titleLabel.setFont(titleFont);
        this.titlePanel.setBackground(backgroundColor);
        this.inputData.setBackground(backgroundColor);
        this.buttonsPanel.setBackground(backgroundColor);
        this.contentPane.setBackground(backgroundColor);
        this.stopButton.setBackground(buttonColor);
        this.startButton.setBackground(buttonColor);
        this.startButton.setBorder(BorderFactory.createMatteBorder(5,5,5,5,buttonColor));
        this.stopButton.setBorder(BorderFactory.createMatteBorder(5,5,5,5,buttonColor));
        this.startButton.setFont(font);
        this.stopButton.setFont(font);

        
    }

    public String getNumberOfClientsTextField() {
        return numberOfClientsTextField.getText().toString();
    }

    public JTextField getNumberOfQueuesTextField() {
        return numberOfQueuesTextField;
    }

    public JTextField getSimulationIntervalTextField() {
        return simulationIntervalTextField;
    }

    public JTextField getMinArrivalTimeTextField() {
        return minArrivalTimeTextField;
    }

    public JTextField getMaxArrivalTimeTextField() {
        return maxArrivalTimeTextField;
    }

    public JTextField getMinServiceTimeTextField() {
        return minServiceTimeTextField;
    }

    public JTextField getMaxServiceTimeTextField() {
        return maxServiceTimeTextField;
    }

    public JRadioButton getQueueStrategy() {
        return queueStrategy;
    }

    public JRadioButton getTimeStrategy() {
        return timeStrategy;
    }
}





