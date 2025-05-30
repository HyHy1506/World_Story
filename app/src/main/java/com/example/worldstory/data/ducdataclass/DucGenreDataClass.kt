package com.example.worldstory.data.ducdataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DucGenreDataClass(var idGenre: Int, var title: String): Parcelable
