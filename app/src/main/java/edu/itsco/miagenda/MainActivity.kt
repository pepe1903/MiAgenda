package edu.itsco.miagenda

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.itsco.miagenda.adapters.ContactosAdapter
import edu.itsco.miagenda.modelos.Contacto

class MainActivity : AppCompatActivity() {

    private val adaptadorContacto:ContactosAdapter = ContactosAdapter(ArrayList<Contacto>())
    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode== RESULT_OK){
            val intent=it.data
            val contacto:Contacto?=intent?.getParcelableExtra<Contacto>("CONTACTO")
            adaptadorContacto.addContacto(contacto!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerContactos:RecyclerView=findViewById(R.id.recycler_contactos)
        recyclerContactos.adapter=adaptadorContacto

        recyclerContactos.layoutManager=LinearLayoutManager(this)
        recyclerContactos.setHasFixedSize(true)

        val fabAddContacto:FloatingActionButton=findViewById(R.id.fab_add_contacto)
        fabAddContacto.setOnClickListener{
            val intent = Intent(this,NuevoContactoActivity::class.java)
            startForResult.launch(intent)
        }
    }
}