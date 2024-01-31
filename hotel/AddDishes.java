package hotel;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.table.DefaultTableModel;

public class AddDishes extends JFrame {

	private JPanel contentPane;
	private JTextField d1;
	private JTextField d2;
	private JTextField d3;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDishes frame = new AddDishes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddDishes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 972, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDishName = new JLabel("DISH NAME");
		lblDishName.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblDishName.setBounds(33, 211, 155, 22);
		contentPane.add(lblDishName);

		JLabel lblDishPrice = new JLabel("DISH PRICE");
		lblDishPrice.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblDishPrice.setBounds(33, 284, 155, 27);
		contentPane.add(lblDishPrice);

		JLabel lblDishType = new JLabel("DISH TYPE");
		lblDishType.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblDishType.setBounds(33, 353, 155, 27);
		contentPane.add(lblDishType);

		d1 = new JTextField();
		d1.setFont(new Font("High Tower Text", Font.BOLD, 20));
		d1.setBounds(182, 207, 232, 30);
		contentPane.add(d1);
		d1.setColumns(10);

		d2 = new JTextField();
		d2.setFont(new Font("High Tower Text", Font.BOLD, 20));
		d2.setBounds(182, 282, 232, 30);
		contentPane.add(d2);
		d2.setColumns(10);

		d3 = new JTextField();
		d3.setFont(new Font("High Tower Text", Font.BOLD, 20));
		d3.setBounds(182, 351, 232, 30);
		contentPane.add(d3);
		d3.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 194, 418, 279);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnAddDish = new JButton("ADD DISH");
		btnAddDish.setIcon(new ImageIcon("images\\plus (1).png"));
		btnAddDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDish();
			}
		});
		btnAddDish.setFont(new Font("High Tower Text", Font.BOLD, 20));
		btnAddDish.setBounds(45, 486, 176, 53);
		contentPane.add(btnAddDish);

		JButton btnDeleteDish = new JButton("DELETE DISH");
		btnDeleteDish.setIcon(new ImageIcon("images\\iconfinder_f-cross_256_282471 (1).png"));
		btnDeleteDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDish();
			}
		});
		btnDeleteDish.setFont(new Font("High Tower Text", Font.BOLD, 20));
		btnDeleteDish.setBounds(245, 486, 221, 53);
		contentPane.add(btnDeleteDish);

		JButton btnUpdateDish = new JButton("UPDATE DISH");
		btnUpdateDish.setIcon(new ImageIcon("images\\updated.png"));
		btnUpdateDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDish();
			}
		});
		btnUpdateDish.setFont(new Font("High Tower Text", Font.BOLD, 20));
		btnUpdateDish.setBounds(502, 486, 221, 53);
		contentPane.add(btnUpdateDish);

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
		btnBack.setBounds(765, 486, 143, 53);
		contentPane.add(btnBack);

		// Additional JLabel components

		// Invoke the method to display dishes initially
		displayDishes();
	}

	public void displayDishes() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Dish Name");
		model.addColumn("Dish Price");
		model.addColumn("Dish Type");
//		model.addColumn("New");
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM restaurant");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("itemName"),
						rs.getString("Price"),
						rs.getString("Type")
				});
			}

			rs.close();
			ps.close();
			connection.close();

			table.setModel(model);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(200);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addDish() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO restaurant( itemName, Price, Type) VALUES (?, ?, ?)");

			preparedStatement.setString(1, d1.getText()); // itemNo
			preparedStatement.setString(2, d2.getText()); // itemName
			preparedStatement.setString(3, d3.getText()); // Price

			if (preparedStatement.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "New Dish Added");
			}

			preparedStatement.close();
			con.close();
//			new AddDishes();



		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateDish() {
		try {
			int selectedRowIndex = table.getSelectedRow();
			if (selectedRowIndex != -1) { // Check if a row is selected
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String itemNo = model.getValueAt(selectedRowIndex, 0).toString();

				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
				PreparedStatement ps = conn.prepareStatement("UPDATE restaurant SET itemName=?, Price=?, Type=? WHERE itemName=?");

				ps.setString(1, d1.getText()); // itemName
				ps.setString(2, d2.getText()); // Price
				ps.setString(3, d3.getText()); // Type
				ps.setString(4, itemNo); // itemNo

				int updatedRows = ps.executeUpdate();
				if (updatedRows > 0) {
					JOptionPane.showMessageDialog(null, "Dish Updated");
					// Refresh the table after updating
					displayDishes();
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



	public void deleteDish() {
		try {
			int selectedRowIndex = table.getSelectedRow();
			if (selectedRowIndex != -1) { // Check if a row is selected
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String itemNo = model.getValueAt(selectedRowIndex, 0).toString();

				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
				PreparedStatement ps = conn.prepareStatement("DELETE FROM restaurant WHERE itemName=?");

				ps.setString(1, itemNo); // itemNo

				int deletedRows = ps.executeUpdate();
				if (deletedRows > 0) {
					JOptionPane.showMessageDialog(null, "Dish Deleted");
					// Refresh the table after deleting
					displayDishes();
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

}
