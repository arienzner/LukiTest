package pkgCar;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class Car extends Thread {
private String name=null;
private Semaphore carWaiting= null;
private Semaphore freePetrolPump= null;
private Semaphore freeCashRegister = null;
private Semaphore spaceInStation = null;
private PetrolStation station = null;
private CashRegister cr = null;
private PetrolPump pp = null;

private boolean isEnd=false;
private int time;
long pumpWaitingTime;
long runningTime ;

public long getPumpWaitingTime() {
	return pumpWaitingTime;
}



public long getRunningTime() {
	return runningTime;
}



public Car(String name, Semaphore freePetrolPump,
		Semaphore freeCashRegister, Semaphore spaceInStation,PetrolStation station) {
	super();
	this.name = name;

	this.freePetrolPump = freePetrolPump;
	this.freeCashRegister = freeCashRegister;
	this.spaceInStation = spaceInStation;
	this.station= station;
}

public void run() {
	while (!isEnd) {
		try {
			long start = new Date().getTime();
			spaceInStation.acquire();
			
			System.out.println(this.name +" drives to station");
			System.out.println(this.name + " in station");
			System.out.println(this.name + " now waiting for free pump");
			long pumpWaitStart = new Date().getTime(); 
			freePetrolPump.acquire();
		    pumpWaitingTime = new Date().getTime() - pumpWaitStart; 
			station.getFreePP().getFuel();
			freePetrolPump.release();
			System.out.println(this.name + "waits for cash register");
			spaceInStation.release();
			freeCashRegister.acquire();
			station.getFreeCR().getPayment();
			freeCashRegister.release();
			runningTime = new Date().getTime() - start; 
			
			System.out.println(this.name + " needed: "+runningTime);
			this.setEnd();
		}
		catch (Exception _ex) {
			_ex.printStackTrace();
			System.err.println("customer " + name + ": error " + _ex.getMessage());
			//_ex.printStackTrace();
		}
	}
	System.out.println("customer " + name + " finished ");
}

public void setEnd(){
	isEnd=true;
}
public String getNameOfCar() {
	return name;
}
public void setNameOfCar(String name) {
	this.name = name;
}

}
