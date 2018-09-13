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
public class ArquivoNaoEncontradoException extends Exception
{
    
    private String mensagem;
    
    public ArquivoNaoEncontradoException(String tipoArquivo)
    {   this.mensagem = "Não foi possível localizar o arquivo contendo os "+tipoArquivo+".\n"
            + "Certifique-se que o mesmo se encontra na pasta e com o nome correto."; }
    
    @Override
    public String getMessage()
    {   return this.mensagem;  }
}
