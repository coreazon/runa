package core;

import java.util.Objects;

/**
 * This class describes a pair
 *
 * @param <S> type of the first element
 * @param <T> type of the second element
 * @author Lucas Alber
 * @author urliz
 * @version 1.0
 */
public class Pair<S, T> implements Comparable<Pair<S, T>> {

    private final S firstElement;
    private final T secondElement;

    /**
     * Constructs new Pair
     *
     * @param firstElement  the first element
     * @param secondElement the second element
     */
    public Pair(S firstElement, T secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    /**
     * returns the first element
     *
     * @return the first element
     */
    public S getFirstElement() {
        return firstElement;
    }

    /**
     * returns the second element
     *
     * @return the second element
     */
    public T getSecondElement() {
        return secondElement;
    }

    /**
     * Compare Method
     *
     * @param o the value to compare with the String value of the firstElement
     * @return the object compared with the String value of the firstElement
     */
    @Override
    public int compareTo(Pair<S, T> o) {
        return this.firstElement.toString().compareTo(o.firstElement.toString());
    }

    /**
     * Checks the equality of two elements
     *
     * @param o object to check the equality
     * @return the equality
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(getFirstElement()
                , pair.getFirstElement()) && Objects.equals(getSecondElement(), pair.getSecondElement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstElement(), getSecondElement());
    }

}
