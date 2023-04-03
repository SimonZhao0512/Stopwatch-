import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener {
    // static variables, JFrame, JButton, JLabel are all javax.swing classes
    JFrame frame = new JFrame();
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JButton setTimeButton = new JButton("Set");
    JLabel timeLabel = new JLabel();
    JTextField jtextfield = new JTextField();
    JTextField setTimeBox = new JTextField();
    JTextField setActualTime = new JTextField();
    JLabel inputTimeLabel = new JLabel();

    int elapsedTime;
    int seconds = (elapsedTime / 1000) % 60;
    int mins = (elapsedTime / 60000) % 60;
    int hours = (elapsedTime / 3600000);
    boolean started = false;
    // placeholder for hours numbers and seconds: 00 00 00
    String seconds_string = String.format("%02d", seconds);
    String mins_string = String.format("%02d", mins);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, e -> {
        // TODO Auto-generated method stub
        System.out.println(elapsedTime);
        if (this.elapsedTime <= 0) {
            stop();
            Toolkit.getDefaultToolkit().beep();
        } else {
            elapsedTime -= 1000;
            hours = (elapsedTime / 3600000);
            mins = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            String seconds_string = String.format("%02d", seconds);
            String mins_string = String.format("%02d", mins);
            String hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + " : " + mins_string + " : " + seconds_string);
        }

    });

    public Stopwatch() {

        timeLabel.setText(hours_string + " : " + mins_string + " : " + seconds_string);
        timeLabel.setBounds(50, 100, 300, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        inputTimeLabel.setText("minutes");
        inputTimeLabel.setBounds(150, 10, 150, 50);
        inputTimeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        inputTimeLabel.setBorder(BorderFactory.createBevelBorder(1));
        inputTimeLabel.setOpaque(true);
        // inputTimeLabel.setHorizontalAlignment(JTextField.CENTER);

        setTimeBox.setText("");
        setTimeBox.setBounds(50, 10, 100, 50);
        setTimeBox.setFont(new Font("Verdana", Font.PLAIN, 30));

        startButton.setBounds(50, 200, 150, 50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this); // what trigger the action to happen

        resetButton.setBounds(220, 200, 150, 50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this); // what trigger the action to happen

        setTimeButton.setBounds(300, 10, 100, 50);
        setTimeButton.setFont(new Font("Ink Free", Font.PLAIN, 25));
        setTimeButton.setFocusable(false);
        setTimeButton.addActionListener(this); // what trigger the action to happen

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.add(jtextfield);
        frame.add(setTimeBox);
        frame.add(inputTimeLabel);
        frame.add(setTimeButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!started) {
                started = true;
                startButton.setText("Stop");
                timer.start();
            } else {
                started = false;
                startButton.setText("Start");
                timer.stop();
            }
        } else if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("Start");
            reset();
        } else if (e.getSource() == setTimeButton) {
            setTime(Integer.parseInt(setTimeBox.getText()));
            // this.elapsedTime = Integer.parseInt(setTimeBox.getText()) * 1000 * 60;
            // System.out.println(elapsedTime);
            // this.seconds = (elapsedTime / 1000) % 60;
            // this.mins = (elapsedTime / 60000) % 60;
            // this.hours = (elapsedTime / 3600000);
            // this.seconds_string = String.format("%02d", seconds);
            // this.mins_string = String.format("%02d", mins);
            // this.hours_string = String.format("%02d", hours);
            // timeLabel.setText(hours_string + " : " + mins_string + " : " +
            // seconds_string);
        } else if (e.getSource() == timer) {
            elapsedTime -= 1000;
            if (elapsedTime < 0) {
                timer.stop();
                timeLabel.setText("00 : 00 : 00");
                started = false;
                startButton.setText("Start");
            } else {
                hours = (elapsedTime / 3600000);
                mins = (elapsedTime / 60000) % 60;
                seconds = (elapsedTime / 1000) % 60;
                String seconds_string = String.format("%02d", seconds);
                String mins_string = String.format("%02d", mins);
                String hours_string = String.format("%02d", hours);
                timeLabel.setText(hours_string + " : " + mins_string + " : " + seconds_string);
            }
        }

    }

    public void start() {
        timer.start();

    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        setTime(0);

    }

    public void setTime(int myTime) {
        this.elapsedTime = myTime * 1000 * 60;
        this.seconds = (this.elapsedTime / 1000) % 60;
        this.mins = (this.elapsedTime / 60000) % 60;
        this.hours = (this.elapsedTime / 3600000);
        // placeholder for hours numbers and seconds: 00 00 00
        this.seconds_string = String.format("%02d", this.seconds);
        this.mins_string = String.format("%02d", this.mins);
        this.hours_string = String.format("%02d", this.hours);
        this.timeLabel.setText(hours_string + " : " + mins_string + " : " + seconds_string);
    }

}