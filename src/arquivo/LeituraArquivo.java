/*
 * Aqui é implementado a Interface Login
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
import objetos.Eleitor;
import objetos.Mesario;

/**
 *
 * @author Anderson
 */
public class LeituraArquivo extends Arquivo implements Login
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
                    
                    inicio = linha.indexOf(ABREELEICAO)+ABREELEICAO.length();
                    fim = linha.indexOf(FECHAELEICAO);
                    nome = linha.substring(inicio, fim);
                    if(br.ready())
                        linha = br.readLine();                                  //<cargos>
                    
                    while(br.ready() && !linha.contains(FECHACARGOS))
                    {   
                        linha = br.readLine();
                        if(linha.contains(ABRECARGO))
                        {
                            titulo = "";
                            vice = false;
                            digitos = 0;
                            votos = 0;
                        }
                        
                        if(linha.contains(ABRETITULO) && linha.contains(FECHATITULO))
                        {
                            inicio = linha.indexOf(ABRETITULO)+ABRETITULO.length();
                            fim = linha.indexOf(FECHATITULO);
                            titulo = linha.substring(inicio, fim);
                        }
                        if(linha.contains(ABREVICE) && linha.contains(FECHAVICE))
                        {
                            inicio = linha.indexOf(ABREVICE)+ABREVICE.length();
                            fim = linha.indexOf(FECHAVICE);
                            String aux = linha.substring(inicio, fim);
                            vice = aux.equalsIgnoreCase("sim");
                        }
                        if(linha.contains(ABREDIGITOS) && linha.contains(FECHADIGITOS))
                        {
                            inicio = linha.indexOf(ABREDIGITOS)+ABREDIGITOS.length();
                            fim = linha.indexOf(FECHADIGITOS);
                            digitos = Integer.parseInt(linha.substring(inicio, fim));
                        }
                        if(linha.contains(ABREELEITOS) && linha.contains(FECHAELEITOS))
                        {
                            inicio = linha.indexOf(ABREELEITOS)+ABREELEITOS.length();
                            fim = linha.indexOf(FECHAELEITOS);
                            eleitos = Integer.parseInt(linha.substring(inicio, fim));
                        }
                        
                        if(linha.contains(FECHACARGO) && !titulo.equals("") && (digitos > 0))
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
                    if(linha.contains(ABREMESARIO))
                    {
                        login = "";
                        senha = "";
                        lendoMesario = true;
                    }
                    if(linha.contains(ABRELOGIN) && lendoMesario)
                    {
                        inicio = linha.indexOf(ABRELOGIN)+ABRELOGIN.length();
                        fim = linha.indexOf(FECHALOGIN);
                        login = linha.substring(inicio, fim);
                        contemLogin = true;
                    }
                    if(linha.contains(ABRESENHA) && lendoMesario)
                    {
                        inicio = linha.indexOf(ABRESENHA)+ABRESENHA.length();
                        fim = linha.indexOf(FECHASENHA);
                        senha = linha.substring(inicio, fim);
                        contemSenha = true;
                    }
                    if(linha.contains(FECHAMESARIO) && lendoMesario && contemLogin && contemSenha)
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

    
    public static ArrayList<Eleitor> lerEleitor(String nomeArquivo)
    {
        String nome = "";
        String titulo = "";
        boolean lendoEleitor = false;
        boolean contemNome = false;
        boolean contemTitulo = false;
        int inicio = 0;
        int fim = 0;
        ArrayList<Eleitor> eleitores = new ArrayList();
        Eleitor eleitor;
        
        String linha = "";
        
        File arquivo = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Eleitores\\"+nomeArquivo+".txt");
        if(arquivo.exists())
        {
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo.getAbsolutePath()), "ISO-8859-1"));
                while(br.ready())
                {
                    linha = br.readLine();
                    if(linha.contains(ABREELEITOR))
                    {
                        nome = "";
                        titulo = "";
                        lendoEleitor = true;
                    }
                    if(linha.contains(ABRENOME) && lendoEleitor)
                    {
                        inicio = linha.indexOf(ABRENOME)+ABRENOME.length();
                        fim = linha.indexOf(FECHANOME);
                        nome = linha.substring(inicio, fim);
                        contemNome = true;
                    }
                    if(linha.contains(ABRETITULO) && lendoEleitor)
                    {
                        inicio = linha.indexOf(ABRETITULO)+ABRETITULO.length();
                        fim = linha.indexOf(FECHATITULO);
                        titulo = linha.substring(inicio, fim);
                        contemTitulo = true;
                    }   //adicionar abrevotos e fecha votos quando testar...
                    if(linha.contains(FECHAELEITOR) && lendoEleitor && contemNome && contemTitulo)
                    {
                        lendoEleitor = false;
                        contemNome = false;
                        contemTitulo = false;
                        eleitor = new Eleitor(nome, titulo);
                        eleitores.add(eleitor);
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
        return eleitores;
    }
    
    
    public static ArrayList<Candidato> lerPolitico(String nomeArquivo, boolean lerVotos)
    {
        String nome = "";
        String titulo = "";
        String cargo = "";
        int numero = 0;
        String partido = "";
        String vice = "";
        String partidoVice = "";
        int votos = 0;
        boolean lendoPolitico = false;
        boolean contemNome = false;
        boolean contemTitulo = false;
        boolean contemCargo = false;
        boolean contemNumero = false;
        boolean contemPartido = false;
        boolean contemVice = false;
        boolean contemPartidoVice = false;
        boolean contemVotos = false;
        int inicio = 0;
        int fim = 0;
        ArrayList<Candidato> candidatos = new ArrayList();
        Candidato candidato;
        
        String linha = "";
        
        File arquivo = null;
        
        if(!lerVotos)
            arquivo = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Candidatos\\"+nomeArquivo+".txt");
        else
            arquivo = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Resultado\\"+nomeArquivo+".txt");
        
        if(arquivo.exists())
        {
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo.getAbsolutePath()), "ISO-8859-1"));
                while(br.ready())
                {
                    linha = br.readLine();
                    if(linha.contains(ABREPOLITICO))
                    {
                        nome = "";
                        titulo = "";
                        cargo = "";
                        numero = 0;
                        partido = "";
                        vice = "";
                        partidoVice = "";
                        votos = 0;
                        lendoPolitico = true;
                    }
                    if(linha.contains(ABRENOME) && lendoPolitico)
                    {
                        inicio = linha.indexOf(ABRENOME)+ABRENOME.length();
                        fim = linha.indexOf(FECHANOME);
                        nome = linha.substring(inicio, fim);
                        contemNome = true;
                    }
                    if(linha.contains(ABRETITULO) && lendoPolitico)
                    {
                        inicio = linha.indexOf(ABRETITULO)+ABRETITULO.length();
                        fim = linha.indexOf(FECHATITULO);
                        titulo = linha.substring(inicio, fim);
                        contemTitulo = true;
                    }
                    if(linha.contains(ABRECARGO) && lendoPolitico)
                    {
                        inicio = linha.indexOf(ABRECARGO)+ABRECARGO.length();
                        fim = linha.indexOf(FECHACARGO);
                        cargo = linha.substring(inicio, fim);
                        contemCargo = true;
                    }
                    if(linha.contains(ABRENUMERO) && lendoPolitico)
                    {
                        inicio = linha.indexOf(ABRENUMERO)+ABRENUMERO.length();
                        fim = linha.indexOf(FECHANUMERO);
                        numero = Integer.parseInt(linha.substring(inicio, fim));
                        contemNumero = true;
                    }
                    if(linha.contains(ABREPARTIDO) && lendoPolitico)
                    {
                        inicio = linha.indexOf(ABREPARTIDO)+ABREPARTIDO.length();
                        fim = linha.indexOf(FECHAPARTIDO);
                        partido = linha.substring(inicio, fim);
                        contemPartido = true;
                    }
                    if(linha.contains(ABREVICE) && lendoPolitico)
                    {
                        inicio = linha.indexOf(ABREVICE)+ABREVICE.length();
                        fim = linha.indexOf(FECHAVICE);
                        vice = linha.substring(inicio, fim);
                        contemVice = true;
                    }
                    if(linha.contains(ABREPARTIDOVICE) && lendoPolitico)
                    {
                        inicio = linha.indexOf(ABREPARTIDOVICE)+ABREPARTIDOVICE.length();
                        fim = linha.indexOf(FECHAPARTIDOVICE);
                        partidoVice = linha.substring(inicio, fim);
                        contemPartidoVice = true;
                    }
                    if(linha.contains(ABREVOTOS) && lendoPolitico)
                    {
                        String aux = "";
                        inicio = linha.indexOf(ABREVOTOS)+ABREVOTOS.length();
                        fim = linha.indexOf(FECHAVOTOS);
                        aux = linha.substring(inicio, fim);
                        if(lerVotos && !aux.equals(""))
                            votos = Integer.parseInt(linha.substring(inicio, fim));
                        else
                            votos = 0;
                        contemVotos = true;
                    }                    
                    if(linha.contains(FECHAPOLITICO) && lendoPolitico && contemNome && contemTitulo && contemCargo && contemNumero && contemPartido && contemVice && contemPartidoVice && contemVotos)
                    {
                        if(contemVice)
                            candidato = new Candidato(nome, titulo, cargo, numero, partido, votos, vice, partidoVice);
                        else
                            candidato = new Candidato(nome, titulo, cargo, numero, partido, votos);
                        
                        lendoPolitico = false;
                        contemNome = false;
                        contemCargo = false;
                        contemNumero = false;
                        contemPartido = false;
                        contemVice = false;
                        contemPartidoVice = false;
                        contemVotos = false;
                        candidatos.add(candidato);
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
        return candidatos;
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
