/*
 Agente Fazendeiro
 */
package Main;

import static Main.GenericAgent.t;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FarmerOne extends GenericAgent{   
    @Override
    protected void setup() 
    {     
        t=new Tabuleiro();
        t.posInicial();
        addBehaviour( new walk( this ) );  
        
    }            
    class walk extends CyclicBehaviour 
    {   
        public walk(Agent a) { 
            super(a);             
        }
        @Override
        public void action() 
        {    
        int col,lin;
        boolean porcoVivo=true;
                
        try {
        frame.repaint(); 
        if(t.round==t.faz1){             
            Thread.sleep(1000);
            col=t.retornaColuna(t.faz1);
            lin=t.retornaLinha(t.faz1);
            t.moveFarmer(col,lin,t.faz1);          
            t.round=t.faz2;                 
        }
        }catch (InterruptedException ex){
            Logger.getLogger(FarmerOne.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }            
} 
        
    
    
    

