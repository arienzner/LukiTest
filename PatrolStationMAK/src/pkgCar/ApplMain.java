package pkgCar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ApplMain extends javax.swing.JFrame implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblMessage;
	private JTextField lblNumberOfCars;
	private JButton btnStart;
	private JTextField txtNumberOfCars;
	private JTextField txtTimeBetween;
	private JLabel lblTimeBetween;
	
	private PetrolStation station;
	/**
	 * other attributes (for simulation)
	 */
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ApplMain inst = new ApplMain();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public ApplMain() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Simulation Petrol Station V1");
			getContentPane().add(getLblMessage());
			getContentPane().add(getLblTimeBetween());
			getContentPane().add(getTxtTimeBetween());
			getContentPane().add(getLblNumberOfCars());
			getContentPane().add(getTxtNumberOfCars());
			getContentPane().add(getBtnStart());
			pack();
			this.setSize(503, 256);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	private JLabel getLblMessage() {
		if(lblMessage == null) {
			lblMessage = new JLabel();
			lblMessage.setText("...");
			lblMessage.setBounds(12, 192, 463, 16);
			lblMessage.setBackground(new java.awt.Color(255,255,128));
			lblMessage.setOpaque(true);
		}
		return lblMessage;
	}

	private JLabel getLblTimeBetween() {
		if(lblTimeBetween == null) {
			lblTimeBetween = new JLabel();
			lblTimeBetween.setText("time between next car (msec):");
			lblTimeBetween.setBounds(29, 45, 202, 16);
			lblTimeBetween.setBackground(new java.awt.Color(192,192,192));
			lblTimeBetween.setOpaque(true);
		}
		return lblTimeBetween;
	}

	private JTextField getTxtTimeBetween() {
		if(txtTimeBetween == null) {
			txtTimeBetween = new JTextField();
			txtTimeBetween.setText("2000");
			txtTimeBetween.setBounds(254, 42, 59, 22);
			txtTimeBetween.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return txtTimeBetween;
	}

	private JTextField getLblNumberOfCars() {
		if(lblNumberOfCars == null) {
			lblNumberOfCars = new JTextField();
			lblNumberOfCars.setText("number of cars:");
			lblNumberOfCars.setBounds(29, 82, 202, 22);
			lblNumberOfCars.setBackground(new java.awt.Color(192,192,192));
		}
		return lblNumberOfCars;
	}

	private JTextField getTxtNumberOfCars() {
		if(txtNumberOfCars == null) {
			txtNumberOfCars = new JTextField();
			txtNumberOfCars.setText("20");
			txtNumberOfCars.setBounds(254, 82, 59, 22);
			txtNumberOfCars.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return txtNumberOfCars;
	}

	private JButton getBtnStart() {
		if(btnStart == null) {
			btnStart = new JButton();
			btnStart.setText("Start");
			btnStart.setBounds(240, 140, 73, 22);
			btnStart.addActionListener(this);
		}
		return btnStart;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnStart){
			generateSubjects(Integer.parseInt(txtNumberOfCars.getText()), Integer.parseInt(txtTimeBetween.getText()));
			startTest();
			int wait = Integer.parseInt(txtTimeBetween.getText())*19;
			try {
				Thread.sleep(wait);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			station.getResult(wait);
		}
	}

	private void startTest() {
	station.startSimulation();
	}
	private void generateSubjects(int numOfCars,int time){

		Semaphore semaFreePetrolPupmp= new Semaphore(2);
		Semaphore semaFreeCashRegister = new Semaphore(1);
		Semaphore semaSpaceInStation = new Semaphore(3);
		station= new PetrolStation("ESSO",  semaFreePetrolPupmp, semaFreeCashRegister, semaSpaceInStation,time);
		for(int i = 0; i<numOfCars;i++){
			station.addCar("Driver "+(i+1));
		}
	}


}
