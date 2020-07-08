/* Ambiente do Tabuleiro */

package Main;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Tabuleiro extends JPanel{     

int numLinhas=5;
int numColunas=5;
int matriz[][]= new int[numLinhas][numColunas];
int tickMaximo=80;
int numTick=0;
int faz1=1;
int faz2=2;
int faz3=3;
int pig=9;
int round=pig;
ArrayList<Integer> farmers = new ArrayList();
 
    public void posInicial() {
       int x,y;
        
        // lista dos fazendeiros
        farmers.add(faz1);
        farmers.add(faz2);
        farmers.add(faz3);
        // eixo [x][y] 4 blocos
        matriz[1][1]=8;
        matriz[3][1]=8;
        matriz[1][3]=8;
        matriz[3][3]=8; 
        

        /*//pos inicial pré definida 
        matriz[4][2]=faz1;
        matriz[4][0]=faz2;
        matriz[4][1]=faz3;
        matriz[4][4]=pig;
        */
        ///*

        //pos inicial dos agentes
       

        do{
            x = (int )(Math.random() * 4 + 0);
            y = (int )(Math.random() * 4 + 0);  
        }while(matriz[x][y]!=0);
        matriz[x][y]=faz1;
        do{
            x = (int )(Math.random() * 4 + 0);
            y = (int )(Math.random() * 4 + 0);  
        }while(matriz[x][y]!=0);        
        matriz[x][y]=faz2;
       /* do{
            x = (int )(Math.random() * 4 + 0);
            y = (int )(Math.random() * 4 + 0);  
        }while(matriz[x][y]!=0);
        matriz[x][y]=faz3;*/
        do{
            x = (int )(Math.random() * 4 + 0);
            y = (int )(Math.random() * 4 + 0);  
        }while(matriz[x][y]!=0);        

        matriz[x][y]=pig; 
        //*/

        matriz[x][y]=pig;         

        try{
        Thread.sleep(2000);
        }catch(InterruptedException ex) {}
    }
    public double calculaDistancia(int x, int x2, int y, int y2){
        double dist, p1, p2;    
        p1= Math.pow(x2-x, 2);
        p2= Math.pow(y2-y, 2);
        dist= Math.sqrt(p1+p2);
        return dist;
    }
    public void moveUp(int x,int y,int agentId){
        if(matriz[x][y-1]==0 || matriz[x][y-1]==pig ){
        matriz[x][y-1]=agentId; 
        matriz[x][y]=0;      
        }
    }
    public void moveDown(int x,int y, int agentId){

        if(matriz[x][y+1]==0 || matriz[x][y+1]==pig ){
            matriz[x][y+1]=agentId; 
            matriz[x][y]=0;                
        }     
    }
    public void moveLeft(int x,int y, int agentId){
        
        if(matriz[x-1][y]==0 || matriz[x-1][y]==pig ){
            matriz[x-1][y]=agentId;
            matriz[x][y]=0;        
        }
    }
    public void moveRight(int x,int y, int agentId){
        if(matriz[x+1][y]==0 || matriz[x+1][y]==pig ){
            matriz[x+1][y]=agentId;
            matriz[x][y]=0;         
        }
    }

    public void moveFarmer(int x, int y, int agentId){

    double left=99,right=99,up=99,down=99;
    int x_porco,y_porco;

    x_porco=retornaColuna(pig);
    y_porco=retornaLinha(pig);  

        if(x!=0 && y!=1 && y!=3 ){
            left=calculaDistancia(x-1,x_porco,y,y_porco);        
        }
        if(x!=4 && y!=1 && y!=3){
            right=calculaDistancia(x+1,x_porco,y,y_porco);
        }
        if(x!=1 && x!=3 && y!=0){
            up=calculaDistancia(x,x_porco,y-1,y_porco);
        }
        if(y!=4 && x!=1 && x!=3){
            down=calculaDistancia(x,x_porco,y+1,y_porco);
        }
       
        if(down<=up && down<=right && down<=left){
            if((x==0 && y==2) || ((x==2 && y==2) && x_porco>2)){
                if(farmers.contains(matriz[x][y+1])||( farmers.contains(matriz[x][y+2])&& matriz[x][y+1]!=pig )){
                    moveRight(x,y,agentId);
                }
                else moveDown(x,y,agentId);
            }else{
                if((x==4 && y==2)||((x==2 && y==2) && x_porco<2)){
                    if((farmers.contains(matriz[x][y+1]) ||(farmers.contains(matriz[x][y+2]) && matriz[x][y+1]!=pig))){                          
                        moveLeft(x,y,agentId);
                    }else moveDown(x,y,agentId);
                }else moveDown(x,y,agentId);                    
            }
        }
        else{
            if(up<=right && up<=left){
                if((x==0 && y==2) || ((x==2 && y==2) && x_porco>2)){
                    if(farmers.contains(matriz[x][y-1])||(farmers.contains(matriz[x][y-2]) && matriz[x][y-1]!=pig )){
                        moveRight(x,y,agentId);
                    }
                    else moveUp(x,y,agentId);
                }else{
                    if((x==4 && y==2)||((x==2 && y==2) && x_porco<2)){
                        if(farmers.contains(matriz[x][y-1]) ||( farmers.contains(matriz[x][y-2]) && matriz[x][y-1]!=pig)){                          
                            moveLeft(x,y,agentId);
                        }else moveUp(x,y,agentId);
                    }else moveUp(x,y,agentId);

                }    
            }
            else{
                if(right<=left){
                    if((x==2 && y==0) || ((x==2 && y==2) && y_porco>2)){
                        if(farmers.contains(matriz[x+1][y])||(farmers.contains(matriz[x+2][y]) && matriz[x+1][y]!=pig )){
                            moveDown(x,y,agentId);
                        }
                        else moveRight(x,y,agentId);
                    }else{
                        if((x==2 && y==4)||((x==2 && y==2) && y_porco<2)){
                            if(farmers.contains(matriz[x+1][y]) ||( farmers.contains(matriz[x+2][y]) && matriz[x+1][y]!=pig)){                          
                                moveUp(x,y,agentId);
                            }else moveRight(x,y,agentId);
                        }else moveRight(x,y,agentId);
                    }    
                }//left<right
                else{
                    if((x==2 && y==0) || ((x==2 && y==2) && y_porco>2)){
                        if(farmers.contains(matriz[x-1][y])||(farmers.contains(matriz[x-2][y]) && matriz[x-1][y]!=pig )){
                            moveDown(x,y,agentId);
                        }
                        else moveLeft(x,y,agentId);
                    }else{
                        if((x==2 && y==4)||((x==2 && y==2) && y_porco<2)){
                            if(farmers.contains(matriz[x-1][y])||( farmers.contains(matriz[x-2][y]) && matriz[x-1][y]!=pig)){                          
                                moveUp(x,y,agentId);
                            }else moveLeft(x,y,agentId);
                        }else moveLeft(x,y,agentId);
                    }                       
                }
            }
        }     
    }
   public void movePig(int x, int y, int agentId){
    int f=numFaz();
    double  leftTotal=0,rightTotal=0,upTotal=0,downTotal=0;      
    double left[]= new double[f], right[]= new double[f], down[]= new double[f], up[]= new double[f]; 
          
        if(x!=0 && y!=1 && y!=3  ){
            for(int i=0;i<f;i++){
                left[i]=calculaDistancia(x-1,retornaColuna(i+1),y,retornaLinha(i+1));
            }
        }
        if(x!=4 && y!=1 && y!=3  ){  
            for(int i=0;i<f;i++){
                right[i]=calculaDistancia(x+1,retornaColuna(i+1),y,retornaLinha(i+1));
            }
        }
        if(x!=1 && x!=3 && y!=0 ){
            for(int i=0;i<f;i++){
                up[i]=calculaDistancia(x,retornaColuna(i+1),y-1,retornaLinha(i+1));
            }
        }
        if(y!=4 && x!=1 && x!=3 ){
            for(int i=0;i<f;i++){
                down[i]=calculaDistancia(x,retornaColuna(i+1),y+1,retornaLinha(i+1));
            }
        } 
        leftTotal=soma(left);
        rightTotal=soma(right);
        downTotal=soma(down);
        upTotal=soma(up);
        
        //System.out.println("left:"+leftTotal+",right:"+rightTotal+",up:"+upTotal+"down:"+downTotal);
        if(downTotal>=upTotal && downTotal>rightTotal && downTotal>leftTotal){
            moveDown(x,y,agentId);
        }
        else{
            if(upTotal>rightTotal && upTotal>leftTotal){
                moveUp(x,y,agentId);
            }
            else{
                if(rightTotal>leftTotal ){
                    moveRight(x,y,agentId);
                }
                else{
                    if(leftTotal>0){
                        moveLeft(x,y,agentId);
                    }
                }                             
            }
        }   
    }
   
    public double soma(double vet[]){
		double soma = 0;
		for(int i = 0; i<vet.length; i++){
			soma += vet[i];
		}
		return soma;
    }
    public int numFaz(){
        int f=0;
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++){
                if(farmers.contains(matriz[col][lin])){
                    f++;                    
                }
            }
        }
    return(f);
    }
    public boolean checaPorco(){
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++){
                if(matriz[col][lin]==pig){
                    return true;
                }
            }
        }
    return false;
    }
    
    public void printTabuleiro() {
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++)
                System.out.print(" "+matriz[col][lin]);
            System.out.print("\n");
        }
    }
    public int retornaLinha(int n) {
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++)
                if(n==matriz[col][lin]){
                    return(lin);
                }            
        }
        return 0;
    }
    public int retornaColuna(int n) {
        for (int lin=0;lin<numLinhas;lin++){
            for (int col=0;col<numColunas;col++)
                if(n==matriz[col][lin]){
                    return(col);
                }            
        }
        return 0;
    }

    public void paint(Graphics g){

    int x, y;     
   
    g.clearRect(100, 100, 250,250); 
    g.drawLine(100, 150, 350, 150);
    g.drawLine(100, 200, 350, 200);
    g.drawLine(100, 250, 350, 250);
    g.drawLine(100, 300, 350, 300);

    g.drawLine(150, 100, 150, 350 );
    g.drawLine(250, 100, 250, 350 );
    g.drawLine(200, 100, 200, 350 );
    g.drawLine(300, 100, 300, 350 );

    g.fillRect(250, 150, 50, 50);
    g.fillRect(150, 150, 50, 50);
    g.fillRect(150, 250, 50, 50);
    g.fillRect(250, 250, 50, 50);

    Image pig = new ImageIcon(this.getClass().getResource("/images/porco.png")).getImage();
    Image f1 = new ImageIcon(this.getClass().getResource("/images/ze.png")).getImage();
    Image f2 = new ImageIcon(this.getClass().getResource("/images/ze2.png")).getImage();

        for (int col=0;col<numColunas;col++){
            for (int lin=0;lin<numLinhas;lin++){
                if(farmers.contains(matriz[col][lin])){
                    x=col*50;
                    y=lin*50;
                    g.drawImage(f2,105+x,102+y,null);                                  
                }
                if(matriz[col][lin]==this.pig){
                    x=col*50;
                    y=lin*50;
                    g.drawImage(pig,105+x,107+y,null);                   
                }
            }
        }
    }    
}
