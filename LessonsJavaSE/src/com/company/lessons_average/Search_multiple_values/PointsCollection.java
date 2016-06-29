package com.company.lessons_average.Search_multiple_values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PointsCollection {

    private Map<PointsIdentifier, Points> map;

    public PointsCollection() {
        map = new HashMap<>();
    }

    public void add(Points p) {
        map.put(new PointsIdentifier(p.name, p.address), p);
    }

    public List<Points> findIdsContaining(String seq) {
        List<Points> resultList = new ArrayList<>();
        for (Map.Entry<PointsIdentifier, Points> entry : map.entrySet()) {
            if (entry.getKey().contains(seq)) {
                resultList.add(entry.getValue());
            }
        }
        // optionally cache result
        return resultList;
    }
}
