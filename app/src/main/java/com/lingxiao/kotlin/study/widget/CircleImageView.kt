package com.lingxiao.kotlin.study.widget

import android.content.Context
import android.graphics.*
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.ImageView
import com.lingxiao.kotlin.study.R

/**
 * @author luckw
 * @date   2020/9/6
 */
class CircleImageView constructor(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {
    companion object {
        /**
         * android.widget.ImageView
         */
        val TYPE_NONE = 0

        /**
         * 圆形
         */
        val TYPE_CIRCLE = 1

        /**
         * 圆角矩形
         */
        val TYPE_ROUNDED_RECT = 2

        private val DEFAULT_TYPE = TYPE_NONE
        private val DEFAULT_BORDER_COLOR = Color.TRANSPARENT
        private val DEFAULT_BORDER_WIDTH = 0
        private val DEFAULT_RECT_ROUND_RADIUS = 0
    }

    private val mType: Int
    private val mBorderColor: Int
    private val mBorderWidth: Int
    private val mRectRoundRadius: Int

    private val mPaintBitmap = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaintBorder = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mRectBorder = RectF()
    private val mRectBitmap = RectF()

    private var mRawBitmap: Bitmap? = null
    private var mShader: BitmapShader? = null
    private val mMatrix = Matrix()

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
        mType = typeArray.getInt(R.styleable.CircleImageView_type, DEFAULT_TYPE)
        mBorderColor = typeArray.getInt(R.styleable.CircleImageView_borderColor, DEFAULT_BORDER_COLOR)
        mBorderWidth = typeArray.getDimensionPixelSize(R.styleable.CircleImageView_borderWidth, DEFAULT_BORDER_WIDTH)
        mRectRoundRadius = typeArray.getDimensionPixelSize(R.styleable.CircleImageView_rectRoundRadius, DEFAULT_RECT_ROUND_RADIUS)
        typeArray.recycle()
    }

}