package FormDataBase;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import Carcare.MyConnect;

public class FormCustomer extends JFrame {
	
	private DefaultTableModel modelCustomer;
	JTextField txtCustNum, txtCustName, txtCustAddr, txtCustPhone, txtCustMail, txtSearchCust;
	JTable tableCust;
	Connection conn = MyConnect.getConnection();
	
	public FormCustomer() {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(5, 1));
		
		JPanel panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(5, 2, 10, 5));
		
		JLabel lbCustNum = new JLabel("เลขที่ลูกค้า (CUST_NUM)");
		panelForm.add(lbCustNum);
		txtCustNum = new JTextField();
		txtCustNum.setPreferredSize(new Dimension(200, 30));
		panelForm.add(txtCustNum);
		
		JLabel lbCustName = new JLabel("ชื่อลูกค้า (CUST_NAME)");
		panelForm.add(lbCustName);
		txtCustName = new JTextField();
		txtCustName.setPreferredSize(new Dimension(200, 30));
		panelForm.add(txtCustName);
		
		JLabel lbCustAddr = new JLabel("ที่อยู่ลูกค้า (CUST_ADDR)");
		panelForm.add(lbCustAddr);
		txtCustAddr = new JTextField();
		txtCustAddr.setPreferredSize(new Dimension(200, 30));
		panelForm.add(txtCustAddr);
		
		JLabel lbCustPhone = new JLabel("โทรศัพท์ลูกค้า (CUST_PHONE)");
		panelForm.add(lbCustPhone);
		txtCustPhone = new JTextField();
		txtCustPhone.setPreferredSize(new Dimension(200, 30));
		panelForm.add(txtCustPhone);
		
		JLabel lbCustMail = new JLabel("อีเมลลูกค้า (CUST_MAIL)");
		panelForm.add(lbCustMail);
		txtCustMail = new JTextField();
		txtCustMail.setPreferredSize(new Dimension(200, 30));
		panelForm.add(txtCustMail);
		
		JLabel panelSearch = new JLabel();
		panelSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSearch.setBorder(BorderFactory.createTitledBorder("ค้นหา"));
		
		txtSearchCust = new JTextField();
		txtSearchCust.setPreferredSize(new Dimension(200, 30));
		txtSearchCust.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyenent) {
				showData();
			}
		});
		panelSearch.add(txtSearchCust);
		
		JPanel panelNorth = new JPanel(new GridLayout(1, 2, 10, 10));
		panelNorth.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelNorth.add(panelForm);
		panelNorth.add(panelSearch);
		
		JPanel panelButton = new JPanel(new FlowLayout());
		JButton cmdSaveCust = new JButton("เพิ่มข้อมูล");
		cmdSaveCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		panelButton.add(cmdSaveCust);
		JButton cmdUpdateCust = new JButton("แก้ไขข้อมูล");
		cmdUpdateCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		panelButton.add(cmdUpdateCust);
		JButton cmdDeleteCust = new JButton("ลบข้อมูล");
		cmdDeleteCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		panelButton.add(cmdDeleteCust);
		
		JPanel panelTable = new JPanel();
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setPreferredSize(new Dimension(750, 300));
		tableCust = new JTable();
		Object data[][] = {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null}
		};
		String columns[] = {"เลขที่ลูกค้า", "ชื่อลูกค้า", "ที่อยู๋ลูกค้า", "หมายเลยโทรศัพท์ลูกค้า", "อีเมลลูกค้า"};
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCust.setModel(tableModel);
		tableCust.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int index = tableCust.getSelectedRow();
				txtCustNum.setEditable(false);
				txtCustNum.setText(tableCust.getValueAt(index, 0).toString());
				txtCustName.setText(tableCust.getValueAt(index, 1).toString());
				txtCustAddr.setText(tableCust.getValueAt(index, 2).toString());
				txtCustPhone.setText(tableCust.getValueAt(index, 3).toString());
				txtCustMail.setText(tableCust.getValueAt(index, 4).toString());
			}
		});
		scrollTable.setViewportView(tableCust);
		panelTable.add(scrollTable);
		
		c.add(panelNorth, BorderLayout.PAGE_START);
		c.add(panelButton, BorderLayout.CENTER);
		c.add(panelTable, BorderLayout.PAGE_END);
		
		modelCustomer = (DefaultTableModel)tableCust.getModel();
		
		showData();
	}
	
	public void showData() {
		try {
			int totalRow = tableCust.getRowCount() -1;
			while(totalRow > -1) {
				modelCustomer.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearchCust.getText().trim();
			String sql = "SELECT * FROM Customer"
					+ " WHERE CUST_NUM LIKE '%" + search + "%'"
					+ " OR CUST_NAME LIKE '%" + search + "%'"
					+ " OR CUST_ADDR LIKE '%" + search + "%'"
					+ " OR CUST_PHONE LIKE '%" + search + "%'"
					+ " OR CUST_MAIL LIKE '%" + search + "%'"
			;
			ResultSet rs = conn.createStatement().executeQuery(sql);
			int row = 0;
			while(rs.next()) {
				modelCustomer.addRow(new Object[0]);
				modelCustomer.setValueAt(rs.getString("CUST_NUM"), row, 0);
				modelCustomer.setValueAt(rs.getString("CUST_NAME"), row, 1);
				modelCustomer.setValueAt(rs.getString("CUST_ADDR"), row, 2);
				modelCustomer.setValueAt(rs.getString("CUST_PHONE"), row, 3);
				modelCustomer.setValueAt(rs.getString("CUST_MAIL"), row, 4);
				row++;
			}
			tableCust.setModel(modelCustomer);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		try {
			String sql = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?)";
			java.sql.PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, txtCustNum.getText().trim());
			pre.setString(2, txtCustName.getText().trim());
			pre.setString(3, txtCustAddr.getText().trim());
			pre.setString(4, txtCustPhone.getText().trim());
			pre.setString(5, txtCustMail.getText().trim());
			
			if(pre.executeUpdate() != -1) {
				JOptionPane.showMessageDialog(this, "บันทึกข้อมูลลูกค้าเรียบร้อย", "ผลการบันทึกรายการ", JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCustNum.setText("");
				txtCustName.setText("");
				txtCustAddr.setText("");
				txtCustPhone.setText("");
				txtCustMail.setText("");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			if(tableCust.getSelectedRow() == -1)
				return;
			
			String sql = "UPDATE CUSTOMER SET "
					+ " CUST_NAME=?, "
					+ " CUST_ADDR=?, "
					+ " CUST_PHONE=?, "
					+ " CUST_MAIL=? "
					+ " WHERE CUST_NUM=? "
					;
			java.sql.PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, txtCustName.getText().trim());
			pre.setString(2, txtCustAddr.getText().trim());
			pre.setString(3, txtCustPhone.getText().trim());
			pre.setString(4, txtCustMail.getText().trim());
			pre.setString(5, txtCustNum.getText().trim());
			
			if(pre.executeUpdate() != -1) {
				JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลลูกค้าเรียบร้อย", "ผลการแก้ไขรายการ", JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCustNum.setEditable(true);
				txtCustNum.setText("");
				txtCustName.setText("");
				txtCustAddr.setText("");
				txtCustPhone.setText("");
				txtCustMail.setText("");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			if(tableCust.getSelectedRow() == -1)
				return;
			
			String sql = "DELETE FROM CUSTOMER "
					+ " WHERE CUST_NUM=?"
					;
			java.sql.PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, txtCustNum.getText().trim());
			
			if(pre.executeUpdate() != -1) {
				JOptionPane.showMessageDialog(this, "ลบข้อมูลลูกค้าเรียบร้อย", "ผลการลบรายการ", JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCustNum.setEditable(true);
				txtCustNum.setText("");
				txtCustName.setText("");
				txtCustAddr.setText("");
				txtCustPhone.setText("");
				txtCustMail.setText("");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
