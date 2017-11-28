package Assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

/**
 * The GUI for assignment 3
 */
public class GUISemaphore {
    /**
     * These are the components you need to handle.
     * You have to add listeners and/or code
     * Static controls are defined inline
     */
    private JFrame frame;                // The Main window
    private JProgressBar bufferStatus;    // The progressbar, showing content in buffer

    // Data for Producer Scan
    private JButton btnStartS;            // Button start Scan
    private JButton btnStopS;            // Button stop Scan
    private JLabel lblStatusS;            // Status Scan
    // DAta for producer Arla
    private JButton btnStartA;            // Button start Arla
    private JButton btnStopA;            // Button stop Arla
    private JLabel lblStatusA;            // Status Arla
    //Data for producer AxFood
    private JButton btnStartX;            // Button start AxFood
    private JButton btnStopX;            // Button stop AxFood
    private JLabel lblStatusX;            // Status AxFood

    // Data for consumer ICA
    private JLabel lblIcaItems;            // Ica limits
    private JLabel lblIcaWeight;
    private JLabel lblIcaVolume;
    private JLabel lblIcaStatus;        // load status
    private JTextArea lstIca;            // The cargo list
    private JButton btnIcaStart;        // The buttons
    private JButton btnIcaStop;
    private JCheckBox chkIcaCont;        // Continue checkbox
    //Data for consumer COOP
    private JLabel lblCoopItems;
    private JLabel lblCoopWeight;
    private JLabel lblCoopVolume;
    private JLabel lblCoopStatus;        // load status
    private JTextArea lstCoop;            // The cargo list
    private JButton btnCoopStart;        // The buttons
    private JButton btnCoopStop;
    private JCheckBox chkCoopCont;        // Continue checkbox
    // Data for consumer CITY GROSS
    private JLabel lblCGItems;
    private JLabel lblCGWeight;
    private JLabel lblCGVolume;
    private JLabel lblCGStatus;            // load status
    private JTextArea lstCG;            // The cargo list
    private JButton btnCGStart;            // The buttons
    private JButton btnCGStop;
    private JCheckBox chkCGCont;        // Continue checkbox

    private ButtonListener buttonListener;
    private Storage storage;
    private Factory scan;
    private Thread scanThread;
    private Factory arla;
    private Thread arlaThread;
    private Factory axfood;
    private Thread axfoodThread;
    private Truck ica;
    private Thread icaThread;
    private Truck coop;
    private Thread coopThread;
    private Truck citygross;
    private Thread citygrossThread;

    /**
     * Constructor, creates the window
     */
    public GUISemaphore() {
        this.storage = new Storage(this, 100);

        arla = new Factory(storage);
        axfood = new Factory(storage);
        scan = new Factory(storage);

        ica = new Truck(storage, this, "ica", 10.0, 5.0);
        coop = new Truck(storage, this, "coop", 15.0, 20.0);
        citygross = new Truck(storage, this, "citygross", 17.5, 23.0);
    }

    /**
     * Starts the application
     */
    public void start() {
        frame = new JFrame();
        frame.setBounds(0, 0, 730, 526);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Food Supply System");
        initializeGUI();                    // Fill in components
        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen
    }

    /**
     * Sets up the GUI with components
     */
    private void initializeGUI() {
        // First create the three main panels
        JPanel pnlBuffer = new JPanel();
        pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Storage"));
        pnlBuffer.setBounds(13, 403, 693, 82);
        pnlBuffer.setLayout(null);
        // Then create the progressbar, only component in buffer panel
        bufferStatus = new JProgressBar();
        bufferStatus.setBounds(155, 37, 500, 23);
        bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
        bufferStatus.setForeground(Color.GREEN);
        pnlBuffer.add(bufferStatus);
        JLabel lblmax = new JLabel("Max capacity (items):");
        lblmax.setBounds(10, 42, 126, 13);
        pnlBuffer.add(lblmax);
        frame.add(pnlBuffer);

        buttonListener = new ButtonListener();

        JPanel pnlProd = new JPanel();
        pnlProd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producers"));
        pnlProd.setBounds(13, 13, 229, 379);
        pnlProd.setLayout(null);

        JPanel pnlCons = new JPanel();
        pnlCons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumers"));
        pnlCons.setBounds(266, 13, 440, 379);
        pnlCons.setLayout(null);

        // Now add the three panels to producer panel
        JPanel pnlScan = new JPanel();
        pnlScan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Scan"));
        pnlScan.setBounds(6, 19, 217, 100);
        pnlScan.setLayout(null);

        // Content Scan panel
        btnStartS = new JButton("Start Producing");
        btnStartS.setBounds(10, 59, 125, 23);
        btnStartS.addActionListener(buttonListener);
        pnlScan.add(btnStartS);
        btnStopS = new JButton("Stop");
        btnStopS.setBounds(140, 59, 65, 23);
        btnStopS.addActionListener(buttonListener);
        btnStopS.setEnabled(false);
        pnlScan.add(btnStopS);
        lblStatusS = new JLabel("Staus Idle/Stop/Producing");
        lblStatusS.setBounds(10, 31, 200, 13);
        pnlScan.add(lblStatusS);
        // Add Scan panel to producers
        pnlProd.add(pnlScan);

        // The Arla panel
        JPanel pnlArla = new JPanel();
        pnlArla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Arla"));
        pnlArla.setBounds(6, 139, 217, 100);
        pnlArla.setLayout(null);

        // Content Arla panel
        btnStartA = new JButton("Start Producing");
        btnStartA.setBounds(10, 59, 125, 23);
        btnStartA.addActionListener(buttonListener);
        pnlArla.add(btnStartA);
        btnStopA = new JButton("Stop");
        btnStopA.setBounds(140, 59, 65, 23);
        btnStopA.addActionListener(buttonListener);
        btnStopA.setEnabled(false);
        pnlArla.add(btnStopA);
        lblStatusA = new JLabel("Staus Idle/Stop/Producing");
        lblStatusA.setBounds(10, 31, 200, 13);
        pnlArla.add(lblStatusA);
        // Add Arla panel to producers
        pnlProd.add(pnlArla);

        // The AxFood Panel
        JPanel pnlAxfood = new JPanel();
        pnlAxfood.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: AxFood"));
        pnlAxfood.setBounds(6, 262, 217, 100);
        pnlAxfood.setLayout(null);

        // Content AxFood Panel
        btnStartX = new JButton("Start Producing");
        btnStartX.setBounds(10, 59, 125, 23);
        btnStartX.addActionListener(buttonListener);
        pnlAxfood.add(btnStartX);
        btnStopX = new JButton("Stop");
        btnStopX.setBounds(140, 59, 65, 23);
        btnStopX.addActionListener(buttonListener);
        btnStopX.setEnabled(false);
        pnlAxfood.add(btnStopX);
        lblStatusX = new JLabel("Staus Idle/Stop/Producing");
        lblStatusX.setBounds(10, 31, 200, 13);
        pnlAxfood.add(lblStatusX);
        // Add Axfood panel to producers
        pnlProd.add(pnlAxfood);
        // Producer panel done, add to frame
        frame.add(pnlProd);

        // Next, add the three panels to Consumer panel
        JPanel pnlICA = new JPanel();
        pnlICA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: ICA"));
        pnlICA.setBounds(19, 19, 415, 100);
        pnlICA.setLayout(null);

        // Content ICA panel
        // First the limits panel
        JPanel pnlLim = new JPanel();
        pnlLim.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
        pnlLim.setBounds(6, 19, 107, 75);
        pnlLim.setLayout(null);
        JLabel lblItems = new JLabel("Items:");
        lblItems.setBounds(7, 20, 50, 13);
        pnlLim.add(lblItems);
        JLabel lblWeight = new JLabel("Weight:");
        lblWeight.setBounds(7, 35, 50, 13);
        pnlLim.add(lblWeight);
        JLabel lblVolume = new JLabel("Volume:");
        lblVolume.setBounds(7, 50, 50, 13);
        pnlLim.add(lblVolume);
        lblIcaItems = new JLabel("Data");
        lblIcaItems.setBounds(60, 20, 47, 13);
        pnlLim.add(lblIcaItems);
        lblIcaWeight = new JLabel("Data");
        lblIcaWeight.setBounds(60, 35, 47, 13);
        pnlLim.add(lblIcaWeight);
        lblIcaVolume = new JLabel("Data");
        lblIcaVolume.setBounds(60, 50, 47, 13);
        pnlLim.add(lblIcaVolume);
        pnlICA.add(pnlLim);
        // Then rest of controls
        lstIca = new JTextArea();
        lstIca.setEditable(false);
        JScrollPane spane = new JScrollPane(lstIca);
        spane.setBounds(307, 16, 102, 69);
        spane.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlICA.add(spane);
        btnIcaStart = new JButton("Start Loading");
        btnIcaStart.setBounds(118, 64, 120, 23);
        btnIcaStart.addActionListener(buttonListener);
        pnlICA.add(btnIcaStart);
        btnIcaStop = new JButton("Stop");
        btnIcaStop.setBounds(240, 64, 60, 23);
        btnIcaStop.addActionListener(buttonListener);
        btnIcaStop.setEnabled(false);
        pnlICA.add(btnIcaStop);
        lblIcaStatus = new JLabel("Ica stop status here");
        lblIcaStatus.setBounds(118, 16, 150, 23);
        pnlICA.add(lblIcaStatus);
        chkIcaCont = new JCheckBox("Continue load");
        chkIcaCont.setBounds(118, 39, 130, 17);
        pnlICA.add(chkIcaCont);
        // All done, add to consumers panel
        pnlCons.add(pnlICA);

        JPanel pnlCOOP = new JPanel();
        pnlCOOP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: COOP"));
        pnlCOOP.setBounds(19, 139, 415, 100);
        pnlCOOP.setLayout(null);
        pnlCons.add(pnlCOOP);

        // Content COOP panel
        // First the limits panel
        JPanel pnlLimC = new JPanel();
        pnlLimC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
        pnlLimC.setBounds(6, 19, 107, 75);
        pnlLimC.setLayout(null);
        JLabel lblItemsC = new JLabel("Items:");
        lblItemsC.setBounds(7, 20, 50, 13);
        pnlLimC.add(lblItemsC);
        JLabel lblWeightC = new JLabel("Weight:");
        lblWeightC.setBounds(7, 35, 50, 13);
        pnlLimC.add(lblWeightC);
        JLabel lblVolumeC = new JLabel("Volume:");
        lblVolumeC.setBounds(7, 50, 50, 13);
        pnlLimC.add(lblVolumeC);
        lblCoopItems = new JLabel("Data");
        lblCoopItems.setBounds(60, 20, 47, 13);
        pnlLimC.add(lblCoopItems);
        lblCoopWeight = new JLabel("Data");
        lblCoopWeight.setBounds(60, 35, 47, 13);
        pnlLimC.add(lblCoopWeight);
        lblCoopVolume = new JLabel("Data");
        lblCoopVolume.setBounds(60, 50, 47, 13);
        pnlLimC.add(lblCoopVolume);
        pnlCOOP.add(pnlLimC);
        // Then rest of controls
        lstCoop = new JTextArea();
        lstCoop.setEditable(false);
        JScrollPane spaneC = new JScrollPane(lstCoop);
        spaneC.setBounds(307, 16, 102, 69);
        spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlCOOP.add(spaneC);
        btnCoopStart = new JButton("Start Loading");
        btnCoopStart.setBounds(118, 64, 120, 23);
        btnCoopStart.addActionListener(buttonListener);
        pnlCOOP.add(btnCoopStart);
        btnCoopStop = new JButton("Stop");
        btnCoopStop.setBounds(240, 64, 60, 23);
        btnCoopStop.addActionListener(buttonListener);
        btnCoopStop.setEnabled(false);
        pnlCOOP.add(btnCoopStop);
        lblCoopStatus = new JLabel("Coop stop status here");
        lblCoopStatus.setBounds(118, 16, 150, 23);
        pnlCOOP.add(lblCoopStatus);
        chkCoopCont = new JCheckBox("Continue load");
        chkCoopCont.setBounds(118, 39, 130, 17);
        pnlCOOP.add(chkCoopCont);
        // All done, add to consumers panel
        pnlCons.add(pnlCOOP);

        JPanel pnlCG = new JPanel();
        pnlCG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: CITY GROSS"));
        pnlCG.setBounds(19, 262, 415, 100);
        pnlCG.setLayout(null);
        pnlCons.add(pnlCG);

        // Content CITY GROSS panel
        // First the limits panel
        JPanel pnlLimG = new JPanel();
        pnlLimG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
        pnlLimG.setBounds(6, 19, 107, 75);
        pnlLimG.setLayout(null);
        JLabel lblItemsG = new JLabel("Items:");
        lblItemsG.setBounds(7, 20, 50, 13);
        pnlLimG.add(lblItemsG);
        JLabel lblWeightG = new JLabel("Weight:");
        lblWeightG.setBounds(7, 35, 50, 13);
        pnlLimG.add(lblWeightG);
        JLabel lblVolumeG = new JLabel("Volume:");
        lblVolumeG.setBounds(7, 50, 50, 13);
        pnlLimG.add(lblVolumeG);
        lblCGItems = new JLabel("Data");
        lblCGItems.setBounds(60, 20, 47, 13);
        pnlLimG.add(lblCGItems);
        lblCGWeight = new JLabel("Data");
        lblCGWeight.setBounds(60, 35, 47, 13);
        pnlLimG.add(lblCGWeight);
        lblCGVolume = new JLabel("Data");
        lblCGVolume.setBounds(60, 50, 47, 13);
        pnlLimG.add(lblCGVolume);
        pnlCG.add(pnlLimG);
        // Then rest of controls
        lstCG = new JTextArea();
        lstCG.setEditable(false);
        JScrollPane spaneG = new JScrollPane(lstCG);
        spaneG.setBounds(307, 16, 102, 69);
        spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlCG.add(spaneG);
        btnCGStart = new JButton("Start Loading");
        btnCGStart.setBounds(118, 64, 120, 23);
        btnCGStart.addActionListener(buttonListener);
        pnlCG.add(btnCGStart);
        btnCGStop = new JButton("Stop");
        btnCGStop.setBounds(240, 64, 60, 23);
        btnCGStop.addActionListener(buttonListener);
        btnCGStop.setEnabled(false);
        pnlCG.add(btnCGStop);
        lblCGStatus = new JLabel("CITY GROSS stop status here");
        lblCGStatus.setBounds(118, 16, 150, 23);
        pnlCG.add(lblCGStatus);
        chkCGCont = new JCheckBox("Continue load");
        chkCGCont.setBounds(118, 39, 130, 17);
        pnlCG.add(chkCGCont);
        // All done, add to consumers panel
        pnlCons.add(pnlCOOP);

        // Add consumer panel to frame
        frame.add(pnlCons);
    }

    public boolean[] isContinueLoading() {
        boolean[] booleans = new boolean[3];
        booleans[0] = chkIcaCont.isSelected();//ica check box
        booleans[1] = chkCoopCont.isSelected();//coop check box
        booleans[2] = chkCGCont.isSelected();//citygross check box
        return booleans;
    }

    public void printIca(String name) {
        if (name.equals("")) {
            lstIca.setText("");
        } else {
            lstIca.append(name);
        }

    }

    public void printCoop(String name) {
        if (name.equals("")) {
            lstCoop.setText("");
        } else {
            lstCoop.append(name);
        }

    }

    public void printCityGross(String name) {
        if (name.equals("")) {
            lstCG.setText("");
        } else {
            lstCG.append(name);
        }
    }

    public void printIcaNumbers(int item, double currentVolume, double currentWeight) {
        lblIcaItems.setText(String.valueOf(item));
        lblIcaVolume.setText(String.valueOf(currentVolume));
        lblIcaWeight.setText(String.valueOf(currentWeight));
    }

    public void printCoopNumbers(int item, double currentVolume, double currentWeight) {
        lblCoopItems.setText(String.valueOf(item));
        lblCoopVolume.setText(String.valueOf(currentVolume));
        lblCoopWeight.setText(String.valueOf(currentWeight));
    }

    public void printCityGrossNumbers(int item, double currentVolume, double currentWeight) {
        lblCGItems.setText(String.valueOf(item));
        lblCGVolume.setText(String.valueOf(currentVolume));
        lblCGWeight.setText(String.valueOf(currentWeight));
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object btnPressed = e.getSource();

            System.out.println("Button pressed");

            if (btnPressed.equals(btnStartA)) {
                arla.setRunning(true);
                arlaThread = new Thread(arla);
                arlaThread.start();
                btnStartA.setEnabled(false);
                btnStopA.setEnabled(true);
            } else if (btnPressed.equals(btnStartS)) {
                scan.setRunning(true);
                scanThread = new Thread(scanThread);
                scanThread.start();
                btnStartS.setEnabled(false);
                btnStopS.setEnabled(true);
            } else if (btnPressed.equals(btnStartX)) {
                axfood.setRunning(true);
                axfoodThread = new Thread(axfood);
                axfoodThread.start();
                btnStartX.setEnabled(false);
                btnStopX.setEnabled(true);
            } else if (btnPressed.equals(btnStopA)) {
                arla.setRunning(false);
                btnStartA.setEnabled(true);
                btnStopA.setEnabled(false);
            } else if (btnPressed.equals(btnStopX)) {
                axfood.setRunning(false);
                btnStopX.setEnabled(false);
                btnStartX.setEnabled(true);
            } else if (btnPressed.equals(btnStopS)) {
                scan.setRunning(false);
                btnStartS.setEnabled(true);
                btnStopS.setEnabled(false);
            } else if (btnPressed.equals(btnIcaStart)) {
                ica.setRunning(true);
                icaThread = new Thread(ica);
                icaThread.start();
                btnIcaStart.setEnabled(false);
                btnIcaStop.setEnabled(true);
            } else if (btnPressed.equals(btnCoopStart)) {
                coop.setRunning(true);
                coopThread = new Thread(coop);
                coopThread.start();
                btnCoopStart.setEnabled(false);
                btnCoopStop.setEnabled(true);
            } else if (btnPressed.equals(btnCGStart)) {
                citygross.setRunning(true);
                citygrossThread = new Thread(citygross);
                citygrossThread.start();
                btnCGStart.setEnabled(false);
                btnCGStop.setEnabled(true);
            } else if (btnPressed.equals(btnIcaStop)) {
                ica.setRunning(false);
                btnIcaStart.setEnabled(true);
                btnIcaStop.setEnabled(false);
            } else if (btnPressed.equals(btnCoopStop)) {
                coop.setRunning(false);
                btnCoopStart.setEnabled(true);
                btnCoopStop.setEnabled(false);
            } else if (btnPressed.equals(btnCGStop)) {
                citygross.setRunning(false);
                btnCGStart.setEnabled(true);
                btnCGStop.setEnabled(false);
            }
        }
    }
}
