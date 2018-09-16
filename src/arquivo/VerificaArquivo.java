/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Anderson
 */
public class VerificaArquivo 
{

    public static final String ABREELEICAO = "<eleicao>";
    public static final String FECHAELEICAO = "</eleicao>";
    public static final String ABRECARGOS = "<cargos>";
    public static final String FECHACARGOS = "</cargos>";
    public static final String ABRECARGO = "<cargo>";
    public static final String FECHACARGO = "</cargo>";
    public static final String ABRETITULO = "<titulo>";
    public static final String FECHATITULO = "</titulo>";
    public static final String ABREVICE = "<vice>";
    public static final String FECHAVICE = "</vice>";
    public static final String ABREDIGITOS = "<digitos>";
    public static final String FECHADIGITOS = "</digitos>";
    public static final String ABREVOTOS = "<votos>";
    public static final String FECHAVOTOS = "</votos>";
    public static final String ABREELEITOS = "<eleitos>";
    public static final String FECHAELEITOS = "</eleitos>";
    
    public static final String ABREMESARIO = "<mesario>";
    public static final String FECHAMESARIO = "</mesario>";
    public static final String ABRELOGIN = "<login>";
    public static final String FECHALOGIN = "</login>";
    public static final String ABRESENHA = "<senha>";
    public static final String FECHASENHA = "</senha>";
    
    public static final String ABREELEITOR = "<eleitor>";
    public static final String FECHAELEITOR = "</eleitor>";
    public static final String ABRENOME = "<nome>";
    public static final String FECHANOME = "</nome>";

    public static final String ABRENUMERO = "<numero>";
    public static final String FECHANUMERO = "</numero>";
    public static final String ABREPARTIDO = "<partido>";
    public static final String FECHAPARTIDO = "</partido>";
    public static final String ABREPARTIDOVICE = "<partidovice>";
    public static final String FECHAPARTIDOVICE = "</partidovice>";
    public static final String ABREPOLITICO = "<politico>";
    public static final String FECHAPOLITICO = "</politico>";



    
    public static boolean checarArquivoCandidatos(String nomeArquivo) 
    {
        boolean valido = false;
        File arquivo = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Candidatos\\"+nomeArquivo+".txt");
        valido = arquivo.exists();
        
        return valido;
    }
    
    
    public static boolean checarArquivoEleitor(String nomeArquivo) 
    {
        boolean valido = false;
        File arquivo = new File(""+new File("").getAbsoluteFile()+"\\Arquivos\\Eleitores\\"+nomeArquivo+".txt");
        valido = arquivo.exists();
        
        return valido;
    }
            
    
    public static boolean checarArquivoEleicao(String caminhoArquivo) 
    {
        boolean valido = false;

        File arquivo = new File(caminhoArquivo);
        if (arquivo.exists()) 
        {
            try 
            {
                String linha = "";
                boolean erro = false;

                boolean abreFechaEleicao = false;
                boolean abreFechaCargos = false;
                boolean abreCargo = false;
                boolean fechaCargo = !abreCargo;
                boolean abreFechaTitulo = false;
                boolean abreFechaVice = false;
                boolean abreFechaDigito = false;
                boolean abreFechaEleitos = false;

                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                while (br.ready() && !erro)                                     //Enquanto tiver linhas para ler e erro não for verdadeiro
                {
                    linha = br.readLine();
                    if (checarLinhaEleicao(linha) && !abreFechaEleicao)         //Verificação se é a primeira vez que esta checagem é realizada
                    {
                        if (abreFechaEleicao == false)                          //Se for falso, é porque é a primeira vez aqui e o arquivo pode estar certo
                        {    
                            abreFechaEleicao = true;
                        }
                        else                                                    //Se for a segunda vez, é porque o arquivo está incorreto, pois esta parte só pode ocorrer uma vez
                        {
                            valido = false;
                            erro = true;
                        }
                    }
                    
                    linha = br.readLine();
                    if (linha.contains(ABRECARGOS) && !erro)                    //Se estiver na seção de cargos e não tiver nenhum erro anterior
                    {
                        while (br.ready() && !erro && !abreFechaCargos)         //Fico preso aqui até chegar ao fim do arquivo ou encontrar erro ou não validar o abre e fecha cargos
                        {
                            linha = br.readLine();
                            if (linha.contains(ABRECARGO) && fechaCargo)        //Procurar a tag de inicialização do cargo
                            {
                                fechaCargo = false;
                                abreCargo = true;
                                linha = br.readLine();
                                
                                if (linha.contains(ABRETITULO) && linha.contains(FECHATITULO))
                                    abreFechaTitulo = true;
                                if (br.ready()) 
                                    linha = br.readLine();
                                if (linha.contains(ABREVICE) && linha.contains(FECHAVICE)) 
                                    abreFechaVice = true;
                                if (br.ready())
                                    linha = br.readLine();
                                if (linha.contains(ABREDIGITOS) && linha.contains(FECHADIGITOS))
                                    abreFechaDigito = true;
                                if (br.ready())
                                    linha = br.readLine();
                                if (linha.contains(ABREELEITOS) && linha.contains(FECHAELEITOS))
                                    abreFechaEleitos = true;
                                if (br.ready())
                                    linha = br.readLine();
                                
                                if (linha.contains(FECHACARGO) && abreFechaTitulo && abreFechaVice && abreFechaDigito && abreFechaEleitos) 
                                {
                                    abreCargo = false;
                                    fechaCargo = true;
                                    abreFechaTitulo = false;
                                    abreFechaVice = false;
                                    abreFechaDigito = false;
                                    abreFechaEleitos = false;
                                } 
                                else
                                {
                                    valido = false;
                                    erro = true;
                                }
                            }
                            else                                                //Se não for abre cargo...
                            {
                                if (linha.contains(FECHACARGOS) && fechaCargo)  //Se encontrar um fecha cargo e se já tiver um cargo aberto, a tag está correga
                                {
                                    abreFechaCargos = true;
                                    valido = true;
                                }
                                else                                            //Senão encontrar um fecha cargo após as checagens anterior ou se não tiver aberto um cargo antes, é incorreto
                                {
                                    valido = false;
                                    erro = true;
                                }
                            }
                        }
                    } 
                    else                                                        //Se logo após checar o titulo de elição não tiver uma lista de cargos, então o arquivo está incorreto
                    {
                        valido = false;
                        erro = true;
                    }
                }
                br.close();
                fr.close();
            } 
            catch (FileNotFoundException ex) 
            {
                System.err.println(ex);
                valido = false;
            } 
            catch (IOException ex) 
            {
                System.err.println(ex);
                valido = false;
            }
        } 
        else 
        {
            System.err.println("Arquivo não encontrado!");
            valido = false;
        }

        return valido;
    }

    private static boolean checarLinhaEleicao(String linha) 
    {
        boolean valido = false;
        String nomePresente = "";
        int inicio = 0;
        int fim = 0;

        if (linha.contains(ABREELEICAO) && linha.contains(FECHAELEICAO)) 
        {
            inicio = linha.indexOf(ABREELEICAO);
            fim = linha.indexOf(FECHAELEICAO);
            nomePresente = linha.substring(inicio, fim);
            if (!nomePresente.equals("")) 
                valido = true;
        }

        return valido;
    }
}
