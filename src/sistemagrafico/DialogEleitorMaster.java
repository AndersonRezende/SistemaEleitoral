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
import javax.swing.JPanel;
import objetos.Candidato;
import objetos.Eleicao;
import objetos.Eleitor;

/**
 *
 * @author Anderson
 */
public class DialogEleitorMaster extends JDialog 
{
    private Dimension tamanhoTela;
    private Container container = getContentPane();
    
    private CardLayout cardManager;
    private JPanel panelDialogTelaVez;
    private JPanel panelsDialogVotoVez[];
    
    //public DialogEleitorMaster(ArrayList<Eleicao> eleicoes, ArrayList<Candidato> candidatos, Eleitor eleitor)
    public DialogEleitorMaster(ArrayList<Eleicao> eleicoes)
    {
        this.setUndecorated(true);
        
        cardManager = new CardLayout();                                         
        panelDialogTelaVez = new JPanel(cardManager); 
        
        panelsDialogVotoVez = new JPanel[eleicoes.size()];                      //A quantidade de panels é referente a quantidade de cargos votados
        
        
        
        
        
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        container.setLayout(new BorderLayout());
        container.add(panelDialogTelaVez);
        
        this.setResizable(false);
        this.setSize(tamanhoTela);
        this.setModal(true);
        this.setVisible(true);
    }
    
}
