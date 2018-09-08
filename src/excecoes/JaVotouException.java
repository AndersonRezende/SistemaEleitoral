/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excecoes;

/**
 *
 * @author Anderson
 */
public class JaVotouException extends Exception
{
    private String mensagem;
    
    public JaVotouException(String nomeEleitor)
    {   this.mensagem = "O eleitor "+nomeEleitor+" já votou.";   }
    
    @Override
    public String getMessage()
    {   return this.mensagem; }
}