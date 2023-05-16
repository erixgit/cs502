import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoTextAreaButton extends JFrame {
    private JTextArea textArea1, textArea2;
    private JButton button;
    
    public TwoTextAreaButton() {
        // Set up the frame
        setTitle("Method encapsulation proof of concept");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the text areas
        textArea1 = new JTextArea();
        textArea2 = new JTextArea();
        
        // Set borders
        textArea1.setBorder(BorderFactory.createLineBorder(Color.black));
        textArea2.setBorder(BorderFactory.createLineBorder(Color.black));

        // Create the button
        button = new JButton("Run query");
        
        // Create a panel for the text areas and add them to it
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new GridLayout(2, 1));
        textAreaPanel.add(textArea1);
        textAreaPanel.add(textArea2);
        
        // Create a panel for the button and add it to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea2.setText(App.execute(textArea1.getText()));
            }
        });
        
        // Add the panels to the frame
        add(textAreaPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Display the frame
        setVisible(true);
    }
}