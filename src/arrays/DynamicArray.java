package arrays;

/**
 * A dynamic array implementation that resizes automatically.
 * This class provides array functionality with dynamic capacity management.
 *
 * @param <T> The type of elements stored in the array
 */
public class DynamicArray<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    /**
     * Constructs a dynamic array with default capacity.
     */
    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a dynamic array with specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the array
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Initial capacity cannot be negative");

        elements = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Returns the number of elements in the array.
     *
     * @return the number of elements
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the array is empty.
     *
     * @return true if the array contains no elements
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current capacity of the array.
     *
     * @return the current capacity
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int capacity() {
        return elements.length;
    }

    /**
     * Checks if the given index is valid.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if index is out of range
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Gets the element at the specified index.
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Sets the element at the specified index.
     *
     * @param index the index of the element to replace
     * @param element the element to be stored
     * @return the previous element at the specified position
     * @throws IndexOutOfBoundsException if index is out of range
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        elements[index] = element;

        return oldValue;
    }

    /**
     * Ensures that the array has enough capacity to hold the specified number of elements.
     *
     * @param minCapacity the minimum capacity needed
     * Time Complexity: O(n) when resizing
     * Space Complexity: O(n) when resizing
     */
    private void ensureCapacity(int minCapacity) {
        if (elements.length == 0) {
            elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }

        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * GROWTH_FACTOR, minCapacity);
            resize(newCapacity);
        }
    }

    /**
     * Resizes the array to the specified capacity.
     *
     * @param newCapacity the new capacity
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private void resize(int newCapacity) {
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
    /**
     * Adds an element to the end of the array.
     *
     * @param element the element to be added
     * Time Complexity: O(1) amortized, O(n) worst case when resizing
     * Space Complexity: O(n) when resizing, O(1) otherwise
     */
    public void add(T element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    /**
     * Inserts an element at the specified index.
     *
     * @param index the index at which to insert
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if index is out of range
     * Time Complexity: O(n) - need to shift elements
     * Space Complexity: O(1) if no resize needed, O(n) if resize needed
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        ensureCapacity(size+1);
        System.arraycopy(elements, index, elements, index+1, size-index);
        elements[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index is out of range
     * Time Complexity: O(n) - need to shift elements
     * Space Complexity: O(1)
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);  // check if index is within bounds
        T removedElement = (T) elements[index];  // store value of the element we are to remove

        // copy elements with a leftward shift
        // copy all elements in elements[index+1..end] to elements[index..size-index-1]
        // size-index-1? all elements after the removal point to the end of active array.
        // -1 signifies we have to account for the removed element now, which is one element lesser
        System.arraycopy(elements, index+1, elements, index, size-index-1);

        // if the array utilization is < 25%, resize the array into half it's size
        if (size > 0 && size < elements.length / 4) {
            resize(elements.length/2);
        }

        return removedElement;
    }

    /**
     * Returns a string representation of the array.
     *
     * @return a string representation of the array
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @Override
    public String toString() {
        if (size == 0)
            return "[]";

        StringBuilder sb = new StringBuilder("[");

        for (int idx=0; idx<size; idx++) {
            sb.append(String.valueOf(elements[idx]));
            if (idx < size-1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
