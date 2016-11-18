
package laboratorio3;

import CLIPSJNI.Environment;
import CLIPSJNI.PrimitiveValue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class panelrobot extends JPanel {
    
     public  Environment clips ;
    private boolean acept,robot=false;
    public JToggleButton [][]bMatriz;
    public int [][]movimientos={{-1,0,1,0},{0,1,0,-1}};
    public int x,y,robotx,roboty;
    public panelprincipal panelprincipal;
    private Moverrobot  jugar;
    
    public panelrobot(JFrame Principal,panelprincipal p)
    {
        setPreferredSize(Principal.getSize());
        panelprincipal=p;
       Principal.getContentPane().setLayout(new BorderLayout());
       Principal.getContentPane().add(this,BorderLayout.CENTER);
       Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Principal.pack();
       iniciar();
       acept=false;
        Matriz.setVisible(false);
        Obtaculos.setVisible(false);
        Robot.setVisible(false);
        Play.setVisible(false);
        Atras.setVisible(false); 
    
    }
    
    
    public void paintComponent(Graphics g)
        {
            Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Images/fondo.jpg"));
            g.drawImage(imagenFondo.getImage(), 0, 0, 
            tamanio.width, tamanio.height, this);
            setOpaque(false);
            super.paintComponent(g);
        }
    
    
    public void mover() throws Exception
    {
        clips=new Environment();
        clips.load("robot.clp");
        bMatriz[robotx][roboty].setBackground(Color.WHITE); 
        clips.run();
        
        clips.eval("(assert(percepcion "
        + "(s1 "+ bMatriz[robotx-1][roboty-1].getName()+")"
        + "(s2 "+ bMatriz[robotx-1][roboty].getName()+")"
        + "(s3 "+ bMatriz[robotx-1][roboty+1].getName()+")"
        + "(s4 "+ bMatriz[robotx][roboty-1].getName()+")"
        + "(s5 "+ bMatriz[robotx][roboty+1].getName()+")"
        + "(s6 "+ bMatriz[robotx+1][roboty-1].getName()+")"
        + "(s7 "+ bMatriz[robotx+1][roboty].getName()+")"
        + "(s8 "+ bMatriz[robotx+1][roboty+1].getName()+")))");
        clips.run();
        String evaluar ="(find-all-facts ((?m movimiento)) TRUE)";            
        PrimitiveValue value=clips.eval(evaluar);
        int a=0;
        a= Integer.parseInt(value.get(0).getFactSlot("direccion").toString());
        robotx+=movimientos[0][a];
        roboty+=movimientos[1][a];
        bMatriz[robotx][roboty].setBackground(Color.red);
        clips.reset();
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
               int cont=1;
               if((x>7 & x<21) & (y>7 & y<21))
               {
                   x=x+2; y=y+2;
                    bMatriz= new JToggleButton[x][y];
                    Matriz.setLayout(new GridLayout(x-2,y-2,0,0));
                    for (int i=0; i< x;i++) {
                        for (int j=0; j<y;j++) {
                            bMatriz[i][j] = new JToggleButton();
                            bMatriz[i][j].setEnabled(false);
                            bMatriz[i][j].setName("0");
                            bMatriz[i][j].setSize(Matriz.getWidth()/x, Matriz.getHeight()/y);
                            bMatriz[i][j].setMinimumSize(new Dimension(i,j));
                            if(i!=0 & j!=0 & i!=x-1 & j!=y-1)
                                Matriz.add(bMatriz[i][j]);
                            else
                            {
                                bMatriz[i][j].setBackground(Color.BLACK);
                                bMatriz[i][j].setName("1");
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

            Obtaculos.setVisible(true);
            Robot.setVisible(true);
            Play.setVisible(true);
            Atras.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(null, "Matriz no generada","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
    }                                       

    
    private void RobotActionPerformed(java.awt.event.ActionEvent evt) {                                      
       
        int i,j; 
        for ( i=0; i< x;i++) {
            for ( j=0; j<y;j++) {
                bMatriz[i][j].setEnabled(true);
                if(bMatriz[i][j].getBackground()!=Color.BLACK)
                {
                    bMatriz[i][j].setSelected(true);
                    bMatriz[i][j].setBackground(Color.white);
                    bMatriz[i][j].setName("0");
                }
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

    private void ObtaculosActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        int i,j; 
        for ( i=0; i< x;i++) {
            for ( j=0; j<y;j++) {
                bMatriz[i][j].setEnabled(true);
                if(bMatriz[i][j].getBackground()!=Color.red & bMatriz[i][j].getBackground()!=Color.BLACK)
                {
                    bMatriz[i][j].setSelected(true);
                    bMatriz[i][j].setBackground(Color.white);
                    bMatriz[i][j].setName("0");
                }
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

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {                                      
        
        jugar.detenrobot();
        acept=false;
        Matriz.setVisible(false);
        Obtaculos.setVisible(false);
        Robot.setVisible(false);
        Play.setVisible(false);
        Atras.setVisible(false);
        VALIDACION.setVisible(true);
        alto.setVisible(true);
        ancho.setVisible(true);
        columnas.setVisible(true);
        filas.setVisible(true);
        generar.setVisible(true);
        ingresar.setVisible(true);
        Aceptar.setVisible(true);
        for (int i=0; i< x;i++) {
            for (int j=0; j<y;j++) {
                bMatriz[i][j].setEnabled(false);
                bMatriz[i][j].setSelected(true);
                bMatriz[i][j].setBackground(Color.white);
                ActionListener[] tak1 = bMatriz[i][j].getActionListeners();
                if(tak1.length!=0) bMatriz[i][j].removeActionListener(tak1[0]);
                bMatriz[i][j].setName("0");
            }
        }
        panelprincipal.closereactivo(); 
        
    }                                     
    

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {                                     
        
        if(robot)
        {
                for (int i=0; i< x;i++) {
                    for (int j=0; j<y;j++) {
                        bMatriz[i][j].setEnabled(false);
                        if(bMatriz[i][j].getBackground()!=Color.red & bMatriz[i][j].getBackground()!=Color.BLACK) 
                            bMatriz[i][j].setSelected(false);
                    }
                 }

                 bMatriz[robotx][roboty].setBackground(Color.WHITE);
                 bMatriz[robotx][roboty].setBackground(Color.red);
                 jugar= new Moverrobot(this);
                 jugar.start();
                 Play.setVisible(false);
                 Obtaculos.setVisible(false);
                 Robot.setVisible(false);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No ah colocado el robot","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
    }                                    

    
    private void bmatrizActionPerformed(java.awt.event.ActionEvent evt) {                                               
       
            JToggleButton elemento = (JToggleButton)evt.getSource();
            elemento.setBackground(Color.red);
            robotx=elemento.getMinimumSize().width;
            roboty=elemento.getMinimumSize().height;
            robot=true;
            for (int i=0; i< x;i++) {
                  for (int j=0; j<y;j++) {

                      bMatriz[i][j].setEnabled(false);
                      bMatriz[i][j].setSelected(false);

                  }
              }
        
    } 
    
    private void bmatriz2ActionPerformed(java.awt.event.ActionEvent evt2) {                                               
       
            JToggleButton elemento2 = (JToggleButton)evt2.getSource();
            elemento2.setBackground(Color.BLACK);
            elemento2.setName("1");
            
        
    } 
    
    
    
    
    
    
    public void iniciar()
    {
        
        Bienvenido = new javax.swing.JLabel();
        Matriz = new javax.swing.JPanel();
        ingresar = new javax.swing.JLabel();
        ancho = new javax.swing.JLabel();
        filas = new javax.swing.JTextField();
        alto = new javax.swing.JLabel();
        columnas = new javax.swing.JTextField();
        VALIDACION = new javax.swing.JLabel();
        Aceptar = new javax.swing.JButton();
        generar = new javax.swing.JButton();
        Robot = new javax.swing.JButton();
        Obtaculos = new javax.swing.JButton();
        Atras = new javax.swing.JButton();
        Play = new javax.swing.JButton();
        
        Bienvenido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenido.setText("Bienvenido Agente reactivo");

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
        ingresar.setToolTipText("");

        ancho.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ancho.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ancho.setText("Filas:");
        ancho.setToolTipText("");

        filas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        filas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filasKeyTyped(evt);
            }
        });

        alto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        alto.setText("columnas:");
        alto.setToolTipText("");

        columnas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        columnas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                columnasKeyTyped(evt);
            }
        });

        VALIDACION.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VALIDACION.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VALIDACION.setText("filas y columnas >8 y <21");

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        generar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        generar.setText("generar matriz");
        generar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        Robot.setText("Colocar Robot");
        Robot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobotActionPerformed(evt);
            }
        });

        Obtaculos.setText("Obtaculos");
        Obtaculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObtaculosActionPerformed(evt);
            }
        });

        Atras.setText("atras");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout robotpanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(robotpanelLayout);
        robotpanelLayout.setHorizontalGroup(
            robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(robotpanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(Matriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(robotpanelLayout.createSequentialGroup()
                        .addComponent(ingresar, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, robotpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(robotpanelLayout.createSequentialGroup()
                                        .addComponent(alto, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(columnas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addGroup(robotpanelLayout.createSequentialGroup()
                                        .addComponent(ancho, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(filas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addGroup(robotpanelLayout.createSequentialGroup()
                                        .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)))
                                .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(robotpanelLayout.createSequentialGroup()
                                        .addComponent(Obtaculos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Robot))
                                    .addComponent(VALIDACION, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, robotpanelLayout.createSequentialGroup()
                                .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, robotpanelLayout.createSequentialGroup()
                                .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Play, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Atras, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                                .addGap(67, 67, 67))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, robotpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
        );
        robotpanelLayout.setVerticalGroup(
            robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(robotpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(robotpanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Matriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(robotpanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ancho, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(columnas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addComponent(VALIDACION)
                        .addGap(18, 18, 18)
                        .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(robotpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Robot)
                            .addComponent(Obtaculos))
                        .addGap(18, 18, 18)
                        .addComponent(Play, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        
    }
    
    
    
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Atras;
    private javax.swing.JLabel Bienvenido;
    private javax.swing.JPanel Matriz;
    private javax.swing.JButton Obtaculos;
    private javax.swing.JButton Play;
    private javax.swing.JButton Robot;
    private javax.swing.JLabel VALIDACION;
    private javax.swing.JLabel alto;
    private javax.swing.JLabel ancho;
    private javax.swing.JTextField columnas;
    private javax.swing.JTextField filas;
    private javax.swing.JButton generar;
    private javax.swing.JLabel ingresar;
}
