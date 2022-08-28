package Carcare;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import FormDataBase.FormCustomer;

public class MenuForm extends JFrame implements ActionListener {
	JButton addCustButton, addInvButton, addServButton, addEmpButton, addInvSerButton;
	
	public MenuForm() {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(5, 1));
		
		JPanel L1 = new JPanel();
		addCustButton = new JButton("ข้อมูลลูกค้า (CUSTOMER)");
		addCustButton.addActionListener(this);
		L1.add(addCustButton);
		c.add(L1);
		
		JPanel L2 = new JPanel();
		addInvButton = new JButton("ข้อมูลใบแจ้งหนี้ (INVOICE)");
		L2.add(addInvButton);
		c.add(L2);
		
		JPanel L3 = new JPanel();
		addServButton = new JButton("ข้อมูลบริการ (SERVICE)");
		L3.add(addServButton);
		c.add(L3);
		
		JPanel L4 = new JPanel();
		addEmpButton = new JButton("ข้อมูลพนักงาน (EMPLOYEE)");
		L4.add(addEmpButton);
		c.add(L4);
		
		JPanel L5 = new JPanel();
		addInvSerButton = new JButton("ข้อมูลใบแจ้งหนี้ระบุการบริการ (INV_SERV)");
		L5.add(addInvSerButton);
		c.add(L5);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addCustButton) {
			FormCustomer frame = new FormCustomer();
			frame.setSize(800, 600);
			frame.setTitle("การจัดการข้อมูลลูกค้า (CUSTOMER)");
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
	}
}
