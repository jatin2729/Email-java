import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SignIn {

	JFrame frmSignIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
					window.frmSignIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	static Connection connection  = null;//connection to the database;
	static JTextField textFieldEmail;
	private JPasswordField passwordFieldPass;
	static String domain;

	public SignIn() {
		initialize();
		connection = sqliteConnection.dbConnector();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignIn = new JFrame();
		frmSignIn.setResizable(false);
		frmSignIn.setTitle("Sign In");
		frmSignIn.setBounds(100, 100, 628, 388);
		frmSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignIn.getContentPane().setLayout(null);
		frmSignIn.setLocation(650, 250);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setBounds(433, 37, 142, 41);
		frmSignIn.getContentPane().add(comboBox);
		comboBox.addItem("@cq.edu");
		comboBox.addItem("@yg.com");
		comboBox.addItem("@lnb.gov");


		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from email where emailId=? and password=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					domain = (String)comboBox.getSelectedItem();
					//					System.out.println(domain);

					pst.setString(1,textFieldEmail.getText().toLowerCase() + domain );
					pst.setString(2,passwordFieldPass.getText() );//to make it case sensitive


					ResultSet rs = pst.executeQuery();//checks data into table databse

					String query1 = "select * from email where emailId=?";
					PreparedStatement pstEmail = connection.prepareStatement(query1);
					pstEmail.setString(1,textFieldEmail.getText().toLowerCase() + domain);
					ResultSet rsEmail = pstEmail.executeQuery();
					int cEmail = 0;
					while(rsEmail.next()) {
						cEmail++;
					}
					if( cEmail==1) {
						//						JOptionPane.showMessageDialog(null,"Email exist:");
						//						System.out.println("Email exist");

						int count = 0;
						while(rs.next()) {//boolean value checks value in table one by one
							count = count +1;				

						}
						if ( count == 1) {
							//							JOptionPane.showMessageDialog(null, "Email and pass is correct");
							frmSignIn.dispose();
							Account x = new Account();
							x.setVisible(true);
						}
						//					else if(count == 0) {
						//						JOptionPane.showMessageDialog(null, "Email Account does not Exist:");
						//					}
						else if (count>1){
							
							JOptionPane.showMessageDialog(null, "Email and pass is DUPLICATE");
						}
						else {
							JOptionPane.showMessageDialog(null, "Email and pass is INCORRECT, TRY AGAIN");
						}



						rs.close();
						pst.close();

					}
					else {
						JOptionPane.showMessageDialog(null,"Email does not exist:");

					}
					rsEmail.close();
					pstEmail.close();
				}

				catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}

			}
		});


		btnSignIn.setBounds(232, 186, 146, 50);
		frmSignIn.getContentPane().add(btnSignIn);

		textFieldEmail = new JTextField();
		textFieldEmail.setToolTipText("");
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFieldEmail.setBounds(182, 37, 236, 35);
		frmSignIn.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);



		JLabel lblEmail = new JLabel("UserName:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEmail.setBounds(51, 42, 116, 20);
		frmSignIn.getContentPane().add(lblEmail);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(61, 109, 106, 26);
		frmSignIn.getContentPane().add(lblPassword);

		passwordFieldPass = new JPasswordField();
		passwordFieldPass.setFont(new Font("Tahoma", Font.PLAIN, 22));
		passwordFieldPass.setEchoChar('*');
		passwordFieldPass.setBounds(182, 106, 236, 35);
		frmSignIn.getContentPane().add(passwordFieldPass);

		JButton btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCreateNewAccount.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frmSignIn.dispose();
				NewAccount x = new NewAccount();
				x.setVisible(true);
			}
		});
		btnCreateNewAccount.setBounds(182, 252, 248, 50);
		frmSignIn.getContentPane().add(btnCreateNewAccount);


	}
}
