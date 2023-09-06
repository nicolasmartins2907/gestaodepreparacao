package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.DAO;
import utils.Validador;

public class Usuarios extends JDialog {
	
		DAO dao = new DAO();
		private Connection con;
		private PreparedStatement pst;
		private ResultSet rs;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtid;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JButton btnadicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnBuscar;
	private JLabel lblNewLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox cboPerfil;
	@SuppressWarnings("rawtypes")
	private JList listarUsuario;
	private JScrollPane scrollPane;
	private JCheckBox chckSenha;
	private JPasswordField txtSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Usuarios() {
		getContentPane().setForeground(new Color(192, 192, 192));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/users.png")));
		setTitle("Usuários");
		setModal(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblId.setBounds(38, 33, 46, 28);
		getContentPane().add(lblId);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNome.setBounds(38, 90, 46, 28);
		getContentPane().add(lblNome);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLogin.setBounds(38, 224, 46, 28);
		getContentPane().add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSenha.setBounds(38, 294, 46, 28);
		getContentPane().add(lblSenha);

		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setBounds(94, 38, 46, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(38, 129, 179, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(20));
		txtNome.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			if (!caracteres.contains(e.getKeyChar() + "")) {
				e.consume();

			}
		}
			@Override
			public void keyReleased(KeyEvent e) {
			listarUsuario();
			}
	});
		txtLogin = new JTextField();
		txtLogin.setBounds(38, 263, 179, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setDocument(new Validador (10));
		

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			limparCampos();
			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBorder(null);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/borracha.png")));
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.setBounds(421, 482, 75, 52);
		getContentPane().add(btnLimpar);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/lupa.png")));
		btnBuscar.setToolTipText("Pesquisar");
		btnBuscar.setBorder(null);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBounds(319, 61, 46, 37);
		getContentPane().add(btnBuscar);

		getRootPane().setDefaultButton(btnBuscar);
		
		btnadicionar = new JButton("");
		btnadicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnadicionar.setContentAreaFilled(false);
		btnadicionar.setBorder(null);
		btnadicionar.setEnabled(false);
		btnadicionar.setForeground(Color.GRAY);
		btnadicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/address.png")));
		btnadicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnadicionar.setBounds(38, 484, 60, 50);
		getContentPane().add(btnadicionar);
		
		btnEditar = new JButton("");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBorder(null);
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckSenha.isSelected()) {
					editarUsuarios();
				}	else {
					editarUsuarioExcetoSenha();
					
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/Editar.png")));
		btnEditar.setBounds(154, 484, 60, 50);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("");
		btnExcluir.setBorder(null);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(283, 484, 60, 50);
		getContentPane().add(btnExcluir);
		
		lblNewLabel = new JLabel("Perfil");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(283, 231, 46, 14);
		getContentPane().add(lblNewLabel);
		
		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "admin", "usuario"}));
		cboPerfil.setBounds(283, 262, 69, 22);
		getContentPane().add(cboPerfil);
		
		chckSenha = new JCheckBox("Alterar Senha");
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckSenha.isSelected()) {
				txtSenha.setText(null);
				txtSenha.requestFocus();
				txtSenha.setBackground(Color.GRAY);
				}else {
				txtSenha.setBackground(Color.WHITE);
				}
			}
		});
		chckSenha.setBounds(274, 332, 133, 23);
		getContentPane().add(chckSenha);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(38, 149, 179, 42);
		getContentPane().add(scrollPane);
		
		listarUsuario = new JList();
		listarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuarioLista();
			}
		});
		scrollPane.setViewportView(listarUsuario);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(38, 333, 179, 20);
		getContentPane().add(txtSenha);

	} 
	private void limparCampos() {
		txtid.setText(null);
		txtNome.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		cboPerfil.setSelectedItem(null);
		btnadicionar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnEditar.setEnabled(false);
		btnBuscar.setEnabled(true);
		txtSenha.setBackground(Color.WHITE);
		chckSenha.setSelected(false);
	}

	private void buscar() {
		String read = "select * from usuarios where login = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtLogin.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtid.setText(rs.getString(1)); 
				txtNome.setText(rs.getString(2));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
				cboPerfil.setSelectedItem(rs.getString(5));
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
				
			} else {
				JOptionPane.showMessageDialog(null, "Usuario inexistente");
				btnadicionar.setEnabled(true);
			}
			con.close();
		} catch (Exception e) {
		}
		}
			@SuppressWarnings("deprecation")
			private void adicionar () {
				if (txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o nome do usuario");
					txtNome.requestFocus();
				} else if (txtLogin.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o login do usuario");
					txtLogin.requestFocus();
				} else if (txtSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira a Senha do usuario");
					txtLogin.requestFocus();
				}else if(cboPerfil.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o Perfil");
					cboPerfil.requestFocus();
				}else{
					String create = "Insert into usuarios(Nome,login,senha,perfil) values  (?,?,md5(?),?)";
					try {
					
			
			con = dao.conectar();
			pst = con.prepareStatement(create);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtLogin.getText());
			pst.setString(3, txtSenha.getText());
			pst.setString(4, cboPerfil.getSelectedItem().toString());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario adicionado");
			limparCampos();
			btnBuscar.setEnabled(true);
			con.close();
		}catch (Exception e ) {
			System.out.println(e);
		}
				}
			}
		@SuppressWarnings("deprecation")
		private void editarUsuarios() {
			if(txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Nome");
					txtNome.requestFocus();
			}else if (txtLogin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o Login do Usuario");
				txtLogin.requestFocus();
			}else {
				String update = "update usuarios set Nome=?,login=?,senha=md5(?),perfil=? where id=?";
				
				try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText ());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5,  txtid.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do usuario editados com sucesso.");
				limparCampos();
				btnBuscar.setEnabled(true);
			}catch (Exception e ) {
					System.out.println(e);
							}
			}
		}
		private void editarUsuarioExcetoSenha() {
				if(txtNome.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o Nome");
						txtNome.requestFocus();
				}else if (txtLogin.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Login do Usuario");
					txtLogin.requestFocus();
				}else {
					String update2 = "update usuarios set Nome=?,login=?,perfil=? where id=?";
					try {
					con = dao.conectar();
					pst = con.prepareStatement(update2);
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtLogin.getText());
					pst.setString(3, cboPerfil.getSelectedItem().toString());
					pst.setString(4,  txtid.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Dados do usuario editados com sucesso.");
					limparCampos();
					btnBuscar.setEnabled(true);
				}catch (Exception e ) {
						System.out.println(e);
								}
				}
			}

		
			private void excluirUsuario() {
				int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuario?","Atenção!",JOptionPane.YES_NO_OPTION);
				if (confirma == JOptionPane.YES_OPTION) {
					String delete = "delete From usuarios where id =?";
					try {
						con = dao.conectar();
						pst = con.prepareStatement(delete);
						pst.setString(1, txtid.getText());
						pst.executeUpdate();
						limparCampos();
						btnBuscar.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Usuario Excluido");
						con.close();
					}catch (Exception e) {
						System.out.println(e);
					}
				}
			}
				@SuppressWarnings("unchecked")
				private void listarUsuario() {
					DefaultListModel<String> modelo = new DefaultListModel<>();
					listarUsuario.setModel(modelo);
					String readlista = "select * from usuarios where Nome like '" + txtNome.getText() + "%'" + "order by Nome";
					try {
						con = dao.conectar();
						pst = con.prepareStatement(readlista);
						rs = pst.executeQuery();
						while(rs.next()) {
							scrollPane.setVisible(true);
							modelo.addElement(rs.getString(2));
							if (txtNome.getText().isEmpty()) {
								scrollPane.setVisible(false);
							}
							
						}
						con.close();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				private void buscarUsuarioLista() {
					int linha = listarUsuario.getSelectedIndex();
					if (linha>= 0) {
						String readlistaUsuario = "select * from usuarios where Nome like '" + txtNome.getText() + "%'" + "order by Nome limit " + (linha) + " , 1";
						try {
							con = dao.conectar();
							pst = con.prepareStatement(readlistaUsuario);
							rs = pst.executeQuery();
							if (rs.next()) {
								scrollPane.setVisible(false);
								txtid.setText(rs.getString(1)); 
								txtNome.setText(rs.getString(2));
								txtLogin.setText(rs.getString(3));
								txtSenha.setText(rs.getString(4));
								cboPerfil.setSelectedItem(rs.getString(5));
								
							} else {
								JOptionPane.showMessageDialog(null, "Usuário inexistente");
								
							}
							con.close();
						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						scrollPane.setVisible(false);
						
						
					}
		
		}
}
