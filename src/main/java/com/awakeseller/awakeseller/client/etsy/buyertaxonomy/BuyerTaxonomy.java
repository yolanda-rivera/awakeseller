package com.awakeseller.awakeseller.client.etsy.buyertaxonomy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.awakeseller.awakeseller.client.etsy.auth.OAuthFeignConfig;
import com.awakeseller.awakeseller.client.etsy.buyertaxonomy.model.BuyerTaxonomyNodes;

@FeignClient(name = "buyer-taxonomy", url = "https://openapi.etsy.com/v3/application/buyer-taxonomy/",
        configuration = OAuthFeignConfig.class)
public interface BuyerTaxonomy {

    @GetMapping("nodes")
    BuyerTaxonomyNodes getBuyerTaxonomyNodes();
}
