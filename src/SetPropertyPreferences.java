import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.event.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import javax.swing.*;
import javax.swing.table.*;

import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.protege.swrlapi.owl2rl.OWL2RLNames.Table;
/*
 * Created by JFormDesigner on Wed Jul 03 15:29:40 EEST 2013
 */



/**
 * @author tugba ozacar
 */
public class SetPropertyPreferences extends JDialog implements ActionListener{
	private ArrayList<String> slots;
	private ArrayList<Result> results;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JToolBar toolBar1;
	private JButton button1;
	private JButton button2;
	private ArrayList<UnitofMeasurement> unitsOfMeasurement;
	
	public SetPropertyPreferences(java.util.ArrayList<String> slots, ArrayList<UnitofMeasurement> unitsOfMeasurement,ModalityType mtype) {
		
		this.setModalityType(mtype);
		this.slots=slots;
		this.unitsOfMeasurement=unitsOfMeasurement;
		initComponents();
		setPropertyPreferences();
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
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout());

		//======== panel1 ========
		{

			panel1.setLayout(new BorderLayout());

			//======== scrollPane1 ========
			{

				
			panel1.add(scrollPane1, BorderLayout.CENTER);

			//======== toolBar1 ========
			{
				toolBar1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

				//---- button1 ----
				button1.setText("Cancel");
				button1.setMaximumSize(new Dimension(100, 21));
				button1.setMinimumSize(new Dimension(100, 21));
				button1.setPreferredSize(new Dimension(100, 21));
				URL fileUri = SetPropertyPreferences.class.getResource("/resources/cancel.gif");
				button1.setIcon(new ImageIcon(fileUri));
				button1.addActionListener(this);
				toolBar1.add(button1);

				//---- button2 ----
				button2.setText("OK");
				button2.setPreferredSize(new Dimension(100, 21));
				button2.setMaximumSize(new Dimension(100, 21));
				button2.setMinimumSize(new Dimension(100, 21));
				fileUri = SetPropertyPreferences.class.getResource("/resources/yes.gif");
				button2.setIcon(new ImageIcon(fileUri));
				button2.addActionListener(this);
				toolBar1.add(button2);
			}
			panel1.add(toolBar1, BorderLayout.SOUTH);
		}
		contentPane.add(panel1);
		pack();
		setLocationRelativeTo(getOwner());
		
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	}
		
	public void setPropertyPreferences()
		{
		ArrayList<String> unitsList=new ArrayList<String>();
		setTitle("Set Type/Units of Properties");
		Object[][] oArray=new Object[slots.size()][3];
		
		for (int i=0;i<slots.size();i++)
		{
			oArray[i][0]=slots.get(i);
			oArray[i][1]=Boolean.FALSE;
			oArray[i][2]=Boolean.FALSE;
			
		}
		
		/*****************************/
//		for (int i=0;i<unitsOfMeasurement.size();i++)
//		{
//			String value=unitsOfMeasurement.get(i).name;
//			if (!unitsOfMeasurement.get(i).symbol.isEmpty())
//				value=value+" ("+unitsOfMeasurement.get(i).symbol+")";
//			unitsList.add(value);
//		}
//		java.util.Collections.sort(unitsList);
//		Object[] unitsCombo=unitsList.toArray();
		/********************************/
		//---- table1 ----
		table1.setModel(new DefaultTableModel(
				oArray,
					new String[] {
						"Property Name", "OWLObjectProperty", "Remove Unit"
					}
				) {
					Class<?>[] columnTypes = new Class<?>[] {
						Object.class, Boolean.class, Boolean.class
					};
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		
//			TableColumnModel cm = table1.getColumnModel();
//			cm.getColumn(2).setCellEditor(new DefaultCellEditor(
//			new JComboBox(new DefaultComboBoxModel(unitsCombo))));
		
			 table1.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
		         //System.out.println("CHANGED");
		      }
		    });
		scrollPane1.setViewportView(table1);
		URL fileUri = SetPropertyPreferences.class.getResource("/resources/PropertyMatrix.gif");
		setIconImage(new ImageIcon(fileUri).getImage());
		setSize(600,400);
	}
	
	
	public void actionPerformed(ActionEvent e) {
	    	 if (e.getSource() == button2) {
	    		 //System.out.println("Button2");
	    		 results=new ArrayList<Result>();
	    		 Result r;
	    		 for (int i=0;i<table1.getRowCount();i++)
	    		 {
	    			 	 r=new Result();
	    			 	 r.pname=table1.getValueAt(i,0).toString();
	    				 r.isObjectType=false;
	    				 r.removeUnit=false;
	    				 if (table1.getValueAt(i,1)==Boolean.TRUE) r.isObjectType=true;	 
//	    				 String units=table1.getValueAt(i,2).toString();
//	    				 r.units=SplitUsingTokenizer(units, ",");
	    				 if (table1.getValueAt(i,1)==Boolean.TRUE) r.removeUnit=true;
	    				 results.add(r);
	    				 
	    		 }
	    		 setVisible(false);
	    		 this.dispose();
	    		  
	    	 }
	    	 if (e.getSource() == button1) {
	    		 setVisible(false);
	    		 this.dispose();
	    	 }

	  }
	
	public ArrayList<Result> getObjectProperties()
	{
		return results;
	}

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

class Result
{
	public String pname;
	public boolean isObjectType;
	public boolean removeUnit;
	
}

