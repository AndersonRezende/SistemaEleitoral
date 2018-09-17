/*
 * AQUI CONTÉM O ENUM ENUMOPCOESMENU
 */
package sistemagrafico;

import arquivo.VerificaArquivo;
import enumeracao.EnumListaPanels;
import enumeracao.EnumOpcoesMenu;
import excecoes.FormatoIncorretoException;
import excecoes.JaVotouException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import objetos.Candidato;
import objetos.Eleitor;
import objetos.auxiliares.ProcessoVotacao;

/**
 *
 * @author Anderson
 */
public class PanelLogado extends JPanel implements ListSelectionListener, ComponentListener
{
    private ProcessoVotacao processoVotacao;
    private Font fontListOpcoesMenu;
    private Font fontLabelTextoSuperior;
    private Container container;
    private CardLayout cardManager;
    private JPanel panelContainerTelaVez;
    private JPanel panelLayoutLogado;
    private JList listOpcoesMenu;
    private DefaultListModel listModelOpcoesMenu;
    private JLabel labelMesarioSuperior;
    
    
    PanelLogado(JPanel panelMesario, Container container, CardLayout cardManager, JPanel panelContainerTelaVez, ProcessoVotacao processoVotacao) 
    {
        this.container = container;
        this.panelContainerTelaVez = panelContainerTelaVez;
        this.cardManager = cardManager;
        this.processoVotacao = processoVotacao;
        
        fontListOpcoesMenu = new Font(Font.SERIF, Font.PLAIN, 25);
        fontLabelTextoSuperior = new Font(Font.SERIF, Font.BOLD, 25);
        
        panelLayoutLogado = new JPanel(new BorderLayout());
        
        labelMesarioSuperior = new JLabel("SISTEMA ELEITORAL - MESÁRIO");
        labelMesarioSuperior.setFont(fontLabelTextoSuperior);
        labelMesarioSuperior.setHorizontalAlignment(JLabel.CENTER);
        //labelMesarioSuperior.setHorizontalTextPosition(JLabel.CENTER);
        
        listModelOpcoesMenu = new DefaultListModel();
        for (EnumOpcoesMenu opcao : EnumOpcoesMenu.values())
            listModelOpcoesMenu.addElement(opcao.getOpcao());
        
        listOpcoesMenu = new JList(listModelOpcoesMenu);
        listOpcoesMenu.setFont(fontListOpcoesMenu);
        listOpcoesMenu.addListSelectionListener(this);  
        listOpcoesMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //panelLayoutLogado.add(labelMesarioSuperior, BorderLayout.NORTH);
        panelLayoutLogado.add(listOpcoesMenu, BorderLayout.WEST);
        panelMesario.add(labelMesarioSuperior, BorderLayout.NORTH);
        panelMesario.add(panelLayoutLogado, BorderLayout.WEST);
        panelMesario.addComponentListener(this);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == listOpcoesMenu)
        {
            //Só permite criar um novo processo de votação se a variavel que informa se há um processo de votação correndo estiver falsa
            if(listOpcoesMenu.getSelectedValue().toString().equalsIgnoreCase(EnumOpcoesMenu.NPV.getOpcao()) && !processoVotacao.getProcessoVotacaoIniciado())
            {   //Novo processo de votação
                int opcao = JOptionPane.showConfirmDialog(container, "Deseja iniciar um novo processo de votação?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                if(opcao == JOptionPane.YES_OPTION)
                    cardManager.show(panelContainerTelaVez, EnumListaPanels.NOVO_PROC_VOTACAO.getOpcao());
            }
            else
            {   
                if(listOpcoesMenu.getSelectedValue().toString().equalsIgnoreCase(EnumOpcoesMenu.NPV.getOpcao()) && processoVotacao.getProcessoVotacaoIniciado())
                {
                    JOptionPane.showMessageDialog(container, "Já existe um processo de eleição ocorrendo, finalize o processo atual antes de iniciar um novo!", "Erro", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            
            if(listOpcoesMenu.getSelectedValue().toString().equalsIgnoreCase(EnumOpcoesMenu.FPV.getOpcao()) && processoVotacao.getProcessoVotacaoIniciado())
            {
                int opcao = JOptionPane.showConfirmDialog(container, "Deseja finalizar o processo de votação corrente?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                if(opcao == JOptionPane.YES_OPTION)
                {
                    processoVotacao.finalizarProcessoVotacao();
                    //IMPLEMENTAR FINALIZAÇÃO
                }
            }
            else
            {   
                if(listOpcoesMenu.getSelectedValue().toString().equalsIgnoreCase(EnumOpcoesMenu.FPV.getOpcao()) && !processoVotacao.getProcessoVotacaoIniciado())
                {
                    JOptionPane.showMessageDialog(container, "Não há nenhum processo de votaçao ocorrendo!", "Erro", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            
            if(listOpcoesMenu.getSelectedValue().toString().equalsIgnoreCase(EnumOpcoesMenu.LPV.getOpcao()) && processoVotacao.getProcessoVotacaoIniciado())
            {
                //LER TITULO E CHECAR SE TA NA LISTA
                String titulo = JOptionPane.showInputDialog(container, "Informe o número do título de eleitor:", "Número do título de eleitor", JOptionPane.QUESTION_MESSAGE);
                Eleitor eleitor = processoVotacao.getEleitor(titulo);
                if(eleitor != null)
                {
                    try
                    {
                        if(!eleitor.votou())
                        {   
                            String mensagem = "Os dados do eleitor estão corretos?\n\n"
                                         + "Nome: "+eleitor.getNome() + "\n"
                                         + "Título de eleitor: "+eleitor.getTitulo();
                            if(JOptionPane.showConfirmDialog(container, mensagem, "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null) == JOptionPane.YES_OPTION)
                            {   DialogEleitorMaster de = new DialogEleitorMaster(processoVotacao, eleitor); }
                        }
                        else
                        {   throw new JaVotouException(eleitor.getNome());  }
                    }
                    catch(JaVotouException ex)
                    {   JOptionPane.showMessageDialog(container, ex.getMessage(), ""+ex.getMessage(), JOptionPane.ERROR_MESSAGE, null);   }
                }
                else
                {   JOptionPane.showConfirmDialog(container, "O eleitor não foi localizado, certifique-se que o titulo esteja correto e que o mesmo vote nesta seção", 
                                                             "Erro ao localizar eleitor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null); }
            }
            else
            {
                if(listOpcoesMenu.getSelectedValue().toString().equalsIgnoreCase(EnumOpcoesMenu.LPV.getOpcao()) && !processoVotacao.getProcessoVotacaoIniciado())
                {
                    JOptionPane.showMessageDialog(container, "Não há nenhum processo de votaçao ocorrendo!", "Erro", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            
            if(listOpcoesMenu.getSelectedValue().toString().equalsIgnoreCase(EnumOpcoesMenu.SAIR.getOpcao()))
            {   //Sair
                int opcao = JOptionPane.showConfirmDialog(container, "Deseja fazer logout?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                if(opcao == JOptionPane.YES_OPTION)
                    cardManager.show(panelContainerTelaVez, EnumListaPanels.LOGIN.getOpcao());
            }
            
        }
        //listOpcoesMenu.setSelectedIndex(0);
    }
    
    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) 
    {
        listOpcoesMenu.setSelectedIndex(5);
    }

    @Override
    public void componentHidden(ComponentEvent e) 
    {
        listOpcoesMenu.setSelectedIndex(5);
    }
}
