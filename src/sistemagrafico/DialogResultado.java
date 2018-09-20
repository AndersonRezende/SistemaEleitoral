/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import arquivo.LeituraArquivo;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class DialogResultado extends JDialog implements ActionListener
{
    private final String opcoesParaBotaoControleTela[] = {"PRÓXIMO", "ANTERIOR"};
    private Dimension tamanhoTela;
    private ProcessoVotacao processoVotacao;
    
    private Container container = getContentPane();
    private Font fontLabelTextoSuperior;
    
    private CardLayout cardManager;      
    private JPanel panelParaBotoesControleTela;
    private JPanel panelContainerTelaVez;
    private JPanel panelsTelaVez[];
    
    private JButton buttonControleTelaVez[];
    
    private JLabel labelSuperiorContainer;
    private JLabel labelCargos[];
    private JLabel labelCargosPoliticos[][];                                    //O primeiro vetor é para relacionar com o cargo e o segundo, é para relacionar com o nome do politico
    
    
    public DialogResultado(ProcessoVotacao processoVotacao)
    {
        this.processoVotacao = processoVotacao;
        this.processoVotacao.setCandidatos(LeituraArquivo.lerPolitico(processoVotacao.getEleicoes().get(0).getEleicao(), true));
        container.setLayout(new BorderLayout());
        cardManager = new CardLayout();                                         
        panelContainerTelaVez = new JPanel(cardManager);
        
        fontLabelTextoSuperior = new Font(Font.SERIF, Font.BOLD, 25);
        labelSuperiorContainer = new JLabel("RESULTADO");
        labelSuperiorContainer.setFont(fontLabelTextoSuperior);
        labelSuperiorContainer.setHorizontalAlignment(JLabel.CENTER);
        
        panelParaBotoesControleTela = new JPanel(new BorderLayout());
        buttonControleTelaVez = new JButton[2];
        for(int index = 0; index < buttonControleTelaVez.length; index++)
        {
            buttonControleTelaVez[index] = new JButton(opcoesParaBotaoControleTela[index]);
            buttonControleTelaVez[index].setFont(fontLabelTextoSuperior);
            buttonControleTelaVez[index].addActionListener(this);
            if(index == 0)
                panelParaBotoesControleTela.add(buttonControleTelaVez[index], BorderLayout.WEST);
            if(index == 1)
                panelParaBotoesControleTela.add(buttonControleTelaVez[index], BorderLayout.EAST);
            
        } 
        configuraPanels();
        
        container.add(labelSuperiorContainer, BorderLayout.NORTH);
        container.add(panelContainerTelaVez, BorderLayout.CENTER);
        container.add(panelParaBotoesControleTela, BorderLayout.SOUTH);
        
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
    
        //this.setUndecorated(true);
        this.setResizable(false);
        //this.setSize(tamanhoTela);
        this.setSize(1000,1000);//AJUSTAR
        
        this.setModal(true);
        this.setVisible(true);
    }
    
    
    public void configuraPanels()
    {
        panelsTelaVez = new JPanel[processoVotacao.getEleicoes().size()];       // Aqui é criado um painel para cada tipo de cargo que existir
        labelCargos = new JLabel[processoVotacao.getEleicoes().size()];         // Aqui é criado um label para cada cargo das eleições
        
        for(int index = 0; index < labelCargos.length; index++)
        {   labelCargos[index] = new JLabel(processoVotacao.getEleicoes().get(index).getEleicao()+" - "+processoVotacao.getEleicoes().get(index).getTitulo());  }
        
        for(int indexCargos = 0; indexCargos < panelsTelaVez.length; indexCargos++)                                     //Este for percorre todos os paineis, e por consequência, os cargos
        {
            panelsTelaVez[indexCargos] = new JPanel(new GridLayout(1 + processoVotacao.getQuantidadeElementosPorCargo(processoVotacao.getEleicoes().get(indexCargos).getTitulo()), 4));
            
            panelsTelaVez[indexCargos].add(new JLabel("Nome"));
            panelsTelaVez[indexCargos].add(new JLabel("Número"));
            panelsTelaVez[indexCargos].add(new JLabel("Partido"));
            panelsTelaVez[indexCargos].add(new JLabel("Votos"));
            
            for(int indexPoliticos = 0; indexPoliticos < processoVotacao.getCandidatos().size(); indexPoliticos++)                 //Este for percorre todos os cargos
            {
                if(processoVotacao.getEleicoes().get(indexCargos).getTitulo().equalsIgnoreCase(processoVotacao.getCandidatos().get(indexPoliticos).getCargo()))
                {
                    panelsTelaVez[indexCargos].add(new JLabel(processoVotacao.getCandidatos().get(indexPoliticos).getEleitor().getNome()));
                    panelsTelaVez[indexCargos].add(new JLabel(""+processoVotacao.getCandidatos().get(indexPoliticos).getNumero()));
                    panelsTelaVez[indexCargos].add(new JLabel(processoVotacao.getCandidatos().get(indexPoliticos).getPartido()));
                    panelsTelaVez[indexCargos].add(new JLabel(""+processoVotacao.getCandidatos().get(indexPoliticos).getVotos()));
                }
            }
            panelContainerTelaVez.add(panelsTelaVez[indexCargos]);
        }
    }

    
    //--------------------------------LISTENERS---------------------------------
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == buttonControleTelaVez[0])
            cardManager.next(panelContainerTelaVez);
        if(e.getSource() == buttonControleTelaVez[1])
            cardManager.previous(panelContainerTelaVez);
    }
}