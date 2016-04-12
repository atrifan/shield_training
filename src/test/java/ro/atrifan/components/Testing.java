package ro.atrifan.components;

import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by alexandru.trifan on 24.10.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class Testing{

    ToTest classUnderTest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTheMethod() {
        classUnderTest = new ToTest();
        Assert.assertTrue(classUnderTest.testMe());
    }

    @Test
    public void testMockToReturnTrue() {
        ClassToBeMocked theMock = Mockito.mock(ClassToBeMocked.class);
        Mockito.doReturn(true).when(theMock).returnStuff();
        classUnderTest = new ToTest(theMock);
        Assert.assertTrue(classUnderTest.anotherTest());
    }

    @Test
    public void testMockToReturnFalse() {
        ClassToBeMocked theMock = Mockito.mock(ClassToBeMocked.class);
        Mockito.doReturn(false).when(theMock).returnStuff();
        classUnderTest = new ToTest(theMock);
        Assert.assertFalse(classUnderTest.anotherTest());
    }
}

