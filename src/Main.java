import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame {
    private Clock clock;
    private JTextField timezoneField;

    public Main() {
        setTitle("Simple Clock");
        setSize(250, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timezoneField = new JTextField("");
        timezoneField.setBounds(20, 120, 100, 30);
        add(timezoneField);

        JButton bt = new JButton("Open");
        bt.setBounds(130, 120, 100, 30);
        add(bt);

        clock = new Clock();
        add(clock);

        clock.Start();

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });

        setVisible(true);
    }

    private void updateClock() {
        String timezone = timezoneField.getText();
        clock.setTimezone(timezone);
    }

    public static void main(String[] args) {
        new Main();
    }
}

class Clock extends JPanel {
    private JLabel timeLabel;
    private String timezone;

    public Clock() {
        setBounds(20, 20, 200, 80);
        setLayout(new FlowLayout());

        timeLabel = new JLabel();
        add(timeLabel);
    }

    public void Start() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    updateTime();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        if (timezone != null && !timezone.isEmpty()) {
            dateFormat.setTimeZone(java.util.TimeZone.getTimeZone(timezone));
        }
        timeLabel.setText(dateFormat.format(new Date()));
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
        updateTime();
    }
}
