/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

import objetos.Eleitor;
import objetos.Mesario;

/**
 *
 * @author Anderson
 */
public class ThreadChecagemTempo extends Thread
{
    private ChecagemTempo<Eleitor> cte;
    private ChecagemTempo<Mesario> ctm;
    private boolean eleitor;
    
    
    public ThreadChecagemTempo(ChecagemTempo<Eleitor> cte, boolean eleitor)
    {   
        this.cte = cte;
        this.eleitor = eleitor;
    }
    
    public ThreadChecagemTempo(ChecagemTempo<Mesario> ctm)
    {   this.ctm = ctm; }
    
    @Override
    public void run()
    {
        if(eleitor)
        {
            while(!cte.getFinalizar())
            {
                if(cte.passouDoTempo())
                {
                    System.out.println("Eleitor está demorando...");
                    try 
                    {   Thread.sleep(60000);   } 
                    catch (Exception e) 
                    {  e.printStackTrace();    }

                }
                try 
                {   Thread.sleep(1000);   } 
                catch (Exception e) 
                {  e.printStackTrace();    }
            }
        }
        else
        {
            while(!ctm.getFinalizar())
            {
                if(ctm.passouDoTempo())
                {
                    System.out.println("O processo de eleição passou do horário...");
                    try 
                    {   Thread.sleep(60000);   } 
                    catch (Exception e) 
                    {  e.printStackTrace();    }

                }
                try 
                {   Thread.sleep(36000000);   } 
                catch (Exception e) 
                {  e.printStackTrace();    }
            }
        
        }
    }
}
