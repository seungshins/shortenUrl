package com.sample.kss.common.util;

import java.util.Random;

/**
 * 공통 util
 * @author seungshins
 *
 */
public class CommonUtil {
	private static String URL_FLAG = "YKGFU";
	private static int RANDOM_NUM = 5;

	/**
	 * Random한 Flag String을 가져오는 메서드
	 * @return Random Flag String
	 */
	public static String getRandomFlagString() {
		Random generator = new Random();
		int randomNum= generator.nextInt(RANDOM_NUM);
		String flagString = String.valueOf(URL_FLAG.charAt(randomNum));
		return flagString;
	}
	
	/**
	 * Random한 Flag Character를 가져오는 메서드
	 * @return Random Flag Character
	 */
	public static char getRandomFlagChar() {
		Random generator = new Random();
		int randomNum= generator.nextInt(RANDOM_NUM);
		return URL_FLAG.charAt(randomNum);
	}
}
