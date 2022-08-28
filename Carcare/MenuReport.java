package Carcare;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuReport extends JFrame implements ActionListener {
	JButton addReCustButton, addReFinButton, addReServButton, closeButton;
	
	public MenuReport() {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(4, 1));
		
		JPanel L1 = new JPanel();
		addReCustButton = new JButton("รายงานข้อมูลลูกค้า");
		L1.add(addReCustButton);
		c.add(L1);
		
		JPanel L2 = new JPanel();
		addReFinButton = new JButton("รายงานข้อมูลการเงิน");
		L2.add(addReFinButton);
		c.add(L2);
		
		JPanel L3 = new JPanel();
		addReServButton = new JButton("รายงานข้อมูลการใช้บริการ");
		L3.add(addReServButton);
		c.add(L3);
		
		JPanel L4 = new JPanel();
		closeButton = new JButton("ออกจากโปรแกรม");
		closeButton.addActionListener(this);
		L4.add(closeButton);
		c.add(L4);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addReCustButton) {
			//
		}else if(e.getSource() == addReFinButton) {
			//
		}else if(e.getSource() == closeButton) {
			System.exit(0);
		}
	}
}
