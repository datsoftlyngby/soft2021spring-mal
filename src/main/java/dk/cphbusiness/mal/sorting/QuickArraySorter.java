package dk.cphbusiness.mal.sorting;

public class QuickArraySorter<T extends Comparable<T>> implements ArraySorter<T>  {
  @Override
  public void sort(T[] items) {
    sort(new Slice<T>(items));
    }

  private int arrangeAndSplit(Slice<T> slice) {
    int left = 0;
    int right = slice.getLength() - 1;
    T pivot = slice.get(slice.getLength()/2);
    while (true) {
      while (slice.compareWith(pivot, left) > 0) {
        left++;
        }
      while (slice.compareWith(pivot, right) < 0) {
        right--;
        }
      if (left < right) slice.swap(left, right);
      else break;
      }
    return left;
    }

  private void sort(Slice<T> slice) {
    if (slice.getLength() <= 1) return;
    int limit = arrangeAndSplit(slice);
    sort(slice.before(limit));
    sort(slice.after(limit));
    }

  public static void main(String[] args) {
    Integer[] numbers = new Integer[] { 7, 3, 10, 1, 9, 5, 17, 4, 21 };
    var sorter = new QuickArraySorter<Integer>();
    sorter.sort(numbers);
    for (int i = 0; i < numbers.length; i++) {
      System.out.println(numbers[i]);
      }
    }

  }

