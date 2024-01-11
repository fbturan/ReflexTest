import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ReflexTest extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private long startTime;
    private Timer colorChangeTimer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ReflexTest frame = new ReflexTest();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    int flag = 0;

    public ReflexTest() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblNewLabel = new JLabel("Press 'START' button to get started");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(119, 28, 223, 16);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("START");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contentPane.getBackground() == Color.RED && flag == 2) {
                    calculateReactionTime();
                    flag = 1;
                }
                else if(flag == 2){
                	lblNewLabel.setText("Try Again!");
                	btnNewButton.setText("RETRY");
                	flag = 3;
                }
                 else {
                    contentPane.setBackground(Color.WHITE);
                    btnNewButton.setText("PRESS");
                    flag = 0;
                    startCountdown();
                }
            }
        });
        btnNewButton.setBounds(170, 215, 117, 29);
        contentPane.add(btnNewButton);
    }

    private void startCountdown() {
        Random random = new Random();
                    lblNewLabel.setText("READY!");
                    flag = 2;
                    colorChangeTimer = new Timer();
                    colorChangeTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                        	
                        		if (flag != 3) {
                        			contentPane.setBackground(Color.RED);
                        			startTime = System.currentTimeMillis();
                        		} else {
                        			flag = 0;
                        		}
                        }
                    }, random.nextInt(3000) + 1000);     
    }

    private void calculateReactionTime() {
        long reactionTime = System.currentTimeMillis() - startTime;
        lblNewLabel.setText("Reaction Time: " + reactionTime + " ms");
        flag = 1;
    }
}
