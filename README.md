##Pre-Requisites##

- **Completion of training_module1**

- **Fitnesse-standalone(not necessary/found in repo)**
	* http://fitnesse.org/FitNesseDownload
	
##This is to be checked out locally:#

- `git clone https://github.com/atrifan/shield_training.git`
- `git checkout training_module2 -b local_training_module2`

##TODO 0 - Fitnesse WalkThrough##

- start fitnesse locally
	* `java -jar fitnesse-standalone.jar [-p <port>]`
- click around / see what it offers

##TODO 1 - ColumnFixture##

- `training_module2/ro.atrifan ColumnExample.class`
- let's write a fitnesse test using current implementation
	* keep in mind that ColumnFixture uses fit engine from fitnesse
	* re-read presentation of how to include jars / make tables / make tests
	
##TODO 1(extended) - ColumnFixture - extended##

- `training_module2/ro.atrifan ColumnExample.class`
- make use of "hand-written tests" in module 1 from App to make it test a real use-case
- adapt fitnesse-test written at the above TODO
- impressions: benefits/disadvantages

##TODO 2 - Fit in it's nature boring in rest##

- `training_module2/ro.atrifan ActionExample.class`
- write fitnesse test on this implementation 
	* keep in mind that this uses fit engine from fitnesse
	* re-read presentation of how to include jars / make tables / make tests
	
##TODO 2(extended) - Fit it to the need##

- `training_module2/ro.atrifan ActionExample.class`
- make use of "hand-written code" in module 1 to make it test a real use-case
- adapt fitnesse-test written at the above TODO
- impressions: benefits/disadvantages
- also compare to ColumnFixture

##TODO 3 - Slim yet powerfull testing tool ~ GENERIC FIXTURES##

- `training_module2/ro.atrifan.fixture.implementations Example.class`
- let's read the code, read an example of table in fitnesse
- `visit http://localhost/FrontPage.FixtureExampleTest`

##TODO 3 - @Work##

- `training_module2/ro.atrifan.fixture.implementations Example.class`
- complete the TODO parts
- adapt fitnesse test

##TODO 4 - Discuss##

- what do you thing about it


#Feedback time#

- follow the following link and give some usefull feedback http://goo.gl/forms/zbJ6jnmWAF
