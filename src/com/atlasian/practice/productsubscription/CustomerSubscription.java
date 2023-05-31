package com.atlasian.practice.productsubscription;

import java.util.List;

public class CustomerSubscription {

    private String customerId;
    private String customerName;
    private List<ProductSubscription> productSubscriptions;

    public CustomerSubscription(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ProductSubscription> getProductSubscriptions() {
        return productSubscriptions;
    }

    public void setProductSubscriptions(List<ProductSubscription> productSubscriptions) {
        this.productSubscriptions = productSubscriptions;
    }
}
