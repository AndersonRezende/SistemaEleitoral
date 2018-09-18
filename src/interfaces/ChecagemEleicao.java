/*
 * Esta interface é utilizada em ProcessoVotacao
 */
package interfaces;

import objetos.Candidato;

/**
 *
 * @author Anderson
 */
public interface ChecagemEleicao 
{
    public int getMaiorNumeroDigitosEleicao();
    
    public Candidato getInfoCandidato(int numero, int votacaoCargoVez);
    
    public Candidato votar(int numero, int votacaoCargoVez);
}
