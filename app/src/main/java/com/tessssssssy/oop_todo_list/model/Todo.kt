package com.tessssssssy.oop_todo_list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Todo (
    var todo: String = "",
    var priority: String = "0",
    var completion: String = "0"
) : Parcelable


// 먼저 Parcel은 '소포', '(선물 등의)꾸러미'라는 뜻을 가지고 있다. Android에서는 Parcel은 "여러 데이터가 하나의 클래스 안에 담겨있다" 라는 의미를 가지게 된다.
// 그러므로 Parcelable은 Parcel(데이터 꾸러미)를 A activity 에서 B activity로 한번에 전달될 수 있도록 해주는 역할을 한다.

