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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class DialogEleitorMaster extends JDialog implements ActionListener
{
    private final String opcoesParaButtons[] = {"BRANCO", "CORRIGE", "CONFIRMA"};
    private final Color colorOpcoesParaButtons[] = {Color.white, Color.orange, Color.green};
    private Dimension tamanhoTela;
    private Container container = getContentPane();
    
    private Font fontButton;
    
    private CardLayout cardManager;
    private JPanel panelDialogTelaVez;
    private JPanel panelsDialogVotoVez[];
    private JPanel panelParaBotoes;
    private JPanel panelParaBotoesNumericos;
    private JPanel panelParaBotoesOpcoes;
    private JPanel panelParaBotoesInformacaoSuperior;
    private JPanel panelParaDisplay;
    private JPanel panelParaDisplayParaLabelSuperior;
    private JPanel panelParaDisplayParaLabelMeio;
    private JPanel panelParaDisplayParaLabelInferior;
    
    
    private JButton buttonNumericoTela[];
    private JButton buttonOpcoesTela[];
    private JTextField textFieldsTela[];
    private ImageIcon imageIconTela[];
    private ImageIcon imageIconLogoJusticaEleitoral;
    private JLabel labelJusticaEleitoralTelaPrincipal;
    private JLabel labelEleicaoTela[];
    private JLabel labelJusticaEleitoral;
    private JLabel labelLogoJusticaEleitoral;
    
    
    public DialogEleitorMaster(ProcessoVotacao processoVotacao)
    {
        this.setUndecorated(true);
        
        cardManager = new CardLayout();                                         
        panelDialogTelaVez = new JPanel(cardManager); 
        
        panelsDialogVotoVez = new JPanel[processoVotacao.getEleicoes().size()];                      //A quantidade de panels é referente a quantidade de cargos votados
        
        configurarPanelBotoes();
        configurarPanelDisplay();
        configurarPanels();
        
        
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
    
    
    public void configurarPanelDisplay()
    {
        panelParaDisplay = new JPanel(new BorderLayout());
        panelParaDisplay.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        panelParaDisplay.setBackground(Color.white);
        
        panelParaDisplayParaLabelSuperior = new JPanel(new GridLayout(1,2));
        panelParaDisplayParaLabelMeio = new JPanel(new GridLayout(4,2));
        panelParaDisplayParaLabelInferior = new JPanel(new GridLayout(3,2));
        
        panelParaDisplayParaLabelSuperior.setBackground(Color.white);
        panelParaDisplayParaLabelMeio.setBackground(Color.white);
        panelParaDisplayParaLabelInferior.setBackground(Color.white);
        
        
        
        
        
        panelParaDisplay.add(panelParaDisplayParaLabelSuperior, BorderLayout.NORTH);
        panelParaDisplay.add(panelParaDisplayParaLabelMeio, BorderLayout.CENTER);
        panelParaDisplay.add(panelParaDisplayParaLabelInferior, BorderLayout.SOUTH);
    }
    
    
    public void configurarPanels()
    {
        for(JPanel panelVotoVez : panelsDialogVotoVez)
            panelVotoVez = new JPanel(new BorderLayout());   
    }

    
    //------------------------------LISTENERS-----------------------------------
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        System.exit(0);
    }
}
