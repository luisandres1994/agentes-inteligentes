package laboratorio3;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;



public final class panelprincipal extends JPanel{
    
    
    private  Reactivo R;
    private  Deliberativo D;
    public panelprincipal(JFrame Principal)
    {
        setPreferredSize(Principal.getSize());
        
       Principal.getContentPane().setLayout(new BorderLayout());
       Principal.getContentPane().add(this,BorderLayout.CENTER);
       Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Principal.pack();
       iniciar();
       R=new Reactivo(this);
       D=new Deliberativo(this);
    }
   
    public void paintComponent(Graphics g)
        {
            Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Images/fond2.jpg"));
            g.drawImage(imagenFondo.getImage(), 0, 0, 
            tamanio.width, tamanio.height, this);
            setOpaque(false);
            super.paintComponent(g);
        }
    
    private void robotActionPerformed(java.awt.event.ActionEvent evt) {                                      
        R.setVisible(true);
    } 
    
    public void closereactivo()
    {
        //R.removeAll();
        R.setVisible(false);
    }
    
    private void wumpusActionPerformed(java.awt.event.ActionEvent evt) {                                      
        D.setVisible(true);
    }
    
    public void closedeliberativo()
    {
        //D.removeAll();
        D.setVisible(false);
    } 
    
    public void iniciar()
    {
        Titulo = new javax.swing.JLabel();
        tema = new javax.swing.JLabel();
        seleccion1 = new javax.swing.JLabel();
        robot = new javax.swing.JButton();
        wunpus = new javax.swing.JButton();
        reactivo = new javax.swing.JLabel();
        Deliverativo = new javax.swing.JLabel();
        Shaira = new javax.swing.JLabel();
        Andres = new javax.swing.JLabel();


        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setMinimumSize(new java.awt.Dimension(800, 600));
        this.setPreferredSize(new java.awt.Dimension(800, 600));

        Titulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Agentes Inteligentes (Inteligencia Artificial)");
        Titulo.setAlignmentY(0.0F);
        Titulo.setFocusable(false);
        Titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Titulo.setMaximumSize(new java.awt.Dimension(1024, 768));

        tema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tema.setText("Agente Reactivo - Agente Deliverativo");

        seleccion1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seleccion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seleccion1.setText("Seleccione el agente a evaluar");

        robot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/robot.png")));
        robot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                robotActionPerformed(evt);
            }
        });
        

        wunpus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wumpus.png")));
        wunpus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wumpusActionPerformed(evt);
            }
        });
        
        reactivo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        reactivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reactivo.setText("Reactivo");

        Deliverativo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Deliverativo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Deliverativo.setText("Deliverativo");

        Shaira.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Shaira.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Shaira.setText("Shaira Pérez                  ");

        Andres.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Andres.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Andres.setText("Luis Andres Ramirez          ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        this.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(seleccion1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(280, 280, 280))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(reactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(robot, javax.swing.GroupLayout.DEFAULT_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wunpus, javax.swing.GroupLayout.DEFAULT_SIZE,  161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Deliverativo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(149, 149, 149))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Shaira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Andres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tema, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(seleccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(robot, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wunpus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reactivo)
                    .addComponent(Deliverativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(Shaira)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Andres)
                .addGap(56, 56, 56))
        );
        
        
    }
    private javax.swing.JLabel reactivo;
    private javax.swing.JLabel Andres;
    private javax.swing.JLabel Deliverativo;
    private javax.swing.JLabel Shaira;
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton robot;
    private javax.swing.JLabel seleccion1;
    private javax.swing.JLabel tema;
    private javax.swing.JButton wunpus;
    
}
