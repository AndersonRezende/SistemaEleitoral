/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Anderson
 */
public class PanelLogin extends JPanel implements ActionListener, KeyListener
{
    private Container container;
    private JPanel panelContainerTelaVez;
    private CardLayout cardManager;
    private JLabel labelEleicoes;
    private JLabel labelLogin;
    private JLabel labelSenha;
    private JLabel labelNomeTela;
    private JTextField textFieldlogin;
    private JPasswordField passwordFieldSenha;
    private JButton buttonLogin;
    private Font fontLabelsInputs;
    private JPanel panelLayoutLogin;
            
    
    public PanelLogin(JPanel panelLogin, Container container, CardLayout cardManager, JPanel panelContainerTelaVez)
    {   
        this.container = container;
        this.panelContainerTelaVez = panelContainerTelaVez;
        this.cardManager = cardManager;
        labelEleicoes = new JLabel("ELEIÇÕES "+Calendar.getInstance().get(Calendar.YEAR));
        labelLogin = new JLabel("Login");
        labelSenha = new JLabel("Senha");
        labelNomeTela = new JLabel("MESÁRIO - LOGIN");
    
        textFieldlogin = new JTextField();
        passwordFieldSenha = new JPasswordField();
            
        buttonLogin = new JButton("LOGIN");      
        
        fontLabelsInputs = new Font(Font.SERIF, Font.PLAIN, 20);
        panelLayoutLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        labelEleicoes.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        labelEleicoes.setHorizontalAlignment(JLabel.CENTER);
        labelNomeTela.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        labelLogin.setFont(fontLabelsInputs);
        labelSenha.setFont(fontLabelsInputs);
        textFieldlogin.setFont(fontLabelsInputs);
        passwordFieldSenha.setFont(fontLabelsInputs);
        textFieldlogin.setColumns(15);
        passwordFieldSenha.setColumns(15);
        textFieldlogin.addKeyListener(this);
        passwordFieldSenha.addKeyListener(this);
        textFieldlogin.addActionListener(this);
        buttonLogin.addActionListener(this);
        
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

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == buttonLogin)
        {
            String login = (String) textFieldlogin.getText();
            String senha = new String(passwordFieldSenha.getPassword());
            if(login.equals("login") && senha.equals("senha"))
                cardManager.show(panelContainerTelaVez, "panelMesario");
            else                                                            //Aqui contém o JOPTIONPANE
                JOptionPane.showMessageDialog(container, "Usuário inválido, tente novamente!", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
               {
                String login = (String) textFieldlogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
                if(login.equals("login") && senha.equals("senha"))
                    cardManager.show(panelContainerTelaVez, "panelMesario");
                else                                                            //Aqui contém o JOPTIONPANE
                    JOptionPane.showMessageDialog(container, "Usuário inválido, tente novamente!", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE, null);
               }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    
}
