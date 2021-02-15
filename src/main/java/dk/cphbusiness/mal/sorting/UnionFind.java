package dk.cphbusiness.mal.sorting;

public interface UnionFind {
  void union(int p, int q);        // unite points p and q
  int find(int p);                 // given point p, return set of p
  boolean connected(int p, int q); // are the points p and q in the same set
  int count();                     // How many sets are there
  }
