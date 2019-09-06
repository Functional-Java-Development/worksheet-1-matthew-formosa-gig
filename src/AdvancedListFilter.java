import java.util.List;

@FunctionalInterface
public interface AdvancedListFilter<E> {

    E filter(List<E> list, E element);
}
