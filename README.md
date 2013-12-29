Time
====
A technology test project. The goal is to build a time record system. 


Technology
----------
* JEE7 as the backbone, running on Glassfish 4
* AngularJS for the frontend
* Lombok for the ubiquitous getter / setter
* In-Container Testing with JBoss Arquillian
* Guava 


Tasks 
-----
- [x] Maven Setup
- [x] Config Module, based on Producer mechanism
- [x] Arquillian Test for Config Module
- [ ] Refactor arq tests, (server, verify the guava and derby problem)
- [ ] war
- [ ] Log: slf4j, logback
- [ ] Setup a self-contained DB, either h2 or derby. 
- [ ] Simplified domain model 
- [ ] Login, either vs a realm, otherwise what?
