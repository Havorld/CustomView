package com.havorld.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.havorld.customview.R;
import com.havorld.customview.utils.ScreenInfoUtil;
 
/**
 * 
 * 博客地址: http://blog.csdn.net/xiaohao0724/article/details/53994009
 */
public class CustomView extends View {

	private Paint paint;
	private int radius;
	private int width;
	private int height;
	private int cicleColor = Color.RED;
	private float strokeWidth = 3;
	private int sleepTime = 150;

	/**
	 * 代码中实例化控件new时候使用
	 * 
	 * @param context
	 */
	public CustomView(Context context) {
		this(context, null);
	}

	/**
	 * 从XML加载到代码中时使用
	 * 
	 * @param context
	 * @param attrs
	 */
	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 获得自定义的样式属性
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * 
	 */

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);


		/*
		 * 获取我们的自定义属性
		 * 
		 * AttributeSet set: 属性值的集合
		 * 
		 * int[] attrs:
		 * 我们要获取的属性的资源ID的一个数组，如同ContextProvider中请求数据库时的Projection数组，
		 * 就是从一堆属性中我们希望查询什么属性的值
		 * 
		 * int defStyleAttr: 这个是当前Theme中的一个attribute，是指向style的一个引用，当在layout
		 * xml中和style中都没有为View指定属性时
		 * ，会从Theme中这个attribute指向的Style中查找相应的属性值，这就是defStyle
		 * 的意思，如果没有指定属性值，就用这个值，所以是默认值，但这个attribute要在Theme中指定，且是指向一个Style
		 * 的引用，如果这个参数传入0表示不向Theme中搜索默认值
		 * 
		 * int defStyleRes:这个也是指向一个Style的资源ID，
		 * 但是仅在defStyleAttr为0或defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用
		 */
		TypedArray obtainStyledAttributes = context.getTheme()
				.obtainStyledAttributes(attrs, R.styleable.CicleCustom,
						defStyleAttr, 0);

		// 如果XML中不使用自定义属性，则obtainStyledAttributes.getIndexCount()为零
		for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
			int attr = obtainStyledAttributes.getIndex(i);
			switch (attr) {
			case R.styleable.CicleCustom_cicleColor:
				cicleColor = obtainStyledAttributes.getColor(attr, Color.RED);
				break;
			case R.styleable.CicleCustom_strokeWidth:
				strokeWidth = obtainStyledAttributes.getDimension(attr, 3);
				break;
			case R.styleable.CicleCustom_sleepTime:
				sleepTime = obtainStyledAttributes.getInt(attr, 100);
				break;
			default:
				break;
			}
		}

		/**
		 * 回收TypedArray，以便后面重用。在调用这个函数后，你就不能再使用这个TypedArray。
		 * 在TypedArray后调用recycle主要是为了缓存。当recycle被调用后，这就说明这个对象从现在可以被重用了。
		 */
		obtainStyledAttributes.recycle();

		getScreenInfo(context);
		initPaint();
		redrawView();

	}

	/**
	 * 重绘View圆环
	 */
	private void redrawView() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (radius <= 200) {

						radius += 10;

					} else {
						radius = 0;
					}
					// Android中提供了两个的方法来重绘更新View
					// invalidate(); // 在被调用的线程更新View(如果直接在子线程调用将报错)
					postInvalidate(); // 在UI线程更新View

					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	/**
	 * 获取屏幕的宽高
	 * 
	 * @param context
	 */
	private void getScreenInfo(Context context) {

		int[] screenMeasures = ScreenInfoUtil.getScreenMeasure(context);
		width = screenMeasures[0] / 2;
		height = screenMeasures[1] / 2;
	}

	/**
	 * 初始化画笔
	 */
	private void initPaint() {

		paint = new Paint();
		paint.setColor(cicleColor);
		paint.setStyle(Style.STROKE);// STROKE为空心,FILL为实心
		paint.setStrokeWidth(strokeWidth);// 空心圆外边框的宽度
		paint.setAntiAlias(true); // 设置抗锯齿,一种算法让圆边缘清晰光滑
	}

	/**
	 * 在画布上绘制View
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 画圆
		canvas.drawCircle(width, height, radius, paint);
	}

}
