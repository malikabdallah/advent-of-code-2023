package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MainGtp {
    public static int navigate(Map<String, List<String>> graph, Set<String> startingNodes, String order) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<List<String>> visited = new HashSet<>();

        // Add the initial set of starting nodes
        queue.offer(new ArrayList<>(startingNodes));
        visited.add(new ArrayList<>(startingNodes));

        int steps = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                List<String> currentNodes = queue.poll();

                // Check if all nodes in the current set end with Z
                if (currentNodes.stream().allMatch(node -> node.endsWith("Z"))) {
                    return steps;
                }

                // Process left or right instructions based on the order
                char currentOrder = order.charAt(steps % order.length());

                // Generate the next set of nodes based on the order
                List<String> nextNodes = new ArrayList<>();
                for (String currentNode : currentNodes) {
                    List<String> neighbors = graph.get(currentNode);
                    int currentIndex = neighbors.indexOf(currentNode);
                    int nextIndex = (currentOrder == 'L') ? (currentIndex + 1) % neighbors.size() : (currentIndex - 1 + neighbors.size()) % neighbors.size();
                    nextNodes.add(neighbors.get(nextIndex));
                }

                // Check if the next set of nodes has not been visited
                if (!visited.contains(nextNodes)) {
                    visited.add(nextNodes);
                    queue.offer(nextNodes);
                }
            }

            steps++;
        }

        // If we reach this point, there is no solution
        return -1;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("11A", Arrays.asList("11B", "XXX"));
        graph.put("11B", Arrays.asList("XXX", "11Z"));
        graph.put("11Z", Arrays.asList("11B", "XXX"));
        graph.put("22A", Arrays.asList("22B", "XXX"));
        graph.put("22B", Arrays.asList("22C", "22C"));
        graph.put("22C", Arrays.asList("22Z", "22Z"));
        graph.put("22Z", Arrays.asList("22B", "22B"));
        graph.put("XXX", Arrays.asList("XXX", "XXX"));

        Set<String> startingNodes = graph.keySet().stream().filter(node -> node.endsWith("A")).collect(Collectors.toSet());
        String order = "LR";  // Specify the order LR

        int steps = navigate(graph, startingNodes, order);
        System.out.println("Steps: " + steps);
    }

}
