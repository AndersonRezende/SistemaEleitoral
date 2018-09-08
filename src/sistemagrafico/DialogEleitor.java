/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objetos.Eleicao;

/**
 *
 * @author Anderson
 */
public class DialogEleitor extends JDialog 
{
    private Dimension tamanhoTela;
    private Container container = getContentPane();
    
    private CardLayout cardManager;
    private JPanel panelDialogTelaVez;
    
    public DialogEleitor(ArrayList<Eleicao> eleicoes)
    {
        this.setUndecorated(true);
        
        cardManager = new CardLayout();                                         
        panelDialogTelaVez = new JPanel(cardManager); 
        
        
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        container.setLayout(new BorderLayout());
        container.add(new JLabel("Height: "+this.getHeight()+"\nWidth"+this.getWidth()));
        
        this.setResizable(false);
        this.setSize(tamanhoTela);
        this.setModal(true);
        this.setVisible(true);
    }
    
}
