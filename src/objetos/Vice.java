/*
 * Esta classe possui o POLIMORFISMO DE SUBTIPAGEM no método getDados da classe Pessoa
 * AQUI CONTÉM UM ACOPLAMENTO DINÂMICO (GETNOME)
 * A classe “Vice” estende da classe “Pessoa”, na classe “Pessoa” existe o método “getNome”, 
 * este método é chamado na classe “Vice” para obter o nome do vice candidato.
 */
package objetos;

/**
 *
 * @author Anderson
 */
class Vice extends Pessoa
{
    private String partido;

    public Vice(String nome, String partido)
    {   
        super(nome);
        this.partido = partido;
    }

    public String getPartido()
    {   return partido; }
    
  
    @Override
    public void setNome(String nome) 
    {   super.setNome(nome);   }

    @Override
    public String getDados() 
    {   return super.getDados()+" ("+this.partido+")";   }
}
