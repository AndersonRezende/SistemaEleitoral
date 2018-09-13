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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objetos.Candidato;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class DialogEleitorMaster extends JDialog implements ActionListener
{
    private int votacaoCargoVez;
    private int digitoVez;
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
    private JLabel labelParaDisplayParaParteInferior[];
    private JTextField textFieldDigitos[];
    
    private ImageIcon icon;   
    private JLabel labelImagem;
    
    public DialogEleitorMaster(ProcessoVotacao processoVotacao)
    {
        this.setUndecorated(true);
        this.processoVotacao = processoVotacao;
        
        votacaoCargoVez = 0;
        digitoVez = 0;
        
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
        panelParaDisplayParaParteMeio = new JPanel(new GridLayout(5,2));
        panelParaDisplayParaParteInferior = new JPanel(new GridLayout(3,1));
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
            labelParaDisplayParaParteMeioDinamico[index] = new JLabel("");
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
        
        icon = new ImageIcon(""+new File("").getAbsoluteFile()+"/Arquivos/Candidatos/usuario.jpg");
        labelImagem = new JLabel(icon);//********************************
        labelImagem.setVisible(false);
        panelParaDisplayParaParteMeio.add(new JLabel(""));
        panelParaDisplayParaParteMeio.add(labelImagem);
        
        //JLabel meuLabel = new JLabel("");
        panelParaDisplayParaParteInferior.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        labelParaDisplayParaParteInferior = new JLabel[3];
        labelParaDisplayParaParteInferior[0] = new JLabel("Aperte a tecla:");
        labelParaDisplayParaParteInferior[1] = new JLabel("    CONFIRMA para CONFIRMAR este voto");
        labelParaDisplayParaParteInferior[2] = new JLabel("    CORRIGE para REINICIAR este voto");
        for(JLabel labelAux : labelParaDisplayParaParteInferior)
        {
            labelAux.setFont(fontLabelDisplay);
            panelParaDisplayParaParteInferior.add(labelAux);
        }
        
        panelParaDisplay.add(panelParaDisplayParaParteSuperior, BorderLayout.NORTH);
        panelParaDisplay.add(panelParaDisplayParaParteMeio, BorderLayout.CENTER);
        //panelParaDisplay.add(foto, BorderLayout.EAST);
        panelParaDisplay.add(panelParaDisplayParaParteInferior, BorderLayout.SOUTH);
        alteraEstadoLabels(false);
    }

    
    //------------------------------LISTENERS-----------------------------------
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == buttonOpcoesTela[2])
        {   tocarSom("inter");  }
        else
        {   tocarSom("SomTecla");   }
        
        if(e.getSource() == buttonOpcoesTela[0])                                //Branco sai
        {    
            this.dispose();
        }
        if(e.getSource() == buttonOpcoesTela[1])                                //Corrige
        {
            limparTela();
        }
        if(e.getSource() == buttonOpcoesTela[2])                                //Se confirmar vai pra proxima tela
        {
            //Adicionar metodo para checar se tem numero
            registrarVoto();
            configurarExibicaoVez();
            limparTela();
        }
        
        if(e.getSource() == buttonNumericoTela[0])
        {   configurarExibirNumeroDigitado(0);   }
        if(e.getSource() == buttonNumericoTela[1])
        {   configurarExibirNumeroDigitado(1);   }
        if(e.getSource() == buttonNumericoTela[2])
        {   configurarExibirNumeroDigitado(2);   }
        if(e.getSource() == buttonNumericoTela[3])
        {   configurarExibirNumeroDigitado(3);   }
        if(e.getSource() == buttonNumericoTela[4])
        {   configurarExibirNumeroDigitado(4);   }
        if(e.getSource() == buttonNumericoTela[5])
        {   configurarExibirNumeroDigitado(5);   }
        if(e.getSource() == buttonNumericoTela[6])
        {   configurarExibirNumeroDigitado(6);   }
        if(e.getSource() == buttonNumericoTela[7])
        {   configurarExibirNumeroDigitado(7);   }
        if(e.getSource() == buttonNumericoTela[8])
        {   configurarExibirNumeroDigitado(8);   }
        if(e.getSource() == buttonNumericoTela[9])
        {   configurarExibirNumeroDigitado(9);   }    
    }

    
    //-----------------------------AUXILIARES-----------------------------------
    private void configurarExibicaoVez()                                        //Este método prepara as telas de acorodo com os itens necessários
    {
        digitoVez = 0;
        if(votacaoCargoVez == processoVotacao.getEleicoes().size())             //Se estiver no último cargo, finaliza a votação
        {
            finalizarVoto();
        }
        else                                                                    //Senão, pega a quantidade de digitos da votação e seta no textField de acordo com a necessidade
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
    
    
    private void configurarExibirNumeroDigitado(int numero)                     //Este método configura a exibição dos numeros no textField
    {
        if(textFieldDigitos.length > digitoVez)                                 //Se digitoVez for maior, vai ultrapassar o limite do vetor
        {
            if(textFieldDigitos[digitoVez].isVisible())                         //Se o textField for visível, set o texto com o numero
                textFieldDigitos[digitoVez].setText(""+numero);
            if(textFieldDigitos.length > digitoVez + 1)                         //Se o proximo digito vez for menor que o tamanho maximo do vetor...
                if(!textFieldDigitos[digitoVez + 1].isVisible())                //Se o proximo espaço do vetor não for visível
                    alterarEstadoBotoes(false);                                 //Desabilita os botões
        }
        if(textFieldDigitos.length == digitoVez + 1)                            //Se tiver no limite do vetor, desabilita o vetor
            alterarEstadoBotoes(false);
        digitoVez++;
    }
    
    
    private void alterarEstadoBotoes(boolean estado)                            //Este método é chamado quando todos os textfields já estiverem completos
    {
        for(JButton buttonAux : buttonNumericoTela)
            buttonAux.setEnabled(estado);
        Candidato candidato = processoVotacao.getInfoCandidato(getNumeroDigitado(), votacaoCargoVez-1);
        if(candidato != null)
        {
            alteraEstadoLabels(true);
            labelParaDisplayParaParteMeioDinamico[0].setText(candidato.getEleitor().getNome());
            labelParaDisplayParaParteMeioDinamico[1].setText(candidato.getPartido());
            if(candidato.temVice())
                labelParaDisplayParaParteMeioDinamico[2].setText(candidato.getNomeVice());
        }
        else
        {
            alteraEstadoLabels(false);
            labelParaDisplayParaParteMeioDinamico[0].setText("VOTO NULO");
            labelParaDisplayParaParteMeioDinamico[0].setVisible(true);
            panelParaDisplayParaParteInferior.setVisible(true);
        }
        //Exibir o candidato (foto)
    }
    
    
    private void limparTela()                                                   //Este método realiza a limpeza dos itens da tela
    {
        digitoVez = 0;                                                          //Esta variável indica qual a posição do textField
        alterarEstadoBotoes(true);
        for(JTextField textFieldAux : textFieldDigitos)
            textFieldAux.setText("");
        alteraEstadoLabels(false);
    }
    
    
    private void finalizarVoto()
    {//Gravar em um arquivo de log com uma thread
        tocarSom("fim");
        this.dispose();
    }
    
    
    private void tocarSom(String nomeSom)
    {
        try
        {   
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(new File("").getAbsoluteFile()+"/assets/audios/"+nomeSom+".wav").getAbsoluteFile()); 
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } 
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) 
        {   Logger.getLogger(DialogEleitorMaster.class.getName()).log(Level.SEVERE, null, ex);  }
    }
    
    
    private void registrarVoto()
    {   Candidato c = processoVotacao.votar(getNumeroDigitado(), votacaoCargoVez-1);    }
    
    private int getNumeroDigitado()//ajustar numero vazio
    {
        String numeroAux = "";
        int numero = 0;
        int index = 0;
        while(textFieldDigitos.length > index && textFieldDigitos[index].isVisible())
        {
            numeroAux += textFieldDigitos[index].getText();
            index++;
        }
        if(!numeroAux.equals(""))
            numero = Integer.parseInt(numeroAux);
        
        return numero;
    }
    
    private void alteraEstadoLabels(boolean visivel)
    {
        icon = new ImageIcon(""+new File("").getAbsoluteFile()+"/Arquivos/Candidatos/usuario.jpg");
        //labelImagem = new JLabel(icon);//********************************
        labelImagem.setVisible(visivel);
        
        for(JLabel labelAux : labelParaDisplayParaParteMeioDinamico)
        {
            labelAux.setText("");
            labelAux.setVisible(visivel);
        }
        for(int index = 1; index < labelParaDisplayParaParteMeioFixo.length; index++)   //O label de numero é sempre visivel
            labelParaDisplayParaParteMeioFixo[index].setVisible(visivel);
        panelParaDisplayParaParteInferior.setVisible(visivel);
    }
}
