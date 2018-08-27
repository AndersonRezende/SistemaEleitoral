/*
 * AQUI CONTÉM JOPTIONPANE
 */
package sistemagrafico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anderson
 */
public class LayoutMaster extends JFrame
{
    private Dimension tamanhoTela;
    private Container container = getContentPane();                            
    private CardLayout cardManager;                                             

    private JPanel panelPrincipal;                                              
    private JPanel panelInferior;
    private JPanel panelContainerTelaVez;                                            
    private JPanel panelLogin;
    private JPanel panelMesario;
    
    private JLabel labelInferior;
    
    public LayoutMaster()
    {
        super("SISTEMA ELEITORAL"); 
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelLogin = new JPanel(new BorderLayout());
        panelMesario = new JPanel();
        
        panelLogin.setName("panelLogin");
        panelMesario.setName("panelMesario");
        
        
        cardManager = new CardLayout();                                         
        panelContainerTelaVez = new JPanel(cardManager);                        
        panelContainerTelaVez.add(panelLogin, panelLogin.getName());
        panelContainerTelaVez.add(panelMesario, panelMesario.getName());
        
        //#########################CONFIGURAR PAINEIS###########################
        configurarPanelInferior();
        configurarTelaLogin();
        configurarTelaMesario();
        //#########################CONFIGURAR PAINEIS###########################

        
        panelPrincipal.add(panelContainerTelaVez, BorderLayout.CENTER);
        
        container.add(panelInferior, BorderLayout.SOUTH);
        container.add(panelPrincipal, BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible( true );
        this.setMinimumSize(new Dimension(tamanhoTela.width/2, tamanhoTela.width/2));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Anderson/Documents/NetBeansProjects/SistemaEleitoral/assets/eleicaoLogo.png"));
    }
    
    
    
    
    
    //#####################MÉTODOS DE CONFIGURAÇÃO##############################
    private void configurarPanelInferior()
    {
        panelInferior = new JPanel();
        panelInferior.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelInferior = new JLabel("SISTEMA ELEITORAL");
        labelInferior.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        panelInferior.add(labelInferior);
    }
    
    private void configurarTelaLogin()
    {
        panelLogin = new PanelLogin(panelLogin, container, cardManager, panelContainerTelaVez);
        /*JLabel labelEleicoes = new JLabel("ELEIÇÕES "+Calendar.getInstance().get(Calendar.YEAR));
        JLabel labelLogin = new JLabel("Login");
        JLabel labelSenha = new JLabel("Senha");
        JLabel labelNomeTela = new JLabel("MESÁRIO - LOGIN");
    
        JTextField textFieldlogin = new JTextField();
        JPasswordField passwordFieldSenha = new JPasswordField();
            
        JButton buttonLogin = new JButton("LOGIN");      
        
        Font fontLabelsInputs = new Font(Font.SERIF, Font.PLAIN, 20);
        JPanel panelLayoutLogin = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        labelEleicoes.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        labelEleicoes.setHorizontalAlignment(JLabel.CENTER);
        labelNomeTela.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        labelLogin.setFont(fontLabelsInputs);
        labelSenha.setFont(fontLabelsInputs);
        textFieldlogin.setFont(fontLabelsInputs);
        passwordFieldSenha.setFont(fontLabelsInputs);
        textFieldlogin.setColumns(15);
        passwordFieldSenha.setColumns(15);
        textFieldlogin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
               if (evt.getKeyCode() == KeyEvent.VK_ENTER)
               {
                String login = (String) textFieldlogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
                if(logar(login, senha))
                    cardManager.show(panelContainerTelaVez, "panelMesario");
                else                                                            //Aqui contém o JOPTIONPANE
                    JOptionPane.showMessageDialog(container, "Usuário inválido, tente novamente!", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE, null);
               }
            }
        });
        passwordFieldSenha.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt)
            {
               if (evt.getKeyCode() == KeyEvent.VK_ENTER)
               {
                String login = (String) textFieldlogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
                if(logar(login, senha))
                    cardManager.show(panelContainerTelaVez, "panelMesario");
                else                                                            //Aqui contém o JOPTIONPANE
                    JOptionPane.showMessageDialog(container, "Usuário inválido, tente novamente!", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE, null);
               } 
            }
        });
        buttonLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String login = (String) textFieldlogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
                if(logar(login, senha))
                    cardManager.show(panelContainerTelaVez, "panelMesario");
                else                                                            //Aqui contém o JOPTIONPANE
                    JOptionPane.showMessageDialog(container, "Usuário inválido, tente novamente!", "Erro ao fazer login", JOptionPane.ERROR_MESSAGE, null);
            }
        });
        
        ImageIcon icon = new ImageIcon("C:/Users/Anderson/Documents/NetBeansProjects/SistemaEleitoral/assets/eleicaoMini.png");
        JLabel labelImagem = new JLabel(icon);
        
        panelLayoutLogin.add(labelImagem);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(labelNomeTela);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(labelLogin);
        panelLayoutLogin.add(textFieldlogin);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(labelSenha);
        panelLayoutLogin.add(passwordFieldSenha);
        panelLayoutLogin.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 100000, 1)));
        panelLayoutLogin.add(buttonLogin);
        
        panelLogin.add(labelEleicoes, BorderLayout.NORTH);
        panelLogin.add(panelLayoutLogin, BorderLayout.CENTER);
        */
    }
    
    private void configurarTelaMesario()
    {
        panelMesario = new PanelLogado(panelMesario, container, cardManager, panelContainerTelaVez);
    }
    
    
    //########################MÉTODOS AUXILIARES################################
    
    //private boolean logar(String nome, String senha)
    {
        /*boolean logado = false;
        if(nome.equals("login") && senha.equals("senha"))
            logado = true;
        return logado;*/
    }  
    
}