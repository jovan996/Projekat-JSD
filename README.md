This is an example of a simple DSL which is used for generating Spring Boot Rest API applications.
With the help of this language we can generate all layers of Spring Boot application including model,controllers,repositories and services.Also we can generate maven pom.xml file and application.properties file in which we generate basic properties for connecting to MySQL database.
 
File term.tx in meta-model folder contains a grammar of the language. Grammar is written in textX DSL. Model example is given in the file model.term in model folder. 

A program app.py will instantiate meta-model from the grammar, register built-in simple types int,long,boolean and String and register user class SimpleType so that built-in types can be instantiated from the code. A model.term is than parsed and instantiated by the meta-model and both meta-model and model are exported to .dot files in the folder dotexport.

All code generation is presented in the program codegen.py. The code is generated in the Spring_boot_app folder using jinja2 template engine and corresponding templates from template folder. For each term instance one java file is generated in all layers.File pom.xml is generated in Spring_boot_app folder and contains basic settings for every application, properties and dependencies which are defined in model. File application.properties is generated in resources subfolder and we generate some basic properties we are also defined in our model. 

Meta-model can be checked or visualized by textX command line tool but model can't because it depends on few built-in simple types which must be provided during meta-model instantiation (app.py file).
To check and visualize meta-model you can use command :
    textx generate meta-model/term.tx --target dot

To run the language do the following:

Install Python 3.6.8 or higher

Install textX

    $ pip install textX
  
Install Jinja2 for code generation

    $ pip install jinja2
  
From powershell(if you are using Visual studio code) or command line run :
  
    $ python app.py
  
Previous command will generate dot files in dotexport folder. We can convert those files to PNG format but first we must have GraphViz installed (on Windows you must add environment variable):

To convert the files to PNG format do the following :

    dot -Tpng -O meta-model/term.dot
   
    dot -Tpng -O model/model.dot
  
You will get term.dot.png and model.dot.png diagram.

Run code generation:

    $ python codegen.py
  
This will produce in Spring_boot_app folder which contains generated layers(model,controller,repository,service) for each term instance,pom.xml file and application.properties file.
