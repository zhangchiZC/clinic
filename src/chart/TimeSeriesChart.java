package chart; /**
 * Created by ZC on 2019/7/5.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import po.Orders;
import util.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeSeriesChart {
    ChartPanel frame1;
    static DbUtil db = new DbUtil();

    public TimeSeriesChart() {
        XYDataset xydataset = createDataset();
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("各时期患者数", "日期", "患者数", xydataset, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));
        frame1 = new ChartPanel(jfreechart, true);
        dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));  //垂直标题
        ValueAxis rangeAxis = xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体

    }

    private static XYDataset createDataset() {  //这个数据集有点多，但都不难理解
        TimeSeries timeseries = new TimeSeries("legal & general欧洲指数信任",
                org.jfree.data.time.Day.class);
//        timeseries.add(new Month(2, 2001), 181.80000000000001D);
//        timeseries.add(new Month(3, 2001), 167.30000000000001D);
//        timeseries.add(new Month(4, 2001), 153.80000000000001D);
//        timeseries.add(new Month(5, 2001), 167.59999999999999D);
//        timeseries.add(new Month(6, 2001), 158.80000000000001D);
//        timeseries.add(new Month(7, 2001), 148.30000000000001D);
//        timeseries.add(new Month(8, 2001), 153.90000000000001D);
//        timeseries.add(new Month(9, 2001), 142.69999999999999D);
//        timeseries.add(new Month(10, 2001), 123.2D);
//        timeseries.add(new Month(11, 2001), 131.80000000000001D);
//        timeseries.add(new Month(12, 2001), 139.59999999999999D);
//        timeseries.add(new Month(1, 2002), 142.90000000000001D);
//        timeseries.add(new Month(2, 2002), 138.69999999999999D);
//        timeseries.add(new Month(3, 2002), 137.30000000000001D);
//        timeseries.add(new Month(4, 2002), 143.90000000000001D);
//        timeseries.add(new Month(5, 2002), 139.80000000000001D);
//        timeseries.add(new Month(6, 2002), 137D);
//        timeseries.add(new Month(7, 2002), 132.80000000000001D);
        try {
            java.util.List<Orders> ordersList = db.chartOrderDateAndPatientAmount();
            for(int i=0;i<ordersList.size();i++) {
                Integer year = Integer.parseInt(ordersList.get(i).getOrderDate().split("-")[0]);
                Integer month = Integer.parseInt(String.valueOf(ordersList.get(0).getOrderDate().split("-")[1].charAt(1)));
                Integer day = Integer.parseInt(ordersList.get(i).getOrderDate().split("-")[2]);
                Integer patient_amount = ordersList.get(i).getPatient_amount();
                timeseries.add(new Day(day, month, year), patient_amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




//        TimeSeries timeseries1 = new TimeSeries("legal & general英国指数信任",
//                org.jfree.data.time.Month.class);
//        timeseries1.add(new Month(2, 2001), 129.59999999999999D);
//        timeseries1.add(new Month(3, 2001), 123.2D);
//        timeseries1.add(new Month(4, 2001), 117.2D);
//        timeseries1.add(new Month(5, 2001), 124.09999999999999D);
//        timeseries1.add(new Month(6, 2001), 122.59999999999999D);
//        timeseries1.add(new Month(7, 2001), 119.2D);
//        timeseries1.add(new Month(8, 2001), 116.5D);
//        timeseries1.add(new Month(9, 2001), 112.7D);
//        timeseries1.add(new Month(10, 2001), 101.5D);
//        timeseries1.add(new Month(11, 2001), 106.09999999999999D);
//        timeseries1.add(new Month(12, 2001), 110.3D);
//        timeseries1.add(new Month(1, 2002), 111.7D);
//        timeseries1.add(new Month(2, 2002), 111D);
//        timeseries1.add(new Month(3, 2002), 109.59999999999999D);
//        timeseries1.add(new Month(4, 2002), 113.2D);
//        timeseries1.add(new Month(5, 2002), 111.59999999999999D);
//        timeseries1.add(new Month(6, 2002), 108.8D);
//        timeseries1.add(new Month(7, 2002), 101.59999999999999D);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        //timeseriescollection.addSeries(timeseries1);
        return timeseriescollection;
    }

    public ChartPanel getChartPanel() {
        return frame1;

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Java数据统计图");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.add(new TimeSeriesChart().getChartPanel());
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}