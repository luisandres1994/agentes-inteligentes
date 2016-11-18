/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio3;

import java.awt.Color;
import java.util.TimerTask;
import java.util.Timer;
import CLIPSJNI.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Usuario
 */
public class a_estrella  extends Thread{
    
    public panelwumpus p;
    
    public a_estrella(panelwumpus p1)
    {
        p=p1;
    }
    
    public void run()
    {
        
        try {
            PriorityQueue<Nodo> Q= new PriorityQueue<>(1,new Comparator<Nodo>() {
                public int compare(Nodo n1, Nodo n2) {
                    return (int) (n1.costo - n2.costo); }});
            boolean encontro,murio,fin=false;
            int costo=0,direccion=0;
            encontro=murio=false;
            Nodo aux;
            int distancia;
            String evaluar;
            int cont;
            cont=0;
            Q.add(new Nodo(10000,0,encontro,murio));
            PrimitiveValue value;
              while(!Q.isEmpty() && !fin && !murio && cont<100)
             {
            p.clips=new Environment();
            p.clips.load("wumpus.clp");
            System.out.println(cont);
            distancia=Math.abs((p.exploradorx-1)-p.tesorox) + Math.abs(p.exploradory-p.tesoroy);
            p.clips.run();
            p.clips.eval("(assert(percepcion "
                    + "(elemento "+ p.bMatriz[p.exploradorx-1][p.exploradory].getName()+")"
                    + "(direccion 1 )"
                    + "(distancia "+Integer.toString(distancia)+ ")))");
            p.clips.run();
            evaluar ="(find-all-facts ((?m heuristica)) TRUE)";
            value=p.clips.eval(evaluar);
            costo= Integer.parseInt(value.get(0).getFactSlot("costo").toString());
            direccion= Integer.parseInt(value.get(0).getFactSlot("movimiento").toString());
            encontro= (Integer.parseInt(value.get(0).getFactSlot("encontro").toString()) != 0);
            if(encontro) fin=true;
            murio= (Integer.parseInt(value.get(0).getFactSlot("murio").toString()) != 0);
            Q.add(new Nodo(costo,direccion,encontro,murio));
            p.clips.reset();
            distancia=Math.abs(p.exploradorx-p.tesorox)+Math.abs((p.exploradory+1)-p.tesoroy);
            p.clips.eval("(assert(percepcion "
                    + "(elemento "+ p.bMatriz[p.exploradorx][p.exploradory+1].getName()+")"
                    + "(direccion 2 )"
                    + "(distancia "+Integer.toString(distancia)+ ")))");
            p.clips.run();
            evaluar ="(find-all-facts ((?m heuristica)) TRUE)";
            value=p.clips.eval(evaluar);
            costo= Integer.parseInt(value.get(0).getFactSlot("costo").toString());
            direccion= Integer.parseInt(value.get(0).getFactSlot("movimiento").toString());
            encontro= (Integer.parseInt(value.get(0).getFactSlot("encontro").toString()) != 0);
            if(encontro) fin=true;
            murio= (Integer.parseInt(value.get(0).getFactSlot("murio").toString()) != 0);
            Q.add(new Nodo(costo,direccion,encontro,murio));
            p.clips.reset();
            distancia=Math.abs((p.exploradorx+1)-p.tesorox)+Math.abs(p.exploradory-p.tesoroy);
            p.clips.eval("(assert(percepcion "
                    + "(elemento "+ p.bMatriz[p.exploradorx+1][p.exploradory].getName()+")"
                    + "(direccion 3 )"
                    + "(distancia "+Integer.toString(distancia)+ ")))");
            p.clips.run();
            evaluar ="(find-all-facts ((?m heuristica)) TRUE)";
            value=p.clips.eval(evaluar);
            costo= Integer.parseInt(value.get(0).getFactSlot("costo").toString());
            direccion= Integer.parseInt(value.get(0).getFactSlot("movimiento").toString());
            encontro= (Integer.parseInt(value.get(0).getFactSlot("encontro").toString()) != 0);
            if(encontro) fin=true;
            murio= (Integer.parseInt(value.get(0).getFactSlot("murio").toString()) != 0);
            Q.add(new Nodo(costo,direccion,encontro,murio));
            p.clips.reset();
            distancia=Math.abs((p.exploradorx)-(p.tesorox))+Math.abs((p.exploradory-1)-p.tesoroy);
            p.clips.eval("(assert(percepcion "
                    + "(elemento "+ p.bMatriz[p.exploradorx][p.exploradory-1].getName()+")"
                    + "(direccion 4 )"
                    + "(distancia "+Integer.toString(distancia)+ ")))");
            p.clips.run();
            evaluar ="(find-all-facts ((?m heuristica)) TRUE)";
            value=p.clips.eval(evaluar);
            costo= Integer.parseInt(value.get(0).getFactSlot("costo").toString());
            direccion= Integer.parseInt(value.get(0).getFactSlot("movimiento").toString());
            encontro= (Integer.parseInt(value.get(0).getFactSlot("encontro").toString()) != 0);
            if(encontro) fin=true;
            murio= (Integer.parseInt(value.get(0).getFactSlot("murio").toString()) != 0);
            Q.add(new Nodo(costo,direccion,encontro,murio));
            p.clips.reset();
             try {
            Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            aux=Q.poll();
            p.bMatriz[p.exploradorx][p.exploradory].setIcon(null);
            p.exploradorx+=p.movimientos[0][aux.movimiento];
            p.exploradory+=p.movimientos[1][aux.movimiento];
            p.bMatriz[p.exploradorx][p.exploradory].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/explorador.jpg")));
            cont++;
            
        }
        } catch (Exception ex) {
            Logger.getLogger(a_estrella.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        
        
    }
    
}
