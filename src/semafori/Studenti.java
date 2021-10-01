package semafori;

import java.util.concurrent.Semaphore;

public class Studenti implements Runnable {
	
	public static void main(String[] args) {
		Studenti s = new Studenti();
		Thread t = new Thread(s);
		t.start();
	}
	
	private Semaphore m = new Semaphore(1);
	private int persone = 0;
	public void run(){
		while(true){
			
			try{
				m.acquire();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			while(persone <= 4){
				if(persone == 0){
					Ingresso("Il Prof",0);
					try{
						Thread.sleep(1000);
					} catch(InterruptedException e){
						e.printStackTrace();
					}
					persone++;
				}else{
					Ingresso("Lo studente", persone);
					try{
						Thread.sleep(1000);
					} catch(InterruptedException e){
						e.printStackTrace();
					}
					persone++;
				}
			}
			
			m.release();
			Uscita();
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void Ingresso(String s, int n){
		if(n == 0){
			
			System.out.println(s+" sta entrando in classe ");
			
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println(s+" è entrato ");
			System.out.println("");
		
		} else if(n > 0){
				
				System.out.println(s+" "+n+" sta entrando in classe ");
				
				try{
					Thread.sleep(1000);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
				
				System.out.println(s+" "+n+" è entrato ");
				System.out.println("");
		}
	}
	
	public void Uscita(){
		persone = 0;
		System.out.println("Gli studenti sono usciti col professore ");
		System.out.println("");
	}
}
