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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class PanelLogin extends JPanel implements ActionListener, KeyListener, ComponentListener
{
    private Container container;
    private JPanel panelContainerTelaVez;
    private JPanel panelLayoutLogin;
    private CardLayout cardManager;
    private JLabel labelEleicoes;
    private JLabel labelLogin;
    private JLabel labelSenha;
    private JLabel labelNomeTela;
    private JTextField textFieldlogin;
    private JPasswordField passwordFieldSenha;
    private JButton buttonLogin;
    private Font fontLabelsInputs;
            
    
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
        panelLogin.addComponentListener(this);
    }

    
    public boolean logar(String login, String senha)
    {
        boolean logado = false;
        if(login.equals("login") && senha.equals("senha"))
            logado = true;
        return logado;
    }
    
    
    public void trocarPanel()
    {
        textFieldlogin.setText("");
        passwordFieldSenha.setText("");
        this.updateUI();
        cardManager.show(panelContainerTelaVez, "panelMesario");
    }
    
    
    //ActionListener
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == buttonLogin)
        {
            String login = (String) textFieldlogin.getText();
            String senha = new String(passwordFieldSenha.getPassword());
            if(logar(login, senha))
                trocarPanel();
            else                                                            //Aqui contém o JOPTIONPANE
                JOptionPane.showMessageDialog(container, "Usuário inválido, tente novamente!", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    
    //KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String login = (String) textFieldlogin.getText();
            String senha = new String(passwordFieldSenha.getPassword());
            if(logar(login, senha))
                trocarPanel();
            else                                                            //Aqui contém o JOPTIONPANE
            {
                passwordFieldSenha.setText("");
                JOptionPane.showMessageDialog(container, "Usuário inválido, tente novamente!", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE, null);
            }
        }
        
        //if(e.getKeyCode() == KeyEvent.VK_CONTROL &&)
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    //ComponentListener
    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) 
    {
        textFieldlogin.setText("");
        passwordFieldSenha.setText("");
    }

    @Override
    public void componentHidden(ComponentEvent e) 
    {
        textFieldlogin.setText("");
        passwordFieldSenha.setText("");
    }
}
