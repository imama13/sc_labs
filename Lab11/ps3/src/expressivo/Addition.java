package expressivo;

import java.util.Objects;

/** Represents an addition operation in the expression. */
public class Addition implements Expression {
    private final Expression left, right;

    /** Abstraction Function:
     *   Represents left + right.
     * Rep Invariant:
     *   left and right are not null.
     * Safety from Rep Exposure:
     *   All fields are private, final, and immutable.
     */
    private void checkRep() {
        assert left != null && right != null;
    }

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }

    @Override
    public String toString() {
        return String.format("%s + %s", left.toString(), right.toString());
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Addition)) return false;
        Addition that = (Addition) thatObject;
        return this.left.equals(that.left) && this.right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
    
    @Override
    public Expression differentiate(String variable) {
        return new Addition(left.differentiate(variable), right.differentiate(variable));
    }

    @Override
    public double evaluate(Environment env) {
        return left.evaluate(env) + right.evaluate(env);
    }

}
