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
import com.example.projectmiikajokinen.databinding.FragmentPartyMembersBinding

class PartyMembers : Fragment() {
    private lateinit var binding: FragmentPartyMembersBinding
    private lateinit var viewModel: MemberActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPartyMembersBinding.inflate(layoutInflater)
        viewModel = MemberActivityViewModel()
        binding.recyclerMember.setOnClickListener {
            findNavController().navigate(R.id.action_parties_to_partyMembers)
            viewModel = ViewModelProvider(this).get(MemberActivityViewModel::class.java)
        }
        binding.recyclerMember.layoutManager = LinearLayoutManager(context)
        viewModel.PartyMembers.observe(viewLifecycleOwner){
            binding.recyclerMember.adapter = MembersAdapter(it)
        }
        return binding.root
    }
}
class MemberActivityViewModel: ViewModel(){
    var PartyMembers = Transformations.map(ParliamentDB.getInstance().membersOfParliamentDAO.getAll()){
        it.map {"${it.firstname} ${it.lastname}"}.toSortedSet().toList()
    }
}