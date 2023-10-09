import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The dynamic array that contains a regular array under
 * the hood and modifies it mainly using System.arraycopy()
 *
 * @param <T> parametrized type for items
 */
public class Gallery<T extends Comparable<T>> {
    private Object[] array;
    private int size = 0;

    public Gallery() {
        this(10);
    }

    public Gallery(int capacity) {
        array = new Object[capacity];
    }

    /**
     * This method adds an element to the array
     * @param el element for adding
     */
    public void add(T el) {
        if (el == null) throw new NullPointerException();
        if (size == array.length) reduplication();
        array[size++] = el;
    }

    /**
     * Adds an element to the array with index
     * @param index index for element
     * @param el element
     */
    public void add(int index, T el) {
        if (size == array.length) reduplication();
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = el;
        size++;
    }

    /**
     * Return element by index
     * @param index for returns elements
     * @return element
     */
    public T get(int index) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        return (T) array[index];
    }

    /**
     * Remove element from the list
     * @param index index for element
     */
    public void remove(int index) {
        if (index > size || index < 0) throw new ArrayIndexOutOfBoundsException();
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    /**
     * Search and remove element from the list if that item exists
     * @param el element
     * @return true or false
     */
    public boolean remove(T el) {
        Optional<Integer> index = getIndex(el);
        if (index.isPresent()) {
            remove(index.get());
            return true;
        }
        return false;
    }

    /**
     * @return size for array
     */
    public int size() {
        return size;
    }

    /**
     * @return true or false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Truncates all unused cells
     */
    public void trim() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Sorts this collections
     */
    public void sort(SortType sortType) {
        switch (sortType) {
            case QUICKSORT -> array = quicksort(this.array);
            default -> array = quicksort(this.array);
        }
    }

    private void reduplication() {
        Object[] x2 = new Object[array.length * 2];
        System.arraycopy(array, 0, x2, 0, array.length);
        array = x2;
    }

    private Optional<Integer> getIndex(T el) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(el)) return Optional.of(i);
        }
        return Optional.empty();
    }

    private Object[] quicksort(Object[] collection) {
        if (collection.length < 2) return collection;

        T supportElement = (T) collection[0];

        Object[] lows = Arrays.stream(collection)
                .map((x) -> (T) x)
                .filter(x -> x.compareTo(supportElement) < 0)
                .toArray();

        Object[] highs = Arrays.stream(collection)
                .map(x -> (T) x)
                .filter(x -> x.compareTo(supportElement) > 0)
                .toArray();

        return Stream.concat(
                    Stream.concat(
                            Stream.of(quicksort(lows)),
                            Stream.of(supportElement)
                    ), Stream.of(quicksort(highs)))
                .toArray();
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

}