import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.jidesoft.swing.*;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protege.ui.ProjectManager;
import edu.stanford.smi.protege.ui.ProjectView;
import edu.stanford.smi.protege.widget.AbstractTabWidget;

import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLClass;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
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

public class IRISTab extends AbstractTabWidget implements Listener{
	
	JenaOWLModel owlModel;

	private static final long serialVersionUID = 1L;
	protected JTextField textField;
    protected JTextArea textArea;
   
    ArrayList<Product> productList;
    int lastProductCount=0;
	int rowsFilled;
	DefaultMutableTreeNode root;

 	CheckBoxTree checkBoxTree_individuals;
 	CheckBoxTree checkBoxTree_properties;
 	ArrayList<DefaultMutableTreeNode> selectedNodes;
 	private JSplitPane splitPane9;
 	private JSplitPane splitPane10;
 	private JSplitPane splitPane12;
 	private JToolBar toolBar1;
 	private JButton button16;
 	private JButton button17;
 	private JButton button18;
 	private JScrollPane scrollPane2;
 	private JSplitPane splitPane11;
 
 	private JToolBar toolBar2;
 	private JButton openButton;
 	private JButton button19;
 	private JButton button20;
 	private JButton button21;
 	private JButton button22;
 	private JButton sourceCodeButton;
 	private JButton saveButton;
 	private JButton setPropertyPreferences;
 	private JButton goodRelations;
 	private JScrollPane scrollPane3;
 	private JScrollPane scrollPane4;
 	
 	private boolean goodRelationsVocabulary_opened;
 	private String RANGE_DELIMETER;
 	
	ArrayList<String> slots, Grslots;
	ArrayList<String> individuals;
	ArrayList<String> datatypeProperties;
	ArrayList<String> objectProperties;
	ArrayList<Result> DialogBoxResults;
	ArrayList<MatchingResult> GoodRelationsMatchingResults;
 	DefaultMutableTreeNode root_individuals,root_properties;
 	UnitsofMeasurementManager um_manager;
 	File savedFile;
 	
	 	public void initialize() 
	  {
	  
	 	org.apache.log4j.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(org.apache.log4j.Level.FATAL);
	 	java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
	 	
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
	 		splitPane12 = new JSplitPane();
	 		toolBar1 = new JToolBar();
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
	 		sourceCodeButton = new JButton();
	 		scrollPane3 = new JScrollPane();
	 		scrollPane4 = new JScrollPane();
	 		setPropertyPreferences=new JButton();
	 		goodRelations=new JButton();
	 		
//	 		menuItem = new JMenuItem("Export to a serialization format");
//	 		menuItem.setOpaque(false);
//	 		//menuItem.setIcon();
//	 		popupMenu.add(menuItem);
//	 		menuItem.addActionListener(new ActionListener(){
//	 		  public void actionPerformed(ActionEvent e)
//	 		  {
//	 			  
//	 			  SerializationTab st=new SerializationTab(owlModel);
//	 			  st.setVisible(true);
//	 		  }
//	 		  });
//
	 		//======== splitPane9 ========
	 		{
	 			splitPane9.setDividerLocation(300);

	 			//======== splitPane10 ========
	 			{
	 				splitPane10.setOrientation(JSplitPane.VERTICAL_SPLIT);

	 				//======== toolBar1 ========
	 				{

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
	 					//button19.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Import16.gif"));
	 					//button19.setToolTipText("Import file");
	 					//toolBar2.add(button19);

	 					//---- button20 ----
	 					//button20.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\jlfgr-1_0\\toolbarButtonGraphics\\general\\Export16.gif"));
	 					//button20.setToolTipText("Export file");
	 					//toolBar2.add(button20);
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
	 					sourceCodeButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Default\\SourceCode.gif"));
	 					sourceCodeButton.addActionListener(this);
	 					sourceCodeButton.setToolTipText("Export to a serialization format");
	 					toolBar2.add(sourceCodeButton);
	 					
	 					saveButton.setIcon(new ImageIcon("..\\icons\\Save16.gif"));
	 					saveButton.addActionListener(this);
	 					saveButton.setToolTipText("Save");
	 					toolBar2.add(saveButton);
	 				}
	 				splitPane11.setTopComponent(toolBar2);
	 				splitPane11.setBottomComponent(splitPane12);
	 				splitPane12.setDividerLocation(450);
	 				splitPane12.setLeftComponent(scrollPane3);
	 				splitPane12.setRightComponent(scrollPane4);	

	 			}
	 			splitPane9.setRightComponent(splitPane11);
	 		}
	 		add(splitPane9, BorderLayout.CENTER);	
	 		um_manager=new UnitsofMeasurementManager();
	  }

	  	public static void main(String[] args) 

	  {
			edu.stanford.smi.protege.Application.main(args);	
	  }

	  	public void drawTree(ArrayList<Product> productList )
	 	{
	  		drawProductsTree(productList);
	  		drawPropertiesTree(productList);
	 	}
	  	
		public void drawPropertiesTree(ArrayList<Product> productList)
		{
		   root_properties=new DefaultMutableTreeNode("PRODUCT PROPERTIES");
		   ArrayList<String> allProperties=new ArrayList<String>();
		   for (int j=0;j<productList.size();j++)
		   {
			   Product p=productList.get(j);
			    for (int y=0;y<p.propertyName.size()&&y<p.propertyValue.size();y++)
			    {
			    	if (!allProperties.contains(p.propertyName.get(y)))
			    	{
			    		allProperties.add(p.propertyName.get(y));
			    		root_properties.add(new DefaultMutableTreeNode(p.propertyName.get(y)));
			    	}
			    }
		   }
 		   
			checkBoxTree_properties = new CheckBoxTree(root_properties) {
	            @Override
	            public boolean isCheckBoxVisible(TreePath path) {
	                if (((DefaultMutableTreeNode) path.getLastPathComponent()).isLeaf())
	                	return true;
	                else
	                	return false;
	                	
	            }
			};
			DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)  checkBoxTree_properties.getActualCellRenderer();
	 		renderer.setClosedIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Multicolored\\RDFPropertyInherited.gif"));
	 		renderer.setOpenIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Multicolored\\RDFPropertyInherited.gif"));
	 		renderer.setLeafIcon(new ImageIcon("C:\\Users\\user\\Desktop\\icons\\Multicolored\\RDFPropertyInherited.gif"));
	 		checkBoxTree_properties.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
		
//				@Override
				public void valueChanged(TreeSelectionEvent arg0) {
					// TODO Auto-generated method stub
					
					
				}
			});

	 		checkBoxTree_properties.getCheckBoxTreeSelectionModel().setDigIn(false);
	 		scrollPane4.setViewportView(checkBoxTree_properties);
	 		scrollPane4.revalidate();  
	 		scrollPane4.repaint();
		}
		
		public void drawProductProperties(DefaultMutableTreeNode cp, String propertyName, String propertyValue)
		{
			
			if ((propertyName!=null&&!propertyName.isEmpty())&&(propertyValue!=null&&!propertyValue.isEmpty()))
			{
				cp.add(new DefaultMutableTreeNode(propertyName+"= "+propertyValue));
			}
		}
		
		public void drawProductsTree(ArrayList<Product> productList)
		{
	  		root_individuals=new DefaultMutableTreeNode("PRODUCTS");
	 		Product p;
	 		DefaultMutableTreeNode cp;
			for (int i=0;i<productList.size();i++)
		       {
		    		   p=productList.get(i);
		    		   cp=new DefaultMutableTreeNode("Product_"+i); 
		    		   
		    		   root_individuals.add(cp);
		    		   drawProductProperties(cp,"Title", p.title);
		    		   drawProductProperties(cp,"Brand", p.brand);
		    		   drawProductProperties(cp,"ProductID", p.productID);
		    		   drawProductProperties(cp,"Description", p.description);
		    		   drawProductProperties(cp,"Image Link", p.imgLink);
		    		   for (int y=0;y<p.features.size();y++)
		    			   drawProductProperties(cp, "Feature", p.features.get(y));
		    		   for (int y=0;y<p.propertyName.size()&&y<p.propertyValue.size();y++)
		    			   drawProductProperties(cp, p.propertyName.get(y), p.propertyValue.get(y));
		    		   for (int y=0;y<p.includes.size();y++)
		    			   drawProductProperties(cp, "Includes", p.includes.get(y));
		    		  // cp.add(new DefaultMutableTreeNode("Title= "+p.title));
		    		  // cp.add(new DefaultMutableTreeNode("Image Link= "+p.imgLink));
		    		  // for (int y=0;y<p.propertyName.size()&&y<p.propertyValue.size();y++)
		    			//   cp.add(new DefaultMutableTreeNode(p.propertyName.get(y)+"= "+p.propertyValue.get(y)));
		    		  
		    }
			
			checkBoxTree_individuals = new CheckBoxTree(root_individuals) {
	            @Override
	            public boolean isCheckBoxVisible(TreePath path) {
	                if (!((DefaultMutableTreeNode) path.getLastPathComponent()).isLeaf() && !((DefaultMutableTreeNode) path.getLastPathComponent()).isRoot())
	                	return true;
	                else
	                	return false;
	                	
	            }
	        };

	 		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)  checkBoxTree_individuals.getActualCellRenderer();
	 		renderer.setClosedIcon(edu.stanford.smi.protege.resource.Icons.getInstanceIcon());
	 		renderer.setOpenIcon(edu.stanford.smi.protege.resource.Icons.getInstanceIcon());
	 		renderer.setLeafIcon(edu.stanford.smi.protege.resource.Icons.getInstanceIcon());
	 		
	 		checkBoxTree_individuals.getCheckBoxTreeSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
	 		
//				@Override
				public void valueChanged(TreeSelectionEvent arg0) {
//					// TODO Auto-generated method stub
//					//checkBoxTree_individuals.getCheckBoxTreeSelectionModel().addSelectionPaths(new TreePath[] {arg0.getPath()});
//					//checkBoxTree_individuals.getCheckBoxTreeSelectionModel().addSelectionPaths(new TreePath[] {arg0.getPath()});
//			         boolean checked = false;
//			         // your code here.
//			         TreePath checkedPath = arg0.getPath();
//			         if (checkedPath != null) {
//			            if (checkBoxTree_individuals.getCheckBoxTreeSelectionModel().isPathSelected(checkedPath)) {
//			               checked = true;
//			            } else {
//			               checked = false;
//			            }
//			         }}}
				}}
				
			);

	 		checkBoxTree_individuals.getCheckBoxTreeSelectionModel().setDigIn(false);
	 		//checkBoxTree_individuals.addMouseListener(this);
	 		
	 		scrollPane3.setViewportView(checkBoxTree_individuals);
	 		scrollPane3.revalidate();  
	 		scrollPane3.repaint();
	  	}
		       
	  	public void actionPerformed(ActionEvent e) 
	  	{
	    	JFileChooser  fc = new JFileChooser();
	    	JFileChooser  fc1 = new JFileChooser();

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
	            int returnVal = fc1.showSaveDialog(this);
	       	 
	            if (returnVal == JFileChooser.APPROVE_OPTION) 
	            {
	                File file = fc1.getSelectedFile();
	                savedFile=file;
	                if (file.getPath().toString().endsWith("myOwl.owl"))
	                {
	                	JOptionPane.showMessageDialog(null, "Save your ontology model to a file other than \"myOwl.owl\"");
	                }
	                else
	                {
	                	try {
							owlModel.save(file.toURI());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	            } 
	        	//saveOntologytoFile();
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
	        	saveOntology(null);
	        	setPropertyPreferences.setEnabled(false);
	        	goodRelations.setEnabled(false);
	        }

	        if (e.getSource() == setPropertyPreferences) 
	        { 
	        	getSelectionData();
	        	ArrayList<Result> DialogBoxResults=getPropertyPreferences();
	        	saveOntology(DialogBoxResults);
	        	goodRelations.setEnabled(false);
	        	setPropertyPreferences.setEnabled(false);
	        }
	        
	        if (e.getSource() == sourceCodeButton) 
	        { 
	 			  SerializationTab st=new SerializationTab(owlModel, savedFile);
	 			  st.setVisible(true);
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
        	         //System.out.println(absProperty.getNamespace());
        	         if (absProperty.getNamespace().contains("http://purl.org/goodrelations/v1#")||absProperty.getNamespace().contains("http://schema.org")||absProperty.getNamespace().contains("http://xmlns.com/foaf/0.1/"))
        	         {
        	        	 Grslots.add(absProperty.getPrefixedName());
        	         }
        	    }
        	}
        	
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
	    	  
	  	public ArrayList<Result> getPropertyPreferences()
	  {
	  	
        SetPropertyPreferences sp = new SetPropertyPreferences(slots, um_manager.getUnitsofMeasurement(), ModalityType.APPLICATION_MODAL);
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
		 return DialogBoxResults;
	  }
	  
	  	public void getSelectionData()
	    {
		  	TreePath[] selectedPaths_individuals=checkBoxTree_individuals.getCheckBoxTreeSelectionModel().getSelectionPaths();
		  	TreePath[] selectedPaths_properties=checkBoxTree_properties.getCheckBoxTreeSelectionModel().getSelectionPaths();

	    	for (int i=0;i<selectedPaths_individuals.length;i++)
	    	{
	    		Object[] path=selectedPaths_individuals[i].getPath();
	    		String indNode="";
	    			if (path.length==2)
	    				indNode=path[path.length-1].toString();
	    			
	    			if (!indNode.isEmpty()&& !individuals.contains(indNode))
	    					individuals.add(indNode);
	    	}
	    	
	    	for (int i=0;i<selectedPaths_properties.length;i++)
	    	{
	    		Object[] path=selectedPaths_properties[i].getPath();
	    		String lastNode="";
	    		String propertyName="";
	    		
    			if (path.length==2)
    				lastNode=path[path.length-1].toString();
    			
	    			if (!lastNode.isEmpty())
	    			{
	    				propertyName=normalize(lastNode);
	    				if (!propertyName.isEmpty() && !slots.contains(propertyName))
	    						slots.add(propertyName);
	    			}
	    			
	    	}

	    	for (int i=0;i<slots.size();i++)
	    		datatypeProperties.add(slots.get(i));
	    	}  

	  	public void saveOntology(ArrayList<Result> DialogBoxResults)
	  	{
		    OWLNamedClass c;
	    	createOWLNamedClass("Laptop");
	    	c= owlModel.getOWLNamedClass(owlModel.getURIForResourceName("Laptop"));
	    	
	  		for (int i=0;i<individuals.size();i++)
				createOWLIndividual(c,individuals.get(i));
	  		
	    	if (goodRelationsVocabulary_opened)
	    		saveGoodRelationsVocabulary(c);
	    	else
	    		saveOntologyModel(c,DialogBoxResults);
	  }
	  	
	  	public void saveGoodRelationsVocabulary(OWLNamedClass c)
	  	{
			
			String quantitativeProductOrServiceProperty="http://purl.org/goodrelations/v1#quantitativeProductOrServiceProperty";
			String qualitativeProductOrServiceProperty="http://purl.org/goodrelations/v1#qualitativeProductOrServiceProperty";
			String datatypeProductOrServiceProperty="http://purl.org/goodrelations/v1#datatypeProductOrServiceProperty";

			
	    	TreePath[] selectedPaths_individuals=checkBoxTree_individuals.getCheckBoxTreeSelectionModel().getSelectionPaths();
	    	TreePath[] selectedPaths_properties=checkBoxTree_properties.getCheckBoxTreeSelectionModel().getSelectionPaths();
	    	Object[] path;
	    	DefaultMutableTreeNode indTreeNode;
	    	String indNode,lastNode,propertyName,propertyValue,individualUri,GRPropertyUri;
	    	String[] propValue;
	    	OWLIndividual ind,vind;
	    	MatchingResult mr;
	    	OWLNamedClass rangeClass;
	    	RDFSDatatype range;
	    	
	    	for (int i=0;i<selectedPaths_individuals.length;i++)
	    	{
	    		path=selectedPaths_individuals[i].getPath();
	    		if (path.length==2)
	    		{
	    		indTreeNode=(DefaultMutableTreeNode)path[path.length-1];
	    		indNode=path[path.length-1].toString();
	    		
	    		for (int m=0;m<indTreeNode.getChildCount();m++)
	    		{
	    			lastNode=indTreeNode.getChildAt(m).toString();
	    			propValue=SplitUsingTokenizer(lastNode, "=");
	    			propertyName="";
	    				if (propValue.length>1) 
	    				{
	    					propertyName=normalize(propValue[0]);
	    					propertyValue=normalize(propValue[1]);

	    					if (!indNode.isEmpty()&&slots.contains(propertyName))
	    					{
	    						
	    						individualUri=owlModel.getURIForResourceName(indNode);
	    						ind=owlModel.getOWLIndividual(individualUri);

	    						for (int j=0;j<GoodRelationsMatchingResults.size();j++)
	    						{
	    							mr=GoodRelationsMatchingResults.get(j);
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
	    											GRPropertyUri="";
	    											
	    											if (!mr.GR_slotName.contains("/"))
	    											{
	    												GRPropertyUri="http://purl.org/goodrelations/v1#"+mr.GR_slotName;
	    											}
	    											else
	    												GRPropertyUri=mr.GR_slotName;
	    											
	    												RDFProperty GRProperty=owlModel.getRDFProperty(GRPropertyUri);
	    												if (GRProperty.hasObjectRange())
	    												{
	    													rangeClass=owlModel.getOWLNamedClass(GRProperty.getRange().getURI());
	    													vind=createOWLIndividual(rangeClass,propertyValue);
	    													GRProperty.addUnionDomainClass(c);
	    													ind.setPropertyValue(GRProperty, vind);
	    												}
	    												else
	    												{
	    													range=owlModel.getRDFSDatatypeByURI(GRProperty.getRange().getURI());
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

	  	public void saveOntologyModel(OWLNamedClass c,ArrayList<Result> DialogBoxResults)
	  	{
   	
	    	for (int i=0;i<datatypeProperties.size();i++)
	    		createOWLDatatypeProperty(datatypeProperties.get(i));
	    	String newClass;
	    	for (int i=0;i<objectProperties.size();i++)
	    	{
	    		createOWLObjectProperty(objectProperties.get(i));
	    		newClass=capitalize(objectProperties.get(i));
	    		createOWLNamedClass(newClass);
				OWLObjectProperty prop=owlModel.getOWLObjectProperty (owlModel.getURIForResourceName(objectProperties.get(i)));
				prop.addUnionRangeClass(owlModel.getOWLNamedClass(owlModel.getURIForResourceName(newClass)));
	    	}
	    	
	    	TreePath[] selectedPaths_individuals=checkBoxTree_individuals.getCheckBoxTreeSelectionModel().getSelectionPaths();
	    	Object[] path;
	    	DefaultMutableTreeNode indTreeNode;
	    	String indNode,lastNode,propertyName,propertyValue,individualUri,propertyUri;
	    	String[] propValue;
	    	OWLIndividual ind;
	    	Result r;
	    	Pattern pattern;
	    	Matcher matcher;
	    	OWLDatatypeProperty prop;
	    	OWLObjectProperty propo;
	    	OWLNamedClass rc;
	    	
	    	for (int i=0;i<selectedPaths_individuals.length;i++)
	    	{
	    		path=selectedPaths_individuals[i].getPath();
	    		if (path.length==2)
	    		{
	    		indTreeNode=(DefaultMutableTreeNode)path[path.length-1];
	    		indNode=path[path.length-1].toString();
	    		
	    		for (int m=0;m<indTreeNode.getChildCount();m++)
	    		{
	    			lastNode=indTreeNode.getChildAt(m).toString();
	    			propValue=SplitUsingTokenizer(lastNode, "=");
	    			propertyName="";
	    				if (propValue.length>1) 
	    				{
	    					propertyName=normalize(propValue[0]);
	    					propertyValue=normalize(propValue[1]);

	    					if (!indNode.isEmpty()&&slots.contains(propertyName))
	    					{
	    						individualUri=owlModel.getURIForResourceName(indNode);
	    						propertyUri=owlModel.getURIForResourceName(propertyName);
	    						ind=owlModel.getOWLIndividual(individualUri);

	    						if (propertyName!=null&&ind!=null)
	    						{
	    							for (int d=0;d<DialogBoxResults.size();d++)
	    							{
	    								r=DialogBoxResults.get(d);
	    								
	    								if (r.pname.contains(propertyName)&&r.pname.length()==propertyName.length()&&r.removeUnit)
	    								{
	    									pattern = Pattern.compile("[0123456789]*[.]?[0123456789]{0,4}");
	    									matcher = pattern.matcher(propertyValue);
	    									if (matcher.find())
	    										propertyValue=matcher.group(0);
	    								}
	    							}
	    							
	    							if (datatypeProperties.contains(propertyName))
	    							{
	    	    						prop=owlModel.getOWLDatatypeProperty(propertyUri);
	    	    						prop.addUnionDomainClass(c);
	    								ind.setPropertyValue(prop, owlModel.createRDFSLiteralOrString(propertyValue, "en"));
	    							}
	    							
	    							if (objectProperties.contains(propertyName))
	    							{
	    	    						propo=owlModel.getOWLObjectProperty(propertyUri);
	    	    						propo.addUnionDomainClass(c);
	    	    						Collection<OWLNamedClass> rc_union=propo.getUnionRangeClasses();
	    	    						
	    	    						if (!rc_union.isEmpty() && !individuals.contains(propertyValue))
	    	    						{
	    	    							individuals.add(propertyValue);
	    	    							rc=rc_union.iterator().next();
	    	    							createOWLIndividual(rc,propertyValue);
	    	    							ind.setPropertyValue(propo, owlModel.getOWLIndividual(owlModel.getURIForResourceName(propertyValue)));
	    	    						}
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
	  		//System.out.println((owlModel.getNamespaceManager().getDefaultNamespace()+i));
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
	    	if (firstChar.contains("I")||firstChar.contains("Ý")) firstChar="i";
	    	else firstChar=firstChar.toLowerCase();
	    	processedValue=firstChar+processedValue.substring(1);
	    	processedValue=processedValue.replaceAll("\\s", "");
	    	//System.out.println("processedValue="+processedValue);
	    	return processedValue;
	    	
	     }
		
	    public void fillProductTree(File file)
	    {
			Html2ProductMatcher matcher=new Html2ProductMatcher(file);
			DefaultMutableTreeNode root=matcher.getRoot();
			//int page_count=Integer.parseInt(matcher.page_range);
			productList=HtmlUnitDefault.parse(root,matcher.next_page,matcher.page_range,matcher.base_uri,matcher.page_uri,matcher.classToSearch);
			drawTree(productList);
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

		@Override
		public void mouseClicked(MouseEvent e) {
			
//		    if (SwingUtilities.isRightMouseButton(e)) {
//		        int row = checkBoxTree_individuals.getClosestRowForLocation(e.getX(), e.getY());
//		        checkBoxTree_individuals.setSelectionRow(row);
//		        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
//	                       checkBoxTree_individuals.getLastSelectedPathComponent();
//		       // TreePath path=checkBoxTree_individuals.getEditingPath();
//		        
//		        if (!node.isLeaf()&&!node.isRoot())
//		        {
//		        	popupMenu.show(e.getComponent(), e.getX(), e.getY());
//		        }
//		    }
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public class IgnoreErrorsHTMLParserListener implements HTMLParserListener { 
		    // Overrides

		    public void error(String message, URL url, int line, int column, String key) { }
		    public void warning(String message, URL url, int line, int column, String key) { }
			@Override
			public void error(String message, URL url, String html, int line,
					int column, String key) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void warning(String message, URL url, String html, int line,
					int column, String key) {
				// TODO Auto-generated method stub
				
			}
		}

		public class IgnoreErrorsIncorrectnessListener implements IncorrectnessListener { 
		    // Override 
		    public void notify(String message, Object origin) { }
		}
}

       

   
	





