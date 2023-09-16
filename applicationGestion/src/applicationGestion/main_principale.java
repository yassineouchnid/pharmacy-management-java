package applicationGestion;

import javax.swing.JFrame;
import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class main_principale {

	private JFrame frame;
	private JTextField txtnomproduct;
	private JTextField txtpriceunit;
	private JTextField txttotal;
	private JTextField txtpayer;
	private JTextField txtreste;
	private JTable tablevent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_principale window = new main_principale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField nomClient;
	public main_principale() {
		initialize();
		table();
	}

	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gestionfirst", "root", "");
			System.out.println("Done");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void table() {
		try {
			connect();
			String[] coluName = {"Code", "Le Nom et Prénom","Nom", "Prix", "Qte", "Total", "Payer", "Reste"};
			String[] ligne = new String[8];
			DefaultTableModel model = new DefaultTableModel(null, coluName);
			String sql = "SELECT * FROM pharmaci_vende_gestion";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ligne[0] = rs.getString("code");
				ligne[1] = rs.getString("nomdeClient");
				ligne[2] = rs.getString("nom");
				ligne[3] = rs.getString("prix");
				ligne[4] = rs.getString("quantite");
				ligne[5] = rs.getString("total");
				ligne[6] = rs.getString("payer");
				ligne[7] = rs.getString("reste");				
				model.addRow(ligne);
			}
			tablevent.setModel(model);
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 793, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(10, 11, 757, 62);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION DE PHARMACIE");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 737, 40);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 28));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 95, 757, 258);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(10, 11, 737, 32);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Le Nouveau Processus de Vente");
		lblNewLabel_1.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 0, 717, 21);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nom de Produit");
		lblNewLabel_2.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 91, 125, 26);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Prix Par Unit");
		lblNewLabel_2_2.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(10, 128, 125, 26);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Quantité");
		lblNewLabel_2_2_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(10, 165, 125, 26);
		panel_1.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Total");
		lblNewLabel_2_1_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(415, 54, 43, 26);
		panel_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Payer");
		lblNewLabel_2_1_1_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1.setBounds(415, 91, 43, 26);
		panel_1.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Reste");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBounds(415, 128, 43, 26);
		panel_1.add(lblNewLabel_2_1_1_1_1);
		
		txtnomproduct = new JTextField();
		txtnomproduct.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		txtnomproduct.setColumns(10);
		txtnomproduct.setBounds(127, 91, 194, 26);
		panel_1.add(txtnomproduct);
		
		txtpriceunit = new JTextField();
		txtpriceunit.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		txtpriceunit.setColumns(10);
		txtpriceunit.setBounds(127, 128, 194, 26);
		panel_1.add(txtpriceunit);
		
		txttotal = new JTextField();
		txttotal.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal.setForeground(Color.WHITE);
		txttotal.setBackground(new Color(0, 0, 0));
		txttotal.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		txttotal.setColumns(10);
		txttotal.setBounds(483, 54, 194, 26);
		panel_1.add(txttotal);
		
		txtpayer = new JTextField();
		txtpayer.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int total = Integer.parseInt(txttotal.getText());
				int payer = Integer.parseInt(txtpayer.getText());
				int reste = total - payer;
				txtreste.setText(String.valueOf(reste));
			}
		});
		txtpayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtpayer.setForeground(new Color(0, 0, 0));
		txtpayer.setBackground(new Color(255, 255, 255));
		txtpayer.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		txtpayer.setColumns(10);
		txtpayer.setBounds(483, 94, 194, 26);
		panel_1.add(txtpayer);
		
		txtreste = new JTextField();
		txtreste.setHorizontalAlignment(SwingConstants.CENTER);
		txtreste.setForeground(Color.WHITE);
		txtreste.setBackground(Color.BLACK);
		txtreste.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		txtreste.setColumns(10);
		txtreste.setBounds(483, 131, 194, 26);
		panel_1.add(txtreste);
		
		final JSpinner txtqunt = new JSpinner();
		txtqunt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int prix = Integer.parseInt(txtpriceunit.getText());
				int qunt = Integer.parseInt(txtqunt.getValue().toString());
				int total = prix * qunt;
				txttotal.setText(String.valueOf(total));
			}
		});
		txtqunt.setBounds(127, 165, 194, 26);
		panel_1.add(txtqunt);
		
		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connect();
					pst = con.prepareStatement("INSERT INTO pharmaci_vende_gestion (nom, prix, quantite, total, payer, reste, nomdeClient) VALUES(?,?,?,?,?,?,?)");
					
					pst.setString(1, txtnomproduct.getText());
					pst.setString(2, txtpriceunit.getText() + "MAD");
					pst.setString(3, txtqunt.getValue().toString());
					pst.setString(4, txttotal.getText() + "MAD");
					pst.setString(5, txtpayer.getText() + "MAD");
					pst.setString(6, txtreste.getText() + "MAD");
					pst.setString(7, nomClient.getText());
					pst.executeUpdate();
					con.close();
					JOptionPane.showMessageDialog(null, txtnomproduct.getText() + " Ajouter");
					table();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16));
		btnNewButton.setBackground(new Color(94, 124, 52));
		btnNewButton.setBounds(296, 209, 169, 32);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("Le Nom et Prénom");
		lblNewLabel_2_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 54, 125, 26);
		panel_1.add(lblNewLabel_2_1);
		
		nomClient = new JTextField();
		nomClient.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		nomClient.setColumns(10);
		nomClient.setBounds(127, 54, 194, 26);
		panel_1.add(nomClient);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 364, 755, 196);
		frame.getContentPane().add(scrollPane_1);
		
		tablevent = new JTable();
		scrollPane_1.setViewportView(tablevent);
		tablevent.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		
		JButton button = new JButton("New button");
		scrollPane_1.setColumnHeaderView(button);
	}
}
