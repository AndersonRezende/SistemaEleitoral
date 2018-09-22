/*
 * Aqui são implementado as duas Interfaces: ChecagemEleicao e ChecagemEleitor
 * AQUI CONTÉM UM ARRAYLIST
 * AQUI É IMPLEMENTADO UMA INTERFACE
 * ArrayList criado na classe “ProcessoVotacao” do tipo Candidato para conter todos os candidatos do processo de eleição escolhido.
 *
 * A classe “ProcessoVotacao” implementa duas interfaces, sendo elas: “ChecagemEleicao” 
 * e “ChecagemEleitor”, que fornecem a assinatura dos métodos “eleitorJaVotou”, “eleitorVotaAqui”, 
 * “getEleitor”, “getMaiorNumeroDigitosEleicao”, “getInfoCandidato”, “votar” que são utilizadas para 
 * determinar os procedimentos que deverão acontecer na classe.
 */
package objetos.auxiliares;

import interfaces.ChecagemEleicao;
import interfaces.ChecagemEleitor;
import java.util.ArrayList;
import java.util.Collections;
import objetos.Candidato;
import objetos.Eleicao;
import objetos.Eleitor;
import objetos.Mesario;

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
    private ArrayList<Candidato> eleitos;
    private Mesario mesario;
    
    
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
    
    public void setMesario(Mesario mesario)
    {   this.mesario = mesario; }
    
    public Mesario getMesario()
    {   return this.mesario;    }
    
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
        ArrayList<Candidato> aux = (ArrayList<Candidato>) candidatos.clone();
        Collections.sort(aux, new ComparadorCandidatos());
        
        return aux;
    }
    
    
    public ArrayList<Candidato> getEleitos()
    {
        ArrayList<Candidato> eleitos = new ArrayList();
        ArrayList<Candidato> candidatosOrdernados = getListaOrdenadaMaiorVoto();
        boolean achou = false;
        int incluidos = 0;
        
        for(int cargos = 0; cargos < eleicoes.size(); cargos++)                 //Percorre todos os cargos disponíveis
        {
            incluidos = 0;
            for(Candidato c : candidatosOrdernados)
            {
                if(c.getCargo().equalsIgnoreCase(eleicoes.get(cargos).getTitulo()) && incluidos < eleicoes.get(cargos).getEleitos())
                {
                    incluidos++;
                    eleitos.add(c);
                }
            }
        }
        return eleitos;
    }
    
    
    public boolean foiEleito(Candidato c)
    {
        boolean eleito = false;
        eleitos =  this.getEleitos();
        
        for(Candidato candidato : eleitos)
        {
            if(candidato.getCargo().equals(c.getCargo()) && candidato.getNumero() == c.getNumero())
            {
                eleito = true;
                break;
            }
        }
        
        return eleito;
    }
}