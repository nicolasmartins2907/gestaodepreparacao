package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Relatorios extends JDialog {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JButton btnVenda;
	private JButton btnCusto;
	private JButton btnReposicao;
	private JButton btnFornecedor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Relatorios() {
		getContentPane().setForeground(new Color(192, 192, 192));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/relatorio.png")));
		setTitle("Relatórios");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		JButton btnServicos = new JButton("");
		btnServicos.setVerticalAlignment(SwingConstants.TOP);
		btnServicos.setIcon(new ImageIcon(Relatorios.class.getResource("/img/servico.png")));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioServicos();
			}
		});
		btnServicos.setBounds(225, 98, 80, 80);
		getContentPane().add(btnServicos);

		JButton btnClientes = new JButton("");
		btnClientes.setIcon(new ImageIcon(Relatorios.class.getResource("/img/user2.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnClientes.setBounds(78, 98, 80, 80);
		getContentPane().add(btnClientes);

		btnCusto = new JButton("");
		btnCusto.setIcon(new ImageIcon(Relatorios.class.getResource("/img/134157_money_cashier_dollar_currency_icon.png")));
		btnCusto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustoPatrimonio();
			}
		});
		btnCusto.setBounds(78, 235, 80, 80);
		getContentPane().add(btnCusto);

		btnVenda = new JButton("");
		btnVenda.setIcon(new ImageIcon(Relatorios.class.getResource("/img/3702389_door sign_label_sale_sign_icon.png")));
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaPatrimonio();
			}
		});
		btnVenda.setBounds(225, 235, 80, 80);
		getContentPane().add(btnVenda);

		btnReposicao = new JButton("");
		btnReposicao.setIcon(new ImageIcon(Relatorios.class.getResource("/img/add for.png")));
		btnReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reposicao();
			}
		});
		btnReposicao.setBounds(360, 235, 80, 80);
		getContentPane().add(btnReposicao);
		
		btnFornecedor = new JButton("");
		btnFornecedor.setIcon(new ImageIcon(Relatorios.class.getResource("/img/fornecedor.png")));
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores();
			}
		});
		btnFornecedor.setBounds(360, 98, 80, 80);
		getContentPane().add(btnFornecedor);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setBounds(88, 189, 70, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Servicos");
		lblNewLabel_1.setBounds(235, 189, 70, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Custo");
		lblNewLabel_2.setBounds(88, 326, 70, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Venda");
		lblNewLabel_3.setBounds(235, 326, 58, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Reposicao");
		lblNewLabel_4.setBounds(370, 326, 70, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fornecedor");
		lblNewLabel_5.setBounds(370, 189, 46, 14);
		getContentPane().add(lblNewLabel_5);

	}

	private void relatorioClientes() {
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" "));
			String readClientes = "select idcli,nome,fone,cep from clientes order by nome";
			try {
				con = dao.conectar();

				pst = con.prepareStatement(readClientes);

				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("ID"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col4 = new PdfPCell(new Paragraph("CEP"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void relatorioServicos() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("servicos.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Servicos:"));
			String readServicos = "select servicos.os,servicos.motor,servicos.dataOS,servicos.servico,servicos.valor,clientes.nome,clientes.idcli from servicos inner join clientes on servicos.idcli = clientes.idcli;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readServicos);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(6);
				PdfPCell col1 = new PdfPCell(new Paragraph("dataOS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("motor"));
				PdfPCell col3 = new PdfPCell(new Paragraph("servico"));
				PdfPCell col4 = new PdfPCell(new Paragraph("valor"));
				PdfPCell col5 = new PdfPCell(new Paragraph("nome"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Id cliente"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);
				while (rs.next()) {
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
					tabela.addCell(rs.getString(7));

				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("servicos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void Reposicao() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("produtos.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Produtos:"));
			document.add(new Paragraph(""));
			String readProdutos = "select codigo as código,produto,date_format(dataval, '%d/%m/%Y') as validade,\n"
					+ "date_format(dataent, '%d/%m/%Y') as entrada,\n" + "estoque, estoquemin as estoque_mínimo \n"
					+ "from produtos where dataval < dataent or estoque < estoquemin";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readProdutos);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(6);
				PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Validade"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Entrada"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Estoque"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Estoque min"));

				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);

				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("produtos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void CustoPatrimonio() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("patrimonio.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Patrímônio:"));
			document.add(new Paragraph(""));
			String readPatrimonio = "select sum(custo * estoque) as Total from produtos;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readPatrimonio);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(1);
				PdfPCell col1 = new PdfPCell(new Paragraph("Patrímônio"));
				tabela.addCell(col1);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));

				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("patrimonio.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void VendaPatrimonio() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("patrimonio.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Patrímônio:"));
			String readPatrimonio = "select sum((custo +(custo * lucro)/100) * estoque) as total from produtos;";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readPatrimonio);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(1);
				PdfPCell col1 = new PdfPCell(new Paragraph("Patrímônio"));
				tabela.addCell(col1);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("patrimonio.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void Fornecedores() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Fornecedores:"));
			document.add(new Paragraph(" ")); 
			String readFornecedores = "select razao,fone, cnpj from fornecedores order by razao";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readFornecedores);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(3); 
				PdfPCell col1 = new PdfPCell(new Paragraph("Razão Social"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("CNPJ"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("fornecedores.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
