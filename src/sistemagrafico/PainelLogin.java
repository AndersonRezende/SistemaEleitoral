/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Anderson
 */
public class PainelLogin extends JPanel
{
    private JPanel panelThis = this;                                            //Referência da própria classe
    private JPanel panelGridLogin;
    
    private JLabel labelEleicoes;
    private JLabel labelLogin;
    private JLabel labelSenha;
    
    private JTextField textFieldLogin;
    private JPasswordField passwordFieldSenha;
    
    private JButton buttonLogin;
    
    
    PainelLogin()
    {
        panelThis.setLayout(new GridLayout(2,2));
        panelGridLogin = new JPanel(new GridLayout(2,2,2,2));
        
        labelEleicoes = new JLabel("ELEIÇÕES");
        labelLogin = new JLabel("Login");
        labelSenha = new JLabel("Senha");
        
        textFieldLogin = new JTextField();
        passwordFieldSenha = new JPasswordField();
        
        panelThis.add(labelLogin);
        panelThis.add(textFieldLogin);
        panelThis.add(labelSenha);
        panelThis.add(passwordFieldSenha);
        
        
        
        //panelThis.add(panelGridLogin, BorderLayout.CENTER);
        //panelThis.setBackground(Color.red);
        //panelThis.add(new JButton("Teste"));
    }
    
    
    public JPanel getPanelThis()
    {   return this.panelThis;  }
}
