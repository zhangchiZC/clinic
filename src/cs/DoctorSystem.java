package cs;

/**
 * Created by ZC on 2019/7/1.
 */

import po.Display2;
import po.Orders;
import util.CallUtil;
import util.DbUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DoctorSystem extends JFrame {

    private JPanel contentPane;
    private JTextField patientName;
    private JTextField codeField;
    private JTextField amountField;
    private JComboBox medicineName;
    private JComboBox cureMethod;
    private JLabel tipLabel;

    private Integer doctor_id;
    DbUtil db = new DbUtil();
    int count = 0;

    String firstPatientName;


    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    DoctorSystem frame = new DoctorSystem();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    /**
     *
     * @param id
     * @throws SQLException
     */
    public DoctorSystem(Integer id) throws SQLException {

        Integer doctor_id = id;
        String doctorName = db.findDoctorNameByID(doctor_id);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 726, 547);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u5F53\u524D\u60A3\u8005\u53CA\u6392\u961F\u4EBA\u6570");
        label.setBounds(14, 25, 264, 18);
        contentPane.add(label);

        JLabel information = new JLabel("");
        information.setBounds(14, 72, 520, 114);
        contentPane.add(information);

        JButton callBtn = new JButton("\u53EB\u53F7");
        callBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DbUtil db = new DbUtil();
//                    String doctorName = db.findDoctorNameByID(doctor_id);
                    Display2 display2 = db.listForDoctorSystem(doctorName);
                    System.out.println(display2);
                    information.setText("<html>" + display2.getPatient_names() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + display2.getPatientAmount() + "</html>");
                    CallUtil.call(display2.getPatient_names().split(",")[0]);
                    count++;
                    firstPatientName = display2.getPatient_names().split(",")[0];

                    if (count == 3) {
                        Orders orders = db.findOrdersIDByPatientNameAndDoctorName(firstPatientName, doctorName);
                        db.deletePatientFromOrders(firstPatientName, doctorName);
                        Thread.sleep(200);
                        //db.addOrder(null,doctorName,display2.getPatient_names().split(",")[0],null,null);

                        System.out.println(orders + "hahahahhaahhah");
                        Thread.sleep(200);
                        db.addOrder(orders.getOrderDate(), orders.getDoctor_name(), orders.getPatient_name(), orders.getDepartment_name(), orders.getOrderTime());
                        count = 0;

                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        callBtn.setBounds(236, 21, 113, 27);
        contentPane.add(callBtn);

        JLabel label_2 = new JLabel("\u5F55\u5165\u75C5\u4F8B");
        label_2.setBounds(14, 248, 72, 18);
        contentPane.add(label_2);

        medicineName = new JComboBox();
        medicineName.setModel(new DefaultComboBoxModel(db.findAllMedicineName().toArray()));
        medicineName.setBounds(91, 302, 118, 24);
        contentPane.add(medicineName);

        JLabel label_3 = new JLabel("\u836F\u54C1\u540D");
        label_3.setBounds(14, 305, 72, 18);
        contentPane.add(label_3);

        JLabel label_4 = new JLabel("\u6570\u91CF");
        label_4.setBounds(14, 336, 72, 18);
        contentPane.add(label_4);

        JLabel label_5 = new JLabel("\u6CBB\u7597");
        label_5.setBounds(14, 367, 72, 18);
        contentPane.add(label_5);

        cureMethod = new JComboBox();
        cureMethod.setModel(new DefaultComboBoxModel(new String[]{"输液","注射","吃药"}));
        cureMethod.setBounds(91, 364, 118, 24);
        contentPane.add(cureMethod);

        JButton logMedicalHistoryBtn = new JButton("\u5F55\u5165\u75C5\u4F8B");
        logMedicalHistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbUtil db = new DbUtil();
                String medicine_name = medicineName.getSelectedItem().toString();
                String cure_type = cureMethod.getSelectedItem().toString();
                Integer medicine_amount = Integer.parseInt(amountField.getText().toString());
                String patient_name = patientName.getText().toString().trim();
                String code = codeField.getText().toString().trim();
                try {
                    //String doctorName = db.findDoctorNameByID(doctor_id);
                    db.addToMedicalHistory(doctorName,patient_name,medicine_name,medicine_amount,cure_type,code);
                    db.deletePatientFromOrders(firstPatientName, doctorName);
                    tipLabel.setText("成功录入病历");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        logMedicalHistoryBtn.setBounds(153, 244, 113, 27);
        contentPane.add(logMedicalHistoryBtn);

        JLabel doctorNameLabel = new JLabel("");
        doctorNameLabel.setText("医生: " + db.findDoctorNameByID(doctor_id));
        doctorNameLabel.setBounds(541, 25, 72, 18);
        contentPane.add(doctorNameLabel);

        JLabel label_1 = new JLabel("\u60A3\u8005\u540D");
        label_1.setBounds(328, 305, 72, 18);
        contentPane.add(label_1);

        patientName = new JTextField();
        patientName.setBounds(388, 302, 86, 24);
        contentPane.add(patientName);
        patientName.setColumns(10);

        JLabel label_6 = new JLabel("\u6302\u53F7\u5355");
        label_6.setBounds(328, 367, 72, 18);
        contentPane.add(label_6);

        codeField = new JTextField();
        codeField.setBounds(388, 364, 86, 24);
        contentPane.add(codeField);
        codeField.setColumns(10);

        amountField = new JTextField();
        amountField.setBounds(91, 333, 118, 24);
        contentPane.add(amountField);
        amountField.setColumns(10);

        tipLabel = new JLabel("");
        tipLabel.setBounds(197, 435, 216, 18);
        contentPane.add(tipLabel);

    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//                    Class clazz = Class.forName("DoctorSystem");
//                    DoctorSystem frame = new DoctorSystem(Integer.parseInt(String.valueOf(clazz.getTypeParameters()[0])));
//                    System.out.println(frame.doctor_id+"zzzzzzzzzzzzzzzz");
        DoctorSystem frame = new DoctorSystem(1);
        frame.setVisible(true);
    }

}
