/*
 * Aqui contém o polimorfismo de SUBTIPAGEM no método getDados();
 * É utilizado em: Eleitor e em Vice
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
    
    public String getDados()
    {   return this.nome;   }
}