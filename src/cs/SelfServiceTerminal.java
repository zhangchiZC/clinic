package cs;

/**
 * Created by ZC on 2019/7/2.
 */

import po.MedicalList;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelfServiceTerminal extends JFrame {

    private JPanel contentPane;
    private JTextField codeField;
    JLabel priceLabel;
    JButton payButton;

    DbUtil db = new DbUtil();
    List<String> list = new ArrayList<String>();
    Integer totalPrice = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SelfServiceTerminal frame = new SelfServiceTerminal();
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
    public SelfServiceTerminal() {
        setTitle("大厅自助机");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 653, 518);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 635, 471);
        contentPane.add(panel);
        panel.setLayout(null);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 32, 572, 259);
        panel.add(scrollPane);


        String[] a = {"a", "b"};
        ArrayList<String> xList = new ArrayList<String>();
        xList.add("x,y");
        xList.add("y");

//        JList jList = new JList(list.toArray());
//
//        scrollPane.setViewportView(jList);
//        panel.add(scrollPane);

        JLabel lblNewLabel = new JLabel(" 药品名     数量      价格      ");
        lblNewLabel.setBounds(34, 13, 572, 18);
        panel.add(lblNewLabel);

        JLabel label = new JLabel("\u6302\u53F7\u5355\u53F7");
        label.setBounds(34, 349, 72, 18);
        panel.add(label);

        codeField = new JTextField();
        codeField.setBounds(120, 346, 154, 24);
        panel.add(codeField);
        codeField.setColumns(10);

        JButton button = new JButton("\u663E\u793A\u7F34\u8D39\u6E05\u5355");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    MedicalList medicalList = db.createViewAndFindMedicineNameAndAmount(codeField.getText().toString().trim());
                    for (int n = 0; n < medicalList.getMedicine_name().split(",").length; n++) {
                        list.add(medicalList.getMedicine_name().split(",")[n] + "        " + medicalList.getMedicine_amount().split(",")[n] + "        " + String.valueOf(db.findMedicinePriceByMedicineName(medicalList.getMedicine_name().split(",")[n]) * Integer.parseInt(medicalList.getMedicine_amount().split(",")[n])));
                        //  System.out.println(list);
                        totalPrice += db.findMedicinePriceByMedicineName(medicalList.getMedicine_name().split(",")[n]) * Integer.parseInt(medicalList.getMedicine_amount().split(",")[n]);
                        priceLabel.setText("总价为: " + totalPrice + " 元");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                JList jList = new JList(list.toArray());

                scrollPane.setViewportView(jList);
                panel.add(scrollPane);
                payButton.setVisible(true);

            }
        });
        button.setBounds(313, 345, 123, 27);
        panel.add(button);

        priceLabel = new JLabel();
        priceLabel.setBounds(34, 304, 154, 18);
        panel.add(priceLabel);

        payButton = new JButton("缴费");
        payButton.setVisible(false);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "缴费成功", "", JOptionPane.INFORMATION_MESSAGE);
                try {
                    db.updateMedicalHistoryPayStatue(codeField.getText().toString().trim());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        payButton.setBounds(313, 300, 113, 27);
        panel.add(payButton);

        JLabel returnRegistration = new JLabel("<html><u>《《今日挂号</u></html");
        returnRegistration.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SelfServiceTerminal.this.dispose();
                try {
                    new Registration().setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        returnRegistration.setBounds(34, 403, 80, 18);
        panel.add(returnRegistration);

    }
}

