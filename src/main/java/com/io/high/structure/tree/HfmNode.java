package com.io.high.structure.tree;

/**
 * 赫夫曼树节点
 * 优先队列,小的我把你优先级调高
 *
 * @author sk
 * @email ma.keke@foxmail,com
 * @date 2019-11-04
 */
public class HfmNode implements Comparable<HfmNode>{

	/** 内容字符 */
	String chars;
	/** 频率 */
	int fre;
	/** 左节点 */
	HfmNode left;
	/** 右节点 */
	HfmNode right;
	/** 父级节点 */
	HfmNode parent;
	
	@Override
	public int compareTo(HfmNode o) {
		return this.fre - o.fre;
	}
	
}
