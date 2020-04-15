import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContactManager {

	private JFrame frmHarrisContactManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactManager window = new ContactManager();//runs the window
					window.frmHarrisContactManager.setVisible(true);//shows the view
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContactManager() {
		initialize();//initializes component
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHarrisContactManager = new JFrame();//specification of the J frame
		frmHarrisContactManager.setTitle("Harris Contact Manager");
		frmHarrisContactManager.setBounds(100, 100, 675, 387);
		frmHarrisContactManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHarrisContactManager.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Business Contacts");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BusinessCont bc=new BusinessCont();

				bc.setVisible(true);//shows the businessConn frame
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(381, 128, 164, 23);
		frmHarrisContactManager.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Personal Contact");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonalCont pc=new PersonalCont();

				pc.setVisible(true);//shows the personalConn frame
			}
		});
		btnNewButton_1.setBounds(118, 128, 164, 23);
		frmHarrisContactManager.getContentPane().add(btnNewButton_1);
	}
}
