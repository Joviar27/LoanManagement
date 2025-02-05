package com.example.loanmanagement.ui.bottomsheet

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.core.BuildConfig
import com.example.core.domain.Document
import com.example.loanmanagement.R
import com.example.loanmanagement.databinding.BottomsheetDocumentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DocumentBottomSheetFragment(): BottomSheetDialogFragment(){

    private var _binding: BottomsheetDocumentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomsheetDocumentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(KEY_EXTRA_DOCUMENT, Document::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(KEY_EXTRA_DOCUMENT)
        }?.let { document ->
            binding.tvDocumentType.text = resources.getString(R.string.document_type, document.type)
            Glide.with(requireContext())
                .load("${BuildConfig.BASE_URL}/andreascandle/p2p_json_test/main${document.url}")
                .into(binding.ivDocument)
        } ?: run {
            dismiss()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val KEY_EXTRA_DOCUMENT = "key-extra-document"

        fun newInstance(
            document: Document?,
        ): BottomSheetDialogFragment {
            val fragment = DocumentBottomSheetFragment()
            val args = Bundle()
            args.putParcelable(KEY_EXTRA_DOCUMENT, document)
            fragment.arguments = args
            return fragment
        }
    }
}