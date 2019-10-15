package iteso.mx.itesogram.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import iteso.mx.itesogram.R
import iteso.mx.itesogram.adapters.AdapterPhoto
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.activityUiThread

class FragmentHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        doAsync{
            val query = ParseQuery.getQuery<ParseObject>("Feed")
            query.findInBackground(object : FindCallback<ParseObject> {
                var recyclerView = view.findViewById<RecyclerView>(R.id.fragment_home_recyclerview)
                var posts: List<ParseObject> = arrayListOf<ParseObject>()

                override fun done(postList: List<ParseObject>, e: ParseException?) {
                        if (e == null) {
                            posts = postList
                            activity?.runOnUiThread {
                                recyclerView.adapter = AdapterPhoto(posts)
                                recyclerView.adapter?.notifyDataSetChanged();
                                recyclerView.layoutManager = LinearLayoutManager(context)
                            }
                            Log.d("score", "Retrieved " + posts.size + " scores")

                        } else {
                            Log.d("score", "Error: " + e.getStackTrace())
                        }
                }
            })

        }

        return view
    }}
