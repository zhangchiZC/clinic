package cs;

/**
 * Created by ZC on 2019/7/2.
 */
import po.Doctor;
import util.DbUtil;


import java.awt.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Registration extends JFrame {

    private JPanel contentPane;
    private JTextField patientField;
    private JLabel tipLabel;
    DbUtil db = new DbUtil();
    String departmentName;
    int time;
    String dbTime;
    Integer depID;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registration frame = new Registration();
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
    /**
     *
     * @throws SQLException
     */
    public Registration() throws SQLException {
        DateFormat format = DateFormat.getDateInstance();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(format.format(date));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 563);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u79D1\u5BA4");
        label.setBounds(204, 23, 42, 18);
        contentPane.add(label);

        JComboBox depBox = new JComboBox();
        depBox.setModel(new DefaultComboBoxModel(db.findAllDepartment().toArray()));
        depBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (e.getStateChange() == ItemEvent.SELECTED){
                    System.out.println(depBox.getSelectedItem());
                    departmentName = depBox.getSelectedItem().toString().trim();
                    switch (departmentName){
                        case "口腔科":
                            depID = 3;
                            break;
                        case "呼吸科":
                            depID = 2;
                            break;
                        case "消化科":
                            depID = 4;
                            break;
                        case "血液科":
                            depID = 1;
                            break;
                    }
                //}
            }
        });
        depBox.setBounds(260, 20, 93, 24);
        contentPane.add(depBox);

        JLabel label_1 = new JLabel("\u65F6\u95F4");
        label_1.setBounds(408, 23, 49, 18);
        contentPane.add(label_1);

        patientField = new JTextField();
        patientField.setText("请先输入患者名");
        patientField.setBounds(59, 20, 108, 24);
        contentPane.add(patientField);
        patientField.setColumns(10);

        JComboBox timeBox = new JComboBox();
        timeBox.setModel(new DefaultComboBoxModel(new String[] {"\u4E0A\u53488:00-10:00", "\u4E0A\u534810:00-12:00", "\u4E0B\u534813:00-15:00", "\u4E0B\u534815:00-17:00"}));
        timeBox.setBounds(463, 20, 155, 24);
        contentPane.add(timeBox);

        tipLabel = new JLabel("");
        tipLabel.setBounds(234, 485, 233, 18);
        contentPane.add(tipLabel);

        timeBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


//                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println(timeBox.getSelectedItem());
                    time = timeBox.getSelectedIndex();
                    switch (time){
                        case 0:
                            dbTime = "am1";
                            break;
                        case 1:
                            dbTime = "am2";
                            break;
                        case 2:
                            dbTime = "pm1";
                            break;
                        case 3:
                            dbTime = "pm2";
                            break;
                    }
       //         }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                List<Doctor> doctors = null;
                try {
//                    DateFormat format = DateFormat.getDateInstance();
//                    Date date = new Date();
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(date);

                    doctors = db.findAllByDoctor_idJoinWorkday(format.format(date),dbTime,depID);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                for (int i = 0; i<doctors.size(); i++){
                    //一行中的第一个JLabel 放图片
                    JLabel photoLabel = new JLabel();
                    photoLabel.setBounds(27, 78+i*83, 58, 70);
                    int height = photoLabel.getSize().height;
                    int width = photoLabel.getSize().width;
                    ImageIcon img = new ImageIcon("F:\\Clinic\\src\\"+doctors.get(i).getPhotoPath());
//                    System.out.println("F:\\Clinic\\src\\"+doctors.get(i).getPhotoPath());
//                    System.out.println("F:\\Clinic\\src\\image");
                    img.setImage(img.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
                    photoLabel.setIcon(img);
                    contentPane.add(photoLabel);

                    //JLabel 放医生简介
                    JLabel introduction = new JLabel();
                    introduction.setText(doctors.get(i).getName()+"     "+doctors.get(i).getSex()+"     "+doctors.get(i).getDegree()+"     "+doctors.get(i).getIntroduction());
                    introduction.setBounds(99, 104+i*85, 315, 18);
                    contentPane.add(introduction);

                    //放button
//                    DateFormat format = DateFormat.getDateInstance();
//                    Date date = new Date();
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(date);
//                    System.out.println(format.format(date));

                    JButton button = new JButton("预约");
                    button.setName(format.format(date)+","+doctors.get(i).getName()+","+patientField.getText().toString().trim()+","+departmentName+","+dbTime);
                    System.out.println(button.getName().split(",")[0].toString());

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                db.addOrder(button.getName().split(",")[0],button.getName().split(",")[1],button.getName().split(",")[2],button.getName().split(",")[3],button.getName().split(",")[4]);
                                tipLabel.setText("预约成功");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                                tipLabel.setText("预约成功");
                            }
                        }
                    });
                    button.setBounds(470,100+i*85,113,27);
                    contentPane.add(button);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    setBounds(100, 100, 651, 563);
                }


            }
        });

        JLabel returnPay = new JLabel("<html><u>《《缴费清单</u></html>");
        returnPay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Registration.this.dispose();
                new SelfServiceTerminal().setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        returnPay.setBounds(14, 485, 136, 18);
        contentPane.add(returnPay);

//        switch (time){
//            case 0:
//                dbTime = "am1";
//                break;
//            case 1:
//                dbTime = "am2";
//                break;
//            case 2:
//                dbTime = "pm1";
//                break;
//            case 3:
//                dbTime = "pm2";
//                break;
//        }
//        switch (departmentName){
//            case "口腔科":
//                depID = 3;
//                break;
//            case "呼吸科":
//                depID = 2;
//                break;
//            case "消化科":
//                depID = 4;
//                break;
//            case "血液科":
//                depID = 1;
//                break;
//        }
//        patientField = new JTextField();
//        patientField.setBounds(481, 20, 108, 24);
//        contentPane.add(patientField);
//        patientField.setColumns(10);

        JLabel label_4 = new JLabel("\u60A3\u8005");
        label_4.setBounds(27, 23, 30, 18);
        contentPane.add(label_4);




//        List<Doctor> doctors = db.findAllByDoctor_idJoinWorkday("2019-06-30",dbTime,depID);
//        for (int i = 0; i<doctors.size(); i++){
//            //一行中的第一个JLabel 放图片
//            JLabel photoLabel = new JLabel();
//            photoLabel.setBounds(27, 78+i*83, 58, 70);
//            int height = photoLabel.getSize().height;
//            int width = photoLabel.getSize().width;
//            ImageIcon img = new ImageIcon(doctors.get(i).getPhotoPath());
//            img.setImage(img.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
//            photoLabel.setIcon(img);
//            contentPane.add(photoLabel);
//
//            //JLabel 放医生简介
//            JLabel introduction = new JLabel();
//            introduction.setText(doctors.get(i).getName()+"     "+doctors.get(i).getSex()+"     "+doctors.get(i).getDegree()+"     "+doctors.get(i).getIntroduction());
//            introduction.setBounds(99, 104+i*85, 315, 18);
//            contentPane.add(introduction);
//
//            //放button
//            JButton button = new JButton("预约");
//            button.setName("2019-06-30"+","+doctors.get(i).getName()+","+patientField.getText().toString().trim()+","+departmentName+","+dbTime);
//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    try {
//                        db.addOrder(button.getName().split(",")[0],button.getName().split(",")[1],button.getName().split(",")[2],button.getName().split(",")[3],button.getName().split(",")[4]);
//                    } catch (SQLException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            });
//            button.setBounds(470,100+i*85,113,27);
//            contentPane.add(button);
//        }

    }
}

