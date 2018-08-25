/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private JPanel panelTelaVez;                                                //Painel que ficará sobre o painel principal que conterá as diferentes telas do sistema
    private JPanel panelLogin;
    private JPanel panelPrincipalMesario;
    private JPanel panelLiberarProximaVotacao;
    private JPanel panelConfirmarEleitor;
    private JPanel panelProcessoVotacao;
    private JPanel panelVotou;
    private JPanel panelIniciarProcessoVotacao;
    
    
    
    public LayoutMaster()
    {
        super("SISTEMA ELEITORAL");                                          
        cardManager = new CardLayout();                                         //Objeto que vai gerênciar os layouts
        
        panelPrincipal = new JPanel(new BorderLayout());     
        panelInferior = new JPanel();                                           //Explorar seções para o usuário *****************
        panelTelaVez = new JPanel(cardManager);                                 //panelPrincipal.setLayout(cardManager);
        
        JPanel p = new PainelLogin();
        
        panelTelaVez.add(p);
        
        panelInferior.add(new JLabel("SISTEMA ELEITORAL"));
        
        panelPrincipal.add(panelTelaVez, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        container.add(panelPrincipal, BorderLayout.CENTER);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible( true );
    }
}
