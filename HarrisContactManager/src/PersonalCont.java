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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonalCont extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalCont frame = new PersonalCont();
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
	public PersonalCont() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 567, 249);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		dbConn d=new dbConn();
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(488, 108, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(70, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(70, 108, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(70, 75, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(247, 111, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(247, 11, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(247, 77, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(70, 44, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(247, 44, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(457, 11, 86, 20);
		contentPane.add(textField_8);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 14, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 47, 50, 14);
		contentPane.add(lblLastName);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(10, 78, 50, 14);
		contentPane.add(lblTelephone);
		
		JLabel lblE = new JLabel("Email");
		lblE.setBounds(10, 112, 46, 14);
		contentPane.add(lblE);
		
		JLabel lblAddressLine = new JLabel("Address Line 1");
		lblAddressLine.setBounds(166, 11, 70, 14);
		contentPane.add(lblAddressLine);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2");
		lblAddressLine_1.setBounds(166, 44, 70, 14);
		contentPane.add(lblAddressLine_1);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(166, 75, 46, 14);
		contentPane.add(lblCity);
		
		JLabel lblPostCode = new JLabel("Post Code");
		lblPostCode.setBounds(166, 109, 70, 14);
		contentPane.add(lblPostCode);
		
		JLabel lblPersonalTelephone = new JLabel("Personal Telephone");
		lblPersonalTelephone.setBounds(340, 14, 107, 14);
		contentPane.add(lblPersonalTelephone);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(488, 74, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("New button");
		btnNewButton_1_1.setBounds(488, 43, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("New button");
		btnNewButton_1_2.setBounds(389, 74, 89, 23);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton("Update");
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllPersonal()));
			}
		});
		btnNewButton_1_1_1.setBounds(389, 43, 89, 23);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_2 = new JButton("New button");
		btnNewButton_1_1_2.setBounds(389, 108, 89, 23);
		contentPane.add(btnNewButton_1_1_2);
	}
}
