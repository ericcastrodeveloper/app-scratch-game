package com.cyberspeed.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for map operations.
 */
public class MapUtils {

    /**
     * Merges two maps where the values are lists. If a key exists in both maps,
     * the values from the second map are appended to the list of values in the first map.
     *
     * @param mainMap the primary map to merge into
     * @param newMap the map to merge from
     * @param <K> the type of keys in the maps
     * @param <V> the type of values in the lists
     * @return a new map containing the merged entries
     */
    public static <K, V> Map<K, List<V>> mergeMaps(Map<K, List<V>> mainMap, Map<K, List<V>> newMap) {
        // Validate input maps
        if (mainMap == null || newMap == null) {
            throw new IllegalArgumentException("Input maps cannot be null");
        }

        // Create a new map for the merged results
        Map<K, List<V>> mergedMap = new HashMap<>(mainMap);

        // Iterate through entries in the new map
        for (Map.Entry<K, List<V>> entry : newMap.entrySet()) {
            mergedMap.merge(entry.getKey(), entry.getValue(), (existing, newList) -> {
                // Combine existing and new lists
                List<V> combined = new ArrayList<>(existing);
                combined.addAll(newList);
                return combined;
            });
        }

        return mergedMap;
    }
}