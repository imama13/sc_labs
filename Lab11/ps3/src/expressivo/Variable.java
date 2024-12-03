package expressivo;

import java.util.Objects;

/** Represents a variable in the expression. */
public class Variable implements Expression {
    private final String name;

    /** Abstraction Function:
     *   Represents a variable identified by a non-empty string name.
     * Rep Invariant:
     *   name is not null or empty.
     * Safety from Rep Exposure:
     *   name is private, final, and immutable.
     */
    private void checkRep() {
        assert name != null && !name.isEmpty();
    }

    public Variable(String name) {
        this.name = name;
        checkRep();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Variable)) return false;
        Variable that = (Variable) thatObject;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    @Override
    public double evaluate(Environment env) {
        if (!env.containsVariable(name)) {
            throw new IllegalArgumentException("Variable " + name + " is not defined.");
        }
        return env.getValue(name);
    }
    
    @Override
    public Expression differentiate(String variable) {
        return name.equals(variable) ? new Value(1) : new Value(0);
    }
}
