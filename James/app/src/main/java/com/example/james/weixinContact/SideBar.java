package com.example.james.weixinContact;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.james.R;


/**
 * 右侧A-Z,#字母控件
 * 使用:设置字母触摸及显示的TextView控件  setOnTouchingLetterChangedListener()及setTextView()
 */
public class SideBar extends View {
	// 触摸事件
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	// 26个字母
	public static String[] mLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" ,"#"};
	private int choose = -1;// 选中
	private Paint paint = new Paint();

	private TextView mTextDialog;
	/**
	 * 设置中间显示单个字母的TextView,必须设置
	 */
	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}


	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context) {
		super(context);
	}

	/**
	 * 重写这个方法
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 获取焦点改变背景颜色.
		int height = getHeight();// 获取对应高度
		int width = getWidth(); // 获取对应宽度
		int singleHeight = height / mLetters.length;// 获取每一个字母的高度

		for (int i = 0; i < mLetters.length; i++) {
			paint.setColor(Color.rgb(33, 65, 98));
			paint.setTypeface(Typeface.DEFAULT);//设置A-Z的字体
			paint.setAntiAlias(true);
			paint.setTextSize(30);//设置A-Z的尺寸大小
			// 选中的状态
			if (i == choose) {
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}
			// x坐标等于中间-字符串宽度的一半.
			float xPos = width / 2 - paint.measureText(mLetters[i]) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(mLetters[i], xPos, yPos, paint);
			paint.reset();// 重置画笔
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();// 点击y坐标
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int index = (int) (y / getHeight() * mLetters.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

		switch (action) {
		case MotionEvent.ACTION_UP:
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			choose = -1;
			invalidate();
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;
		default:
			setBackgroundResource(R.drawable.sidebar_background);
			if (oldChoose != index) {
				if (index >= 0 && index < mLetters.length) {
					if (listener != null) {
						listener.onTouchingLetterChanged(mLetters[index]);
					}
					if (mTextDialog != null) {
						mTextDialog.setText(mLetters[index]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					choose = index;
					invalidate();
				}
			}
			break;
		}
		return true;
	}

	/**
	 * 设置触摸时字母改变的监听,必须设置
	 * 
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 * 接口:改变字母时调用此接口的方法
	 */
	public interface OnTouchingLetterChangedListener {
		void onTouchingLetterChanged(String s);
	}
}