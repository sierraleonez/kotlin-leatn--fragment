package com.example.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetailCategoryFragment : Fragment(), View.OnClickListener {

    var description : String? = null

    companion object{
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    lateinit var tvCategoryName : TextView
    lateinit var tvCategoryDescription : TextView
    lateinit var btnProfile : Button
    lateinit var btnShowDialog : Button
    lateinit var btnTestShowDialog : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCategoryName = view.findViewById(R.id.tv_category_name)
        tvCategoryDescription = view.findViewById(R.id.tv_category_description)
        btnProfile = view.findViewById(R.id.btn_to_profile)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)
        btnTestShowDialog = view.findViewById(R.id.btn_test_show_dialog)

        btnProfile.setOnClickListener(this)
        btnShowDialog.setOnClickListener(this)
        btnTestShowDialog.setOnClickListener(this)
        btnProfile.setOnClickListener(this)

        if(savedInstanceState != null){
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }

        if(arguments!=null){
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = categoryName
            tvCategoryDescription.text = description
        }
        }
    internal var optionDialogListener : OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener{
        override fun onOptionChoosen(text: String?) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_show_dialog -> {
                val mOptionDialogFragment = OptionDialogFragment()
                val mFragmentManager = childFragmentManager
                mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
            }
            R.id.btn_test_show_dialog -> {
                val mOptionDialogFragment = testFragment()
                val mFragmentManager = childFragmentManager
                mOptionDialogFragment.show(mFragmentManager, testFragment::class.java.simpleName)
            }
            R.id.btn_to_profile -> {
                val mIntent = Intent(activity, ProfileActivity::class.java)
                startActivity(mIntent)
            }
        }
    }


}