import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class BusinessCont extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tbFname;
	private JTextField tbEmail;
	private JTextField tbTel;
	private JTextField tbPost;
	private JTextField tbAddr1;
	private JTextField tbCity;
	private JTextField tbLname;
	private JTextField tbAddr2;
	private JTextField tbBTel;

	boolean updatemode= true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusinessCont frame = new BusinessCont();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JButton btnAddNew = new JButton("Add New");
	JButton btnSaveNew = new JButton("Save New");
	JButton btnUpdate = new JButton("Update Selected");
	JButton btnSave = new JButton("Save Selected");
	JButton btnRefresh = new JButton("Refresh");
	JButton btnDelete = new JButton("Delete Selected");
	/**
	 * Create the frame.
	 */
	public BusinessCont() {
		setTitle("Business Contacts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		dbConn d=new dbConn();

		table = new JTable();
		table.setModel(DbUtils.resultSetToTableModel(d.GetAllBusiness()));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tbFname.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				tbLname.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				tbEmail.setText(table.getValueAt(table.getSelectedRow(),4).toString());
				tbTel.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				tbAddr1.setText(table.getValueAt(table.getSelectedRow(),5).toString());
				tbAddr2.setText(table.getValueAt(table.getSelectedRow(),6).toString());
				tbCity.setText(table. getValueAt(table.getSelectedRow(),7).toString());
				tbPost.setText(table.getValueAt(table.getSelectedRow(),8).toString());
				tbBTel.setText(table.getValueAt(table.getSelectedRow(),9).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		btnSaveNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(tbFname.getText().isEmpty()||tbLname.getText().isEmpty()||tbEmail.getText().isEmpty()||tbAddr1.getText().isEmpty()||tbCity.getText().isEmpty()||tbPost.getText().isEmpty()||tbBTel.getText().isEmpty()))
				{
				if(updatemode == true)
				{
				String f=tbFname.getText();
				String l=tbLname.getText();
				String t=tbTel.getText();
				String em=tbEmail.getText();
				String a1=tbAddr1.getText();
				String a2=tbAddr2.getText();
				String c=tbCity.getText();
				String p=tbPost.getText();
				String pt=tbBTel.getText();
				String id=table.getValueAt(table.getSelectedRow(), 0).toString();
				d.UpdateBusiness(f, l, t, em, a1, a2, c, p, pt, id);
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllBusiness()));
				}
				else
				{
					String f=tbFname.getText();
					String l=tbLname.getText();
					String t=tbTel.getText();
					String em=tbEmail.getText();
					String a1=tbAddr1.getText();
					String a2=tbAddr2.getText();
					String c=tbCity.getText();
					String p=tbPost.getText();
					String pt=tbBTel.getText();
					d.AddBusiness(f, l, t, em, a1, a2, c, p, pt);
					table.setModel(DbUtils.resultSetToTableModel(d.GetAllBusiness()));

				}
				btnUpdate.setEnabled(true);
				btnAddNew.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnSaveNew.setEnabled(false);
				tbFname.setText("");
				tbEmail.setText("");
				tbTel.setText("");
				tbPost.setText("");
				tbAddr1.setText("");
				tbCity.setText("");
				tbLname.setText("");
				tbAddr2.setText("");
				tbBTel.setText("");;
				tbFname.setEnabled(false);
				tbEmail.setEnabled(false);
				tbTel.setEnabled(false);
				tbPost.setEnabled(false);
				tbAddr1.setEnabled(false);
				tbCity.setEnabled(false);
				tbLname.setEnabled(false);
				tbAddr2.setEnabled(false);
				tbBTel.setEnabled(false);
			}
			else JOptionPane.showMessageDialog(null, "Please enter some values");
		}
		});
		btnSaveNew.setEnabled(false);
		
		
		btnAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbFname.setEnabled(true);
				tbEmail.setEnabled(true);
				tbTel.setEnabled(true);
				tbPost.setEnabled(true);
				tbAddr1.setEnabled(true);
				tbCity.setEnabled(true);
				tbLname.setEnabled(true);
				tbAddr2.setEnabled(true);
				tbBTel.setEnabled(true);
				updatemode=false;
				System.out.println(updatemode);
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true); 
			}
		});

		tbFname = new JTextField();
		tbFname.setColumns(10);
		tbFname.setEnabled(false);
		
		tbEmail = new JTextField();
		tbEmail.setColumns(10);
		tbEmail.setEnabled(false);
		
		tbTel = new JTextField();
		tbTel.setColumns(10);
		tbTel.setEnabled(false);
		
		tbPost = new JTextField();
		tbPost.setColumns(10);
		tbPost.setEnabled(false);
		
		tbAddr1 = new JTextField();
		tbAddr1.setColumns(10);
		tbAddr1.setEnabled(false);
		
		tbCity = new JTextField();
		tbCity.setColumns(10);
		tbCity.setEnabled(false);
		
		tbLname = new JTextField();
		tbLname.setColumns(10);
		tbLname.setEnabled(false);
		
		tbAddr2 = new JTextField();
		tbAddr2.setColumns(10);
		tbAddr2.setEnabled(false);
		
		tbBTel = new JTextField();
		tbBTel.setColumns(10);
		tbBTel.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("First Name");
		
		JLabel lblLastName = new JLabel("Last Name");
		
		JLabel lblTelephone = new JLabel("Telephone");
		
		JLabel lblE = new JLabel("Email");
		
		JLabel lblAddressLine = new JLabel("Address Line 1");
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2");
		
		JLabel lblCity = new JLabel("City");
		
		JLabel lblPostCode = new JLabel("Post Code");
		
		JLabel lblBusinessTelephone = new JLabel("Business Telephone");
		
		btnSaveNew.setEnabled(false);
		
		
		
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbFname.setEnabled(true);
				tbEmail.setEnabled(true);
				tbTel.setEnabled(true);
				tbPost.setEnabled(true);
				tbAddr1.setEnabled(true);
				tbCity.setEnabled(true);
				tbLname.setEnabled(true);
				tbAddr2.setEnabled(true);
				tbBTel.setEnabled(true);
				updatemode=true;
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
	            btnSaveNew.setEnabled(false);
	            btnSave.setEnabled(true);
	            btnAddNew.setEnabled(false);
			}
		});		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(tbFname.getText().isEmpty()||tbLname.getText().isEmpty()||tbEmail.getText().isEmpty()||tbAddr1.getText().isEmpty()||tbCity.getText().isEmpty()||tbPost.getText().isEmpty()||tbBTel.getText().isEmpty()))
				{
					String f=tbFname.getText();
					String l=tbLname.getText();
					String t=tbTel.getText();
					String em=tbEmail.getText();
					String a1=tbAddr1.getText();
					String a2=tbAddr2.getText();
					String c=tbCity.getText();
					String p=tbPost.getText();
					String pt=tbBTel.getText();
					String id=table.getValueAt(table.getSelectedRow(), 0).toString();
					d.UpdateBusiness(f, l, t, em, a1, a2, c, p, pt, id);
				}
					btnSave.setEnabled(false);
					btnUpdate.setEnabled(true);
					btnAddNew.setEnabled(true);
					btnDelete.setEnabled(true);
					tbFname.setText("");
					tbEmail.setText("");
					tbTel.setText("");
					tbPost.setText("");
					tbAddr1.setText("");
					tbCity.setText("");
					tbLname.setText("");
					tbAddr2.setText("");
					tbBTel.setText("");;
					tbFname.setEnabled(false);
					tbEmail.setEnabled(false);
					tbTel.setEnabled(false);
					tbPost.setEnabled(false);
					tbAddr1.setEnabled(false);
					tbCity.setEnabled(false);
					tbLname.setEnabled(false);
					tbAddr2.setEnabled(false);
					tbBTel.setEnabled(false);
			}
		});
		
		btnSave.setEnabled(false);
		

		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllBusiness()));
			}
		});
		

		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id=table.getValueAt(table.getSelectedRow(), 0).toString();
				d.DeleteBusiness(id);
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllBusiness()));
				tbFname.setText("");
				tbEmail.setText("");
				tbTel.setText("");
				tbPost.setText("");
				tbAddr1.setText("");
				tbCity.setText("");
				tbLname.setText("");
				tbAddr2.setText("");
				tbBTel.setText("");;
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tbFname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblAddressLine, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tbAddr1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblBusinessTelephone, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tbBTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelephone, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tbLname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tbTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAddressLine_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tbAddr2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tbCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddNew, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSaveNew, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblE, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tbEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblPostCode, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tbPost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(185)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE))
					.addGap(1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(tbFname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblAddressLine)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(tbAddr1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblBusinessTelephone))
						.addComponent(tbBTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(lblLastName)
							.addGap(17)
							.addComponent(lblTelephone))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(tbLname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(tbTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lblAddressLine_1)
							.addGap(17)
							.addComponent(lblCity))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addComponent(tbAddr2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(tbCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnUpdate)
							.addGap(2)
							.addComponent(btnSave)
							.addGap(6)
							.addComponent(btnDelete))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAddNew)
							.addGap(2)
							.addComponent(btnSaveNew)))
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(tbEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPostCode)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(tbPost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(btnRefresh)))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
