package com.lingxiao.kotlin.study.base

/**
 * @author luckw
 * @date   2020/9/3
 */


//out 相当于java里面的 <? extend>
// 子类泛型对象可以赋值给父类泛型对象，用 out。
//in 相当于java里面的 <? super>
// 父类泛型对象可以赋值给子类泛型对象，用 in；

interface IPresenter<in V : IBaseView> {
    fun attach(rootView: IBaseView)
    fun detachView()
}