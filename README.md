Time
====
A technology test project. The goal is to build a time record system. 

Technology
----------
* JEE7 as the backbone, running on Glassfish 4
* AngularJS for the frontend
* CSS - Bootstrap Twitter v3.1
* Lombok for the ubiquitous getter / setter
* In-Container Testing with JBoss Arquillian
* Guava 

Warnings
--------
 * Application will only run on a patched glassfish v4 with the current slf4j. Otherwise you will end directly in the classpath hell. Full credit to http://hwellmann.blogspot.ch/2010/12/glassfish-logging-with-slf4j-part-2.html
 * The bundled jersey distribution (2.0 I believe) is lacking. There are numerous bugs in conjunction with using cdi / ejb. For example you can't inject cdi beans into a jersey provider.
 * The application needs a glassfish realm. You may use a simple filerealm. Only the restendpoints are secured. 

Tasks 
-----
- [x] Maven Setup
- [x] Config Module, based on Producer mechanism
- [x] Arquillian Test for Config Module
- [x] Refactor arq tests, (server)
- [x] Arquillian-tests in config: verify the guava and derby problem
- [x] war
- [x] Log: slf4j, logback
- [ ] Setup a self-contained DB, either h2 or derby. 
- [ ] Simplified domain model 
- [x] Login into a realm, funnel
