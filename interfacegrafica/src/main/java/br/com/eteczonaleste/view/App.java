package br.com.eteczonaleste.view;

import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.eteczonaleste.model.Cliente;
import br.com.eteczonaleste.controller.ClienteJpaDAO;


public class App extends JFrame{
	
	MaskFormatter formatTel = null;
	
	JLabel lblTelefone = new JLabel("Telefone:");
	JFormattedTextField txtTelefone = null;
	JComboBox cboEstado = new JComboBox();
	JRadioButton[] rdnSexo = new JRadioButton[2];
	ButtonGroup grupo = new ButtonGroup();
	
	JButton btnSalvar = new JButton("Salvar");
	JLabel lblNome = new JLabel("Nome:");
	JTextField txtNome = new JTextField();

	Cliente cliente = new Cliente();
	
	
	public App(){
		super("Aula");
		
		Container paine = this.getContentPane();
		paine.setLayout(null);
		
		
		cboEstado.addItem("AC");		cboEstado.addItem("AL");
		cboEstado.addItem("AP");		cboEstado.addItem("AM");
		cboEstado.addItem("BA");		cboEstado.addItem("CE");
		cboEstado.addItem("DF");		cboEstado.addItem("ES");
		cboEstado.addItem("GO");		cboEstado.addItem("MA");
		cboEstado.addItem("MT");		cboEstado.addItem("MS");
		cboEstado.addItem("MG");		cboEstado.addItem("PA");
		cboEstado.addItem("PB");		cboEstado.addItem("PR");
		cboEstado.addItem("PE");		cboEstado.addItem("PI");
		cboEstado.addItem("RJ");		cboEstado.addItem("RN");
		cboEstado.addItem("RS");		cboEstado.addItem("RO");
		cboEstado.addItem("RR");		cboEstado.addItem("SC");
		cboEstado.addItem("SP");		cboEstado.addItem("SE");
		cboEstado.addItem("TO");		
		cboEstado.setBounds(20, 20, 200, 20);
		paine.add(cboEstado);
		
		rdnSexo[0] = new JRadioButton("Masculino");
		rdnSexo[1] = new JRadioButton("Feminino");
		
		grupo.add(rdnSexo[0]);
		grupo.add(rdnSexo[1]);
		
		rdnSexo[0].setBounds(20, 60, 100, 20);
		paine.add(rdnSexo[0]);
		
		rdnSexo[1].setBounds(120, 60, 100, 20);
		paine.add(rdnSexo[1]);
		
		lblTelefone.setBounds(20, 90, 80, 20);
		paine.add(lblTelefone);
		
		try {  
			formatTel = new MaskFormatter("(##)#####-####");
			txtTelefone = new JFormattedTextField(formatTel);
		} catch (Exception ex) {  
            ex.printStackTrace();  
		}  
		
		txtTelefone.setBounds(90, 90, 150, 20);
		paine.add(txtTelefone);
		
		lblNome.setBounds(20, 120, 80, 20);
		paine.add(lblNome);
		
		txtNome.setBounds(90, 120, 150, 20);
		paine.add(txtNome);
		
		paine.add(btnSalvar);
		btnSalvar.setBounds(90, 180, 130, 30);
		btnSalvar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cliente.setEstado(String.valueOf(cboEstado.getSelectedItem()));
				
				if (rdnSexo[0].isSelected()) {
					cliente.setSexo("Masculino");
				}else if(rdnSexo[1].isSelected()) {
					cliente.setSexo("Feminino");
				}
				
				cliente.setTelefone(txtTelefone.getText());
				
				cliente.setNome(txtNome.getText());
				
				ClienteJpaDAO.getInstance().merge(cliente);
				
			}
		});
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500, 300);
		
	}
	public static void main(String args[]){
		App tela = new App();
	}
}
