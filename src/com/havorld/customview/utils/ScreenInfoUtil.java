package com.havorld.customview.utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class ScreenInfoUtil {
	/**
	 * 获取屏幕的宽和高
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int[] getScreenMeasure(Context context) {
		// 1、得到设备屏幕的分辨率的宽和高：
		// 得到系统提供的屏幕管理服务对象
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		// 得到默认分辨率对象
		Display display = wm.getDefaultDisplay();
		// 得到屏幕的宽和高
		int screeWidth = display.getWidth();
		int screeHeigth = display.getHeight();

		return new int[] { screeWidth, screeHeigth };
	}

//	/**
//	 * 获取屏幕的属性
//	 * 
//	 * @param activity
//	 * @return
//	 */
//	public static int[] getScreenProperties(Activity activity) {
//
//		DisplayMetrics dm = new DisplayMetrics();
//		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//		int screenWidth = dm.widthPixels;
//		int screenHeigh = dm.heightPixels;
//
//		return new int[] { screenWidth, screenHeigh };
//	}
}
