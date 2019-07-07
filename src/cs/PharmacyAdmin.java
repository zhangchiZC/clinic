package cs;

/**
 * Created by ZC on 2019/7/4.
 */

import po.Medicine;
import util.DbUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PharmacyAdmin extends JFrame {

    private JPanel contentPane;
    JLabel warmLabel;

    DbUtil db = new DbUtil();
    List<Medicine> medicines = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PharmacyAdmin frame = new PharmacyAdmin();
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
    public PharmacyAdmin() {
        class MyTask extends TimerTask {

            @Override
            public void run() {

//            }
//        }
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 580, 432);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);

                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(36, 47, 482, 268);
                contentPane.add(scrollPane);

                try {
                    medicines = db.findAllMedicne();
                    for (int i =0;i<medicines.size();i++) { //库存不足报警  弹对话框
                        if (medicines.get(i).getMedicine_amount() < 10) {
                            //JOptionPane.showMessageDialog(null, medicines.get(i).getMedicine_name()+"库存不足10", "", JOptionPane.INFORMATION_MESSAGE);
//                            JOptionPane jOptionPane = new JOptionPane(medicines.get(i).getMedicine_name()+"库存不足10",JOptionPane.INFORMATION_MESSAGE);
//                            JDialog jDialog = jOptionPane.createDialog("库存不足！！");
//                            jDialog.setVisible(true);
//                            jDialog.setVisible(false);
//                            jDialog.dispose();
                            //continue;
//                            warmLabel = new JLabel("");
//                            warmLabel.setBounds(46, 323, 472, 18);
//                            contentPane.add(warmLabel);
//                            warmLabel.setText(medicines.get(i).getMedicine_name()+"库存不足10");
//                            Thread.sleep(2000);

                            Toolkit toolkit = Toolkit.getDefaultToolkit();
                            toolkit.beep();
                            toolkit.beep();




                        }

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                JList list = new JList(medicines.toArray());
                scrollPane.setViewportView(list);

                JLabel applyLabel = new JLabel("<html><u>《《申请采购</u></html>");
                applyLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        PharmacyAdmin.this.dispose();
                        try {
                            new ApplyMedicine().setVisible(true);
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
                applyLabel.setBounds(31, 354, 90, 18);
                contentPane.add(applyLabel);

                JLabel storeLabel = new JLabel("<html><u>入库登记》》</u></html>");
                storeLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        PharmacyAdmin.this.dispose();
                        new InputRecord().setVisible(true);
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
                storeLabel.setBounds(465, 354, 90, 18);
                contentPane.add(storeLabel);

                JLabel headLabel = new JLabel("New label");
                headLabel.setBounds(36, 28, 482, 18);
                contentPane.add(headLabel);

//                warmLabel = new JLabel("");
//                warmLabel.setBounds(46, 323, 472, 18);
//                contentPane.add(warmLabel);

                setBounds(100, 100, 581, 432);

            }
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0, 10000);

    }
}
