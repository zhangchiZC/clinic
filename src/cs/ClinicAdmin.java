package cs;

/**
 * Created by ZC on 2019/7/5.
 */

import java.awt.EventQueue;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import chart.StatisticChart;
import util.DbUtil;
import util.MyJcheckBox;

public class ClinicAdmin extends JFrame {

    private JPanel contentPane;
    private JList list;
    private JButton selectAllButton;
    DbUtil db = new DbUtil();
    List<po.ApplyMedicine> listData = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClinicAdmin frame = new ClinicAdmin();
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
    public ClinicAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(596, 478);
        setTitle("诊所管理员");
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(83, 43, 423, 302);
        contentPane.add(scrollPane);

        list = new JList();
        list.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);

                }
            }
        });
//        list.addListSelectionListener(new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                System.out.println(list.getSelectedValuesList());
//
//            }
//        });

        scrollPane.setViewportView(list);

        selectAllButton = new JButton("全选/反选");
        selectAllButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(list.getSelectedValuesList().size());

                for (int i = 0; i < listData.size(); i++) {
                    list.setSelectedIndex(i);
                }

            }
        });
        selectAllButton.setBounds(83, 358, 113, 27);
        contentPane.add(selectAllButton);

        JButton approveButton = new JButton("批准");
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(list.getSelectedValuesList().toString().split("\\[")[1].split("  ")[0]);
//                System.out.println(list.getSelectedValuesList().toString().split("\\[")[1].split(",")[0].split("  ")[0]);
//                System.out.println(list.getSelectedValuesList().toString());

                List<Integer> info = new ArrayList<Integer>();
                for (int i = 0; i < list.getSelectedValuesList().size(); i++) {
                    info.add(Integer.parseInt(list.getSelectedValuesList().toString().split("\\[")[1].split(",")[i].split("  ")[0].trim()));
                }
                for (Integer k : info) {
                    System.out.println(k);
                    try {
                        db.updateApproveStatueByID(k);
                        Thread.sleep(100);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                setList();
            }
        });
        approveButton.setBounds(393, 358, 113, 27);
        contentPane.add(approveButton);

        JLabel topLabel = new JLabel("药品ID      药名       数量       批准状态");
        topLabel.setBounds(83, 23, 423, 18);
        contentPane.add(topLabel);

        JButton refreshButton = new JButton("刷新");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setList();
            }
        });
        refreshButton.setBounds(241, 358, 113, 27);
        contentPane.add(refreshButton);


        JLabel chartLabel = new JLabel("<html><u>查看统计信息》》</u></html>");
        chartLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClinicAdmin.this.dispose();
                new StatisticChart().setVisible(true);
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
        chartLabel.setBounds(438, 400, 126, 18);
        contentPane.add(chartLabel);

        setList();
    }

    protected void setList() {
//        List<po.ApplyMedicine> listData= new ArrayList<>();
        try {
            listData = db.findAllApply();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list.setListData(listData.toArray());
        MyJcheckBox cell = new MyJcheckBox();
        list.setCellRenderer(cell);
    }

}

