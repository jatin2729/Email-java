import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class DraftSend extends JFrame {

	private JPanel contentPane;
	static JTextField textFieldtooo;
	static JTextField textField_1_sbj;
	static JTextArea textAreadraft;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DraftSend frame = new DraftSend();
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
	public DraftSend() {
		setTitle("Draft Sender");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		setLocation(650, 250);

		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTo.setBounds(27, 17, 69, 20);
		contentPane.add(lblTo);

		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSubject.setBounds(27, 61, 84, 20);
		contentPane.add(lblSubject);

		textFieldtooo = new JTextField();
		textFieldtooo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldtooo.setBounds(118, 16, 309, 26);
		contentPane.add(textFieldtooo);
		textFieldtooo.setColumns(10);

		textField_1_sbj = new JTextField();
		textField_1_sbj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1_sbj.setColumns(10);
		textField_1_sbj.setBounds(118, 60, 309, 26);
		contentPane.add(textField_1_sbj);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(118, 135, 667, 272);
		contentPane.add(scrollPane);


		textAreadraft = new JTextArea();
		textAreadraft.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(textAreadraft);
		textAreadraft.setLineWrap(true);

		JButton btnNewButton = new JButton("send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String lists = textFieldtooo.getText().toString().toLowerCase();
				//				System.out.println(lists);

				String[] emaillists = lists.split(",");
				//				System.out.print(lists);

				//				System.out.println(emaillists.length);
				Random rand = new Random();





				////


				try {

					try {
						for(int i = 0; i<emaillists.length; i++) {

							int  n = rand.nextInt(1000) + 1;
							int y =rand.nextInt(2000)+1001;
							PreparedStatement pst2,pst3;



							String query1 = "select * from email where emailId=?";
							PreparedStatement pstEmail = SignIn.connection.prepareStatement(query1);
							pstEmail.setString(1,emaillists[i]);
							ResultSet rsEmail = pstEmail.executeQuery();

							int count = 0;
							while(rsEmail.next()) {//boolean value checks value in table one by one
								count = count +1;				

							}
							if ( count == 1) {




								String toEmail = emaillists[i];
								int cut = toEmail.indexOf("@");			
								String userToSend = toEmail.substring(0, cut)+toEmail.substring(cut+1,toEmail.indexOf("."))+"inbox";


								String senderEmail = SignIn.textFieldEmail.getText().toLowerCase()+SignIn.domain;
								int cutsender = senderEmail.indexOf("@");			
								String sender = senderEmail.substring(0, cutsender)+senderEmail.substring(cutsender+1,senderEmail.indexOf("."))+"Outbox";


								DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								Date date = new Date();
								String x =  dateFormat.format(date);



								String query3 = "insert into "+ userToSend +"(id,fromemail,subject,incoming,date) values (?,?,?,?,?)";
								pst2 = SignIn.connection.prepareStatement(query3);
								pst2.setInt(1,y);
								pst2.setString(2,senderEmail );
								pst2.setString(3, textField_1_sbj.getText());
								pst2.setString(4, textAreadraft.getText());
								pst2.setString(5,x);
								pst2.execute();
								pst2.close();

								String query4 = "insert into "+ sender +"(idd,emailTo,subject,message,dateout) values (?,?,?,?,?)";


								pst3 = SignIn.connection.prepareStatement(query4);
								pst3.setInt(1, n);
								pst3.setString(2, toEmail );
								pst3.setString(3, textField_1_sbj.getText());
								pst3.setString(4,textAreadraft.getText() );
								pst3.setString(5, x);
								pst3.execute();
								pst3.close();

								int cs =emaillists.length;
								if(i== cs-1) {
									DraftSend.super.setVisible(false);
								}


								JOptionPane.showMessageDialog(null,"Message sent for:"+toEmail);
								if(i == 0) {


									Account.btnNewButton.doClick();

								}

								count--;



							}

							else {
								JOptionPane.showMessageDialog(null,"Email is not correct:" +emaillists[i]);
							}

						}


					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Message not sent:");

					}

				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please provide valid email address:");
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(216, 455, 184, 59);
		contentPane.add(btnNewButton);

		JButton btnSaveAsDraft = new JButton("Update Draft");
		btnSaveAsDraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String Draftreciver;
					String draftEml;

					String lists = textFieldtooo.getText().toString().toLowerCase();

					Draftreciver = " ";
					draftEml=" ";
					//						}

					String Draftsender = SignIn.textFieldEmail.getText().toLowerCase()+SignIn.domain;

					int cutDraftsender = Draftsender.indexOf("@");			
					String Draftsender1 = Draftsender.substring(0, cutDraftsender)+Draftsender.substring(cutDraftsender+1,Draftsender.indexOf("."))+"DraftBox";



					Random rand = new Random();

					//				int  n = rand.nextInt(1000) + 1;
					int y =rand.nextInt(30000)+2001;

					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					String xq =  dateFormat.format(date);

					int selectedroww = Account.table.getSelectedRow();
					String xid = Account.table.getModel().getValueAt(selectedroww, 0).toString();
					//					System.out.println(xid);

					PreparedStatement pst4,pst5;


					String query5 = "Update "+ Draftsender1 +" set idq='"+y+"' ,Draftto='"+ lists +"' ,subject='"+ textField_1_sbj.getText() +"' ,message='"+ textAreadraft.getText() +"' ,dateDraft='"+ xq +"' where idq='"+xid+"'  ";


					pst4 = SignIn.connection.prepareStatement(query5);
					pst4.execute();
					pst4.close();
					DraftSend.super.setVisible(false);
					JOptionPane.showMessageDialog(null,"Draft Updated:");
					//				}

				}catch (Exception e1) {
					String x = "Draft can not be updated:";


					JOptionPane.showMessageDialog(null,x);
				}
			}
		});
		btnSaveAsDraft.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnSaveAsDraft.setBounds(493, 455, 198, 59);
		contentPane.add(btnSaveAsDraft);

		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMessage.setBounds(27, 135, 94, 26);
		contentPane.add(lblMessage);

		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOr.setBounds(428, 470, 69, 26);
		contentPane.add(lblOr);



	}
}
