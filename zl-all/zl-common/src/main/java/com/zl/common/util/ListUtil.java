package com.zl.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	public static List<Integer> getIntegerList(int... a) {
		List<Integer> list = new ArrayList<Integer>();
		if (a == null || a.length <= 0) {
			return list;
		}

		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
		return list;
	}
}
