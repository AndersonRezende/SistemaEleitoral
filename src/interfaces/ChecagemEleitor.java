/*
 * Esta interface é utilizada em ProcessoVotacao
 */
package interfaces;

import objetos.Eleitor;

/**
 *
 * @author Anderson
 */
public interface ChecagemEleitor 
{
    public boolean eleitorJaVotou(String titulo);
    
    public boolean eleitorVotaAqui(String titulo);
    
    public Eleitor getEleitor(String titulo);
    
}
