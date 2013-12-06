import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.IOUtils;

public class Html2ProductMatcher  {
	DefaultMutableTreeNode root=null;
	public static String next_page;
	public static String page_range;
	public static String base_uri;
	public static String page_uri;
	public static String classToSearch;
	//(AMAZON) 
	public Html2ProductMatcher(File file){
		Html2ProductNode uNode=new Html2ProductNode();
		//DefaultMutableTreeNode proot=null;
		try {
			root=readFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
//		uNode.select="div";
//		uNode.atrr="id";
//		uNode.value="result_";
//		
//		root = new DefaultMutableTreeNode("select=div+attr=id+value=result_+selectAs=product");
//		root.setUserObject(uNode);
//		
//		Html2ProductNode uNode1=new Html2ProductNode();
//		uNode1.select="span";
//		uNode1.atrr="class";
//		uNode1.value="lrg bold";
//		uNode1.selectAs="product.title";
//		uNode1.getMethod="asText";
//		uNode1.order=0;
//		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("");
//		child1.setUserObject(uNode1);
//		root.add(child1);
//		
//		Html2ProductNode uNode2=new Html2ProductNode();
//		uNode2.select="img";
//		uNode2.atrr="src";
//		uNode2.value="";
//		uNode2.order=0;
//		uNode2.getMethod="Src";
//		uNode2.selectAs="product.imgLink";
//		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("");
//		child2.setUserObject(uNode2);
//		root.add(child2);
//		
//		
//		
//		Html2ProductNode uNode2_0=new Html2ProductNode();
//		uNode2_0.select="div";
//		uNode2_0.atrr="class";
//		uNode2_0.value="image imageContainer";
//		DefaultMutableTreeNode child2_0 = new DefaultMutableTreeNode("");
//		child2_0.setUserObject(uNode2_0);
//		root.add(child2_0);
//		
//		Html2ProductNode uNode3=new Html2ProductNode();
//		uNode3.select="a";
//		uNode3.atrr="href";
//		uNode3.getMethod="href";
//		uNode3.order=0;
//		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("");
//		child3.setUserObject(uNode3);
//		child2_0.add(child3);
//		
//		Html2ProductNode uNode4=new Html2ProductNode();
//		uNode4.select="div";
//		uNode4.atrr="id";
//		uNode4.value="prodDetails";
//		uNode4.order=0;
//		DefaultMutableTreeNode child4 = new DefaultMutableTreeNode("");
//		child4.setUserObject(uNode4);
//		child3.add(child4);
//		
//		
//		Html2ProductNode uNode6=new Html2ProductNode();
//		uNode6.select="td";
//		uNode6.atrr="class";
//		uNode6.value="label";
//		uNode6.getMethod="asText";
//		uNode6.selectAs="product.propertyName";
//		uNode6.order=0;
//		DefaultMutableTreeNode child6 = new DefaultMutableTreeNode("");
//		child6.setUserObject(uNode6);
//		child4.add(child6);
//		
//		Html2ProductNode uNode7=new Html2ProductNode();
//		uNode7.select="td";
//		uNode7.atrr="class";
//		uNode7.value="value";
//		uNode7.getMethod="asText";
//		uNode7.selectAs="product.propertyValue";
//		uNode7.order=0;
//		DefaultMutableTreeNode child7 = new DefaultMutableTreeNode("");
//		child7.setUserObject(uNode7);
//		child4.add(child7);
//		
//		
//	}
	
	public void fillPnode(Html2ProductNode pnode)
	{
		String [] tokens=SplitUsingTokenizer(pnode.fullText, ",");
		//System.out.println("PNODE:" + pnode.fullText);
		String val;
		
		for (int i=0;i<tokens.length;i++)
		{
			val=tokens[i].substring((tokens[i].indexOf('('))+1,(tokens[i].indexOf(')')));
			
			if (tokens[i].toLowerCase().trim().startsWith("select"))
			{
				pnode.select=val;
				//System.out.println("SELECT:" + pnode.select);
			}
			
			if (tokens[i].toLowerCase().trim().startsWith("attr"))
			{
				pnode.atrr=val;
				//System.out.println("ATRR:" + pnode.atrr);
			}
			
			if (tokens[i].toLowerCase().trim().startsWith("value"))
			{
				pnode.value=val;
				//System.out.println("VALUE:" + pnode.value);
			}
			
			if (tokens[i].toLowerCase().trim().startsWith("as"))
			{
				pnode.selectAs=val;
				//System.out.println("SELECTAS:" + pnode.selectAs);
			}
			
			if (tokens[i].toLowerCase().trim().startsWith("getmethod"))
			{
				pnode.getMethod=val;
				//System.out.println("GETMETHOD:" + pnode.getMethod);
			}
			
			if (tokens[i].toLowerCase().trim().startsWith("order"))
			{
				pnode.order=Integer.parseInt(val);
				//System.out.println("ORDER:" + pnode.order);
			}
			
		}
		
	}
	
	public DefaultMutableTreeNode readFile(File file) throws IOException{
		
			FileInputStream inputStream = new FileInputStream(file);
			ArrayList<DefaultMutableTreeNode> nodeList=new ArrayList<DefaultMutableTreeNode>();
			String fString,pString,fileText;
		    try {
		        fileText = IOUtils.toString(inputStream);
		       
		        int index=fileText.indexOf("NEXT_PAGE");
		        fString=fileText.substring(0,index-1);
		        pString=fileText.substring(index);
		        buildParameters(pString);
		    } finally {
		        inputStream.close();
		    }
		    String [] prenodes=SplitUsingTokenizer(fString, "[];");
		    System.out.println("PRENODES: "+prenodes.length);
		    Html2ProductNode pnode;
		    DefaultMutableTreeNode newnode;
		    Html2ProductNode node_i;
		    int startindex,stopindex;
		    String substring;
		    Html2ProductNode node_j;
		    
		    for (int i=0;i<prenodes.length;i++)
		    	if (!prenodes[i].trim().isEmpty())
		    	{
		    		pnode=new Html2ProductNode();
		    		pnode.fullText=prenodes[i];
		    		fillPnode(pnode);
		    		newnode=new DefaultMutableTreeNode(pnode);
		    		nodeList.add(newnode);
		    	}
		    
		    for (int i=0;i<nodeList.size();i++)
		    {
		    	
		    	node_i=(Html2ProductNode) nodeList.get(i).getUserObject();
		    	if (fString.charAt(fString.indexOf(node_i.fullText)+node_i.fullText.length())=='[')
		    	{
		    		startindex=fString.indexOf(node_i.fullText)+node_i.fullText.length()+1;
		    		int o_count=1;
		    		stopindex=-1;
		    		for (int j=startindex;j<fString.length();j++)
		    		{
		    			if (fString.charAt(j)=='[')
		    				o_count++;
		    			if (fString.charAt(j)==']')
		    				o_count--;
		    			if (o_count==0)
		    			{
		    				stopindex=j;
		    				break;
		    			}
		    		}
		    		
		    		//stopindex=fString.indexOf("[",startindex);
		    		if (stopindex==-1) stopindex=fString.length()-1;
		    		substring=fString.substring(startindex, stopindex);
		    		for (int j=0;j<nodeList.size();j++)
		    		{
		    			node_j=(Html2ProductNode)nodeList.get(j).getUserObject();
		    			if (substring.contains(node_j.fullText)&&!node_j.fullText.isEmpty())
		    			{
		    					nodeList.get(i).add(nodeList.get(j));
		    					System.out.println(i+"-"+j);
		    			}
		    		}
		    					
		    	}
		    }
		    return nodeList.get(0);   
	}

	public static void buildParameters(String pString)
	{
		int parameter_counter=0;
		int index,endIndex;
		
		while (pString.indexOf(":{")>=0 && pString.indexOf("}")>=0 && pString.indexOf("}")>pString.indexOf(":{"))
		{
			
			index=pString.indexOf(":{");
			endIndex=pString.indexOf("}");

			if (parameter_counter==0)

				next_page=pString.substring(index+2, endIndex);

			if (parameter_counter==1)

				page_range=pString.substring(index+2, endIndex);

			if (parameter_counter==2)
			
				base_uri=pString.substring(index+2, endIndex);

			if (parameter_counter==3)
			
				page_uri=pString.substring(index+2, endIndex);

			if (parameter_counter==4)
			
				classToSearch=pString.substring(index+2, endIndex);

			
			pString=pString.substring(endIndex+1);
			parameter_counter++;
		}
		
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
//		(ARÇELÝK) public Html2ProductMatcher(String filePath){
//			Html2ProductNode uNode=new Html2ProductNode();
//			
//			uNode.select="div";
//			uNode.atrr="class";
//			uNode.value="urunListeIcDiv";
//			
//			root = new DefaultMutableTreeNode("select=div+attr=id+value=result_+selectAs=product");
//			root.setUserObject(uNode);
//			
//			Html2ProductNode uNode1=new Html2ProductNode();
//			uNode1.select="div";
//			uNode1.atrr="class";
//			uNode1.value="urunListe_marka";
//			uNode1.selectAs="product.brand";
//			uNode1.getMethod="asText";
//			uNode1.order=0;
//			DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("");
//			child1.setUserObject(uNode1);
//			root.add(child1);
//			
//			Html2ProductNode uNode2=new Html2ProductNode();
//			uNode2.select="div";
//			uNode2.atrr="class";
//			uNode2.value="urunListe_urunkodu";
//			uNode2.order=0;
//			uNode2.getMethod="asText";
//			uNode2.selectAs="product.productID";
//			DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("");
//			child2.setUserObject(uNode2);
//			root.add(child2);
//			
//			
//			
//			Html2ProductNode uNode2_0=new Html2ProductNode();
//			uNode2_0.select="div";
//			uNode2_0.atrr="class";
//			uNode2_0.value="urunListe_detayGoster";
//			DefaultMutableTreeNode child2_0 = new DefaultMutableTreeNode("");
//			child2_0.setUserObject(uNode2_0);
//			root.add(child2_0);
//			
//			Html2ProductNode uNode3=new Html2ProductNode();
//			uNode3.select="a";
//			uNode3.atrr="href";
//			uNode3.getMethod="href";
//			uNode3.order=0;
//			DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("");
//			child3.setUserObject(uNode3);
//			child2_0.add(child3);
//			
//			Html2ProductNode uNode4=new Html2ProductNode();
//			uNode4.select="div";
//			uNode4.atrr="id";
//			uNode4.value="ctl00_u20_ascUrunDetay_pnlTabs";
//			uNode4.order=0;
//			DefaultMutableTreeNode child4 = new DefaultMutableTreeNode("");
//			child4.setUserObject(uNode4);
//			child3.add(child4);
//			
//			
//			Html2ProductNode uNode6=new Html2ProductNode();
//			uNode6.select="a";
//			uNode6.atrr="link";
//			uNode6.getMethod="link";
//			uNode6.order=1;
//			DefaultMutableTreeNode child6 = new DefaultMutableTreeNode("");
//			child6.setUserObject(uNode6);
//			child4.add(child6);
//			
//			Html2ProductNode uNode7=new Html2ProductNode();
//			uNode7.select="table";
//			uNode7.atrr="id";
//			uNode7.value="ctl00_frmMain_ascUrunOzellik_dtParametreGrup";
//			uNode7.order=0;
//			DefaultMutableTreeNode child7 = new DefaultMutableTreeNode("");
//			child7.setUserObject(uNode7);
//			child6.add(child7);
//			
//			Html2ProductNode uNode8=new Html2ProductNode();
//			uNode8.select="td";
//			uNode8.atrr="class";
//			uNode8.value="gridUrunOzellikDetayBaslik";
//			uNode8.getMethod="asText";
//			uNode8.selectAs="product.propertyName";
//			uNode8.order=0;
//			DefaultMutableTreeNode child8 = new DefaultMutableTreeNode("");
//			child8.setUserObject(uNode8);
//			child7.add(child8);
//			
//			Html2ProductNode uNode9=new Html2ProductNode();
//			uNode9.select="td";
//			uNode9.atrr="class";
//			uNode9.value="gridUrunOzellikDetayIcerik";
//			uNode9.getMethod="asText";
//			uNode9.selectAs="product.propertyValue";
//			uNode9.order=0;
//			DefaultMutableTreeNode child9 = new DefaultMutableTreeNode("");
//			child9.setUserObject(uNode9);
//			child7.add(child9);
//			
//			
//		}
	

	
	public DefaultMutableTreeNode getRoot(){
		return root;
	}
	
}

