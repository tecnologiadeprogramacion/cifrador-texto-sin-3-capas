import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaCifrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCifrador frame = new VentanaCifrador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCifrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("TdP :: Cifrador de texto SIN modelo de 3 capas");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.WEST);
		panelBotones.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton botonCifrar = new JButton("Cifrar texto");
		botonCifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aCifrar = textArea.getText();
				aCifrar = aCifrar.replace('a', '1');
				aCifrar = aCifrar.replace('e', '2');
				aCifrar = aCifrar.replace('i', '3');
				aCifrar = aCifrar.replace('o', '4');
				aCifrar = aCifrar.replace('u', '5');
				aCifrar = aCifrar.replace('A', '6');
				aCifrar = aCifrar.replace('E', '7');
				aCifrar = aCifrar.replace('I', '8');
				aCifrar = aCifrar.replace('O', '9');
				aCifrar =  aCifrar.replace('U', '0');
				textArea.setText(aCifrar);
			}
		});
		panelBotones.add(botonCifrar);
		
		JButton botonGuardar = new JButton("Guardar texto");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoAGuardar = textArea.getText();
				BufferedWriter archivo;
				try {
					archivo = new BufferedWriter(new FileWriter("archivo_cifrado.tdp"));
					archivo.write(textoAGuardar);
					archivo.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelBotones.add(botonGuardar);
		
		JButton botonCargar = new JButton("Cargar último texto");
		botonCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lineaLeida;
				String textoLeido = "";
				BufferedReader archivo;
				try {
					archivo = new BufferedReader(new FileReader("archivo_cifrado.tdp"));
					while((lineaLeida = archivo.readLine()) != null) {
						textoLeido += lineaLeida;
					}
					archivo.close();
				} catch (IOException e1) {
					textoLeido = "No existe un último archivo";
				}
				textArea.setText(textoLeido);
			}
		});
		panelBotones.add(botonCargar);
		
		JPanel panelTexto = new JPanel();
		contentPane.add(panelTexto, BorderLayout.CENTER);
		
		textArea = new JTextArea(15,25);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panelTexto.add(textArea);
		
	}

}
