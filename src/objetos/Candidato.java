/*
 * Aqui é implementado a Interface VotacaoCandidato, possui composição e agregação e o Polimorfismo de Sobrecarga
 * AQUI CONTÉM POLIMORFISMO DE SOBRECARGA (CONSTRUTOR)
 * AQUI É IMPLEMENTADO UMA INTERFACE
 * Polimorfismo de sobrecarga criado no construtor da classe “Candidato”, pois alguns 
 * candidatos têm vice e outros não, logo um construtor possui os atributos do vice e o outro não.
 * 
 * A classe “Candidato” implementa a interface “VotacaoCandidato” que fornece a 
 * assinatura dos métodos “receberVoto” e “getVotos” que é utilizado para o candidato 
 * receber um voto e checar a quantidade de votos do mesmo, respectivamente.
 */
package objetos;

import interfaces.VotacaoCandidato;

/**
 *
 * @author Anderson
 */
public class Candidato implements VotacaoCandidato
{
    private int numero;
    private String partido;
    private int votos;
    private String cargo;
    private Eleitor eleitor; 
    private Vice vice;

    
    public Candidato(String nome, String titulo, String cargo, int numero, String partido, int votos)
    {
        this.eleitor = new Eleitor(nome,titulo);
        this.cargo = cargo;
        this.numero = numero;
        this.partido = partido;
        this.votos = votos;
    }
    
    public Candidato(String nome, String titulo, String cargo, int numero, String partido, int votos, String vice, String partidoVice)
    {
        this(nome, titulo, cargo, numero, partido, votos);
        this.vice = new Vice(vice, partidoVice);
    }
    
    public int getNumero() 
    {   return numero;  }

    public void setNumero(int numero) 
    {   this.numero = numero;   }

    public String getPartido() 
    {   return partido; }

    public void setPartido(String partido) 
    {   this.partido = partido; }

    public String getCargo() 
    {   return cargo;   }

    public void setCargo(String cargo) 
    {   this.cargo = cargo; }
    
    public Vice getVice()
    {   
        Vice vice = null;
        if(this.temVice())
            vice = this.vice;
        return vice;
    }
    
    public Eleitor getEleitor()
    {   return eleitor; }

    @Override
    public int getVotos() 
    {   return votos;  }

    @Override
    public boolean receberVoto() 
    {   
        boolean votou = false;
        int aux = votos;
        this.votos++;
        votou = votos > aux;
        return votou;
    }
    
    public boolean temVice()
    {
        boolean temVice = false;
        if(vice != null)
            if(!vice.getNome().equals(""))
                temVice = true;
        return temVice;
    }
    
    public String getNomeVice()
    {   return vice.getNome();  }
    
    
    public String getPartidoVice()
    {   return vice.getPartido();  }
    
    public String getDadosVice()
    {   return vice.getDados(); }
}