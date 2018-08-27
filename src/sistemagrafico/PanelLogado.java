/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Anderson
 */
public class PanelLogado extends JPanel implements ListSelectionListener
{
    private final String []opcoesMenu = {"Novo processo de votação","Finalizar processo de votação", "Liberar próximo voto", "Sair"};
    private Font fontListOpcoesMenu = new Font(Font.SERIF, Font.PLAIN, 25);
    private Container container;
    private CardLayout cardManager;
    private JPanel panelContainerTelaVez;
    private JPanel panelLayoutLogado;
    private JList listOpcoesMenu;
    
    PanelLogado(JPanel panelMesario, Container container, CardLayout cardManager, JPanel panelContainerTelaVez) 
    {
        this.container = container;
        this.panelContainerTelaVez = panelContainerTelaVez;
        this.cardManager = cardManager;
        
        panelLayoutLogado = new JPanel(new BorderLayout());
        
        listOpcoesMenu = new JList(opcoesMenu);
        listOpcoesMenu.setBounds(200,150,150,80);
        listOpcoesMenu.setFont(fontListOpcoesMenu);
        //listOpcoesMenu.setSelectionModel(ListSelectionModel.SINGLE_SELECTION);
        listOpcoesMenu.addListSelectionListener(this);
                
        panelLayoutLogado.add(listOpcoesMenu, BorderLayout.EAST);
        panelMesario.add(panelLayoutLogado, BorderLayout.EAST);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        
        if(e.getSource() == listOpcoesMenu)
        {
            int opcao = JOptionPane.showConfirmDialog(container, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if(opcao == JOptionPane.YES_OPTION)
                cardManager.show(panelContainerTelaVez, "panelLogin");
        }
    }
}
