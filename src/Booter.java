import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.random;
import static java.lang.String.format;
import static java.util.Arrays.setAll;
import static java.util.Arrays.sort;
import static java.util.Arrays.stream;

public class Booter {

    public static void main(final String[] args) {
        // 1
        final var randomPositiveIntegers = new Integer[10];
        setAll(randomPositiveIntegers, Booter::fillArrayWithPositiveRandomIntegers);
        final Comparator<Integer> comparator = (firstInteger, secondInteger) -> {
            final var firstIntegerLength = firstInteger.toString().length();
            final var secondIntegerLength = secondInteger.toString().length();
            if (firstIntegerLength == secondIntegerLength) {
                return secondInteger.compareTo(firstInteger);
            }
            if (firstIntegerLength > secondIntegerLength) {
                return 1;
            }
            return -1;
        };
        sort(randomPositiveIntegers, comparator);
        stream(randomPositiveIntegers).forEach(System.out::println);
        System.out.println();

        // 2
        final var filteredList = new FilteredList<Integer>(element -> element % 2 == 1);
        filteredList.add(1);
        filteredList.add(3);
        filteredList.add(5);
        filteredList.forEach(element -> System.out.println(format("Element %d is in the list", element)));
        try {
            filteredList.add(6);
        } catch (final IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }
        System.out.println();

        // 3
        final var advancedFilteredList = new AdvancedFilteredList<Integer>((list, element) -> {
            if (list.stream().noneMatch(existingElement -> abs(element) % existingElement == 0)) {
                return abs(element);
            }
            throw new IllegalArgumentException(format("Unable to add element %d to the list!", element));
        });
        advancedFilteredList.addAll(List.of(81, 9, 27, -5, 14, 15, 3, -6));
        advancedFilteredList.forEach(element -> System.out.println(format("Element %d is in the list", element)));
    }

    private static int fillArrayWithPositiveRandomIntegers(final int index) {
        return (int) (random() * 10000);
    }
}