package com.hamidreza.newsapp.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController

class ShowAlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("لطفا وضعیت اینترنت خود را چک کنید.در صورت امکان از VPN استفاده نمایید.")
            .setPositiveButton("Ok") { _, _ ->
                findNavController().popBackStack()
            }
            .create()

}