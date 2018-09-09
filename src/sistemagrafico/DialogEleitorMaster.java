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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objetos.Eleicao;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class DialogEleitorMaster extends JDialog implements ActionListener
{
    private final String opcoesParaButtons[] = {"BRANCO", "CORRIGE", "CONFIRMA"};
    private final Color colorOpcoesParaButtons[] = {Color.white, Color.orange, Color.green};
    private final String opcoesParaLabelsDisplayFixo[] = {"Número: ", "Nome: ", "Partido: ", "Vice: "};
    
    private int cargoVotacaoVez;
            
    private ProcessoVotacao processoVotacao;
    private Dimension tamanhoTela;
    private Container container = getContentPane();
    
    private Font fontButton;
    private Font fontLabelDisplay;
    
    private CardLayout cardManager;
    
    private JPanel panelsDialogVotoVez[];
    private JPanel panelParaBotoes;
    private JPanel panelParaBotoesNumericos;
    private JPanel panelParaBotoesOpcoes;
    private JPanel panelParaBotoesInformacaoSuperior;
    private JPanel panelParaDisplay;
    private JPanel panelParaDisplayParaParteSuperior;
    private JPanel panelParaDisplayParaParteMeio;
    private JPanel panelParaDisplayParaParteInferior;
    private JPanel panelParaDisplayParaParteMeioParaNumeros;
    private JPanel panelParaDisplayParaParteMeioCargos[];
    private JPanel panelParaDisplayParaParteMeioNumeros[];
    
    
    private JButton buttonNumericoTela[];
    private JButton buttonOpcoesTela[];
    private ImageIcon imageIconLogoJusticaEleitoral;
    private JLabel labelJusticaEleitoralTelaPrincipal;
    private JLabel labelJusticaEleitoral;
    private JLabel labelLogoJusticaEleitoral;
    private JLabel labelParaDisplayParaParteSuperior[];
    private JLabel labelParaDisplayParaParteMeioFixo[];
    private JLabel labelParaDisplayParaParteMeioDinamicos[];
    
    
    public DialogEleitorMaster(ProcessoVotacao processoVotacao)
    {
        this.setUndecorated(true);
        this.processoVotacao = processoVotacao;
        
        configurarPanelBotoes();
        configurarPanelDisplay();
        
        cargoVotacaoVez = 0;
        
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        container.setLayout(new BorderLayout());
        container.add(panelParaBotoes,BorderLayout.EAST);
        container.add(panelParaDisplay, BorderLayout.CENTER);
        
        
        labelJusticaEleitoralTelaPrincipal = new JLabel("SISTEMA ELEITORAL BRASILEIRO");
        labelJusticaEleitoralTelaPrincipal.setHorizontalAlignment(JLabel.CENTER);
        labelJusticaEleitoralTelaPrincipal.setFont(new Font(Font.SERIF, Font.BOLD, 55));
        
        container.add(labelJusticaEleitoralTelaPrincipal, BorderLayout.NORTH);
        container.add(new JLabel(imageIconLogoJusticaEleitoral), BorderLayout.SOUTH);
        
        //container.add(panelDialogTelaVez, BorderLayout.CENTER);
        
        this.setResizable(false);
        this.setSize(tamanhoTela);
        this.setModal(true);
        this.setVisible(true);
    }
    
    
    //-----------------------MÉTODOS DE CONFIGURAÇÃO----------------------------
    public void configurarPanelBotoes()
    {
        panelParaBotoesNumericos = new JPanel(new GridLayout(4,3));
        panelParaBotoesOpcoes = new JPanel(new GridLayout(1,3));
        panelParaBotoes = new JPanel(new BorderLayout());
        panelParaBotoesInformacaoSuperior = new JPanel(new GridLayout(1,2));
        
        fontButton = new Font(Font.SERIF, Font.BOLD, 35);
        
        buttonNumericoTela = new JButton[10];
        buttonOpcoesTela = new JButton[opcoesParaButtons.length];
        
        for(int index = 0; index < buttonNumericoTela.length; index++)
        {
            buttonNumericoTela[index] = new JButton(""+index);
            buttonNumericoTela[index].setFont(fontButton);
            buttonNumericoTela[index].addActionListener(this);
            buttonNumericoTela[index].setBackground(Color.black);
            buttonNumericoTela[index].setForeground(Color.white);
            if(index != 0)
                panelParaBotoesNumericos.add(buttonNumericoTela[index]);
        }
        JButton buttonAux1 = new JButton();
        buttonAux1.setEnabled(false);
        JButton buttonAux2 = new JButton();
        buttonAux2.setEnabled(false);
        panelParaBotoesNumericos.add(buttonAux1);
        panelParaBotoesNumericos.add(buttonNumericoTela[0]);
        panelParaBotoesNumericos.add(buttonAux2);
        
        for(int index = 0; index < buttonOpcoesTela.length; index++)
        {
            buttonOpcoesTela[index] = new JButton(opcoesParaButtons[index]);
            buttonOpcoesTela[index].setFont(fontButton);
            buttonOpcoesTela[index].addActionListener(this);
            buttonOpcoesTela[index].setBackground(colorOpcoesParaButtons[index]);
            panelParaBotoesOpcoes.add(buttonOpcoesTela[index]);
        }
        
        labelJusticaEleitoral = new JLabel("JUSTIÇA ELEITORAL");
        labelJusticaEleitoral.setFont(fontButton);
        labelJusticaEleitoral.setHorizontalAlignment(JLabel.CENTER);
        
        imageIconLogoJusticaEleitoral = new ImageIcon(""+new File("").getAbsoluteFile()+"/assets/justiçaEleitoralLogoMini.png");
        labelLogoJusticaEleitoral = new JLabel(imageIconLogoJusticaEleitoral);
        labelLogoJusticaEleitoral.setHorizontalAlignment(JLabel.RIGHT);
        
        panelParaBotoesInformacaoSuperior.add(labelLogoJusticaEleitoral);
        panelParaBotoesInformacaoSuperior.add(labelJusticaEleitoral);
        
        panelParaBotoes.add(panelParaBotoesInformacaoSuperior, BorderLayout.NORTH);
        panelParaBotoes.add(panelParaBotoesNumericos, BorderLayout.CENTER);
        panelParaBotoes.add(panelParaBotoesOpcoes, BorderLayout.SOUTH);
    }
    
    
    public void configurarPanelDisplay()//ADICIONAR ESTE PANEL PARA UM CARD LAYOUT E GERAR UMA INICIALIZAÇÃO
    {
        fontLabelDisplay = new Font(Font.SERIF, Font.BOLD, 25);
        
        panelParaDisplay = new JPanel(new BorderLayout());
        panelParaDisplay.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        panelParaDisplay.setBackground(Color.white);
        
        panelParaDisplayParaParteSuperior = new JPanel(new GridLayout(2,1));
        panelParaDisplayParaParteMeio = new JPanel(new GridLayout(4,2));
        panelParaDisplayParaParteInferior = new JPanel(new GridLayout(3,2));
        panelParaDisplayParaParteSuperior.setBackground(Color.white);
        panelParaDisplayParaParteMeio.setBackground(Color.white);
        panelParaDisplayParaParteInferior.setBackground(Color.white);
        
        labelParaDisplayParaParteSuperior = new JLabel[2];
        labelParaDisplayParaParteSuperior[0] = new JLabel("SEU VOTO PARA");
        labelParaDisplayParaParteSuperior[1] = new JLabel(processoVotacao.getEleicoes().get(cargoVotacaoVez).getTitulo());
        for(JLabel labelAux : labelParaDisplayParaParteSuperior)
        {
            labelAux.setFont(fontLabelDisplay);
            labelAux.setHorizontalAlignment(JLabel.CENTER);
        }
        panelParaDisplayParaParteSuperior.add(labelParaDisplayParaParteSuperior[0]);
        panelParaDisplayParaParteSuperior.add(labelParaDisplayParaParteSuperior[1]);
        
        labelParaDisplayParaParteMeioFixo = new JLabel[4];
        for(int index = 0; index < labelParaDisplayParaParteMeioFixo.length; index++)
        {
            labelParaDisplayParaParteMeioFixo[index] = new JLabel(opcoesParaLabelsDisplayFixo[index]);
            labelParaDisplayParaParteMeioFixo[index].setFont(fontLabelDisplay);
            panelParaDisplayParaParteMeio.add(labelParaDisplayParaParteMeioFixo[index]);
        }
        
        
        
        cardManager = new CardLayout();
        panelParaDisplayParaParteMeioParaNumeros = new JPanel(cardManager);
        
        ArrayList<Eleicao> eleicoes = processoVotacao.getEleicoes();
        int panelsParaCriar = 0;
        for(int index = 0; index < eleicoes.size(); index++)
        {
            panelsParaCriar += eleicoes.get(index).getEleitos();
        }
        
        panelParaDisplayParaParteMeioCargos = new JPanel[processoVotacao.getEleicoes().size()];     //Vai criar panels para o tanto de cargos possíveis
        
        
        
        
        
        panelParaDisplay.add(panelParaDisplayParaParteSuperior, BorderLayout.NORTH);
        panelParaDisplay.add(panelParaDisplayParaParteMeio, BorderLayout.CENTER);
        panelParaDisplay.add(panelParaDisplayParaParteInferior, BorderLayout.SOUTH);
    }

    
    //------------------------------LISTENERS-----------------------------------
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        System.exit(0);
    }
}
