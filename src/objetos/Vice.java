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
class Vice extends Pessoa
{
    private String nome;
    private String partido;

    public Vice(String nome, String partido)
    {   
        this.nome = nome;
        this.partido = partido;
    }

    public String getPartido()
    {   return partido; }
    
    @Override
    public String getNome() 
    {   return nome;    }
  
    @Override
    public void setNome(String nome) 
    {   this.nome = nome;   }
}
