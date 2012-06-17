package com.qagile.bookstore;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.qagile.bookstore.ws.*;
/**
 * User: ful
 * Date: 15.05.12
 * Time: 22:02
 */
public class BuecherServiceTest {

    private static final BuecherService buecherService = new BuecherServiceImpl();
    private static final int  ANZAHL_BUECHER = 1000;
    private static final long START_ISBN     = 3000000000L;

    static {
        for( int i = 0; i < ANZAHL_BUECHER; i++ ) {
            BuchDO bu = new BuchDO();
            bu.setIsbn( Long.valueOf( START_ISBN + i ) );
            bu.setTitel( "Buch " + i );
            bu.setPreis( new Double( i ) );
            try {
                buecherService.createBuch( bu );
            } catch( Exception ex ) {
                throw new RuntimeException( ex );
            }
        }
    }

    @Test public void testCreateBuchDuplicateCreateException()
    {
        try {
            BuchDO bu = new BuchDO();
            bu.setIsbn( Long.valueOf( START_ISBN + ANZAHL_BUECHER - 1 ) );
            bu.setTitel( "Buch" );
            bu.setPreis( new Double( 0 ) );
            buecherService.createBuch( bu );
            fail();
        } catch( Exception ex ) {
        }
    }

    @Test
    public void testGetBuchByIsbn()
    {
        BuecherTO buTO = buecherService.getBuchByIsbn( null );
        assertEquals( 0, buTO.getResults().size() );
        buTO = buecherService.getBuchByIsbn( Long.valueOf( -1 ) );
        assertEquals( 0, buTO.getResults().size() );
        buTO = buecherService.getBuchByIsbn( Long.valueOf( START_ISBN + ANZAHL_BUECHER / 2 ) );
        assertEquals( 1, buTO.getResults().size() );
    }

    @Test
    public void testFindeBuecher()
    {
        BuchDO bu = new BuchDO();
        BuecherTO buTO = buecherService.findeBuecher( bu );
        assertTrue( buTO.getResults() != null && buTO.getResults().size() >= ANZAHL_BUECHER );
        bu.setTitel( "buch" );
        buTO = buecherService.findeBuecher( bu );
        assertTrue( buTO.getResults() != null && buTO.getResults().size() >= ANZAHL_BUECHER );
        bu.setTitel( null );
        bu.setIsbn( Long.valueOf( -1 ) );
        buTO = buecherService.findeBuecher( bu );
        assertEquals( 0, buTO.getResults().size() );
        bu.setIsbn( Long.valueOf( START_ISBN ) );
        buTO = buecherService.findeBuecher( bu );
        assertEquals( 1, buTO.getResults().size() );
    }
}
