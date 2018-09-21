/*
 * Classe abstrata aqui
 * AQUI CONTÉM CONSTANTES
 * AQUI CONTÉM UMA CLASSE ABSTRATA
 * Constante “ABRECARGO” na classe “Arquivo” criada para identificar a tag de marcação 
 * que informa ao programa em qual parte do arquivo contém o cargo do candidato. 
 * A constante em questão é utilizada na classe “LeituraArquivo”.
 * 
 * Classe abstrata “Arquivo” que contém os métodos e constantes necessárias para 
 * realizar o trabalho das classes manipuladoras de arquivos.
 */
package arquivo;

/**
 *
 * @author Anderson
 */
public abstract class Arquivo 
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
}
