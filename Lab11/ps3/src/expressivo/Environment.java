package expressivo;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Double> variables = new HashMap<>();

    public void setVariable(String variable, double value) {
        variables.put(variable, value);
    }

    public double getValue(String variable) {
        return variables.get(variable);
    }

    public boolean containsVariable(String variable) {
        return variables.containsKey(variable);
    }
}