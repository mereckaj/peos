import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.symantec.itools.javax.swing.JToolBarSeparator;
import com.symantec.itools.javax.swing.icons.ImageIcon;
import com.symantec.itools.javax.swing.borders.TitledBorder;

/**
 * A basic JFC 1.1 based application.
 */
public class PEOS_Performer extends javax.swing.JFrame
{
	public PEOS_Performer()
	{
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.
		//{{INIT_CONTROLS
		setJMenuBar(JMenuBar1);
		setTitle("PEOS Performer");
		setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0,0));
		setSize(735,552);
		setVisible(false);
		saveFileDialog.setMode(FileDialog.SAVE);
		saveFileDialog.setTitle("Save");
		//$$ saveFileDialog.move(12,540);
		openFileDialog.setMode(FileDialog.LOAD);
		openFileDialog.setTitle("Open");
		//$$ openFileDialog.move(0,540);
		JPanel2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		getContentPane().add(BorderLayout.NORTH, JPanel2);
		JPanel2.setBounds(0,0,735,9);
		JToolBar1.setAlignmentY(0.222222F);
		JPanel2.add(JToolBar1);
		JToolBar1.setBounds(0,0,38,9);
		JToolBar1.add(JToolBarSeparator1);
		JToolBarSeparator1.setBounds(16,2,10,5);
		JToolBar1.add(JToolBarSeparator2);
		JToolBarSeparator2.setBounds(26,2,10,5);
		JPanel1.setLayout(null);
		getContentPane().add(BorderLayout.CENTER, JPanel1);
		JPanel1.setBounds(0,9,735,543);
		JPanel3.setBorder(titledBorder1);
		JPanel3.setOpaque(false);
		JPanel3.setLayout(null);
		JPanel1.add(JPanel3);
		JPanel3.setBounds(24,27,216,492);
		JcreateButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		JcreateButton.setText("Create");
		JcreateButton.setActionCommand("Create");
		JcreateButton.setMnemonic((int)'C');
		JPanel3.add(JcreateButton);
		JcreateButton.setBounds(48,444,73,25);
		JPanel3.add(JList1);
		JList1.setBounds(24,24,168,396);
		JPanel4.setBorder(titledBorder2);
		JPanel4.setOpaque(false);
		JPanel4.setLayout(null);
		JPanel1.add(JPanel4);
		JPanel4.setBounds(300,27,408,264);
		JrunButton.setText("Run");
		JrunButton.setActionCommand("jbutton");
		JrunButton.setMnemonic((int)'R');
		JPanel4.add(JrunButton);
		JrunButton.setBounds(156,228,84,25);
		JTree1.setAutoscrolls(true);
		JPanel4.add(JTree1);
		JTree1.setBounds(24,24,360,192);
		JPanel5.setBorder(titledBorder3);
		JPanel5.setOpaque(false);
		JPanel5.setLayout(null);
		JPanel1.add(JPanel5);
		JPanel5.setBounds(300,315,408,202);
		JDoneButton.setText("Done");
		JDoneButton.setActionCommand("jbutton");
		JDoneButton.setMnemonic((int)'D');
		JPanel5.add(JDoneButton);
		JDoneButton.setBounds(162,156,84,25);
		JrunrunButton.setText("Run");
		JrunrunButton.setActionCommand("jbutton");
		JrunrunButton.setMnemonic((int)'R');
		JPanel5.add(JrunrunButton);
		JrunrunButton.setBounds(60,156,84,25);
		JSuspendButton.setText("Suspend");
		JSuspendButton.setActionCommand("jbutton");
		JSuspendButton.setMnemonic((int)'S');
		JPanel5.add(JSuspendButton);
		JSuspendButton.setBounds(264,156,84,25);
		JPanel5.add(JTable1);
		JTable1.setBounds(24,24,360,120);
		//$$ JMenuBar1.move(132,576);
		fileMenu.setText("Connection");
		fileMenu.setActionCommand("File");
		fileMenu.setMnemonic((int)'C');
		JMenuBar1.add(fileMenu);
		connectItem.setText("Connect to Server");
		connectItem.setActionCommand("New");
		connectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.Event.CTRL_MASK));
		connectItem.setMnemonic((int)'C');
		fileMenu.add(connectItem);
		disconnectItem.setText("Disconnect");
		disconnectItem.setActionCommand("Open...");
		disconnectItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
		disconnectItem.setMnemonic((int)'D');
		fileMenu.add(disconnectItem);
		fileMenu.add(JSeparator1);
		exitItem.setText("Exit");
		exitItem.setActionCommand("Exit");
		exitItem.setMnemonic((int)'X');
		fileMenu.add(exitItem);
		helpMenu.setText("Help");
		helpMenu.setActionCommand("Help");
		helpMenu.setMnemonic((int)'H');
		JMenuBar1.add(helpMenu);
		aboutItem.setText("About...");
		aboutItem.setActionCommand("About...");
		aboutItem.setMnemonic((int)'A');
		helpMenu.add(aboutItem);
		titledBorder1.setTitle("List of Models");
		//$$ titledBorder1.move(180,576);
		titledBorder2.setTitle("List of Available Processes");
		//$$ titledBorder2.move(204,588);
		titledBorder3.setTitle("List of Running Processes");
		//$$ titledBorder3.move(240,600);
		//}}

		//{{INIT_MENUS
		//}}

		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		connectItem.addActionListener(lSymAction);
		disconnectItem.addActionListener(lSymAction);
		exitItem.addActionListener(lSymAction);
		aboutItem.addActionListener(lSymAction);
		JDoneButton.addActionListener(lSymAction);
		//}}
	}

    /**
     * Creates a new instance of JFrame1 with the given title.
     * @param sTitle the title for the new frame.
     * @see #JFrame1()
     */
	public PEOS_Performer(String sTitle)
	{
		this();
		setTitle(sTitle);
	}
	
	/**
	 * The entry point for this application.
	 * Sets the Look and Feel to the System Look and Feel.
	 * Creates a new JFrame1 and makes it visible.
	 */
	static public void main(String args[])
	{
		try {
		    // Add the following code if you want the Look and Feel
		    // to be set to the Look and Feel of the native system.
		    /*
		    try {
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (Exception e) { 
		    }
		    */

			//Create a new instance of our application's frame, and make it visible.
			(new PEOS_Performer()).setVisible(true);
		} 
		catch (Throwable t) {
			t.printStackTrace();
			//Ensure the application exits with an error condition.
			System.exit(1);
		}
	}

    /**
     * Notifies this component that it has been added to a container
     * This method should be called by <code>Container.add</code>, and 
     * not by user code directly.
     * Overridden here to adjust the size of the frame if needed.
     * @see java.awt.Container#removeNotify
     */
	public void addNotify()
	{
		// Record the size of the window prior to calling parents addNotify.
		Dimension size = getSize();
		
		super.addNotify();
		
		if (frameSizeAdjusted)
			return;
		frameSizeAdjusted = true;
		
		// Adjust size of frame according to the insets and menu bar
		javax.swing.JMenuBar menuBar = getRootPane().getJMenuBar();
		int menuBarHeight = 0;
		if (menuBar != null)
		    menuBarHeight = menuBar.getPreferredSize().height;
		Insets insets = getInsets();
		setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height + menuBarHeight);
	}

	// Used by addNotify
	boolean frameSizeAdjusted = false;

	//{{DECLARE_CONTROLS
	java.awt.FileDialog saveFileDialog = new java.awt.FileDialog(this);
	java.awt.FileDialog openFileDialog = new java.awt.FileDialog(this);
	javax.swing.JPanel JPanel2 = new javax.swing.JPanel();
	javax.swing.JToolBar JToolBar1 = new javax.swing.JToolBar();
	com.symantec.itools.javax.swing.JToolBarSeparator JToolBarSeparator1 = new com.symantec.itools.javax.swing.JToolBarSeparator();
	com.symantec.itools.javax.swing.JToolBarSeparator JToolBarSeparator2 = new com.symantec.itools.javax.swing.JToolBarSeparator();
	javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
	javax.swing.JPanel JPanel3 = new javax.swing.JPanel();
	javax.swing.JButton JcreateButton = new javax.swing.JButton();
	javax.swing.JList JList1 = new javax.swing.JList();
	javax.swing.JPanel JPanel4 = new javax.swing.JPanel();
	javax.swing.JButton JrunButton = new javax.swing.JButton();
	javax.swing.JTree JTree1 = new javax.swing.JTree();
	javax.swing.JPanel JPanel5 = new javax.swing.JPanel();
	javax.swing.JButton JDoneButton = new javax.swing.JButton();
	javax.swing.JButton JrunrunButton = new javax.swing.JButton();
	javax.swing.JButton JSuspendButton = new javax.swing.JButton();
	javax.swing.JTable JTable1 = new javax.swing.JTable();
	javax.swing.JMenuBar JMenuBar1 = new javax.swing.JMenuBar();
	javax.swing.JMenu fileMenu = new javax.swing.JMenu();
	javax.swing.JMenuItem connectItem = new javax.swing.JMenuItem();
	javax.swing.JMenuItem disconnectItem = new javax.swing.JMenuItem();
	javax.swing.JSeparator JSeparator1 = new javax.swing.JSeparator();
	javax.swing.JMenuItem exitItem = new javax.swing.JMenuItem();
	javax.swing.JMenu helpMenu = new javax.swing.JMenu();
	javax.swing.JMenuItem aboutItem = new javax.swing.JMenuItem();
	com.symantec.itools.javax.swing.borders.TitledBorder titledBorder1 = new com.symantec.itools.javax.swing.borders.TitledBorder();
	com.symantec.itools.javax.swing.borders.TitledBorder titledBorder2 = new com.symantec.itools.javax.swing.borders.TitledBorder();
	com.symantec.itools.javax.swing.borders.TitledBorder titledBorder3 = new com.symantec.itools.javax.swing.borders.TitledBorder();
	//}}

	//{{DECLARE_MENUS
	//}}

	void exitApplication()
	{
		try {
	    	// Beep
	    	Toolkit.getDefaultToolkit().beep();
	    	// Show a confirmation dialog
	    	int reply = JOptionPane.showConfirmDialog(this, 
	    	                                          "Do you really want to exit?", 
	    	                                          "JFC Application - Exit" , 
	    	                                          JOptionPane.YES_NO_OPTION, 
	    	                                          JOptionPane.QUESTION_MESSAGE);
			// If the confirmation was affirmative, handle exiting.
			if (reply == JOptionPane.YES_OPTION)
			{
		    	this.setVisible(false);    // hide the Frame
		    	this.dispose();            // free the system resources
		    	System.exit(0);            // close the application
			}
		} catch (Exception e) {
		}
	}

	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == PEOS_Performer.this)
				PEOS_Performer_windowClosing(event);
		}
	}

	void PEOS_Performer_windowClosing(java.awt.event.WindowEvent event)
	{
		// to do: code goes here.
			 
		PEOS_Performer_windowClosing_Interaction1(event);
	}

	void PEOS_Performer_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == disconnectItem)
				disconnectItem_actionPerformed(event);
			if (object == exitItem)
				exitItem_actionPerformed(event);
			else if (object == aboutItem)
				aboutItem_actionPerformed(event);
			else if (object == connectItem)
				connectItem_actionPerformed(event);
			else if (object == JDoneButton)
				JDoneButton_actionPerformed(event);
			
		}
	}

	void disconnectItem_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
	}

	void exitItem_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
			 
		exitItem_actionPerformed_Interaction1(event);
	}

	void exitItem_actionPerformed_Interaction1(java.awt.event.ActionEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	void aboutItem_actionPerformed(java.awt.event.ActionEvent event)
	{
		try {
			// JAboutDialog Create with owner and show as modal
			{
				JAboutDialog JAboutDialog = new JAboutDialog(this);
				JAboutDialog.setModal(true);
				JAboutDialog.show();
			}
		} catch (Exception e) {
		}
		// to do: code goes here.
	}

	void connectItem_actionPerformed(java.awt.event.ActionEvent event)
	{
		try {
			// JAboutDialog Create with owner and show as modal
			{
				JLoginDialog JLoginDialog1 = new JLoginDialog(this);
				JLoginDialog1.setModal(true);
				JLoginDialog1.show();
			}
		} catch (Exception e) {
		}
			 
			 
			 
	}

	void JDoneButton_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
	}
}