import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.event.*;
import javax.swing.table.TableModel;

import javax.swing.*;
import javax.swing.table.*;

import org.protege.swrlapi.owl2rl.OWL2RLNames.Table;
/*
 * Created by JFormDesigner on Wed Jul 03 15:29:40 EEST 2013
 */



/**
 * @author tugba ozacar
 */
public class GRDialog extends JDialog implements ActionListener{
	private ArrayList<String> slots;
	private ArrayList<Result> results;
	
	
	


	public GRDialog(java.util.ArrayList<String> slots, ModalityType mtype) {
		this.setModalityType(mtype);
		this.slots=slots;
		initComponents();
        
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - tugba ozacar
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		toolBar1 = new JToolBar();
		button1 = new JButton();
		button2 = new JButton();

		//======== this ========
		setTitle("Set Type/Units of Properties");
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout());

		//======== panel1 ========
		{

			panel1.setLayout(new BorderLayout());

			//======== scrollPane1 ========
			{

				Object[][] oArray=new Object[slots.size()][3];
				
				for (int i=0;i<slots.size();i++)
				{
					oArray[i][0]=slots.get(i);
					oArray[i][1]=Boolean.FALSE;
					oArray[i][2]="";
					
				}
				//---- table1 ----
				table1.setModel(new DefaultTableModel(
						oArray,
							new String[] {
								"Property Name", "OWLObjectProperty", "Units of Measurement"
							}
						) {
							Class<?>[] columnTypes = new Class<?>[] {
								Object.class, Boolean.class, String.class
							};
							@Override
							public Class<?> getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
				table1.getModel().addTableModelListener(new TableModelListener() {

				      public void tableChanged(TableModelEvent e) {
				         System.out.println("CHANGED");
				      }
				    });
				scrollPane1.setViewportView(table1);
			}
			panel1.add(scrollPane1, BorderLayout.CENTER);

			//======== toolBar1 ========
			{
				toolBar1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

				//---- button1 ----
				button1.setText("Cancel");
				button1.setMaximumSize(new Dimension(100, 21));
				button1.setMinimumSize(new Dimension(100, 21));
				button1.setPreferredSize(new Dimension(100, 21));
				button1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\protege\\edu\\stanford\\smi\\protege\\resource\\image\\cancel.gif"));
				toolBar1.add(button1);

				//---- button2 ----
				button2.setText("OK");
				button2.setPreferredSize(new Dimension(100, 21));
				button2.setMaximumSize(new Dimension(100, 21));
				button2.setMinimumSize(new Dimension(100, 21));
				button2.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\protege\\edu\\stanford\\smi\\protege\\resource\\image\\yes.gif"));
				button2.addActionListener(this);
				toolBar1.add(button2);
			}
			panel1.add(toolBar1, BorderLayout.SOUTH);
		}
		contentPane.add(panel1);
		pack();
		setLocationRelativeTo(getOwner());
		setIconImage(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Default\\PropertyMatrix.gif").getImage());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	public void actionPerformed(ActionEvent e) {
	    	 if (e.getSource() == button2) {
	    		 System.out.println("Button2");
	    		 for (int i=0;i<table1.getRowCount();i++)
	    		 {
	    			 	 Result r=new Result();
	    			 	 r.pname=table1.getValueAt(i,0).toString();
	    				 r.isObjectType=false;
	    				 if (table1.getValueAt(i,1)==Boolean.TRUE) r.isObjectType=true;	 
	    				 String units=table1.getValueAt(i,2).toString();
	    				 r.units=SplitUsingTokenizer(units, ",");
	    				 
	    				 
	    		 }
	    		 setVisible(false);
	    		 this.dispose();
	    		  
	    	 }

	  }
	
	public ArrayList<Result> getObjectProperties()
	{
		return results;
	}
	    
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - tugba ozacar
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JToolBar toolBar1;
	private JButton button1;
	private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
    public static String[] SplitUsingTokenizer(String Subject, String Delimiters) 
    {
     StringTokenizer StrTkn = new StringTokenizer(Subject, Delimiters);
     ArrayList<String> ArrLis = new ArrayList<String>(Subject.length());
     while(StrTkn.hasMoreTokens())
     {
       ArrLis.add(StrTkn.nextToken());
     }
     return ArrLis.toArray(new String[0]);
    }
}



