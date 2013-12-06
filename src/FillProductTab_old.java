import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFVisitor;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.jidesoft.swing.*;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import edu.stanford.smi.protege.widget.AbstractTabWidget;

import edu.stanford.smi.protege.Application;
import edu.stanford.smi.protege.model.Project;
import edu.stanford.smi.protege.util.ApplicationProperties;

import edu.stanford.smi.protege.resource.*;
import edu.stanford.smi.protege.model.*;
import edu.stanford.smi.protege.ui.*;
import edu.stanford.smi.protege.util.*;
import edu.stanford.smi.protege.action.*;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protege.model.KnowledgeBase;
import edu.stanford.smi.protege.model.Slot;
import edu.stanford.smi.protege.resource.Icons;

import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaKnowledgeBaseFactory;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLClass;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.OWLProperty;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;
import edu.stanford.smi.protegex.owl.model.RDFSLiteral;
import edu.stanford.smi.protegex.owl.model.impl.DefaultRDFProperty;
import edu.stanford.smi.protegex.owl.model.impl.XMLSchemaDatatypes;
import edu.stanford.smi.protegex.owl.model.util.ImportHelper;
import edu.stanford.smi.protegex.owl.ui.dialogs.SelectClassPanel;
import edu.stanford.smi.protegex.owl.ui.dialogs.SelectOWLClsesPanel;

public class FillProductTab extends AbstractTabWidget implements ActionListener{
	
	JenaOWLModel owlModel;

	private static final long serialVersionUID = 1L;
	protected JTextField textField;
    protected JTextArea textArea;
   
    private final static String newline = "\n";
    ArrayList<Product> productList;
    int lastProductCount=0;
	int rowsFilled;
	DefaultMutableTreeNode root;
 	JTree checkBoxTree;
 	ArrayList<DefaultMutableTreeNode> selectedNodes;
 	private JSplitPane splitPane9;
 	private JSplitPane splitPane10;
 	private JToolBar toolBar1;
 	private JButton button14;
 	private JButton button15;
 	private JButton button16;
 	private JButton button17;
 	private JButton button18;
 	private JScrollPane scrollPane2;
 	private JSplitPane splitPane11;
 	private JSplitPane splitPane12;
 	private JToolBar toolBar2;
 	private JButton openButton;
 	private JButton button19;
 	private JButton button20;
 	private JButton button21;
 	private JButton button22;
 	private JButton previewButton;
 	private JButton saveButton;
 	private JButton setPropertyPreferences;
 	private JButton goodRelations;
 	private JScrollPane scrollPane3;
 	private boolean goodRelationsVocabulary_opened;
 	private String RANGE_DELIMETER;
 	
	ArrayList<String> slots, Grslots;
	ArrayList<String> individuals;
	ArrayList<String> datatypeProperties;
	ArrayList<String> objectProperties;
	ArrayList<Result> DialogBoxResults;
	ArrayList<MatchingResult> GoodRelationsMatchingResults;
 	
	  	public void initialize() 
	  {
	  
		BorderLayout border=new BorderLayout();
		setLayout(border);
        setLabel("IRIS");
        setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\Project1.gif"));
        this.setSize(300,150);
        owlModel=(JenaOWLModel) getProject().getKnowledgeBase();
        
		productList=new ArrayList<Product>();
		slots=new ArrayList<String>();
		individuals=new ArrayList<String>();
		datatypeProperties=new ArrayList<String>();
		objectProperties=new ArrayList<String>();
		Grslots=new ArrayList<String>();
		GoodRelationsMatchingResults=new ArrayList<MatchingResult>();
		goodRelationsVocabulary_opened=false;
		RANGE_DELIMETER="-";
	    
	    /************************************/
	 // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
	 		// Generated using JFormDesigner Evaluation license - tugba ozacar
	 		splitPane9 = new JSplitPane();
	 		splitPane10 = new JSplitPane();
	 		toolBar1 = new JToolBar();
	 		button14 = new JButton();
	 		button15 = new JButton();
	 		button16 = new JButton();
	 		button17 = new JButton();
	 		button18 = new JButton();
	 		scrollPane2 = new JScrollPane();
	 		splitPane11 = new JSplitPane();
	 		splitPane12 = new JSplitPane();
	 		toolBar2 = new JToolBar();
	 		openButton = new JButton();
	 		button19 = new JButton();
	 		button20 = new JButton();
	 		button21 = new JButton();
	 		button22 = new JButton();
	 		saveButton = new JButton();
	 		previewButton = new JButton();
	 		scrollPane3 = new JScrollPane();
	 		setPropertyPreferences=new JButton();
	 		goodRelations=new JButton();
//
	 		//======== splitPane9 ========
	 		{
	 			splitPane9.setDividerLocation(200);

	 			//======== splitPane10 ========
	 			{
	 				splitPane10.setOrientation(JSplitPane.VERTICAL_SPLIT);

	 				//======== toolBar1 ========
	 				{

	 					//---- button14 ----
//	 					button14.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Multicolored\\down.gif"));
//	 					button14.setToolTipText("Retrieve from hierarchy archive");
//	 					toolBar1.add(button14);

//	 					//---- button15 ----
//	 					button15.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Multicolored\\up.gif"));
//	 					button15.setToolTipText("Save to hierarchy archive");
//	 					toolBar1.add(button15);
//	 					toolBar1.addSeparator();
	 					//---- button16 ----
	 					button16.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Default\\SubClass.gif"));
	 					button16.setToolTipText("Create subclass");
	 					toolBar1.add(button16);

	 					//---- button17 ----
	 					button17.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Default\\SiblingClass.gif"));
	 					button17.setToolTipText("Create sibling class");
	 					toolBar1.add(button17);

	 					//---- button18 ----
	 					button18.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\protege\\edu\\stanford\\smi\\protege\\resource\\image\\Project8.gif"));
	 					button18.setToolTipText("Remove class");
	 					toolBar1.add(button18);
	 				}
	 				splitPane10.setTopComponent(toolBar1);
	 				splitPane10.setBottomComponent(scrollPane2);
	 			}
	 			
		  		Collection rootClses = Collections.singleton(owlModel.getOWLThingClass());
		  		SelectOWLClsesPanel clsesPanel = new SelectOWLClsesPanel(owlModel);
		  		//clsesPanel.resize(splitPane9.WIDTH, splitPane9.HEIGHT);
		  		//scrollPane2.add(clsesPanel);
//		 		scrollPane2.setViewportView(clsesPanel);
//		 		scrollPane2.revalidate();  
//		 		scrollPane2.repaint();
	 			splitPane9.setLeftComponent(clsesPanel);

	 			//======== splitPane11 ========
	 			{
	 				splitPane11.setOrientation(JSplitPane.VERTICAL_SPLIT);

	 				//======== toolBar2 ========
	 				{

	 					//---- button24 ----
	 					openButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Open16.gif"));
	 					openButton.addActionListener(this);
	 					toolBar2.add(openButton);

	 					//---- button19 ----
	 					button19.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Import16.gif"));
	 					button19.setToolTipText("Import file");
	 					toolBar2.add(button19);

	 					//---- button20 ----
	 					button20.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Export16.gif"));
	 					button20.setToolTipText("Export file");
	 					toolBar2.add(button20);
	 					toolBar2.addSeparator();
	 					//---- button21 ----
	 					button21.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Properties16.gif"));
	 					button21.setToolTipText("Select all");
	 					toolBar2.add(button21);

	 					//---- button22 ----
	 					button22.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\New16.gif"));
	 					button22.setToolTipText("Deselect all");
	 					toolBar2.add(button22);
	 					toolBar2.addSeparator();
	 					
	 					setPropertyPreferences.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Default\\PropertyMatrix.gif"));
	 					setPropertyPreferences.addActionListener(this);
	 					setPropertyPreferences.setToolTipText("Set type/units for property");
	 					toolBar2.add(setPropertyPreferences);
	 					
	 					goodRelations.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\protege\\edu\\stanford\\smi\\protege\\resource\\image\\GR_16_16_32.gif"));
	 					goodRelations.addActionListener(this);
	 					goodRelations.setToolTipText("Use Good Relations Vocabulary");
	 					toolBar2.add(goodRelations);
	 					toolBar2.addSeparator();
	 					
	 					
	 					//---- button23 ----
	 					previewButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Zoom16.gif"));
	 					previewButton.addActionListener(this);
	 					previewButton.setToolTipText("Preview");
	 					toolBar2.add(previewButton);
	 					
	 					saveButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Save16.gif"));
	 					saveButton.addActionListener(this);
	 					saveButton.setToolTipText("Save");
	 					toolBar2.add(saveButton);
	 				}
	 				splitPane11.setTopComponent(toolBar2);
	 				splitPane11.setBottomComponent(scrollPane3);
	 				

	 			}
	 			splitPane9.setRightComponent(splitPane11);
	 		}
	 		add(splitPane9, BorderLayout.CENTER);	
	  }

	  	public static void main(String[] args) 

	  {
			edu.stanford.smi.protege.Application.main(args);	
	  }

	  	public void drawTree(ArrayList<Product> productList )
	 	{

 		root=new DefaultMutableTreeNode("PRODUCTS");
 		
		for (int i=0;i<productList.size();i++)
	       {
	    		   Product p=productList.get(i);
	    		   DefaultMutableTreeNode cp=new DefaultMutableTreeNode("Product_"+i); 
	    		   root.add(cp);
	    		   cp.add(new DefaultMutableTreeNode("Title= "+p.title));
	    		   cp.add(new DefaultMutableTreeNode("Image Link= "+p.imgLink));
	    		   for (int y=0;y<p.propertyName.size()&&y<p.propertyValue.size();y++)
	    			   cp.add(new DefaultMutableTreeNode(p.propertyName.get(y)+"= "+p.propertyValue.get(y)));
	    		  
	    }
		

		
 		tree = new JTree(root);
 		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)  checkBoxTree.getActualCellRenderer();
 		renderer.setClosedIcon(edu.stanford.smi.protege.resource.Icons.getInstanceIcon());
 		renderer.setOpenIcon(edu.stanford.smi.protege.resource.Icons.getInstanceIcon());
 		renderer.setLeafIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Multicolored\\RDFPropertyInherited.gif"));
 		checkBoxTree.getCheckBoxTreeSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			
//			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

 		
 		scrollPane3.setViewportView(checkBoxTree);
 		scrollPane3.revalidate();  
 		scrollPane3.repaint();
	  	}
	  	
	  	public void actionPerformed(ActionEvent e) 
	  	{
	    	JFileChooser  fc = new JFileChooser();

	        if (e.getSource() == openButton) 
	        {
	            int returnVal = fc.showOpenDialog(this);
	 
	            if (returnVal == JFileChooser.APPROVE_OPTION) 
	            {
	                File file = fc.getSelectedFile();
	                fillProductTree(file);
	            } 
	            openButton.setEnabled(false);
	        }
	        
	        if (e.getSource() == saveButton) 
	        {
	        	saveOntology();
	        }
	        
	        if (e.getSource() == goodRelations) 
	        {
	        	if (goodRelationsVocabulary_opened==false)
	        	{
	        		try 
	        		{
	        			openGoodRelationsVocabulary();
	        			SelectOWLClsesPanel clsesPanel = new SelectOWLClsesPanel(owlModel);
	        			splitPane9.setLeftComponent(clsesPanel);
	        			splitPane9.revalidate();
	        			splitPane9.repaint();
	        		} catch (OntologyLoadException e1) 
	        		{
	        			// TODO Auto-generated catch block
	        			//e1.printStackTrace();
	        		}
	        	}
	        	
	        	getSelectionData();
	        	getGRDMatchings();
	        	setPropertyPreferences.setEnabled(false);
	        	goodRelations.setEnabled(false);
	        }

	        if (e.getSource() == setPropertyPreferences) 
	        { 
	        	getSelectionData();
	        	getPropertyPreferences();
	        	goodRelations.setEnabled(false);
	        	setPropertyPreferences.setEnabled(false);
	        }
	        
	        
	        
	  }
	  	
	  	public void getGRDMatchings()
	  	{
        	Collection<DefaultRDFProperty> absProperties=owlModel.getRDFProperties();
        	if (!absProperties.isEmpty())
        	{
        		final Iterator<DefaultRDFProperty> iterator= absProperties.iterator();
        		while(iterator.hasNext()) 
        		{
        			DefaultRDFProperty absProperty = iterator.next();
        	         System.out.println(absProperty.getNamespace());
        	         if (absProperty.getNamespace().contains("http://purl.org/goodrelations/v1#")||absProperty.getNamespace().contains("http://schema.org")||absProperty.getNamespace().contains("http://xmlns.com/foaf/0.1/"))
        	         {
        	        	 Grslots.add(absProperty.getPrefixedName());
        	         }
        	    }
        	}
        	UnitsofMeasurementManager um_manager=new UnitsofMeasurementManager();
        	
        	GoodRelationsDialog grd=new GoodRelationsDialog(slots,Grslots,um_manager.getUnitsofMeasurement(),ModalityType.APPLICATION_MODAL);
        	grd.setVisible(true);
        	GoodRelationsMatchingResults=grd.getMatchingResults();
	  	}
	  	
	  	public void openGoodRelationsVocabulary() throws OntologyLoadException{
	  			  		
	  		 InputStream is;
	  		 ImportHelper imh=new ImportHelper(owlModel);
	  	    try {
	  	        is = new FileInputStream("C:\\Users\\user\\Desktop\\GoodRelations_v1.owl");
	  	        imh.addImport(is);
	  	        imh.importOntologies(false);
	  	        owlModel.getNamespaceManager().setPrefix("http://purl.org/goodrelations/v1#", "gr");
	  	        owlModel.getNamespaceManager().setPrefix("http://xmlns.com/foaf/0.1/","foaf");
	  	        owlModel.getNamespaceManager().setPrefix("http://www.w3.org/2002/07/owl#","owl");
	  	        owlModel.getNamespaceManager().setPrefix("http://purl.org/goodrelations/v1#","gr");
	  	        owlModel.getNamespaceManager().setPrefix("http://www.w3.org/2000/01/rdf-schema#","rdfs");
	  	        owlModel.getNamespaceManager().setPrefix("http://purl.org/dc/elements/1.1/","dc");
	  	        owlModel.getNamespaceManager().setPrefix("http://www.w3.org/1999/02/22-rdf-syntax-ns#","rdf");
	  	        owlModel.getNamespaceManager().setPrefix("http://purl.org/dc/terms/","dcterms");
	  	        
	  	      ProjectView prjView = ProjectManager.getProjectManager().getCurrentProjectView(); 	          
	  	      if (prjView != null) prjView.reloadAllTabsExcept(this); 
	  	      is.close(); 
	  	      goodRelationsVocabulary_opened=true;
	  	    } catch (FileNotFoundException e) {
	  	        // TODO Auto-generated catch block
	  	        //e.printStackTrace();
	  	    } catch (IOException e) {
	  	        // TODO Auto-generated catch block
	  	       // e.printStackTrace();
	  	    }
	  	}
	    	  
	  	public void getPropertyPreferences()
	  {
	  		
        SetPropertyPreferences sp = new SetPropertyPreferences(slots, ModalityType.APPLICATION_MODAL);
        sp.setVisible(true);
        ArrayList<Result> DialogBoxResults =sp.getObjectProperties();
		 
		 for (int i=0;i<DialogBoxResults.size();i++)
		 {
			 Result r=DialogBoxResults.get(i);
			 if (r.isObjectType)
				 objectProperties.add(r.pname);
		 }

		 for (int i=0;i<objectProperties.size();i++)
		 {
			 String co=objectProperties.get(i);
			 if (datatypeProperties.contains(co))
				 datatypeProperties.remove(co);
		 }
	  }
	  
	  	public void getSelectionData()
	    {
		  	TreePath[] selectedPaths=checkBoxTree.getCheckBoxTreeSelectionModel().getSelectionPaths();

	    	for (int i=0;i<selectedPaths.length;i++)
	    	{
	    		Object[] path =selectedPaths[i].getPath();

	    			String lastNode="";
	    			String indNode="";
	    			String propertyName;
	    			
	    			if (path.length>1)
	    			{
	    				indNode=path[path.length-2].toString();
	    				lastNode=path[path.length-1].toString();
	    			}
	    			
	    			if (!indNode.isEmpty()&& !individuals.contains(indNode))
	    					individuals.add(indNode);

	    			if (!lastNode.isEmpty())
	    			{
	    				
	    				String[] propValue=SplitUsingTokenizer(lastNode, "=");
	    				if (propValue.length>1) 
	    				{
	    					propertyName=normalize(propValue[0]);
	    					String propertyValue=normalize(propValue[1]);
	    					
	    					if (!propertyName.isEmpty() && !slots.contains(propertyName))
	    						slots.add(propertyName);
	    				}
	    			}
	    	}

	    	for (int i=0;i<slots.size();i++)
	    		datatypeProperties.add(slots.get(i));
	    	}  

	  	public void saveOntology()
	  	{
		    OWLNamedClass c;
	    	createOWLNamedClass("Laptop");
	    	c= owlModel.getOWLNamedClass(owlModel.getURIForResourceName("Laptop"));
	    	
	  		for (int i=0;i<individuals.size();i++)
				createOWLIndividual(c,individuals.get(i));
	  		
	    	if (goodRelationsVocabulary_opened)
	    		saveGoodRelationsVocabulary(c);
	    	else
	    		saveOntologyModel(c);
	  }
	  	
	  	public void saveGoodRelationsVocabulary(OWLNamedClass c)
	  	{
			
			String quantitativeProductOrServiceProperty="http://purl.org/goodrelations/v1#quantitativeProductOrServiceProperty";
			String qualitativeProductOrServiceProperty="http://purl.org/goodrelations/v1#qualitativeProductOrServiceProperty";
			String datatypeProductOrServiceProperty="http://purl.org/goodrelations/v1#datatypeProductOrServiceProperty";

			
	    	TreePath[] selectedPaths=checkBoxTree.getCheckBoxTreeSelectionModel().getSelectionPaths();
			
	    	for (int i=0;i<selectedPaths.length;i++)
	    	{
	    		Object[] path =selectedPaths[i].getPath();

	    			String lastNode="";
	    			String indNode="";
	    			String propertyName;
	    			
	    			if (path.length>1)
	    			{
	    				indNode=path[path.length-2].toString();
	    				lastNode=path[path.length-1].toString();
	    			}
	    			
	    			if (!lastNode.isEmpty())
	    			{
	    				
	    				String[] propValue=SplitUsingTokenizer(lastNode, "=");
	    				if (propValue.length>1) 
	    				{
	    					propertyName=normalize(propValue[0]);
	    					String propertyValue=normalize(propValue[1]);

	    					if (!indNode.isEmpty())
	    					{
	    						
	    						String individualUri=owlModel.getURIForResourceName(indNode);
	    						OWLIndividual ind=owlModel.getOWLIndividual(individualUri);

	    						for (int j=0;j<GoodRelationsMatchingResults.size();j++)
	    						{
	    							MatchingResult mr=GoodRelationsMatchingResults.get(j);
	    							if (mr.slotName.contains(propertyName)&&mr.slotName.length()==propertyName.length())
	    							{
	  
	    								if (mr.GR_slotName.endsWith("quantitativeProductOrServiceProperty"))
	    									createQuantitativeProperty(mr, propertyValue, c,ind);
	    								else
	    									if (mr.GR_slotName.endsWith("qualitativeProductOrServiceProperty"))
	    										createQualitativeProperty(mr, propertyValue, c,ind);
	    									else
	    										if (mr.GR_slotName.endsWith("datatypeProductOrServiceProperty"))
	    										{
	    											RDFProperty datatypeProductOrServicePropertyObject=owlModel.getRDFProperty(datatypeProductOrServiceProperty);
	    		    	    						OWLDatatypeProperty datatypeProperty=createOWLDatatypeProperty(mr.slotName);
	    		    	    						datatypeProperty.addSuperproperty(datatypeProductOrServicePropertyObject);
	    		    	    						datatypeProperty.addUnionDomainClass(c);
	    		    								ind.setPropertyValue(datatypeProperty, owlModel.createRDFSLiteralOrString(propertyValue, "en"));
	    										}
	    										else
	    										{
	    											String GRPropertyUri="";
	    											
	    											if (!mr.GR_slotName.contains("/"))
	    											{
	    												GRPropertyUri="http://purl.org/goodrelations/v1#"+mr.GR_slotName;
	    											}
	    											else
	    												GRPropertyUri=mr.GR_slotName;
	    											
	    												RDFProperty GRProperty=owlModel.getRDFProperty(GRPropertyUri);
	    												if (GRProperty.hasObjectRange())
	    												{
	    													OWLNamedClass rangeClass=owlModel.getOWLNamedClass(GRProperty.getRange().getURI());
	    													OWLIndividual vind=createOWLIndividual(rangeClass,propertyValue);
	    													GRProperty.addUnionDomainClass(c);
	    													ind.setPropertyValue(GRProperty, vind);
	    												}
	    												else
	    												{
	    													RDFSDatatype range=owlModel.getRDFSDatatypeByURI(GRProperty.getRange().getURI());
	    				    	    						GRProperty.addUnionDomainClass(c);
	    				    								ind.setPropertyValue(GRProperty,owlModel.createRDFSLiteral(propertyValue, range));
	    												}
	    										}
	    								
	    							}
	    						}

	    					}
	    				}
	    			}
	    	}
	  		
	  	}
	  	
	  	public void createQuantitativeProperty(MatchingResult mr, String propertyValue, OWLNamedClass c,OWLIndividual ind)
	  	{
	  		
	  		RDFProperty quantitativeProductOrServiceProperty=owlModel.getRDFProperty("http://purl.org/goodrelations/v1#quantitativeProductOrServiceProperty"); 
		    RDFProperty hasMinValueFloat=owlModel.getRDFProperty("http://purl.org/goodrelations/v1#hasMinValueFloat");
		    RDFProperty hasMaxValueFloat=owlModel.getRDFProperty("http://purl.org/goodrelations/v1#hasMaxValueFloat");
		    RDFProperty hasValueFloat=owlModel.getRDFProperty("http://purl.org/goodrelations/v1#hasValueFloat");
		    RDFProperty hasUnitOfMeasurement=owlModel.getRDFProperty("http://purl.org/goodrelations/v1#hasUnitOfMeasurement");
			OWLNamedClass QuantitativeValue=owlModel.getOWLNamedClass("http://purl.org/goodrelations/v1#QuantitativeValueFloat");

	  		createOWLObjectProperty(mr.slotName);
	  		
			OWLObjectProperty owlProperty=owlModel.getOWLObjectProperty(owlModel.getURIForResourceName(mr.slotName));
			owlProperty.addSuperproperty(quantitativeProductOrServiceProperty);
			owlProperty.addUnionDomainClass(c);
			owlProperty.addUnionRangeClass(QuantitativeValue);
			
			int ic=QuantitativeValue.getDirectInstanceCount();

			String s="QuantitativeValue"+"_"+ic;
			OWLIndividual qind=createOWLIndividual(QuantitativeValue,s);
			ind.setPropertyValue(owlProperty, qind);
			
			if (propertyValue.contains(RANGE_DELIMETER))
			{
				RDFSLiteral unitOfMeasurement;
				String[] values=SplitUsingTokenizer(propertyValue, RANGE_DELIMETER);

			    RDFSLiteral minValue=owlModel.createRDFSLiteral(values[0], owlModel.getRDFSDatatypeByURI("http://www.w3.org/2001/XMLSchema#float" ));
			    RDFSLiteral maxValue=owlModel.createRDFSLiteral(values[1], owlModel.getRDFSDatatypeByURI("http://www.w3.org/2001/XMLSchema#float" ));
			    if (!mr.unit_commonCode.isEmpty())
			    {
			    	unitOfMeasurement=owlModel.createRDFSLiteral(mr.unit_commonCode, owlModel.getRDFSDatatypeByURI("http://www.w3.org/2001/XMLSchema#string" ));
			    	qind.setPropertyValue(hasUnitOfMeasurement, unitOfMeasurement);
			    }
			    qind.setPropertyValue(hasMinValueFloat, minValue);
			    qind.setPropertyValue(hasMaxValueFloat, maxValue);
			    
			}
			else
			{
				RDFSLiteral unitOfMeasurement;
				String processedValue=propertyValue;
				Pattern pattern = Pattern.compile("[0123456789]*[.]?[0123456789]{0,4}");
				Matcher matcher = pattern.matcher(propertyValue);
				if (matcher.find())
				{

				    processedValue=matcher.group(0);
				    RDFSLiteral value=owlModel.createRDFSLiteral(processedValue, owlModel.getRDFSDatatypeByURI("http://www.w3.org/2001/XMLSchema#float" ));
				    
				    if(mr.unit_commonCode!=null)
				    	if (!mr.unit_commonCode.isEmpty())
				    	{
				    		unitOfMeasurement=owlModel.createRDFSLiteral(mr.unit_commonCode, owlModel.getRDFSDatatypeByURI("http://www.w3.org/2001/XMLSchema#string" ));
				    		
				    		qind.setPropertyValue(hasUnitOfMeasurement, unitOfMeasurement);
				    	}

				    qind.setPropertyValue(hasValueFloat, value);
		
				}
				
			}
	  	}
	  	
	  	public void createQualitativeProperty(MatchingResult mr, String propertyValue, OWLNamedClass c,OWLIndividual ind)
	  	{
	  		RDFProperty qualitativeProductOrServiceProperty=owlModel.getRDFProperty("http://purl.org/goodrelations/v1#qualitativeProductOrServiceProperty"); 
			OWLNamedClass QualitativeValue=owlModel.getOWLNamedClass("http://purl.org/goodrelations/v1#QualitativeValue");
			
			OWLObjectProperty owlProperty=createOWLObjectProperty(mr.slotName);
			owlProperty.addSuperproperty(qualitativeProductOrServiceProperty);
			owlProperty.addUnionDomainClass(c);
			OWLNamedClass rangeClass=createOWLNamedClass("ValuesFor_"+mr.slotName);
			rangeClass.addDirectSuperclass(QualitativeValue);
			owlProperty.addUnionRangeClass(rangeClass);
			OWLIndividual rind=createOWLIndividual(rangeClass,propertyValue);

			ind.setPropertyValue(owlProperty, rind);
	  	}

	  	public void saveOntologyModel(OWLNamedClass c)
	  	{
   	
	    	for (int i=0;i<datatypeProperties.size();i++)
	    		createOWLDatatypeProperty(datatypeProperties.get(i));
	    	
	    	for (int i=0;i<objectProperties.size();i++)
	    	{
	    		createOWLObjectProperty(objectProperties.get(i));
	    		String newClass=capitalize(objectProperties.get(i));
	    		createOWLNamedClass(newClass);
				OWLObjectProperty prop=owlModel.getOWLObjectProperty (owlModel.getURIForResourceName(objectProperties.get(i)));
				prop.addUnionRangeClass(owlModel.getOWLNamedClass(owlModel.getURIForResourceName(newClass)));
	    	}
	    	
	    	TreePath[] selectedPaths=checkBoxTree.getCheckBoxTreeSelectionModel().getSelectionPaths();
			
	    	for (int i=0;i<selectedPaths.length;i++)
	    	{
	    		Object[] path =selectedPaths[i].getPath();

	    			String lastNode="";
	    			String indNode="";
	    			String propertyName;
	    			
	    			if (path.length>1)
	    			{
	    				indNode=path[path.length-2].toString();
	    				lastNode=path[path.length-1].toString();
	    			}
	    			
	    			if (!lastNode.isEmpty())
	    			{
	    				
	    				String[] propValue=SplitUsingTokenizer(lastNode, "=");
	    				if (propValue.length>1) 
	    				{
	    					propertyName=normalize(propValue[0]);
	    					String propertyValue=normalize(propValue[1]);
	    					System.out.println(propertyName+"="+propertyValue);

	    					if (!indNode.isEmpty())
	    					{
	    						//c= owlModel.getOWLNamedClass(c.getURI());
	    						String individualUri=owlModel.getURIForResourceName(indNode);
	    						String propertyUri=owlModel.getURIForResourceName(propertyName);
	    						OWLIndividual ind=owlModel.getOWLIndividual(individualUri);

	    						if (propertyName!=null&&ind!=null)
	    						{
	    							if (datatypeProperties.contains(propertyName))
	    							{
	    	    						OWLDatatypeProperty prop=owlModel.getOWLDatatypeProperty(propertyUri);
	    	    						prop.addUnionDomainClass(c);
	    								ind.setPropertyValue(prop, owlModel.createRDFSLiteralOrString(propertyValue, "en"));
	    							}
	    							
	    							if (objectProperties.contains(propertyName))
	    							{
	    	    						OWLObjectProperty prop=owlModel.getOWLObjectProperty(propertyUri);
	    	    						prop.addUnionDomainClass(c);
	    	    						Collection<OWLNamedClass> rc_union=prop.getUnionRangeClasses();
	    	    						
	    	    						if (!rc_union.isEmpty() && !individuals.contains(propertyValue))
	    	    						{
	    	    							individuals.add(propertyValue);
	    	    							OWLNamedClass rc=rc_union.iterator().next();
	    	    							createOWLIndividual(rc,propertyValue);
	    	    							ind.setPropertyValue(prop, owlModel.getOWLIndividual(owlModel.getURIForResourceName(propertyValue)));
	    	    						}
	    							}
	    							
	    						}

	    					}

	    				}
	    			
	    			}
	    			
	    		}
	  	}
	  	
	  	public OWLIndividual createOWLIndividual(OWLNamedClass c, String i)
	  	{
	  		System.out.println((owlModel.getNamespaceManager().getDefaultNamespace()+i));
	  		if (owlModel.getOWLIndividual((owlModel.getNamespaceManager().getDefaultNamespace()+i))==null)
	  			c.createOWLIndividual(i);
	  		return owlModel.getOWLIndividual(owlModel.getNamespaceManager().getDefaultNamespace()+i);
	  	}
		  
	  	public OWLNamedClass createOWLNamedClass(String c)
	  	{
	  		if (owlModel.getOWLNamedClass((owlModel.getNamespaceManager().getDefaultNamespace()+c))==null)
	  			owlModel.createOWLNamedClass(c);
	  		return owlModel.getOWLNamedClass(owlModel.getNamespaceManager().getDefaultNamespace()+c);
	  	}
	  	
	  	public OWLObjectProperty createOWLObjectProperty(String p)
	  	{
	  		if (owlModel.getOWLObjectProperty((owlModel.getNamespaceManager().getDefaultNamespace()+p))==null)
	  			owlModel.createOWLObjectProperty(p);
	  		return owlModel.getOWLObjectProperty(owlModel.getNamespaceManager().getDefaultNamespace()+p);
  		}
	  	
	  	public OWLDatatypeProperty createOWLDatatypeProperty(String p)
	  	{
	  		if (owlModel.getOWLDatatypeProperty((owlModel.getNamespaceManager().getDefaultNamespace()+p))==null)
	  			owlModel.createOWLDatatypeProperty(p);
	  		return owlModel.getOWLDatatypeProperty(owlModel.getNamespaceManager().getDefaultNamespace()+p);
  		}
	  	
	    public String capitalize(String s)
	    {
	    	String newFirst=s.substring(0,1).toUpperCase();
	    	String newName=newFirst+s.substring(1);
	    	return newName;
	    }
	    		
	    public String normalize(String preValue)
	    {
	    	String processedValue=preValue;
	    	processedValue=processedValue.trim();
	    	String firstChar=processedValue.substring(0, 1);
	    	if (firstChar=="I"||firstChar=="Ý") firstChar="i";
	    	else firstChar=firstChar.toLowerCase();
	    	processedValue=firstChar+processedValue.substring(1);
	    	processedValue=processedValue.replaceAll("\\s", "");
	    	System.out.println("processedValue="+processedValue);
	    	return processedValue;
	    	
	     }
		
	    public void fillProductTree(File file)
	    {
			Html2ProductMatcher matcher=new Html2ProductMatcher(file);
			DefaultMutableTreeNode root=matcher.getRoot();
			productList=HtmlUnitDefault.parse(root);
			drawTree(productList);
	    }

	    public void serializeData()
	    {
	    	
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

       

   
	





