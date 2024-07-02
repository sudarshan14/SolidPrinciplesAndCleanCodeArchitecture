package com.amlavati.data_remote

import com.amlavati.data_remote.networking.user.UserApiModel
import com.amlavati.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() = runTest {

        val data = returnData()
        data.onEach {
            println("printing on each $it")
        }

    }

    private fun returnData(): Flow<List<User>> = flow {

        emit(apiService())

    }.map { users ->

        users.map {
            User(it.id, it.name, it.username, it.email)
        }
    }

    private fun apiService(): List<UserApiModel> {
        val first = UserApiModel(1, "name", "username", "email")
        val second = UserApiModel(2, "name", "username", "email")

        return listOf(first, second)

    }
}