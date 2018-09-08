/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import arquivo.LeituraArquivo;
import arquivo.VerificaArquivo;
import enumeracao.EnumListaPanels;
import enumeracao.EnumOpcoesMenu;
import excecoes.FormatoIncorretoException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import objetos.Eleicao;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class PanelNovoProcessoVotacao extends JPanel implements ActionListener, ListSelectionListener, ComponentListener
{
    private ProcessoVotacao processoVotacao;
    private File arquivo;
    private File[] arquivos;
    private ArrayList<Eleicao> eleicoes;
    private Font fontListOpcoesMenu;
    private Font fontLabelTextoSuperior;
    private Font fontButton;
    private Container container;
    private CardLayout cardManager;
    private JPanel panelContainerTelaVez;
    private JPanel panelLayoutNovoProcessoVotacao;
    private JPanel panelBotoes;
    private JLabel labelNovoProcessoVotacaoSuperior;
    private JLabel labelLayoutArquivos;
    private JList listOpcoesMenu;
    private DefaultListModel listModelOpcoesMenu;
    private JButton buttonSelecionar;
    private JButton buttonCancelar;
    private JTextArea textAreaDetalhesArquivo;
    private JScrollPane scrollTextArea;
    
    
    
    public PanelNovoProcessoVotacao(JPanel panelNovoProcessoVotacao, Container container, CardLayout cardManager, JPanel panelContainerTelaVez, ProcessoVotacao processoVotacao)
    {
        this.container = container;
        this.panelContainerTelaVez = panelContainerTelaVez;
        this.cardManager = cardManager;
        this.processoVotacao = processoVotacao;
            
        fontButton = new Font(Font.SERIF, Font.PLAIN, 15);
        fontListOpcoesMenu = new Font(Font.SERIF, Font.PLAIN, 25);
        fontLabelTextoSuperior = new Font(Font.SERIF, Font.BOLD, 25);
            
        labelNovoProcessoVotacaoSuperior = new JLabel(EnumOpcoesMenu.NPV.getOpcao());
        labelNovoProcessoVotacaoSuperior.setFont(fontLabelTextoSuperior);
        labelNovoProcessoVotacaoSuperior.setHorizontalAlignment(JLabel.CENTER);

        labelLayoutArquivos = new JLabel("ARQUIVOS DE ELEIÇÃO DISPONÍVEIS");
        labelLayoutArquivos.setFont(fontListOpcoesMenu);

        listModelOpcoesMenu = new DefaultListModel();
        //configurarLista();
        configurarBotoes();
        configurarTextArea();
        
        listOpcoesMenu = new JList(listModelOpcoesMenu);
        listOpcoesMenu.setFont(fontListOpcoesMenu);
        listOpcoesMenu.addListSelectionListener(this);  
        listOpcoesMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panelBotoes = new JPanel(new BorderLayout());
        panelBotoes.add(buttonCancelar, BorderLayout.WEST);
        panelBotoes.add(buttonSelecionar, BorderLayout.EAST);
        
        panelLayoutNovoProcessoVotacao = new JPanel(new BorderLayout());
        panelLayoutNovoProcessoVotacao.add(listOpcoesMenu, BorderLayout.CENTER);
        panelLayoutNovoProcessoVotacao.add(labelLayoutArquivos, BorderLayout.NORTH);
        panelLayoutNovoProcessoVotacao.add(panelBotoes,BorderLayout.SOUTH);
        panelLayoutNovoProcessoVotacao.add(scrollTextArea, BorderLayout.EAST);
        
        panelNovoProcessoVotacao.add(labelNovoProcessoVotacaoSuperior, BorderLayout.NORTH);
        panelNovoProcessoVotacao.add(panelLayoutNovoProcessoVotacao, BorderLayout.CENTER);
        panelNovoProcessoVotacao.addComponentListener(this);
    }
    
    //------------------------------AUXILIARES----------------------------------
    private void exibirInfoTextArea()
    {
        File file = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Eleição\\"+listOpcoesMenu.getSelectedValue()+".txt");
        eleicoes = LeituraArquivo.lerArquivoEleicao(file.getPath());
        String texto = "";
        
        if(eleicoes.size() > 0)
        {
            texto = ""+eleicoes.get(0).getEleicao()+"\n\n";
            for(Eleicao eleicao : eleicoes)
            {
                texto += "Cargo: "+eleicao.getTitulo()+".\n";
                if(eleicao.getVice())
                    texto += "Possui vice: Sim.\n";
                else
                    texto += "Possui vice: Não.\n";
                texto += "Quantidade de dígitos para voto: "+eleicao.getDigitos()+".\n";
                texto += "Quantidade de escolhas neste cargo: "+eleicao.getVotos()+".\n";
                texto += "Quantidade de eleitos neste cargo: "+eleicao.getEleitos()+".\n";
                texto += "\n";
            }
        }
        textAreaDetalhesArquivo.setText(texto);
        scrollTextArea.getViewport().setViewPosition(new java.awt.Point(0, 0)); 
        textAreaDetalhesArquivo.setCaretPosition(0);
        
        processoVotacao.setEleicoes(eleicoes);
    }
        
    //-----------------------------CONFIGURAÇÕES--------------------------------
    private void configurarLista()
    {
        //listModelOpcoesMenu = new DefaultListModel();
        listModelOpcoesMenu.removeAllElements();

        arquivo = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Eleição");
        arquivos = arquivo.listFiles();

        for (File arquivoAux : arquivos) 
            if (arquivoAux.isFile()) 
            {
                try
                {
                    if(VerificaArquivo.checarArquivoEleicaoPresidencial(arquivoAux.getPath()))
                        listModelOpcoesMenu.addElement(arquivoAux.getName().substring(0, arquivoAux.getName().indexOf(".txt")));
                    else
                        throw new FormatoIncorretoException(arquivoAux.getName());
                }
                catch(FormatoIncorretoException ex)
                {   JOptionPane.showMessageDialog(container, ex.getMessage(), "Erro no processamento do arquivo", JOptionPane.ERROR_MESSAGE, null);   }
            }
    }

    private void configurarBotoes()
    {
        buttonSelecionar = new JButton("SELECIONAR");
        buttonCancelar = new JButton("CANCELAR");

        buttonSelecionar.setEnabled(false);
        buttonSelecionar.setToolTipText("Selecione um arquivo antes de prosseguir");
        buttonCancelar.setToolTipText("Voltar ao menu principal");
        buttonSelecionar.setFont(fontButton);
        buttonCancelar.setFont(fontButton);

        buttonSelecionar.addActionListener(this);
        buttonCancelar.addActionListener(this);
    }

    private void configurarTextArea()
    {
        textAreaDetalhesArquivo = new JTextArea(0, 50);
        textAreaDetalhesArquivo.setFont(fontListOpcoesMenu);
        textAreaDetalhesArquivo.setEditable(false);
        textAreaDetalhesArquivo.setBackground(Color.lightGray);
        textAreaDetalhesArquivo.setText("Nenhum arquivo selecionado.");
        textAreaDetalhesArquivo.setToolTipText("Selecione um arqvuio na lista para visualizar informações");
        scrollTextArea = new JScrollPane(textAreaDetalhesArquivo);
        scrollTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    
    //-------------------------------LISTENERS----------------------------------
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == buttonSelecionar && buttonSelecionar.isEnabled() && !listOpcoesMenu.isSelectionEmpty())
        {
            processoVotacao.iniciarProcessoVotacao();
            cardManager.show(panelContainerTelaVez, EnumListaPanels.MESARIO.getOpcao());
        }
        if(e.getSource() == buttonCancelar)
        {
            int opcao = JOptionPane.showConfirmDialog(container, "Deseja realmente cancelar essa operação?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if(opcao == JOptionPane.YES_OPTION)
                cardManager.show(panelContainerTelaVez, EnumListaPanels.MESARIO.getOpcao());
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == listOpcoesMenu)
        {
            if(!buttonSelecionar.isEnabled())
            {
                buttonSelecionar.setEnabled(true);
                buttonSelecionar.setToolTipText("Iniciar processo de votação");
            }     
            exibirInfoTextArea();
        }
    }

    @Override
    public void componentResized(ComponentEvent e)
    {   textAreaDetalhesArquivo.setColumns((panelLayoutNovoProcessoVotacao.getWidth()*50)/(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());    }//Redimensiona proporcionalmente    

    @Override
    public void componentMoved(ComponentEvent e)
    {   textAreaDetalhesArquivo.setColumns((panelLayoutNovoProcessoVotacao.getWidth()*50)/(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());    }

    @Override
    public void componentShown(ComponentEvent e) 
    {   configurarLista();  }

    @Override
    public void componentHidden(ComponentEvent e) 
    {}

}
