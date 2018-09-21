/*
 * Esta classe possui um acoplamento dinâmico da classe Pessoa nos metodos getNome e setNome
 * Esta classe possui o POLIMORFISMO DE SUBTIPAGEM no método getDados da classe Pessoa
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
    public String getNome() 
    {   return super.getNome();    }
  
    @Override
    public void setNome(String nome) 
    {   super.setNome(nome);   }

    @Override
    public String getDados() 
    {   return super.getDados()+" ("+this.partido+")";   }
}
