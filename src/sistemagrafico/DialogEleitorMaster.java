/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class DialogEleitorMaster extends JDialog implements ActionListener
{
    private int votacaoCargoVez;
    private final String opcoesParaButtons[] = {"BRANCO", "CORRIGE", "CONFIRMA"};
    private final Color colorOpcoesParaButtons[] = {Color.white, Color.orange, Color.green};
    private final String opcoesParaLabelsDisplayFixo[] = {"Número: ", "Nome: ", "Partido: ", "Vice: "};
            
    private ProcessoVotacao processoVotacao;
    private Dimension tamanhoTela;
    private Container container = getContentPane();
    
    private Font fontButton;
    private Font fontLabelDisplay;
    
    private JPanel panelParaBotoes;
    private JPanel panelParaBotoesNumericos;
    private JPanel panelParaBotoesOpcoes;
    private JPanel panelParaBotoesInformacaoSuperior;
    private JPanel panelParaDisplay;
    private JPanel panelParaDisplayParaParteSuperior;
    private JPanel panelParaDisplayParaParteMeio;
    private JPanel panelParaDisplayParaParteInferior;
    private JPanel panelParaDisplayParaParteMeioParaNumeros;
    
    private JButton buttonNumericoTela[];
    private JButton buttonOpcoesTela[];
    private ImageIcon imageIconLogoJusticaEleitoral;
    private JLabel labelJusticaEleitoralTelaPrincipal;
    private JLabel labelJusticaEleitoral;
    private JLabel labelLogoJusticaEleitoral;
    private JLabel labelParaDisplayParaParteSuperior[];
    private JLabel labelParaDisplayParaParteMeioFixo[];
    private JLabel labelParaDisplayParaParteMeioDinamico[];
    private JTextField textFieldDigitos[];
    
    public DialogEleitorMaster(ProcessoVotacao processoVotacao)
    {
        this.setUndecorated(true);
        this.processoVotacao = processoVotacao;
        
        votacaoCargoVez = 0;
        
        configurarPanelBotoes();
        configurarPanelDisplay();
        configurarExibicaoVez();
        
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
        labelParaDisplayParaParteSuperior[1] = new JLabel(processoVotacao.getEleicoes().get(votacaoCargoVez).getTitulo());
        for(JLabel labelAux : labelParaDisplayParaParteSuperior)
        {
            labelAux.setFont(fontLabelDisplay);
            labelAux.setHorizontalAlignment(JLabel.CENTER);
        }
        panelParaDisplayParaParteSuperior.add(labelParaDisplayParaParteSuperior[0]);
        panelParaDisplayParaParteSuperior.add(labelParaDisplayParaParteSuperior[1]);
        
        
        
        
        //Crio um conjunto de textfield para os numeros e adiciono em um panel
        textFieldDigitos = new JTextField[processoVotacao.getMaiorNumeroDigitosEleicao()];
        panelParaDisplayParaParteMeioParaNumeros = new JPanel(new GridLayout(1, textFieldDigitos.length));
        panelParaDisplayParaParteMeioParaNumeros.setBackground(Color.white);
        for(int index = 0; index < textFieldDigitos.length; index++)
        {
            textFieldDigitos[index] = new JTextField(1);
            textFieldDigitos[index].setFont(fontLabelDisplay);
            textFieldDigitos[index].setEditable(false);
            textFieldDigitos[index].setBackground(Color.white);
            textFieldDigitos[index].setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
            panelParaDisplayParaParteMeioParaNumeros.add(textFieldDigitos[index]);
        }
        
        labelParaDisplayParaParteMeioDinamico = new JLabel[3];
        for(int index = 0; index < labelParaDisplayParaParteMeioDinamico.length; index++)
        {
            labelParaDisplayParaParteMeioDinamico[index] = new JLabel("Teste");
            labelParaDisplayParaParteMeioDinamico[index].setFont(fontLabelDisplay);
        }
        
        /*Nesta parte, estou criando a parte que tem o numero, nome, partido e vice*/
        labelParaDisplayParaParteMeioFixo = new JLabel[4];  
        for(int index = 0; index < labelParaDisplayParaParteMeioFixo.length; index++)
        {
            labelParaDisplayParaParteMeioFixo[index] = new JLabel(opcoesParaLabelsDisplayFixo[index]);
            labelParaDisplayParaParteMeioFixo[index].setFont(fontLabelDisplay);
            panelParaDisplayParaParteMeio.add(labelParaDisplayParaParteMeioFixo[index]);
            switch(index)
            {
                case 0:
                        panelParaDisplayParaParteMeio.add(panelParaDisplayParaParteMeioParaNumeros);
                break;
                case 1:
                        panelParaDisplayParaParteMeio.add(labelParaDisplayParaParteMeioDinamico[0]);
                break;
                case 2:
                        panelParaDisplayParaParteMeio.add(labelParaDisplayParaParteMeioDinamico[1]);
                break;
                case 3:
                        panelParaDisplayParaParteMeio.add(labelParaDisplayParaParteMeioDinamico[2]);
                break;
                
            }
        }    
        
        panelParaDisplay.add(panelParaDisplayParaParteSuperior, BorderLayout.NORTH);
        panelParaDisplay.add(panelParaDisplayParaParteMeio, BorderLayout.CENTER);
        //panelParaDisplay.add(foto, BorderLayout.EAST);
        panelParaDisplay.add(panelParaDisplayParaParteInferior, BorderLayout.SOUTH);
    }

    
    //------------------------------LISTENERS-----------------------------------
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == buttonOpcoesTela[0])                                //Branco sai
            this.dispose();
        if(e.getSource() == buttonOpcoesTela[1])
        {
            for(JTextField textFieldAux : textFieldDigitos)
                textFieldAux.setText("");
        }
        if(e.getSource() == buttonOpcoesTela[2])                                //Se confirmar vai pra proxima tela
        {
            configurarExibicaoVez();
        }
        if(e.getSource() == buttonNumericoTela[0])
            JOptionPane.showMessageDialog(null, "Teste", "Teste", JOptionPane.INFORMATION_MESSAGE);
    }

    
    //-----------------------------AUXILIARES-----------------------------------
    private void configurarExibicaoVez() 
    {
        if(votacaoCargoVez == processoVotacao.getEleicoes().size())
        {
            this.dispose();
        }
        else
        {
            int auxCargoVez = processoVotacao.getEleicoes().get(votacaoCargoVez).getDigitos();
            labelParaDisplayParaParteSuperior[1].setText(processoVotacao.getEleicoes().get(votacaoCargoVez).getTitulo());
            for(int index = 0; index < textFieldDigitos.length; index++)
            {
                if(auxCargoVez <= index)
                    textFieldDigitos[index].setVisible(false);
                else
                    textFieldDigitos[index].setVisible(true);
            }
            votacaoCargoVez ++;
        }
    }
}
