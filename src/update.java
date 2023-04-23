import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;

public class update extends JFrame {

	private JPanel contentPane;
	private JTextField txt;
	private JTextField txtmobile;
	private JTextField txtcourse;
	private JTable table;
	private JTable table_1;
	
	
	static JButton addButton = new JButton("Add");
	static JButton editButton = new JButton("Edit");
	static JButton deleteButton = new JButton("Delete");
	Connection con1;
	PreparedStatement insert;
	
	String add = "Record Added";
	String update = "Record Updated";
	String delete = "Record Deleted";

	public void dialog() {
		
		String ad = add;

		JOptionPane.showMessageDialog(this, ad);
		setFocusable(true);
		
	}
	public void dialogUP() {
		
		String up = update;
		
		JOptionPane.showMessageDialog(this, up);
		setFocusable(true);
		
	}
	public void dialogDel() {
		
		String del = delete;
		
		JOptionPane.showMessageDialog(this, del);
		setFocusable(true);
		
	}
	
	@SuppressWarnings("unchecked")
	public void update_table(){

		int c;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//database name
			con1 = DriverManager.getConnection("jdbc:mysql://localhost/management","root","");
			insert = con1.prepareStatement("select * from users");
			
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss = (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel Df = (DefaultTableModel) table_1.getModel();
			Df.setRowCount(0);
			
			while(rs.next()) {
				
				Vector v2 = new Vector();
				for(int a = 1; a<=c; a++) {
					v2.add(rs.getString("id"));
					v2.add(rs.getString("username"));
					v2.add(rs.getString("password"));
					v2.add(rs.getString("course"));
				}
				
				Df.addRow(v2);
				
			}
			
			
			
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		
		}
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update frame = new update();
					
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setResizable(false);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public update() {
		setTitle("Database Management System");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Yuri Kun\\Desktop\\test.jpg"));
		
		
		
	
		
		setLocationRelativeTo(null);
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 962, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//SET BACKGROUND
		contentPane.setBackground(new Color(150, 160, 223));
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Student Registration");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setBounds(25, 39, 118, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(25, 77, 118, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Course");
		lblNewLabel_3.setBounds(25, 119, 118, 14);
		panel.add(lblNewLabel_3);
		
		txt = new JTextField();
		txt.setBounds(127, 36, 178, 20);
		panel.add(txt);
		txt.setColumns(10);
		
		txtmobile = new JTextField();
		txtmobile.setBounds(127, 74, 178, 20);
		panel.add(txtmobile);
		txtmobile.setColumns(10);
		
		txtcourse = new JTextField();
		txtcourse.setBounds(127, 116, 178, 20);
		panel.add(txtcourse);
		txtcourse.setColumns(10);

		addButton.setFocusable(false);
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String username = txt.getText();
				String mobile= txtmobile.getText();
				String course = txtcourse.getText();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					
					//database name
					con1 = DriverManager.getConnection("jdbc:mysql://localhost/management","root","");
					insert = con1.prepareStatement("insert into users (username, password, course)values(?,?,?)");
					
					
					insert.setString(1, username);
					insert.setString(2, mobile);
					insert.setString(3, course);
					
					insert.executeUpdate();
					
					
					dialog();

					txt.setText("");
					txtmobile.setText("");
					txtcourse.setText("");
					txt.requestFocus();
					
					update_table();
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				
				}
			}
		});
		addButton.setBounds(126, 176, 70, 23);
		panel.add(addButton);
		
		editButton.setFocusable(false);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Df = (DefaultTableModel) table_1.getModel();
				int selectedIndex = table_1.getSelectedRow();
				
				
				

				try {
					
					
					int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
					
		
					String username = txt.getText();
					String password= txtmobile.getText();
					String course = txtcourse.getText();
					
					
					Class.forName("com.mysql.jdbc.Driver");
					//database name
					con1 = DriverManager.getConnection("jdbc:mysql://localhost/management","root","");
					insert = con1.prepareStatement("update users set username= ?,password=? ,course=? where id=? ");
					
					
					insert.setString(1, username);
					insert.setString(2, password);
					insert.setString(3, course);
					insert.setInt(4, id);
					
					insert.executeUpdate();
					
					 dialogUP();
					
					txt.setText("");
					txtmobile.setText("");
					txtcourse.setText("");
					txt.requestFocus();
					
					update_table();
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				
				}
			}
				
				
				
			
		});
		editButton.setBounds(206, 176, 70, 23);
		panel.add(editButton);
		
		deleteButton.setFocusable(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				DefaultTableModel Df = (DefaultTableModel) table_1.getModel();
				int selectedIndex = table_1.getSelectedRow();

				try {

					int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
					
					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete the Record? ", "Warning", JOptionPane.YES_NO_OPTION);
					
					
					if(dialogResult == JOptionPane.YES_NO_OPTION) {

						Class.forName("com.mysql.jdbc.Driver");
						
						//database name
						
						con1 = DriverManager.getConnection("jdbc:mysql://localhost/management","root","");
						insert = con1.prepareStatement("delete from users where id =? ");
	
						insert.setInt(1, id);
						
						insert.executeUpdate();
						
						 dialogDel();
						
						txt.setText("");
						txtmobile.setText("");
						txtcourse.setText("");
						txt.requestFocus();

					}

					update_table();

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				
				}

			}
		});
		deleteButton.setBounds(286, 176, 70, 23);
		panel.add(deleteButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(0, 0, 443, 216);
		panel_1.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Password", "Course"
			}
		));
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, panel);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel_1, 471, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel_1, 316, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel_1, -26, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 95, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 45, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 316, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 427, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 11, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 338, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 603, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		contentPane.add(lblNewLabel);
		contentPane.add(panel);
		contentPane.add(panel_1);
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("maxi.gif").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		//ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("maxi.gif").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		
		JLabel maxwell = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, maxwell, 39, SpringLayout.SOUTH, panel);
		sl_contentPane.putConstraint(SpringLayout.WEST, maxwell, 377, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, maxwell, -24, SpringLayout.SOUTH, contentPane);
		contentPane.add(maxwell);
		maxwell.setIcon(imageIcon);
		
//		JLabel mindset = new JLabel("");
//		sl_contentPane.putConstraint(SpringLayout.WEST, mindset, 101, SpringLayout.WEST, contentPane);
//		sl_contentPane.putConstraint(SpringLayout.SOUTH, mindset, 0, SpringLayout.SOUTH, maxwell);
//		mindset.setIcon(imageIcon2);
//		contentPane.add(mindset);
		
		
		
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel Df = (DefaultTableModel) table_1.getModel();
				int selectedIndex = table_1.getSelectedRow();
				
				txt.setText(Df.getValueAt(selectedIndex, 1).toString());
				txtmobile.setText(Df.getValueAt(selectedIndex, 2).toString());
				txtcourse.setText(Df.getValueAt(selectedIndex, 3).toString());
				
			}
		});
		
		update_table();
	}
}
