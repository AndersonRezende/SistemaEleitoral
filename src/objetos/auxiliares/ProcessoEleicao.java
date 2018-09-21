/*
 * AQUI CONTÉM ATRIBUTO FINAL EM SUPERCLASSE (HORARIOINICIOELEICAO)
 * AQUI CONTÉM UMA CLASSE ABSTRATA
 * Atributo final “HORARIOINICIOELEICAO” criado na superclasse “ProcessoEleicao” 
 * para checar se o horário de início das eleições está correto para que a mesma possa acontecer.
 * 
 * Classe abstrata “ProcessoEleicao” que contém os elementos, assinaturas de métodos 
 * básicos e constantes necessárias para que o procedimento de eleição possa ocorrer.
 */
package objetos.auxiliares;


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
