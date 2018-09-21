/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

import java.util.Comparator;
import objetos.Candidato;

/**
 *
 * @author Anderson
 */
public class ComparadorCandidatos implements Comparator<Candidato> 
{
    
    
    @Override
    public int compare(Candidato c1, Candidato c2) 
    {
        int resultado = 0;
        if(c1.getVotos() > c2.getVotos())
            resultado = -1;
        else
        {
            if(c1.getVotos() < c2.getVotos())
                resultado = 1;
            else
                resultado = 0;
        }
        return resultado;
    }
    
}
