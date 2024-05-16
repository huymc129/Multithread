
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Clock extends JPanel {
    private JLabel timeLabel;
    private String timezone;

    public Clock(String timezone) {
        this.timezone = timezone;
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
}