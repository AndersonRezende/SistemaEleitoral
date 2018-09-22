/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import arquivo.LeituraArquivo;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    private Font fontLabelResultado;
    
    private CardLayout cardManager;      
    private JPanel panelParaBotoesControleTela;
    private JPanel panelContainerTelaVez;
    private JPanel panelsTelaVez[];
    private JPanel panelsTelaVezGrid[];
    
    private JButton buttonControleTelaVez[];
    
    private JLabel labelSuperiorContainer;
    private JLabel labelCargos[];
    
    
    public DialogResultado(ProcessoVotacao processoVotacao)
    {
        this.processoVotacao = processoVotacao;
        this.processoVotacao.setCandidatos(LeituraArquivo.lerPolitico(processoVotacao.getEleicoes().get(0).getEleicao(), true));
        
        container.setLayout(new BorderLayout());
        cardManager = new CardLayout();                                         
        panelContainerTelaVez = new JPanel(cardManager);
        
        fontLabelTextoSuperior = new Font(Font.SERIF, Font.BOLD, 25);
        fontLabelResultado = new Font(Font.SERIF, Font.BOLD, 20);
        
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
                panelParaBotoesControleTela.add(buttonControleTelaVez[index], BorderLayout.EAST);
            if(index == 1)
                panelParaBotoesControleTela.add(buttonControleTelaVez[index], BorderLayout.WEST);
            
        } 
        configuraPanels();
        
        container.add(labelSuperiorContainer, BorderLayout.NORTH);
        container.add(panelContainerTelaVez, BorderLayout.CENTER);
        container.add(panelParaBotoesControleTela, BorderLayout.SOUTH);
        
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
    
        //this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(tamanhoTela);
        //this.setSize(1500,1000);//AJUSTAR
        this.setAlwaysOnTop(true);
        
        this.setModal(true);
        this.setVisible(true);
    }
    
    
    public void configuraPanels()
    {
        panelsTelaVez = new JPanel[processoVotacao.getEleicoes().size()];       // Aqui é criado um painel para cada tipo de cargo que existir
        panelsTelaVezGrid = new JPanel[processoVotacao.getEleicoes().size()];
        labelCargos = new JLabel[processoVotacao.getEleicoes().size()];         // Aqui é criado um label para cada cargo das eleições
        
        JLabel labelNome;
        JLabel labelVice;
        JLabel labelNumero;
        JLabel labelPartido;
        JLabel labelVotos;
        JLabel labelEleito;
        JScrollPane scrollPane;
        for(int index = 0; index < labelCargos.length; index++)
        {   
            labelCargos[index] = new JLabel(processoVotacao.getEleicoes().get(index).getEleicao()+" - "+processoVotacao.getEleicoes().get(index).getTitulo());
            labelCargos[index].setHorizontalAlignment(JLabel.CENTER);
            labelCargos[index].setFont(fontLabelTextoSuperior);
        }
        
        for(int indexCargos = 0; indexCargos < panelsTelaVez.length; indexCargos++)                                     //Este for percorre todos os paineis, e por consequência, os cargos
        {
            panelsTelaVez[indexCargos] = new JPanel(new BorderLayout());
            if(processoVotacao.getEleicoes().get(indexCargos).getVice())
                panelsTelaVezGrid[indexCargos] = new JPanel(new GridLayout(1 + processoVotacao.getQuantidadeElementosPorCargo(processoVotacao.getEleicoes().get(indexCargos).getTitulo()), 6));
            else
                panelsTelaVezGrid[indexCargos] = new JPanel(new GridLayout(1 + processoVotacao.getQuantidadeElementosPorCargo(processoVotacao.getEleicoes().get(indexCargos).getTitulo()), 5));
            
            panelsTelaVez[indexCargos].add(labelCargos[indexCargos], BorderLayout.NORTH);
            
            labelNome = new JLabel("Nome");
            labelVice = new JLabel("Vice");
            labelNumero = new JLabel("Número");
            labelPartido = new JLabel("Partido");
            labelVotos = new JLabel("Votos");
            labelEleito = new JLabel("Eleito");
            
            labelNome.setBorder(BorderFactory.createLineBorder(Color.black));
            labelVice.setBorder(BorderFactory.createLineBorder(Color.black));
            labelNumero.setBorder(BorderFactory.createLineBorder(Color.black));
            labelPartido.setBorder(BorderFactory.createLineBorder(Color.black));
            labelVotos.setBorder(BorderFactory.createLineBorder(Color.black));
            labelEleito.setBorder(BorderFactory.createLineBorder(Color.black));
            
            labelNome.setFont(fontLabelResultado);
            labelVice.setFont(fontLabelResultado);
            labelNumero.setFont(fontLabelResultado);
            labelPartido.setFont(fontLabelResultado);
            labelVotos.setFont(fontLabelResultado);
            labelEleito.setFont(fontLabelResultado);
            
            panelsTelaVezGrid[indexCargos].add(labelNome);
            if(processoVotacao.getEleicoes().get(indexCargos).getVice())
                panelsTelaVezGrid[indexCargos].add(labelVice);
            panelsTelaVezGrid[indexCargos].add(labelNumero);
            panelsTelaVezGrid[indexCargos].add(labelPartido);
            panelsTelaVezGrid[indexCargos].add(labelVotos);
            panelsTelaVezGrid[indexCargos].add(labelEleito);
            
            for(int indexPoliticos = 0; indexPoliticos < processoVotacao.getCandidatos().size(); indexPoliticos++)                 //Este for percorre todos os cargos
            {
                if(processoVotacao.getEleicoes().get(indexCargos).getTitulo().equalsIgnoreCase(processoVotacao.getCandidatos().get(indexPoliticos).getCargo()))
                {
                    labelNome = new JLabel(processoVotacao.getCandidatos().get(indexPoliticos).getEleitor().getNome());
                    if(processoVotacao.getEleicoes().get(indexCargos).getVice())
                        labelVice = new JLabel(""+processoVotacao.getCandidatos().get(indexPoliticos).getDadosVice());   //DADOS VICE
                    labelNumero = new JLabel(""+processoVotacao.getCandidatos().get(indexPoliticos).getNumero());
                    labelPartido = new JLabel(processoVotacao.getCandidatos().get(indexPoliticos).getPartido());
                    labelVotos = new JLabel(""+processoVotacao.getCandidatos().get(indexPoliticos).getVotos());
                    if(processoVotacao.foiEleito(processoVotacao.getCandidatos().get(indexPoliticos)) && processoVotacao.getCandidatos().get(indexPoliticos).getVotos() > 0)
                    {
                        labelEleito = new JLabel("Eleito");
                        labelEleito.setForeground(Color.blue);
                    }
                    else
                    {
                        labelEleito = new JLabel("Não foi eleito");
                        labelEleito.setForeground(Color.red);
                    }
                    
                    labelNome.setFont(fontLabelResultado);
                    if(processoVotacao.getEleicoes().get(indexCargos).getVice())
                        labelVice.setFont(fontLabelResultado);
                    labelNumero.setFont(fontLabelResultado);
                    labelPartido.setFont(fontLabelResultado);
                    labelVotos.setFont(fontLabelResultado);
                    labelEleito.setFont(fontLabelResultado);
                    
                    labelNome.setBorder(BorderFactory.createLineBorder(Color.black));
                    if(processoVotacao.getEleicoes().get(indexCargos).getVice())
                        labelVice.setBorder(BorderFactory.createLineBorder(Color.black));
                    labelNumero.setBorder(BorderFactory.createLineBorder(Color.black));
                    labelPartido.setBorder(BorderFactory.createLineBorder(Color.black));
                    labelVotos.setBorder(BorderFactory.createLineBorder(Color.black));
                    labelEleito.setBorder(BorderFactory.createLineBorder(Color.black));
                    
                    panelsTelaVezGrid[indexCargos].add(labelNome);
                    if(processoVotacao.getEleicoes().get(indexCargos).getVice())
                        panelsTelaVezGrid[indexCargos].add(labelVice);
                    panelsTelaVezGrid[indexCargos].add(labelNumero);
                    panelsTelaVezGrid[indexCargos].add(labelPartido);
                    panelsTelaVezGrid[indexCargos].add(labelVotos);
                    panelsTelaVezGrid[indexCargos].add(labelEleito);
                }
            }
            scrollPane = new JScrollPane(panelsTelaVezGrid[indexCargos]);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            panelsTelaVez[indexCargos].add(scrollPane, BorderLayout.CENTER);
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