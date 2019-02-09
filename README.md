# JWebMP Home Page

This is the site as viewable at www.jwebmp.com, 

# Get her running
* Copy persistence_local.xml (or h2) to persistence.xml in the META-INF folder
* Open HomePageStartup.java, remove the TODO lines for Font Awesome Pro (Or all icons will go missing Line 149)
* Run ```mvn package -Pjdk8``` or ```-Pjdk11```. These set the database drivers (sql server) per release 
* ```cd target``` ?
* Start with ```java -jar JWebMPHomepage.java```
* Site is available at ```http://localhost:6002```

The profiles jdk8 and jdk11 should be built under their classes.

Enjoy using this library!

* Tip : Build with JDK8, Change the packaging to war and deploy straight to your container...
