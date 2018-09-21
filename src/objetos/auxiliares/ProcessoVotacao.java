/*
 * Aqui são implementado as duas Interfaces: ChecagemEleicao e ChecagemEleitor
 */
package objetos.auxiliares;

import interfaces.ChecagemEleicao;
import interfaces.ChecagemEleitor;
import java.util.ArrayList;
import java.util.Collections;
import objetos.Candidato;
import objetos.Eleicao;
import objetos.Eleitor;

/**
 *
 * @author Anderson
 */
public class ProcessoVotacao implements ChecagemEleicao, ChecagemEleitor
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
    
    
    @Override
    public int getMaiorNumeroDigitosEleicao()
    {
        int maiorNumeroDigitos = 0;
        for(Eleicao eleicao : eleicoes)
            if(eleicao.getDigitos() > maiorNumeroDigitos)
                maiorNumeroDigitos = eleicao.getDigitos();
        return maiorNumeroDigitos;
    }

    @Override
    public Candidato votar(int numero, int votacaoCargoVez)
    {        
        Candidato candidato = null;
        candidato = getInfoCandidato(numero, votacaoCargoVez);
        if(candidato != null)
            candidato.receberVoto();
        return candidato;
    }
    
    @Override
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
    
    
    @Override
    public boolean eleitorJaVotou(String titulo)
    {
        boolean resultado = false;
        
        for(Eleitor eleitor : eleitores)
        {
            if(eleitor.getTitulo().equals(titulo) && eleitor.votou())
            {
                resultado = true;
                break;
            }
        }
        
        return resultado;
    }
    
    
    @Override
    public boolean eleitorVotaAqui(String titulo)
    {
        boolean resultado = false;
        
        for(Eleitor eleitor : eleitores)
        {
            if(eleitor.getTitulo().equals(titulo))
            {
                resultado = true;
                break;
            }
        }
        
        return resultado;
    }
    
    
    @Override
    public Eleitor getEleitor(String titulo)
    {
        Eleitor eleitor = null;

        int index = 0;
        while(index < eleitores.size() && eleitor == null)
        {
            if(eleitores.get(index).getTitulo().equals(titulo))
                eleitor = eleitores.get(index);
            index++;
        }
        
        return eleitor;
    }    
    
    
    public int getQuantidadeElementosPorCargo(String cargo)
    {
        int quantidade = 0;
        
        //quantidade = candidatos.stream().filter((candidato) -> (candidato.getCargo().equalsIgnoreCase(cargo))).map((_item) -> 1).reduce(quantidade, Integer::sum);
        for(Candidato candidato : candidatos)
        {
            if(candidato.getCargo().equalsIgnoreCase(cargo))
                quantidade++;
        }
        
        return quantidade;
    }
    
    
    public ArrayList<Candidato> getListaOrdenadaMaiorVoto()
    {
        Collections.sort(candidatos, new ComparadorCandidatos());
       
        return this.candidatos;
    }
    
    
    public ArrayList<Candidato> getEleitos()
    {
        ArrayList<Candidato> eleitos = new ArrayList();
        ArrayList<Candidato> candidatosOrdernados = getListaOrdenadaMaiorVoto();
        boolean achou = false;
        int percorreCandidatos = 0;
        
        for(int index = 0; index < eleicoes.size(); index++)
        {
            achou = false;
            for(int candidatosEleitos = 0; candidatosEleitos < eleicoes.get(index).getEleitos(); candidatosEleitos++)
            {
                while(!achou)
                {
                    if(candidatosOrdernados.get(percorreCandidatos).getCargo().equalsIgnoreCase(eleicoes.get(index).getTitulo()))
                    {
                        eleitos.add(candidatosOrdernados.get(percorreCandidatos));
                        achou = true;
                    }
                    percorreCandidatos++;
                }
            }
        }
        return eleitos;
    }
}