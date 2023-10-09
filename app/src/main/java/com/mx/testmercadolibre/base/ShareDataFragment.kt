package com.mx.testmercadolibre.base

import android.os.Parcel
import android.os.Parcelable

data class ShareDataFragment(val id: String?,val product: String? ) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(product)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShareDataFragment> {
        override fun createFromParcel(parcel: Parcel): ShareDataFragment {
            return ShareDataFragment(parcel)
        }

        override fun newArray(size: Int): Array<ShareDataFragment?> {
            return arrayOfNulls(size)
        }
    }

}
