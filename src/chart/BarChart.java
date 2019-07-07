package chart; /**
 * Created by ZC on 2019/7/5.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import po.Orders;
import util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BarChart {
    ChartPanel frame1;
    static DbUtil db = new DbUtil();

    public BarChart() {
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "各时期患者数", // 图表标题
                "日期", // 目录轴的显示标签
                "数量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );

        //从这里开始
        CategoryPlot plot = chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));  //垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        frame1 = new ChartPanel(chart, true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }

private static CategoryDataset getDataSet() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    java.util.List<Orders> ordersList = null;
    try {
        ordersList = db.chartOrderDateAndPatientAmount();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    for(int i=0;i<ordersList.size();i++) {
        Integer year = Integer.parseInt(ordersList.get(i).getOrderDate().split("-")[0]);
        Integer month = Integer.parseInt(String.valueOf(ordersList.get(0).getOrderDate().split("-")[1].charAt(1)));
        Integer day = Integer.parseInt(ordersList.get(i).getOrderDate().split("-")[2]);
        Integer patient_amount = ordersList.get(i).getPatient_amount();
        dataset.addValue(patient_amount,ordersList.get(i).getOrderDate().toString(),ordersList.get(i).getOrderDate().toString());
    }
    return dataset;
}

    public ChartPanel getChartPanel() {
        return frame1;

    }

    public static void main(String[] args) {
        JFrame frame=new JFrame("数据统计图");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2,2,10,10));
        frame.add(jPanel);

        jPanel.add(new BarChart().getChartPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,2,10,10));
//        frame.add(new BarChart().getChartPanel());           //添加柱形图
//        frame.add(new BarChart1().getChartPanel());          //添加柱形图的另一种效果
//        frame.add(new PieChart().getChartPanel());           //添加饼状图
//        frame.add(new TimeSeriesChart().getChartPanel());    //添加折线图
        //frame.add(new BarChart().getChartPanel());
        frame.setBounds(50, 50, 600, 600);
        frame.setVisible(true);
    }
}

