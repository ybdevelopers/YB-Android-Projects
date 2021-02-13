package com.yb.todolist.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.renderscript.RenderScript
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yb.todolist.R
import com.yb.todolist.data.models.Priority
import com.yb.todolist.data.models.ToDoData
import com.yb.todolist.data.viewmodel.ToDoViewModel
import com.yb.todolist.databinding.FragmentUpdateBinding
import com.yb.todolist.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mTodoViewModel: ToDoViewModel by viewModels()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Data Binding
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        binding.args = args

        //Set Menu
        setHasOptionsMenu(true)

        binding.currentPrioritiesSpinner.current_priorities_spinner.onItemSelectedListener = mSharedViewModel.listener
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemove()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title = current_title_edt.text.toString()
        val description = current_description_edt.text.toString()
        val getPriority = current_priorities_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)

        if (validation) {
            // Update Current Item
            val updatedItem = ToDoData(
                args.currentItem.id, title, mSharedViewModel.parsePriority(getPriority), description
            )
            mTodoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "Successfully Updated..!!", Toast.LENGTH_SHORT).show()

            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please Fill out all Fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun confirmItemRemove() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES") { _, _ ->
            mTodoViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: ${args.currentItem.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("NO") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.title}'?")
        builder.setMessage("Are You Sure, You Want to Remove '${args.currentItem.title}'?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}