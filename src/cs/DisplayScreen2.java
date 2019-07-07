package cs;


import po.Display2;
import util.DbUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by ZC on 2019/6/30.
 */
public class DisplayScreen2 extends JFrame {

    private JPanel contentPane;
    DbUtil db = new DbUtil();


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayScreen2 frame = new DisplayScreen2();
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
     * @throws InterruptedException
     */
    public DisplayScreen2() throws SQLException, InterruptedException {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 633, 516);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);


//        JPanel panel = new JPanel();
//        panel.setBounds(49, 77, 539, 352);
//        contentPane.add(panel);
//        panel.setLayout(new BorderLayout(0, 0));

        class MyTask extends TimerTask {
            @Override
            public void run() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 633, 516);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);
                JPanel panel = new JPanel();
                panel.setBounds(49, 77, 539, 352);
                contentPane.add(panel);
                panel.setLayout(new BorderLayout(0, 0));

                // 表头（列名）
                Object[] columnNames = {"医生", "患者", "科室", "排队人数"};

                // 表格所有行数据
                List<Display2> display2s = new ArrayList<>();
                try {
                    display2s = db.createViewAndFindDisplayListByDepatmentName("口腔科");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Object[][] data = new Object[display2s.size()][4];
                for (int i = 0; i < display2s.size(); i++) {
                    Display2 display2 = (Display2) display2s.get(i);
                    data[i][0] = display2.getDoctor_name();
                    data[i][1] = display2.getPatient_names().replaceAll("&", ",");
                    data[i][2] = display2.getDepartment_name();
                    data[i][3] = display2.getPatientAmount();
                    //System.out.println(data[i][0]);

                }

                // 创建一个表格，指定 所有行数据 和 表头
                JTable table = new JTable(data, columnNames);

                table.setEnabled(false);
                table.setRowHeight(50);


                // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
                panel.add(table.getTableHeader(), BorderLayout.NORTH);
                // 把 表格内容 添加到容器中心
                panel.add(table, BorderLayout.CENTER);
                setBounds(100, 100, 634, 516);//解决困扰多天问题的办法(*^▽^*)，伪刷新


            }
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0, 3000);
        Thread.sleep(500);
    }
}
