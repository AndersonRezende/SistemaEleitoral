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
public class HorarioIncorretoEleicaoException extends Exception
{
    private String mensagem;
    private final int HORARIOINICIAL = 0;
    private final int HORARIOFINAL = 1;
    
    public HorarioIncorretoEleicaoException(int opcao)
    {   
        if(opcao == HORARIOINICIAL)
            this.mensagem = "Não é possível iniciar o processo de eleição neste horário.";
        if(opcao == HORARIOFINAL)
            this.mensagem = "Não é possível terminar o processo de eleição neste horário.";
    }
    
    @Override
    public String getMessage()
    {   return this.mensagem; }
}
