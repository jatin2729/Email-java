import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Window extends JFrame {

	private JPanel contentPane;
	static JTextField textFieldFrom;
	static JTextArea textArea;
	private JLabel labelDate;
	static JTextField textFieldDate;
	
	static JButton btnReply;
	static JButton btnForward;


	private JScrollPane scrollPane;
	static JLabel lblFrom;
	static JTextField textFieldSub;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
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
	public Window() {
		setTitle("Preview Screen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 784, 504);
		setLocation(650, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);









		lblFrom = new JLabel(Account.lbl);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFrom.setBounds(51, 46, 105, 25);
		contentPane.add(lblFrom);

		textFieldFrom = new JTextField();
		textFieldFrom.setEditable(false);
		textFieldFrom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldFrom.setBounds(152, 45, 286, 28);
		contentPane.add(textFieldFrom);
		textFieldFrom.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 128, 639, 231);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setEditable(false);

		labelDate = new JLabel("Date:");
		labelDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelDate.setBounds(444, 87, 63, 25);
		contentPane.add(labelDate);

		textFieldDate = new JTextField();
		textFieldDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldDate.setEditable(false);
		textFieldDate.setBounds(508, 88, 192, 26);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);

		btnReply = new JButton("Reply");
		btnReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reply window1 = new Reply();
				window1.setVisible(true);
				Window.super.setVisible(false);



				//				if(Account.btnDrafts.)) {
				//					System.out.println("worked");
				//					
				//				}
				//				else {
				//					System.out.println("didnot work");
				//				}
//				String oi = "/n"+textFieldFrom.getText();
				Reply.textTo.setText(textFieldFrom.getText());
				Reply.textFieldsbj.setText("RE: "+textFieldSub.getText());
				window1.textArea.setText("\n"+Reply.textArea.getText());
				/*
				 * 
				 * 
				 * 
				 * 
				 * DWLETE ABOVE LINE IF SOMETHING WRONG HAPPENS
				 * 
				 * 
				 * 
				 * 
				 * 
				 */


			}
		});
		btnReply.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReply.setBounds(138, 375, 169, 42);
		contentPane.add(btnReply);

		btnForward = new JButton("Forward");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reply window = new Reply();
				Window.super.setVisible(false);
				window.setVisible(true);
				window.textFieldsbj.setText("FWD:"+textFieldSub.getText());
				window.textFieldsbj.setEditable(false);
				String rty = ("\n"+"\n"+"------Forwarded Message------\n"+
						"FROM: "+textFieldFrom.getText()+
						"\nDate: "+textFieldDate.getText()+
						"\nSubject: " + textFieldSub.getText()+
						"\nTo: "+SignIn.textFieldEmail.getText()+SignIn.domain+
						"\n"+"\n"+
						Reply.textArea.getText());

				window.textArea.setText(rty);
			}
		});
		btnForward.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnForward.setBounds(419, 375, 169, 42);
		contentPane.add(btnForward);

		JLabel labelSub = new JLabel("Subject:");
		labelSub.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelSub.setBounds(51, 92, 105, 25);
		contentPane.add(labelSub);

		textFieldSub = new JTextField();
		textFieldSub.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldSub.setEditable(false);
		textFieldSub.setColumns(10);
		textFieldSub.setBounds(152, 84, 286, 28);
		contentPane.add(textFieldSub);
	}
}
