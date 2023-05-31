package com.atlasian.practice.productsubscription;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionPlans {

    private Map<PlanType, Double> planMonthlyCost;

    public SubscriptionPlans() {
        this.planMonthlyCost = new HashMap<>();
    }

    public void addOrUpdatePlan(PlanType planType, Double monthlyCost){
        planMonthlyCost.put(planType, monthlyCost);
    }

    public Map<PlanType, Double> getPlanMonthlyCost() {
        return planMonthlyCost;
    }

    public void setPlanMonthlyCost(Map<PlanType, Double> planMonthlyCost) {
        this.planMonthlyCost = planMonthlyCost;
    }

    public Double getCostByPlan(PlanType planType){
        if(planMonthlyCost.containsKey(planType))
            return planMonthlyCost.get(planType);
        throw new RuntimeException("No such plan exist!!");
    }
}
