package com.atlasian.practice.productsubscription;

public class ProductSubscription {

    private String productName;
    private SubscriptionDetail subscriptionDetail;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public SubscriptionDetail getSubscriptionDetail() {
        return subscriptionDetail;
    }

    public void setSubscriptionDetail(SubscriptionDetail subscriptionDetail) {
        this.subscriptionDetail = subscriptionDetail;
    }
}
