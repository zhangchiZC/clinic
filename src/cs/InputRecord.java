package cs;

/**
 * Created by ZC on 2019/7/5.
 */
import util.DbUtil;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InputRecord extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JLabel tipLabel;
    JComboBox comboBox;

    DbUtil db = new DbUtil();
    String medicineName;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InputRecord frame = new InputRecord();
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
    public InputRecord() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 403, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u836F\u540D");
        label.setBounds(54, 69, 41, 18);
        contentPane.add(label);


        try {
            comboBox = new JComboBox(new DefaultComboBoxModel(db.findAllMedicineName().toArray()));
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    medicineName = comboBox.getSelectedItem().toString().trim();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBox.setBounds(106, 66, 122, 24);
        contentPane.add(comboBox);

        JLabel label_1 = new JLabel("\u6570\u91CF");
        label_1.setBounds(54, 124, 41, 18);
        contentPane.add(label_1);

        textField = new JTextField();
        textField.setBounds(106, 121, 122, 24);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton inputButton = new JButton("入库");
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    db.updateMedicineByPharmacyAdmin(medicineName,Integer.parseInt(textField.getText().trim()));
                    tipLabel.setText("入库成功");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("入库失败");
                }

            }
        });
        inputButton.setBounds(54, 190, 113, 27);
        contentPane.add(inputButton);

        tipLabel = new JLabel();
        tipLabel.setBounds(54, 277, 280, 18);
        contentPane.add(tipLabel);

        JLabel lblNewLabel = new JLabel("<html><u>《《返回</u></html>");
        lblNewLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InputRecord.this.dispose();
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
        lblNewLabel.setBounds(23, 385, 103, 18);
        contentPane.add(lblNewLabel);

    }
}
