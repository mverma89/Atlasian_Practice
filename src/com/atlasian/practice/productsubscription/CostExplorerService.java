package com.atlasian.practice.productsubscription;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CostExplorerService {

    private SubscriptionPlans subscriptionPlans;

    public CostExplorerService(SubscriptionPlans subscriptionPlans) {
        this.subscriptionPlans = subscriptionPlans;
    }

    public Map<Month, Double> monthlyCost(CustomerSubscription customerSubscription){
        Map<Month, Double> monthlyCost = new HashMap<>();
        for(ProductSubscription productSubscription : customerSubscription.getProductSubscriptions()){
            PlanType planType = productSubscription.getSubscriptionDetail().getPlanType();
            Date startDate = productSubscription.getSubscriptionDetail().getStartDate();

            for(int month = startDate.getMonth(); month <= Month.DECEMBER.getValue(); month++){
                monthlyCost.put(Month.of(month), monthlyCost.getOrDefault(Month.of(month), 0d)+subscriptionPlans.getCostByPlan(planType));
            }
        }
        return monthlyCost;
    }

    public Double yearlyCost(CustomerSubscription customerSubscription){
        Double yearlyCost = 0d;
        for(ProductSubscription productSubscription : customerSubscription.getProductSubscriptions()){
            PlanType planType = productSubscription.getSubscriptionDetail().getPlanType();
            Date startDate = productSubscription.getSubscriptionDetail().getStartDate();

            for(int month = startDate.getMonth(); month <= Month.DECEMBER.getValue(); month++){
                yearlyCost += subscriptionPlans.getCostByPlan(planType);
            }
        }
        return yearlyCost;
    }
}
