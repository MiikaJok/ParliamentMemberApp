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
import com.example.projectmiikajokinen.databinding.FragmentEveryMemberBinding

class EveryMember : Fragment() {
        private lateinit var binding: FragmentEveryMemberBinding
        private lateinit var viewModel: EveryMemberActivityViewModel
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = FragmentEveryMemberBinding.inflate(layoutInflater)
            viewModel = EveryMemberActivityViewModel()
            binding.recycleEveryMember.setOnClickListener {
                findNavController().navigate(R.id.action_parties_to_partyMembers)
                viewModel = ViewModelProvider(this).get(EveryMemberActivityViewModel::class.java)
            }
            binding.recycleEveryMember.layoutManager = LinearLayoutManager(context)
            viewModel.members.observe(viewLifecycleOwner) {
                binding.recycleEveryMember.adapter = EveryMemberAdapter(it)
            }
            return binding.root
        }
    }
    class EveryMemberActivityViewModel : ViewModel() {
        var members =
            Transformations.map(ParliamentDB.getInstance().membersOfParliamentDAO.getAll()) {
                it.map {"${it.firstname} ${it.lastname} Party: ${it.party} "}.toSortedSet().toList()
            }
    }
