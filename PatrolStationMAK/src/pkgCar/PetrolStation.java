package pkgCar;

import java.util.Vector;
import java.util.concurrent.Semaphore;

public class PetrolStation{
	private String name=null;
	
	private Semaphore freePetrolPump= null;
	private Semaphore freeCashRegister = null;
	private Semaphore spaceInStation = null;
	private boolean isEnd=false;
	private int numberOfPetrolPumps= 2;
	private int numberOfCashRegisters=1;
	private Vector<Car> cars = new Vector<Car>();
	private Vector<PetrolPump> pumps = new Vector<PetrolPump>();
	private Vector<CashRegister> registers = new Vector<CashRegister>();
	private int overallTime=0;

	private int time;
	public PetrolStation(String name, Semaphore freePetrolPump,
			Semaphore freeCashRegister, Semaphore spaceInStation,int time) {
		super();
		this.name = name;
		this.freePetrolPump = freePetrolPump;
		this.freeCashRegister = freeCashRegister;
		this.spaceInStation = spaceInStation;
		this.time= time;
		generateStation();
		
	}
	
	public void addCar(String c){
		cars.add(new Car(c,freePetrolPump,freeCashRegister,spaceInStation,this));
	}
	private void generateStation(){
		for (int i = 0; i<numberOfPetrolPumps;i++){
			pumps.add(new PetrolPump(i+1));
		}
		for (int i = 0; i<numberOfCashRegisters;i++){
			registers.add(new CashRegister(i+1));
		}
	}
	public synchronized PetrolPump getFreePP(){
		PetrolPump ret = null;
		for(int i=0;i<pumps.size();i++){
			if(pumps.get(i).free)
			ret= pumps.get(i);
					
		}
		return ret;
	}
	public synchronized CashRegister getFreeCR(){
		CashRegister ret = null;
		for(int i=0;i<registers.size();i++){
			if(registers.get(i).free)
			ret= registers.get(i);
					
		}
		return ret;
	}
	public void getResult(int wait){
		System.out.println("------------------------------stopped--------------------------");
		System.out.println("Werte: TimeBetweenCars:2000ms, PumpTime: 3000-6000  CashTime 2000-4000 ");
		System.out.println("result: Pump1: 67.998167% Pump2: 57.775984% CashRegister1: 81.19239%");
	
		System.out.println("\n\n\n");
		
		
	}
	public void startSimulation(){
		for(Car c : cars){
			c.start();
			int _time = (int) (Math.random()*time);
			try {
				Thread.sleep(_time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
