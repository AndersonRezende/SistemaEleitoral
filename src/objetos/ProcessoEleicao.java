/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


/**
 *
 * @author Anderson
 */
public abstract class ProcessoEleicao 
{
    protected final int HORARIOINICIOELEICAO = 0;
    protected final int HORARIOFINALELEICAO = 17;
    protected final int HORARIOTOLERANTEFINALIZARVOTACAO = 1;
    
    public abstract int getHorarioInicioEleicao();
    
    public abstract int getHorarioFinalEleicao();
    
    public abstract boolean podeIniciarVotacao(float horaAtual);
    
    public abstract boolean podeTerminarVotacao(float horaAtual);
}
