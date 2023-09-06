package view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import utils.Validador;
import java.awt.Color;

public class Servicos extends JDialog {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JTextField txtOS;
	private JTextField txtData;
	private JTextField txtMotor;
	private JTextField txtServico;
	private JTextField txtID;
	private JTextField txtNome;
	private JScrollPane Painel1;
	@SuppressWarnings("rawtypes")
	private JList listarCliente;
	private JTextField txtValor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos dialog = new Servicos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("rawtypes")
	public Servicos() {
		getContentPane().setForeground(new Color(192, 192, 192));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servicos.class.getResource("/img/relatorio.png")));
		setTitle("Ordem de Serviço");
		setModal(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setBounds(32, 28, 32, 14);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.setEditable(false);
		txtOS.setBounds(74, 25, 86, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBounds(32, 69, 39, 14);
		getContentPane().add(lblNewLabel_1);

		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(74, 66, 125, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Motor");
		lblNewLabel_3.setBounds(32, 117, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Serviço");
		lblNewLabel_4.setBounds(32, 151, 46, 14);
		getContentPane().add(lblNewLabel_4);

		txtMotor = new JTextField();
		txtMotor.setBounds(88, 114, 346, 20);
		getContentPane().add(txtMotor);
		txtMotor.setColumns(10);
		txtMotor.setDocument(new Validador(15));

		txtServico = new JTextField();
		txtServico.setBounds(88, 148, 346, 20);
		getContentPane().add(txtServico);
		txtServico.setColumns(10);
		txtServico.setDocument(new Validador(40));

		JLabel lblNewLabel_5 = new JLabel("Valor");
		lblNewLabel_5.setBounds(32, 190, 46, 14);
		getContentPane().add(lblNewLabel_5);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorder(null);
		btnAdicionar.setIcon(new ImageIcon(Servicos.class.getResource("/img/address.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(10, 244, 70, 45);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/Editar.png")));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorder(null);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(102, 244, 75, 50);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorder(null);
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(197, 239, 75, 55);
		getContentPane().add(btnExcluir);

		JButton btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorder(null);
		btnBuscar.setIcon(new ImageIcon(Servicos.class.getResource("/img/lupa.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(183, 11, 59, 44);
		getContentPane().add(btnBuscar);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(311, 11, 186, 102);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarCliente();
			}
		});
		txtNome.setBounds(10, 30, 116, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(136, 30, 40, 20);
		panel.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(136, 11, 46, 14);
		panel.add(lblNewLabel_2);

		Painel1 = new JScrollPane();
		Painel1.setVisible(false);
		Painel1.setBounds(10, 48, 116, 30);
		panel.add(Painel1);

		listarCliente = new JList();
		listarCliente.setValueIsAdjusting(true);
		listarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarCliente();

			}
		});
		Painel1.setViewportView(listarCliente);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Servicos.class.getResource("/img/borracha.png")));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(297, 228, 89, 67);
		getContentPane().add(btnNewButton);

		JButton btnOS = new JButton("");
		btnOS.setContentAreaFilled(false);
		btnOS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOS.setBorder(null);
		btnOS.setIcon(new ImageIcon(Servicos.class.getResource("/img/imprimir.png")));
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
			}
		});
		btnOS.setBounds(425, 239, 70, 50);
		getContentPane().add(btnOS);

		txtValor = new JTextField();
		txtValor.setBounds(88, 187, 86, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

	}

	private void limparCampos() {
		txtOS.setText(null);
		txtData.setText(null);
		txtMotor.setText(null);
		txtServico.setText(null);
		txtValor.setText(null);
		txtID.setText(null);
		txtNome.setText(null);
	}

	private void buscar() {
		String numOS = JOptionPane.showInputDialog("Número da OS");
		String read = "select * from servicos where os = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				txtMotor.setText(rs.getString(3));
				txtServico.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));

			} else {
				JOptionPane.showMessageDialog(null, "Serviço inexistente");

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void adicionar() {
		if (txtMotor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha O modelo do Motor");
			txtMotor.requestFocus();
		} else if (txtServico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Peça");
			txtServico.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Valor do serviço");
			txtValor.requestFocus();
		} else {
			String create = "insert into servicos(motor,servico,valor,idcli) values (?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);

				pst.setString(1, txtMotor.getText());
				pst.setString(2, txtServico.getText());
				pst.setString(3, txtValor.getText());
				pst.setString(4, txtID.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Serviço adicionado");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void excluir() {
		// validação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste serviço?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// CRUD - Delete
			String delete = "delete from servicos where os=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtOS.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Serviço excluído");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editar() {
		if (txtOS.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a OS do serviço");
			txtOS.requestFocus();
		} else if (txtMotor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o modelo");
			txtMotor.requestFocus();
		} else {
			String update = "update servicos set os=?,dataOS=?,motor=?,servico=?,valor=? where os=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtOS.getText());
				pst.setString(2, txtData.getText());
				pst.setString(3, txtMotor.getText());
				pst.setString(4, txtServico.getText());
				pst.setString(5, txtValor.getText());
				pst.setString(6, txtOS.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Dados do serviço editados com sucesso");
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void buscarCliente() {
		int linha = listarCliente.getSelectedIndex();
		if (linha >= 0) {
			String readlistaUsuario = "select * from clientes where nome like '" + txtNome.getText() + "%'"
					+ "order by idcli limit " + (linha) + " , 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readlistaUsuario);
				rs = pst.executeQuery();
				if (rs.next()) {
					Painel1.setVisible(false);
					txtID.setText(rs.getString(1));
				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			Painel1.setVisible(false);
		}
	}

	@SuppressWarnings("unchecked")
	private void listarCliente() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listarCliente.setModel(modelo);
		String readlista = "select * from clientes where nome like '" + txtNome.getText() + "%'" + "order by idcli";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readlista);
			rs = pst.executeQuery();
			while (rs.next()) {
				Painel1.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
					Painel1.setVisible(false);
				}

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void imprimirOS() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
			document.open();
			String readOS = "select * from servicos where os = ?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readOS);
				pst.setString(1, txtOS.getText());
				rs = pst.executeQuery();
				if (rs.next()) {
					Paragraph os = new Paragraph("OS: " + rs.getString(1));
					os.setAlignment(Element.ALIGN_RIGHT);
					document.add(os);
					Paragraph data = new Paragraph("" + rs.getString(2));
					data.setAlignment(Element.ALIGN_LEFT);
					document.add(data);
					Paragraph usuario = new Paragraph("Motor: " + rs.getString(3));
					usuario.setAlignment(Element.ALIGN_LEFT);
					document.add(usuario);
					Paragraph servico = new Paragraph("Serviço a ser feito: " + rs.getString(4));
					servico.setAlignment(Element.ALIGN_LEFT);
					document.add(servico);
					Paragraph valor = new Paragraph("Valor Total: R$" + rs.getString(5));
					valor.setAlignment(Element.ALIGN_LEFT);
					document.add(valor);

					Image imagem = Image.getInstance(Servicos.class.getResource("/img/chave.png"));
					imagem.scaleToFit(64, 64);
					imagem.setAbsolutePosition(500, 10);
					document.add(imagem);

				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("os.pdf"));
		} catch (Exception e) {
			System.out.println(e);

		}

	}
}
