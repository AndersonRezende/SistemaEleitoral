/*
 * AQUI CONTÉM JOPTIONPANE
 * AQUI É PARTE DE UM PACOTE
 * O pacote “sistemagrafico” é referente as classes que constroem os recursos gráficos 
 * do sistema: Janelas, botões, caixas de diálogo, etc.
 */
package sistemagrafico;

import enumeracao.EnumListaPanels;
import enumeracao.EnumOpcoesMenu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import objetos.Candidato;
import objetos.Eleicao;
import objetos.Eleitor;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class LayoutMaster extends JFrame implements ActionListener
{
    private ProcessoVotacao processoVotacao;
    private ArrayList<Eleicao> eleicoes;
    private ArrayList<Candidato> candidatos;
    private ArrayList<Eleitor> eleitores;
    
    private String stringTextoAjudaSobre;
    private Dimension tamanhoTela;
    private Container container = getContentPane();                            
    private CardLayout cardManager;                                             

    private JPanel panelPrincipal;                                              
    private JPanel panelInferior;
    private JPanel panelContainerTelaVez;                                            
    private JPanel panelLogin;
    private JPanel panelMesario;                                                //PanelLogado
    private JPanel panelNovoProcessoVotacao;
    
    private JLabel labelInferior;
    
    private JMenuBar menu;
    private JMenu menuArquivo;
    private JMenu menuAcoes;
    private JMenu menuAjuda;
    
    
    private JMenuItem menuItemArquivoSair;
    private JMenuItem menuItemAcaoMenuMesario;
    private JMenuItem menuItemAcaoNovoProcessoVotacao;
    private JMenuItem menuItemAcaoCancelar;
    private JMenuItem menuItemAjudaSobre;
    
    
    
    public LayoutMaster()
    {
        super("SISTEMA ELEITORAL"); 
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        stringTextoAjudaSobre = "SOBRE\n\n"
                + "Sistema eleitoral "+Calendar.getInstance().get(Calendar.YEAR)+".\n"
                + "Versão 1.0.0\n"
                + "Java: 1.8.0_151\n"
                + "Copyright © Todos os direitos reservados.";
        
        eleicoes = new ArrayList();
        processoVotacao = new ProcessoVotacao();
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelLogin = new JPanel(new BorderLayout());
        panelMesario = new JPanel(new BorderLayout());
        panelNovoProcessoVotacao = new JPanel(new BorderLayout());
        
        panelLogin.setName(EnumListaPanels.LOGIN.getOpcao());
        panelMesario.setName(EnumListaPanels.MESARIO.getOpcao());
        panelNovoProcessoVotacao.setName(EnumListaPanels.NOVO_PROC_VOTACAO.getOpcao());
        
        cardManager = new CardLayout();                                         
        panelContainerTelaVez = new JPanel(cardManager);      
        panelContainerTelaVez.add(panelLogin, EnumListaPanels.LOGIN.getOpcao());
        panelContainerTelaVez.add(panelMesario, EnumListaPanels.MESARIO.getOpcao());
        panelContainerTelaVez.add(panelNovoProcessoVotacao, EnumListaPanels.NOVO_PROC_VOTACAO.getOpcao());
        
        //#########################CONFIGURAR PAINEIS###########################
        configurarMenu();
        configurarPanelInferior();
        configurarTelaLogin();
        configurarTelaMesario();
        configurarTelaNovoProcessoVotacao();
        //#########################CONFIGURAR PAINEIS###########################

        
        panelPrincipal.add(panelContainerTelaVez, BorderLayout.CENTER);
        
        container.add(panelInferior, BorderLayout.SOUTH);
        container.add(panelPrincipal, BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible( true );
        this.setMinimumSize(new Dimension(tamanhoTela.width/2, tamanhoTela.width/2));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(""+new File("").getAbsoluteFile()+"/assets/eleicaoLogo.png"));
        this.setJMenuBar(menu);
    }
    
 
    //#####################MÉTODOS DE CONFIGURAÇÃO##############################
    private void configurarMenu() 
    {
        menu = new JMenuBar();
        
        menuArquivo = new JMenu("Arquivo");
        menuAcoes = new JMenu("Ações");
        menuAjuda = new JMenu("Ajuda");
        
        menuItemArquivoSair = new JMenuItem("Sair");
        menuItemAcaoMenuMesario = new JMenuItem("Menu principal");
        menuItemAcaoNovoProcessoVotacao = new JMenuItem(EnumOpcoesMenu.NPV.getOpcaoParaMenu());
        menuItemAcaoCancelar = new JMenuItem("Cancelar");
        menuItemAjudaSobre = new JMenuItem("Sobre");
        
        menuArquivo.setMnemonic('A');
        menuAjuda.setMnemonic('j');
        menuAcoes.setMnemonic('e');
        menuItemArquivoSair.setMnemonic('S');
        menuItemAcaoMenuMesario.setMnemonic('M');
        menuItemAcaoNovoProcessoVotacao.setMnemonic('N');
        menuItemAcaoCancelar.setMnemonic('C');
        menuItemAjudaSobre.setMnemonic('S');
        
        menuArquivo.add(menuItemArquivoSair);
        menuAcoes.add(menuItemAcaoMenuMesario);
        menuAcoes.add(menuItemAcaoNovoProcessoVotacao);
        menuAcoes.addSeparator();
        menuAcoes.add(menuItemAcaoCancelar);
        menuAjuda.add(menuItemAjudaSobre);
        menu.add(menuArquivo);
        menu.add(menuAcoes);
        menu.add(menuAjuda);
        
        menuItemArquivoSair.addActionListener(this);
        menuItemAcaoMenuMesario.addActionListener(this);
        menuItemAcaoNovoProcessoVotacao.addActionListener(this);
        menuItemAcaoCancelar.addActionListener(this);
        menuItemAjudaSobre.addActionListener(this);
    }
    
    private void configurarPanelInferior()
    {
        panelInferior = new JPanel();
        panelInferior.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelInferior = new JLabel("SISTEMA ELEITORAL");
        labelInferior.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        panelInferior.add(labelInferior);
    }
    
    private void configurarTelaLogin()
    {
        panelLogin = new PanelLogin(panelLogin, container, cardManager, panelContainerTelaVez, menu, processoVotacao);
    }
    
    private void configurarTelaMesario()
    {
        panelMesario = new PanelLogado(panelMesario, container, cardManager, panelContainerTelaVez, processoVotacao);
    }
    
    private void configurarTelaNovoProcessoVotacao()
    {
        panelNovoProcessoVotacao = new PanelNovoProcessoVotacao(panelNovoProcessoVotacao, container, cardManager, panelContainerTelaVez, processoVotacao);
    }

    
    //########################MÉTODOS LISTENERS#################################
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == menuItemArquivoSair)
            System.exit(0);
        if(e.getSource() == menuItemAjudaSobre)
            JOptionPane.showMessageDialog(container, stringTextoAjudaSobre, "AJUDA", JOptionPane.INFORMATION_MESSAGE, null);
        if(e.getSource() == menuItemAcaoMenuMesario)
        {
            int opcao = JOptionPane.showConfirmDialog(container, "Deseja realmente voltar ao menu principal?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if(opcao == JOptionPane.YES_OPTION)
                cardManager.show(panelContainerTelaVez, EnumListaPanels.MESARIO.getOpcao());
        }
        if(e.getSource() == menuItemAcaoCancelar)
        {
            int opcao = JOptionPane.showConfirmDialog(container, "Deseja realmente cancelar essa operação?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if(opcao == JOptionPane.YES_OPTION)
                cardManager.show(panelContainerTelaVez, EnumListaPanels.MESARIO.getOpcao());
        }
        if(e.getSource() == menuItemAcaoNovoProcessoVotacao)
        {
            int opcao = JOptionPane.showConfirmDialog(container, "Deseja iniciar um novo processo de votação?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if(opcao == JOptionPane.YES_OPTION)
                cardManager.show(panelContainerTelaVez, EnumListaPanels.NOVO_PROC_VOTACAO.getOpcao());
        }
    }
}