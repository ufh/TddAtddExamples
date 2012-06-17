package com.qagile.bookstore.dao;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import com.qagile.bookstore.ws.*;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 21:32
 */
public class BuecherCrudDAO {

    // Map als Datenbank-Simulation:
    private              Map<Long,BuchDO> buecherPool = new ConcurrentHashMap<Long,BuchDO>();
    private static final BuecherCrudDAO   INSTANCE    = new BuecherCrudDAO();
    private static final long             DFLT_ID     = 4710;

    private BuecherCrudDAO()
    {
    }

    public static BuecherCrudDAO getInstance()
    {
        return INSTANCE;
    }

    // Neues Buch hinzufuegen:
    public BuchDO createBuch( BuchDO bu ) throws DuplicateCreateException
    {
        synchronized( buecherPool ) {
            if( bu.getIsbn() != null ) {
                if( getBuchByIsbn( bu.getIsbn() ) != null )
                    throw new DuplicateCreateException(
                            "Fehler: Es gibt bereits ein Buch mit der ISBN " + bu.getIsbn() + ".", null );
            } else {
                long maxIsbn = ( buecherPool.size() > 0 )
                        ? Collections.max( buecherPool.keySet() ).longValue() : DFLT_ID;
                bu.setIsbn( Long.valueOf( ++maxIsbn ) );
            }
            buecherPool.put( bu.getIsbn(), bu );
            return bu;
        }
    }

    // Finde Buch:
    public BuchDO getBuchByIsbn( Long isbn )
    {
        return ( isbn == null ) ? null : buecherPool.get( isbn );
    }

    // Finde Buecher:
    public List<BuchDO> findeBuecher( Long isbn, String titel )
    {
        List<BuchDO> resultList = new ArrayList<BuchDO>();
        List<BuchDO> snapshotList;
        if( isbn == null && isEmpty( titel ) )
            return Collections.unmodifiableList( new ArrayList<BuchDO>( buecherPool.values() ) );
        if( isbn != null && isEmpty( titel ) ) {
            BuchDO bu = getBuchByIsbn( isbn );
            if( bu != null ) resultList.add( bu );
            return resultList;
        }
        synchronized( buecherPool ) {
            snapshotList = new ArrayList<BuchDO>( buecherPool.values() );
        }
        String titelLC = titel.trim().toLowerCase();
        for( BuchDO bu : snapshotList )
            if( (isbn != null && bu.getIsbn() != null && isbn.equals( bu.getIsbn() )) ||
                    (!isEmpty( bu.getTitel() ) &&
                            bu.getTitel().trim().toLowerCase().contains( titelLC )) )
                resultList.add( bu );
        return resultList;
    }

    // Daten eines per ISBN definierten Buches aendern:
    public BuchDO updateBuchByIsbn( Long isbn, String titel, Double preis )
    {
        synchronized( buecherPool ) {
            BuchDO bu = buecherPool.get( isbn );
            if( bu == null ) throw new RuntimeException( "Fehler: Es gibt kein Buch mit der ISBN " + isbn + "." );
            bu.setTitel( titel );
            bu.setPreis( preis );
            buecherPool.put( bu.getIsbn(), bu );
            return bu;
        }
    }

    // Per ISBN definiertes Buch loeschen:
    public BuchDO deleteBuchByIsbn( Long isbn )
    {
        synchronized( buecherPool ) {
            return buecherPool.remove( isbn );
        }
    }

    private static boolean isEmpty( String s )
    {
        return s == null || s.trim().length() <= 0;
    }
}
