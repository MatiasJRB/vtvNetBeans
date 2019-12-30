/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author mjrca
 */
public class conectorDB 
{
    
    private Connection conector;
    private String servidor,basededatos,url,user,pwd;
    private JFrame ventana;
    
    public conectorDB(String serv,String basedatos, String us, String pw, JFrame vent)
    {
        user=us;
        pwd=pw;
        basededatos=basedatos;
        url="jdbc:mysql://"+servidor+"/"+basededatos;  
        ventana=vent;
        conectar();
    }
    
    public void conectar()
    {
        try
        {
            conector= DriverManager.getConnection(url,user,pwd);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ventana), ex.getMessage() + "\n",
						"Error al intentar conectarse a la base de datos "+basededatos, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void desconectar()
    {
        try
        {
            conector.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ventana), ex.getMessage() + "\n",
						"Error al intentar desconectarse de la base de datos "+basededatos, JOptionPane.ERROR_MESSAGE);
        }
    }
}
