/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Anderson
 */
public class LogArquivo 
{
    public static void gravaDados(String log)
    {
        final String CAMINHO = new File("").getAbsolutePath()+"\\Arquivos\\Log\\log.txt";
        File arquivo = new File(CAMINHO);
        if(!arquivo.exists())
        {
            try 
            {   arquivo.createNewFile(); } 
            catch (IOException ex) 
            {    System.err.print("Erro ao criar novo arquivo de log: "+ex.toString());  }
        }
        
        try 
        {
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(log);
            bw.close();
            fw.close();
        } 
        catch (IOException ex) 
        {   System.err.print("Erro ao criar novo arquivo de log: "+ex.toString());  }
    }
}
