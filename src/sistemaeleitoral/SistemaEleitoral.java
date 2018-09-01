/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaeleitoral;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import sistemagrafico.LayoutMaster;

/**
 *
 * @author Anderson
 */
public class SistemaEleitoral 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        LayoutMaster lm = new LayoutMaster();
        lm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*File file = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Eleição");
        File[] s = file.listFiles();
        
        for(int i = 0; i<s.length; i++)
            if(s[i].isFile())
                System.out.print(s[i].getName());
        */
        //String path = new File("").getAbsolutePath() + "\\Arquivos\\Eleição\\Eleição.txt";
        //System.out.println("O arquivo é: "+VerificaArquivo.checarArquivoEleicaoPresidencial(path));
    }
    
}
