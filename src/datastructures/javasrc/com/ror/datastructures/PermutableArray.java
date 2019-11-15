package com.ror.datastructures;


import java.util.NoSuchElementException;

public class PermutableArray<T> {
    private final T[] array;
    private final int[] order;
    private final Direction[] directions;


    public PermutableArray(final T[] array) {
        this.array = array;
        this.order = new int[array.length];
        this.directions = new Direction[array.length];
        for (int i = 0; i < this.array.length; ++i) {
            this.order[i] = i;
            this.directions[i] = Direction.LEFT;
        }
    }

    /**
     * Descending order indicates the end for permutations
     */
    private boolean haveMorePermutations() {
        if (order.length == 0) {
            return false;
        }

        for (int i = 1; i < order.length; ++i) {
            if (order[i - 1] < order[i] && directions[i] == Direction.LEFT) {
                return true;
            }

            if (order[i - 1] > order[i] && directions[i - 1] == Direction.RIGHT) {
                return true;
            }
        }

        return false;
    }

    public T get(final int index) {
        return array[order[index]];
    }

    private void swapOrder(int index1, int index2) {
        final int order1 = order[index1];
        order[index1] = order[index2];
        order[index2] = order1;

        final Direction direction1 = directions[index1];
        directions[index1] = directions[index2];
        directions[index2] = direction1;
    }

    private void invertDirections(int greaterThan) {
        for (int i = 0; i < order.length; ++i) {
            if (order[i] > greaterThan) {
                if (directions[i] == Direction.LEFT) {
                    directions[i] = Direction.RIGHT;
                } else {
                    directions[i] = Direction.LEFT;
                }
            }
        }
    }

    public void nextPermutation() {
        if (!haveMorePermutations()) {
            throw new NoSuchElementException("All permutations were already exhausted");
        }

        int largestMobileOrderIndex = -1;

        for (int i = 0; i < order.length - 1; ++i) {
            int mobileIndex = -1;
            if (order[i] < order[i + 1] && directions[i + 1] == Direction.LEFT) {
                mobileIndex = i + 1;
            } else if (order[i] > order[i + 1] && directions[i] == Direction.RIGHT) {
                mobileIndex = i;
            }
            if (largestMobileOrderIndex == -1 ||
                    (mobileIndex != -1 && order[mobileIndex] > order[largestMobileOrderIndex])) {
                largestMobileOrderIndex = mobileIndex;
            }
        }

        invertDirections(order[largestMobileOrderIndex]);

        if (directions[largestMobileOrderIndex] == Direction.LEFT) {
            swapOrder(largestMobileOrderIndex - 1, largestMobileOrderIndex);
        } else if (directions[largestMobileOrderIndex] == Direction.RIGHT) {
            swapOrder(largestMobileOrderIndex, largestMobileOrderIndex + 1);
        }
    }

    private enum Direction {LEFT, RIGHT}
}
