/*
 * AQUI TEM UM ENUM UTILIZADO NO PANELLOGADO COM AS OPÇÕES DO MENU
 * Enum criado no pacote “enumeracao” no arquivo enum “EnumOpcoesMenu” para exibir as opções do menu.
 */
package enumeracao;

/**
 *
 * @author Anderson
 */
public enum EnumOpcoesMenu 
{
    NPV("NOVO PROCESSO DE VOTAÇÃO"),
    LPV("LIBERAR PRÓXIMA VOTAÇÃO"),
    FPV("FINALIZAR PROCESSO DE VOTAÇÃO"),
    RPV("RESULTADO DO PROCESSO DE VOTAÇÃO"),
    SAIR("SAIR");
    
    public String opcao;
    
    EnumOpcoesMenu(String opcao)
    {   this.opcao = opcao; }
    
    public String getOpcao()
    {   return this.opcao;  }
    
    public String getOpcaoParaMenu()
    {   
        String aux = "" + opcao.charAt(0);
        aux += opcao.substring(1).toLowerCase();
        return aux;
    }
}
