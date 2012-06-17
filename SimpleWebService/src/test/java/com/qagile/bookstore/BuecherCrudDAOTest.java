package com.qagile.bookstore;

import com.qagile.bookstore.dao.BuecherCrudDAO;
import com.qagile.bookstore.ws.BuchDO;
import org.testng.annotations.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.FileAssert.fail;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 21:36
 */
public class BuecherCrudDAOTest {

    private static final BuecherCrudDAO dao  = BuecherCrudDAO.getInstance();
    private static final int  ANZAHL_BUECHER = 1000;
    private static final long START_ISBN     = 2000000000L;

    // use BeforeClass !!!
    static {
        for( int i = 0; i < ANZAHL_BUECHER; i++ ) {
            BuchDO bu = new BuchDO();
            bu.setIsbn( Long.valueOf( START_ISBN + i ) );
            bu.setTitel( "Buch " + i );
            bu.setPreis( new Double( i ) );
            try {
                dao.createBuch( bu );
            } catch( Exception ex ) {
                throw new RuntimeException( ex );
            }
        }
    }

    @Test
    public void testCreateBuchDuplicateCreateException()
    {
        try {
            BuchDO bu = new BuchDO();
            bu.setIsbn( Long.valueOf( START_ISBN + ANZAHL_BUECHER - 1 ) );
            bu.setTitel( "Buch" );
            bu.setPreis( new Double( 0 ) );
            dao.createBuch( bu );
            fail();
        } catch( Exception ex ) {
        }
    }

    @Test public void testGetBuchByIsbn()
    {
        BuchDO bu = dao.getBuchByIsbn( null );
        assertNull( bu );
        bu = dao.getBuchByIsbn( Long.valueOf( -1 ) );
        assertNull( bu );
        bu = dao.getBuchByIsbn( Long.valueOf( START_ISBN + ANZAHL_BUECHER / 2 ) );
        assertNotNull( bu );
    }

    @Test public void testFindeBuecher()
    {
        List<BuchDO> buecher = dao.findeBuecher( null, null );
        assertTrue( buecher != null && buecher.size() >= ANZAHL_BUECHER );
        buecher = dao.findeBuecher( null, "buch" );
        assertTrue( buecher != null && buecher.size() >= ANZAHL_BUECHER );
        buecher = dao.findeBuecher( Long.valueOf( -1 ), "" );
        assertEquals( 0, buecher.size() );
        buecher = dao.findeBuecher( Long.valueOf( START_ISBN ), null );
        assertEquals( 1, buecher.size() );
    }

    @Test public void testUpdateBuchByIsbn()
    {
        Long   isbn = Long.valueOf( START_ISBN + ANZAHL_BUECHER / 2 );
        BuchDO bu = dao.getBuchByIsbn( isbn );
        String titel1 = bu.getTitel();
        dao.updateBuchByIsbn( isbn, "Buch mit neuem Titel", new Double( 0 ) );
        bu = dao.getBuchByIsbn( isbn );
        assertFalse( titel1.equals( bu.getTitel() ) );
    }

    @Test public void testDeleteBuchByIsbn() throws Exception
    {
        BuchDO bu = dao.deleteBuchByIsbn( Long.valueOf( -1 ) );
        assertNull( bu );
        Long isbn = Long.valueOf( START_ISBN + ANZAHL_BUECHER );
        bu = new BuchDO();
        bu.setIsbn( isbn );
        bu.setTitel( "Buch" );
        bu.setPreis( new Double( 0 ) );
        dao.createBuch( bu );
        List<BuchDO> buecher = dao.findeBuecher( null, null );
        assertTrue( buecher.size() >= ANZAHL_BUECHER + 1 );
        assertNotNull( dao.getBuchByIsbn( isbn ) );
        assertNotNull( dao.deleteBuchByIsbn( isbn ) );
        assertNull(    dao.getBuchByIsbn( isbn ) );
    }
}
