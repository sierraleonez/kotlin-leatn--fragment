package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment


class OptionDialogFragment : DialogFragment() {
    private lateinit var btnChoose : Button
    private lateinit var btnClose : Button
    private lateinit var rgOptions : RadioGroup
    private lateinit var rbSaf : RadioButton
    private lateinit var rbJm : RadioButton
    private lateinit var rbLvg : RadioButton
    private lateinit var rbMoyes : RadioButton
    private var optionDialogListener : OnOptionDialogListener? = null

    interface OnOptionDialogListener : onOptionDialogFragment {
        fun onOptionChoosen(text : String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbJm = view.findViewById(R.id.rb_jm)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMoyes = view.findViewById(R.id.rb_dm)

        btnChoose.setOnClickListener{
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if(checkedRadioButtonId != -1){
                var coach : String? = null
                when(checkedRadioButtonId){
                    R.id.rb_saf -> coach = rbSaf.text.toString().trim()
                    R.id.rb_dm -> coach = rbMoyes.text.toString().trim()
                    R.id.rb_lvg -> coach = rbLvg.text.toString().trim()
                    R.id.rb_jm -> coach = rbJm.text.toString().trim()
                }
                optionDialogListener?.onOptionChoosen(coach)
                dialog?.cancel()

            }
        }
        btnClose.setOnClickListener{
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment
        if(fragment is DetailCategoryFragment){
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }



}