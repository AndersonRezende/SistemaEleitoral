/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

import arquivo.VerificaArquivo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import objetos.Candidato;
/**
 *
 * @author Anderson
 */
public class ThreadGravarResultado extends Thread
{
    private ProcessoVotacao processoVotacao; 
    private ObjetoCompartilhado oc;
    
    public ThreadGravarResultado(ObjetoCompartilhado oc, ProcessoVotacao processoVotacao)
    {
        this.oc = oc;
        this.processoVotacao = processoVotacao;
    }
    
    @Override
    public void run() 
    {
        ArrayList<Candidato> candidatos = processoVotacao.getCandidatos();
        int index = 0;                             //(100 * index) / size
        int porcentagem = 0;
        String caminho = new File("").getAbsolutePath()+"\\Arquivos\\Resultado\\"+processoVotacao.getEleicoes().get(0).getEleicao()+".txt";
        File arquivo = new File(caminho);
        if(!arquivo.exists())
        {
            try 
            {   arquivo.createNewFile(); } 
            catch (IOException ex) 
            {    System.err.print("Erro ao criar novo arquivo de resultado: "+ex.toString());  }
        }

        try 
        {
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(arquivo),"ISO-8859-1");

            while(index < candidatos.size())
            {
                osw.write(VerificaArquivo.ABREPOLITICO);
                osw.write("\n");;

                osw.write("\t" + VerificaArquivo.ABRENOME + candidatos.get(index).getEleitor().getNome() + VerificaArquivo.FECHANOME);
                osw.write("\n");;
                osw.write("\t" + VerificaArquivo.ABRETITULO + candidatos.get(index).getEleitor().getTitulo() + VerificaArquivo.FECHATITULO);
                osw.write("\n");;
                osw.write("\t" + VerificaArquivo.ABRECARGO + candidatos.get(index).getCargo() + VerificaArquivo.FECHACARGO);
                osw.write("\n");;
                osw.write("\t" + VerificaArquivo.ABRENUMERO + candidatos.get(index).getNumero() + VerificaArquivo.FECHANUMERO);
                osw.write("\n");;
                osw.write("\t" + VerificaArquivo.ABREPARTIDO + candidatos.get(index).getPartido() + VerificaArquivo.FECHAPARTIDO);
                osw.write("\n");;
                if(candidatos.get(index).temVice())
                {
                    osw.write("\t" + VerificaArquivo.ABREVICE + candidatos.get(index).getNomeVice() + VerificaArquivo.FECHAVICE);
                    osw.write("\n");;
                    osw.write("\t" + VerificaArquivo.ABREPARTIDOVICE + candidatos.get(index).getPartidoVice() + VerificaArquivo.FECHAPARTIDOVICE);
                    osw.write("\n");;
                }
                else
                {
                    osw.write("\t" + VerificaArquivo.ABREVICE + VerificaArquivo.FECHAVICE);
                    osw.write("\n");;
                    osw.write("\t" + VerificaArquivo.ABREPARTIDOVICE + VerificaArquivo.FECHAPARTIDOVICE);
                    osw.write("\n");;
                }
                osw.write("\t" + VerificaArquivo.ABREVOTOS + candidatos.get(index).getVotos() + VerificaArquivo.FECHAVOTOS);
                osw.write("\n");;

                osw.write(VerificaArquivo.FECHAPOLITICO);
                osw.write("\n");;

                porcentagem = (100 * index) / candidatos.size();
                oc.setValor(porcentagem);
                index++;
                try {
                    Thread.sleep(10);
                 } catch (Exception e) {
                    e.printStackTrace();
                 }
            }
            oc.setValor(100);

            osw.close();
            fw.close();
        } 
        catch (IOException ex) 
        {   System.err.print("Erro ao gravar arquivo de resultado: "+ex.toString());  }
    }
}
