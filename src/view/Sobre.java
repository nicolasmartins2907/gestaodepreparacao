 package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings({ "serial", "unused" })
public class Sobre extends JDialog {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sobre() {
		getContentPane().setForeground(new Color(192, 192, 192));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/about.png")));
		setTitle("Sobre");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		
		JButton btnLogo = new JButton("");
		btnLogo.setIcon(new ImageIcon(Sobre.class.getResource("/img/mit-icon.png")));
		btnLogo.setToolTipText("Logo");
		btnLogo.setBorderPainted(false);
		btnLogo.setBorder(null);
		btnLogo.setBounds(325, 177, 109, 84);
		getContentPane().add(btnLogo);
		
		JLabel lblNewLabel = new JLabel("Autor: Nicolas Gomes Martins");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 34, 235, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Licença MIT");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(325, 162, 168, 17);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sistema de Gestão de Preparação de Motores de Alta Potencia");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 11, 376, 24);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Sobre.class.getResource("/img/Racing_Logo.png")));
		lblNewLabel_3.setBounds(-141, 154, 473, 156);
		getContentPane().add(lblNewLabel_3);

	}

}
