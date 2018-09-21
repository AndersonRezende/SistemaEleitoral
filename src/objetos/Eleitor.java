/*
 * Aqui é implementada a Interface Votacao
 * Esta classe possui um acoplamento dinâmico da classe Pessoa nos metodos getNome e setNome
 * Esta classe possui o POLIMORFISMO DE SUBTIPAGEM no método getDados da classe Pessoa
 */
package objetos;

import interfaces.Votacao;

/**
 *
 * @author Anderson
 */
public class Eleitor extends Pessoa implements Votacao
{
    private String titulo;
    private boolean votou;

    public Eleitor(String nome, String titulo) 
    {
        super(nome);
        this.titulo = titulo;
        this.votou = false;
    }
    
    public String getTitulo() 
    {   return titulo;  }

    public void setTitulo(String titulo) 
    {   this.titulo = titulo;   }
    
    @Override
    public String getNome() 
    {   return super.getNome();   }

    @Override
    public void setNome(String nome) 
    {   super.setNome(nome);   }

    @Override
    public boolean votou()
    {   return this.votou;  }
    
    @Override
    public boolean votar() 
    {
        this.votou = true;
        return votou;
    }
    
    @Override
    public String getDados()
    {
        String dados = "Nome: "+super.getDados() +"\n"
                     + "Titulo: "+this.getTitulo() +"\n";
        return dados;
    }
}