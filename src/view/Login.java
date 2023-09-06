package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	Principal principal = new Principal();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JLabel lblStatus;
	private JLabel lblData;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/login.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
				
			}
		});
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 15));
		lblNewLabel.setBounds(353, 57, 63, 20);
		contentPane.add(lblNewLabel);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(235, 88, 300, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(353, 196, 63, 21);
		contentPane.add(lblNewLabel_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(235, 228, 300, 20);
		contentPane.add(txtSenha);
		
		JButton btnAcessar = new JButton("Logar");
		btnAcessar.setForeground(new Color(255, 255, 255));
		btnAcessar.setFont(new Font("Swis721 Hv BT", Font.PLAIN, 15));
		btnAcessar.setContentAreaFilled(false);
		btnAcessar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAcessar.setBorder(null);
		btnAcessar.setIcon(new ImageIcon(Login.class.getResource("/img/acessar.png")));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(419, 290, 116, 80);
		contentPane.add(btnAcessar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/img/vel.png")));
		lblNewLabel_2.setBounds(0, 11, 784, 383);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 128));
		panel.setBounds(0, 394, 784, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(10, 0, 48, 48);
		panel.add(lblStatus);
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		
		lblData = new JLabel("");
		lblData.setForeground(new Color(0, 0, 0));
		lblData.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 17));
		lblData.setBounds(500, 11, 274, 25);
		panel.add(lblData);
	}
	private void status() {
		try {
			con = dao.conectar();
			if (con == null) {
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/Img/dboff.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/Img/dbon.png")));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void logar() {
		String capturaSenha = new String(txtSenha.getPassword());
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtSenha.requestFocus();
		} else {
			String read = "select * from usuarios where login=? and senha=md5(?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				rs = pst.executeQuery();
				if (rs.next()) {
					String perfil = rs.getString(5);
					if(perfil.equals("admin")) {
						principal.setVisible(true);
						principal.lblNomeUsu.setText(rs.getString(2));
						principal.btnRelatorio.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						principal.panelRodape.setBackground(Color.BLUE);
						this.dispose();
					} else {
						principal.setVisible(true);
						principal.lblNomeUsu.setText(rs.getString(2));
						this.dispose();
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido(s)");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
		private void setarData() {
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			lblData.setText(formatador.format(data));
		}
}
