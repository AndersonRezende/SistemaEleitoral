/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Login;

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
