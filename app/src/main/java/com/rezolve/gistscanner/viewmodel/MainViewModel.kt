package com.rezolve.gistscanner.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rezolve.gistscanner.data.GistRepository
import com.rezolve.gistscanner.data.NetworkCallback
import com.rezolve.gistscanner.model.GistComment
import javax.inject.Inject

class MainViewModel @Inject constructor(val gistRepository: GistRepository) : ViewModel() {

    private var commentListMutableLiveData: MutableLiveData<List<GistComment>>? = null

    private var addCommentMutableLiveData: MutableLiveData<GistComment>? = null


    fun getCommentListResponse(gistID: String, username: String, password: String): LiveData<List<GistComment>>? {
        if (commentListMutableLiveData == null) {
            commentListMutableLiveData = MutableLiveData()
            gistRepository.fetchGistCommentList(gistID, username, password,
                    NetworkCallback<List<GistComment>>().apply {
                        success = {
                            commentListMutableLiveData?.postValue(it)
                        }
                        error = {
                            commentListMutableLiveData?.postValue(null)
                        }
                    })
        }

        return commentListMutableLiveData
    }

    fun getAddCommentMutableLiveData(gistID: String, username: String, password: String, comment: String)
            : LiveData<GistComment>? {
        if (addCommentMutableLiveData == null) {
            addCommentMutableLiveData = MutableLiveData()
            gistRepository.createdGistComment(gistID, username, password, comment,
                    NetworkCallback<GistComment>().apply {
                        success = {
                            addCommentMutableLiveData?.postValue(it)
                        }
                        error = {
                            addCommentMutableLiveData?.postValue(null)
                        }
                    })
        }


        return addCommentMutableLiveData
    }

    fun invalidateCommentList() = { addCommentMutableLiveData?.value = null }

}