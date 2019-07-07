package test;

/**
 * Created by ZC on 2019/6/29.
 */

import po.Display;
import po.Display2;
import util.DbUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

        JLabel label = new JLabel("医生");
        label.setBounds(68, 42, 72, 18);
        contentPane.add(label);

        JLabel label_1 = new JLabel("患者");
        label_1.setBounds(177, 42, 72, 18);
        contentPane.add(label_1);

        JLabel label_2 = new JLabel("诊室");
        label_2.setBounds(319, 42, 72, 18);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("排队数");
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
                    List<Display2> displays = db.createViewAndFindDisplayListByDepatmentName("口腔科");
                    //System.out.println(displays);
                    if (displays.size() <= 2) {
                        StringBuffer stringBuilder = new StringBuffer();
                        for (Display2 display2 : displays) {
                            stringBuilder.append(display2.getDoctor_name() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + display2.getPatient_names() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + display2.getDepartment_name() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + display2.getPatientAmount() + "<br>");
                            //System.out.println(stringBuilder.toString());

                        }
                        displayLabel.setText("<html>" + "<font color=\"green\" >" + stringBuilder.toString() + "</font><br>" + "</html>");

                        stringBuilder.delete(0,stringBuilder.length());
                        Thread.sleep(500);

                    } else {
                        StringBuffer stringBuilder = new StringBuffer();
                        for (int i = 0; i < 2; i++) {
                            stringBuilder.append(db.createViewAndFindDisplayListByDepatmentName("口腔科").get(i).getDoctor_name()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+db.createViewAndFindDisplayListByDepatmentName("口腔科").get(i).getPatient_names()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+db.createViewAndFindDisplayListByDepatmentName("口腔科").get(i).getDepartment_name()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+db.createViewAndFindDisplayListByDepatmentName("口腔科").get(i).getPatientAmount()+"<br>");
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

