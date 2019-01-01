import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Reply extends JFrame {

	private JPanel contentPane;
	static JTextField textTo;
	static JTextArea textArea;
	static JTextField textFieldsbj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reply frame = new Reply();
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
	public Reply() {
		setTitle("Reply");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocation(650, 250);
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTo.setBounds(57, 16, 49, 25);
		contentPane.add(lblTo);

		textTo = new JTextField();
		textTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textTo.setBounds(151, 17, 214, 25);
		contentPane.add(textTo);
		textTo.setColumns(10);

		//			textTo.setText(Window.textFieldFrom.getText());




		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lists = textTo.getText().toString().toLowerCase();
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
								pst2.setString(3, textFieldsbj.getText());
								pst2.setString(4, textArea.getText());
								pst2.setString(5,x);
								pst2.execute();
								pst2.close();

								String query4 = "insert into "+ sender +"(idd,emailTo,subject,message,dateout) values (?,?,?,?,?)";


								pst3 = SignIn.connection.prepareStatement(query4);
								pst3.setInt(1, n);
								pst3.setString(2, toEmail );
								pst3.setString(3, textFieldsbj.getText());
								pst3.setString(4,textArea.getText() );
								pst3.setString(5, x);
								pst3.execute();
								pst3.close();

								int cs =emaillists.length;
								if(i== cs-1) {
									Reply.super.setVisible(false);
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

				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please provide valid email address:");
				}


				//				








			}

		});
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnSend.setBounds(176, 318, 182, 43);
		contentPane.add(btnSend);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(101, 94, 611, 208);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
	
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);
		textArea.setText(Window.textArea.getText());

		//		textArea.setText(Window.textArea.getText());

		JButton btdraftreply = new JButton("Save as Draft");
		btdraftreply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String Draftreciver;
					String draftEml;

					String lists = textTo.getText().toString().toLowerCase();
					//					System.out.println(lists);

					//					String[] emaillists = lists.split(",");
					//					for(int i = 0; i<emaillists.length;i ++) {


					//						String DraftEmail = emaillists[i];
					//						int cutDraft = DraftEmail.indexOf("@");		
					//						if(!(emaillists.length==0)) {
					//							Draftreciver = DraftEmail.substring(0, cutDraft)+"DraftBox";
					//							draftEml = DraftEmail;
					//						}
					//						else {
					Draftreciver = " ";
					draftEml=" ";
					//						}


					String Draftsender =SignIn.textFieldEmail.getText().toLowerCase()+SignIn.domain;


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
					pst4.setString(3, textFieldsbj.getText());
					pst4.setString(4, textArea.getText());
					pst4.setString(5, xq);

					pst4.execute();
					pst4.close();

					Reply.super.setVisible(false);
					JOptionPane.showMessageDialog(null,"Message saved as a draft:");
					//				}

				}catch (Exception e1) {
					e1.printStackTrace();
				}
				
//				try {
//					String Draftreciver;
//					String draftEml;
//
//					String lists = textTo.getText().toString().toLowerCase();
//					
//					Draftreciver = " ";
//					draftEml=" ";
//					//						}
//
//					String Draftsender = SignIn.textFieldEmail.getText().toLowerCase()+SignIn.domain;
//
//					int cutDraftsender = Draftsender.indexOf("@");			
//					String Draftsender1 = Draftsender.substring(0, cutDraftsender)+Draftsender.substring(cutDraftsender+1,Draftsender.indexOf("."))+"DraftBox";
//
//
//
//					Random rand = new Random();
//
//					//				int  n = rand.nextInt(1000) + 1;
//					int y =rand.nextInt(30000)+2001;
//
//					DateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd");
//					Date date = new Date();
//					String xq =  dateFormat.format(date);
//
//					int selectedroww = Account.table.getSelectedRow();
//					String xid = Account.table.getModel().getValueAt(selectedroww, 0).toString();
////					System.out.println(xid);
//
//					PreparedStatement pst4,pst5;
//
//
////					String query5 = "Update "+ Draftsender1 +" set idq='"+y+"' ,Draftto='"+ lists +"' ,subject='"+ textFieldsbj.getText() +"' ,message='"+ textArea.getText() +"' ,dateDraft='"+ xq +"' where idq='"+xid+"'  ";
//
//
//					pst4 = SignIn.connection.prepareStatement(query5);
//					pst4.execute();
//					pst4.close();
//					Reply.super.setVisible(false);
//					JOptionPane.showMessageDialog(null,"Draft Saved:");
////					//				}
////
//				}catch (Exception e1) {
//					String x = "Draft can not be updated:";
//
//
//					JOptionPane.showMessageDialog(null,x);
//				}

			}
		});
		btdraftreply.setFont(new Font("Tahoma", Font.BOLD, 19));
		btdraftreply.setBounds(415, 318, 182, 43);
		contentPane.add(btdraftreply);

		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSubject.setBounds(56, 57, 84, 25);
		contentPane.add(lblSubject);

		textFieldsbj = new JTextField();
		textFieldsbj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldsbj.setColumns(10);
		textFieldsbj.setBounds(151, 53, 214, 25);
		contentPane.add(textFieldsbj);
	}
}
