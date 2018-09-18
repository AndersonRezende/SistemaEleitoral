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
import java.util.ArrayList;
import javax.swing.JProgressBar;
import objetos.Candidato;
import objetos.Eleicao;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class Resultado 
{
    public static void gravarResultado(ProcessoVotacao processoVotacao, JProgressBar progressBarFinalizarVoto)
    {   
        ArrayList<Eleicao> eleicoes = processoVotacao.getEleicoes();
        ArrayList<Candidato> candidatos = processoVotacao.getCandidatos();
        int index = 0;
        int conversao = 0;                              //(100 * index) / size
        int porcentagem = 0;
        String caminho = new File("").getAbsolutePath()+"\\Arquivos\\Resultado\\"+processoVotacao.getEleicoes().get(0).getEleicao()+".txt";
        File arquivo = new File(caminho);
        if(!arquivo.exists())
        {
            try 
            {   arquivo.createNewFile(); } 
            catch (IOException ex) 
            {    System.err.print("Erro ao criar novo arquivo de log: "+ex.toString());  }
        }
        
        try 
        {
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            
            while(index < candidatos.size())
            {
                bw.write(VerificaArquivo.ABREPOLITICO);
                bw.newLine();
                
                bw.write(VerificaArquivo.ABRENOME + candidatos.get(index).getEleitor().getNome() + VerificaArquivo.FECHANOME);
                bw.newLine();
                bw.write(VerificaArquivo.ABREPARTIDO + candidatos.get(index).getPartido() + VerificaArquivo.FECHAPARTIDO);
                bw.newLine();
                bw.write(VerificaArquivo.ABRENUMERO + candidatos.get(index).getNumero() + VerificaArquivo.FECHANUMERO);
                bw.newLine();
                bw.write(VerificaArquivo.ABREVOTOS + candidatos.get(index).getVotos() + VerificaArquivo.FECHAVOTOS);
                bw.newLine();
                if(candidatos.get(index).temVice())
                {
                    bw.write(VerificaArquivo.ABREVICE + candidatos.get(index).getNomeVice() + VerificaArquivo.FECHAVICE);
                    bw.newLine();
                }
                
                bw.write(VerificaArquivo.FECHAPOLITICO);
                bw.newLine();
                
                porcentagem = (100 * index) / candidatos.size();
                System.out.println(""+progressBarFinalizarVoto.getValue());
                progressBarFinalizarVoto.setValue(porcentagem);
                progressBarFinalizarVoto.setString(""+porcentagem);
                index++;
                try 
                {   Thread.sleep(100); } 
                catch (Exception e) 
                {   e.printStackTrace();    }
            }
            
            //progressBarFinalizarVoto.setValue(100);
            //progressBarFinalizarVoto.setString(""+100);
            
            bw.close();
            fw.close();
        } 
        catch (IOException ex) 
        {   System.err.print("Erro ao criar novo arquivo de log: "+ex.toString());  }
    }
}
