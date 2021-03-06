/*
 * Trabalhar aqui a sobrescrita do metodo de checagem
 * AQUI CONTÉM MÉTODO ESTÁTICO
 * Método estático “checarArquivoEleicao” criado na classe “VerificaArquivo” é 
 * utilizado para verificar se o arquivo de eleição está no formato correto para 
 * que o mesmo possa ser lido e convertido em objeto.
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
public class VerificaArquivo extends Arquivo
{
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
