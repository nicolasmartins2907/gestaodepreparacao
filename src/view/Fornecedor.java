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
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class Fornecedor extends JDialog {

	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JTextField txtID;
	private JTextField txtRazao;
	private JTextField txtFone;
	private JTextField txtCNPJ;
	private JTextField txtEndereco;
	private JTextField txtCEP;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUF;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUF_1;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JScrollPane scrollPane00;
	@SuppressWarnings("rawtypes")
	private JList listarUsuarios;
	private JTextField txtFantasia;
	private JTextField txtVendedor;
	private JTextField txtIE;
	private JTextField txtEmail;
	private JTextField txtSite;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedor dialog = new Fornecedor();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Fornecedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedor.class.getResource("/img/fornecedor.png")));
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Fornecedores");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		scrollPane00 = new JScrollPane();
		scrollPane00.setVisible(false);
		scrollPane00.setBounds(10, 90, 214, 33);
		getContentPane().add(scrollPane00);

		listarUsuarios = new JList();
		listarUsuarios.setDoubleBuffered(true);
		listarUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuarioLista();
			}
		});
		scrollPane00.setViewportView(listarUsuarios);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(10, 11, 26, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(46, 8, 53, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		txtRazao = new JTextField();
		txtRazao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuario();
			}
		});
		txtRazao.setBounds(10, 74, 214, 20);
		getContentPane().add(txtRazao);
		txtRazao.setColumns(10);
		txtRazao.setDocument(new Validador(40));

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});
		txtFone.setBounds(268, 74, 144, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);
		txtFone.setDocument(new Validador(15));

		txtCNPJ = new JTextField();
		txtCNPJ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});
		txtCNPJ.setBounds(238, 280, 144, 20);
		getContentPane().add(txtCNPJ);
		txtCNPJ.setColumns(10);
		txtCNPJ.setDocument(new Validador(15));

		lblNewLabel_1 = new JLabel("Razão Social");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 57, 104, 14);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_2.setBounds(268, 57, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CNPJ");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_3.setBounds(240, 257, 36, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Endereço");
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_4.setBounds(380, 311, 61, 14);
		getContentPane().add(lblNewLabel_4);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(380, 336, 214, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		txtCEP = new JTextField();
		txtCEP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});
		txtCEP.setBounds(10, 336, 61, 20);
		getContentPane().add(txtCEP);
		txtCEP.setColumns(10);
		txtCEP.setDocument(new Validador(8));

		JLabel lblNewLabel_5 = new JLabel("CEP");
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_5.setBounds(10, 311, 36, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Número");
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_6.setBounds(230, 311, 46, 14);
		getContentPane().add(lblNewLabel_6);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {

		});
		txtNumero.setBounds(230, 336, 86, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		txtNumero.setDocument(new Validador(7));

		JLabel lblNewLabel_7 = new JLabel("Cidade");
		lblNewLabel_7.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_7.setBounds(138, 369, 46, 14);
		getContentPane().add(lblNewLabel_7);

		txtCidade = new JTextField();
		txtCidade.setBounds(138, 393, 105, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);
		txtCidade.setDocument(new Validador(30));

		txtComplemento = new JTextField();
		txtComplemento.setBounds(10, 448, 109, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);
		txtComplemento.setDocument(new Validador(25));

		JLabel lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_8.setBounds(10, 423, 76, 14);
		getContentPane().add(lblNewLabel_8);

		cboUF_1 = new JComboBox();
		cboUF_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		cboUF_1.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUF_1.setBounds(260, 391, 54, 22);
		getContentPane().add(cboUF_1);

		JButton btnbuscarCep = new JButton("BuscarCEP");
		btnbuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnbuscarCep.setBounds(108, 335, 86, 23);
		getContentPane().add(btnbuscarCep);

		JLabel lblNewLabel_9 = new JLabel("Bairro");
		lblNewLabel_9.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_9.setBounds(10, 367, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtBairro = new JTextField();
		txtBairro.setBounds(10, 392, 105, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		txtBairro.setDocument(new Validador(40));

		txtFantasia = new JTextField();
		txtFantasia.setBounds(10, 169, 202, 20);
		getContentPane().add(txtFantasia);
		txtFantasia.setColumns(10);

		lblNewLabel_10 = new JLabel("Fantasia");
		lblNewLabel_10.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_10.setBounds(10, 140, 46, 14);
		getContentPane().add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("Vendedor");
		lblNewLabel_11.setBounds(240, 201, 57, 14);
		getContentPane().add(lblNewLabel_11);

		txtVendedor = new JTextField();
		txtVendedor.setBounds(238, 226, 123, 20);
		getContentPane().add(txtVendedor);
		txtVendedor.setColumns(10);

		lblNewLabel_12 = new JLabel("IE (Inscrição Estadual)");
		lblNewLabel_12.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_12.setBounds(10, 260, 128, 14);
		getContentPane().add(lblNewLabel_12);

		txtIE = new JTextField();
		txtIE.setBounds(10, 280, 149, 20);
		getContentPane().add(txtIE);
		txtIE.setColumns(10);
		txtIE.setDocument(new Validador(15));

		lblNewLabel_13 = new JLabel("E-mail");
		lblNewLabel_13.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_13.setBounds(20, 200, 46, 14);
		getContentPane().add(lblNewLabel_13);

		txtEmail = new JTextField();
		txtEmail.setBounds(10, 225, 168, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		lblNewLabel_14 = new JLabel("Site");
		lblNewLabel_14.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_14.setBounds(238, 140, 46, 14);
		getContentPane().add(lblNewLabel_14);

		txtSite = new JTextField();
		txtSite.setBounds(238, 169, 165, 20);
		getContentPane().add(txtSite);
		txtSite.setColumns(10);

		btnExcluir = new JButton("");
		btnExcluir.setBounds(292, 490, 80, 60);
		getContentPane().add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFornecedor();
			}
		});
		btnExcluir.setToolTipText("Excluir Fornecedor");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorder(null);
		btnExcluir.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/delete for.png")));

		btnEditar = new JButton("");
		btnEditar.setBounds(164, 490, 80, 60);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarFornecedor();
			}
		});
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorder(null);
		btnEditar.setToolTipText("Editar Fornecedor");
		btnEditar.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/edit for.png")));

		btnAdicionar = new JButton("");
		btnAdicionar.setBounds(49, 490, 80, 60);
		getContentPane().add(btnAdicionar);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFornecedor();
			}
		});
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorder(null);
		btnAdicionar.setToolTipText("Adicionar Fornecedor");
		btnAdicionar.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/add for.png")));

		btnLimpar = new JButton("");
		btnLimpar.setBounds(402, 490, 80, 60);
		getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorder(null);
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/clean.png")));

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
					cboUF_1.setSelectedItem(element.getText());
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

	private void excluirFornecedor() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste fornecedor?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedores where idfor=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();

				JOptionPane.showMessageDialog(null, "Fornecedor excluído");
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Não foi Possível Excluir o Fornecedor!\nHá um Serviço Pendente.");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editarFornecedor() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do fornecedor");
			txtRazao.requestFocus();
		} else {
			String update = "update fornecedores set razao=?, fantasia=?, vendedor=?, cnpj=?, ie=?, email=?, site=?, fone=?, cep=?, endereco=?, bairro=?, complemento=?, numero=?, cidade=?, uf=? WHERE idfor=? ";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtVendedor.getText());
				pst.setString(4, txtCNPJ.getText());
				pst.setString(5, txtIE.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtSite.getText());
				pst.setString(8, txtFone.getText());
				pst.setString(9, txtCEP.getText());
				pst.setString(10, txtEndereco.getText());
				pst.setString(11, txtBairro.getText());
				pst.setString(12, txtComplemento.getText());
				pst.setString(13, txtNumero.getText());
				pst.setString(14, txtCidade.getText());
				pst.setString(15, cboUF_1.getSelectedItem().toString());
				pst.setString(16, txtID.getText());
				pst.executeUpdate();
				

				JOptionPane.showMessageDialog(null, "Dados do fornecedor editados com sucesso");
				limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "CNPJ Existente");
				txtCNPJ.setText(null);
				txtCNPJ.requestFocus();
			} catch (Exception e1) {

				System.out.println(e1);

			}
		}
	}

	private void limparCampos() {
		txtID.setText(null);
		txtRazao.setText(null);
		txtVendedor.setText(null);
		txtEndereco.setText(null);
		txtCNPJ.setText(null);
		txtIE.setText(null);
		txtEmail.setText(null);
		txtSite.setText(null);
		txtFantasia.setText(null);
		txtCEP.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtFone.setText(null);
		cboUF_1.setSelectedItem(null);
	}

	private void adicionarFornecedor() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do fornecedor");
			txtRazao.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtFone.requestFocus();
		} else if (txtCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtCNPJ.requestFocus();
		} else if (txtCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtCEP.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtCidade.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtNumero.requestFocus();
		} else {
			String create = "insert into fornecedores(razao,fantasia,vendedor,cnpj,ie,email,site,fone,cep,endereco,bairro,complemento,numero,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtVendedor.getText());
				pst.setString(4, txtCNPJ.getText());
				pst.setString(5, txtIE.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtSite.getText());
				pst.setString(8, txtFone.getText());
				pst.setString(9, txtCEP.getText());
				pst.setString(10, txtEndereco.getText());
				pst.setString(11, txtBairro.getText());
				pst.setString(12, txtComplemento.getText());
				pst.setString(13, txtNumero.getText());
				pst.setString(14, txtCidade.getText());
				pst.setString(15, cboUF_1.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Fornecedor adicionado");
				limparCampos();
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "CNPJ Existente");
				txtCNPJ.setText(null);
				txtCNPJ.requestFocus();
			} catch (Exception e1) {

				System.out.println(e1);

			}

		}
	}

	@SuppressWarnings("unchecked")
	private void listarUsuario() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listarUsuarios.setModel(modelo);
		String readlista = "select * from fornecedores where razao like '" + txtRazao.getText() + "%'"
				+ "order by razao";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readlista);
			rs = pst.executeQuery();
			while (rs.next()) {
				scrollPane00.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtRazao.getText().isEmpty()) {
					scrollPane00.setVisible(false);
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
			String readlistaUsuario = "select * from fornecedores where razao like '" + txtRazao.getText() + "%'"
					+ "order by razao limit " + (linha) + " , 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readlistaUsuario);
				rs = pst.executeQuery();
				if (rs.next()) {
					scrollPane00.setVisible(false);
					txtID.setText(rs.getString(1));
					txtRazao.setText(rs.getString(2));
					txtFantasia.setText(rs.getString(3));
					txtVendedor.setText(rs.getString(4));
					txtCNPJ.setText(rs.getString(5));
					txtIE.setText(rs.getString(6));
					txtEmail.setText(rs.getString(7));
					txtSite.setText(rs.getString(8));
					txtFone.setText(rs.getString(9));
					txtCEP.setText(rs.getString(10));
					txtEndereco.setText(rs.getString(11));
					txtBairro.setText(rs.getString(12));
					txtComplemento.setText(rs.getString(13));
					txtNumero.setText(rs.getString(14));
					txtCidade.setText(rs.getString(15));
					cboUF_1.setSelectedItem(rs.getString(16));

				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			scrollPane00.setVisible(false);

		}
	}
}
