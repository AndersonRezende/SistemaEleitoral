/*
 * Aqui contém o polimorfismo de SUBTIPAGEM no método getDados();
 * É utilizado em: Eleitor e em Vice
 * AQUI CONTÉM O POLIMORFISMO DE SUBTIPAGEM
 * AQUI É PARTE DE UM PACOTE
 * Utilizado na classe “Pessoa” no método “getDados” que é sobrescrito conforme a 
 * necessidade do objeto que é utilizado.
 * 
 * O pacote “objetos” é referente as classes de objetos mais simples do sistema, 
 * referente aos itens básicos de uma votação, como: pessoas, candidatos,  eleitores, etc.
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