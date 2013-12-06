import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.params.DefaultedHttpParams;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlBlink;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLAnchorElement;


	public class HtmlUnitDefault {


	    static WebClient webClient;
	    static Html2ProductMatcher matcher;
	    static List<DefaultMutableTreeNode> roots;
	    static List<HtmlPage> pages;
		static DefaultMutableTreeNode root;
		static String rootPage;
		static ArrayList<Product> productList;
		static String page_baseUri_global,baseUri_global,classToSearch_global;
		static int productCount_in_previousPage;
		static String pageRange;
		static String nextPage_xPath;
		
		public static ArrayList<Product> parse(DefaultMutableTreeNode root, String next_page, String page_range, String page_baseUri, String baseUri, String classToSearch)
		{
			//getParameters(String baseURI,String classToSearch;int pageCount);
			//page_baseUri="http://www.amazon.com";
			//String baseUri="http://www.amazon.com/s/ref=sr_nr_n_1?rh=n%3A565108%2Ck%3Alaptop&keywords=laptop&ie=UTF8&qid=1374832151&rnid=2941120011";
			//String baseUri="http://www.amazon.com/s/ref=amb_link_362805182_21?ie=UTF8&hidden-keywords=B005CWHZP4|B005CWIGHU|B005CWIN1E|B005CWIRPG|B005CWIVYI|B005CWJ1DI|B005CWIZ4O|B005CWJ76O|B005CWJ3MM|B005CWJ8YA|B005CWJABQ|B005CWJB5G|B00746YD24|B0074703CM|B007470SMM|B0074712UY|B007471D2Q|B007471PZQ|B0074721BI|B007473FNQ|B007473X6A|B007474DSM&node=565108&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=browse&pf_rd_r=0D50H0CWFKHCKA11QRQ1&pf_rd_t=101&pf_rd_p=1538654782&pf_rd_i=2956501011";
			//page_baseUri="http://www.arcelik.com.tr";
			//String baseUri="http://www.arcelik.com.tr/buzdolabi-ve-derin-dondurucular-buzdolaplari.html";
			//int pageCount=3; //20
			
			nextPage_xPath=next_page;
			pageRange=page_range;
			page_baseUri_global=page_baseUri;
			baseUri_global=baseUri;
			classToSearch_global=classToSearch;
			int start_page=0;
			int stop_page=0;
			
			if(pageRange.contains("-"))
			{
				
				String [] range=pageRange.split("-");
				//System.out.println("spage0"+range[0]+"spage1"+range[1]);
				start_page=Integer.parseInt(range[0]);
				stop_page=Integer.parseInt(range[1]);
			}
			else
			{
				stop_page=Integer.parseInt(pageRange);
				start_page=Integer.parseInt(pageRange);
			}
			
			roots=new ArrayList<DefaultMutableTreeNode>();
			pages=new ArrayList<HtmlPage>();
			productList=new ArrayList<>();
			
			webClient=setWebClient();
			HtmlPage page= connectPage(baseUri);
			Html2ProductNode r_node=(Html2ProductNode) root.getUserObject();
		
			roots.add(root);
			pages.add(page);
			productCount_in_previousPage=0;
			
			String nodeText;
			int productCount_inPage;
			Product newProduct;
			
			for (int i=start_page;i<=stop_page;i++)
			{
				System.out.println("PAGE= "+i);
				nodeText="";
				productCount_inPage=getProductCount_inPage(page,root);
				System.out.println("PC: "+productCount_inPage);
				for (int j=0;j<productCount_inPage;j++)
				{
					System.out.println("PRODUCT= "+j);
					newProduct=new Product();
					productList.add(newProduct);
					ProcessHtmlTree(page,root,nodeText,newProduct);
					nodeText="";
				}

				if ((i+1)<=stop_page)
				{
					page=getNextPage(page);
					pages.clear();
					pages.add(page);
					roots.clear();
					roots.add(root);
					productCount_in_previousPage=productList.size();
				}
			}
			return productList;
			
		}	
		
		public static HtmlPage getNextPage(HtmlPage page)
		{
			System.out.println("NextPAGE:" + nextPage_xPath);
			
			if (!nextPage_xPath.startsWith("SELECT"))
			{
				String currentURL=page.getUrl().toString();
				int startindex=9;
				int stopindex=nextPage_xPath.length()-1;
				//String phrase=nextPage_xPath.substring(startindex,stopindex);
				String phrase=nextPage_xPath;
				String pageNum="";
				String[] tkos=nextPage_xPath.split("=");
				String pre=tkos[1].substring(1);
				
				if (currentURL.contains("="))
				{
					String pageNumSt=currentURL.substring(phrase.indexOf("=")+1);
					for (int i=0;i<pageNumSt.length();i++)
					{
						String c=""+pageNumSt.charAt(i);
					    try {
					        int h=Integer.parseInt(c);
					        pageNum=pageNum+h;
					    } catch (NumberFormatException nfe) {}
					}
				}
				
				System.out.println("PN: "+pageNum+" "+pre);
				int pageNumber=1;
				try{
					pageNumber=Integer.parseInt(pageNum);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				//String oldPhrase=phrase.replaceFirst(pageNumber+"", Integer.toString(pageNumber));
				pageNumber++;

				//String newPhrase=phrase.replaceFirst(pageNum, Integer.toString(pageNumber));
				String next=page.getUrl().toString().replaceFirst((pre+"="+(pageNumber-1)), (pre+"="+(pageNumber)));
				HtmlPage nextPage;
				//String next=anc.getAttribute(nextpagenode.getMethod);
				System.out.println("next:"+next);
				nextPage=connectLink(page,next);
				return nextPage;
			}
			
			String [] tokens=SplitUsingTokenizer(nextPage_xPath, ",");
			//
			String val;
			Html2ProductNode nextpagenode=new Html2ProductNode();
			
			for (int i=0;i<tokens.length;i++)
			{
				val=tokens[i].substring((tokens[i].indexOf('('))+1,(tokens[i].indexOf(')')));
				
				
				if (tokens[i].toLowerCase().trim().startsWith("select"))
				{
					nextpagenode.select=val;
					//System.out.println("SELECT:" + nextpagenode.select);
				}
				
				if (tokens[i].toLowerCase().trim().startsWith("attr"))
				{
					nextpagenode.atrr=val;
					//System.out.println("ATRR:" + nextpagenode.atrr);
				}
				
				if (tokens[i].toLowerCase().trim().startsWith("value"))
				{
					nextpagenode.value=val;
					//System.out.println("VALUE:" + nextpagenode.value);
				}
				
				if (tokens[i].toLowerCase().trim().startsWith("as"))
				{
					nextpagenode.selectAs=val;
					//System.out.println("SELECTAS:" + nextpagenode.selectAs);
				}
				
				if (tokens[i].toLowerCase().trim().startsWith("getmethod"))
				{
					nextpagenode.getMethod=val;
					//System.out.println("GETMETHOD:" + nextpagenode.getMethod);
				}
				
				if (tokens[i].toLowerCase().trim().startsWith("order"))
				{
					nextpagenode.order=Integer.parseInt(val);
					//System.out.println("ORDER:" + nextpagenode.order);
				}
				
			}
			System.out.println("//" + nextpagenode.select + "[@" + nextpagenode.atrr + "[starts-with(.,'" + nextpagenode.value +"')]]");
			HtmlAnchor anc=(HtmlAnchor)page.getByXPath("//" + nextpagenode.select + "[@" + nextpagenode.atrr + "[starts-with(.,'" + nextpagenode.value +"')]]").get(0);
			HtmlPage nextPage;
			String next=anc.getAttribute(nextpagenode.getMethod);
			System.out.println("next:"+next);
			nextPage=connectLink(page,next);
			return nextPage;
		}
			
		public static int getProductCount_inPage(HtmlPage page, DefaultMutableTreeNode root)
		{
			Html2ProductNode p_node=(Html2ProductNode)roots.get(0).getUserObject();
			String nodeText="";
			
	   		if (p_node.value.isEmpty())
	   			nodeText="//" + p_node.select + "[@" + p_node.atrr + "]";	
	   		else
	   			nodeText="//" + p_node.select + "[@" + p_node.atrr + "[starts-with(.,'" + p_node.value +"')]]";
	   		return page.getByXPath(nodeText).size();
	   	
		}
		
	    public static WebClient setWebClient()
	    {
	    	 webClient = new WebClient();
	    	 
	    	 webClient.setTimeout(60000);
	    	 webClient.setRedirectEnabled(true);
	    	 webClient.setJavaScriptEnabled(true);
	    	 webClient.setThrowExceptionOnFailingStatusCode(false);
	    	 webClient.setThrowExceptionOnScriptError(false);
	    	 webClient.setCssEnabled(false);
	    	 try {
				webClient.setUseInsecureSSL(true);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
			 webClient.setThrowExceptionOnScriptError(false);
			 LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
			 java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
			 java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
			 //webClient.setThrowExceptionOnFailingStatusCode(false);
			 //webClient.setThrowExceptionOnScriptError(false);
			 webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			 return webClient;
	    }
	     
	   	public static HtmlPage connectPage(String Uri)
		{
			try 
			{
				final HtmlPage page = webClient.getPage(Uri);
				int ix=webClient.waitForBackgroundJavaScript(100);
				return page;
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			
			return null;
		}
			
		public static void ProcessHtmlTree(HtmlPage page,DefaultMutableTreeNode node, String nodeText, Product newProduct)
		{
			//System.out.println("PNODETEXT: "+nodeText);
			String rootText=buildNodeText((Html2ProductNode)roots.get(0).getUserObject());
			Html2ProductNode p_node=(Html2ProductNode) node.getUserObject();
			String link="";
			String nodeText_backUp;
			int index;

			if (node.isLeaf()||!p_node.select.equals("a"))
			{
				nodeText=nodeText+buildNodeText(p_node);
			}
			
			for (int i=0;i<node.getChildCount();i++)
			{
				DefaultMutableTreeNode child=(DefaultMutableTreeNode) node.getChildAt(i);
				Html2ProductNode p_child=(Html2ProductNode) child.getUserObject();

				//if (p_child.value.contains("ctl00_u20_ascUrunDetay_pnlTabs")) //System.out.println("ÞÝMDÝ "+nodeText); 
				if (child.isLeaf())
				{
					nodeText_backUp=nodeText;
					nodeText=nodeText+buildNodeText(p_child);
					System.out.println("LEAFTEXT: "+nodeText);
					fillProductProperty(page,nodeText,p_child,newProduct);
					nodeText=nodeText_backUp;
					if (roots.size()>1 && child.getNextSibling()==null)
					{
						roots.remove(roots.size()-1);
						pages.remove(pages.size()-1);
						page=pages.get(pages.size()-1);
						//webClient.deregisterWebWindow(webClient.getCurrentWindow());
						webClient.closeAllWindows();
					}	
				}
				else
				{
					if (p_child.getMethod.startsWith("href")||p_child.getMethod.startsWith("link"))
					{
						index=productList.indexOf(newProduct);
						nodeText=nodeText+buildNodeText(p_child);
						HtmlAnchor linkElement = null;
	                    //System.out.println("FULLTEXT: "+p_child.select+" "+p_child.atrr);
						if (p_child.getMethod.startsWith("link")) 
							System.out.println("NODETEXT: "+nodeText);
						if (nodeText.startsWith(rootText))
						{
							linkElement=(HtmlAnchor) page.getByXPath(nodeText).get(index-productCount_in_previousPage);
							//System.out.println("LINKELEMENT_NODETEXT:"+nodeText.toString());
						}
						
						else
						{
							
							try{
							//System.out.println("GÝRDÝM");
							linkElement=(HtmlAnchor) page.getByXPath(nodeText).get(p_child.order);
							//System.out.println("LINKELEMENT:"+linkElement.toString());
							}
							catch (Exception e) {
								//System.out.println(e.getCause().getMessage());
								break;
							}
						}
						
						if (p_child.getMethod.startsWith("href"))
							link=linkElement.getAttribute("href");
						else
							link=linkElement.getAttribute("link");
						//System.out.println("CONNECT2: "+link);
						page=connectLink(page,link);
					
						for (int k=0;k<child.getChildCount();k++)
						{
							if (child.getChildAt(k).getParent().equals(child))
							{
								pages.add(page);
								roots.add((DefaultMutableTreeNode)child.getChildAt(k));
							}
						}
						nodeText="";
						ProcessHtmlTree(page, child, nodeText, newProduct);
						
					}
					else
					{

						ProcessHtmlTree(page, child, nodeText, newProduct);
					}
						
				}
				
			}
			
		}
			
		public static void fillProductProperty (HtmlPage page,String nodeText,Html2ProductNode p_child,Product newProduct)
		{			
			int index=productList.indexOf(newProduct);
			
			List<?> matchedPatterns=page.getByXPath(nodeText);
			
			HtmlImage image;
			String propertyValue;
			Object element;
			String rootText=buildNodeText((Html2ProductNode)roots.get(0).getUserObject());
					
			if (matchedPatterns.size()!=0)
			{
				//System.out.println("GÝRDÝM");
				if (nodeText.startsWith(rootText) && (index-productCount_in_previousPage) < matchedPatterns.size())
				    element = matchedPatterns.get(index-productCount_in_previousPage);
				else
				{
					element = matchedPatterns.get(0);
				}
				//System.out.println("Element:"+element.toString());
				switch(p_child.selectAs)
				{
					
					case "product.title":
						{
							//System.out.println("title");
			    			propertyValue=getPropertyValue(element,p_child);
			    			newProduct.title=propertyValue;
			    			//System.out.println("title= "+newProduct.title);
						}
						break;
						
					case "product.description":
					{
						//System.out.println("title");
		    			propertyValue=getPropertyValue(element,p_child);
		    			newProduct.description=propertyValue;
		    			//System.out.println("description= "+newProduct.description);
					}
					break;
						
					case "product.imgLink":
						{
					    			propertyValue=getPropertyValue(element,p_child);
					    	    	try 
					    	    	{
						    			new URL(propertyValue);
						            }
					    	    	catch (Exception e) 
						        	{
						        	}
					    			newProduct.imgLink=propertyValue;
						}
						break;
						
					case "product.brand":
					{
				    			propertyValue=getPropertyValue(element,p_child);
				    	    	try 
				    	    	{
					    			new URL(propertyValue);
					            }
				    	    	catch (Exception e) 
					        	{
					        	}
				    			newProduct.brand=propertyValue;
					}
					break;
					
					case "product.productID":
					{
				    			propertyValue=getPropertyValue(element,p_child);
				    	    	try 
				    	    	{
					    			new URL(propertyValue);
					            }
				    	    	catch (Exception e) 
					        	{
					        	}
				    			newProduct.productID=propertyValue;
				    			
					}
					break;
						
					case "product.propertyName":
						{
					    	for (int i=0;i<matchedPatterns.size();i++)
					    	{
					    		
					    		element=(Object) matchedPatterns.get(i);
					    		propertyValue=getPropertyValue(element,p_child);
					    		newProduct.propertyName.add(propertyValue);
					    		//System.out.println(propertyValue+": ");
					    	}
						}
					break;
					
					case "product.propertyValue":
					{

				    	for (int i=0;i<matchedPatterns.size();i++)
				    	{
				    		//System.out.println("propertyValue");
				    		element=(Object) matchedPatterns.get(i);
				    		propertyValue=getPropertyValue(element,p_child);
				    		newProduct.propertyValue.add(propertyValue);
				    		//System.out.println(propertyValue);
				    	}
					}
					break;
					
					case "product.features":
					{
				    	for (int i=0;i<matchedPatterns.size();i++)
				    	{
				    		
				    		element=(Object) matchedPatterns.get(i);
				    		propertyValue=getPropertyValue(element,p_child);
				    		newProduct.features.add(propertyValue);
				    		//System.out.println("propertyFeature"+": "+propertyValue);
				    	}
					}
					break;
				
					case "product.includes":
					{
				    	for (int i=0;i<matchedPatterns.size();i++)
				    	{
				    		System.out.print("propertyIncludes");
				    		element=(Object) matchedPatterns.get(i);
				    		propertyValue=getPropertyValue(element,p_child);
				    		newProduct.includes.add(propertyValue);
				    		//System.out.print("propertyIncludes"+": "+propertyValue);
				    	}
					}
					break;
					
					default: break;
				}
			}
		}
		
		public static HtmlPage connectLink(HtmlPage page, String preLink)
		{
			String link="";
			if (preLink.contains("www.")||preLink.contains("http://"))
				link=preLink;
			else
			{
				link=page_baseUri_global+preLink;
			}
	    	try {
	    			new URL(link);
	            }
	        catch (Exception e) 
	        	{

	        	}
				return connectPage(link);
		}
		
	   	public static String getPropertyValue(Object element,Html2ProductNode p_child)
	   	{
	   		
	   		HtmlElement e=(HtmlElement)element;
	   		if (p_child.getMethod.startsWith("asText"))
	   			return e.asText();
	   		else
	   			return e.getAttribute(p_child.getMethod);
	   		
//			switch(p_child.select)
//			{
//				
//			HtmlElement e=(HtmlElement)element;
//			
//				case "span":
//					{
//		    			HtmlSpan spanElement=(HtmlSpan) element;
//		    	   		if (p_child.getMethod.startsWith("asText"))
//		    	   			return spanElement.asText();
//		    	   		else
//		    	   			return spanElement.getAttribute(p_child.getMethod);
//					}
//					
//				case "img":
//					{
//		    			HtmlImage imageElement=(HtmlImage) element;
//		    	   		if (p_child.getMethod.startsWith("asText"))
//		    	   			return imageElement.asText();
//		    	   		else
//		    	   			return imageElement.getAttribute(p_child.getMethod);
//					}
//
//					
//				case "td":
//					{
//						HtmlTableCell cellElement=(HtmlTableCell) element;
//		    	   		if (p_child.getMethod.startsWith("asText"))
//		    	   			return cellElement.asText();
//		    	   		else
//		    	   			return cellElement.getAttribute(p_child.getMethod);
//					}
//
//				default: break;
//			}
			//return "";
		}

	
	   	public static String buildNodeText(Html2ProductNode p_node)
	   	{
	   		String nodeText="";
	   		if (p_node.atrr.isEmpty())
	   			nodeText="//" + p_node.select;	
	   		else
	   		if (p_node.value.isEmpty())
	   			nodeText="//" + p_node.select + "[@" + p_node.atrr + "]";	
	   		else
	   			nodeText="//" + p_node.select + "[@" + p_node.atrr + "[starts-with(.,'" + p_node.value +"')]]";
	   		//System.out.println("NODETEXT: "+nodeText);
	   		return nodeText;
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
	
	


		    

		
