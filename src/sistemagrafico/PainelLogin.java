/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

/**
 *
 * @author Anderson
 */
public class PainelLogin extends JPanel
{
    private JPanel panelThis = this;                                            //Referência da própria classe
    private JPanel panelGridLogin;
    
    private SpringLayout springLayout;
    
    private JLabel labelEleicoes;
    private JLabel labelLogin;
    private JLabel labelSenha;
    
    private JTextField textFieldLogin;
    private JPasswordField passwordFieldSenha;
    
    private JButton buttonLogin;
    
    
    PainelLogin()
    {   
        panelThis.setLayout(new BorderLayout());
        panelGridLogin = new JPanel(new GridLayout(2,2,5,5));
        
        labelEleicoes = new JLabel("SISTEMA ELEITORAL");
        labelEleicoes.setFont(new Font(Font.SERIF, Font.BOLD, 26));
        labelEleicoes.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogin = new JLabel("Login");
        labelSenha = new JLabel("Senha");
        
        
        textFieldLogin = new JTextField();
        passwordFieldSenha = new JPasswordField();
        
        buttonLogin = new JButton("LOGIN");
        
        panelGridLogin.add(labelLogin);
        panelGridLogin.add(textFieldLogin);
        panelGridLogin.add(labelSenha);
        panelGridLogin.add(passwordFieldSenha);
        panelGridLogin.setBorder(BorderFactory.createTitledBorder("LOGIN"));
        
        panelThis.add(labelEleicoes, BorderLayout.NORTH);
        panelThis.add(panelGridLogin, BorderLayout.CENTER);
        
        //panelThis.setBackground(Color.red);
    }
    
    
    public JPanel getPanelThis()
    {   return this.panelThis;  }
}
