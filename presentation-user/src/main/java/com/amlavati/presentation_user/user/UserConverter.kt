package com.amlavati.presentation_user.user

import android.content.Context
import com.amlavati.common.state.CommonResultConverter
import com.amlavati.domain.usecase.GetPostUseCase
import com.amlavati.domain.usecase.GetUserUseCase
import com.amlavati.presentation_post.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetUserUseCase.Response, UserModel>() {
    override fun convertSuccess(data: GetUserUseCase.Response): UserModel {
        return UserModel(
            context.getString(R.string.name, data.user.name),
            context.getString(R.string.username, data.user.userName),
            context.getString(R.string.email, data.user.email)

        )
    }

}