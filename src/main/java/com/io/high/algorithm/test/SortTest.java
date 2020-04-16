package com.io.high.algorithm.test;

import com.io.high.algorithm.CountSort;
import com.io.high.algorithm.MergeSort;
import com.io.high.algorithm.QuickSort;
import com.io.utils.ArrayUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class SortTest {
	public static void main(String[] args) throws Exception {
//		String str;
//		String fileName = "E:\\百宝箱\\project\\data-structure\\src\\main\\java\\com\\io\\high\\algorithm\\test\\200w.txt";
//		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
//		BufferedReader br = new BufferedReader(isr);
//		int data[] = new int[2100002];
//		int i = 0;
//		while ((str = br.readLine()) != null) {
//			double a = Double.valueOf(str);
//			a = a * 100;
//			data[i++] = (int)a;
//		}
//		System.out.println("总共条数为：" + i);
		int data[] = ArrayUtil.createData(1000, 10000);


		// 计数排序
		System.out.println("计数排序开始");
		long start = System.currentTimeMillis();
		CountSort.countSort(data,data.length);
		System.out.println("计数排序总耗时:" + (System.currentTimeMillis() - start) + "ms");

		// 归并排序
		System.out.println("归并排序开始");
		start = System.currentTimeMillis();
		MergeSort.mergeSort(data,0,data.length - 1);
		start = System.currentTimeMillis() - start;
		System.out.println("归并排序总耗时:" + start + "ms");

		// 快速排序
		System.out.println("快速排序开始");
		start = System.currentTimeMillis();
		QuickSort.quickSort(data,0,data.length - 1);
		start = System.currentTimeMillis() - start;
		System.out.println("快速排序总耗时:" + start + "ms");

		// 输出排序文件
//		File file = new File("E:\\百宝箱\\project\\data-structure\\src\\main\\java\\com\\io\\high\\algorithm\\test\\200w.txt");
//		Writer out = new FileWriter(file);
//		for (i = 0; i < data.length; i++) {
//			out.write((data[i] / 100) + "\r\n");
//		}
//		out.close();
	}
}
