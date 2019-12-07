/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author mjrca
 */
public class ventanaInformacionMaquina extends javax.swing.JFrame {

    /**
     * Creates new form ventanaInformacionMaquina
     */
    
    private ventanaPrincipal ventanaPri;
    private ventanaLineaProduccion ventanaLineaProd;  
    private String contenidoArchivo;
    private Timer timer;
    private TimerTask task;
    private boolean bobina;
    
    public ventanaInformacionMaquina() 
    {
        this.setLocationRelativeTo(null);
        initComponents();
    }
    
    public ventanaInformacionMaquina(ventanaLineaProduccion ventanaLP) 
    {        
        initComponents();
        this.setLocationRelativeTo(null);
        ventanaLineaProd= ventanaLP;
        bobina=false;
        cargarInfoBasica();
        jPanel16.setVisible(false);
        timer = new Timer();
        task = new TimerTask() {
                                int tics=0;
                                boolean flag=false;
                                boolean activado=true;
                                public void establecerActivado(boolean a)
                                {
                                    activado=a;
                                }
                                @Override
                                public void run()
                                {
                                    if (bobina)
                                    {
                                        if (flag)
                                        {
                                            flag=false;
                                            cargarInfoBasicaBobina();
                                        }
                                        else
                                        {
                                            flag=true;
                                            cargarIntroBobina();
                                        }
                                        return;
                                    }
                                    if (activado)
                                    {
                                        if (flag)
                                        {
                                            flag=false;
                                            cargarInfoBasica();
                                        }                                        
                                        else
                                        {
                                            flag=true;
                                            cargarIntroduccion();
                                        }
                                    }                                        
                                    tics++;
                                }
        };            
        timer.schedule(task, 10, 20000);   // Empezamos dentro de 10ms y luego lanzamos la tarea cada 100ms     
    }
    
    public void cargarIntroduccion()
    {
        jLabel5.setText("INTRODUCCIÓN");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\intro.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel6.setText(contenidoArchivo);    
    }
    
    public void cargarInfoBasica()
    {
        jLabel5.setText("INFORMACIÓN BÁSICA");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\y2.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel6.setText(contenidoArchivo);
    }
    
    private void cargarInfoBasicaBobina()
    {
        jLabel5.setText("INFORMACIÓN BÁSICA");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\bobina\\\\bobina.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel6.setText(contenidoArchivo);
    }
    
    
    public void cargarInfoTecnica()
    {
        jLabel4.setText("INFORMACIÓN TÉCNICA");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\datostecnicos.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);
    }
    
    private void cargarInfoTecnicaBobina()
    {
        jLabel4.setText("INFORMACIÓN TÉCNICA");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\bobina\\\\datostecnicos.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);    
    }
    
    public void cargarTextoPrevision()
    {
        jLabel4.setText("PREVISIÓN");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\prevision.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);
    }
    
    private void cargarTextoPrevisionBobina()
    {
        jLabel4.setText("PREVISIÓN");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\bobina\\\\prevision.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);   
    }
    
    public void cargarTextoEstadístico()
    {
        jLabel4.setText("ESTADÍSTICO");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\estadistico.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);
    }
    
    private void cargarTextoEstadísticoBobina()
    {
        jLabel4.setText("ESTADÍSTICO");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\bobina\\\\estadistico.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);
    }
    
    public void cargarTextoCondicionesDeOperacion()
    {
        jLabel4.setText("CONDICIONES DE OPERACIÓN");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\condicionesdeoperacion.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);    
    }
    
    private void cargarTextoCondicionesDeOperacionBobina()
    {
        jLabel4.setText("CONDICIONES DE OPERACIÓN");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\bobina\\\\condicionesdeoperacion.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);  
    }
    
    
    public ventanaInformacionMaquina(ventanaPrincipal ventanaPrii)
    {        
        initComponents();
        this.setLocationRelativeTo(null);
        ventanaPri= ventanaPrii;
    }
    
    private void cargarImagenBobina()
    {
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Imagenes maquinas\\bobina.png"));    
    }
    
    private void cargarIntroBobina()
    {
        jLabel5.setText("INTRODUCCIÓN");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\bobina\\\\intro.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel6.setText(contenidoArchivo);
    
    }
    
    private void cargarDatosTecnicosBobina()
    {
        jLabel4.setText("INFORMACIÓN TÉCNICA");
        try
        {
            volcarContenido("C:\\\\Users\\\\mjrca\\\\Desktop\\\\Basedatosmaquinas\\\\y2\\\\bobina\\\\datostecnicos.txt");
        }
        catch(Exception e)
        {
            System.out.println("No pude abrir el txt");
        }
        jLabel1.setText(contenidoArchivo);        
    }
    
    public void habilitarInfoBobina()
    {
        bobina=true;
        jComboBox1.setVisible(false);
        jPanel16.setVisible(true);
        jLabel3.setText("Bobina del Y2-180L-4");
        cargarImagenBobina();
        cargarIntroBobina();
        cargarDatosTecnicosBobina();
        
    }
    
    private void cargarImagenMotor()
    {
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Imagenes maquinas\\Y2-180L-4.png")); 
    }

    
    public void cambiarContextoCierre()
    {
        ventanaLineaProd.toggleButtons();
    }
    
    public void cambiarContexto()
    {
        
    }
    
    
    
    public void cargarMaquina(String nombre)
    {
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jLabel3.setText(nombre);
        cargarInfoBasica();
        cargarInfoTecnica();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1150, 640));
        setMinimumSize(new java.awt.Dimension(1150, 640));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1150, 640));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));

        jButton1.setBackground(new java.awt.Color(0, 153, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("COND. DE OPERACIÓN");
        jButton1.setActionCommand("ASPECTO1");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton2.setBackground(new java.awt.Color(0, 153, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("INFO TÉCNICA");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton3.setBackground(new java.awt.Color(0, 153, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("ESTADÍSTICO");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton4.setBackground(new java.awt.Color(0, 153, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Iconos usados\\close [#1538].png")); // NOI18N
        jButton4.setText("SALIR");
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton6.setBackground(new java.awt.Color(0, 153, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("ATRÁS");
        jButton6.setBorder(null);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 660));

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 880, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 970, 40));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setBackground(new java.awt.Color(153, 153, 255));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 51, 102));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOBINA", "PARTE2", "PARTE3", "PARTE4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBox1MouseExited(evt);
            }
        });
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 0, 110, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Imagenes maquinas\\Y2-180L-4.png")); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(410, 290));
        jLabel2.setMinimumSize(new java.awt.Dimension(410, 290));
        jLabel2.setName(""); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(410, 290));
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 290));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 410, 290));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("INFORMACIÓN TÉCNICA");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 860, 40));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(10, 0));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 470, 1));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(10, 0));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 320, 1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(10, 0));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 430, 1));

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(153, 153, 255));

        jLabel6.setBackground(new java.awt.Color(0, 51, 102));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jScrollPane1.setViewportView(jLabel6);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 890, 170));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 430, 250));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Imagenes maquinas\\fondo blanco.png")); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 390, 240));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("INFORMACIÓN BÁSICA");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 860, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\mjrca\\Desktop\\Imagenes maquinas\\fondoinfomaquina.png")); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 620));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 950, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        cambiarContextoCierre();
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        // TODO add your handling code here:
        //jButton4MouseClicked(evt);
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if (bobina)
        {
            cargarTextoCondicionesDeOperacionBobina();
            return;
        }
        cargarTextoCondicionesDeOperacion();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if (bobina)
        {
            cargarInfoTecnicaBobina();
            return;
        }
        cargarInfoTecnica();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        if (bobina)
        {
            cargarTextoEstadísticoBobina();
            return;
        }
        cargarTextoEstadístico();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        if (bobina)
        {
            cargarTextoPrevisionBobina();
            return;
        }
        cargarTextoPrevision();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jComboBox1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseExited

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        String seleccionadoCombo=jComboBox1.getSelectedItem().toString();
        if (seleccionadoCombo.equals("BOBINA"))
        {
            habilitarInfoBobina();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        if (bobina)
        {
            jPanel16.setVisible(false);
            bobina=false;
            cargarImagenMotor();
            jComboBox1.setVisible(true);
            cargarMaquina("Y2-280L-4");
        }
    }//GEN-LAST:event_jButton6MouseClicked

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
            java.util.logging.Logger.getLogger(ventanaInformacionMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaInformacionMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaInformacionMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaInformacionMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaInformacionMaquina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    // METODOS
    
    public void volcarContenido(String archivo) throws FileNotFoundException, IOException {
        
        //FileReader f = new FileReader(archivo);
        FileReader f = new FileReader(archivo, StandardCharsets.UTF_8);
        InputStreamReader fis= new InputStreamReader(new FileInputStream(archivo),"ISO-8859-1");        
        BufferedReader b = new BufferedReader(f);
        String linea;
        contenidoArchivo= "<html>";
        linea=b.readLine();
        while(linea!=null) 
        {
            contenidoArchivo+=linea + "<p>";
            linea=b.readLine();                    
        }
        contenidoArchivo+= "</html>";
        //contenidoArchivo = new String(contenidoArchivo.getBytes("UTF-8"), "ISO-8859-1");
        b.close();
    }  
    
    


}
