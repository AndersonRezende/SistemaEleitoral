/*
 * AQUI TEM UM ENUM UTILIZADO NO PANELLOGADO COM AS OPÇÕES DO MENU
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
    SAIR("SAIR");
    
    public String opcao;
    
    EnumOpcoesMenu(String opcao)
    {   this.opcao = opcao; }
    
    public String getOpcao()
    {   return this.opcao;  }
}
