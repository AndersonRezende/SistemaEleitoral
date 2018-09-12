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
class Vice 
{
    private String nome;
    private String partidoVice;

    public Vice(String nome, String partidoVice)
    {   
        this.nome = nome;
        this.partidoVice = partidoVice;
    }

    public String getNome() 
    {   return nome;    }
    
    public String getPartidoVice()
    {   return partidoVice; }
}
