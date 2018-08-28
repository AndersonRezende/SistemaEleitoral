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
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Anderson
 */
public class PanelLogado extends JPanel implements ListSelectionListener, ComponentListener
{
    private Font fontListOpcoesMenu = new Font(Font.SERIF, Font.PLAIN, 25);
    private Container container;
    private CardLayout cardManager;
    private JPanel panelContainerTelaVez;
    private JPanel panelLayoutLogado;
    private JList listOpcoesMenu;
    private DefaultListModel listModelOpcoesMenu;
    
    PanelLogado(JPanel panelMesario, Container container, CardLayout cardManager, JPanel panelContainerTelaVez) 
    {
        this.container = container;
        this.panelContainerTelaVez = panelContainerTelaVez;
        this.cardManager = cardManager;
        
        panelLayoutLogado = new JPanel(new BorderLayout());
        
        listModelOpcoesMenu = new DefaultListModel();
        listModelOpcoesMenu.addElement("NOVO PROCESSO DE VOTAÇÃO");
        listModelOpcoesMenu.addElement("LIBERAR PRÓXIMO VOTO");
        listModelOpcoesMenu.addElement("FINALIZAR PROCESSO DE VOTAÇÃO");
        listModelOpcoesMenu.addElement("SAIR");
        
        listOpcoesMenu = new JList(listModelOpcoesMenu);
        listOpcoesMenu.setBounds(200,150,150,80);
        listOpcoesMenu.setFont(fontListOpcoesMenu);
        //listOpcoesMenu.setSelectionModel(ListSelectionModel.SINGLE_SELECTION);
        listOpcoesMenu.addListSelectionListener(this);  
        listOpcoesMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        panelLayoutLogado.add(listOpcoesMenu, BorderLayout.WEST);
        panelMesario.add(panelLayoutLogado, BorderLayout.WEST);
        panelMesario.addComponentListener(this);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == listOpcoesMenu)
        {
            if(listOpcoesMenu.getSelectedIndex() == (listModelOpcoesMenu.getSize() - 1))
            {   
                int opcao = JOptionPane.showConfirmDialog(container, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                if(opcao == JOptionPane.YES_OPTION)
                    cardManager.show(panelContainerTelaVez, "panelLogin");
            }
        }
        //listOpcoesMenu.setSelectedIndex(0);
    }
    
    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) 
    {
        listOpcoesMenu.setSelectedIndex(5);
    }

    @Override
    public void componentHidden(ComponentEvent e) 
    {
        listOpcoesMenu.setSelectedIndex(5);
    }
}
