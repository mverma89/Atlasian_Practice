package com.atlasian.practice.routerpath;

import java.util.List;

public class RouteService {

    private static String DELIMITER = "/";
    private Route root;

    public RouteService() {
        this.root = new Route("");
    }

    public void addRoute(String route, String path){
        List<String> splitRoute = RouteUtil.splitRoute(route, DELIMITER);
        Route currentRoute = root;
        int r = 0;
        while(currentRoute != null && r < splitRoute.size()){
            if(!currentRoute.getRoute().containsKey(splitRoute.get(r))){
                currentRoute.getRoute().put(splitRoute.get(r), new Route(splitRoute.get(r)));
            }
            currentRoute = currentRoute.getRoute().get(splitRoute.get(r));
            r++;

            if(currentRoute == null){
                throw new RuntimeException("Route is not found!!");
            }
        }
        currentRoute.setRoutedPath(path);
    }

    public String route(String route){
        List<String> splitRoute = RouteUtil.splitRoute(route, DELIMITER);
        Route currentRoute = root;
        int r = 0;
        while(currentRoute != null && r < splitRoute.size()){
            currentRoute = currentRoute.getRoute().get(splitRoute.get(r++));

            if(currentRoute == null){
                throw new RuntimeException("Route is not found!!");
            }
        }
        return currentRoute.getRoutedPath();
    }
}
