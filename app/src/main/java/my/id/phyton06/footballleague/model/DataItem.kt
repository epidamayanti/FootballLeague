package my.id.phyton06.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem (
    val id: Int?,
    val name: String?,
    val image: Int?,
    val desc: String?
): Parcelable