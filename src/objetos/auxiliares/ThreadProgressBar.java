/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Anderson
 */
public class ThreadProgressBar extends Thread
{
    JProgressBar progressBar;
    JLabel labelFimVotacao;
    ObjetoCompartilhado oc;
    
    
    public ThreadProgressBar(ObjetoCompartilhado oc, JProgressBar progressBar, JLabel labelFimVotacao)
    {
        this.progressBar = progressBar;
        this.oc = oc;
        this.labelFimVotacao = labelFimVotacao;
    }

    @Override
    public void run() 
    {
        do
        {
            if(oc.disponivel())
            {
                progressBar.setValue(oc.getValor());
                progressBar.setString(progressBar.getValue()+"%");
            }
            else
            {
                try 
                {   Thread.sleep(5);  } 
                catch (Exception e) 
                {   e.printStackTrace();    }
            }
        }
        while(progressBar.getValue() < 100);
        labelFimVotacao.setVisible(true);
    }
    
}
