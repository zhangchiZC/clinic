package test;

import cs.DisplayScreen2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ZC on 2019/7/1.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayScreen2 frame = new DisplayScreen2();
                    frame.setVisible(true);
                    JTableOperation jTableInit = new JTableOperation();
                    JTable jTable = jTableInit.JtableDataInit();
                    JScrollPane scrollPane=new JScrollPane(jTable);
                    scrollPane.setPreferredSize(new Dimension(535, 540));
//增加监听 这里我重写了监听方法 如果只显示与刷新可不比添加监听
                    //jTable.addMouseListener(myActionListener);
//将scrollPane与jTable 添加到全局变量中，这里非常重要，下面介绍
                    GlobalObject.setjScrollPane(scrollPane);
                    GlobalObject.setjTable(jTable);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
