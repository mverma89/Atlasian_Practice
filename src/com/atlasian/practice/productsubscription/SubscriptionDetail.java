package com.atlasian.practice.productsubscription;

import java.util.Date;

public class SubscriptionDetail {

    private PlanType planType;
    private Date startDate;

    public SubscriptionDetail(PlanType planType, Date startDate) {
        this.planType = planType;
        this.startDate = startDate;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
