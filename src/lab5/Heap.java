package lab5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Heap<T extends Comparable<T>> {

    private ArrayList<T> container;

    public Heap() {
        container = new ArrayList<>();
    }

    public T peek() {
    	if (container.size() == 0)
    		throw new IllegalStateException();
        return container.get(0);
    }

    public T poll() {
    	T temp = container.get(0);
    	if (container.size() == 0)
    		throw new IllegalStateException();
    	else {
    		container.set(0, container.get(size()-1));
    		container.remove(size()-1);
    		this.heapifyDown();
    	}
        return temp;
    }

    private void heapifyDown() {
        int pos = 0;
        while (hasLeft(pos)) {
            int smallerChildIndex = getLeftIndex(pos);
            if (hasRight(pos) && container.get(getRightIndex(pos)).compareTo(container.get(getLeftIndex(pos))) < 0) {
                smallerChildIndex = getRightIndex(pos);
            }
            if (container.get(pos).compareTo(container.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(pos, smallerChildIndex);
            }
            pos = smallerChildIndex;
        }
    }

    public void add(T obj) {
    	container.add(obj);
    	this.heapifyUp();
    }

    public void addAll(Collection<T> list) {
        list.forEach(this::add);
    }

    private void heapifyUp() {
    	int pos = container.size() - 1;
    	while (hasParent(pos) && container.get(pos).compareTo(container.get(getParentIndex(pos))) == -1) {
    		swap(pos, getParentIndex(pos));
    		pos = getParentIndex(pos);
    	}

    }

    public int size() {
        return container.size();
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasParent(int i) {
        return getParentIndex(i) >= 0;
    }

    private boolean hasLeft(int i) {
        return getLeftIndex(i) < container.size();
    }

    private boolean hasRight(int i) {
        return getRightIndex(i) < container.size();
    }

    private void swap(int i1, int i2) {
        Collections.swap(container, i1, i2);
    }

}
