package iteso.mx.itesogram.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.parse.ParseFile
import com.parse.ParseObject
import iteso.mx.itesogram.R

class AdapterPhoto(var photos : List<ParseObject>): RecyclerView.Adapter<PhotoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        Log.d("AdapterUser", photos.size.toString())
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }
}

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private var username   : TextView = view.findViewById(R.id.item_photo_username)
    private var likes    : TextView = view.findViewById(R.id.item_photo_likes_amount)
    private var mainphoto   : ImageView = view.findViewById(R.id.item_photo_main_image_iv)
    private var  rm  = Glide.with(view);

    fun bind(photo : ParseObject){
        Log.d("AdapterUser", photo.getString("username") + " - " + photo.getString("commentsNumber"))
        username.text = photo.getString("username")
        likes.text = photo.getInt("commentsNumber").toString() + " likes!"
        var imagen : ParseFile = photo.getParseFile("photo")!!
        rm.load(imagen.url).into(mainphoto)
        //photo.text = user.get("photo"). oString()
    }
}