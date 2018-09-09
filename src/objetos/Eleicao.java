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
public class Eleicao extends ProcessoEleicao
{
    private String eleicao;
    private String titulo;
    private boolean vice;
    private int digitos;
    private int eleitos;

    public Eleicao(String eleicao, String titulo, boolean vice, int digitos, int eleitos) 
    {
        this.eleicao = eleicao;
        this.titulo = titulo;
        this.vice = vice;
        this.digitos = digitos;
        this.eleitos = eleitos;
    }

    public String getEleicao() 
    {   return eleicao; }

    public String getTitulo() 
    {   return titulo;  }

    public boolean getVice() 
    {   return vice;    }

    public int getDigitos() 
    {   return digitos; }
    
    public int getEleitos()
    {   return eleitos; }

    @Override
    public int getHorarioInicioEleicao() 
    {   return super.HORARIOINICIOELEICAO;  }

    @Override
    public int getHorarioFinalEleicao() 
    {   return super.HORARIOFINALELEICAO;   }

    @Override
    public boolean podeIniciarVotacao(float hora) 
    {
        int horaAtual = (int) hora;
        boolean podeIniciar = false;
        podeIniciar = horaAtual >= super.HORARIOINICIOELEICAO;
        return podeIniciar;
    }

    @Override
    public boolean podeTerminarVotacao(float hora) 
    {
        int horaAtual = (int) hora;
        boolean podeTerminar = false;
        podeTerminar = horaAtual > super.HORARIOFINALELEICAO;
        return podeTerminar;
    }
}