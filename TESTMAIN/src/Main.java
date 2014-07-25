import java.awt.Rectangle;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.pushingpixels.trident.swt.SWTToolkitHandler;
import org.eclipse.swt.custom.ScrolledComposite;


public class Main {

	protected Shell shell;
	private GC gc;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(835, 555);
		shell.setText("SWT Application");
		
		
		Button btnDraw = new Button(shell, SWT.NONE);
		btnDraw.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.graphics.Rectangle r = new org.eclipse.swt.graphics.Rectangle(200, 50, 100, 20);
				gc.dispose();
				gc.drawRectangle(r);
				gc.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
				gc.dispose();
			}
		});
		btnDraw.setBounds(57, 10, 75, 25);
		btnDraw.setText("DRAW");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(132, 40, 414, 300);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawRectangle(500, 500, 20, 30);
				e.gc.drawRectangle(10, 10, 20, 20);
			
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setItemCount(10);
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(600,600));
		
		fillTable();
	}

	private void fillTable() {
		
	}
}
