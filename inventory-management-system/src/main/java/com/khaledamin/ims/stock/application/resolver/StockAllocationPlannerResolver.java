package com.khaledamin.ims.stock.application.resolver;

import com.khaledamin.ims.stock.application.service.StockAllocationPlanner;
import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StockAllocationPlannerResolver {

    private final Map<StockAllocationStrategy, StockAllocationPlanner> planners;

    public StockAllocationPlannerResolver(List<StockAllocationPlanner> planners) {

        this.planners = planners
                .stream()
                .collect(
                        Collectors.toMap(
                                StockAllocationPlanner::getStrategy,
                                Function.identity()
                        )
                );
    }

    public StockAllocationPlanner resolve(StockAllocationStrategy strategy) {

        return planners.get(strategy);
    }
}