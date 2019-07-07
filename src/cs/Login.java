package cs;

import sun.rmi.runtime.Log;
import util.DbUtil;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField staffID;
    private JPasswordField password;
    JLabel tipLabel;

    DbUtil db = new DbUtil();

    /**
     * Launch the application.
     */
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public Login() {
        setTitle("\u5458\u5DE5\u767B\u5F55");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 709, 540);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u8EAB\u4EFD");
        label.setBounds(114, 95, 48, 18);
        contentPane.add(label);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"\u7BA1\u7406\u5458", "\u79D1\u5BA4\u7BA1\u7406\u5458", "\u524D\u53F0\u5BFC\u8BCA", "\u533B\u751F", "\u836F\u623F\u533B\u751F", "\u836F\u623F\u7BA1\u7406\u5458", "\u8F93\u6DB2\u5BA4\u533B\u5E08", "\u8BCA\u6240\u7BA1\u7406\u4EBA\u5458"}));
        comboBox.setBounds(214, 92, 146, 24);
        contentPane.add(comboBox);

        JLabel label_1 = new JLabel("\u5DE5\u53F7");
        label_1.setBounds(114, 141, 48, 18);
        contentPane.add(label_1);

        staffID = new JTextField();
        staffID.setBounds(214, 138, 146, 24);
        contentPane.add(staffID);
        staffID.setColumns(10);

        JLabel label_2 = new JLabel("\u5BC6\u7801");
        label_2.setBounds(114, 188, 72, 18);
        contentPane.add(label_2);

        password = new JPasswordField();
        password.setBounds(214, 185, 146, 24);
        contentPane.add(password);
        password.setColumns(10);

        JButton loginBtn = new JButton("\u767B\u5F55");
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBox.getSelectedItem().toString());
                Integer index = comboBox.getSelectedIndex();
                Integer id = Integer.parseInt(staffID.getText().toString().trim());
                String pwd = password.getText().toString().trim();
                try {
                    if (comboBox.getSelectedItem().toString().equals("管理员") && db.isExistsOfStaff(index, id, pwd)) {
                        System.out.println(index+"  "+id+"  "+pwd);
                        Login.this.dispose();
                        new Admin().setVisible(true);
                    } else if (comboBox.getSelectedItem().toString().equals("科室管理员") && db.isExistsOfStaff(index, id, pwd)) {
                        System.out.println(index+"  "+id+"  "+pwd);
                        Login.this.dispose();
                        new DepartmentAdmin().setVisible(true);

                    } else if (comboBox.getSelectedItem().toString().equals("前台导诊") && db.isExistsOfStaff(index, id, pwd)) {
                        Login.this.dispose();
                        new Receptionist().setVisible(true);

                    } else if (comboBox.getSelectedItem().toString().equals("医生") && db.isExistsOfStaff(index, id, pwd)) {
                        Login.this.dispose();
                        new DoctorSystem(id).setVisible(true);
                    } else if (comboBox.getSelectedItem().toString().equals("药房医生") && db.isExistsOfStaff(index, id, pwd)) {
                        Login.this.dispose();
                        new PharmacyDoctor().setVisible(true);

                    } else if (comboBox.getSelectedItem().toString().equals("药房管理员") && db.isExistsOfStaff(index, id, pwd)) {
                        Login.this.dispose();
                        new PharmacyAdmin().setVisible(true);

                    } else if (comboBox.getSelectedItem().toString().equals("输液室医生") && db.isExistsOfStaff(index, id, pwd)) {

                    } else if (comboBox.getSelectedItem().toString().equals("诊所管理人员") && db.isExistsOfStaff(index, id, pwd)) {
                        Login.this.dispose();
                        new ClinicAdmin().setVisible(true);

                    } else {
                        tipLabel.setText("账号或密码错误");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("账号或密码错误");
                }catch (NumberFormatException e1){
                    e1.printStackTrace();
                    tipLabel.setText("账号或密码错误");
                }
            }
        });
        loginBtn.setBounds(115, 249, 113, 27);
        contentPane.add(loginBtn);

        JButton resetBtn = new JButton("\u91CD\u7F6E");
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffID.setText("");
                password.setText("");
            }
        });
        resetBtn.setBounds(274, 249, 113, 27);
        contentPane.add(resetBtn);

        tipLabel = new JLabel("");
        tipLabel.setBounds(186, 335, 201, 18);
        contentPane.add(tipLabel);
    }
}
