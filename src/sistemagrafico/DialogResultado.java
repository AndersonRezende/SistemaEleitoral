/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import arquivo.LeituraArquivo;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JLabel;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class DialogResultado extends JDialog 
{
    private Container container = getContentPane();
    private Dimension tamanhoTela;
    
    public DialogResultado(ProcessoVotacao processoVotacao)
    {
        container.setLayout(new FlowLayout());
        processoVotacao.setCandidatos(LeituraArquivo.lerPolitico(processoVotacao.getEleicoes().get(0).getEleicao(), true));
        for(int index = 0; index < processoVotacao.getCandidatos().size(); index++)
        {
            container.add(new JLabel("{"+processoVotacao.getCandidatos().get(index).getEleitor().getNome()));
            container.add(new JLabel("-"+processoVotacao.getCandidatos().get(index).getVotos()+"}"));
        }
        
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
    
        this.setResizable(false);
        this.setSize(tamanhoTela);
        this.setModal(true);
        this.setVisible(true);
    }
}
