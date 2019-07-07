package test;

import po.Display2;
import util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZC on 2019/7/1.
 */
public class JTableOperation {
    public JTable JtableDataInit() throws SQLException {
        DbUtil db = new DbUtil();
        List<Display2> display2s = db.createViewAndFindDisplayListByDepatmentName("口腔科");

        Object[][] data = new Object[display2s.size()][4];

        for (int i = 0; i < display2s.size(); i++) {
            Display2 display2 = (Display2) display2s.get(i);
            data[i][0] = display2.getDoctor_name();
            data[i][1] = display2.getPatient_names().replaceAll("&", ",");
            data[i][2] = display2.getDepartment_name();
            data[i][3] = display2.getPatientAmount();
            //System.out.println(data[i][0]);

        }
        Object[] columnNames = {"医生", "患者", "科室", "排队人数"};
        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Dialog", 1, 12));
        return table;
    }

    public void reloadJTable(JTable jtable) throws SQLException {

        DbUtil db = new DbUtil();
        List<Display2> display2s = db.createViewAndFindDisplayListByDepatmentName("口腔科");

        Object[] columnNames = {"医生", "患者", "科室", "排队人数"};

        Object[][] data = new Object[display2s.size()][4];
        //遍历List
        for (int i = 0; i < display2s.size(); i++) {
            Display2 display2 = (Display2) display2s.get(i);
            data[i][0] = display2.getDoctor_name();
            data[i][1] = display2.getPatient_names().replaceAll("&", ",");
            data[i][2] = display2.getDepartment_name();
            data[i][3] = display2.getPatientAmount();
            //System.out.println(data[i][0]);

        }

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Dialog", 1, 12));
        //增加监听
//        MyActionListener myActionListener = new MyActionListener();
//        table.addMouseListener(myActionListener);

        GlobalObject.setjTable(table);
        GlobalObject.getjScrollPane().setViewportView(table);

    }


}
