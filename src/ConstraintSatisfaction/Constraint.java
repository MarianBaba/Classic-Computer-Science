package ConstraintSatisfaction;

import java.util.List;
import java.util.Map;

//elements of a constraint-satisfaction problem = variables, constraints and domains

public abstract class Constraint<V, D> {

    protected List<V> variables;

    public Constraint(List<V> variables) {
        this.variables = variables;
    }

    public abstract boolean satisfied(Map<V, D> assignment);
}
