package com.example.mytranslator.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import coil.ImageLoader
import coil.request.LoadRequest
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mytranslator.R
import com.example.mytranslator.databinding.FragmentDescriptionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

private const val WORD_EXTRA = "b1990e8e-045d-4420-b54a-4d87c144703c"
private const val DESCRIPTION_EXTRA = "c6f5d263-1888-4c86-8141-c04115948a42"
private const val URL_EXTRA = "d6b0ab4d-d3e1-47b0-bd7a-86a6135a9935"

class DescriptionFragment : BottomSheetDialogFragment() {
    private var header: String? = null
    private var descr: String? = null
    private var url: String? = null

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            header = it.getString(WORD_EXTRA)
            descr = it.getString(DESCRIPTION_EXTRA)
            url = it.getString(URL_EXTRA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.descrHeader.text = header
        binding.descrText.text = descr
        val imageLink = url ?: ""
        if (imageLink.isNotEmpty() ) {
            usePicassoToLoadPhoto(binding.descrImg, imageLink)
            //useGlideToLoadPhoto(binding.descriptionImageview, imageLink)
            //useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
        }
        binding.close.setOnClickListener { dismiss() }
    }

    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String) {
        Picasso.get().load("https:$imageLink")
            .placeholder(R.drawable.no_img_200_200).fit().centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    imageView.setImageResource(R.drawable.ic_baseline_error_32)
                }
            })
    }

    private fun useGlideToLoadPhoto(imageView: ImageView, imageLink: String) {
        Glide.with(imageView)
            .load("https:$imageLink")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.setImageResource(R.drawable.ic_baseline_error_32)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.no_img_200_200)
                    .centerCrop()
            )
            .into(imageView)
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        val request = LoadRequest.Builder(requireContext())
            .data("https:$imageLink")
            .target(
                onStart = {},
                onSuccess = { result ->
                    imageView.setImageDrawable(result)
                },
                onError = {
                    imageView.setImageResource(R.drawable.ic_baseline_error_32)
                }
            )
            .transformations(
                CircleCropTransformation(),
            )
            .build()

        ImageLoader(requireContext()).execute(request)
    }

    companion object {
        @JvmStatic
        fun newInstance(word: String, description: String, url: String?) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(WORD_EXTRA, word)
                    putString(DESCRIPTION_EXTRA, description)
                    putString(URL_EXTRA, url)
                }
            }
    }
}