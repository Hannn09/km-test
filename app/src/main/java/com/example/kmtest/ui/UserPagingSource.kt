package com.example.kmtest.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kmtest.service.api.ApiService
import com.example.kmtest.service.response.DataItem

class UserPagingSource(private val apiService: ApiService): PagingSource<Int, DataItem>() {

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getUser(position, params.loadSize)
            Log.d("UserPagingSource", "load: ${response.body()}")
            LoadResult.Page(
                data = response.body()?.data ?: emptyList(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (response.body()?.data.isNullOrEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            Log.d("UserPagingSource", "load: $e")
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}