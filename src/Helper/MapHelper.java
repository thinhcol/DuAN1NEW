/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class MapHelper extends JPanel{
    private Stage stage;  
    private WebView browser;  
    private JFXPanel jfxPanel;  
    private JButton swingButton;  
    private WebEngine webEngine;  
  
    public MapHelper(){  
        initComponents();  
         createScene(); 
           add(jfxPanel);  
    }  
  
    public static void main(String ...args){  
        SwingUtilities.invokeLater(new Runnable() {  
            @Override
            public void run() {  
                final JFrame frame = new JFrame();  
                 
                frame.getContentPane().add(new MapHelper());  
          
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                frame.setVisible(true);  
            }  
        });     
    }  
     
    private void initComponents(){  
        jfxPanel = new JFXPanel();  
        setLayout(new CardLayout());  
        swingButton = new JButton();  
        swingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        webEngine.reload();
                    }
                });
            }
        });   
    }     

    private void createScene() {  
        PlatformImpl.startup(new Runnable() {  
            @Override
            public void run() {  
           try {
                    Group root = new Group();
                    Scene scene = new Scene(root);
                    WebView browser = new WebView();
                    WebEngine webEngine = browser.getEngine();
                    File file = new File("Help/map.html");
                    URL url = file.toURI().toURL();
                    System.out.println("Local URL: " + url.toString());
                    webEngine.load(url.toString());
                    root.getChildren().add(browser);
                     ObservableList<Node> children = root.getChildren();
                    jfxPanel.setScene(scene);
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }  
        });  
    }
}
