package com.atlasian.practice.productsubscription;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RunWith(JMockit.class)
public class ProductSubscriptionTest {

    @Test
    public void testSingleSubscriptionCost(){

        SubscriptionPlans subscriptionPlans = new SubscriptionPlans();
        subscriptionPlans.addOrUpdatePlan(PlanType.BASIC, 100d);
        subscriptionPlans.addOrUpdatePlan(PlanType.STANDARD, 300d);
        subscriptionPlans.addOrUpdatePlan(PlanType.PREMIUM, 500d);

        CustomerSubscription customerSubscription = new CustomerSubscription("M_121", "Mohit");
        ProductSubscription productSubscription1 = new ProductSubscription();
        productSubscription1.setProductName("JIRA");
        SubscriptionDetail subscriptionDetail1 = new SubscriptionDetail(PlanType.STANDARD, new Date(2023, 2, 23));
        productSubscription1.setSubscriptionDetail(subscriptionDetail1);
        customerSubscription.setProductSubscriptions(Arrays.asList(productSubscription1));

        CostExplorerService costExplorerService = new CostExplorerService(subscriptionPlans);

        new Verifications(){
            {
                Map<Month, Double> monthlyCost = costExplorerService.monthlyCost(customerSubscription);
                Assert.assertNotNull(monthlyCost);
                Assert.assertEquals(11, monthlyCost.size());
                Assert.assertEquals((double) 300.0, (double)monthlyCost.get(Month.AUGUST),0);
                Assert.assertNull(monthlyCost.get(Month.JANUARY));

                Double yearlyCost = costExplorerService.yearlyCost(customerSubscription);
                Assert.assertNotNull(yearlyCost);
                Assert.assertEquals((double) 3300.0, yearlyCost,0);
            }
        };


    }

    @Test
    public void testYearlyCost(){
        SubscriptionPlans subscriptionPlans = new SubscriptionPlans();
        subscriptionPlans.addOrUpdatePlan(PlanType.BASIC, 100d);
        subscriptionPlans.addOrUpdatePlan(PlanType.STANDARD, 300d);
        subscriptionPlans.addOrUpdatePlan(PlanType.PREMIUM, 500d);

        CustomerSubscription customerSubscription = new CustomerSubscription("M_121", "Mohit");
        ProductSubscription productSubscription1 = new ProductSubscription();
        productSubscription1.setProductName("JIRA");
        SubscriptionDetail subscriptionDetail1 = new SubscriptionDetail(PlanType.STANDARD, new Date(2023, 2, 23));
        productSubscription1.setSubscriptionDetail(subscriptionDetail1);

        ProductSubscription productSubscription2 = new ProductSubscription();
        productSubscription2.setProductName("BIRA");
        SubscriptionDetail subscriptionDetail2 = new SubscriptionDetail(PlanType.PREMIUM, new Date(2023, 1, 23));
        productSubscription2.setSubscriptionDetail(subscriptionDetail2);
        customerSubscription.setProductSubscriptions(Arrays.asList(productSubscription1,productSubscription2));

        CostExplorerService costExplorerService = new CostExplorerService(subscriptionPlans);

        new Verifications(){
            {
                Map<Month, Double> monthlyCost = costExplorerService.monthlyCost(customerSubscription);
                Assert.assertNotNull(monthlyCost);
                Assert.assertEquals(12, monthlyCost.size());
                Assert.assertEquals((double) 800.0, (double)monthlyCost.get(Month.AUGUST),0);
                Assert.assertNotNull(monthlyCost.get(Month.JANUARY));
                Assert.assertEquals((double) 500.0, (double)monthlyCost.get(Month.JANUARY),0);

                Double yearlyCost = costExplorerService.yearlyCost(customerSubscription);
                Assert.assertNotNull(yearlyCost);
                Assert.assertEquals((double) 9300.0, yearlyCost,0);
            }
        };


    }
}
