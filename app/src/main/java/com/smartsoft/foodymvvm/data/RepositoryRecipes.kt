package com.smartsoft.foodymvvm.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
@ActivityRetainedScoped
class RepositoryRecipes @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSourse: LocalDataSourse
){
    val remote = remoteDataSource
    val local = localDataSourse
}