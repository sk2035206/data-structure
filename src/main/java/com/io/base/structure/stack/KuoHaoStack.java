package com.io.base.structure.stack;

import java.util.Scanner;

/**
 * 括号匹配测试
 * @author sk
 * @date 2019-10-30
 */
public class KuoHaoStack {

	/**
	 * 判断数据是否匹配  时间复杂度 O(n)
	 * @param regularStr 表示的就是待匹配的括号串 [}使用字符来表示
	 * @return 匹配结果
	 */
	public static boolean isOk(String regularStr){
		// 创建栈
		MyStack<Character> brackets = new ArrayStack<>(20);
		//
		char[] c = regularStr.toCharArray();
		Character top;
		for(char x : c){
			switch (x) {
			case '{':
			case '(':
			case '[':
				// 匹配到开头括号入栈
				brackets.push(x);
				break;
			case '}':
				// 匹配到结尾括号入栈
				top = brackets.pop();
				if(top == null) {
					return false;
				}
				if(top == '{'){
					break;
				}else{
					return false;
				}
			case ')':
				top = brackets.pop();
				if(top == null) {
					return false;
				}
				if(top == '('){
					break;
				}else{
					return false;
				}
			case ']':
				top = brackets.pop();
				if(top == null) {
					return false;
				}
				if(top ==  '['){
					break;
				}else{
					return false;
				}
			default:
				break;
			}
		}
		return brackets.isEmpty();
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入需匹配的字符:");
		while(scanner.hasNext()){
			String s = scanner.next();
			if ("0".equals(s)) { return; }
			System.out.println("s的匹配结果:"+isOk(s));
			System.out.println("请输入需匹配的字符:");
		}
	}
	
}
