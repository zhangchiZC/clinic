package cs;
/**
 * Created by ZC on 2019/6/27.
 */

import po.Department;
import po.Doctor;
import po.Medicine;
import util.DbUtil;
import util.PoiUtil;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class Admin extends JFrame {
    JFileChooser chooser = new JFileChooser();
    String path;
    DbUtil db = new DbUtil();


    private JPanel contentPane;
    private JTextField pathField;
    JLabel tipLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin frame = new Admin();
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
    public Admin() {
        setTitle("\u7BA1\u7406\u5458");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 691, 599);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 673, 552);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton excelBtn = new JButton("\u9009\u62E9excel");
        excelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    path = chooser.getSelectedFile().getPath();
                    System.out.println(path);
                    pathField.setText(path);
                }
            }
        });
        excelBtn.setBounds(111, 85, 113, 27);
        panel.add(excelBtn);

        pathField = new JTextField();
        pathField.setBounds(238, 86, 408, 24);
        panel.add(pathField);
        pathField.setColumns(10);

        JButton uploadToStaff = new JButton("\u5BFC\u5165\u804C\u5DE5\u5E93");
        uploadToStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    db.truncateDoctor();
                    DbUtil db = new DbUtil();
                    for (Doctor doctor : PoiUtil.paresDoctor(path)) {
                        db.addStaff(doctor);
                    }
                    tipLabel.setText("成功导入员工信息");

                } catch (IOException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                }


            }
        });
        uploadToStaff.setBounds(111, 177, 113, 27);
        panel.add(uploadToStaff);

        JButton uploadToMedicine = new JButton("\u5BFC\u5165\u836F\u54C1\u5E93");
        uploadToMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.truncateMedicine();
                    for (Medicine medicine : PoiUtil.paresMedicine(path)) {
                        db.addMedicine(medicine);
                        tipLabel.setText("成功导入药品信息");

                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                }
            }
        });
        uploadToMedicine.setBounds(271, 177, 113, 27);
        panel.add(uploadToMedicine);

        JButton uploadToDepartment = new JButton("\u5BFC\u5165\u79D1\u5BA4\u5E93");
        uploadToDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.truncateDepartment();
                    for (Department department : PoiUtil.paresDepartment(path)) {
                        db.addDepartment(department);
                        tipLabel.setText("成功导入部门信息");

                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                }

            }
        });
        uploadToDepartment.setBounds(440, 177, 113, 27);
        panel.add(uploadToDepartment);

        tipLabel = new JLabel("");
        tipLabel.setBounds(238, 270, 171, 33);
        panel.add(tipLabel);

        JLabel returnToLogin = new JLabel("<html><u>\u300A\u300A\u8FD4\u56DE\u767B\u5F55\u9875</u><html>");
        returnToLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Admin.this.dispose();
                new Login().setVisible(true);
            }
        });
        returnToLogin.setBounds(27, 13, 135, 18);
        panel.add(returnToLogin);
    }

}
