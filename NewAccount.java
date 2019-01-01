import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.Font;
import javax.swing.JScrollBar;

public class NewAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textEmail;
	private JPasswordField password;
	private JPasswordField passwordConfirm;
	static String dmn1;
	static String dmn;


	/**
	 * Launch the application.
	 */

	Connection connection = null;
	Statement createTable = null;
	Statement createOutbox = null;
	Statement createDraftbox = null;
	Statement createTrash=null;
	/**
	 * Create the frame.
	 */
	public NewAccount() {
		setResizable(false);
		setTitle("Create Account");

		connection = sqliteConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		setLocation(650, 250);

		contentPane.setLayout(null);

		JLabel lblFirstbname = new JLabel("FirstName");
		lblFirstbname.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFirstbname.setBounds(58, 42, 101, 20);
		contentPane.add(lblFirstbname);

		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLastname.setBounds(58, 85, 101, 20);
		contentPane.add(lblLastname);

		textFirstName = new JTextField();
		textFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFirstName.setBounds(161, 35, 233, 35);
		contentPane.add(textFirstName);
		textFirstName.setColumns(10);

		textLastName = new JTextField();
		textLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textLastName.setBounds(161, 78, 233, 35);
		contentPane.add(textLastName);
		textLastName.setColumns(10);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textEmail.setBounds(58, 139, 271, 35);
		contentPane.add(textEmail);
		textEmail.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setBounds(344, 139, 146, 42);
		contentPane.add(comboBox);
		comboBox.addItem("@cq.edu");
		comboBox.addItem("@yg.com");
		comboBox.addItem("@lnb.gov");

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(61, 218, 119, 20);
		contentPane.add(lblPassword);

		JLabel lblConfrimPassword = new JLabel("Confrim Password:");
		lblConfrimPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblConfrimPassword.setBounds(61, 258, 187, 20);
		contentPane.add(lblConfrimPassword);

		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 22));
		password.setBounds(252, 215, 208, 26);
		contentPane.add(password);

		//		char[] pas = password.getPassword();
		//
		//        if (pas.length > 7) {
		//            System.out.println("Password must contain 7 characters!");
		//        }

		passwordConfirm = new JPasswordField();
		passwordConfirm.setFont(new Font("Tahoma", Font.PLAIN, 22));
		passwordConfirm.setBounds(252, 255, 208, 26);
		contentPane.add(passwordConfirm);





		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String pass = new String(password.getPassword());
					String passConfirm = new String(passwordConfirm.getPassword());
					int x = pass.length();




					if(!(textEmail.getText().isEmpty() || textFirstName.getText().isEmpty() || textLastName.getText().isEmpty() || 
							password.getText().isEmpty() || passwordConfirm.getText().isEmpty()))
					{
						if(!textEmail.getText().contains("@")) {

							String x11 = comboBox.getSelectedItem().toString();
							int x12 = x11.length();
							//							System.out.println(x12);
							if(!(((textEmail.getText().length()+x12)>20) || textEmail.getText().length()<4)) {//new if startst
								if(!(x<4 || x>12)){
									//								if(textEmail.getText().contains("@")) {



									if(pass.equals(passConfirm)) {
										try {

											String query = "insert into email (emailId,password,firstName,lastName) values (?,?,?,?)";

											PreparedStatement pst = connection.prepareStatement(query);



											pst.setString(1,textEmail.getText().toLowerCase() + comboBox.getSelectedItem() );

											pst.setString(2,password.getText() );
											pst.setString(3,textFirstName.getText());
											pst.setString(4, textLastName.getText());	

											pst.execute();

											dmn = comboBox.getSelectedItem().toString();
											int cutt= dmn.indexOf(".");
											dmn1 = dmn.substring(1, cutt);
											//											System.out.println(dmn1);



											//										System.out.println(x11);
											JOptionPane.showMessageDialog(null,"Account Created Successfully: Congratulation!");

											createTable =  connection.createStatement();
											String emailtable = textEmail.getText().toLowerCase()+dmn1+"inbox";
											//										String domain = comboBox.getSelectedItem().toString();
											//										String  tblName = emailtable + domain;
											//										System.out.println(tblName);

											createOutbox = connection.createStatement();
											String emailOut = textEmail.getText().toLowerCase() +dmn1+ "Outbox";



											createTable.executeUpdate("CREATE TABLE "+emailtable+" " +
													"(id TEXT not NULL, " +
													" fromemail TEXT, " +
													" subject TEXT, " +
													" incoming BLOB, " +
													" date TEXT, " + 
													" PRIMARY KEY ( id ))");

											createOutbox.executeUpdate("CREATE TABLE "+emailOut+" " +
													"(idd TEXT not NULL, " +
													" emailto TEXT, " +
													" subject TEXT, " +
													" message BLOB, " + 
													" dateout TEXT, "+
													" PRIMARY KEY ( idd ))");

											createDraftbox = connection.createStatement();
											String emailDraft = textEmail.getText().toLowerCase() +dmn1+"DraftBox";

											createTable.executeUpdate("CREATE TABLE "+ emailDraft+" "+
													"(idq TEXT, " +
													"Draftto TEXT, "+
													" subject TEXT, "+
													"message BLOB, "+
													" dateDraft TEXT, "+
													" PRIMARY KEY ( idq ))");
											
											createTrash = connection.createStatement();
											String emailTrash = textEmail.getText().toLowerCase()+dmn1+"Trash";
											
											createTable.executeUpdate("CREATE TABLE "+ emailTrash +" "+
												"(idz TEXT, " +
												"fromTo TEXT, "+
												"subject TEXT, "+
												"message BLOB, "+
												"dateTrash TEXT, "+
												" PRIMARY KEY( idz )) ");

											contentPane.setVisible(false);
											NewAccount.super.setVisible(false);
											SignIn window = new SignIn();
											window.frmSignIn.setVisible(true);



											pst.close();
										}
										catch(Exception e5) {
											JOptionPane.showMessageDialog(null,"This email account already exist. Try another please:");
										}
									}
									else {
										JOptionPane.showMessageDialog(null,"Password does not match: Try again!");
									}
								}//

								else {
									JOptionPane.showMessageDialog(null,"password length has to be atleast 4 charcter long and less then 13:");

									//								JOptionPane.showMessageDialog(null,"User name can not be more than 20 charcters: Try Again:");
								}//new else


							}
							else {
								JOptionPane.showMessageDialog(null,"User name can not be more than 20 charcters and less than 4 characters: Try Again:");

							}
						}
						else {
							JOptionPane.showMessageDialog(null,"User name must not conatain @:Try Again:");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"please fill all the information:");
					}


				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreateAccount.setBounds(243, 302, 271, 47);
		contentPane.add(btnCreateAccount);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Password Criteria:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(542, 221, 286, 72);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextPane txtpnEfwef = new JTextPane();
		txtpnEfwef.setBounds(0, 16, 281, 130);
		panel.add(txtpnEfwef);
		txtpnEfwef.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnEfwef.setEnabled(false);
		txtpnEfwef.setEditable(false);
		txtpnEfwef.setText("1. password lenght must be more than 4 and less then 13.");

		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setToolTipText("Go back to sign in screen");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				NewAccount.super.setVisible(false);
				SignIn window = new SignIn();
				//				window.setVisible(true);
				window.frmSignIn.setVisible(true);
			}
		});
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSignIn.setBounds(675, 16, 135, 35);
		contentPane.add(btnSignIn);
	}
}
