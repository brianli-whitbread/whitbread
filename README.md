# Whitbread Demostration Assignment Project

This AEM 6.3 project is created to demonstrate my ability to build and deploy AEM projects and components. There are no package prerequisites for this project; the only thing required is AEM 6.3 with a proper AEM license.

#####This project includes a number features:

* ####One Page Content:
  * English page with example content

* ####One Content Template Based on Requirements:
    The content template utilizes BootStrap 3 for the responsive layout; This template is a 2-column layout.
    This template comprises of 3 sections of the page; the header, content, and footer sections of the page.
    Each section of the page enables limited components for the authors use during content creation. Listed below is a detailed list of all the available components for a given paragraph system. 
  
  * Header Section Area 
    * Inheritance Paragraph System
      * Text Foundation Component
      
  * Content Section Body Area (BootStrap V3) - main content section
      * Paragraph System 1
        * Text & Image Foundation Component
        * Image Foundation Component
        * List Foundation Component
        * Title Foundation Component
        * [Custom] Carousel Component 
        
      * Paragraph System 2 - side bar section
        * Reference Foundation Component
        * Download Foundation Component
        * SiteMap Foundation Component
        * [Custom] List Component

  * Footer Section Area
    * Inheritance Paragraph System
      * Text Foundation Component

* ####Page Component:
  * Based on the [page AEM Core WCM Component](https://github.com/Adobe-Marketing-Cloud/aem-core-wcm-components/tree/master/content/src/content/jcr_root/apps/core/wcm/components/page/v1/page)
  * customfooterlibs.html and customheaderlibs.html snippet to load additional JS and CSS clientlibs according to the {cssId} property
  * Bootstrap3 2 Column Responsive Layout
  
* ####Custom Content Components:
  * Carousel Component 
  * List Component
   
* ####OSGI Bundle - WCMUsePojo Classes w/ Test Code:
  * Carousel
  * ChildPagesList

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage -Padobe-public
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish -Padobe-public
    
Or alternatively

    mvn clean install -PautoInstallPackage -Padobe-public -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle -Padobe-public

## Testing

There are three levels of testing contained in the project:

* unit test in core: this show-cases classic unit testing of the code contained in the bundle. To test, execute:

    mvn clean test

* server-side integration tests: this allows to run unit-like tests in the AEM-environment, ie on the AEM server. To test, execute:

    mvn clean verify -PintegrationTests

* client-side Hobbes.js tests: JavaScript-based browser-side tests that verify browser-side behavior. To test:

    in the browser, open the page in 'Developer mode', open the left panel and switch to the 'Tests' tab and find the generated 'MyName Tests' and run them.


## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
