package com.example.projectmiikajokinen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.projectmiikajokinen.databinding.FragmentMainViewBinding
import kotlinx.coroutines.launch

class mainView : Fragment() {
   private lateinit var binding: FragmentMainViewBinding
   private lateinit var viewModel: MainActivityVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainViewBinding.inflate(layoutInflater)
        viewModel = MainActivityVM()
        binding.buttonParties.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_parties)
            viewModel = ViewModelProvider(this).get(MainActivityVM::class.java)
        }
        return binding.root
    }
}
class MainActivityVM: ViewModel(){
    var members: MutableLiveData<List<MembersOfParliament>> = MutableLiveData()

    fun readMembers(){
        viewModelScope.launch{
            try {
                val dao = ParliamentDB.getInstance().membersOfParliamentDAO
                members.value = ParliamentApi.retrofitService.getParliamentList()
                println("Read members from parliament with great success.")
                members.value?.forEach {
                    dao.insert(it)
                }
                println("wirtten to database")
            } catch (e: Exception){
                println("no luck in reading members from parliament $e")
            }
        }
    }
}