package com.atlasian.practice.routerpath;

import java.util.HashMap;
import java.util.Map;

public class Route {

    private Map<String, Route> route;
    private String routedPath;

    public Route(String routedPath) {
        this.routedPath = routedPath;
        this.route = new HashMap<>();
    }

    public Map<String, Route> getRoute() {
        return route;
    }

    public void setRoute(Map<String, Route> route) {
        this.route = route;
    }

    public String getRoutedPath() {
        return routedPath;
    }

    public void setRoutedPath(String routedPath) {
        this.routedPath = routedPath;
    }
}
