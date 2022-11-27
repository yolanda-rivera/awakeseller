package com.awakeseller.awakeseller.client.etsy.buyertaxonomy.model;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuyerTaxonomyNode {

    private Integer id;

    private Integer level;

    private String name;

    private Integer parentId;

    private List<BuyerTaxonomyNode> children;

    private List<Integer> fullPathTaxonomyIds;

}
