
package laboratorio3;


import java.awt.Color;
import java.util.TimerTask;
import java.util.Timer;
import CLIPSJNI.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Moverrobot extends Thread{
    
    
    private boolean continuar=true;
    
    Timer timer = new Timer();
    public panelrobot R;
    public Moverrobot(panelrobot r)
    {
        
        R=r;
        
    }
    public void detenrobot()
   {
      continuar=false;
      timer.purge();
      timer.cancel();
   }
    
    public void run()
    {
        
        timer.scheduleAtFixedRate(timerTask, 0, 300);
       
        
        while(continuar)
        {
            
        }
    }
    
    
     TimerTask timerTask = new TimerTask()
     {
         public void run() 
         {
            
             try {
                 R.mover();
             } catch (Exception ex) {
                 Logger.getLogger(Moverrobot.class.getName()).log(Level.SEVERE, null, ex);
             }
            
         }
     }; 
}
