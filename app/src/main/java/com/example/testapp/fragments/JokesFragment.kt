package com.example.testapp.fragments

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testapp.R
import com.example.testapp.`interface`.OnClickListener
import com.example.testapp.`interface`.RetrofitServices
import com.example.testapp.adapters.JokesAdapter
import com.example.testapp.common.Common
import com.example.testapp.models.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokesFragment : Fragment() {

    lateinit var rootView: View
    lateinit var retrofitServices: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var srlRefreshList: SwipeRefreshLayout
    lateinit var rvList: RecyclerView
    lateinit var tvNoItems: TextView
    lateinit var adapter: JokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_jokes, container, false)
        setupUI()
        setupUX()
        retrofitServices = Common.retrofitService
        getAllJokeList()
        return rootView
    }

    private fun setupUX() {
        layoutManager = LinearLayoutManager(context)
        rvList.layoutManager = layoutManager
        srlRefreshList.setOnRefreshListener {
            getAllJokeList()
        }
    }

    private fun getAllJokeList() {
        retrofitServices.getJokesList().enqueue(object : Callback<MutableList<Joke>> {
            override fun onFailure(call: Call<MutableList<Joke>>, t: Throwable) {
                if (srlRefreshList.isRefreshing) srlRefreshList.setRefreshing(false)
                tvNoItems.visibility = View.VISIBLE
                rvList.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<MutableList<Joke>>,
                response: Response<MutableList<Joke>>
            ) {
                if (srlRefreshList.isRefreshing) srlRefreshList.setRefreshing(false)
                val listData: MutableList<Joke> = response.body() as MutableList<Joke>
                if (listData.isEmpty()) {
                    tvNoItems.visibility = View.VISIBLE
                    rvList.visibility = View.GONE
                } else {
                    tvNoItems.visibility = View.GONE
                    rvList.visibility = View.VISIBLE
                    adapter = JokesAdapter(listData, onClickListener)
                    adapter.notifyDataSetChanged()
                    rvList.adapter = adapter
                }
            }
        })
    }

    private var onClickListener = object : OnClickListener {
        override fun onClick(cardView: CardView, joke: Joke) {
            val inflater: LayoutInflater =
                getSystemService(requireContext(), LayoutInflater::class.java) as LayoutInflater
            val view = inflater.inflate(R.layout.popup_joke, null)
            val popupWindow = PopupWindow(
                view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                popupWindow.elevation = 10.0F
            val tvText = view.findViewById<TextView>(R.id.tvText)
            val btOk = view.findViewById<Button>(R.id.btOk)
            tvText.setText("Id: " + joke.id + "\nType:" + joke.type + "\nSetup:" + joke.setup + "\nPunchline:" + joke.punchline)
            btOk.setOnClickListener { popupWindow.dismiss() }
            popupWindow.isOutsideTouchable = true
            popupWindow.setOnDismissListener { popupWindow.dismiss() }
            popupWindow.showAtLocation(
                cardView,
                Gravity.CENTER,
                0,
                0
            )
        }
    }

    private fun setupUI() {
        rvList = rootView.findViewById(R.id.rvList)
        srlRefreshList = rootView.findViewById(R.id.srlRefreshList)
        tvNoItems = rootView.findViewById(R.id.tvNoItems)
    }
}