/*
 * Aqui é implementada a Interface Votacao
 * Esta classe possui um acoplamento dinâmico da classe Pessoa nos metodos getNome e setNome
 * Esta classe possui o POLIMORFISMO DE SUBTIPAGEM no método getDados da classe Pessoa
 * AQUI CONTÉM COMPOSIÇÃO (ELEITOR)
 * AQUI CONTÉM AGREGAÇÃO (VICE)
 * AQUI É IMPLEMENTADO UMA INTERFACE
 * Composição do tipo “Eleitor” na classe “Candidato”, pois não faz sentido existir candidato que não seja também um eleitor.
 *
 * Agregação do tipo “Vice” na classe “Candidato”, pois pode existir candidato que não tenha vice.
 *  
 * A classe “Eleitor” implementa a interface “Votacao” que fornece a assinatura 
 * dos métodos “votar” e “votou” que é utilizado para o eleitor realizar seu voto e 
 * checar se o mesmo já votou, respectivamente.
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