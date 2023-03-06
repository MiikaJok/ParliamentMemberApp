package com.example.projectmiikajokinen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmiikajokinen.databinding.FragmentEveryMemberBinding
class EveryMember : Fragment() {
    /*
    Miika Jokinen
    2201315
    5.3.2023
    Tässä luokassa listataan jokainen tietokannan jäsen recyclerviewssä. Vaihtoehtona on myös klikata
    jäsenen nimeä, joka vie seuraavaan näkymään, joka näyttää enemmän infoa jäsenestä.
    */
    private lateinit var binding: FragmentEveryMemberBinding
    private lateinit var viewModel: EveryMemberActivityViewModel
    //tätä funktiota kutsutaan, kun fragmentti luodaan ja se palauttaa fragmentin näkymän
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEveryMemberBinding.inflate(layoutInflater) //Bindataan näkymään
        viewModel = EveryMemberActivityViewModel() //tässä toteutetaan viewmodel
        binding.recycleEveryMember.layoutManager = LinearLayoutManager(context)
        //observoidaan members LiveData ja päivitetään recyclerview adapteri, kun data muuttuu
        viewModel.members.observe(viewLifecycleOwner) {
            binding.recycleEveryMember.adapter = EveryMemberAdapter(it)
        }
        return binding.root
    }
}
/*
Miika Jokinen
2201315
5.3.2023
tämä luokka extendaa ViewModel luokan ja pitää dataa EveryMember fragmentille
* */
class EveryMemberActivityViewModel : ViewModel() {
    //Määritetään LiveData objekti, joka mappaa member datan MemberRepo objektista
    var members = Transformations.map(MemberRepo.logData) {
        //mapataan määritetyt objektit merkkijonoksi ja järjestetään listaan
        it.map { "${it.firstname} ${it.lastname} Party: ${it.party} Id:${it.hetekaId}" }.toSortedSet().toList()
    }
}
