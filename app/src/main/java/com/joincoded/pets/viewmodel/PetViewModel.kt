package com.joincoded.pets.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joincoded.pets.interfaceApi.PetApiService
import com.joincoded.pets.model.Pet
import com.joincoded.pets.repo.PetRepo
import com.joincoded.pets.singleton.RetroFitHelper
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {

    private val petApiService = RetroFitHelper.getInstance().create(PetApiService::class.java)
    private val repository = PetRepo(petApiService)

    var pets by mutableStateOf(listOf<Pet>())
    var errorMsg: String? by mutableStateOf(null)
    var successMsg: String? by mutableStateOf(null)





    //connector
    init {
        fetchData()
    }
    fun fetchData() {
        viewModelScope.launch {
            try {
                successMsg = "Successful"
                pets = repository.getAllpets()

            } catch (e: Exception) {
                print("error"+ e )
                errorMsg = e.toString()

            }
        }
    }

    fun addPet(pet: Pet){
        viewModelScope.launch {
            try{

                var response = petApiService.addPet(pet)

                if(response.isSuccessful && response.body() != null){
                  //print("Successful")
                    successMsg = "Successful"

                }else{
                    errorMsg = "Error check your connection"
                    //print("Error")
                }


        } catch(e:Exception){
            //print("There is a Network Issue"+ e )

            }        }
    }
    fun deletepet(petID:Int){
        viewModelScope.launch {
            try {
                var response = petApiService.deletePet(petID)

            if(response.isSuccessful){
                // pet has been deleted
            } else{
                //
            }
            } catch (e:Exception){

//
            }
        }
    }


}



