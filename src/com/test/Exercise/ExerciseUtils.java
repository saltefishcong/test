package com.test.Exercise;

public class ExerciseUtils {

	public static <T> T findIntermediateNum(T[] nums, Comparator<? super T> comparator) {

		int IntermediateNum_index = 0;
		int nums_index = (nums.length - 1) / 2;
		T[] new_nums = (T[]) new Object[nums.length];
		for (int i = 1; i < nums_index; i++) {
			if (comparator.compare(nums[IntermediateNum_index], nums[i]) > 0) {
				new_nums[IntermediateNum_index] = nums[IntermediateNum_index];
				IntermediateNum_index = i;
			} else {
				new_nums[i] = new_nums[i];
			}
		}
		return null;
	}
}

class sort {
	public static <T> T[] bubbleSort(T[] arr, int index) { // 快速排序,根据传进来的数组排序
		if (arr instanceof Nums[]) {
			int Cardinal_index = index;
			Nums[] shapes = (Nums[]) arr;
			for (int i = 0; i < arr.length; i++) {
				System.out.print(shapes[i].getNum() + "    ");
			}
			for (int i = arr.length - 1; i >= (int) arr.length / 2; i--) {
				System.out.println(shapes[Cardinal_index].getNum() + "   " + shapes[i].getNum());
				if (shapes[Cardinal_index].getNum() > shapes[i].getNum()) {
					Nums temp = shapes[Cardinal_index];
					shapes[Cardinal_index] = shapes[i];
					shapes[i] = temp;
					Cardinal_index = i;
					System.out.println("   右边进行交换   " + Cardinal_index);
					for (int o = 0; o < (int) arr.length / 2; o++) {
						if (shapes[o].getNum() > shapes[Cardinal_index].getNum()) {
							temp = shapes[Cardinal_index];
							shapes[Cardinal_index] = shapes[o];
							shapes[o] = temp;
							Cardinal_index = o;
							System.out.println("     左边进行交换   " + Cardinal_index);
							break;
						}
					}
				}
			}
			for (int i = 0; i < arr.length; i++) {
				System.out.print(shapes[i].getNum() + "    ");
			}
		}
		return null;
	}
}
