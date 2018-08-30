/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import enumeracao.EnumListaPanels;
import enumeracao.EnumOpcoesMenu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anderson
 */
public class PanelNovoProcessoVotacao extends JPanel
{
    private Container container;
    private CardLayout cardManager;
    private JPanel panelContainerTelaVez;
    private JPanel panelLayoutNovoProcessoVotacao;
    private JLabel labelNovoProcessoVotacaoSuperior;
    private Font fontLabelTextoSuperior;
    
    
        public PanelNovoProcessoVotacao(JPanel panelNovoProcessoVotacao, Container container, CardLayout cardManager, JPanel panelContainerTelaVez)
        {
            this.container = container;
            this.panelContainerTelaVez = panelContainerTelaVez;
            this.cardManager = cardManager;
            
            fontLabelTextoSuperior = new Font(Font.SERIF, Font.BOLD, 25);
            //labelNovoProcessoVotacaoSuperior = new JLabel(EnumOpcoesMenu.NPV.getOpcao());
            labelNovoProcessoVotacaoSuperior = new JLabel(EnumOpcoesMenu.NPV.getOpcao());
            labelNovoProcessoVotacaoSuperior.setFont(fontLabelTextoSuperior);
            labelNovoProcessoVotacaoSuperior.setHorizontalAlignment(JLabel.CENTER);
            
            panelLayoutNovoProcessoVotacao = new JPanel(new BorderLayout());
            
            panelNovoProcessoVotacao.add(labelNovoProcessoVotacaoSuperior, BorderLayout.NORTH);
            panelNovoProcessoVotacao.add(panelLayoutNovoProcessoVotacao, BorderLayout.CENTER);
        }
    
}
