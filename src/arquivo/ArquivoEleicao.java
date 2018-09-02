/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import objetos.Eleicao;

/**
 *
 * @author Anderson
 */
public class ArquivoEleicao 
{
    
    public static ArrayList<Eleicao> lerArquivo(String path)
    {
        ArrayList<Eleicao> eleicoes = new ArrayList();
        Eleicao eleicao;
        String linha;
        File arquivo = new File(path);
        
        String nome = "";
        String titulo = "";
        boolean vice = false;
        int digitos = 0;
        int votos = 0;
        
        int inicio = 0;
        int fim = 0;
        
        if(arquivo.exists())
        {
            try
            {
                //FileReader fr = new FileReader(arquivo);
                //BufferedReader br = new BufferedReader(fr);
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo.getAbsolutePath()), "ISO-8859-1"));

                if(br.ready())                                                   //Enquanto tiver linhas para ler e erro não for verdadeiro
                {
                    linha = br.readLine();
                    
                    inicio = linha.indexOf(VerificaArquivo.ABREELEICAO)+VerificaArquivo.ABREELEICAO.length();
                    fim = linha.indexOf(VerificaArquivo.FECHAELEICAO);
                    nome = linha.substring(inicio, fim);
                    if(br.ready())
                        linha = br.readLine();                                  //<cargos>
                    
                    while(br.ready() && !linha.contains(VerificaArquivo.FECHACARGOS))
                    {   
                        linha = br.readLine();
                        if(linha.contains(VerificaArquivo.ABRECARGO))
                        {
                            titulo = "";
                            vice = false;
                            digitos = 0;
                            votos = 0;
                        }
                        
                        if(linha.contains(VerificaArquivo.ABRETITULO) && linha.contains(VerificaArquivo.FECHATITULO))
                        {
                            inicio = linha.indexOf(VerificaArquivo.ABRETITULO)+VerificaArquivo.ABRETITULO.length();
                            fim = linha.indexOf(VerificaArquivo.FECHATITULO);
                            titulo = linha.substring(inicio, fim);
                        }
                        if(linha.contains(VerificaArquivo.ABREVICE) && linha.contains(VerificaArquivo.FECHAVICE))
                        {
                            inicio = linha.indexOf(VerificaArquivo.ABREVICE)+VerificaArquivo.ABREVICE.length();
                            fim = linha.indexOf(VerificaArquivo.FECHAVICE);
                            String aux = linha.substring(inicio, fim);
                            vice = aux.equalsIgnoreCase("sim");
                        }
                        if(linha.contains(VerificaArquivo.ABREDIGITOS) && linha.contains(VerificaArquivo.FECHADIGITOS))
                        {
                            inicio = linha.indexOf(VerificaArquivo.ABREDIGITOS)+VerificaArquivo.ABREDIGITOS.length();
                            fim = linha.indexOf(VerificaArquivo.FECHADIGITOS);
                            digitos = Integer.parseInt(linha.substring(inicio, fim));
                        }
                        if(linha.contains(VerificaArquivo.ABREVOTOS) && linha.contains(VerificaArquivo.FECHAVOTOS))
                        {
                            inicio = linha.indexOf(VerificaArquivo.ABREVOTOS)+VerificaArquivo.ABREVOTOS.length();
                            fim = linha.indexOf(VerificaArquivo.FECHAVOTOS);
                            votos = Integer.parseInt(linha.substring(inicio, fim));
                        }
                        
                        if(linha.contains(VerificaArquivo.FECHACARGO) && !titulo.equals("") && (digitos > 0) && (votos > 0))
                        {
                            eleicao = new Eleicao(nome, titulo, vice, digitos, votos);
                            eleicoes.add(eleicao);
                        }
                    }
                }
                br.close();
            }
            catch (FileNotFoundException ex) 
            {   System.err.println(ex); } 
            catch (IOException ex) 
            {   System.err.println(ex); }
        }
        return eleicoes;
    }
    
}
