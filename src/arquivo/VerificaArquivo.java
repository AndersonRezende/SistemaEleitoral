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

    private static final String ABREELEICAO = "<eleicao>";
    private static final String FECHAELEICAO = "</eleicao>";
    private static final String ABRECARGOS = "<cargos>";
    private static final String FECHACARGOS = "</cargos>";
    private static final String ABRECARGO = "<cargo>";
    private static final String FECHACARGO = "</cargo>";
    private static final String ABRETITULO = "<titulo>";
    private static final String FECHATITULO = "</titulo>";
    private static final String ABREVICE = "<vice>";
    private static final String FECHAVICE = "</vice>";
    private static final String ABREDIGITOS = "<digitos>";
    private static final String FECHADIGITOS = "</digitos>";
    private static final String ABREVOTOS = "<votos>";
    private static final String FECHAVOTOS = "</votos>";
    private static final String ABREIMAGEM = "<imagem>";
    private static final String FECHAIMAGEM = "</imagem>";

    public static boolean checarArquivoEleicaoPresidencial(String caminhoArquivo) 
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
                boolean abreFechaVotos = false;
                boolean abreFechaImagem = false;

                FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);
                while (br.ready() && !erro)                                     //Enquanto tiver linhas para ler e erro não for verdadeiro
                {
                    linha = br.readLine();
                    //System.out.println(linha);
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
                    //System.out.println(linha);
                    if (linha.contains(ABRECARGOS) && !erro)                    //Se estiver na seção de cargos e não tiver nenhum erro anterior
                    {
                        while (br.ready() && !erro && !abreFechaCargos)         //Fico preso aqui até chegar ao fim do arquivo ou encontrar erro ou não validar o abre e fecha cargos
                        {
                            linha = br.readLine();
                            //System.out.println(linha);
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
                                if (linha.contains(ABREVOTOS) && linha.contains(FECHAVOTOS))
                                    abreFechaVotos = true;
                                if (br.ready())
                                    linha = br.readLine();
                                if (linha.contains(ABREIMAGEM) && linha.contains(FECHAIMAGEM))
                                    abreFechaImagem = true;
                                if (br.ready()) 
                                    linha = br.readLine();                              
                                
                                if (linha.contains(FECHACARGO) && abreFechaTitulo && abreFechaVice && abreFechaDigito && abreFechaVotos && abreFechaImagem) 
                                {
                                    abreCargo = false;
                                    fechaCargo = true;
                                    abreFechaTitulo = false;
                                    abreFechaVice = false;
                                    abreFechaDigito = false;
                                    abreFechaVotos = false;
                                    abreFechaImagem = false;
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
