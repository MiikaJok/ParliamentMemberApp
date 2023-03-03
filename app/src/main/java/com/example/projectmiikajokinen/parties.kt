package com.example.projectmiikajokinen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmiikajokinen.databinding.FragmentPartiesBinding

class parties : Fragment() {
    private lateinit var binding: FragmentPartiesBinding
    private lateinit var viewModel: PartyActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPartiesBinding.inflate(layoutInflater)
        viewModel = PartyActivityViewModel()
        binding.recyclerParty.setOnClickListener {
            findNavController().navigate(R.id.action_parties_to_partyMembers)
            viewModel = ViewModelProvider(this).get(PartyActivityViewModel::class.java)
        }
           binding.recyclerParty.layoutManager = LinearLayoutManager(context)
           viewModel.parties.observe(viewLifecycleOwner){
               binding.recyclerParty.adapter = PartiesAdapter(it)
           }
        return binding.root
    }
}
class PartyActivityViewModel: ViewModel(){
    var parties = Transformations.map(ParliamentDB.getInstance().membersOfParliamentDAO.getAll()){
        it.map {it.party}.toSortedSet().toList()
    }
}

