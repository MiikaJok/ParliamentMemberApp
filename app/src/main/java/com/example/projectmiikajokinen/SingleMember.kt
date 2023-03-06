package com.example.projectmiikajokinen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.projectmiikajokinen.databinding.FragmentSingleMemberBinding
class SingleMember : Fragment() {
    /*
    Miika Jokinen
    2201315
    6.3.2023
    Tässä fragment luokassa näytetään tietoja yksittäisestä jäsenestä
    ja observoidaan LiveDataa */
    private lateinit var binding: FragmentSingleMemberBinding
    private lateinit var viewModel: SingleMemberViewModel
    val args: SingleMemberArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rightId = args.member.split(":").last()
        Log.d("QQ", rightId)
        Log.d("QQ", args.toString())
        viewModel = SingleMemberViewModel(rightId.toInt())
        Log.d("QWERTY", viewModel.toString())
        binding = FragmentSingleMemberBinding.inflate(layoutInflater)
        viewModel.members.observe(viewLifecycleOwner){
            binding.memberText.text = it.toString().removePrefix("[").removeSuffix("]")
        }
        return binding.root
    }
}
/*
    Miika Jokinen
    2201315
    6.3.2023
    Tässä luokassa mapataan LiveDataa repon getSingleMember() funktiosta
    merkkijonoksi. ylempi Fragment myös observoi members muuttujaa. Kun se
    päivittyy, niin myös UI päivittyy.*/
class SingleMemberViewModel(val id: Int?): ViewModel(){
    //Livedatasta mapattu getSingleMember() repofunktion tietyt parametrit näkyville
    val members = Transformations.map(MemberRepo.getSingleMember(id)){
        it.map { "ID:${it.hetekaId} \nName: ${it.firstname} ${it.lastname} \nSeat Number: ${it.seatNumber} \nParty: ${it.party}"}
    }
}


