package com.qagile.bookstore.ws;

import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * User: ful
 * TO -> Return Transfer Object
 */
@XmlRootElement
public class BuecherTO {

    @XmlElement(nillable = true)
    private List<BuchDO> results = new ArrayList<BuchDO>();
    private String       message;
    private Integer      returncode;

    public List<BuchDO> getResults()    { return results;    }
    public String       getMessage()    { return message;    }
    public Integer      getReturncode() { return returncode; }
    public void setMessage(    String  message    ) { this.message    = message;    }
    public void setReturncode( Integer returncode ) { this.returncode = returncode; }
}
