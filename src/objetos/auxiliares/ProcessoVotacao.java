/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

import java.util.ArrayList;
import objetos.Candidato;
import objetos.Eleicao;
import objetos.Eleitor;

/**
 *
 * @author Anderson
 */
public class ProcessoVotacao
{
    private boolean processoVotacaoIniciado;
    private ArrayList<Eleicao> eleicoes;
    private ArrayList<Candidato> candidatos;
    private ArrayList<Eleitor> eleitores;
    
    
    public ProcessoVotacao()
    {   this.processoVotacaoIniciado = false;   }
    
    public ProcessoVotacao(boolean processoVotacaoIniciado)
    {   this.processoVotacaoIniciado = processoVotacaoIniciado;  }
    
    public boolean getProcessoVotacaoIniciado()
    {   return this.processoVotacaoIniciado;    }
    
    public void iniciarProcessoVotacao()
    {   this.processoVotacaoIniciado = true; }
    
    public void finalizarProcessoVotacao()
    {   this.processoVotacaoIniciado = false;   }

    public ArrayList<Eleicao> getEleicoes() 
    {   return eleicoes;    }

    public ArrayList<Candidato> getCandidatos()     
    {   return candidatos;  }

    public ArrayList<Eleitor> getEleitores() 
    {   return eleitores;   }

    public void setEleicoes(ArrayList<Eleicao> eleicoes) 
    {   this.eleicoes = eleicoes;   }

    public void setCandidatos(ArrayList<Candidato> candidatos) 
    {    this.candidatos = candidatos;  }

    public void setEleitores(ArrayList<Eleitor> eleitores) 
    {   this.eleitores = eleitores; }
    
    public int getMaiorNumeroDigitosEleicao()
    {
        int maiorNumeroDigitos = 0;
        for(Eleicao eleicao : eleicoes)
            if(eleicao.getDigitos() > maiorNumeroDigitos)
                maiorNumeroDigitos = eleicao.getDigitos();
        return maiorNumeroDigitos;
    }

    public Candidato votar(int numero, int votacaoCargoVez)
    {        
        Candidato candidato = null;
        candidato = getInfoCandidato(numero, votacaoCargoVez);
        if(candidato != null)
            candidato.receberVoto();
        return candidato;
    }
    
    public Candidato getInfoCandidato(int numero, int votacaoCargoVez)
    {
        Candidato candidato = null;
        if(candidatos != null)
        {
            for(int index = 0; index < candidatos.size(); index++)
            {
                if(candidatos.get(index).getNumero() == numero && eleicoes.get(votacaoCargoVez).getTitulo().equals(candidatos.get(index).getCargo()))
                {
                    candidato = candidatos.get(index);
                    break;
                }
            } 
        }
        return candidato;
    }
}