package cs;

/**
 * Created by ZC on 2019/7/4.
 */

import util.DbUtil;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ApplyMedicine extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    JComboBox comboBox;
    JLabel tipLabel;

    String medicineName;

    DbUtil db = new DbUtil();
    List<po.ApplyMedicine> applyMedicines = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApplyMedicine frame = new ApplyMedicine();
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
    public ApplyMedicine() throws SQLException {
        setTitle("采购申请");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 723, 475);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u836F\u54C1\u540D");
        label.setBounds(25, 36, 55, 18);
        contentPane.add(label);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(db.findAllMedicineName().toArray()));
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicineName = comboBox.getSelectedItem().toString().trim();
                System.out.println(medicineName);
            }
        });
        comboBox.setBounds(79, 33, 124, 24);
        contentPane.add(comboBox);

        JLabel label_1 = new JLabel("\u6570\u91CF");
        label_1.setBounds(25, 81, 43, 18);
        contentPane.add(label_1);

        textField = new JTextField();
        textField.setBounds(79, 78, 124, 24);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton button = new JButton("\u63D0\u4EA4\u7533\u8BF7");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.applyMedicine(medicineName, textField.getText().toString().trim());
                    tipLabel.setText("已提交申请");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("提交失败");
                }


            }
        });
        button.setBounds(252, 55, 113, 27);
        contentPane.add(button);

        JLabel lblNewLabel = new JLabel("<html><u>《《返回</u></html>");
        lblNewLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ApplyMedicine.this.dispose();
                new PharmacyAdmin().setVisible(true);
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
        lblNewLabel.setBounds(25, 229, 72, 18);
        contentPane.add(lblNewLabel);

        tipLabel = new JLabel("");
        tipLabel.setBounds(131, 146, 72, 18);
        contentPane.add(tipLabel);

//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setBounds(213, 192, 455, 190);
//        contentPane.add(scrollPane);
//
//        JList list = new JList(applyMedicines.toArray());
//        scrollPane.setViewportView(list);

        JButton refreshButton = new JButton("刷新列表");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    applyMedicines = db.findAllApply();
                    JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setBounds(213, 192, 455, 190);
                    contentPane.add(scrollPane);

                    JList list = new JList(applyMedicines.toArray());
                    scrollPane.setViewportView(list);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        refreshButton.setBounds(533, 152, 113, 27);
        contentPane.add(refreshButton);
    }
}

