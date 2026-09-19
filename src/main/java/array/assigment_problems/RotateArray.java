package array.assigment_problems;

import java.util.Arrays;

public class RotateArray {

    public static int[] rotateArray(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return nums;

        k = k % n;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        System.out.println("Rotated 1: " + Arrays.toString(rotateArray(nums1, k1)));

        int[] nums2 = {1, 2};
        int k2 = 3;
        System.out.println("Rotated 2: " + Arrays.toString(rotateArray(nums2, k2)));
    }
}
