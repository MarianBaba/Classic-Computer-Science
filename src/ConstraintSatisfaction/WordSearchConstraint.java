package ConstraintSatisfaction;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ConstraintSatisfaction.WordGrid.GridLocation;

public class WordSearchConstraint extends Constraint<String, List<GridLocation>> {

    public WordSearchConstraint(List<String> words) {
        super(words);
    }

    @Override
    public boolean satisfied(Map<String, List<GridLocation>> assignment) {
        List<GridLocation> allLocations = assignment.values().stream().flatMap(Collection::stream)
                .collect(Collectors.toList());

        Set<GridLocation> allLocationsSet = new HashSet<>(allLocations);

        return allLocations.size() == allLocationsSet.size();
    }

    public static void main(String... strings) {
        WordGrid grid = new WordGrid(9, 9);
        List<String> words = List.of("MATTHEW", "JOE", "MARY", "SARAH", "SALLY");

        Map<String, List<List<GridLocation>>> domains = new HashMap<>();
        for (String word : words) {
            domains.put(word, grid.generateDomain(word));
        }

        CSP<String, List<GridLocation>> csp = new CSP<>(words, domains);
        csp.addConstraint(new WordSearchConstraint(words));

        Map<String, List<GridLocation>> solution = csp.backtrackingSearch();
        if (solution == null) {
            System.out.println("No solution found");
        } else {
            Random random = new Random();
            for (Entry<String, List<GridLocation>> item : solution.entrySet()) {
                String word = item.getKey();
                List<GridLocation> locations = item.getValue();

                if (random.nextBoolean()) {
                    Collections.reverse(locations);
                }
                grid.mark(word, locations);
            }
            System.out.println(grid);
        }
    }
}
