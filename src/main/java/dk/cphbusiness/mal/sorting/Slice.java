package dk.cphbusiness.mal.sorting;

public class Slice<T extends Comparable<T>> {
  private final T[] items;
  private final int floor;
  private final int roof;

  @Override
  public String toString() {
    return ""+floor+" ["+items[floor]+",...,"+items[roof - 1]+"] "+roof;
    }

  public Slice(T[] items, int floor, int roof) {
    this.items = items;
    this.floor = floor;
    this.roof = roof;
    }

  public Slice(T[] items) {
    this(items, 0, items.length);
    }

  public int getLength() { return roof - floor; }

  public T get(int index) {
    if (index < 0 || getLength() <= index)
        throw new ArrayIndexOutOfBoundsException();
    return items[floor + index];
    }

  public void set(int index, T value) {
    if (index < 0 || getLength() <= index)
        throw new ArrayIndexOutOfBoundsException();
    items[floor + index] = value;
    }

  public void swap(int i, int j) {
    T temp = get(i);
    set(i, get(j));
    set(j, temp);
    }

  public int compareWith(T value, int index) {
    if (index < 0) return 1;
    if (index >= getLength()) return -1;
    return value.compareTo(get(index));
    }

  public Slice<T> before(int limit) {
    return new Slice<>(items, floor, floor + limit);
    }

  public Slice<T> after(int limit) {
    return new Slice<>(items, floor + limit, roof);
    }

  }
