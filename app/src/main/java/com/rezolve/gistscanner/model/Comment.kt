package com.rezolve.gistscanner.model

import com.google.gson.annotations.SerializedName

data class CommentRequest(@SerializedName("body") val body: String)

data class User(val login: String)

data class GistComment(val body: String, @SerializedName("updated_at") val updatedAt: String, val user: User)