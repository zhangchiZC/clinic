package cs;

/**
 * Created by ZC on 2019/6/29.
 */

import po.Display;
import po.Orders;
import util.DbUtil;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Receptionist extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    JLabel tipLabel;

    DbUtil db = new DbUtil();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Receptionist frame = new Receptionist();
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
    public Receptionist() {
        setTitle(" 前台导诊");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 740, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u5F55\u5165\u9884\u7EA6\u53F7");
        label.setBounds(95, 144, 101, 18);
        contentPane.add(label);

        textField = new JTextField();
        textField.setBounds(245, 141, 187, 24);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("\u751F\u6210\u6302\u53F7\u5355\u5E76\u6392\u961F");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //根据id 也就是预约号查询ordres表里的医生患者名字，诊室，挂号单=日期+id，存入display表
                try {
                    Orders orders = db.findAllByIDfromOrders(Integer.parseInt(textField.getText().toString().trim()));
                    Display display = new Display();
                    display.setCode(orders.getOrderDate().replaceAll("-", "") + orders.getId().toString());
                    display.setDoctor_name(orders.getDoctor_name());
                    display.setPatient_name(orders.getPatient_name());
                    display.setDepartment_name(orders.getDepartment_name());
                    db.addDisply(display);
                    tipLabel.setText("录入成功");


                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("录入失败");
                }catch (NullPointerException e1){
                    e1.printStackTrace();
                    tipLabel.setText("录入失败,不存在此预约号");
                }

            }
        });
        btnNewButton.setBounds(462, 140, 162, 27);
        contentPane.add(btnNewButton);


        tipLabel = new JLabel("");
        tipLabel.setBounds(253, 244, 179, 18);
        contentPane.add(tipLabel);
    }
}

