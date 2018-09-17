/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.ChecarVotoEleitor;
import interfaces.Votar;

/**
 *
 * @author Anderson
 */
public class Eleitor extends Pessoa implements ChecarVotoEleitor, Votar
{
    private String nome;
    private String titulo;
    private boolean votou;

    public Eleitor(String nome, String titulo) 
    {
        this.nome = nome;
        this.titulo = titulo;
        this.votou = false;
    }
    
    public String getTitulo() 
    {   return titulo;  }

    public void setTitulo(String titulo) 
    {   this.titulo = titulo;   }
    
    @Override
    public String getNome() 
    {   return this.nome;   }

    @Override
    public void setNome(String nome) 
    {   this.nome = nome;   }

    @Override
    public boolean votou()
    {   return this.votou;  }
    
    @Override
    public boolean votar() 
    {
        this.votou = true;
        return votou;
    }
}