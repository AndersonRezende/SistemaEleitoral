/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

/**
 *
 * @author Anderson
 */
public class ObjetoCompartilhado 
{
    private int valor = 0;
    private boolean disponivel = false;
    
    
    public void setValor(int valor)
    {   
        this.valor = valor; 
        disponivel = true;
    }
    
    public int getValor()
    {   return this.valor;  }
    
    public boolean disponivel()
    {   return disponivel;  }
}
