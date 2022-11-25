package com.tessssssssy.oop_todo_list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Todo (
    var todo: String = "",
    var priority: String = "0",
    var completion: String = "0"
) : Parcelable

