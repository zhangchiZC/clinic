package chart; /**
 * Created by ZC on 2019/7/5.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import po.MedicalList;
import util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class PieChart {
    ChartPanel frame1;
    static DbUtil db = new DbUtil();

    public PieChart() {
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D("药品销售量", data, true, false, false);
        //设置百分比
        PiePlot pieplot = (PiePlot) chart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
        NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
        pieplot.setLabelGenerator(sp1);//设置饼图显示百分比

        //没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setIgnoreNullValues(true);//设置不显示空值
        pieplot.setIgnoreZeroValues(true);//设置不显示负值
        frame1 = new ChartPanel(chart, true);
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        PiePlot piePlot = (PiePlot) chart.getPlot();//获取图表区域对象
        piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));//解决乱码
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
    }

    private static DefaultPieDataset getDataSet() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            java.util.List<MedicalList> medicalLists = db.pieChartSaleVolume();
            for (int i = 0; i < medicalLists.size(); i++) {
                String medicineName = medicalLists.get(i).getMedicine_name();
                Integer total = 0;
                for (int j = 0; j < medicalLists.get(i).getMedicine_amount().split(",").length; j++) {

                    total = Integer.parseInt(medicalLists.get(0).getMedicine_amount().split(",")[j]);
                }
                dataset.setValue(medicineName, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    public ChartPanel getChartPanel() {
        return frame1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("数据统计图");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(new PieChart().getChartPanel());
        frame.setBounds(50, 50, 600, 600);
        frame.setVisible(true);
    }
}