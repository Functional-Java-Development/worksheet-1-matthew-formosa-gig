import java.util.Collection;
import java.util.LinkedList;

public class AdvancedFilteredList<E> extends LinkedList<E> {

    private final AdvancedListFilter<E> filter;

    public AdvancedFilteredList(final AdvancedListFilter<E> filter) {
        this.filter = filter;
    }

    @Override
    public boolean add(final E element) {
        return super.add(filter.filter(this, element));
    }

    @Override
    public void add(final int index, final E element) {
        super.add(index, filter.filter(this, element));
    }

    @Override
    public void addFirst(final E element) {
        super.addFirst(filter.filter(this, element));
    }

    @Override
    public void addLast(final E element) {
        super.addLast(filter.filter(this, element));
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
        collection.forEach(element -> {
            try {
                add(element);
            } catch (final IllegalArgumentException illegalArgumentException) {
                // Continue
            }
        });
        return true;
    }

    @Override
    public boolean addAll(int index, final Collection<? extends E> collection) {
        for (final var element : collection) {
            try {
                add(index, element);
                index++;
            } catch (final IllegalArgumentException illegalArgumentException) {
                // Continue
            }
        }
        return true;
    }
}
