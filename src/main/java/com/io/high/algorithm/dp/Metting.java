package com.io.high.algorithm.dp;

/**
 * 会议类
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
class Metting implements Comparable<Metting> {

	/** 编号 */
	int meNum;
	/** 开始时间 */
	int startTime;
	/** 结束时间 */
	int endTime;

	public Metting(int meNum, int startTime, int endTime) {
		super();
		this.meNum = meNum;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Metting o) {
		if (this.endTime > o.endTime) {
			return 1;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "Metting [meNum=" + meNum + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}

}
