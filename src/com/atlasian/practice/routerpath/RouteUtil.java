package com.atlasian.practice.routerpath;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RouteUtil {

    public static List<String> splitRoute(String route, String delimiter){
        return Arrays.stream(Optional.ofNullable(route).orElse("").split(delimiter))
                .filter( r -> r!=null && r.length()>0).collect(Collectors.toList());
    }
}
