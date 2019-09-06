import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

import static java.lang.String.format;

public class FilteredList<E> extends LinkedList<E> {

    private final Predicate<E> filter;

    public FilteredList(final Predicate<E> filter) {
        this.filter = filter;
    }

    @Override
    public boolean add(final E element) {
        testElement(element);
        return super.add(element);
    }

    @Override
    public void add(final int index, final E element) {
        testElement(element);
        super.add(index, element);
    }

    @Override
    public void addFirst(final E element) {
        testElement(element);
        super.addFirst(element);
    }

    @Override
    public void addLast(final E element) {
        testElement(element);
        super.addLast(element);
    }

    @Override
    public void push(final E element) {
        addFirst(element);
    }

    @Override
    public boolean offerFirst(final E element) {
        addFirst(element);
        return true;
    }

    @Override
    public boolean offer(final E element) {
        addLast(element);
        return true;
    }

    @Override
    public boolean offerLast(final E element) {
        addLast(element);
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        collection.forEach(this::testElement);
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> collection) {
        collection.forEach(this::testElement);
        return super.addAll(index, collection);
    }

    private void testElement(final E element) {
        if (!filter.test(element)) {
            throw new IllegalArgumentException(format("Unable to add element %s to the list!", element));
        }
    }
}
