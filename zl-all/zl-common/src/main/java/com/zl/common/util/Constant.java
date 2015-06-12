package com.zl.common.util;

public interface Constant {
	public static interface STATUS {
		public static int CHECKED = 1;// 审核通过
		public static int NOMARL = 0;// 初始化
		public static int NO_CHECK = -1;// 审核不通过
		public static int DELETE = -4;// 删除
	}

	public static interface ENV {
		public static String TEST = "test";
		public static String YUFA = "yufa";
		public static String ONLINE = "online";
	}
}
