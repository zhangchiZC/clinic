package chart;

import org.jfree.chart.demo.PieChartDemo1;
import util.DbUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StatisticChart extends JFrame {

	private JPanel contentPane;
	DbUtil db = new DbUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticChart frame = new StatisticChart();
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
	public StatisticChart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("各时期患者数", null, panel, null);
		panel.add(new BarChart().getChartPanel());
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("药品销量", null, panel_1, null);
		panel_1.add(new PieChart().getChartPanel());
		
//		JPanel panel_2 = new JPanel();
//		tabbedPane.addTab("New tab", null, panel_2, null);
//		//panel_2.setLayout(new GridLayout(2, 2, 10, 10));
//		panel_2.add(new PieChart().getChartPanel());
//
//
//		JComboBox comboBox = null;
//		try {
//			comboBox = new JComboBox(new DefaultComboBoxModel(db.findAllDepartment().toArray()));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		comboBox.setBounds(107, 474, 114, 24);
//		panel_2.add(comboBox);
//
//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.setBounds(409, 473, 113, 27);
//		panel_2.add(btnNewButton);


	}
}
