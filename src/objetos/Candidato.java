/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.VotacaoCandidato;

/**
 *
 * @author Anderson
 */
public class Candidato implements VotacaoCandidato
{
    private int numero;
    private String partido;
    private int votos;
    private String cargo;
    private Eleitor eleitor; 
    private Vice vice;

    
    public Candidato(String nome, String titulo, String cargo, int numero, String partido)
    {
        this.eleitor = new Eleitor(nome,titulo);
        this.cargo = cargo;
        this.numero = numero;
        this.partido = partido;
    }
    
    public Candidato(String nome, String titulo, String cargo, int numero, String partido, String vice, String partidoVice)
    {
        this(nome, titulo, cargo, numero, partido);
        this.vice = new Vice(vice, partidoVice);
    }
    
    public int getNumero() 
    {   return numero;  }

    public void setNumero(int numero) 
    {   this.numero = numero;   }

    public String getPartido() 
    {   return partido; }

    public void setPartido(String partido) 
    {   this.partido = partido; }

    public String getCargo() 
    {   return cargo;   }

    public void setCargo(String cargo) 
    {   this.cargo = cargo; }
    
    public Vice getVice()
    {   return vice;    }
    
    public Eleitor getEleitor()
    {   return eleitor; }

    @Override
    public int getVotos() 
    {   return votos;  }

    @Override
    public boolean receberVoto() 
    {   
        boolean votou = false;
        int aux = votos;
        this.votos++;
        votou = votos > aux;
        return votou;
    }
}
