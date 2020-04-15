import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PersonalCont extends JFrame {

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
	private JTextField tbPTel;

	boolean updatemode= true;//sets updatemode default

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalCont frame = new PersonalCont();//main method that will make the frame visible
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JButton btnAddNew = new JButton("Add New");//buttons :)
	JButton btnSaveNew = new JButton("Save New");
	JButton btnUpdate = new JButton("Update Selected");
	JButton btnSave = new JButton("Save Selected");
	JButton btnRefresh = new JButton("Refresh");
	JButton btnDelete = new JButton("Delete Selected");
	/**
	 * Create the frame.
	 */
	public PersonalCont() {
		setTitle("Personal Contacts");//PersonalCont frame info
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();//scoll panel
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {//perhaps a useless piece of code but why fix something does works?

			}
		});
		dbConn d=new dbConn();//creates the variable for other pieces of code

		table = new JTable();//creates the variable for other pieces of code
		table.setModel(DbUtils.resultSetToTableModel(d.GetAllPersonal()));//makes the table display personal records

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {//text fields and how the will get their information and then display it
				tbFname.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				tbLname.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				tbEmail.setText(table.getValueAt(table.getSelectedRow(),4).toString());
				tbTel.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				tbAddr1.setText(table.getValueAt(table.getSelectedRow(),5).toString());
				tbAddr2.setText(table.getValueAt(table.getSelectedRow(),6).toString());
				tbCity.setText(table. getValueAt(table.getSelectedRow(),7).toString());
				tbPost.setText(table.getValueAt(table.getSelectedRow(),8).toString());
				tbPTel.setText(table.getValueAt(table.getSelectedRow(),9).toString());
			}
		});
		scrollPane.setViewportView(table);//will disply the table
		
		btnSaveNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//on mouse click event
				//if statement that makes sure the the text fields will not be empty
				if(!(tbFname.getText().isEmpty()||tbLname.getText().isEmpty()||tbEmail.getText().isEmpty()||tbAddr1.getText().isEmpty()||tbCity.getText().isEmpty()||tbPost.getText().isEmpty()||tbPTel.getText().isEmpty()))
				{
				if(updatemode == true)
				{
				String f=tbFname.getText();//the values will be read thanks the the get text command
				String l=tbLname.getText();
				String t=tbTel.getText();
				String em=tbEmail.getText();
				String a1=tbAddr1.getText();
				String a2=tbAddr2.getText();
				String c=tbCity.getText();
				String p=tbPost.getText();
				String pt=tbPTel.getText();
				String id=table.getValueAt(table.getSelectedRow(), 0).toString();
				d.UpdatePersonal(f, l, t, em, a1, a2, c, p, pt, id);//executes with the specified values
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllPersonal()));//updates the table
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
					String pt=tbPTel.getText();
					d.AddPersonal(f, l, t, em, a1, a2, c, p, pt);//adds a record
					table.setModel(DbUtils.resultSetToTableModel(d.GetAllPersonal()));

				}
				btnUpdate.setEnabled(true);//resets the buttons and text fields so that everything can be done again
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
				tbPTel.setText("");;
				tbFname.setEnabled(false);
				tbEmail.setEnabled(false);
				tbTel.setEnabled(false);
				tbPost.setEnabled(false);
				tbAddr1.setEnabled(false);
				tbCity.setEnabled(false);
				tbLname.setEnabled(false);
				tbAddr2.setEnabled(false);
				tbPTel.setEnabled(false);
			}
			else JOptionPane.showMessageDialog(null, "Please enter some values");//when the if statement is not true, show this message 
		}
		});
		btnSaveNew.setEnabled(false);
		
		
		btnAddNew.addMouseListener(new MouseAdapter() {//settings for the add new button
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
				tbPTel.setEnabled(true);
				updatemode=false;
				System.out.println(updatemode);
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true); 
			}
		});
//text fields that will show data, disabled by default
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
		
		tbPTel = new JTextField();
		tbPTel.setColumns(10);
		tbPTel.setEnabled(false);
		//labeles that will make the app readible
		JLabel lblNewLabel = new JLabel("First Name");
		
		JLabel lblLastName = new JLabel("Last Name");
		
		JLabel lblTelephone = new JLabel("Telephone");
		
		JLabel lblE = new JLabel("Email");
		
		JLabel lblAddressLine = new JLabel("Address Line 1");
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2");
		
		JLabel lblCity = new JLabel("City");
		
		JLabel lblPostCode = new JLabel("Post Code");
		
		JLabel lblPersonalTelephone = new JLabel("Personal Telephone");
		
		btnSaveNew.setEnabled(false);
		
		
		
		btnUpdate.addMouseListener(new MouseAdapter() {//update button specification on mouse click
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
				tbPTel.setEnabled(true);
				updatemode=true;
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
	            btnSaveNew.setEnabled(false);
	            btnSave.setEnabled(true);
	            btnAddNew.setEnabled(false);
			}
		});		
		btnSave.addActionListener(new ActionListener() {//save button specification
			public void actionPerformed(ActionEvent e) {
				if(!(tbFname.getText().isEmpty()||tbLname.getText().isEmpty()||tbEmail.getText().isEmpty()||tbAddr1.getText().isEmpty()||tbCity.getText().isEmpty()||tbPost.getText().isEmpty()||tbPTel.getText().isEmpty()))
				{
					String f=tbFname.getText();
					String l=tbLname.getText();
					String t=tbTel.getText();
					String em=tbEmail.getText();
					String a1=tbAddr1.getText();
					String a2=tbAddr2.getText();
					String c=tbCity.getText();
					String p=tbPost.getText();
					String pt=tbPTel.getText();
					String id=table.getValueAt(table.getSelectedRow(), 0).toString();
					d.UpdatePersonal(f, l, t, em, a1, a2, c, p, pt, id);
				}
					btnSave.setEnabled(false);
					btnUpdate.setEnabled(true);//resets the buttons
					btnAddNew.setEnabled(true);
					btnDelete.setEnabled(true);
					tbFname.setText("");//this will reset the text fields
					tbEmail.setText("");
					tbTel.setText("");
					tbPost.setText("");
					tbAddr1.setText("");
					tbCity.setText("");
					tbLname.setText("");
					tbAddr2.setText("");
					tbPTel.setText("");;
					tbFname.setEnabled(false);//disables the text fields
					tbEmail.setEnabled(false);
					tbTel.setEnabled(false);
					tbPost.setEnabled(false);
					tbAddr1.setEnabled(false);
					tbCity.setEnabled(false);
					tbLname.setEnabled(false);
					tbAddr2.setEnabled(false);
					tbPTel.setEnabled(false);
			}
		});
		
		btnSave.setEnabled(false);
		

		btnRefresh.addMouseListener(new MouseAdapter() {//refresh button functionality
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllPersonal()));
			}
		});
		

		btnDelete.addMouseListener(new MouseAdapter() {//delete function of the app
			@Override
			public void mouseClicked(MouseEvent e) {
				String id=table.getValueAt(table.getSelectedRow(), 0).toString();//based only on ID
				d.DeletePersonal(id);
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllPersonal()));
				tbFname.setText("");
				tbEmail.setText("");
				tbTel.setText("");
				tbPost.setText("");
				tbAddr1.setText("");
				tbCity.setText("");
				tbLname.setText("");
				tbAddr2.setText("");
				tbPTel.setText("");;
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);//automaticly created layout information that was created in the design view
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
							.addComponent(lblPersonalTelephone, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tbPTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
		gl_contentPane.setVerticalGroup(//automaticly created layout information that was created in the design view
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
							.addComponent(lblPersonalTelephone))
						.addComponent(tbPTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
		contentPane.setLayout(gl_contentPane);//sets the layout this way
	}
}
