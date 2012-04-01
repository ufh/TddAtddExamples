package com.qagile.tddatdd.examples.mock.income;

import org.easymock.EasyMock;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 12.03.12
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
public class DbMockTest {

    private IDbObject dbMockObject;

    @Test
    public void testDbMock() throws NotFoundException {

        dbMockObject = EasyMock.createMock(IDbObject.class);
        EasyMock.expect(dbMockObject.doQuery("Select * from objects where name = hans")).andReturn("hans");
        EasyMock.replay(dbMockObject);
        
        String check = dbMockObject.doQuery("Select * from objects where name = hans");
        assertEquals("hans",check );
    }

    @Test
    public void testSequencedCalls() throws NotFoundException {
        // first read should return a valid object
        // delete object should return null
        // reading this object again throws ElementNotFound Exception
        dbMockObject = EasyMock.createMock(IDbObject.class);
        EasyMock.expect(dbMockObject.doQuery("Select * from objects where name = hans")).andReturn("hans");
        EasyMock.expect(dbMockObject.doQuery("delete * from objects where name = hans")).andReturn(null);
        EasyMock.expect(dbMockObject.doQuery("Select * from objects where name = hans")).andThrow(new NotFoundException("Hans not found"));
        EasyMock.replay(dbMockObject);

        String dbObject = dbMockObject.doQuery("Select * from objects where name = hans");
        assertEquals("hans",dbObject );
        assertEquals(null, dbMockObject.doQuery("delete * from objects where name = hans"));
        try{
            dbObject = dbMockObject.doQuery("Select * from objects where name = hans");
            fail("Expected Not Found Exception!");
        }catch (NotFoundException e) {}
    }

    /**
     * Tests the lifecycle of mocking:
     * - instantiate a class and check regular behaviour
     * - replace by a mock-object and define fake-behaviour
     * - as long as not set to replay mode EasyMock throws IllegalStateException when the object is called
     * - after that, EasyMOck goes in an undefined state.. so don't do that!
     * - when EasyMock is set to replay, it returns the fake value
     */
    @Test
    public void testClassAsMock(){

        
        TestClassAsMock mockObject = new TestClassAsMock();
        assertEquals("Something",mockObject.doSomething());
        
        mockObject = EasyMock.createMock(TestClassAsMock.class);
        EasyMock.expect(mockObject.doSomething()).andReturn("SomethingElse");
        
        // EasyMock is still in record mode, so it throws an IllegalStateException
        //String something = mockObject.doSomething();
        //assertEquals(null, something);

        //now, we set EasyMock to replay the defined behaviour. Now the fake answer should be returned
        EasyMock.replay(mockObject);

        assertEquals("SomethingElse",mockObject.doSomething());
     
    }
    
    @Test
    public void testVerify(){

        TestClassAsMock mockObject = new TestClassAsMock();
        mockObject = EasyMock.createMock(TestClassAsMock.class);
        EasyMock.expect(mockObject.doSomething()).andReturn("SomethingElse");
        EasyMock.expect(mockObject.doSomething()).andReturn("SomethingMoreElse");
        //now, we set EasyMock to replay the defined behaviour. Now the fake answer should be returned
        EasyMock.replay(mockObject);

        assertEquals("SomethingElse",mockObject.doSomething());
        //Verify should throw exception, as the mock object is called only once
        try{
            EasyMock.verify(mockObject);
            fail("Expected Verify failure");
        } catch(java.lang.AssertionError e){

        }
        // now call the object the second time and verify again
        assertEquals("SomethingMoreElse",mockObject.doSomething());
        EasyMock.verify(mockObject);
        //no exception is thrown, so this assertion should be reached!
        assertTrue(true);

    }
}
