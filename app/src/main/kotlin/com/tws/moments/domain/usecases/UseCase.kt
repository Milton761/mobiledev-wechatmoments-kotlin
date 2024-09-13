package com.tws.moments.domain.usecases

interface UseCaseParams

interface UseCase<P: UseCaseParams, D: Any> {
    suspend fun execute(params: P): D?
}