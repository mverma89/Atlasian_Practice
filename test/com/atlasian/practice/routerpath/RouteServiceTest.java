package com.atlasian.practice.routerpath;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class RouteServiceTest {

    @Test
    public void testRoute(){

        RouteService routeService = new RouteService();
        routeService.addRoute("/aaa/bbb/ccc", "abc");
        routeService.addRoute("/aaa/bbb", "ab");
        routeService.addRoute("/aaa/ddd/ccc", "adc");
        routeService.addRoute("/kkk/bbb/ddd", "kbd");

        new Verifications(){
            {
                Assert.assertEquals("abc",routeService.route("/aaa/bbb/ccc"));
                Assert.assertEquals("ab",routeService.route("/aaa/bbb"));
                Assert.assertEquals("adc",routeService.route("/aaa/ddd/ccc"));
                Assert.assertEquals("kbd",routeService.route("/kkk/bbb/ddd"));
            }
        };
    }
}
