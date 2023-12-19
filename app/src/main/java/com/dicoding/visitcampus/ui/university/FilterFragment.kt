package com.dicoding.visitcampus.ui.university

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private lateinit var univType: String
    private lateinit var scienceScope: String
    private lateinit var accreditation: String
    private lateinit var major: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_filter, container, false)
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = binding.spinner

//        val categories: MutableList<String> = ArrayList()
//        categories.add("Item 1")
//        categories.add("Item 2")
//        categories.add("Item 3")
//        categories.add("Item 4")
//        categories.add("Item 5")
//        categories.add("Item 6")

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter.createFromResource(requireActivity(), R.array.planets_array ,android.R.layout.simple_spinner_item)
        // val dataAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, categories)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = dataAdapter
        spinner.onItemSelectedListener = this



        binding.apply {
            btnSave.setOnClickListener{

                val btnType = rgUnivType.checkedRadioButtonId
                val btnScope = rgScopeScience.checkedRadioButtonId
                val btnAccreditation = rgAccreditation.checkedRadioButtonId

                if (btnType != 1 && btnScope != 1) {
                    univType = when(btnType) {
                        R.id.rb_allUniv -> rbAllUniv.text.toString().trim()
                        R.id.rb_publicUniv -> rbPublicUniv.text.toString().trim()
                        R.id.rb_privateUniv -> rbPrivateUniv.text.toString().trim()
                        else -> "tidak memilih"
                    }
                    scienceScope = when(btnScope) {
                        R.id.rb_science -> rbScience.text.toString().trim()
                        R.id.rb_law -> rbLaw.text.toString().trim()
                        else -> "tidak memilih"
                    }
//                    accreditation = when(btnType) {
//                    }
                }

                Toast.makeText(requireActivity(), "Perguruan Tinggi $univType, Lingkup Ilmu $scienceScope, Jurusan $major", Toast.LENGTH_LONG).show()
                dismiss()
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long
    ) {
        major = parent.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    companion object {
        const val TAG = "FilterFragment"
    }


}