package pkgCar;

public class CashRegister extends Thread{
int num;
boolean free= true;
private int max=2000;
private int min=4000;

public CashRegister(int num) {
	super();
	this.num = num;
}
public synchronized void getPayment() throws InterruptedException{
	System.out.println("CashRegister "+num+" starts payment");
	this.free=false;
	Thread.sleep((long) (Math.random()*(max - min ) + min));
	System.out.println("CashRegister "+num+" ends payment");
	this.free=true;
}
public synchronized boolean getFree(){
	return this.free;
}
@Override
public String toString() {
	return "CashRegister " + num ;
}

}
