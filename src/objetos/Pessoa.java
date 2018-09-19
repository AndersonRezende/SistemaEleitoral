/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author Anderson
 */
public class Pessoa 
{
    private String nome;
    
    public Pessoa(String nome)
    {
        this.nome = nome;
    }
    
    public String getNome()
    {   return this.nome;   }
    
    public void setNome(String nome)
    {   this.nome = nome;   }
}