import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class Gallery<T extends Comparable<T>> {
    private Object[] array;
    private int size = 0;

    public Gallery() {
        this(10);
    }

    public Gallery(int capacity) {
        array = new Object[capacity];
    }

    public void add(T el) {
        if (el == null) throw new NullPointerException();
        if (size == array.length) reduplication();
        array[size++] = el;
    }

    public void add(int index, T el) {
        if (size == array.length) reduplication();
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = el;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        return (T) array[index];
    }

    public void remove(int index) {
        if (index > size || index < 0) throw new ArrayIndexOutOfBoundsException();
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    public boolean remove(T el) {
        Optional<Integer> index = getIndex(el);
        if (index.isPresent()) {
            remove(index.get());
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void trim() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public void sort() {
        array = quicksort(this.array);
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