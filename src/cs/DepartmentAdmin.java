package cs;

/**
 * Created by ZC on 2019/6/29.
 */

import po.Workday;
import util.PoiUtil;
import util.DbUtil;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentAdmin extends JFrame {

    private JPanel contentPane;
    private JTextField pathField;
    JLabel tipLabel;
    DbUtil db = new DbUtil();

    JFileChooser chooser = new JFileChooser();
    String path;
    ArrayList<String> workdayList = new ArrayList<String>();
    JList jList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DepartmentAdmin frame = new DepartmentAdmin();
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
    public DepartmentAdmin() throws SQLException {
        setTitle("就诊安排");
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

//        String b = "aa";
//        String[] a = {"a", "b", b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b, b};

        JButton refreshBtn = new JButton("\u5237\u65B0\u5C31\u8BCA\u5B89\u6392");
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    for (Workday workday : db.findAllWorkday()) {
                        System.out.println(workday);
                        workdayList.add(workday.toString());



                    }
                    jList = new JList(workdayList.toArray());
                    scrollPane.setViewportView(jList);
                    panel.add(scrollPane);
                    workdayList.clear();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });
        refreshBtn.setBounds(34, 304, 152, 27);
        panel.add(refreshBtn);







//        scrollPane.setViewportView(jList);
//        panel.add(scrollPane);

        JButton btnexcel = new JButton("\u4ECEExcel\u5BFC\u5165\u5C31\u8BCA\u5B89\u6392");
        btnexcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = chooser.showOpenDialog(null);
                //System.out.println(jList.getSelectedValue().toString());
                if (result == JFileChooser.APPROVE_OPTION) {
                    path = chooser.getSelectedFile().getPath();
                    System.out.println(path);
                    pathField.setText(path);
                }
                try {
                    db.truncateWorkday();
                    for (Workday workday : PoiUtil.paresWorkday(path)) {
                        db.addWorkday(workday);
                    }
                    tipLabel.setText("成功导入就诊安排");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    tipLabel.setText("导入失败");
                }

            }
        });
        btnexcel.setBounds(34, 358, 222, 27);
        panel.add(btnexcel);

        pathField = new JTextField();
        pathField.setBounds(270, 359, 336, 24);
        panel.add(pathField);
        pathField.setColumns(10);

//        JButton refreshBtn = new JButton("\u5237\u65B0\u5C31\u8BCA\u5B89\u6392");
//        refreshBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//
//                    for (Workday workday : db.findAllWorkday()) {
//                        System.out.println(workday);
//                        workdayList.add(workday.toString());
//
//
//
//                    }
//
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//
//
//            }
//        });
//        refreshBtn.setBounds(34, 304, 152, 27);
//        panel.add(refreshBtn);

        tipLabel = new JLabel("");
        tipLabel.setBounds(210, 418, 214, 18);
        panel.add(tipLabel);

        JLabel lblNewLabel = new JLabel("     \u5DE5\u53F7       \u90E8\u95E8\u53F7           \u65F6\u95F4                     \u65E5\u671F      ");
        lblNewLabel.setBounds(34, 13, 572, 18);
        panel.add(lblNewLabel);

    }
}
