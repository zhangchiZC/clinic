package cs;

/**
 * Created by ZC on 2019/6/29.
 */

import po.Display;
import util.DbUtil;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class DisplayScreen extends JFrame {

    private JPanel contentPane;
    DbUtil db = new DbUtil();

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayScreen frame = new DisplayScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public DisplayScreen() {
        setTitle("叫号");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 601, 299);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel(" \u533B\u751F");
        label.setBounds(68, 42, 72, 18);
        contentPane.add(label);

        JLabel label_1 = new JLabel("\u8BCA\u5BA4");
        label_1.setBounds(177, 42, 72, 18);
        contentPane.add(label_1);

        JLabel label_2 = new JLabel("\u6302\u53F7\u5355");
        label_2.setBounds(319, 42, 72, 18);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("\u60A3\u8005\u59D3\u540D");
        label_3.setBounds(448, 42, 72, 18);
        contentPane.add(label_3);

        JLabel displayLabel = new JLabel("");
        displayLabel.setBounds(68, 79, 452, 160);
        contentPane.add(displayLabel);

        class MyTask extends TimerTask {

            @Override
            public void run() {
                try {
//                    DbUtil db = new DbUtil();
                    List<Display> displays = db.findAllfromDisplay();
                    //System.out.println(displays);
                    if (displays.size() <= 2) {
                        StringBuffer stringBuilder = new StringBuffer();
                        for (Display display : displays) {
                            stringBuilder.append(display.getDoctor_name() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + display.getDepartment_name() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + display.getCode() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + display.getPatient_name() + "<br>");
                            //System.out.println(stringBuilder.toString());

                        }
                        displayLabel.setText("<html>" + "<font color=\"green\" >" + stringBuilder.toString() + "</font><br>" + "</html>");

                        stringBuilder.delete(0,stringBuilder.length());
                        Thread.sleep(500);

                    } else {
                        StringBuffer stringBuilder = new StringBuffer();
                        for (int i = 0; i < 2; i++) {
                            stringBuilder.append(db.findAllfromDisplay().get(i).getDoctor_name()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+db.findAllfromDisplay().get(i).getDepartment_name()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+db.findAllfromDisplay().get(i).getCode()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+db.findAllfromDisplay().get(i).getPatient_name()+"<br>");
                        }
                        System.out.println(stringBuilder);
                        displayLabel.setText("<html>" + "<font color=\"green\" >" + stringBuilder.toString() + "</font><br>" + "</html>");
                        stringBuilder.delete(0,stringBuilder.length()+1);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("刷新ing");
                displayLabel.setBounds(68, 79, 452, 160);
                contentPane.add(displayLabel);

            }
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0, 2000);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

