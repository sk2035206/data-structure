package com.io.high.algorithm.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 贪心算法
 *
 * 排会议时间
 * 1.某天早上公司领导找你解决一个问题，明天公司有N个同等级的会议需要使用同一个会议室，现在给你这个N个会议的开始和结束
 * 时间，你怎么样安排才能使会议室最大利用？即安排最多场次的会议？电影的话 那肯定是最多加票价最高的，入场率。综合算法
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class MettingTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<Metting> mettings = new ArrayList<>();
		// 会议个数
		int num = input.nextInt();
		for (int i = 0 ;i < num; i++){
			int start = input.nextInt();
			int end = input.nextInt();
			Metting metting = new Metting(i+1, start, end);
			mettings.add(metting);
		}
		mettings.sort(null);
		// 当前的时间,从一天的0点开始,如果领导要求从8点开始 那curTime=8
		int curTime = 0;
		for(int i = 0 ; i < num; i ++){
			Metting metting = mettings.get(i);
			// 会议的开始时间比我们当前的要大 表示可以开
			if(metting.startTime >= curTime){
				System.out.println(metting.toString());
				curTime = metting.endTime;
			}
		}
	}
}
