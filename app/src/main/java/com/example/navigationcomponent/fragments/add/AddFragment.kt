package com.example.roomimplementation.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponent.R
import com.example.roomimplementation.model.User
import com.example.roomimplementation.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var  mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.addBtn.setOnClickListener{
            insertDataToDatabase()
        }




        return view
    }

    private fun insertDataToDatabase() {
        val firstName = firstNameEt.text.toString()
        val lastName = lastNameEt.text.toString()
        val age = ageEt.text

        if (inputCheck(firstName,lastName, age)){
            val newUser = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.addUser(newUser)
            Toast.makeText(requireContext(), "Succesfully Added.",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(), "Please fill out All fields.",Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck (firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||  age.isEmpty())
    }
}