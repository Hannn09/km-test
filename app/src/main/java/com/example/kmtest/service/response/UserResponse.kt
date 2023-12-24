package com.example.kmtest.service.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
	@SerializedName("per_page")
	val perPage: Int,

	@SerializedName("total")
	val total: Int,

	@SerializedName("data")
	val data: List<DataItem>,

	@SerializedName("page")
	val page: Int,

	@SerializedName("total_page")
	val totalPages: Int,
)

data class DataItem(
	@SerializedName("last_name")
	val lastName: String,

	@SerializedName("id")
	val id: Int,

	@SerializedName("avatar")
	val avatar: String,

	@SerializedName("first_name")
	val firstName: String,

	@SerializedName("email")
	val email: String
)


