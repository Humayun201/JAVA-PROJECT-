package hotel;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.ImageIcon;

public class AddRooms extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField r1;
	private JTextField r2;
	private JTextField r3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRooms frame = new AddRooms();
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
	public AddRooms() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				displayRooms();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 965, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddRooms = new JButton("ADD ROOMS");
		btnAddRooms.setIcon(new ImageIcon("images\\plus (1).png"));
		btnAddRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRooms();

			}
		});
		btnAddRooms.setFont(new Font("High Tower Text", Font.BOLD, 23));
		btnAddRooms.setBounds(12, 456, 225, 50);
		contentPane.add(btnAddRooms);

		JButton btnAddDish = new JButton("UPDATE ROOMS");
		btnAddDish.setIcon(new ImageIcon("images\\updated.png"));
		btnAddDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRooms();
			}
		});
		btnAddDish.setFont(new Font("High Tower Text", Font.BOLD, 23));
		btnAddDish.setBounds(518, 456, 270, 50);
		contentPane.add(btnAddDish);

		JButton btnDleteRooms = new JButton("DELETE ROOMS");
		btnDleteRooms.setIcon(new ImageIcon("images\\iconfinder_f-cross_256_282471 (1).png"));
		btnDleteRooms.setFont(new Font("High Tower Text", Font.BOLD, 23));
		btnDleteRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRooms();
			}
		});
		btnDleteRooms.setBounds(248, 457, 258, 49);
		contentPane.add(btnDleteRooms);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 201, 431, 242);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblRoomType = new JLabel("ROOM TYPE");
		lblRoomType.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblRoomType.setBounds(12, 216, 136, 38);
		contentPane.add(lblRoomType);

		JLabel lblBedType = new JLabel("BED TYPE");
		lblBedType.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblBedType.setBounds(12, 289, 136, 25);
		contentPane.add(lblBedType);

		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblPrice.setBounds(23, 345, 107, 27);
		contentPane.add(lblPrice);

		r1 = new JTextField();
		r1.setFont(new Font("High Tower Text", Font.BOLD, 20));
		r1.setBounds(160, 220, 147, 30);
		contentPane.add(r1);
		r1.setColumns(10);

		r2 = new JTextField();
		r2.setFont(new Font("High Tower Text", Font.BOLD, 20));
		r2.setBounds(160, 286, 146, 30);
		contentPane.add(r2);
		r2.setColumns(10);

		r3 = new JTextField();
		r3.setFont(new Font("High Tower Text", Font.BOLD, 20));
		r3.setBounds(160, 343, 147, 30);
		contentPane.add(r3);
		r3.setColumns(10);

		JButton btnBack = new JButton("BACK");
		btnBack.setIcon(new ImageIcon("images\\back.png"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminForm af = new AdminForm();
				af.setVisible(true);
				af.pack();
				af.setLocationRelativeTo(null);
				af.setBounds(100, 100, 1080, 633);
				setVisible(false);
			}
		});
		btnBack.setFont(new Font("High Tower Text", Font.BOLD, 20));
		btnBack.setBounds(800, 457, 136, 50);
		contentPane.add(btnBack);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("images\\collage.jpg"));
		lblNewLabel.setBounds(0, 0, 947, 202);
		contentPane.add(lblNewLabel);
	}

	public void displayRooms()
	{

//		GetConnection connect=new GetConnection();
//		Connection conn=connect.getConnection();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ROOM NO");
		model.addColumn("ROOM TYPE");
		model.addColumn("BED TYPE");
		model.addColumn("PRICE");

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
			String query = "SELECT * FROM room";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			Statement st= connection.createStatement();
			ResultSet rs= st.executeQuery(query);
			while(rs.next())
			{
				model.addRow(new Object[] {
						rs.getString("roomNo"),
						rs.getString("roomType"),
						rs.getString("bedType"),
						rs.getString("Price")
				});
			}

			rs.close();
			st.close();
			connection.close();
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(80);
			table.getColumnModel().getColumn(1).setPreferredWidth(140);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(90);


		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void addRooms() {
		try {
			Random random = new Random();
			int roomNumber = random.nextInt(1000) + 1; // Generates a random number between 1 and 1000

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO room(roomNo, roomType, bedType, Price) VALUES (?, ?, ?, ?)");

			preparedStatement.setString(1, String.valueOf(roomNumber)); // roomNo
			preparedStatement.setString(2, r1.getText()); // roomType
			preparedStatement.setString(3, r2.getText()); // bedType
			preparedStatement.setString(4, r3.getText()); // Price

			if (preparedStatement.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "New Room Added");
			}

			preparedStatement.close();
			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

//	public void updateRooms()
//	{
//		PreparedStatement ps = null;
//		ResultSet result = null;
//		try {
//			int i =table.getSelectedRow();
//			DefaultTableModel model = (DefaultTableModel)table.getModel();
//			String no= model.getValueAt(i, 0).toString();
//			System.out.println(no);
//			GetConnection connect=new GetConnection();
//			Connection conn=connect.getConnection();
//			ps=conn.prepareStatement("UPDATE room SET roomType=?, bedType= ?, Price=? where roomNo=?");
//			ps.setString(1,r1.getText());
//			ps.setString(2,r2.getText());
//			ps.setString(3,r3.getText());
//			ps.setString(4, no);
//
//
//			if(ps.executeUpdate()>0)
//			{
//				JOptionPane.showMessageDialog(null, " Room Updated ");
//			}
//
//		}
//
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}

	public void updateRooms() {
		try {
			int selectedRowIndex = table.getSelectedRow();
			if (selectedRowIndex != -1) { // Check if a row is selected
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String itemNo = model.getValueAt(selectedRowIndex, 0).toString();

				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
				PreparedStatement ps = conn.prepareStatement("UPDATE room SET roomType=?, bedType=?, Price=? WHERE roomNo=?");

				ps.setString(1, r1.getText()); // itemName
				ps.setString(2, r2.getText()); // Price
				ps.setString(3, r3.getText()); // Type
				ps.setString(4, itemNo); // itemNo

				int updatedRows = ps.executeUpdate();
				if (updatedRows > 0) {
					JOptionPane.showMessageDialog(null, "Room Updated");
					// Refresh the table after updating
					displayRooms();
				}

				ps.close();
				conn.close();
			} else {
				JOptionPane.showMessageDialog(null, "Please select a row to update.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteRooms() {
		try {
			int selectedRowIndex = table.getSelectedRow();
			if (selectedRowIndex != -1) { // Check if a row is selected
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String Room = model.getValueAt(selectedRowIndex, 0).toString();

				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
				PreparedStatement ps = conn.prepareStatement("DELETE FROM room WHERE roomNo=?");

				ps.setString(1, Room); // itemNo

				int deletedRows = ps.executeUpdate();
				if (deletedRows > 0) {
					JOptionPane.showMessageDialog(null, "Room Deleted");
					// Refresh the table after deleting
					displayRooms();
				}

				ps.close();
				conn.close();
			} else {
				JOptionPane.showMessageDialog(null, "Please select a row to delete.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public void checkOut()
	{

	}


}
