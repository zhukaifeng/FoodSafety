package com.osiris.food.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Arrays;

public class CustomTextView extends TextView {

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomTextView(Context context) {
		super(context);
	}



	@Override
	protected void onDraw(Canvas canvas) {
		TextPaint mPaint = getPaint();
		mPaint.setColor(getCurrentTextColor());  //这里获取字体颜色
		Paint.FontMetrics fm = mPaint.getFontMetrics();

		float baseline = fm.descent - fm.ascent;
		float x = 0;
		float y = baseline;  //由于系统基于字体的底部来绘制文本，所有需要加上字体的高度。

		String txt = getText().toString();

		//文本自动换行
		String[] texts = autoSplit(txt, mPaint, getWidth() - 10);

		System.out.printf("line indexs: %s\n", Arrays.toString(texts));

		for (String text : texts) {
			canvas.drawText(text, x, y, mPaint);  //坐标以控件左上角为原点
			y += baseline + fm.leading; //添加字体行间距
		}
	}

	private String[] autoSplit(String content, Paint p, float width) {
		int length = content.length();
		float textWidth = p.measureText(content);
		if (textWidth <= width) {
			return new String[]{content};
		}

		int start = 0, end = 1, i = 0;
		int lines = (int) Math.ceil(textWidth / width); //计算行数
		String[] lineTexts = new String[lines];
		while (start < length) {
			if (p.measureText(content, start, end) > width) { //文本宽度超出控件宽度时
				lineTexts[i++] = (String) content.subSequence(start, end);
				start = end;
			}
			if (end == length) { //不足一行的文本
				lineTexts[i] = (String) content.subSequence(start, end);
				break;
			}
			end += 1;
		}
		return lineTexts;
	}
}
