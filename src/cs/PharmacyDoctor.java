package cs;

/**
 * Created by ZC on 2019/7/3.
 */

import po.MedicalList;
import util.DbUtil;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PharmacyDoctor extends JFrame {

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
                    PharmacyDoctor frame = new PharmacyDoctor();
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
    public PharmacyDoctor() {
//        setTitle("药房医师");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 737, 535);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setBounds(28, 31, 595, 268);
//        contentPane.add(scrollPane);


        class MyTask extends TimerTask {

            @Override
            public void run() {

//            }
//        }
                setTitle("药房医师");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 737, 535);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);

                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(28, 31, 595, 268);
                contentPane.add(scrollPane);

//                for (int n=0;n<3;n++){
//                    try {
//                        info.add(db.findPatientsWhoPayed().get(n).getPatient_code()+"   "+db.findPatientsWhoPayed().get(n).getPatient_name());
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }

                try {
                    medicalLists = db.findPatientsWhoPayed();
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

                JButton btnNewButton = new JButton("\u786E\u8BA4\u62FF\u836F");
                btnNewButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            db.updateMedicineInventory(textField.getText().toString().trim());
                            db.deleteAfterConfirmMedicine(textField.getText().toString().trim());
//                            db.updateMedicineInventory(textField.getText().toString().trim());
                            MyTask.this.run();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                    }
                });
                btnNewButton.setBounds(333, 338, 113, 27);
                contentPane.add(btnNewButton);

                info.clear();
                medicalLists.clear();
                setBounds(100, 100, 738, 535);
            }

        }


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0, 10000);
    }



}
