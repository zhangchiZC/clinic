package cs;

import po.MedicalList;
import util.DbUtil;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by ZC on 2019/7/5.
 */
public class TransfusionDoctor extends JFrame{
    private JPanel contentPane;
    private JTextField textField;
    DbUtil db = new DbUtil();
    List<MedicalList> medicalLists = new ArrayList<>();
    List<String> info = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TransfusionDoctor frame = new TransfusionDoctor();
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
    public TransfusionDoctor() {
        class MyTask extends TimerTask {

            @Override
            public void run() {

                setTitle("输液室医师");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 737, 535);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);

                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(28, 31, 595, 268);
                contentPane.add(scrollPane);

                try {
                    medicalLists = db.findPatientsWhoPayedAndTransfusion();
                    for (int i = 0; i < medicalLists.size(); i++) {
                        for (int j = 0; j < medicalLists.get(i).getMedicine_name().split(",").length; j++) {
                            info.add(medicalLists.get(i).getPatient_code() + "                        " + medicalLists.get(i).getPatient_name() + "                        " + medicalLists.get(i).getMedicine_name().split(",")[j] + "*" + medicalLists.get(i).getMedicine_amount().split(",")[j]);
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                JList list = new JList(info.toArray());
                scrollPane.setViewportView(list);

                JLabel label = new JLabel("\u6302\u53F7\u5355\u53F7");
                label.setBounds(28, 342, 72, 18);
                contentPane.add(label);

                textField = new JTextField();

                textField.setBounds(104, 339, 173, 24);
                contentPane.add(textField);
                textField.setColumns(10);

                JButton btnNewButton = new JButton("确认输液");
                btnNewButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,"分配到"+new Random().nextInt(51)+"号床位","床位",JOptionPane.INFORMATION_MESSAGE);

                    }
                });
                btnNewButton.setBounds(333, 338, 113, 27);
                contentPane.add(btnNewButton);

                info.clear();
                medicalLists.clear();
                setBounds(100, 100, 738, 535);
            }

        }

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0, 10000);
    }



}


