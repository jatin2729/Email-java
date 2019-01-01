import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class Account extends JFrame {

	private JPanel contentPane;
	static String user;
	static String id;
	static String a, b, c, d, exxx;
	static String lbl;
	static String btnsnd;
	static String btnrepl;
	static JButton btnDrafts;
	static String qwer123;
	static JButton btnNewButton;
	boolean draft5;
	boolean trashactivated;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account frame = new Account();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	static JTable table;
	// static String d;

	/**
	 * Create the frame.
	 */
	public Account() {
		setTitle("Welcome To Your Account");
		connection = sqliteConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocation(650, 250);
		JButton btnInbox = new JButton("Inbox");
		btnInbox.setBounds(26, 85, 129, 42);
		btnInbox.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				draft5 = false;
				trashactivated = false;
				String qwer = SignIn.domain;
				int as = qwer.indexOf(".");
				String adf = qwer.substring(1, as);
				lbl = "From: ";

				// System.out.println(adf);

				String hhh = SignIn.textFieldEmail.getText() + adf + "inbox";
				// System.out.println(hhh);
				try {
					String query = "select * from " + hhh + " order by date DESC";
					// System.out.println(query);
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery(); // rs2xml.jar file need to be added in the project libararies
					table.setModel(DbUtils.resultSetToTableModel(rs));// helps to convert rs to table//dbutils is class
																		// inside rs2xl.jar

					table.setEnabled(true);

					Font myFont = new Font("Serif", Font.BOLD, 18);
					table.setFont(myFont);

				} catch (Exception e) {

				}

			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnInbox);

		JButton btnOutbox = new JButton("Outbox");
		btnOutbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				draft5 = false;
				trashactivated = false;
				String qwer1 = SignIn.domain;
				int as1 = qwer1.indexOf(".");
				String adf1 = qwer1.substring(1, as1);
				lbl = "To:";

				String out = SignIn.textFieldEmail.getText() + adf1 + "Outbox";

				try {
					String query = "select * from " + out + " order by dateout DESC";
					// System.out.println(query);
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery(); // rs2xml.jar file need to be added in the project libararies
					table.setModel(DbUtils.resultSetToTableModel(rs));// helps to convery rs to table//dbutils is class
																		// inside rs2xl.jar
					table.setEnabled(true);

					Font myFont = new Font("Serif", Font.BOLD, 18);
					table.setFont(myFont);

				} catch (Exception e) {

				}
			}
		});
		btnOutbox.setBounds(26, 136, 129, 42);
		btnOutbox.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.add(btnOutbox);

		btnDrafts = new JButton("Drafts");
		btnDrafts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draft5 = true;
				trashactivated = false;

				lbl = "To:";
				String qwer2 = SignIn.domain;
				int as2 = qwer2.indexOf(".");
				String adf2 = qwer2.substring(1, as2);
				// System.out.println(adf2);

				String draft = SignIn.textFieldEmail.getText() + adf2 + "DraftBox";
				// System.out.println(hhh);
				try {

					String query = "select * from " + draft + " order by dateDraft DESC";
					// System.out.println(query);
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery(); // rs2xml.jar file need to be added in the project libararies
					table.setModel(DbUtils.resultSetToTableModel(rs));// helps to convery rs to table//dbutils is class
																		// inside rs2xl.jar
					table.setEnabled(true);

					Font myFont = new Font("Serif", Font.BOLD, 18);
					table.setFont(myFont);

					// TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
					// table.setRowSorter(sorter);
					// table.setEnabled(false);
					table.setRowSelectionAllowed(true);

					// table.setFocusable(false);

					// List<RowSorter.SortKey> sortKeys = new ArrayList<>();

					// int columnIndexToSort = 4;
					// sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));

					// sorter.setSortKeys(sortKeys);
					// sorter.sort();

					//

				} catch (Exception e1) {

				}
			}
		});

		btnDrafts.setBounds(26, 181, 129, 42);
		btnDrafts.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.add(btnDrafts);

		JButton btnCompose = new JButton("COMPOSE");
		btnCompose.setBounds(26, 32, 129, 48);
		btnCompose.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCompose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComposeEmail x = new ComposeEmail();
				x.setVisible(true);
			}
		});
		contentPane.add(btnCompose);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setBounds(15, 305, 154, 54);
		btnSignOut.setBackground(new Color(255, 99, 71));
		btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.exit(1);
				// contentPane.setVisible(false);
				Account.super.setVisible(false);
				SignIn window = new SignIn();
				// window.setVisible(true);
				window.frmSignIn.setVisible(true);

			}
		});
		contentPane.add(btnSignOut);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(226, 51, 811, 303);
		contentPane.add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setRowHeight(20);
		// table.setEnabled(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));

		btnNewButton = new JButton("Remove Selected");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				try {
					// int selectedRow = table.getSelectedRow();
					int row = table.getSelectedRow();

					user = SignIn.textFieldEmail.getText() + SignIn.domain;
					int cutuser = user.indexOf("@");
					String user1 = user.substring(0, cutuser) + user.substring(cutuser + 1, user.indexOf("."))
							+ "Outbox";

					String user2 = user.substring(0, cutuser) + user.substring(cutuser + 1, user.indexOf("."))
							+ "inbox";
					String user3 = user.substring(0, cutuser) + user.substring(cutuser + 1, user.indexOf("."))
							+ "DraftBox";
					String trashh = user.substring(0, cutuser) + user.substring(cutuser + 1, user.indexOf("."))
							+ "Trash";

					id = table.getModel().getValueAt(row, 0).toString();
					String from = table.getModel().getValueAt(row, 1).toString();
					String message = table.getModel().getValueAt(row, 2).toString();
					String date = table.getModel().getValueAt(row, 3).toString();

					// a = table.getModel().getValueAt(row, 0).toString();
					b = table.getModel().getValueAt(row, 1).toString();
					c = table.getModel().getValueAt(row, 2).toString();
					d = table.getModel().getValueAt(row, 3).toString();
					exxx = table.getModel().getValueAt(row, 3).toString();

					Random rand = new Random();

					try {

						int n = rand.nextInt(1000) + 1;
						int y = rand.nextInt(2000) + 1001;
						PreparedStatement pst7;
						if (trashactivated == false) {

							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Date date1 = new Date();
							String x = dateFormat.format(date1);

							String query7 = "insert into " + trashh
									+ "(idz,fromTo,subject,message,dateTrash) values (?,?,?,?,?)";
							pst7 = SignIn.connection.prepareStatement(query7);

							pst7.setInt(1, y);
							pst7.setString(2, b);
							pst7.setString(3, c);
							pst7.setString(4, d);
							pst7.setString(5, x);
							pst7.execute();
							pst7.close();

						}

						else {
							PreparedStatement pst22;
							String query37 = "DELETE FROM " + trashh + " where idz=?";
							pst22 = SignIn.connection.prepareStatement(query37);
							pst22.setString(1, id);

							pst22.execute();

						}

						trashactivated = true;

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Message moved to Trash:");

					}

					try {
						PreparedStatement pst2;

						String query3 = "DELETE FROM " + user1 + " where idd=?";
						pst2 = SignIn.connection.prepareStatement(query3);
						pst2.setString(1, id);

						pst2.execute();

						String query4 = "DELETE FROM " + user2 + " where id=?";
						pst2 = SignIn.connection.prepareStatement(query4);
						pst2.setString(1, id);
						// pst2.setString(2,from );
						// pst2.setString(3, message);
						// pst2.setString(4,date);
						pst2.execute();

						String query5 = "DELETE FROM " + user3 + " where idq=?";
						pst2 = SignIn.connection.prepareStatement(query5);
						pst2.setString(1, id);
						// pst2.setString(2,from );
						// pst2.setString(3, message);
						// pst2.setString(4,date);
						pst2.execute();
						model.removeRow(row);

						// JOptionPane.showMessageDialog(null,"email deleted:");

					} catch (Exception ex) {
						//
						JOptionPane.showMessageDialog(null, e);
					}

				} catch (Exception e88) {
					JOptionPane.showMessageDialog(null, e88);
				}
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(824, 16, 213, 29);
		contentPane.add(btnNewButton);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				// if(SwingUtilities.isLeftMouseButton(e)) {

				int row = table.getSelectedRow();
				// System.out.println(row);

				String id = table.getModel().getValueAt(row, 0).toString();
				String from = table.getModel().getValueAt(row, 1).toString();
				String subject = table.getModel().getValueAt(row, 2).toString();
				String message = table.getModel().getValueAt(row, 3).toString();
				String date = table.getModel().getValueAt(row, 4).toString();

				if (draft5 == true) {
					DraftSend fff = new DraftSend();
					fff.setVisible(true);

					fff.textFieldtooo.setText(from);
					fff.textAreadraft.setText(message);
					fff.textField_1_sbj.setText(subject);
				} else {
					Window window = new Window();

					window.setVisible(true);

					window.textFieldFrom.setText(from);
					window.textFieldSub.setText(subject);
					window.textArea.setText(message);
					window.textFieldDate.setText(date);

					// DraftSend.textFieldtooo.setText(from);

				}
				// }
				// else {
				// int x = table.getSelectedRow();
				// System.out.println(x);
				// }

			}
		});

		try {
			String xqwe = SignIn.textFieldEmail.getText() + SignIn.domain;

			String qwer12 = SignIn.domain;
			int as12 = qwer12.indexOf(".");
			String adf12 = qwer12.substring(1, as12);
			lbl = "To:";

			String innn = SignIn.textFieldEmail.getText() + adf12 + "inbox";
			// System.out.println(innn);

			String query = "select * from " + innn + " order by date DESC";
			// System.out.println(query);
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery(); // rs2xml.jar file need to be added in the project libararies
			table.setModel(DbUtils.resultSetToTableModel(rs));// helps to convert rs to table//dbutils is class inside
																// rs2xl.jar

			table.setEnabled(true);

			Font myFont = new Font("Serif", Font.BOLD, 18);
			table.setFont(myFont);

			JButton btnTrash = new JButton("Trash");
			btnTrash.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					draft5 = false;
					trashactivated = true;
					String qwer = SignIn.domain;
					int as = qwer.indexOf(".");
					String adf = qwer.substring(1, as);
					lbl = "From: ";

					String hhh = SignIn.textFieldEmail.getText() + adf + "Trash";
					// System.out.println(hhh);
					try {
						String query99 = "select * from " + hhh + " order by dateTrash DESC";
						// System.out.println(query);
						PreparedStatement pst99 = connection.prepareStatement(query99);
						ResultSet rs1 = pst99.executeQuery(); // rs2xml.jar file need to be added in the project
																// libararies
						table.setModel(DbUtils.resultSetToTableModel(rs1));// helps to convert rs to table//dbutils is
																			// class inside rs2xl.jar

						table.setEnabled(true);

						Font myFont = new Font("Serif", Font.BOLD, 18);
						table.setFont(myFont);

					} catch (Exception e) {

					}

				}
			});
			btnTrash.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnTrash.setBounds(612, 17, 173, 29);
			contentPane.add(btnTrash);

		} catch (Exception e) {

		}
	}
}
