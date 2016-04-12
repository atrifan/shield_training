package ro.atrifan.components;

/**
 * Created by alexandru.trifan on 24.10.2015.
 */
public class ToTest {

    private ClassToBeMocked theClassToBeMocked;

    public ToTest(){}

    public ToTest(ClassToBeMocked theMock) {
        this.theClassToBeMocked = theMock;
    }

    public boolean testMe() {
        return true;
    }


    public boolean anotherTest() {
        return theClassToBeMocked.returnStuff();
    }
}
