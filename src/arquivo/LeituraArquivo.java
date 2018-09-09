/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivo;

import interfaces.Login;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import objetos.Candidato;
import objetos.Eleicao;
import objetos.Mesario;

/**
 *
 * @author Anderson
 */
public class LeituraArquivo implements Login
{
    
    
    public static ArrayList<Eleicao> lerArquivoEleicao(String path)
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
        int eleitos = 0;
        
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
                        if(linha.contains(VerificaArquivo.ABREELEITOS) && linha.contains(VerificaArquivo.FECHAELEITOS))
                        {
                            inicio = linha.indexOf(VerificaArquivo.ABREELEITOS)+VerificaArquivo.ABREELEITOS.length();
                            fim = linha.indexOf(VerificaArquivo.FECHAELEITOS);
                            eleitos = Integer.parseInt(linha.substring(inicio, fim));
                        }
                        
                        if(linha.contains(VerificaArquivo.FECHACARGO) && !titulo.equals("") && (digitos > 0))
                        {
                            eleicao = new Eleicao(nome, titulo, vice, digitos, eleitos);
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
    
    
   /* private static ArrayList<Candidato> lerCandidato(String path)
    {
        File arquivo = new File(path);
        ArrayList<Candidato> candidatos = new ArrayList();
        Candidato candidato;
        String nome = "";
        String vice = "";
        String partido = "";
        String numero = "";
        int votos = 0;
        String cargo = ""; 
        String titulo = "";
        
        String linha;
        
        if(arquivo.exists())
        {
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo.getAbsolutePath()), "ISO-8859-1"));
                while(br.ready())
                {
                    linha=br.readLine();
                    if(linha.contains("<nome>"))
                    {
                        linha = linha.replaceAll("<nome>", "");
                        nome = linha;
                    }
                    if(linha.contains("<numero>"))
                    {
                        linha = linha.replaceAll("<numero>", "");
                        numero = linha;
                    }
                    if(linha.contains("<partido>"))
                    {
                        linha = linha.replaceAll("<partido>", "");
                        partido = linha;
                    }
                    if(linha.contains("<cargo>"))
                    {
                        linha = linha.replaceAll("<cargo>", "");
                        cargo = linha;
                    }
                    if(linha.contains("<titulo>"))
                    {
                        linha = linha.replaceAll("<titulo>", "");
                        titulo = linha;
                    }
                    if(linha.contains("<vice>"))
                    {
                        linha = linha.replaceAll("<vice>", "");
                        vice = linha;
                    }


                    if(linha.contains("</politico>"))
                    {    
                        if(vice.length() > 1)
                            c = new Candidato(nome, numero, partido, cargo, titulo, new Vice(vice));
                        else
                            c = new Candidato(nome, numero, partido, cargo, titulo);
                        candidatos.add(c);
                        nome = "";
                        numero = "";
                        partido = "";
                        cargo = "";
                        titulo = "";
                        vice = "";
                    }

                    if(linha.contains("</politicos>"))
                        break;


                }
                br.close();
            }
            catch (FileNotFoundException ex) 
            {   System.err.println(ex); } 
            catch (IOException ex) 
            {   System.err.println(ex); }
        }
        else
        {
          System.out.println("Arquivo nÃ£o encontrado.");
        }
        return candidatos;
    }*/
    
    
    public static ArrayList<Mesario> lerMesario()
    {
        String login = "";
        String senha = "";
        boolean lendoMesario = false;
        boolean contemLogin = false;
        boolean contemSenha = false;
        int inicio = 0;
        int fim = 0;
        ArrayList<Mesario> mesarios = new ArrayList();
        Mesario mesario;
        
        String linha = "";
        
        File arquivo = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Mesário\\Mesários.txt");
        if(arquivo.exists())
        {
            try 
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo.getAbsolutePath()), "ISO-8859-1"));
                while(br.ready())
                {
                    linha = br.readLine();
                    if(linha.contains(VerificaArquivo.ABREMESARIO))
                    {
                        login = "";
                        senha = "";
                        lendoMesario = true;
                    }
                    if(linha.contains(VerificaArquivo.ABRELOGIN) && lendoMesario)
                    {
                        inicio = linha.indexOf(VerificaArquivo.ABRELOGIN)+VerificaArquivo.ABRELOGIN.length();
                        fim = linha.indexOf(VerificaArquivo.FECHALOGIN);
                        login = linha.substring(inicio, fim);
                        contemLogin = true;
                    }
                    if(linha.contains(VerificaArquivo.ABRESENHA) && lendoMesario)
                    {
                        inicio = linha.indexOf(VerificaArquivo.ABRESENHA)+VerificaArquivo.ABRESENHA.length();
                        fim = linha.indexOf(VerificaArquivo.FECHASENHA);
                        senha = linha.substring(inicio, fim);
                        contemSenha = true;
                    }
                    if(linha.contains(VerificaArquivo.FECHAMESARIO) && lendoMesario && contemLogin && contemSenha)
                    {
                        lendoMesario = false;
                        contemLogin = false;
                        contemSenha = false;
                        mesario = new Mesario(login, senha);
                        mesarios.add(mesario);
                    }
                }
                br.close();
            } 
            catch (FileNotFoundException | UnsupportedEncodingException ex) 
            {   System.err.println(ex); } 
            catch (IOException ex) 
            {   System.err.println(ex); }            
        }
        else
            System.err.println("Arquivo não encontrado.");
    return mesarios;
    }
    
    
    @Override
    public boolean logar(Mesario mesario) 
    {
        ArrayList<Mesario> mesarios = lerMesario();
        boolean logado = false;
        
        for(Mesario mesarioAux : mesarios)
        {
            if(mesarioAux.getLogin().equals(mesario.getLogin()) && mesarioAux.getSenha().equals(mesario.getSenha()))
            {
                logado = true;
                break;
            }
        }
        
        return logado;
    }
}
