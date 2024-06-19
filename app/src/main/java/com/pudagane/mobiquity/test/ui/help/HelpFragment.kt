package com.pudagane.mobiquity.test.ui.help

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.pudagane.mobiquity.test.R

class HelpFragment : Fragment() {

    private lateinit var viewModel: HelpViewModel
    lateinit var webview: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.help_fragment, container, false)
        webview = root.findViewById(R.id.webview)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HelpViewModel::class.java)

        webview.loadUrl("file:///android_asset/demo.html")

    }

}