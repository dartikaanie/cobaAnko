package com.example.sub1.fitur.klasemen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sub1.Model.klasemen.KlasemenResponse
import com.example.sub1.Utils.invisible
import com.example.sub1.Utils.visible
import com.example.sub1.adapter.KlasemenAdapter
import com.example.sub1.fitur.DetailLiga.DetailLigaActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.toast

class KlasemenFragment : Fragment(), AnkoComponent<Context>,
    KlasemenLigaContract.View{

    private lateinit var presenter: KlasemenLigaPresenter
        private lateinit var adapter: KlasemenAdapter
        private lateinit var detailActivity: DetailLigaActivity
        private lateinit var progressBar: ProgressBar
        lateinit var rv : RecyclerView

        override fun onAttach(context: Context) {
            super.onAttach(context)
            detailActivity = context as DetailLigaActivity
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            presenter = KlasemenLigaPresenter(
                this,
                GetKlasemenLiga(detailActivity.liga.id.toString())
            )
            presenter.requestDataFromServer()

            return createView(AnkoContext.create(requireContext()))
        }

        override fun createView(ui: AnkoContext<Context>): View = with(ui){
            verticalLayout {
                rv = recyclerView {
                    lparams {
                        width = matchParent
                        height = wrapContent
                        rightMargin = dip(8)
                        leftMargin = dip(8)
                    }
//                    adapter = eventNextAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                progressBar = progressBar().lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }



        override fun showProgress() {
            progressBar.visible()
        }

        override fun hideProgress() {
            progressBar.invisible()
        }


    override fun setDataToList(data: KlasemenResponse) {
        adapter = data.table.let { KlasemenAdapter(it) }
        rv.adapter = adapter
    }





    override fun onResponseFailure(throwable: Throwable) {
        Log.e("data Klasemen", throwable.toString())
            toast("Maaf Ada Kesalahan")
            progressBar.invisible()

        }
    }