package pkgCar;

public class PetrolPump extends Thread{
int num;
int min = 2000;
int max = 5000;
boolean free= true;

public PetrolPump(int num) {
	super();
	this.num = num;
}
public synchronized void getFuel() throws InterruptedException{
	System.out.println("Pump "+num+" starts filling");
	this.free=false;
	Thread.sleep((long) (Math.random()*(max - min ) + min));
	System.out.println("Pump "+num+" ends filling");
	this.free=true;
}
public synchronized boolean getFree(){
	return this.free;
}
@Override
public String toString() {
	return "PetrolPump " + num ;
}

}
