package com.awakeseller.awakeseller.client.etsy.buyertaxonomy.model;

import java.util.List;

import lombok.Data;

@Data
public class BuyerTaxonomyNodes {

    private Integer count;

    private List<BuyerTaxonomyNode> results;

}
