/*
 * AQUI CONTÉM UMA EXCEÇÃO
 * a classe “FormatoIncorretoException” foi criada para tratar as exceções que ocorrem 
 * quando for verificado que um determinado arquivo está no formato correto. 
 * Exemplo: arquivo de eleição com tags incompletas.
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
