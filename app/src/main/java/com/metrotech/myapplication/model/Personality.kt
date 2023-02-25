package com.metrotech.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Personality(
    var title : String,
    var img : String,
    var desc : String,
    var hint : String,
    var link : String,
    var type : String,
    var banner : String,
    var id : Int
) : Parcelable