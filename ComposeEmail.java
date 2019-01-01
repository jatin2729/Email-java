import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton.ToggleButtonModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class ComposeEmail extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTo;
	private JTextField textFieldSubject;
	static JTextField textFieldFrom;
	static String x1;
	static JTextArea textAreaCompose;
	static String toEmail;
	static JButton btnDraft ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComposeEmail frame = new ComposeEmail();
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
	public ComposeEmail() {
		setTitle("Compose New Email");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 722, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocation(650, 250);

		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTo.setBounds(40, 67, 69, 20);
		contentPane.add(lblTo);

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubject.setBounds(40, 103, 69, 20);
		contentPane.add(lblSubject);



		textFieldTo = new JTextField();
		textFieldTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldTo.setBounds(107, 64, 464, 26);
		contentPane.add(textFieldTo);
		textFieldTo.setColumns(10);

		textFieldSubject = new JTextField();
		textFieldSubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldSubject.setBounds(107, 103, 464, 26);
		contentPane.add(textFieldSubject);
		textFieldSubject.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 134, 545, 408);
		contentPane.add(scrollPane);

		textAreaCompose = new JTextArea();
		textAreaCompose.setLineWrap(true);
		scrollPane.setViewportView(textAreaCompose);
		textAreaCompose.setRows(55);
		textAreaCompose.setTabSize(55);
		textAreaCompose.setFont(new Font("Monospaced", Font.PLAIN, 20));

		JLabel lblFrom = new JLabel("From:");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFrom.setBounds(40, 31, 69, 20);
		contentPane.add(lblFrom);

		textFieldFrom = new JTextField();
		textFieldFrom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldFrom.setEditable(false);
		textFieldFrom.setColumns(10);
		textFieldFrom.setBounds(107, 25, 305, 26);

		contentPane.add(textFieldFrom);
		x1 = SignIn.textFieldEmail.getText().toLowerCase()+ SignIn.domain;

		//		String xxxx = textFieldEmail.getText().toLowerCase()
		textFieldFrom.setText(x1);

		JButton btnSendEmail = new JButton("Send");
		btnSendEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				String lists = textFieldTo.getText().toString().toLowerCase();
				//				System.out.println(lists);

				String[] emaillists = lists.split(",");

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




								toEmail = emaillists[i];
								int cut = toEmail.indexOf("@");
								int cutt = toEmail.indexOf(".");
								String dmn2 = toEmail.substring(cut+1, cutt);

								String userToSend = toEmail.substring(0, cut)+dmn2+"inbox";
								//								System.out.println(userToSend);

								String senderEmail = textFieldFrom.getText().toLowerCase();
								int cutsender = senderEmail.indexOf("@");			

								int cutt1 = senderEmail.indexOf(".");
								String dmn3 = textFieldFrom.getText().substring(cutsender+1, cutt1);
								//								System.out.println(dmn3);

								String sender = senderEmail.substring(0, cutsender)+dmn3+"Outbox";
								//								System.out.println(sender);


								DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								Date date = new Date();
								String x =  dateFormat.format(date);



								String query3 = "insert into "+ userToSend +"(id,fromemail,subject,incoming,date) values (?,?,?,?,?)";
								pst2 = SignIn.connection.prepareStatement(query3);
								pst2.setInt(1,y);
								pst2.setString(2,senderEmail );
								pst2.setString(3, textFieldSubject.getText());
								pst2.setString(4, textAreaCompose.getText());
								pst2.setString(5,x);
								pst2.execute();
								pst2.close();

								String query4 = "insert into "+ sender +"(idd,emailTo,subject,message,dateout) values (?,?,?,?,?)";


								pst3 = SignIn.connection.prepareStatement(query4);
								pst3.setInt(1, n);
								pst3.setString(2, toEmail );

								pst3.setString(3, textFieldSubject.getText());
								pst3.setString(4,textAreaCompose.getText() );
								pst3.setString(5, x);
								pst3.execute();
								pst3.close();

								int cs =emaillists.length;
								if(i== cs-1) {
									ComposeEmail.super.setVisible(false);
								}


								JOptionPane.showMessageDialog(null,"Message sent for:"+toEmail);
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

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please provide valid email address:");
				}





				//				








			}

		});
		btnSendEmail.setBounds(158, 558, 150, 40);
		contentPane.add(btnSendEmail);

		btnDraft = new JButton("Save as Draft");
		btnDraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String Draftreciver;
					String draftEml;

					String lists = textFieldTo.getText().toString().toLowerCase();

					Draftreciver = " ";
					draftEml=" ";



					String Draftsender = textFieldFrom.getText().toLowerCase();


					int cutDraftsender = Draftsender.indexOf("@");		
					int cutDD = Draftsender.indexOf(".");
					String qwe = Draftsender.substring(cutDraftsender+1, cutDD);
					String Draftsender1 = Draftsender.substring(0, cutDraftsender)+qwe+"DraftBox";
					//						System.out.println(Draftsender1);


					Random rand = new Random();

					//				int  n = rand.nextInt(1000) + 1;
					int y =rand.nextInt(3000)+2001;

					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					String xq =  dateFormat.format(date);


					PreparedStatement pst4;
					String query5 = "insert into "+ Draftsender1 +"(idq,Draftto,subject,message,dateDraft) values (?,?,?,?,?)";
					pst4 = SignIn.connection.prepareStatement(query5);
					pst4.setInt(1,y);
					pst4.setString(2,lists );
					pst4.setString(3, textFieldSubject.getText());
					pst4.setString(4, textAreaCompose.getText());
					pst4.setString(5, xq);

					pst4.execute();
					pst4.close();

					ComposeEmail.super.setVisible(false);
					JOptionPane.showMessageDialog(null,"Message saved as a draft:");
					//				}

				}catch (Exception e1) {
					e1.printStackTrace();
				}



			}
		});
		btnDraft.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDraft.setBounds(390, 559, 150, 40);
		contentPane.add(btnDraft);

		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(341, 570, 69, 20);
		contentPane.add(lblOr);


	}
}
