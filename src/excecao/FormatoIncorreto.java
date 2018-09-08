/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excecao;

/**
 *
 * @author Administrador
 */
public class FormatoIncorreto extends Exception{
    
    public String getMessage(){
        return "Formato do Arquivo Incorreto!";
    }
}
