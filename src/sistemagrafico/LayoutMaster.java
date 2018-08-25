/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Anderson
 */
public class LayoutMaster extends JFrame
{
    private Container container = getContentPane();                            //Container para pegar referência da tela
    private CardLayout cardManager;                                             //Gerenciador de layout para trabalhar com diferentes telas
    
    //-------------------------------------VARIÁVEIS DO TIPO PANEL------------------------------------------
    private JPanel panelPrincipal;                                              //Painel principal que conterá todos os outros paineis
    private JPanel panelInferior;
    private JPanel panelContainerTelaVez;                                                //Painel que ficará sobre o painel principal que conterá as diferentes telas do sistema
    private JPanel panelLogin;
    
    
    public LayoutMaster()
    {
        super("SISTEMA ELEITORAL");                                          
        panelPrincipal = new JPanel(new BorderLayout());
       
        cardManager = new CardLayout();                                         //Objeto que vai gerênciar os layouts
        panelContainerTelaVez = new JPanel(cardManager);                                 //panelPrincipal.setLayout(cardManager); 
        
        configurarTelaLogin(); 
        
        panelPrincipal.add(panelContainerTelaVez, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        container.add(panelPrincipal, BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible( true );
    }
    
    
    
    
    private void configurarTelaLogin()
    {
        //JLabel
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        panelLogin = new JPanel();
        panelLogin.setLayout(groupLayout);
        
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(rootPane)
        
        );
    }
}
