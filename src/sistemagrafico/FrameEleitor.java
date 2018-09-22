/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagrafico;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Anderson
 */
public class FrameEleitor extends JFrame implements ComponentListener
{
    private Container container = getContentPane();
    private Dimension tamanhoTela;
    
    
    public FrameEleitor(Container container)
    {
        super("ELEITOR");
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.container.add(new JLabel("Teste"));
        
        
        this.setAlwaysOnTop(true);
        this.setFocusableWindowState(true);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(tamanhoTela);
        this.setVisible( true );
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(""+new File("").getAbsoluteFile()+"/assets/eleicaoLogo.png"));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addComponentListener(this);
        
    }

    @Override
    public void componentResized(ComponentEvent e){}

    @Override
    public void componentMoved(ComponentEvent e) 
    {
        this.setLocationRelativeTo(null);
    }

    @Override
    public void componentShown(ComponentEvent e){}

    @Override
    public void componentHidden(ComponentEvent e) {}
    
}
