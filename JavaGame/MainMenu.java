import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Ship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));

        JPanel namePanel = new JPanel(new GridLayout(1, 2)); 
        JPanel speedPanel = new JPanel(new GridLayout(1, 2));

        JLabel nameLabel = new JLabel("Name:");
        JLabel speedLabel = new JLabel("Speed:");

        JTextField nameField = new JTextField();
        JTextField speedField = new JTextField();

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        speedPanel.add(speedLabel);
        speedPanel.add(speedField);

        add(namePanel);
        add(speedPanel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText();
                String speedStr = speedField.getText();

                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenu.this, "Name cannot be empty!");
                } 
                else if (speedStr.isEmpty()) {
                    JOptionPane.showMessageDialog(MainMenu.this, "Speed cannot be empty!");
                } 
                else {
                    try {
                        int speed = Integer.parseInt(speedStr);
                        JFrame gameF = new JFrame("Ship Game");
                        gameF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        gameF.setSize(500, 500);
                        GamePanel gamePanel = new GamePanel(playerName, speed);
                        gameF.add(gamePanel);
                        gameF.setVisible(true);
                        setVisible(false);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainMenu.this, "Speed should be only numbers!");
                    }
                }
            }
        });
        add(startButton);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
