package me.ramazanenescik04.sayac;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SayacFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static JLabel text = new JLabel("00:00:00");;

	public SayacFrame() {
		setTitle("Sayac - v0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 214);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		
		text.setFont(new Font("Tahoma", Font.BOLD, 42));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(text, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAyarlar = new JMenu("Ayarlar");
		menuBar.add(mnAyarlar);
		
		JMenuItem mnıtmStart = new JMenuItem("Sayacı Başlat");
		mnAyarlar.add(mnıtmStart);
		
		JMenuItem mnıtmSayacDurdur = new JMenuItem("Sayacı Durdur");
		mnıtmSayacDurdur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sayac.stop();
				mnıtmStart.setEnabled(true);
				mnıtmSayacDurdur.setEnabled(false);
			}
		});
		mnıtmStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sayac.start();
				mnıtmStart.setEnabled(false);
				mnıtmSayacDurdur.setEnabled(true);
			}
		});
		mnıtmSayacDurdur.setEnabled(false);
		mnAyarlar.add(mnıtmSayacDurdur);
	}

}
