/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

import java.util.Calendar;
import objetos.Eleitor;
import objetos.Mesario;

/**
 *
 * @author Anderson
 */
public class ChecagemTempo<G>
{
    private final int tempoMediaEleitorVotar = 1;
    private final int tempoMediaMesarioVotacao = 10;
    private int horaInicio;
    private int horaFinal;
    private boolean finalizar;
    private Calendar c;
    private Calendar dataInicial;
    private Calendar dataFinal;
    
    private G objeto;
    
    public ChecagemTempo(G objeto)
    {
        this.objeto = objeto;
        c = Calendar.getInstance();
        horaInicio = c.get(Calendar.HOUR_OF_DAY);
        dataInicial = Calendar.getInstance();
        finalizar = false;
    }
    
    
    public void atualizarContagem()
    {
        horaFinal = c.get(Calendar.HOUR_OF_DAY);
        dataFinal = Calendar.getInstance();
    }    
    
    
    public long getTempo()
    {
        long diferenca = System.currentTimeMillis() - dataInicial.getTimeInMillis();
        long diferencaMin = diferenca / (60 * 1000);    //DIFERENCA EM MINUTOS   

        return diferencaMin;
    }
    
    
    public boolean passouDoTempo()
    {
        boolean passouTempo = false;
        if(objeto.getClass().equals(Eleitor.class))
            if(getTempo() > tempoMediaEleitorVotar)
                passouTempo = true;
        if(objeto.getClass().equals(Mesario.class))
            if(getTempo() > tempoMediaMesarioVotacao)
                passouTempo = true;
        

        return passouTempo;
    }
    
    
    public void finalizar()
    {   finalizar = true;   }
    
    public boolean getFinalizar()
    {   return finalizar;   }
}
