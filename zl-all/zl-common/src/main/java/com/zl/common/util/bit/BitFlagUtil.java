package com.zl.common.util.bit;

/**
 * 标位工具
 * @author is_zhoufeng@163.com
 * 2015年1月1日 上午3:41:45
 */
public class BitFlagUtil {
	
	public static final int FULL_FLAG = 0XFFFFFFFF ;
	
	/**
	 * 打标
	 * @param falg 需要打标的源数
	 * @param offset 标位
	 */
	public static int sign(int flag , int offset){
		int offsetT = 1 << ( offset - 1);
		return flag | offsetT ;
	}
	
	/**
	 * 去标
	 * @param falg 需要移除标的源数
	 * @param offset 标位
	 */
	public static int removeSign(int flag , int offset){
		int offsetT = 1 << ( offset - 1);
		offsetT = FULL_FLAG ^ offsetT ;
		return flag & offsetT ;
	}
	
	/**
	 * 检查标位是否被打标
	 * @param flag	
	 * @param offset  标位
	 * @return 该标位是否被打标
	 */
	public static boolean checkSign(int flag , int offset){
		int offsetT = 1 << ( offset - 1);
		return (flag & offsetT) == offsetT ;
	}

}
