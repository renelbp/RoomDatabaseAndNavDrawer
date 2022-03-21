package com.example.roomimplementation.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponent.R
import com.example.roomimplementation.model.User
import com.example.roomimplementation.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updatefirstNameEt.setText(args.currentUser.firstName)
        view.updatelastNameEt.setText(args.currentUser.lastName)
        view.updateageEt.setText(args.currentUser.age.toString())

        view.updateBtn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return view
    }

    private fun updateItem() {
        val firstName = updatefirstNameEt.text.toString()
        val lastName = updatelastNameEt.text.toString()
        val age = Integer.parseInt(updateageEt.text.toString())

        if (inputCheck(firstName, lastName, updateageEt.text)) {
            //CREATE METHOD TO UPDATE USER
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            //UPDATE CURRENT USER
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Update Succesfully", Toast.LENGTH_SHORT).show()
            //NAVIGATE BACK
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),
                "Succesfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}? ")
        builder.setMessage("Are you sure you wan to delete ${args.currentUser.firstName}")
        builder.create().show()

    }


}