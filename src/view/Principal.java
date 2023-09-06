package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class Principal extends JFrame {
	
	DAO dao = new DAO();
	private Connection con;
	@SuppressWarnings("unused")
	private PreparedStatement pst;
	@SuppressWarnings("unused")
	private ResultSet rs;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatusz;
	private JLabel lblData;
	public JLabel lblNomeUsu;
	public JButton btnUsuarios;
	public JButton btnRelatorio;
	public JPanel panelRodape;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/notebook.png")));
		setTitle("Sistema OS");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setEnabled(false);
		btnUsuarios.setBorder(null);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/user2.png")));
		btnUsuarios.setToolTipText("Usuários");
		btnUsuarios.setBounds(127, 217, 70, 70);
		contentPane.add(btnUsuarios);

		JButton btnSobre = new JButton("");
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setContentAreaFilled(false);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/img/Sobre.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setActionCommand("Sobre");
		btnSobre.setBorder(null);
		btnSobre.setBounds(675, 25, 52, 52);
		contentPane.add(btnSobre);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(622, 377, -623, 52);
		contentPane.add(panel);
								
		panelRodape = new JPanel();
		panelRodape.setBackground(new Color(0, 0, 0));
		panelRodape.setBounds(0, 503, 784, 58);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);
		
		lblData = new JLabel("");
		lblData.setBackground(new Color(255, 255, 255));
		lblData.setBounds(477, 11, 297, 28);
		panelRodape.add(lblData);
		lblData.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 15));
		lblData.setForeground(new Color(255, 255, 255));
		
		lblStatusz = new JLabel("");
		lblStatusz.setBounds(0, 0, 48, 58);
		panelRodape.add(lblStatusz);
		lblStatusz.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
		
		JLabel lblUsu = new JLabel("Usuario:");
		lblUsu.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 15));
		lblUsu.setForeground(new Color(255, 255, 255));
		lblUsu.setBounds(58, 11, 79, 28);
		panelRodape.add(lblUsu);
		
		lblNomeUsu = new JLabel("");
		lblNomeUsu.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 15));
		lblNomeUsu.setForeground(new Color(255, 255, 255));
		lblNomeUsu.setBounds(147, 11, 190, 28);
		panelRodape.add(lblNomeUsu);
		
		JButton btnCliente = new JButton("");
		btnCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliente.setContentAreaFilled(false);
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes cliente = new Clientes();
				cliente.setVisible(true);
			}
		});
		btnCliente.setBorder(null);
		btnCliente.setIcon(new ImageIcon(Principal.class.getResource("/img/cliente.png")));
		btnCliente.setBounds(127, 84, 70, 70);
		contentPane.add(btnCliente);
		
		JButton btnServicos = new JButton("");
		btnServicos.setContentAreaFilled(false);
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos servicos = new Servicos();
				servicos.setVisible(true);
			}
		});
		btnServicos.setBorder(null);
		btnServicos.setIcon(new ImageIcon(Principal.class.getResource("/img/servico.png")));
		btnServicos.setBounds(26, 84, 70, 70);
		contentPane.add(btnServicos);
		
		btnRelatorio = new JButton("");
		btnRelatorio.setContentAreaFilled(false);
		btnRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorio.setEnabled(false);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorio.setBorder(null);
		btnRelatorio.setIcon(new ImageIcon(Principal.class.getResource("/img/relatorio.png")));
		btnRelatorio.setBounds(26, 217, 70, 70);
		contentPane.add(btnRelatorio);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(137, 165, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Serviços");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(36, 161, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Relatorios");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(36, 298, 58, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Principal.class.getResource("/img/Racing_Logo.png")));
		lblNewLabel_3.setBounds(391, 418, 549, 173);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Usuarios");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(137, 298, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Principal.class.getResource("/img/carr.png")));
		lblNewLabel_5.setBounds(0, 0, 774, 503);
		contentPane.add(lblNewLabel_5);
	} 
	private void status() {
		try {
			con = dao.conectar();
			if (con == null) {
				lblStatusz.setIcon(new ImageIcon(Principal.class.getResource("/Img/dboff.png")));
			} else {
				lblStatusz.setIcon(new ImageIcon(Principal.class.getResource("/Img/dbon.png")));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(data));
	}
}
