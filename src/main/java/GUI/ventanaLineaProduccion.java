/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;

import org.jfree.chart.plot.PlotOrientation;*/




/*
 *
 * @author mjrca
 */
public class ventanaLineaProduccion extends javax.swing.JFrame {
    
    private DefaultTableModel   modelo_tabla_sistema_electrico_banco_suspension,
                                modelo_tabla_sistema_mecanico_banco_suspension,
                                modelo_tabla_señal_sistema_electrico_frenometro,
                                modelo_tabla_control_sistema_electrico_frenometro,
                                modelo_tabla_potencia_sistema_electrico_frenometro,
                                modelo_tabla_sistema_hidraulico_frenometro,
                                modelo_tabla_sistema_mecanico_frenometro,
                                modelo_tabla_prevision_mensual;
    
    private String maquinaSeleccionada;
    private ventanaPrincipal ventanaPrinci;
    public ventanaInformacionMaquina ventanaInformacionMaq;
    
    private int tipoPrevision;
    
    private boolean infoApretada,
                    sistema_electrico_seleccionado,
                    sistema_hidraulico_seleccionado,
                    sistema_mecanico_seleccionado;
    
    private String  fila17,
                    fila18,
                    fila9,
                    dir;
    
    private Impresor impresor;
    /**
     * Creates new form ventanaLineaProduccion
     */
    public ventanaLineaProduccion() {
        initComponents();
        this.setLocationRelativeTo(null);
        // esta linea es para quitarle la barra a la ventana interna
        /*((javax.swing.plaf.basic.BasicInternalFrameUI) 
            jInternalFrame1.getUI()).setNorthPane(null);
        BasicInternalFrameTitlePane titlePane =  (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) jInternalFrame1.getUI()).getNorthPane();
        jInternalFrame1.remove(titlePane);
        */    
        caracteristicas_jtable();
        inicializarVentanas();        
        dir= new String("C:\\\\BullMetall");        
    }
    
    
    public ventanaLineaProduccion(ventanaPrincipal ventanaPri){
        cargarTablas();
        initComponents();
        this.setLocationRelativeTo(null);
        ventanaPrinci=ventanaPri;        
        caracteristicas_jtable();
        inicializarVentanas();
        infoApretada=false;
        deshabilitarInnecesarios();
        sistema_electrico_seleccionado=false;
        sistema_hidraulico_seleccionado=false;
        sistema_mecanico_seleccionado=false;        
        tipoPrevision=0;
        impresor= new Impresor();
    }
    
    public void previsionesMensuales()
    {
        jButton5MouseClicked(null);
        jButton14MouseClicked(null);
    }
    
    private void deshabilitarInnecesarios()
    {
        jTextField1.setVisible(false);
        jScrollPane5.setVisible(false);
        jPanel15.setVisible(false);
    }
    
    private void habilitarSistemaElectrico()
    {
        sistema_electrico_seleccionado=true;
        jLabel3.setText("SISTEMA ELÉCTRICO");
        jButton7.setText("SEÑAL");
        jButton6.setText("CONTROL");
        jButton9.setText("POTENCIA");
        jPanel15.setVisible(true);
        jScrollPane5.setVisible(true);
        jTextField1.setVisible(true);
    }
    
    private void habilitarSistemaMecanico()
    {
        sistema_mecanico_seleccionado=true;
        jScrollPane5.setVisible(true);
        jTextField1.setVisible(true);
        manejador_tabla_sist_mecanicos();
    }
    
    private void deshabilitarSistemaElectrico()
    {
        sistema_electrico_seleccionado=false;
        jLabel3.setText("LÍNEA DE PRODUCCIÓN");
        jButton7.setText("SIST. ELÉCTRICO");
        jButton6.setText("SIST. MECÁNICO");
        jButton9.setText("SIST. HIDRÁULICO");
        jPanel15.setVisible(false);
        jScrollPane5.setVisible(false);
        jTextField1.setVisible(false);
    }
    
    
    private void inicializarVentanas()
    {
        ventanaInformacionMaq= new ventanaInformacionMaquina(this);
        ventanaInformacionMaq.setVisible(false);
    }
    
    private void cambiarContexto()
    {
        infoApretada=false;
        toggleButtons();
    }
    
    
    public void cargarTablas()
    {
        cargarTablaSeñalSistemaElectricoFrenometro();        
        cargarTablaControlSistemaElectricoFrenometro();
        cargarTablaPotenciaSistemaElectricoFrenometro();
        cargarTablaSistemaHidraulicoFrenometro();
        cargarTablaSistemaMecanicoFrenometro();
        cargarTablaSitemaElectricoBancoSuspension();   
        cargarTablaSistemaMecanicoBancoSuspension();
    }
    
    public String dateToString(Date date)
    {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();    
        String toret = simpleDateFormat.format(today);
        return toret;
    }
    
    private void abrirVentanaInformacionMaquina (String nombre, MouseEvent evt)
    {
        // nombre seria la clave de la maquina
        ventanaInformacionMaq.setVisible(true);
        ventanaInformacionMaq.cargarMaquina(nombre);
        cambiarContexto();
        
    }    
    
    public void caracteristicas_jtable()
    {
        jTable1.setRowHeight(70);
        jTable1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) 
            {                
                    JTable table = (JTable) evt.getSource();
                    int row = table.rowAtPoint(evt.getPoint());
                    TableModel model = table.getModel();
                    maquinaSeleccionada= new String(model.getValueAt(row, 0).toString());
                    System.out.println("La maquina seleccionada es "+ maquinaSeleccionada);
                    
            }
        });;
        jTable2.setRowHeight(70);
        jTable2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) 
            {                
                    JTable table = (JTable) evt.getSource();
                    int row = table.rowAtPoint(evt.getPoint());
                    TableModel model = table.getModel();
                    maquinaSeleccionada= new String(model.getValueAt(row, 0).toString());
                    System.out.println("La maquina seleccionada es "+ maquinaSeleccionada);
                    
            }
        });;
             
    }
    
    private void cargarTablaSistemaHidraulicoFrenometro()
    {
        modelo_tabla_sistema_hidraulico_frenometro = new DefaultTableModel();
        modelo_tabla_sistema_hidraulico_frenometro.addColumn("Modelo");
        modelo_tabla_sistema_hidraulico_frenometro.addColumn("Año");
        modelo_tabla_sistema_hidraulico_frenometro.addColumn("Fecha adquisición");
        modelo_tabla_sistema_hidraulico_frenometro.addColumn("Costo USD");
        modelo_tabla_sistema_hidraulico_frenometro.addColumn("Campo A");
        modelo_tabla_sistema_hidraulico_frenometro.addColumn("Campo B");
        
        Object [] fila1=new Object[6];
        fila1[0]= (String) "maquina hidraulica";
        fila1[1]= (int)1999;
        fila1[2]= (String) dateToString(new Date());
        fila1[3]= (float)750.0f;
        fila1[4]= (String) "Dato 1";
        fila1[5]= (String) "Dato 2";
        Object [] fila2= new String[6];
        fila2[0]= (String)"JDK-505";
        fila2[1]= (String)"2009";
        fila2[2]= dateToString(new Date());
        fila2[3]= (String)"8195.00";
        fila2[4]= (String)"Dato 1";
        fila2[5]= (String)"Dato 2";

        Object [] fila3= new String[6];
        fila3[0]= (String) "RPC-7";
        fila3[1]= (String) "2000";
        fila3[2]= (String) dateToString(new Date());
        fila3[3]= (String) "105,000.00";
        fila3[4]= (String) "Dato 1";
        fila3[5]= (String) "Dato 2";

        modelo_tabla_sistema_hidraulico_frenometro.addRow(fila1);
        modelo_tabla_sistema_hidraulico_frenometro.addRow(fila2);
        modelo_tabla_sistema_hidraulico_frenometro.addRow(fila3);
    }
    
    private void cargarTablaSistemaMecanicoFrenometro()
    {
        modelo_tabla_sistema_mecanico_frenometro = new DefaultTableModel();
        modelo_tabla_sistema_mecanico_frenometro.addColumn("Modelo");
        modelo_tabla_sistema_mecanico_frenometro.addColumn("Año");
        modelo_tabla_sistema_mecanico_frenometro.addColumn("Fecha adquisición");
        modelo_tabla_sistema_mecanico_frenometro.addColumn("Costo USD");
        modelo_tabla_sistema_mecanico_frenometro.addColumn("Campo A");
        modelo_tabla_sistema_mecanico_frenometro.addColumn("Campo B");
        
        Object [] fila1=new Object[6];
        fila1[0]= (String) "maquina mecanica";
        fila1[1]= (int)1999;
        fila1[2]= (String) dateToString(new Date());
        fila1[3]= (float)750.0f;
        fila1[4]= (String) "Dato 1";
        fila1[5]= (String) "Dato 2";
        Object [] fila2= new String[6];
        fila2[0]= (String)"JDK-505";
        fila2[1]= (String)"2009";
        fila2[2]= dateToString(new Date());
        fila2[3]= (String)"8195.00";
        fila2[4]= (String)"Dato 1";
        fila2[5]= (String)"Dato 2";

        Object [] fila3= new String[6];
        fila3[0]= (String) "RPC-7";
        fila3[1]= (String) "2000";
        fila3[2]= (String) dateToString(new Date());
        fila3[3]= (String) "105,000.00";
        fila3[4]= (String) "Dato 1";
        fila3[5]= (String) "Dato 2";

        modelo_tabla_sistema_mecanico_frenometro.addRow(fila1);
        modelo_tabla_sistema_mecanico_frenometro.addRow(fila2);
        modelo_tabla_sistema_mecanico_frenometro.addRow(fila3);
    }
    
    private void cargarTablaPotenciaSistemaElectricoFrenometro()
    {
        modelo_tabla_potencia_sistema_electrico_frenometro = new DefaultTableModel();
        modelo_tabla_potencia_sistema_electrico_frenometro.addColumn("Modelo");
        modelo_tabla_potencia_sistema_electrico_frenometro.addColumn("Año");
        modelo_tabla_potencia_sistema_electrico_frenometro.addColumn("Fecha adquisición");
        modelo_tabla_potencia_sistema_electrico_frenometro.addColumn("Costo USD");
        modelo_tabla_potencia_sistema_electrico_frenometro.addColumn("Campo A");
        modelo_tabla_potencia_sistema_electrico_frenometro.addColumn("Campo B");
        
        Object [] fila1=new Object[6];
        fila1[0]= (String) "Potenciador";
        fila1[1]= (int)1999;
        fila1[2]= (String) dateToString(new Date());
        fila1[3]= (float)750.0f;
        fila1[4]= (String) "Dato 1";
        fila1[5]= (String) "Dato 2";
        Object [] fila2= new String[6];
        fila2[0]= (String)"JDK-505";
        fila2[1]= (String)"2009";
        fila2[2]= dateToString(new Date());
        fila2[3]= (String)"8195.00";
        fila2[4]= (String)"Dato 1";
        fila2[5]= (String)"Dato 2";

        Object [] fila3= new String[6];
        fila3[0]= (String) "RPC-7";
        fila3[1]= (String) "2000";
        fila3[2]= (String) dateToString(new Date());
        fila3[3]= (String) "105,000.00";
        fila3[4]= (String) "Dato 1";
        fila3[5]= (String) "Dato 2";


        //fila1= {,1997,new Date(), 7500};
        modelo_tabla_potencia_sistema_electrico_frenometro.addRow(fila1);
        modelo_tabla_potencia_sistema_electrico_frenometro.addRow(fila2);
        modelo_tabla_potencia_sistema_electrico_frenometro.addRow(fila3);
    }            
           
    
    private void cargarTablaControlSistemaElectricoFrenometro()
    {
        modelo_tabla_control_sistema_electrico_frenometro = new DefaultTableModel();
        modelo_tabla_control_sistema_electrico_frenometro.addColumn("Modelo");
        modelo_tabla_control_sistema_electrico_frenometro.addColumn("Año");
        modelo_tabla_control_sistema_electrico_frenometro.addColumn("Fecha adquisición");
        modelo_tabla_control_sistema_electrico_frenometro.addColumn("Costo USD");
        modelo_tabla_control_sistema_electrico_frenometro.addColumn("Campo A");
        modelo_tabla_control_sistema_electrico_frenometro.addColumn("Campo B");
        
        Object [] fila1=new Object[6];
        fila1[0]= (String) "Controlador";
        fila1[1]= (int)1999;
        fila1[2]= (String) dateToString(new Date());
        fila1[3]= (float)750.0f;
        fila1[4]= (String) "Dato 1";
        fila1[5]= (String) "Dato 2";
        Object [] fila2= new String[6];
        fila2[0]= (String)"JDK-505";
        fila2[1]= (String)"2009";
        fila2[2]= dateToString(new Date());
        fila2[3]= (String)"8195.00";
        fila2[4]= (String)"Dato 1";
        fila2[5]= (String)"Dato 2";

        Object [] fila3= new String[6];
        fila3[0]= (String) "RPC-7";
        fila3[1]= (String) "2000";
        fila3[2]= (String) dateToString(new Date());
        fila3[3]= (String) "105,000.00";
        fila3[4]= (String) "Dato 1";
        fila3[5]= (String) "Dato 2";


        //fila1= {,1997,new Date(), 7500};
        modelo_tabla_control_sistema_electrico_frenometro.addRow(fila1);
        modelo_tabla_control_sistema_electrico_frenometro.addRow(fila2);
        modelo_tabla_control_sistema_electrico_frenometro.addRow(fila3);
    
    }
    
    
    private void cargarTablaSeñalSistemaElectricoFrenometro()
    {
        modelo_tabla_señal_sistema_electrico_frenometro = new DefaultTableModel();
        modelo_tabla_señal_sistema_electrico_frenometro.addColumn("Modelo");
        modelo_tabla_señal_sistema_electrico_frenometro.addColumn("Año");
        modelo_tabla_señal_sistema_electrico_frenometro.addColumn("Fecha adquisición");
        modelo_tabla_señal_sistema_electrico_frenometro.addColumn("Costo USD");
        modelo_tabla_señal_sistema_electrico_frenometro.addColumn("Campo A");
        modelo_tabla_señal_sistema_electrico_frenometro.addColumn("Campo B");
        
        Object [] fila1=new Object[6];
        fila1[0]= (String) "Señalador";
        fila1[1]= (int)1999;
        fila1[2]= (String) dateToString(new Date());
        fila1[3]= (float)750.0f;
        fila1[4]= (String) "Dato 1";
        fila1[5]= (String) "Dato 2";
        Object [] fila2= new String[6];
        fila2[0]= (String)"JDK-505";
        fila2[1]= (String)"2009";
        fila2[2]= dateToString(new Date());
        fila2[3]= (String)"8195.00";
        fila2[4]= (String)"Dato 1";
        fila2[5]= (String)"Dato 2";

        Object [] fila3= new String[6];
        fila3[0]= (String) "RPC-7";
        fila3[1]= (String) "2000";
        fila3[2]= (String) dateToString(new Date());
        fila3[3]= (String) "105,000.00";
        fila3[4]= (String) "Dato 1";
        fila3[5]= (String) "Dato 2";


        //fila1= {,1997,new Date(), 7500};
        modelo_tabla_señal_sistema_electrico_frenometro.addRow(fila1);
        modelo_tabla_señal_sistema_electrico_frenometro.addRow(fila2);
        modelo_tabla_señal_sistema_electrico_frenometro.addRow(fila3);
    }
    
    private void cargarTablaSistemaMecanicoBancoSuspension()
    {
        modelo_tabla_sistema_mecanico_banco_suspension = new DefaultTableModel();
        modelo_tabla_sistema_mecanico_banco_suspension.addColumn("Modelo");
        modelo_tabla_sistema_mecanico_banco_suspension.addColumn("Año");
        modelo_tabla_sistema_mecanico_banco_suspension.addColumn("Fecha adquisición");
        modelo_tabla_sistema_mecanico_banco_suspension.addColumn("Costo USD");
        modelo_tabla_sistema_mecanico_banco_suspension.addColumn("Origen");
        modelo_tabla_sistema_mecanico_banco_suspension.addColumn("Campo B");
        
        Object [] fila1=new Object[6];
        fila1[0]= (String) "Y2-180L-4";
        fila1[1]= (int)2010;
        fila1[2]= (String) "10/2011";
        fila1[3]= (float)310.0f;
        fila1[4]= (String) "Xiamen, China";
        fila1[5]= (String) "Dato 2";
        
        Object [] fila2= new String[6];
        fila2[0]= (String)"JDK-505";
        fila2[1]= (String)"2009";
        fila2[2]= dateToString(new Date());
        fila2[3]= (String)"8195.00";
        fila2[4]= (String)"Dato 1";
        fila2[5]= (String)"Dato 2";

        Object [] fila3= new String[6];
        fila3[0]= (String) "RPC-7";
        fila3[1]= (String) "2000";
        fila3[2]= (String) dateToString(new Date());
        fila3[3]= (String) "105,000.00";
        fila3[4]= (String) "Dato 1";
        fila3[5]= (String) "Dato 2";


        //fila1= {,1997,new Date(), 7500};
        modelo_tabla_sistema_mecanico_banco_suspension.addRow(fila1);
        modelo_tabla_sistema_mecanico_banco_suspension.addRow(fila2);
        modelo_tabla_sistema_mecanico_banco_suspension.addRow(fila3);
    }
    
    private void cargarTablaSitemaElectricoBancoSuspension(){
       modelo_tabla_sistema_electrico_banco_suspension= new DefaultTableModel();
       modelo_tabla_sistema_electrico_banco_suspension.addColumn("Modelo");
       modelo_tabla_sistema_electrico_banco_suspension.addColumn("Año");
       modelo_tabla_sistema_electrico_banco_suspension.addColumn("Fecha adquisición");
       modelo_tabla_sistema_electrico_banco_suspension.addColumn("Costo USD");
       modelo_tabla_sistema_electrico_banco_suspension.addColumn("Campo A");
       modelo_tabla_sistema_electrico_banco_suspension.addColumn("Campo B");
       // añado las filas
       Object [] fila1=new Object[6];
       fila1[0]= (String) "ACZ-11";
       fila1[1]= (int)1997;
       fila1[2]= (String) dateToString(new Date());
       fila1[3]= (float)7500.0f;
       fila1[4]= (String) "Dato 1";
       fila1[5]= (String) "Dato 2";
       Object [] fila2= new String[6];
       fila2[0]= (String)"SP3-505";
       fila2[1]= (String)"2001";
       fila2[2]= dateToString(new Date());
       fila2[3]= (String)"895.00";
       fila2[4]= (String)"Dato 1";
       fila2[5]= (String)"Dato 2";
       
       Object [] fila3= new String[6];
       fila3[0]= (String) "MDQ-777";
       fila3[1]= (String) "2007";
       fila3[2]= (String) dateToString(new Date());
       fila3[3]= (String) "777.00";
       fila3[4]= (String) "Dato 1";
       fila3[5]= (String) "Dato 2";
       
       
       //fila1= {,1997,new Date(), 7500};
       modelo_tabla_sistema_electrico_banco_suspension.addRow(fila1);
       modelo_tabla_sistema_electrico_banco_suspension.addRow(fila2);
       modelo_tabla_sistema_electrico_banco_suspension.addRow(fila3);
       
    }
    
    public void manejador_tabla_señal_sistema_electrico_frenometro()
    {
        jTable1.setModel(modelo_tabla_señal_sistema_electrico_frenometro);
        caracteristicas_jtable();
    }
    
    public void manejador_tabla_sist_electricos()
    {     
        jTable1.setModel(modelo_tabla_sistema_electrico_banco_suspension);
        caracteristicas_jtable();
    }
    
    public void  manejador_tabla_sist_mecanicos()
    {
        jTable1.setModel(modelo_tabla_sistema_mecanico_banco_suspension);
        caracteristicas_jtable();
    }
    
    public void manejador_tabla_control_sistema_electrico_frenometro()
    {
        jTable1.setModel(modelo_tabla_control_sistema_electrico_frenometro);
        caracteristicas_jtable();
    }
    
    public void manejador_tabla_potencia_sistema_electrico_frenometro()
    {        
        jTable1.setModel(modelo_tabla_potencia_sistema_electrico_frenometro);
        caracteristicas_jtable();
    }
    
    public void manejador_tabla_sist_hidraulico()
    {
        
    }
    
     public void toggleButtons(){
        jButton1.setEnabled(!jButton1.isEnabled());
        jButton2.setEnabled(!jButton2.isEnabled());
        jButton3.setEnabled(!jButton3.isEnabled());
        jButton4.setEnabled(!jButton4.isEnabled());
        jButton5.setEnabled(!jButton5.isEnabled());
    }
    
    //////// previsiones
     
     
     public void habilitarPrevisionSemanal()
     {
         tipoPrevision=0;
         jLabel5.setText("PROVISIÓN SEMANAL");
         cargarTablaPrevisionSemanal();
         jLabel4.setText("TOTAL: 17 USD");
     }
     
    public void cargarTablaPrevisionSemanal()
    {
        System.out.println("Cargo las previsiones semanales");
        
    }
     
    public void habilitarPrevisionMensual()
    {
        tipoPrevision=1;
        jLabel5.setText("PROVISIÓN MENSUAL");
        cargarTablaPrevisionMensual();
        jTable2.setModel(modelo_tabla_prevision_mensual);
        crearFilasExcelPrevisionMensual();
        jLabel4.setText("TOTAL: 9.172,00 USD");     
    }
    
    private void crearFilasExcelPrevisionMensual()
    {
        
    }
    
    public void cargarTablaPrevisionMensual()
    {
        modelo_tabla_prevision_mensual = new DefaultTableModel();
        modelo_tabla_prevision_mensual.addColumn("Modelo");
        modelo_tabla_prevision_mensual.addColumn("Año");
        modelo_tabla_prevision_mensual.addColumn("Fecha adquisición");
        modelo_tabla_prevision_mensual.addColumn("Costo USD");
        modelo_tabla_prevision_mensual.addColumn("Campo A");
        modelo_tabla_prevision_mensual.addColumn("Campo B");
        
       Object [] fila1=new Object[6];
       fila1[0]= (String) "ACZ-11";
       fila1[1]= (int)1997;
       fila1[2]= (String) dateToString(new Date());
       fila1[3]= (float)7500.0f;
       fila1[4]= (String) "Dato 1";
       fila1[5]= (String) "Dato 2";
       Object [] fila2= new String[6];
       fila2[0]= (String)"SP3-505";
       fila2[1]= (String)"2001";
       fila2[2]= dateToString(new Date());
       fila2[3]= (String)"895.00";
       fila2[4]= (String)"Dato 1";
       fila2[5]= (String)"Dato 2";
       
       Object [] fila3= new String[6];
       fila3[0]= (String) "MDQ-777";
       fila3[1]= (String) "2007";
       fila3[2]= (String) dateToString(new Date());
       fila3[3]= (String) "777.00";
       fila3[4]= (String) "Dato 1";
       fila3[5]= (String) "Dato 2";
       
        modelo_tabla_prevision_mensual.addRow(fila1);
        modelo_tabla_prevision_mensual.addRow(fila2);
        modelo_tabla_prevision_mensual.addRow(fila3);
        System.out.println("Cargo las previsiones mensual");
    }
     
     
     
     public void manejador_impresion()
     {
         generarExcel();
         String direccion=dir+"\\Basedatosmaquinas\\previsionmensual.pdf";
         try
         {
            impresor.imprimir(direccion);         
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
     }
     
     private void generarExcel()
     {
         // solo voy a generar el semanal
         if (tipoPrevision==1)
         {
             generarExcelPrevisionMensual();
         
         }
         else
         {
             System.out.println("Por el momento sólo generamos previsiones mensuales");
         }
     }
     
     private void generarExcelPrevisionMensual()
     {
         try
         {
              String rutaExcel=dir+"\\Basedatosmaquinas\\previsionmensual.xlsx";
              FileInputStream inputStream = new FileInputStream(new File(rutaExcel));             
              Workbook workbook = new XSSFWorkbook(inputStream);
              Sheet firstSheet = workbook.getSheetAt(0);
              Iterator iterator = firstSheet.iterator();
              Object [] row;
             System.out.println("Abri el excel");
             DataFormatter formatter = new DataFormatter();
             Iterator cellIterator;
             Cell cell;
             int i,c; 
             i=0;
             c=0;
             Row r;
             while (iterator.hasNext())
             {
             	r= (Row) iterator.next();
             	cellIterator = r.cellIterator();            	
             	while (cellIterator.hasNext()) 
                {
                    cell= (Cell) cellIterator.next();             		
                    String contenidoCelda = formatter.formatCellValue(cell);
                    //System.out.println(contenidoCelda);
                }
             }
         }
         catch (Exception e)
         {        
             System.out.println("No pudo abrir el Excel");
             e.printStackTrace();
         }
                                   
     
     }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 153, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Iconos usados\\close [#1538].png")); // NOI18N
        jButton1.setText("SALIR");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 220, 50));

        jButton2.setBackground(new java.awt.Color(0, 153, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("FOTOGRAFÍA/GASES");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 220, 50));

        jButton3.setBackground(new java.awt.Color(0, 153, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton3.setLabel("PLACAS CONTROL");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 220, 50));

        jButton4.setBackground(new java.awt.Color(0, 153, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("BANCO SUSPENSIÓN");
        jButton4.setBorder(null);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 220, 50));

        jButton5.setBackground(new java.awt.Color(0, 153, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("PREVISIÓN");
        jButton5.setBorder(null);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 220, 50));

        jButton11.setBackground(new java.awt.Color(0, 153, 204));
        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setBorder(null);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton11.setLabel("FRENÓMETRO");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton11MousePressed(evt);
            }
        });
        jPanel4.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 220, 50));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 640));

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LÍNEA DE PRODUCCIÓN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 660, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 930, 70));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(180, 82, 56));
        jTabbedPane1.setToolTipText("FOTOGRAFÍA/GASES");
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(217, 124, 78));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(153, 174, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 570));

        jPanel9.setBackground(new java.awt.Color(153, 174, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 470, 570));

        jTabbedPane1.addTab("FOTOGRAFÍA/GASES", jPanel3);

        jPanel5.setBackground(new java.awt.Color(163, 184, 255));
        jPanel5.setForeground(new java.awt.Color(217, 124, 78));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 885, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("BANCO SUSPENSIÓN", jPanel5);

        jPanel6.setBackground(new java.awt.Color(153, 194, 255));

        jPanel10.setBackground(new java.awt.Color(153, 184, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(0, 153, 204));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable1);

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 710, 480));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("Búsqueda");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 710, -1));

        jPanel11.setBackground(new java.awt.Color(0, 153, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setBackground(new java.awt.Color(0, 153, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("SIST. MECÁNICO");
        jButton6.setBorder(null);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });
        jPanel11.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 0, 100, 50));

        jPanel12.setBackground(new java.awt.Color(0, 153, 204));
        jPanel12.setForeground(new java.awt.Color(0, 153, 204));
        jPanel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel12MousePressed(evt);
            }
        });
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setBackground(new java.awt.Color(0, 153, 204));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("SIST. ELÉCTRICO");
        jButton7.setBorder(null);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
        });
        jPanel12.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 5, 110, 40));

        jPanel13.setBackground(new java.awt.Color(0, 153, 204));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setBackground(new java.awt.Color(0, 153, 204));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("INFO");
        jButton8.setBorder(null);
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton8MousePressed(evt);
            }
        });
        jPanel13.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 110, 40));

        jPanel14.setBackground(new java.awt.Color(0, 153, 204));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setBackground(new java.awt.Color(0, 153, 204));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("SIST. HIDRÁULICO");
        jButton9.setBorder(null);
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
        });
        jPanel14.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 110, 40));

        jPanel15.setBackground(new java.awt.Color(0, 153, 204));
        jPanel15.setForeground(new java.awt.Color(255, 255, 255));
        jPanel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setBackground(new java.awt.Color(0, 153, 204));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("ATRÁS");
        jButton10.setBorder(null);
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton10MousePressed(evt);
            }
        });
        jPanel15.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 110, 40));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("FRENÓMETRO", jPanel6);

        jPanel7.setBackground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 885, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("PLACAS CONTROL", jPanel7);

        jPanel16.setBackground(new java.awt.Color(153, 214, 255));
        jPanel16.setToolTipText("PREVISIÓN");

        jPanel21.setBackground(new java.awt.Color(153, 184, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("TOTAL: ");
        jPanel21.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 330, 40));

        jTable2.setBackground(new java.awt.Color(0, 153, 204));
        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable2);

        jPanel21.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 710, 390));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("PREVISIÓN SEMANAL");
        jPanel21.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 400, 40));

        jPanel28.setBackground(new java.awt.Color(153, 184, 255));
        jPanel28.setForeground(new java.awt.Color(255, 255, 255));
        jPanel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton20.setBackground(new java.awt.Color(153, 184, 255));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Iconos usados\\white printer 4242.png")); // NOI18N
        jButton20.setBorder(null);
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton20MousePressed(evt);
            }
        });
        jPanel28.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

        jPanel21.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 460, 50, -1));

        jPanel29.setBackground(new java.awt.Color(153, 184, 255));
        jPanel29.setForeground(new java.awt.Color(255, 255, 255));
        jPanel29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton21.setBackground(new java.awt.Color(153, 184, 255));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Iconos usados\\save white.png")); // NOI18N
        jButton21.setBorder(null);
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton21MousePressed(evt);
            }
        });
        jPanel29.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

        jPanel21.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 460, 50, -1));

        jPanel23.setBackground(new java.awt.Color(0, 153, 204));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton14.setBackground(new java.awt.Color(0, 153, 204));
        jButton14.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("MENSUAL");
        jButton14.setBorder(null);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton14MousePressed(evt);
            }
        });
        jPanel23.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 0, 100, 50));

        jPanel24.setBackground(new java.awt.Color(0, 153, 204));
        jPanel24.setForeground(new java.awt.Color(0, 153, 204));
        jPanel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel24MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel24MousePressed(evt);
            }
        });
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton16.setBackground(new java.awt.Color(0, 153, 204));
        jButton16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("SEMANAL");
        jButton16.setBorder(null);
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton16MousePressed(evt);
            }
        });
        jPanel24.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 5, 110, 40));

        jPanel25.setBackground(new java.awt.Color(0, 153, 204));
        jPanel25.setForeground(new java.awt.Color(255, 255, 255));
        jPanel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton17.setBackground(new java.awt.Color(0, 153, 204));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("SEMESTRAL");
        jButton17.setBorder(null);
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton17MousePressed(evt);
            }
        });
        jPanel25.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 110, 40));

        jPanel26.setBackground(new java.awt.Color(0, 153, 204));
        jPanel26.setForeground(new java.awt.Color(255, 255, 255));
        jPanel26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton18.setBackground(new java.awt.Color(0, 153, 204));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("CUATRIMESTRAL");
        jButton18.setBorder(null);
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton18MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton18MousePressed(evt);
            }
        });
        jPanel26.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 110, 40));

        jPanel27.setBackground(new java.awt.Color(0, 153, 204));
        jPanel27.setForeground(new java.awt.Color(255, 255, 255));
        jPanel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton19.setBackground(new java.awt.Color(0, 153, 204));
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("ANUAL");
        jButton19.setBorder(null);
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton19MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton19MousePressed(evt);
            }
        });
        jPanel27.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 110, 40));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PREVISIÓN", jPanel16);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 890, 560));
        jTabbedPane1.getAccessibleContext().setAccessibleName("FOTOGRAFÍA/GASES");

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 930, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:       
        this.setVisible(false);
        ventanaPrinci.toggleButtons();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
       jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        //jButton2MouseClicked(evt);
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        // TODO add your handling code here:
        //jButton4MouseClicked(evt);
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        // TODO add your handling code here:
        //jButton5MouseClicked(evt);
    }//GEN-LAST:event_jButton5MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        // TODO add your handling code here:
        //jButton3MouseClicked(evt);
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        //jButton1MouseClicked(evt);
    }//GEN-LAST:event_jButton1MousePressed

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed
        // TODO add your handling code here:
        //jButton7MouseClicked(evt);
    }//GEN-LAST:event_jButton7MousePressed

    private void jPanel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12MousePressed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here: 
        if (sistema_electrico_seleccionado)
        {
            manejador_tabla_señal_sistema_electrico_frenometro();
            return;
        }
        habilitarSistemaElectrico();
        manejador_tabla_señal_sistema_electrico_frenometro();
        //manejador_tabla_sist_electricos();
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:        
        if (!infoApretada)
        {
            infoApretada=true;
            abrirVentanaInformacionMaquina(maquinaSeleccionada,evt);            
        }      
        
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MousePressed
        // TODO add your handling code here:
        if (!infoApretada)
        {
            //jButton8MouseClicked(evt);
        }
        
    }//GEN-LAST:event_jButton8MousePressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        if (sistema_electrico_seleccionado)
        {
            manejador_tabla_control_sistema_electrico_frenometro();
            return;
        }
        habilitarSistemaMecanico();        
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        // TODO add your handling code here:
        //jButton6MouseClicked(evt);
    }//GEN-LAST:event_jButton6MousePressed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        if (sistema_electrico_seleccionado)
        {
            manejador_tabla_potencia_sistema_electrico_frenometro();
            return;
        }
        manejador_tabla_sist_hidraulico();
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MousePressed

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        // TODO add your handling code here:
        if (sistema_electrico_seleccionado)
        {
            deshabilitarSistemaElectrico();
        }
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MousePressed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MousePressed

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        // TODO add your handling code here:
        habilitarPrevisionMensual();
        caracteristicas_jtable();
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14MousePressed

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
        //cargarEstadisticoSemanal();
        habilitarPrevisionSemanal();
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16MousePressed

    private void jPanel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel24MouseClicked

    private void jPanel24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel24MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel24MousePressed

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17MouseClicked

    private void jButton17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17MousePressed

    private void jButton18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18MouseClicked

    private void jButton18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18MousePressed

    private void jButton19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19MouseClicked

    private void jButton19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19MousePressed

    private void jButton20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseClicked
        // TODO add your handling code here:
        manejador_impresion();
    }//GEN-LAST:event_jButton20MouseClicked

    private void jButton20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20MousePressed

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21MouseClicked

    private void jButton21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaLineaProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaLineaProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaLineaProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaLineaProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaLineaProduccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
