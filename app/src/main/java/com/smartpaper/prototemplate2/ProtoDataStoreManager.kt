package com.smartpaper.prototemplate2

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ProtoDataStoreManager{

    suspend fun saveUser(context: Context, value: TemplateSetting){
        context.templateStore.updateData{
            it.toBuilder().setToken(value.token).build();
            it.toBuilder().setStatus(value.status).build();
            it.toBuilder().setUserDetail(value.userDetail).build()
        }
    }

    /**
     * <code>syntax getUserInfo().collectAsState(initial = null);</code>
     * @return Type flow.
     */
    fun getUserInfo(context: Context): Flow<TemplateSetting> {
        val userInfo = flow {
            context.templateStore.data.map{
                it
            }.collect(
                collector = {
                    this.emit(it)
                })
        }
        return userInfo
    }
}