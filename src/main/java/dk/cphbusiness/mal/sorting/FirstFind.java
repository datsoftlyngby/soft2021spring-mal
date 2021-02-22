package dk.cphbusiness.mal.sorting;

public class FirstFind implements UnionFind {
  private int[] id;
  private int count;

  /*
  ---> 0  1  2  3  4  5  6  7  8  9
  id: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ]
  count: 10

  union(1, 7);

  ---> 0  1  2  3  4  5  6  7  8  9
  id: [0, 1, 2, 3, 4, 5, 6, 1, 8, 9 ]
  count: 9

  union(8, 7);

  ---> 0  1  2  3  4  5  6  7  8  9
  id: [0, 8, 2, 3, 4, 5, 6, 8, 8, 9 ]
  count: 9
   */

  public FirstFind(int n) {
    id = new int[n];
    count = n;
    for (int i = 0; i < n; i++) id[i] = i;
    }

  @Override
  public void union(int p, int q) {
    if (connected(p, q)) return;
    int idQ = id[q]; // 1
    int idP = id[p]; // 8
    for (int i = 0; i < id.length; i++) {
      if (id[i] == idQ) id[i] = idP;
      }
    count--;
    throw new UnsupportedOperationException();
    }

  @Override
  public int find(int p) {
    return id[p];
    }

  @Override
  public int count() {
    return count;
    }

  }
