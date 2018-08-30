/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumeracao;

/**
 *
 * @author Anderson
 */
public enum EnumListaPanels 
{
    LOGIN("panelLogin"),
    MESARIO("panelMesario"),
    NOVO_PROC_VOTACAO("panelNovoProcessoVotacao");
    
    public String panel;
    
    EnumListaPanels(String panel)
    {   this.panel = panel; }
    
    public String getOpcao()
    {   return this.panel;  }
}
