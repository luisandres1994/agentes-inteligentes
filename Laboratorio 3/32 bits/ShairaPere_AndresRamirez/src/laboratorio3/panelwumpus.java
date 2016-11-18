
package laboratorio3;

import CLIPSJNI.Environment;
import CLIPSJNI.PrimitiveValue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.PriorityQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class panelwumpus extends JPanel {
    
    public  Environment clips ;
    public int x,y,exploradorx=-1,exploradory=-1,tesorox,tesoroy;
    public panelprincipal panelprincipal;
    public JToggleButton [][]bMatriz;
    public boolean [][]Mundo;
    private boolean acept, explorador=false,wump=false,tesoro=false;
    public int [][]movimientos={{0,-1,0,1,0},{0,0,1,0,-1}};
    Timer timer = new Timer();
    a_estrella jugar;
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Atras;
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JButton Explorador;
    private javax.swing.JPanel Matriz;
    private javax.swing.JButton Play;
    private javax.swing.JButton Precipicio;
    private javax.swing.JButton Tesoro;
    private javax.swing.JLabel VALIDACION;
    private javax.swing.JButton Wumpus;
    private javax.swing.JLabel alto;
    private javax.swing.JLabel ancho;
    private javax.swing.JTextField columnas;
    private javax.swing.JTextField filas;
    private javax.swing.JButton generar;
    private javax.swing.JLabel ingresar;
    
    
    public panelwumpus(JFrame Principal,panelprincipal p)
    {
       this.setPreferredSize(new Dimension(800,600));
       panelprincipal=p;
       Principal.getContentPane().setLayout(new BorderLayout());
       Principal.getContentPane().add(this,BorderLayout.CENTER);
       Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Principal.pack();
       iniciar();
       acept=false;
        Matriz.setVisible(false);
        Wumpus.setVisible(false);
        Explorador.setVisible(false);
        Play.setVisible(false);
        Atras.setVisible(false);
        Tesoro.setVisible(false);
        Precipicio.setVisible(false);
        
    
    }
    
   
   
    
    
    
    public void paintComponent(Graphics g)
        {
            Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Images/fondo.png"));
            g.drawImage(imagenFondo.getImage(), 0, 0, 
            tamanio.width, tamanio.height, this);
            setOpaque(false);
            super.paintComponent(g);
        }
    
    
    private void filasKeyTyped(java.awt.event.KeyEvent evt) {                               
        char c=evt.getKeyChar();  
                if(Character.isLetter(c)) {
                  getToolkit().beep();
               evt.consume();   
                }
    }                              

    private void columnasKeyTyped(java.awt.event.KeyEvent evt) {                                  
        char c=evt.getKeyChar();  
                if(Character.isLetter(c)) {
                  getToolkit().beep();
               evt.consume();   
                }
    }                                 

    private void generarActionPerformed(java.awt.event.ActionEvent evt) {                                        
        Matriz.setVisible(false);
        Matriz.removeAll();     
        if(filas.getText().length()>0 & columnas.getText().length()>0)
         {
                x=Integer.parseInt(filas.getText());
                y=Integer.parseInt(columnas.getText());
               Mundo=new boolean[x][y];
               int cont=1;
               if((x>10 & x<41) & (y>10 & y<41))
               {
                   x=x+2; y=y+2;
                    bMatriz= new JToggleButton[x][y];
                    Matriz.setLayout(new GridLayout(x-2,y-2,0,0));
                    for (int i=0; i< x;i++) {
                        for (int j=0; j<y;j++) {
                            bMatriz[i][j] = new JToggleButton();
                            bMatriz[i][j].setEnabled(false);
                            bMatriz[i][j].setName("vacio");
                            bMatriz[i][j].setSize(Matriz.getWidth()/x, Matriz.getHeight()/y);
                            bMatriz[i][j].setMinimumSize(new Dimension(i,j));
                             if(i!=0 & j!=0 & i!=x-1 & j!=y-1)
                                Matriz.add(bMatriz[i][j]);
                            else
                            {
                                bMatriz[i][j].setName("limite");
                            }
                        }
                    }
                    Matriz.setVisible(true);
                    generar.setText("Cambiar Dimensiones");
                    acept=true;
                    
               }else
               {
                   acept=false;
                   generar.setText("generar matriz");
                   JOptionPane.showMessageDialog(null, "Dimensiones invalidas","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
               } 
         }else 
         {
             acept=false;
             generar.setText("generar matriz");
              JOptionPane.showMessageDialog(null, "No ah ingresado dimensiones","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
         }
         filas.setText(null);
         columnas.setText(null);
    }    
    
    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
        if(acept)
        {
            VALIDACION.setVisible(false);
            alto.setVisible(false);
            ancho.setVisible(false);
            columnas.setVisible(false);
            filas.setVisible(false);
            generar.setVisible(false);
            ingresar.setVisible(false);
            Aceptar.setVisible(false);

            Wumpus.setVisible(true);
            Explorador.setVisible(true);
            Play.setVisible(true);
            Atras.setVisible(true);
            Tesoro.setVisible(true);
            Precipicio.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null, "Matriz no generada","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Explorador
    private void bmatrizActionPerformed(java.awt.event.ActionEvent evt) {                                               
       
        
            JToggleButton elemento = (JToggleButton)evt.getSource();
            elemento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/explorador.jpg")));
            elemento.setName("vacio");
            for (int i=0; i< x;i++) {
                  for (int j=0; j<y;j++) {
                      bMatriz[i][j].setEnabled(false);
                      bMatriz[i][j].setSelected(false);
                  }
              }
            exploradorx=elemento.getMinimumSize().width;
            exploradory=elemento.getMinimumSize().height;
            explorador=true;
        elemento.setEnabled(true);
    }
    
    
   //Wumpus
   private void bmatriz2ActionPerformed(java.awt.event.ActionEvent evt2) {                                               
       
            JToggleButton elemento2 = (JToggleButton)evt2.getSource();
            elemento2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wumpus-lab.png")));
            elemento2.setName("wumpus");
            int x1,y1;
            x1=elemento2.getMinimumSize().width;
            y1=elemento2.getMinimumSize().height;
            if(x1>0 && bMatriz[x1-1][y1].getName()=="vacio")
            {
                bMatriz[x1-1][y1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mal-olor.png")));
                bMatriz[x1-1][y1].setName("olor");
            }
            if(y1<(y-1) && bMatriz[x1][y1+1].getName()=="vacio") 
            {
                bMatriz[x1][y1+1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mal-olor.png")));
                bMatriz[x1][y1+1].setName("olor");
            }
            if(x1<(x-1) && bMatriz[x1+1][y1].getName()=="vacio") 
            {
                bMatriz[x1+1][y1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mal-olor.png")));
                bMatriz[x1+1][y1].setName("olor");
            }
            if(y1>0 && bMatriz[x1][y1-1].getName()=="vacio") 
            {
                bMatriz[x1][y1-1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mal-olor.png")));
                bMatriz[x1][y1-1].setName("olor");
            }
            for (int i=0; i< x;i++) {
                  for (int j=0; j<y;j++) {
                      bMatriz[i][j].setEnabled(false);
                      bMatriz[i][j].setSelected(false);
                  }
              }
            elemento2.setEnabled(true);
            wump=true;
        
    }
   
   
 
    //Precipicio
    private void bmatriz3ActionPerformed(java.awt.event.ActionEvent evt) {                                               
       
            JToggleButton elemento2 = (JToggleButton)evt.getSource();
            elemento2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/acantilado.png")));
            elemento2.setName("precipicio");
            int x1,y1;
            x1=elemento2.getMinimumSize().width;
            y1=elemento2.getMinimumSize().height;
            if(x1>0 && bMatriz[x1-1][y1].getName()=="vacio")
            {
                bMatriz[x1-1][y1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/viento.png")));
                bMatriz[x1-1][y1].setName("viento");
            }
            if(y1<(y-1) && bMatriz[x1][y1+1].getName()=="vacio") 
            {
                bMatriz[x1][y1+1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/viento.png")));
                bMatriz[x1][y1+1].setName("viento");
            }
            if(x1<(x-1) && bMatriz[x1+1][y1].getName()=="vacio") 
            {
                bMatriz[x1+1][y1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/viento.png")));
                bMatriz[x1+1][y1].setName("viento");
            }
            if(y1>0 && bMatriz[x1][y1-1].getName()=="vacio") 
            {
                bMatriz[x1][y1-1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/viento.png")));
                bMatriz[x1][y1-1].setName("viento");
            }
            elemento2.setEnabled(true);
            
        
    } 
    
    
    //Tesoro 
    private void bmatriz4ActionPerformed(java.awt.event.ActionEvent evt) {                                               
       
            JToggleButton elemento2 = (JToggleButton)evt.getSource();
            elemento2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tesoro.jpg")));
            elemento2.setName("tesoro");
            int x1,y1;
            x1=elemento2.getMinimumSize().width;
            y1=elemento2.getMinimumSize().height;
            if(x1>0 && bMatriz[x1-1][y1].getName()=="vacio")
            {
                bMatriz[x1-1][y1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/brillo.png")));
                bMatriz[x1-1][y1].setName("brillo");
            }
            if(y1<(y-1) && bMatriz[x1][y1+1].getName()=="vacio") 
            {
                bMatriz[x1][y1+1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/brillo.png")));
                bMatriz[x1][y1+1].setName("brillo");
            }
            if(x1<(x-1) && bMatriz[x1+1][y1].getName()=="vacio") 
            {
                bMatriz[x1+1][y1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/brillo.png")));
                bMatriz[x1+1][y1].setName("brillo");
            }
            if(y1>0 && bMatriz[x1][y1-1].getName()=="vacio") 
            {
                bMatriz[x1][y1-1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/brillo.png")));
                bMatriz[x1][y1-1].setName("brillo");
            }
            for (int i=0; i< x;i++) {
                  for (int j=0; j<y;j++) {
                      bMatriz[i][j].setEnabled(false);
                      bMatriz[i][j].setSelected(false);
                  }
              }
            elemento2.setEnabled(true);
            tesorox=elemento2.getMinimumSize().width;
            tesoroy=elemento2.getMinimumSize().height;
            tesoro=true;
        
    }
      
    
    private void WumpusActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        int i,j; 
        if(!wump)
        {
        for ( i=0; i< x;i++) {
            for ( j=0; j<y;j++) {
                if(bMatriz[i][j].getName()=="vacio")bMatriz[i][j].setEnabled(true);
                bMatriz[i][j].setSelected(true);
                ActionListener[] tak1 = bMatriz[i][j].getActionListeners();
                if(tak1.length!=0) bMatriz[i][j].removeActionListener(tak1[0]);
                bMatriz[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt2) {
                              bmatriz2ActionPerformed(evt2);
                             }
                    });
            }
        }
        }
    }
    
   
   
   
    private void ExploradorActionPerformed(java.awt.event.ActionEvent evt) {                                      
       
        int i,j; 
        if(!explorador)
        {
            for ( i=0; i< x;i++) {
                for ( j=0; j<y;j++) {
                 if(bMatriz[i][j].getName()=="vacio")bMatriz[i][j].setEnabled(true);
                ActionListener[] tak1 = bMatriz[i][j].getActionListeners();
                if(tak1.length!=0) 
                    bMatriz[i][j].removeActionListener(tak1[0]);
                    bMatriz[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                              bmatrizActionPerformed(evt);
                             }
                    });
                }
            }
        }
        
    } 
    
     private void PrecipicioActionPerformed(java.awt.event.ActionEvent evt) {
        int i,j;
        for ( i=0; i< x;i++) {
            for ( j=0; j<y;j++) {
                if(bMatriz[i][j].getName()=="vacio")bMatriz[i][j].setEnabled(true);
                bMatriz[i][j].setSelected(true);
                ActionListener[] tak1 = bMatriz[i][j].getActionListeners();
                if(tak1.length!=0) bMatriz[i][j].removeActionListener(tak1[0]);
                bMatriz[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt2) {
                              bmatriz3ActionPerformed(evt2);
                             }
                    });
            }
        }
        
    }  
     
      private void TesoroActionPerformed(java.awt.event.ActionEvent evt) {                                       
         int i,j;
         if(!tesoro)
         {
            for ( i=0; i< x;i++) {
            for ( j=0; j<y;j++) {
                if(bMatriz[i][j].getName()=="vacio")bMatriz[i][j].setEnabled(true);
                bMatriz[i][j].setSelected(true);
                ActionListener[] tak1 = bMatriz[i][j].getActionListeners();
                if(tak1.length!=0) bMatriz[i][j].removeActionListener(tak1[0]);
                bMatriz[i][j].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt2) {
                              bmatriz4ActionPerformed(evt2);
                             }
                    });
            }
        }
        }
    }  
    
    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {                                      
        
        acept=false;
        Matriz.setVisible(false);
        Wumpus.setVisible(false);
        Explorador.setVisible(false);
        Play.setVisible(false);
        Atras.setVisible(false);
        Tesoro.setVisible(false);
        Precipicio.setVisible(false);
        VALIDACION.setVisible(true);
        alto.setVisible(true);
        ancho.setVisible(true);
        columnas.setVisible(true);
        filas.setVisible(true);
        generar.setVisible(true);
        ingresar.setVisible(true);
        Aceptar.setVisible(true);
        explorador=false;wump=false;tesoro=false;
        for (int i=0; i< x;i++) {
            for (int j=0; j<y;j++) {
                bMatriz[i][j].setEnabled(false);
                bMatriz[i][j].setSelected(true);
                bMatriz[i][j].setBackground(Color.white);
                ActionListener[] tak1 = bMatriz[i][j].getActionListeners();
                if(tak1.length!=0) bMatriz[i][j].removeActionListener(tak1[0]);
                bMatriz[i][j].setName("vacio");
            }
        }
        panelprincipal.closedeliberativo(); 
        
    }
    
    
    
    
    
   
    
                                          

 
     private void PlayActionPerformed(java.awt.event.ActionEvent evt) throws Exception {                                     
        
         jugar=new a_estrella(this);
         jugar.start();
    }                                    

    
    
    public void iniciar()
    {
        
        Bienvenido = new javax.swing.JLabel();
        Matriz = new javax.swing.JPanel();
        ingresar = new javax.swing.JLabel();
        ancho = new javax.swing.JLabel();
        filas = new javax.swing.JTextField();
        columnas = new javax.swing.JTextField();
        alto = new javax.swing.JLabel();
        VALIDACION = new javax.swing.JLabel();
        Aceptar = new javax.swing.JButton();
        generar = new javax.swing.JButton();
        Explorador = new javax.swing.JButton();
        Wumpus = new javax.swing.JButton();
        Precipicio = new javax.swing.JButton();
        Tesoro = new javax.swing.JButton();
        Play = new javax.swing.JButton();
        Atras = new javax.swing.JButton();

       

        Bienvenido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenido.setText("Bienvenido agente deliverativo");

        Matriz.setBackground(new java.awt.Color(255, 255, 255));
        Matriz.setForeground(new java.awt.Color(255, 255, 255));
        Matriz.setMaximumSize(new java.awt.Dimension(500, 500));
        Matriz.setMinimumSize(new java.awt.Dimension(500, 500));
        Matriz.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout MatrizLayout = new javax.swing.GroupLayout(Matriz);
        Matriz.setLayout(MatrizLayout);
        MatrizLayout.setHorizontalGroup(
            MatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        MatrizLayout.setVerticalGroup(
            MatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );


        ingresar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ingresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ingresar.setText("Ingrese las dimensiones de la matriz");

        ancho.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ancho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ancho.setText("Filas:");
        ancho.setToolTipText("");

        filas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        filas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filasKeyTyped(evt);
            }
        });

        columnas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        columnas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                columnasKeyTyped(evt);
            }
        });

        alto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alto.setText("columnas:");

        VALIDACION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VALIDACION.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VALIDACION.setText("filas y columnas >10 y <41");

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        generar.setText("generar");
        generar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        Explorador.setText("Explorador");
        Explorador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExploradorActionPerformed(evt);
            }
        });

        Wumpus.setText("Wumpus");
        Wumpus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WumpusActionPerformed(evt);
            }
        });

        Precipicio.setText("Precipicios");
        Precipicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecipicioActionPerformed(evt);
            }
        });

        Tesoro.setText("Tesoro");
        Tesoro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesoroActionPerformed(evt);
            }
        });

        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    PlayActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(panelwumpus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Atras.setText("Atras");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelwumpusLayout = new javax.swing.GroupLayout(this);
        this.setLayout(panelwumpusLayout);
        panelwumpusLayout.setHorizontalGroup(
            panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(Bienvenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(120, 120, 120))
            .addGroup(panelwumpusLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Matriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                        .addComponent(Precipicio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Tesoro, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                        .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                                .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Aceptar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                                .addComponent(Explorador, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Wumpus, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                        .addComponent(alto)
                        .addGap(18, 18, 18)
                        .addComponent(columnas, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                        .addComponent(ingresar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                        .addComponent(ancho, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(filas, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                        .addComponent(VALIDACION, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelwumpusLayout.createSequentialGroup()
                        .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Play, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))))
        );
        panelwumpusLayout.setVerticalGroup(
            panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelwumpusLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelwumpusLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(ingresar)
                        .addGap(36, 36, 36)
                        .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ancho, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(columnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alto))
                        .addGap(18, 18, 18)
                        .addComponent(VALIDACION)
                        .addGap(37, 37, 37)
                        .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Aceptar)
                            .addComponent(generar))
                        .addGap(44, 44, 44)
                        .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Explorador, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wumpus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelwumpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Precipicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tesoro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addComponent(Play, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelwumpusLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Matriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))))
        );
    }
    
    
    
}
