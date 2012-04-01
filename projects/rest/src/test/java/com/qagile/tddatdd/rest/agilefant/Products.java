package com.qagile.tddatdd.rest.agilefant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 26.03.12
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class Products {

    List<ProductInfo> products = new ArrayList<ProductInfo>();

    public Collection<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }
}
