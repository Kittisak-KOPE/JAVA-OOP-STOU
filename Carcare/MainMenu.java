package Carcare;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MainMenu extends JFrame implements ActionListener{
	JButton menuFormButton, reportButton, closeButton;
	
	public MainMenu() {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(4, 0));
		JLabel L1 = new JLabel("บริษัท STOU CARCARE จำกัด", SwingConstants.CENTER);
		L1.setForeground(Color.RED);
		L1.setFont(new Font("Tahoma", Font.ITALIC | Font.BOLD, 30));
		c.add(L1);
		
		JPanel L2 = new JPanel();
		menuFormButton = new JButton("ระบบจัดการข้อมูลพื้นฐาน");
		menuFormButton.addActionListener(this);
		L2.add(menuFormButton);
		reportButton = new JButton("ระะบรายงาน");
		reportButton.addActionListener(this);
		L2.add(reportButton);
		c.add(L2);
		
		JPanel L3 = new JPanel();
		closeButton = new JButton("ออกจากโปรแกรม");
		closeButton.addActionListener(this);
		L3.add(closeButton);
		c.add(L3);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuFormButton) {
			MenuForm addMenuFormFrame = new MenuForm();
			addMenuFormFrame.setSize(500, 300);
			addMenuFormFrame.setTitle("ระบบจัดการข้อมูลพื้นฐาน : ธุรกิจดูแลรักษารถยนต์");
			addMenuFormFrame.setLocationRelativeTo(null);
			addMenuFormFrame.setVisible(true);
		}else if(e.getSource() == reportButton) {
			MenuReport newMenuReport = new MenuReport();
			newMenuReport.setSize(500, 300);
			newMenuReport.setTitle("ระบบจัดการรายงาน : ธุรกิจดูแลรักษารถยนต์");
			newMenuReport.setLocationRelativeTo(null);
			newMenuReport.setVisible(true);
		}else if(e.getSource() == closeButton) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			new MainMenu();
		}
		
		MainMenu frame = new MainMenu();
		frame.setSize(650, 250);
		frame.setTitle("ระบบธุรกิจดูแลรักษารถยนต์");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
