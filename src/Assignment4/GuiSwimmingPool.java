package Assignment4;

import java.awt.*;
import javax.swing.*;

public class GuiSwimmingPool {

    private JFrame mainFrame;

    //Reception panel
    private JPanel pnlReception;

    public GuiSwimmingPool(){

    }

    public void start() {
        mainFrame = new JFrame();
        mainFrame.setBounds(0,0,730,526);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setTitle("Adventure Swimming pool");
        initializeGUI();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
    }

    private void initializeGUI(){
        //Reception panel
        pnlReception = new JPanel();
        pnlReception.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Reception"));
        pnlReception.setBounds(13,403,693,82);
        pnlReception.setLayout(null);

        mainFrame.add(pnlReception);
    }
}
