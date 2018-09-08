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
public class Eleicao 
{
    private String eleicao;
    private String titulo;
    private boolean vice;
    private int digitos;
    private int votos;
    private int eleitos;

    public Eleicao(String eleicao, String titulo, boolean vice, int digitos, int votos, int eleitos) 
    {
        this.eleicao = eleicao;
        this.titulo = titulo;
        this.vice = vice;
        this.digitos = digitos;
        this.votos = votos;
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

    public int getVotos() 
    {   return votos;   }
    
    public int getEleitos()
    {   return eleitos; }
}
