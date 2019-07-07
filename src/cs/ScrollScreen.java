package cs;

/**
 * Created by ZC on 2019/7/3.
 */
import po.MedicalList;
import util.DbUtil;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;

public class ScrollScreen extends JFrame {

    private JPanel contentPane;
    JList list;
    DbUtil db = new DbUtil();
    List<MedicalList> medicalLists = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScrollScreen frame = new ScrollScreen();
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
    public ScrollScreen() {
        class MyTask extends TimerTask{
            @Override
            public void run() {
//                arrayList.add("q");
//                arrayList.add("b");
//                arrayList.add("v");
//                arrayList.add("m");
                try {
                    medicalLists = db.findPatientsWhoPayed();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                for (int i=0;i<medicalLists.size();i++){
                    setTitle("取药滚动屏");
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setBounds(900, 100, 729, 110);//509最后一个参数
                    contentPane = new JPanel();
                    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                    setContentPane(contentPane);
                    contentPane.setLayout(null);

                    MedicalList first = medicalLists.get(0);

                    medicalLists.remove(0);
                    medicalLists.add(medicalLists.size(), first);
                    //System.out.println(medicalLists);
                    list = new JList(medicalLists.toArray());

                    list.setBounds(0, 26, 710, 436);
                    contentPane.add(list);
                    //setBounds(100, 100, 722, 509);


                    JLabel lblNewLabel = new JLabel("挂号单号             姓名");
                    lblNewLabel.setBounds(0, 0, 711, 24);
                    contentPane.add(lblNewLabel);
                    setBounds(900, 100, 728, 110);//509最后一个参数
                    //System.out.println(first);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                arrayList.clear();
            }
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0, 3000);
    }
}

