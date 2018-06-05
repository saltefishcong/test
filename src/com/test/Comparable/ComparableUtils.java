package com.test.Comparable;

public class ComparableUtils {

	/**
	 * 找出面积最大的图形,用Comparable实现
	 * 
	 * @param arr
	 *            要进行检查的数组
	 * @return 最大面积的图形对象
	 */
	public static Comparable findMax(Comparable[] arr) {
		int maxindex = 0; // 初始化最大面积的图形为第一个
		for (int i = 1; i < arr.length; i++) { // 开始循环
			if (arr[i].compareTo(arr[maxindex]) > 0) { // 判断下一个图形面积是否大于当前最大面积的图形
				maxindex = i; // 更换最大面积图形的位置
			}
		}
		return arr[maxindex];
	}

	/**
	 * 使用泛型实现类型对比,用于返回任何实现了Comparable接口的类,不止步于Shape类,令代码更加灵活
	 * 
	 * @param arr
	 *            要进行检查的数组
	 * @return 最大面积的图形对象
	 */
	public static <T extends Comparable<T>> T findMaxs(T[] arr) {
		int maxindex = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].compareTo(arr[maxindex]) > 0) {
				maxindex = i;
			}
		}
		return arr[maxindex];
	}

	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<? super T>> T findMaxBefore(T[] arr) {
		int maxindex = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].compareTo(arr[maxindex]) > 0) {
				maxindex = i;
			}
		}
		return arr[maxindex];
	}

	/**
	 * 判断数组是否是否包含x
	 * 
	 * @param arr
	 *            检查数组
	 * @param x
	 *            检查数据
	 * @return 是否存在
	 */
	public static <T> boolean contains(T[] arr, T x) {
		for (T s : arr) {
			if (s.equals(x)) {
				return true;
			}
		}
		return false;
	}
}
