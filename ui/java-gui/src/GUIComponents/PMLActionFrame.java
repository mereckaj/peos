package GUIComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

import PML.*;
import Utils.*;

///////////////////////////////////////////////////////////////////////////////
// Class Description:                                                        //
//                                                                           //
// This class is a frame which is used to display an action that the user    //
// has selected to run.                                                      //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
public class PMLActionFrame extends JFrame
{  

  public PMLActionFrame(PMLController cntrl, PMLAction action)
  {  
    MainController = cntrl;
    ActionForFrame = action;
    setTitle(action.GetName());
    setSize(300, 300);

    Container contentPane = getContentPane();

    // Create the button panel for the frame.
    JPanel buttonPanel = CreateButtonPanel();

    // Display the action based on its type.
    if (action.GetType().equals("action") ) {
      // Action has type action.

      // Display the fields in the action (i.e. requires, agent) in a panel
      JPanel fieldsPanel = CreateFieldsPanel();

      // Display the script for the action in a scroll pane.
      JScrollPane scriptPane = CreateScriptPane();
   
      // Add the fields panel and script pane to the content pane for the
      // action frame. 
      contentPane.add(fieldsPanel, "North");
      contentPane.add(scriptPane, "Center");

    } else {

      // Currently commands like Process actions can contain control structures
      // in there action lists.  For example when an available commands is
      // done on the current PML Kernel it could contain the following:
      //
      // 100-martini.006 twist_or_olive type selection children ( prepare_twist prepare_olive )
      //
      // This section of the code displays these type of actions.  Professor
      // Noll does not believe that the kernel should be returning information
      // in this fashion.  This code may need to be modified or removed in
      // the future.

      String panelLabelStr = "Action Type is: " + action.GetType();
      JLabel panelLabel = new JLabel(panelLabelStr);

      JPanel childrenPanel = CreateChildrenPanel();

      contentPane.add(panelLabel, "North");
      contentPane.add(childrenPanel, "Center");

    }

    // Add the button panel to the action frames content pane. 
    contentPane.add(buttonPanel, "South");
  }

  /////////////////////////////////////////////////////////////////////////////
  //  This function creates a list compenent which displays the children for //
  //  an action which is a sequence, selection or branch.  It puts the list  //
  //  in a panel and returns that panel.                                     //
  //                                                                         //
  /////////////////////////////////////////////////////////////////////////////
  private JPanel CreateChildrenPanel()
  {
    // Create the model for the list box.
    DefaultListModel childrenListModel = new DefaultListModel();
    
    // Create the list box. 
    JList list = new JList(childrenListModel);

    // Put the children in the list box.
    for (Iterator i = ActionForFrame.GetChildren().iterator(); i.hasNext();) {
      String nextStr = (String) i.next();
      childrenListModel.addElement(nextStr);
    }

    // Put the list box in a scroll pane so list appears in a scrolling fashion.
    JScrollPane listScroller = new JScrollPane(list);
    listScroller.setPreferredSize(new Dimension(250, 80));
    //XXX: Must do the following, too, or else the scroller thinks
    //XXX: it's taller than it is:
    listScroller.setMinimumSize(new Dimension(250, 80));
    listScroller.setAlignmentX(LEFT_ALIGNMENT);

   
    // Create a panel to hold the scroll pane 
    JPanel childPane = new JPanel();

    childPane.setLayout(new BoxLayout(childPane, BoxLayout.Y_AXIS));

    // Create label to display over the list.
    String listPaneStr = "Children for " + ActionForFrame.GetType();
    JLabel label = new JLabel(listPaneStr);
    label.setLabelFor(list);

    // Add the label to the child pane.
    childPane.add(label);
    childPane.add(Box.createRigidArea(new Dimension(0,5)));
    
    // add the scroll pane to the child pane.
    childPane.add(listScroller);

    childPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    return childPane;

  }

  /////////////////////////////////////////////////////////////////////////////
  //  This function creates a panel to display the fields of an action.  It  //
  //  displays the field names in one column with the values for the fields  //
  //  in another column.                                                     //
  //                                                                         //
  /////////////////////////////////////////////////////////////////////////////
  private JPanel CreateFieldsPanel()
  {
    String requires = ActionForFrame.GetFieldValue("requires");
    String agent = ActionForFrame.GetFieldValue("agent");
    String provides = ActionForFrame.GetFieldValue("provides");
    String tool = ActionForFrame.GetFieldValue("tool");

    //Create the labels.
    JLabel requiresLabel = new JLabel("Requires:");
    JLabel providesLabel = new JLabel("Provides:");
    JLabel agentLabel = new JLabel("Agent:");

    ActionTextField requiresField = ActionTextField.CreateField(requires);
    ActionTextField providesField = ActionTextField.CreateField(provides);
    ActionTextField agentField = ActionTextField.CreateField(agent);

    //Layout the labels in a panel.
    JPanel labelPane = new JPanel();
    labelPane.setLayout(new GridLayout(0, 1));
    labelPane.add(requiresLabel);
    labelPane.add(providesLabel);
    labelPane.add(agentLabel);

    //Layout the text fields in a panel.
    JPanel fieldPane = new JPanel();
    fieldPane.setLayout(new GridLayout(0, 1));
    fieldPane.add(requiresField);
    fieldPane.add(providesField);
    fieldPane.add(agentField);

    //Put the panels in another panel, labels on left,
    //text fields on right.
    JPanel contentPane = new JPanel();
    contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    contentPane.setLayout(new BorderLayout());
    contentPane.add(labelPane, BorderLayout.CENTER);
    contentPane.add(fieldPane, BorderLayout.EAST);

    return contentPane;
  }

  /////////////////////////////////////////////////////////////////////////////
  //  This function creates a panel which contains the buttons for the action//
  //  frame.  It contains two buttons a Done button and a Suspend button.    //
  //                                                                         //
  /////////////////////////////////////////////////////////////////////////////
  private JPanel CreateButtonPanel()
  {
    // Create a panel for the buttons.
    JPanel panel = new JPanel();

    // Create the buttons.
    JButton doneButton = new JButton("Done");
    JButton suspendButton = new JButton("Suspend");

    // Add an ActionDone action listener to the done button.
    ActionDone actionDone = new ActionDone(this, MainController, ActionForFrame);
    doneButton.addActionListener(actionDone);

    // Add the buttons to the button panel.
    panel.add(doneButton);
    panel.add(suspendButton);

    return panel;
  }

  /////////////////////////////////////////////////////////////////////////////
  //  This function creates a panel which displays the script for an action. //
  //  It displays the script text in a scrolling text field.                 //
  //                                                                         //
  /////////////////////////////////////////////////////////////////////////////
  private JScrollPane CreateScriptPane()
  {

    String script = ActionForFrame.GetFieldValue("script");

    //Create a text area.
    JTextArea textArea = new JTextArea(script);
    textArea.setFont(new Font("Serif", Font.ITALIC, 16));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setEditable(false);

    // Create scroll pane to display the text area.
    JScrollPane areaScrollPane = new JScrollPane(textArea);
    areaScrollPane.setVerticalScrollBarPolicy(
    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    areaScrollPane.setPreferredSize(new Dimension(250, 250));
    areaScrollPane.setBorder(
        BorderFactory.createCompoundBorder(
        BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Script Text"),
        BorderFactory.createEmptyBorder(5,5,5,5)),
        areaScrollPane.getBorder()));

    return areaScrollPane;
  }

//  private void ExecTool(PMLAction action)
//  {
//    String tool = ActionForFrame.GetFieldValue("tool");
//  
//    if (tool != null) {
//      String requires = ActionForFrame.GetFieldValue("requires");
//      String execStr = PMLConfig.GetConfig().GetStringValue(tool);      
//      if (execStr != null) {
//        execStr
//
//  }

  public PMLAction GetAction()
  {
    return ActionForFrame;
  }

  // Main controller for GUI.
  private PMLController MainController;

  // Action that the action frame is displaying.
  private PMLAction ActionForFrame;
}
