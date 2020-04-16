package com.io.base.structure.array;

import java.io.*;
import java.util.Random;

/**
 * 统计140亿人口年龄分布数量
 * @author sk
 * @date 2019-10-30
 */
public class AgeStas {

	public static void main(String[] args) throws Exception {
		// 写数据
		AgeStas.writeData();
		String str;
		String fileName = "c:/age.txt";
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
		
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(isr);
		int tot = 0 ;
		int[] data = new int[200];
		// 一行一行的读 时间复杂度O(n)
		while((str = br.readLine()) != null){
			int age = Integer.valueOf(str);
			data[age] ++ ;
			tot ++ ;
		}
		//O(n) 14亿. 100万/秒 *1000 = 10亿 100~1000s之间 => 500s以下 60*8=480s
		System.out.println("总共的数据大小: " + tot);
		// 输出统计结果
		for(int i = 0 ; i < 200 ; i ++){
			System.out.println(i + "岁的统计数量为:" + data[i]);
		}
		//144239ms => 144s
		System.out.println("计算花费的时间为:" + (System.currentTimeMillis() - start) + "ms");
	}

	/**
	 * 写数据
	 * @throws IOException
	 */
	public static void writeData() throws IOException {
		final String fileName = "c:/age.txt";
		final Random random = new Random();
		BufferedWriter bufferedWriter;
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
		for (int i = 0; i < 1400000000; i++) {
			int age = Math.abs(random.nextInt()) % 180;
			bufferedWriter.write(age + "\r\n");
		}
		bufferedWriter.flush();
		bufferedWriter.close();

	}
}
