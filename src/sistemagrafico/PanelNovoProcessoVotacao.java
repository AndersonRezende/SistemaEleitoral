/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import arquivo.VerificaArquivo;
import enumeracao.EnumListaPanels;
import enumeracao.EnumOpcoesMenu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
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
public class PanelNovoProcessoVotacao extends JPanel implements ListSelectionListener, ComponentListener
{
    private File file;
    private File[] arquivos;
    private Font fontListOpcoesMenu;
    private Font fontLabelTextoSuperior;
    private Container container;
    private CardLayout cardManager;
    private JPanel panelContainerTelaVez;
    private JPanel panelLayoutNovoProcessoVotacao;
    private JLabel labelNovoProcessoVotacaoSuperior;
    private JLabel labelLayoutArquivos;
    private JList listOpcoesMenu;
    private DefaultListModel listModelOpcoesMenu;
    
    
    
        public PanelNovoProcessoVotacao(JPanel panelNovoProcessoVotacao, Container container, CardLayout cardManager, JPanel panelContainerTelaVez)
        {
            this.container = container;
            this.panelContainerTelaVez = panelContainerTelaVez;
            this.cardManager = cardManager;
            
            fontListOpcoesMenu = new Font(Font.SERIF, Font.PLAIN, 25);
            fontLabelTextoSuperior = new Font(Font.SERIF, Font.BOLD, 25);
            
            labelNovoProcessoVotacaoSuperior = new JLabel(EnumOpcoesMenu.NPV.getOpcao());
            labelNovoProcessoVotacaoSuperior.setFont(fontLabelTextoSuperior);
            labelNovoProcessoVotacaoSuperior.setHorizontalAlignment(JLabel.CENTER);
            
            labelLayoutArquivos = new JLabel("ARQUIVOS DE ELEIÇÃO");
            labelLayoutArquivos.setFont(fontListOpcoesMenu);
            
            configurarLista();
            
            panelLayoutNovoProcessoVotacao = new JPanel(new BorderLayout());
            panelLayoutNovoProcessoVotacao.add(listOpcoesMenu, BorderLayout.CENTER);
            panelLayoutNovoProcessoVotacao.add(labelLayoutArquivos, BorderLayout.NORTH);
            
            
            panelNovoProcessoVotacao.add(labelNovoProcessoVotacaoSuperior, BorderLayout.NORTH);
            panelNovoProcessoVotacao.add(panelLayoutNovoProcessoVotacao, BorderLayout.CENTER);
            panelNovoProcessoVotacao.addComponentListener(this);
        }
    
        
        private void configurarLista()
        {
            listModelOpcoesMenu = new DefaultListModel();
            
            file = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Eleição");
            arquivos = file.listFiles();
            
            for (File arquivo : arquivos) 
                if (arquivo.isFile()) 
                {
                    if(VerificaArquivo.checarArquivoEleicaoPresidencial(arquivo.getPath()))
                        listModelOpcoesMenu.addElement(arquivo.getName().substring(0, arquivo.getName().indexOf(".txt")));
                }
            listOpcoesMenu = new JList(listModelOpcoesMenu);
            listOpcoesMenu.setFont(fontListOpcoesMenu);
            listOpcoesMenu.addListSelectionListener(this);  
            listOpcoesMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            if(panelLayoutNovoProcessoVotacao != null)
            {
                panelLayoutNovoProcessoVotacao.remove(listOpcoesMenu);
                panelLayoutNovoProcessoVotacao.add(listOpcoesMenu, BorderLayout.CENTER);
            }
        }

        
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        
    }

    @Override
    public void componentResized(ComponentEvent e){}

    @Override
    public void componentMoved(ComponentEvent e){}

    @Override
    public void componentShown(ComponentEvent e) 
    {   configurarLista();  }

    @Override
    public void componentHidden(ComponentEvent e) 
    {   configurarLista();  }
}
