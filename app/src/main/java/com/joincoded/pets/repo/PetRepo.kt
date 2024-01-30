package com.joincoded.pets.repo

import com.joincoded.pets.interfaceApi.PetApiService

class PetRepo (private val api: PetApiService){
        //the fun in the the BookApiService should match the fun here because we are putting
        //fun = fun that why we have created a suspend fun here too
        suspend fun getAllpets() = api.getALlpets()

    }
