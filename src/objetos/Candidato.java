/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Votar;

/**
 *
 * @author Anderson
 */
public class Candidato implements Votar
{
    private String numero;
    private String partido;
    private int votos;
    private String cargo;
    private Eleitor eleitor; 
    private Vice vice;

    public Candidato(String numero, String partido, String cargo, String nome, String titulo) 
    {
        this.numero = numero;
        this.partido = partido;
        this.cargo = cargo;
        this.votos = 0;
        this.eleitor = new Eleitor(nome, titulo);
    }
    
    public Candidato(String nome, String numero, String partido, String cargo, String titulo, Vice vice) 
    {
        this(nome, numero, partido, cargo, titulo);
        this.vice = new Vice(nome);
    }
    
    public String getNumero() 
    {   return numero;  }

    public void setNumero(String numero) 
    {   this.numero = numero;   }

    public String getPartido() 
    {   return partido; }

    public void setPartido(String partido) 
    {   this.partido = partido; }

    public String getCargo() 
    {   return cargo;   }

    public void setCargo(String cargo) 
    {   this.cargo = cargo; }

    public String getVice() 
    {   return vice.getNome();  }

    @Override
    public int getQuantidadeVotos() 
    {   return votos;  }

    @Override
    public boolean votar() 
    {
        boolean votou = false;
        int aux = votos;
        this.votos++;
        votou = aux < votos;
        return votou;
    }
}
