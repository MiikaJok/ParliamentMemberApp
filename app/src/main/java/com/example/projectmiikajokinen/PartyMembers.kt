package com.example.projectmiikajokinen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmiikajokinen.databinding.FragmentPartyMembersBinding
class PartyMembers : Fragment() {
    /*
    Miika Jokinen
    2201315
    6.3.2023
    Tässä luokassa näytetään lista tietyn puolueen jäsenistä
    recyclerviewissä, näytetään siihen kuuluva layoutti,
     observoidaan puolueiden jäseniä ja päivitetään
     adapteria, kun tapahtuu muutoksia. */
    private lateinit var binding: FragmentPartyMembersBinding
    private lateinit var viewModel: MemberActivityViewModel
    val args: PartyMembersArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPartyMembersBinding.inflate(layoutInflater)
        viewModel = MemberActivityViewModel(savedStateHandle = args.toSavedStateHandle())
        binding.recyclerMember.layoutManager = LinearLayoutManager(context)
        viewModel.PartyMembers.observe(viewLifecycleOwner){
            binding.recyclerMember.adapter = MembersAdapter(it)
        }
        return binding.root
    }
}
/*
    Miika Jokinen
    2201315
    6.3.2023
    Tässä luokassa haetaan puolueen nimi saved state handlesta,
     ja lista puolueen jäsenistä reposta. Mapataan lista jäsenistä,jotka
     kuuluvat puolueeseen. */
class MemberActivityViewModel(savedStateHandle: SavedStateHandle): ViewModel(){
    //palautetaan puoluenimi saved state handlesta
    val party: String? = savedStateHandle["party"]
    //palautetaan puolueen jäsenet reposta ja mapataan järjestetyksi listaksi
    var PartyMembers = Transformations.map(MemberRepo.getPartyMembers(party.toString())){
        it.map {"${it.firstname} ${it.lastname} Seat Number: ${it.seatNumber} Id:${it.hetekaId}"}.toSortedSet().toList()
    }
}