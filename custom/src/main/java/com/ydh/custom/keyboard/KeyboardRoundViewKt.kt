package com.ydh.custom.keyboard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ydh.custom.R

/**
 * 自定义键盘控件
 */
class KeyboardRoundViewKt : LinearLayout {
    private var mVerticalLaout: LinearLayout? = null

    internal var str = arrayOf(arrayOf("1", "2", "3"), arrayOf("4", "5", "6"), arrayOf("7", "8", "9"), arrayOf("清除", "0", "确认"))
    internal var size: Float = 0.toFloat()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)

    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(context, attributes, defStyleAttr)


    /**
     * 界面初始化
     * @param keyboardListener 按键监听
     */
    fun initView(keyboardListener: KeyboardListener) {

        this.keyboardListener = keyboardListener

        post { initView() }

    }

    private fun initView() {

        size = (height / 4 * 0.15).toFloat()

        removeAllViews()

        val linearParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        mVerticalLaout = LinearLayout(context)
        mVerticalLaout?.setOrientation(LinearLayout.VERTICAL)
        mVerticalLaout?.setLayoutParams(linearParams)

        val linearParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f)

        val linearParamView = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT )

        for (i in str.indices) {
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.HORIZONTAL
            linearLayout.layoutParams = linearParam
            linearLayout.setPadding(0, (width * 0.07).toInt(), 0, (width * 0.07).toInt())

            for (name in str[i]) {
                val textView = TextView(context)
                textView.width = (width * 0.2).toInt()
                textView.height = 0
                linearLayout.addView(textView, linearParamView)

                linearLayout.addView(getTextView(name), linearParam)
            }
            mVerticalLaout?.addView(linearLayout)
        }

        addView(mVerticalLaout)
    }

    /**
     * 控件按钮
     *
     * @return 按钮的textView
     */
    private fun getTextView(name: String): TextView {
        val textView = LayoutInflater.from(context).inflate(R.layout.view_keyboard_round, null) as TextView
        textView.text = name
        textView.width = textView.height
        textView.setTextSize(size)
        if (name.length == 2) {
            textView.setBackgroundColor(context.resources.getColor(R.color.keyboard_0000))
        }
        textView.setOnClickListener { view ->
            when (name) {
                "清除" -> keyboardListener!!.cancel()
                "确认" -> keyboardListener!!.launch()
                else -> keyboardListener!!.append(name)
            }
        }
        return textView
    }


    internal var keyboardListener: KeyboardListener? = null

    /**
     * 按键监听
     */
    interface KeyboardListener {

        /**
         * 按键返回
         * @param string 添加内容
         */
        fun append(string: String)

        /**
         * 具体操作
         */
        fun launch()

        /**
         * 清除操作
         */
        fun cancel()

    }

}