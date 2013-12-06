IRIS Protege Plug-in
====

IRIS is a declarative and manual tree wrapper that extracts the semi-structured product information from online shops and presents this information in a GoodRelations-compliant ontology. IRIS also automatically marks up your products using RDFa or Microdata. Creating GoodRelations snippets in RDFa or Microdata using the product information extracted from Web is a business value, especially when you consider most of the popular search engines including Google and Yahoo recommend the use of these standards to provide rich site data for their index. Finally, IRIS is a Protégé plug-in which is developed as an open source Java Application.

How can I use IRIS?

It is simple:
1.	Create a Template File
2.	Open the Ontology and the Template
3.	Create a GoodRelations-compliant Ontology
        or
    Create a Custom Ontology from Scratch
4.	Save and Serialize the Ontology


1.	Create a Template File
Creating a template file is the basic step for using the IRIS plug-in. The information collected using the template is mapped to the attributes of the Product object. Title, brand, productID, description, and imgLink are single-valued attributes of the Product object whereas feature, includes, propertyName and propertyValue are multi-valued attributes. 

A template has two parts; the first part contains the tree that specifies the paths of HTML tags around the product attribute names and product attribute values. The second part contains the information on how the HTML documents should be acquired.

The product information is extracted using the tree in the first part of the template. The tree is created manually by the user therefore the user should analyze the HTML code of the Web page carefully. “amazon.com” pages that contain information about laptops are used as an example. Figure 1 shows the HTML code which contains the information about the first product on the first page. Figure 2 shows the tree which is built for extracting product information from the Web page in Figure 1.
Figure 1: The HTML code which contains the first product on the page.
Figure 2: The tree nodes in the template for “amazon.com” pages about laptops.
The leaf nodes of the tree (Figure 2) contains the HTML tag around a product attribute name or a product attribute value, and the internal nodes of the tree contains the HTML tags in which the HTML tag in the leaf node is nested. Therefore the hierarchy of the tree also represents the hierarchy of the HTML tags. In other words, internal nodes contain the information on paths that go to the leaf nodes. There may be more than one path to go to the leaf node; therefore there is more than one way to create the tree for extracting specific product information.
In the example Web page, the HTML code in each division having an id starting with “result” contains all information about a specific product, e.g. < div id =“result 0” for the first product, < div id =“result 1” for the second product, < div id =“result 2” for the third product etc. Therefore, the root node (c0) in the tree is the node that specifies these divisions. The first child of the root node (c1) specifies the HTML element < span class =“lrg bold”. c1 is a leaf node and contains the value of the title attribute.
The second child of the root node (c2) specifies the HTML element < img src. c2 is also a leaf node and contains the image link of the product. The third child of the root node (c3) is one of the internal nodes that specify the path to its leaf nodes. c3 specifies that all children of c3 contain HTML tags which are nested within the h3 heading tag having class name “newaps” (< h3 class =“newaps”>). Its child node (c4) specifies the HTML link element which goes to another Web page that contains detailed information about the product.
The starting Web page is referred as root page and the pages navigated from root page are child pages. After jumping the page address which is represented by this link element, product properties and their values are chosen from this Web page which is shown in Figure 3.
Figure 3: Product properties and their values.
The properties and their corresponding values are stored in an HTML table, which is nested in an HTML division identified by “prodDetails” id. Therefore c5 specifies this HTML division and its child nodes c6 and c7 specifies the HTML cells containing product properties (< td class =“label”) and their values (< td class =“value”).
After determining the HTML elements which contain the product information, the user defines these elements in the template properly. First of all, each node in the tree is a combination of the following fields: SELECT, ATTR, VALUE, ORDER, GETMETHOD and AS. These fields are described below:

SELECT-ATTR-VALUE: These three fields are used to build the XPath query that specifies the HTML element in the page.
	
ORDER: This field is used when there is more than one HTML element matching with the expression. The numeric value of the ORDER element specifies which one of these elements will be selected. Please note that the ORDER field can only be used with the nodes which do not reside in the root pages. In other words, you can use the ORDER field with the elements residing in the child pages.

GETMETHOD: Use GETMETHOD to collect the proper values in the selected HTML element e. If you want to get the textual representation of the element (e), in other words what would be visible if this page was shown in a Web browser, you define the value of GETMETHOD field as “asText”. On the other hand if you want to get the value of an element (e) attribute than you specify the name of that attribute as the value of GETMETHOD field. 

AS: This field is only used with leaf nodes. The value which is collected from a leaf node using GETMETHOD field is mapped to the Product attribute specified in the AS field. 

The nodes of the tree (Figure 2) are specified in the first part of the template file (amazon.txt). The second part of a template file contains the information on how the HTML documents should be acquired. This part has the following fields:

NEXT PAGE: The information about laptops in “amazon.com” is spread across 400 pages. The link of the next page is stored in this field. The link is determined by a link in the HTML code of the page or by an open parameter in the URL of the page. In the first case, the value of the NEXT PAGE field stores the node which specifies the HTML element that is around the next page link. In my example (“amazon.com”), the value of this field is (SELECT(a), ATTR=(id), VALUE=(pagnNextLink), GETMETHOD=(href)). This value is determined by the next page link in the HTML code of the example Web page (Figure 4). In the second case, the page number is specified in the URL of the page, e.g. “?page=1”. For navigating to next page, the page number is increased by one, e.g. “?page=2”. The NEXT PAGE field stores the value of the page parameter in the URL.
Figure 4: The HTML code which the value of NEXT PAGE field is obtained from.
PAGE RANGE: This field stores the range of pages which you want to collect information from. You can also specify the number of a single page. In my example, I want to collect the products in pages from 1 to 3.

BASE URI: This field represents the base URI of the site. In my example, the value of this field is http://www.amazon.com.

PAGE URI: In this field, you enter the URI of the first page which you want to collect information from. In my example, this is the URI of the page 1:
http://www.amazon.com/s/ref=srnr n 1?rh=n%3A565108%2Ck%3Alaptop& keywords=laptop&ie=UTF8&qid=1374832151&rnid=2941120011

CLASS: This field contains the name of the class that represents the products to be collected. In my example, “Laptop” class is be used.

3.2. Open the ontology and the Template
The next step is opening an empty ontology to store the extracted information. IRIS is developed as a plug-in for Protégé Ontology Editor therefore the user opens an empty ontology in Protégé Editor preceding the following steps:
1.	From the welcome dialog, select “Create New Project...” then “The Create New Project” wizard appears. Select “OWL/RDF Files” then in the next step select “OWL Files”.
2.	Go to the next step and select “myOwl.owl” file by clicking the “Browse for File” button. In the next step, select “OWL DL” dialect. Finally, choose “Properties View”. The Protégé window opens and the standard tabs become visible.
3.	Choose Project - Configure... to bring up the “Configure” dialog. The new IRIS tab will be listed on the TabWidgets panel. Check the “Visible” checkbox and click the OK button. The system will now display the IRIS tab as in Figure 5.
After opening the ontology, you select the template that is be used to import product information from the Web. In my example, I choose the amazon.txt by clicking the “Open template” button in Figure 5. Then the tool imports all laptops from the “amazon.com” pages specified in the PAGE RANGE field of the template file: amazon.txt (Figure 6). The imported individuals are listed in the “Individuals Window”. Figure 6 shows the expanded version of the Product 0 node, whose children store all properties of Product 0 and their corresponding values. The “Properties Window” lists all properties of the individuals in “Individuals Window”. Although there are 39 properties in “Properties Window”, only 19 of these properties are listed in the children of the Product 0 node, because if the value of a property is not specified for a specific product individual, then this property is not listed in the children of that individual. This means 19 property values are specified for Product 0.
	Figure 5: The IRIS tab in Protégé Ontology Editor.
Figure 6: The tool imports all laptops from the “amazon.com” pages specified in the PAGE RANGE field.
3.3. Create the Ontology
The next step is creating the ontology that stores the product information. Here the plug-in offers two ways:
–	Create an ontology that is compatible with GoodRelations Vocabulary
–	Create a custom ontology from scratch

3.3.1. Create an Ontology that is Compatible with GoodRelations Vocabulary
This section explains how your new ontology describing products plus their attributes must be designed in order to be compatible with the GoodRelations ontology using the pattern in Figure 7. In this section, I follow up the descriptions and examples introduced in GoodRelations Primer [30]. First of all, the system defines the class in your template (in my example, “Laptop” class) as a subclass of “gr:Individual” class of the GoodRelations vocabulary. A “gr:Individual” is an actual product or service instance. The properties of this class can be classified as follows:

–	First category: “gr:category”, “gr:color”, “gr:condition”, “gr:depth”, “gr:description”, “gr:hasBrand”, “gr:hasEAN UCC-13”, “gr:hasGTIN-14”, “gr:hasGTIN-8”, “gr:hasMPN”, “gr:hasMakeAndModel”, “gr:hasManufacturer”, “gr:hasStockKeepingUnit”, “gr:height”, “gr:isAccessoryOrSparePartFor”, “gr:isConsumableFor”, “gr:isSimilarTo”, “gr:name”, “gr:serialNumber”, “gr:weight”, “gr:width”.
–	Second category: “gr:quantitativeProductOrServiceProperty”.
–	Third category: “gr:qualitativeProductOrServiceProperty”.
–	Fourth category: “gr:datatypeProductOrServiceProperty”.

Since “Laptop” is a subclass of “gr:Individual” class, each “Laptop” instance becomes also an instance of “gr:Individual” class. Please note that “gr:Individual” class is a subclass of “gr:ProductOrService” class in Figure 7. Then the properties of the “Laptop” class, which are collected from the Web page should be mapped to the properties of “gr:Individual” class in one of the following ways:

–	If the property px is semantically equivalent of a property from the first category py, then you simply map px to py. All properties that specify quantitative characteristics, for which an interval is at least theoretically an appropriate value, are defined as subproperties of “gr:quantitativeProductOrServiceProperty”. Allowed values for a “quantitativeProductOrServiceProperty” are “gr:QuantitativeValue”, “gr:QuantitativeValueFloat” and “gr:QuantitativeValueInteger”. A quantitative value is a numerical interval that represents the range of a certain “gr:quantitativeProductOrServiceProperty” in terms of the lower and upper bounds for a particular “gr:ProductOrService”. It is to be interpreted in combination with the respective unit of measurement. Most quantitative values are intervals even if they are in practice often treated as a single point value. Example: a temperature between 60 and 67 degrees Fahrenheit. Please note that some properties of the first category (“gr:depth”, “gr:height”, “gr:weight”, and “gr:width”) are defined as subproperties of “gr:quantitativeProductOrServiceProperty”.

–	All properties for which value instances are specified are subproperties of “gr:qualitativeProductOrServiceProperty”. Allowed value for a “qualitativeProductOrServiceProperty” is “gr:QualitativeValue”, which is a predefined value for a product characteristic. For example, light bulbs have the qualitative property “hasEnergyEfficiency”, with predefined values from “A” to “G”.

–	“gr:datatypeProductOrServiceProperty” is the superproperty for all pure datatype properties that can be used to describe a product and services instance. Only such properties that are no quantitative properties and that have no predefined value instances are subproperties of this property. In practice, this refers to a few integer properties for which the integer value represents qualitative aspects, for string datatypes (as long as no predefined values exist), and for boolean datatype properties. For example, “isTouchscreenEnabled” is a “datatypeProductOrServiceProperty” of type boolean (yes/no).

Figure 7: Pattern for GoodRelations-compliant Product Ontologies.
For creating a GoodRelations-compliant ontology using IRIS plug-in, user selects the individuals and properties that will reside in the ontology. Then she clicks the “Use GoodRelations Vocabulary” button (Figure 5) and “Use GoodRelations Vocabulary” wizard appears. She selects the corresponding GoodRelations property type and respective unit of measurement for each selected property. For the example, I choose “brandName”, “hardDrive”, “color”, and “numberofUSB2Ports” properties and specify the property types and units as in Figure 8. The selected unit of measurement is saved as an UN/CEFACT Common Code in the ontology and “Symbol” in Figure 8 means the property does not specified with a unit of measurement.
Figure 8: Selecting the types and units of properties using “Use GoodRelations” wizard.
3.3.2. Create a Custom Ontology from Scratch
In order to create a custom ontology, user selects the individuals and properties that will reside in the ontology. Then she clicks the “Set types/units for properties” button (Figure 5) and “Set types/Units for properties” wizard appears (Figure 9). User specifies whether the property is OWLObjectProperty. Otherwise the property is defined as OWLDatatypeProperty. This version of the tool does not support units in custom ontologies. Therefore, if there is a unit value in the imported data, user chooses the “Remove Unit” field.
 
Figure 9: “Set types/Units for properties” wizard.
3.4. Save and Serialize the Ontology
The user clicks the “Save” button (Figure 5) and saves the ontology in an owl file. Then she clicks the “Export to a serialization format” button (Figure 5) and views the ontology in one of the structured data markup standards (Figure 10).

Figure 10: “Export to a serialization format” wizard.



