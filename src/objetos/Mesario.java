/*
 * AQUI CONTÉM ATRIBUTOS ENCAPSULADOS
 * Todos os atributos de classes não estáticas são do private. Exemplo: atributo “login” e “senha” da 
 * Classe “Mesario” são do tipo private para que não possam ser alterados diretamente.
 */
package objetos;

/**
 *
 * @author Anderson
 */
public class Mesario
{
    private String login;
    private String senha;

    public Mesario(String login, String senha) 
    {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() 
    {   return login;   }

    public String getSenha() 
    {   return senha;  }
}
