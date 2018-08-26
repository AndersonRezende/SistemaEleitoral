/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Anderson
 */
public class LayoutMaster extends JFrame
{
    private Dimension tamanhoTela;
    private Container container = getContentPane();                            
    private CardLayout cardManager;                                             

    private JPanel panelPrincipal;                                              
    private JPanel panelInferior;
    private JPanel panelContainerTelaVez;                                            
    private JPanel panelLogin;
    
    private JLabel labelInferior;
    
    public LayoutMaster()
    {
        super("SISTEMA ELEITORAL"); 
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        
        panelPrincipal = new JPanel(new BorderLayout());
       
        cardManager = new CardLayout();                                         
        panelContainerTelaVez = new JPanel(cardManager);                                 //panelPrincipal.setLayout(cardManager); 
        
        
        configurarPanelInferior();
        configurarTelaLogin(); 
        
        
        panelContainerTelaVez.add(panelLogin);
        panelPrincipal.add(panelContainerTelaVez, BorderLayout.CENTER);
        
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        container.add(scroll);
        
        container.add(panelInferior, BorderLayout.SOUTH);
        container.add(panelPrincipal, BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible( true );
        this.setMinimumSize(new Dimension(tamanhoTela.width/2, tamanhoTela.height/2));
    }
    
    
    
    
    private void configurarTelaLogin()
    {
        JLabel labelEleicoes = new JLabel("ELEIÇÕES "+Calendar.getInstance().get(Calendar.YEAR));;
        JLabel labelLogin = new JLabel("Login");
        JLabel labelSenha = new JLabel("Senha");
        JLabel labelNomeTela = new JLabel("MESÁRIO - LOGIN");
    
        JTextField textFieldlogin = new JTextField();
        JPasswordField passwordFieldSenha = new JPasswordField();
            
        JButton buttonLogin = new JButton("LOGIN");      
        
        Font fontLabelsInputs = new Font(Font.SERIF, Font.PLAIN, 20);
        JPanel panelLayoutLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        panelLogin = new JPanel(new BorderLayout());
        
        labelEleicoes.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        labelEleicoes.setHorizontalAlignment(JLabel.CENTER);
        labelNomeTela.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        
        
        labelLogin.setFont(fontLabelsInputs);
        labelSenha.setFont(fontLabelsInputs);
        textFieldlogin.setFont(fontLabelsInputs);
        passwordFieldSenha.setFont(fontLabelsInputs);
        textFieldlogin.setColumns(15);
        passwordFieldSenha.setColumns(15);
        buttonLogin.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String login = (String) textFieldlogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
                
                
                //#############################################
                //ENVIAR LOGIN E SENHA E RECEBER TRUE OU FALSE
                /**********************************************
                *   if(logado == true)                        *
                *       trocar painel                         *
                **********************************************/
                //#############################################
            }
        });
        
        ImageIcon icon = new ImageIcon("C:/Users/Anderson/Documents/NetBeansProjects/SistemaEleitoral/assets/eleicaoMini.png");
        JLabel labelImagem = new JLabel(icon);
        
        panelLayoutLogin.add(labelImagem);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(labelNomeTela);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(labelLogin);
        panelLayoutLogin.add(textFieldlogin);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(labelSenha);
        panelLayoutLogin.add(passwordFieldSenha);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(buttonLogin);
        
        panelLogin.add(labelEleicoes, BorderLayout.NORTH);
        panelLogin.add(panelLayoutLogin, BorderLayout.CENTER);
        
    }
    
    private void configurarPanelInferior()
    {
        panelInferior = new JPanel();
        panelInferior.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelInferior = new JLabel("SISTEMA ELEITORAL");
        labelInferior.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        panelInferior.add(labelInferior);
    }
}
