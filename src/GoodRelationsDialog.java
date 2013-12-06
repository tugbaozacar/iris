import java.awt.Dialog.ModalityType;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

//import com.jidesoft.combobox.MultiSelectListComboBox;


/**
 * @author tugba ozacar
 */
public class GoodRelationsDialog extends JDialog implements ActionListener {
	
	private JScrollPane scrollPane1;
	private JTable table1;
	private JToolBar toolBar1;
	private JButton button1;
	private JButton button2;
	ArrayList<String> slots, GRslots;
	ArrayList<MatchingResult> matchings;
	ArrayList<UnitofMeasurement> unitsOfMeasurement;
	ArrayList<String> unitsList;
	int s1,s2; 
	
	public GoodRelationsDialog(ArrayList<String> slots, ArrayList<String> GRslots, ArrayList<UnitofMeasurement> unitsOfMeasurement,ModalityType mtype) {
		this.setModalityType(mtype);
		this.slots=slots;
		this.GRslots=GRslots;
		this.unitsOfMeasurement=unitsOfMeasurement;
		initComponents();    
	}

	private void initComponents() {

		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		toolBar1 = new JToolBar();
		button1 = new JButton();
		button2 = new JButton();
		unitsList=new ArrayList<String>();
		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setTitle("Use Good Relations Vocabulary");
		setIconImage(new ImageIcon("C:\\Users\\user\\Desktop\\protege\\edu\\stanford\\smi\\protege\\resource\\image\\GR_16_16_32.gif").getImage());
		//======== scrollPane1 ========
		{
			scrollPane1.setPreferredSize(new Dimension(550, 427));
			scrollPane1.setMinimumSize(new Dimension(550, 427));
			Object[][] rowData = new String[slots.size()][3];
			Object[] combo=new Object[GRslots.size()+3];
			for (int i=0;i<slots.size();i++)
			{
				rowData[i][0]=slots.get(i);
				rowData[i][1]="\u228A gr:datatypeProductOrServiceProperty";
				rowData[i][2]=unitsOfMeasurement.get(0).symbol;
			}
			
			combo[0]="\u228A gr:datatypeProductOrServiceProperty";
			combo[1]="\u228A gr:quantitativeProductOrServiceProperty";
			combo[2]="\u228A gr:qualitativeProductOrServiceProperty";
			String grslot;
			for (int i=0;i<GRslots.size();i++)
			{
				grslot=GRslots.get(i);
				if (grslot!="gr:datatypeProductOrServiceProperty"&&grslot!="gr:quantitativeProductOrServiceProperty"&&grslot!="gr:qualitativeProductOrServiceProperty")
					combo[i+3]="= "+grslot;
			}
			
			String value;
			for (int i=0;i<unitsOfMeasurement.size();i++)
			{
				value=unitsOfMeasurement.get(i).name;
				if (!unitsOfMeasurement.get(i).symbol.isEmpty())
					value=value+" ("+unitsOfMeasurement.get(i).symbol+")";
				unitsList.add(value);
			}
			java.util.Collections.sort(unitsList);
			Object[] unitsCombo=unitsList.toArray();
			String[] columnNames = {"Property Name", "Corresponding GR Property","Units of Measurement"};
			table1 = new JTable(rowData, new String[] {"Property Name", "Corresponding GR Property","Units of Measurement"});
		
			{
				TableColumnModel cm = table1.getColumnModel();
				cm.getColumn(1).setCellEditor(new DefaultCellEditor(
					new JComboBox(new DefaultComboBoxModel(combo))));
				cm.getColumn(2).setCellEditor(new DefaultCellEditor(
					new JComboBox(new DefaultComboBoxModel(unitsCombo))));
			}
		scrollPane1.setViewportView(table1);
		contentPane.add(scrollPane1, BorderLayout.CENTER);
		}
		//======== toolBar1 ========
		{
			toolBar1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

			//---- button1 ----
			button1.setText("Cancel");
			button1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\protege\\edu\\stanford\\smi\\protege\\resource\\image\\cancel.gif"));
			button1.setPreferredSize(new Dimension(100, 23));
			button1.setMaximumSize(new Dimension(100, 23));
			button1.setMinimumSize(new Dimension(100, 23));
			toolBar1.add(button1);

			//---- button2 ----
			button2.setText("OK");
			button2.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\protege\\edu\\stanford\\smi\\protege\\resource\\image\\yes.gif"));
			button2.setPreferredSize(new Dimension(100, 23));
			button2.setMaximumSize(new Dimension(100, 23));
			button2.setMinimumSize(new Dimension(100, 23));
			button2.addActionListener(this);
			toolBar1.add(button2);
		}
		contentPane.add(toolBar1, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		setSize(600,400);
	}

	
	public void actionPerformed(ActionEvent e) {
   	 if (e.getSource() == button2) {
   		 matchings=new ArrayList<MatchingResult>();
   		 
   		 
   		MatchingResult mr;
   		String unit, unitname, unitsymbol;
   		int sindex,findex;
   		UnitofMeasurement u;
   		
   		 for (int i=0;i<slots.size();i++)
   		 {
   			 mr=new MatchingResult();
   			 mr.slotName=slots.get(i);
   			 if (table1.getValueAt(i, 1).toString().contains(":"))
   				 mr.GR_slotName=SplitUsingTokenizer(table1.getValueAt(i, 1).toString(), ":").get(1);
   			 else
   				 mr.GR_slotName=table1.getValueAt(i, 1).toString();

   			 if (table1.getValueAt(i, 2)!=null)
   			 {
   				 unit=table1.getValueAt(i, 2).toString();
   				 unitname=unit;
   				 unitsymbol="";
   				 sindex=unit.indexOf('(');
   				 findex=unit.indexOf(')');

   				 if (sindex!=-1&&findex!=-1)
   				 {
   				 	 unitsymbol=unit.substring(sindex+1, findex);
   				 	 unitname=unit.substring(0, sindex-1);
   				 	 mr.unit_name=unitname.trim();
   				 	 mr.unit_symbol=unitsymbol.trim();
   				 	 u=null;
   				 	 for (int l=0;l<unitsOfMeasurement.size();l++)
   				 	 {
   				 		u=unitsOfMeasurement.get(l);
   				 		 if (u.name.contains(mr.unit_name)&&u.name.length()==mr.unit_name.length())
   				 			 mr.unit_commonCode=u.commonCode;
   				 	 }
   				 }
   				 else
   				 {
   					mr.unit_name=unitname.trim();
   					u=null;
   					for (int l=0;l<unitsOfMeasurement.size();l++)
   					{
   						u=unitsOfMeasurement.get(l);
   						if (u.name.contains(mr.unit_name)&&u.name.length()==mr.unit_name.length())
   						{
   							mr.unit_commonCode=u.commonCode;
   							System.out.println(mr.unit_commonCode);
   						}
   					}
   				 }

   			 }
   			 
   			 matchings.add(mr);
   		 }
   		 
   		 setVisible(false);
   		 this.dispose();
   		  
   	 }
   	 
   	 if (e.getSource() == button1) {
   		 setVisible(false);
   		 this.dispose(); 
   	 }

 }
	
	public ArrayList<MatchingResult> getMatchingResults()
	{
		return matchings;
	}
	

    public static ArrayList<String> SplitUsingTokenizer(String Subject, String Delimiters) 
    {
     StringTokenizer StrTkn = new StringTokenizer(Subject, Delimiters);
     ArrayList<String> ArrLis = new ArrayList<String>();
     while(StrTkn.hasMoreTokens())
     {
       ArrLis.add(StrTkn.nextToken().trim());
     }
     return ArrLis;
    }

}

class MatchingResult
{
	String slotName;
	String GR_slotName;
	String unit_name;
	String unit_symbol;
	String unit_commonCode;
}






