package com.example.projectmiikajokinen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmiikajokinen.databinding.FragmentPartiesBinding
class Parties : Fragment() {
    /*
    Miika Jokinen
    2201315
    5.6.2023
    Puoluefragmentti, joka näyttää listan puolueista,
    muutetaan dataa reposta listanäkymään ja tarkastellaan
    sitä recyclerviewiä varten*/
    private lateinit var binding: FragmentPartiesBinding
    private lateinit var viewModel: PartyActivityViewModel
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = FragmentPartiesBinding.inflate(layoutInflater)
            viewModel = PartyActivityViewModel()
            //määritetään layoutmanager recycler viewistä
            binding.recyclerParty.layoutManager = LinearLayoutManager(context)
            //observoidaan puolue LiveDataa ja määritetään adapteri,kun data muuttuu
            viewModel.parties.observe(viewLifecycleOwner) {
                binding.recyclerParty.adapter = PartiesAdapter(it)
            }
            return binding.root
        }
    }
/*
    Miika Jokinen
    2201315
    5.6.2023
    Tässä luokassa haetaan jäsenien LiveData reposta
    ja järjestellään se listaksi puoluenimiä*/
class PartyActivityViewModel: ViewModel() {
    var parties = Transformations.map(MemberRepo.logData){
        it.map {it.party }.toSortedSet().toList()
    }
}


