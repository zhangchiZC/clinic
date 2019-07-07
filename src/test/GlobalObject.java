package test;

import javax.swing.*;

/**
 * Created by ZC on 2019/7/1.
 */
public class GlobalObject {

    /**
     * 全局jScrollPane
     */
    public static JScrollPane jScrollPane;
    public static JScrollPane getjScrollPane() {
        return jScrollPane;
    }
    public static void setjScrollPane(JScrollPane jScrollPane) {
        GlobalObject.jScrollPane = jScrollPane;
    }

    /**
     * 全局JTable
     */
    public static JTable jTable;
    public static JTable getjTable() {
        return jTable;
    }
    public static void setjTable(JTable jTable) {
        GlobalObject.jTable = jTable;
    }
}
