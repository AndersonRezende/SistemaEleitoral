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
public class FormatoIncorretoException extends Exception
{
    private String nomeArquivo;
    private String mensagem;
    
    public FormatoIncorretoException(String nomeArquivo)
    {   
        this.nomeArquivo = nomeArquivo;
        this.mensagem = "Erro na leitura do arquivo.\n\n"
                      + "O arquivo \""+nomeArquivo+"\" não está no formato correto.";
    }
    
    @Override
    public String getMessage()
    {   return this.mensagem; }
}
