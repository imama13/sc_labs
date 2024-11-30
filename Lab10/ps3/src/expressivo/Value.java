package expressivo;

import java.util.Objects;

/** Represents a numeric value in the expression. */
public class Value implements Expression {
    private final double value;

    /** Abstraction Function:
     *   Represents the constant numeric value of the expression.
     * Rep Invariant:
     *   value is a finite double.
     * Safety from Rep Exposure:
     *   All fields are private and final. No mutators provided.
     */
    private void checkRep() {
        assert Double.isFinite(value);
    }

    public Value(double value) {
        this.value = value;
        checkRep();
    }

    @Override
    public String toString() {
        return String.format("%.5f", value).replaceAll("\\.?0+$", "");
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Value)) return false;
        Value that = (Value) thatObject;
        return Math.abs(this.value - that.value) < 1e-5;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(value * 1e5));
    }
}
