package com.dicoding.visitcampus.ui.university

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.data.Result
import com.dicoding.visitcampus.databinding.FragmentFilterBinding
import com.dicoding.visitcampus.ui.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private lateinit var univType: String
    private lateinit var scienceScope: String
    private lateinit var accreditation: String
    private lateinit var major: String

    private val viewModel by viewModels<UniversityViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMajors().observe(viewLifecycleOwner) {result ->
            when (result) {
                is Result.Loading -> {

                }
                is Result.Success -> {
                    val majorList = result.data
                    val majorNames = majorList.map { it.majorName }
                    setupFilter(majorNames)
                }
                is Result.Error -> {
                    Log.d("UniversityViewModel", result.error)

                }
                else -> {}
            }
        }
    }

    private fun setupFilter(majors: List<String>) {
        val uniqueSortedMajors = majors.distinct().sorted()

        val dataAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, uniqueSortedMajors)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = binding.spinner
        spinner.adapter = dataAdapter
        spinner.onItemSelectedListener = this

        binding.apply {
            btnSave.setOnClickListener{
                val btnType = rgUnivType.checkedRadioButtonId
                val btnScope = rgScopeScience.checkedRadioButtonId
                val btnAccreditation = rgAccreditation.checkedRadioButtonId

                if (btnType != 1 && btnScope != 1 && btnAccreditation != 1) {
                    univType = when(btnType) {
                        R.id.rb_allUniv -> "Semua"
                        R.id.rb_publicUniv -> "Negeri"
                        R.id.rb_privateUniv -> "Swasta"
                        else -> "tidak memilih"
                    }
                    scienceScope = when(btnScope) {
                        R.id.rb_science -> "SAINTEK"
                        R.id.rb_law -> "SOSHUM"
                        else -> "tidak memilih"
                    }
                    accreditation = when(btnAccreditation) {
                        R.id.rb_superior -> rbSuperior.text.toString().trim()
                        R.id.rb_good -> rbGood.text.toString().trim()
                        R.id.rb_bad -> rbBad.text.toString().trim()
                        else -> "tidak memilih"
                    }
                }


                val intent = Intent(context, FilterUniversityActivity::class.java)
                intent.putExtra(FilterUniversityActivity.EXTRA_UNIV_TYPE, univType)

                intent.putExtra(FilterUniversityActivity.EXTRA_SCIENCE_SCOPE, scienceScope)
                intent.putExtra(FilterUniversityActivity.EXTRA_MAJOR, major)
                intent.putExtra(FilterUniversityActivity.EXTRA_MAJOR_ACD, accreditation)
                startActivity(intent)

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