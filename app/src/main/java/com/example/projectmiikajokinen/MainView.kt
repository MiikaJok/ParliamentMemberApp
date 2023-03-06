package com.example.projectmiikajokinen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.projectmiikajokinen.databinding.FragmentMainViewBinding
import kotlinx.coroutines.launch
class MainView : Fragment() {
    /*
    * Miika Jokinen
    * 2201315
    * 3.3.2023
    * Tässä luokassa näytetään aloitusnäkymä, kun sovellus aukeaa,
    * lisätään buttonit ja toiminnot, kutsutaan apia ja
    * observoidaan LiveDataa ViewModelissa */
   private lateinit var binding: FragmentMainViewBinding
   private lateinit var viewModel: MainActivityVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainViewBinding.inflate(layoutInflater)
        viewModel = MainActivityVM()
        //nappi puolueisiin ja navigoidaan puoluenäkymään, jossa kaikki puolueet listattuna
        binding.buttonParties.setOnClickListener{
            findNavController().navigate(R.id.action_mainView_to_parties)
            viewModel = ViewModelProvider(this).get(MainActivityVM::class.java)
        }
        //nappi kaikkiin jäseniin ja navigoidaan recyclerviewiin, jossa kaikki 200jäsentä
        binding.buttonAllMembers.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_everyMember)
            viewModel = ViewModelProvider(this).get(MainActivityVM::class.java)
        }
        return binding.root
    }
    //tässä funktiossa luodaan näkymä, kun kaikki tarvittava hierarkia on luotu ja näkymän bindattu
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //kutsutaan readMembers metodia viewmodelista, jolla luetaan jäsenet apista
        viewModel.readMembers()
        //tarkastellaan jäsenten LiveDataa viewmodelissa
        viewModel.member.observe(viewLifecycleOwner) {
            Log.d("QWERTY", "SUCCESS")
        }
    }
}
/*
    * Miika Jokinen
    * 2201315
    * 3.3.2023
    * Tässä luokassa määritetään mainactivitylle viewmodel  ja
    * määritetään MutableLiveData lista jäsenistä
    *  */
class MainActivityVM: ViewModel() {
    var member: MutableLiveData<List<MembersOfParliament>> = MutableLiveData()
    //Tämä funktio lukee jäsendataa apista ja asettaa sen databaseen
    fun readMembers(){
        Log.d("test", "readMembers called")
        viewModelScope.launch {
            try {
                val dao = ParliamentDB.getInstance().membersOfParliamentDAO
                Log.d("test", "in try")
                member.value = ParliamentApi.retrofitService.getParliamentList()
                println("Read members from parliament with great success.")
                member.value?.forEach{
                    dao.insert(it)
                }
                println("Written to database")
            } catch (e: Exception){
                Log.d("test", "in catch")
                println("No luck in reading members from parliament: $e")
            }
        }

    }
}