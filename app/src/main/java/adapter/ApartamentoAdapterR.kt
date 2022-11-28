package adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fora_neo.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import datos.Apartamento

class ApartamentoAdapterR(private val listaApartamento:List<Apartamento>, activity: FragmentActivity?, controller: NavController, etq:String):
    RecyclerView.Adapter<ApartamentoViewHolderR>() {
    var yes:Boolean? = null
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val actividad = activity
    val controler = controller
    var id = " "
    var contexto = etq

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartamentoViewHolderR {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApartamentoViewHolderR(layoutInflater.inflate(R.layout.apartamento_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ApartamentoViewHolderR, position: Int) {
        val item = listaApartamento[position]
        val likes = item.likes!!.toMutableList()
        val liked = likes.contains(auth.uid)
        holder.renderizar(item, actividad)
        holder.like(likes, liked, item)
        id = holder.clicked(controler, item, contexto)
    }

    override fun getItemCount(): Int {
        return listaApartamento.size
    }
}