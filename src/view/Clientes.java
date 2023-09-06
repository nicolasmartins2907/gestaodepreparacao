package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import utils.Validador;

public class Clientes extends JDialog {
	
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	@SuppressWarnings("unused")
	private JButton btnAdicionar;
	@SuppressWarnings("unused")
	private JButton btnPesquisar;
	@SuppressWarnings("unused")
	private JButton btnExcluir;
	@SuppressWarnings("unused")
	private JButton btnEditar;
	private JTextField txtID;
	private JTextField txtCEP;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JButton btnExcluir_1;
	private JButton btnAdicionar_1;
	private JButton btnEditar_1;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUf;
	private JScrollPane scrollPane2;
	@SuppressWarnings("rawtypes")
	private JList listarUsuarios;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Clientes() {
		getContentPane().setForeground(new Color(192, 192, 192));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/cliente.png")));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Clientes");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		scrollPane2 = new JScrollPane();
		scrollPane2.setVisible(false);
		scrollPane2.setBorder(null);
		scrollPane2.setBounds(30, 119, 222, 42);
		getContentPane().add(scrollPane2);

		listarUsuarios = new JList();
		listarUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuarioLista();
			}
		});
		scrollPane2.setRowHeaderView(listarUsuarios);
		listarUsuarios.setDoubleBuffered(true);
		listarUsuarios.setBorder(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(30, 74, 46, 14);
		getContentPane().add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuario();

			}
		});
		txtNome.setBounds(30, 99, 222, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(50));

		txtEndereco = new JTextField();
		txtEndereco.setBounds(30, 440, 244, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);
		txtEndereco.setDocument(new Validador(80));

		txtTelefone = new JTextField();
		txtTelefone.setBounds(30, 216, 123, 20);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		txtTelefone.setDocument(new Validador(20));

		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setBounds(30, 415, 86, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setBounds(30, 186, 81, 14);
		getContentPane().add(lblNewLabel_2);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBorder(null);
		btnLimpar.setIcon(new ImageIcon(Clientes.class.getResource("/img/borracha.png")));
		btnLimpar.setBounds(384, 490, 80, 60);
		getContentPane().add(btnLimpar);

		btnEditar_1 = new JButton("");
		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContato();
			}
		});
		btnEditar_1.setContentAreaFilled(false);
		btnEditar_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar_1.setBorder(null);
		btnEditar_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/Editar.png")));
		btnEditar_1.setBounds(149, 490, 80, 60);
		getContentPane().add(btnEditar_1);

		btnAdicionar_1 = new JButton("");
		btnAdicionar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar_1.setContentAreaFilled(false);
		btnAdicionar_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar_1.setBorder(null);
		btnAdicionar_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/address.png")));
		btnAdicionar_1.setBounds(30, 490, 80, 60);
		getContentPane().add(btnAdicionar_1);

		btnExcluir_1 = new JButton("");
		btnExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnExcluir_1.setContentAreaFilled(false);
		btnExcluir_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir_1.setBorder(null);
		btnExcluir_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnExcluir_1.setBounds(265, 490, 80, 60);
		getContentPane().add(btnExcluir_1);

		JLabel lblNewLabel_8 = new JLabel("ID");
		lblNewLabel_8.setBounds(30, 33, 20, 14);
		getContentPane().add(lblNewLabel_8);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(67, 30, 39, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("CEP");
		lblNewLabel_9.setBounds(30, 247, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtCEP = new JTextField();
		txtCEP.setBounds(30, 272, 99, 20);
		getContentPane().add(txtCEP);
		txtCEP.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Número");
		lblNewLabel_10.setBounds(30, 303, 46, 14);
		getContentPane().add(lblNewLabel_10);

		txtNumero = new JTextField();
		txtNumero.setBounds(30, 328, 86, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Cidade");
		lblNewLabel_11.setBounds(30, 359, 46, 14);
		getContentPane().add(lblNewLabel_11);

		txtCidade = new JTextField();
		txtCidade.setBounds(30, 384, 99, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Bairro");
		lblNewLabel_12.setBounds(164, 359, 80, 14);
		getContentPane().add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Complemento");
		lblNewLabel_13.setBounds(164, 303, 90, 14);
		getContentPane().add(lblNewLabel_13);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(164, 328, 99, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		txtBairro = new JTextField();
		txtBairro.setBounds(162, 384, 112, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		JButton btnBuscarCep = new JButton("Buscar ");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscarCep.setBounds(164, 271, 89, 23);
		getContentPane().add(btnBuscarCep);

		JLabel lblNewLabel_14 = new JLabel("UF");
		lblNewLabel_14.setBounds(321, 303, 46, 14);
		getContentPane().add(lblNewLabel_14);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(321, 327, 55, 22);
		getContentPane().add(cboUf);
	}

	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtEndereco.setText(null);
		txtTelefone.setText(null);
		txtCEP.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);

	}

	private void adicionarUsuario() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do cliente");
			txtNome.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do cliente");
			txtTelefone.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o endereço do cliente");
			txtEndereco.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o bairro do cliente");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do cliente");
			txtCidade.requestFocus();
		} else if (txtCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do cliente");
			txtCEP.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero da casa do cliente");
			txtNumero.requestFocus();

		} else {
			
			String create = "insert into clientes(nome,fone,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?)";	
			try {		
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtTelefone.getText());
				pst.setString(3, txtCEP.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente adicionado");	
				limparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void excluirContato() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();

				JOptionPane.showMessageDialog(null, "Contato excluído");
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Não foi Possível Excluir o Cliente!\nHá um Serviço Pendente.");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editarContato() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome");
			txtNome.requestFocus();
		} else {
			String update = "update clientes set nome=?, fone=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, uf=? WHERE idcli=? ";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtTelefone.getText());
				pst.setString(3, txtCEP.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtID.getText());

				pst.executeUpdate();
				limparCampos();

				JOptionPane.showMessageDialog(null, "Dados do contato editados com sucesso");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCEP.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						System.out.println("OK");
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void listarUsuario() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listarUsuarios.setModel(modelo);
		String readlista = "select * from clientes where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readlista);
			rs = pst.executeQuery();
			while (rs.next()) {
				scrollPane2.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
					scrollPane2.setVisible(false);
				}
			}		
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarUsuarioLista() {
		int linha = listarUsuarios.getSelectedIndex();
		if (linha >= 0) {
			String readlistaUsuario = "select * from clientes where nome like '" + txtNome.getText() + "%'"
					+ "order by nome limit " + (linha) + " , 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readlistaUsuario);
				rs = pst.executeQuery();
				if (rs.next()) {
					scrollPane2.setVisible(false);
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtTelefone.setText(rs.getString(3));
					txtCEP.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtNumero.setText(rs.getString(6));
					txtComplemento.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));

				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");

				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			scrollPane2.setVisible(false);

		}

	}
}