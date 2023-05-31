package com.atlasian.practice.filecollection;


import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class FileCollectionService {

    Map<String, List<File>> fileCollection;

    public FileCollectionService() {
        this.fileCollection = new HashMap<>();
    }

    public void addFile(File file, String collection){
        fileCollection.computeIfAbsent(collection, v -> new ArrayList<>()).add(file);
    }

    public List<String> getTopKCollection(int k){
        /*Queue<Pair<String, Integer>> queue = new PriorityQueue<>((k1, k2) -> k2.getValue().compareTo(k1.getValue()));

        for(Map.Entry<String, List<File>> entry : fileCollection.entrySet()){
            queue.add(new Pair<>(entry.getKey(), entry.getValue().size()));
        }

        List<String> topK = new ArrayList<>();
        while(!queue.isEmpty() && k>0){
            topK.add(queue.poll().getKey());
            k--;
        }
        return topK;*/

        return fileCollection.keySet().stream()
                .sorted((k1, k2) -> fileCollection.get(k2).size() - fileCollection.get(k1).size()).limit(k).collect(Collectors.toList());
    }
}
