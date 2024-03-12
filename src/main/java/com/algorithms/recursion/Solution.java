package com.algorithms.recursion;

import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<List<Integer>> powerset(int[] inputSet) {
    List<List<Integer>> powerset = new ArrayList<>();
    generatePowerset(0, new ArrayList<>(), inputSet, powerset);

    return powerset;
  }

  private void generatePowerset( int currentIndex, List<Integer> selectedSoFar, int[] inputSet, List<List<Integer>> powerset) {
    /*
      Base case: when we have made n decisions then we have
      expressed a 'path'. We reap that path here and return
      to continue the recursion.
    */
    if (currentIndex == inputSet.length) {
      powerset.add(new ArrayList<>(selectedSoFar));
      return;
    }
    
    /*
      Recurse WITH the item at 'currentIndex' in the powerset we
      are working on.
    */
    selectedSoFar.add(inputSet[currentIndex]);
    generatePowerset(currentIndex + 1, selectedSoFar, inputSet, powerset);
    
    // When the recursion returns, remove the choice we made
    selectedSoFar.remove(selectedSoFar.size() - 1);
    
    /*
      Recurse WITHOUT the item at 'currentIndex' in the powerset
      we are working on.
    */
    generatePowerset(currentIndex + 1, selectedSoFar, inputSet, powerset);
  }
}