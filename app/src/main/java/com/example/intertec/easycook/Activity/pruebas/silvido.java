package com.example.intertec.easycook.Activity.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.intertec.easycook.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class silvido extends AppCompatActivity {
    EditText nombre_Receta,autor,categoria,puntuacion;
    Button boton_anyadir, boton_mostrar, boton_modificar, boton_borrar;
    ListView lista;
    DatabaseReference bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silvido);
        nombre_Receta = (EditText) findViewById(R.id.edtCategoria);
        autor = (EditText) findViewById(R.id.edtAutor);
        categoria=(EditText)findViewById(R.id.edtAutor);
        boton_anyadir = (Button) findViewById(R.id.button);
        boton_mostrar = (Button) findViewById(R.id.button2);
        boton_modificar = (Button) findViewById(R.id.button3);
        boton_borrar = (Button) findViewById(R.id.button4);
        lista = (ListView)findViewById(R.id.listView);
        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_discos));
        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();
                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    Disco disco = datasnapshot.getValue(Disco.class);
                    String receta1 = disco.getReceta();
                    listado.add(receta1);
                    String categoria1 = disco.getCategoria();
                    listado.add(categoria1);
                    String autor1 = disco.getAutor();
                    listado.add(autor1);
                }
                adaptador = new ArrayAdapter<String>(silvido.this,android.R.layout.simple_list_item_1,listado);
                lista.setAdapter(adaptador);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        boton_anyadir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String txtReceta = nombre_Receta.getText().toString();
                String txtCategoria = categoria.getText().toString();
                String txtAutor = autor.getText().toString();
                if(!TextUtils.isEmpty(txtReceta)){
                    if(!TextUtils.isEmpty(txtCategoria)){
                        if(!TextUtils.isEmpty(txtAutor)) {
                            Disco d = new Disco(txtReceta,txtCategoria,txtAutor);
                            String clave = bbdd.push().getKey();
                            bbdd.child(clave).setValue(d);
                            Toast.makeText(silvido.this, "Disco añadido", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(silvido.this, "Introduce el nombre de la receta", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(silvido.this, "introduce la categoria", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(silvido.this, "introduce el autor", Toast.LENGTH_LONG).show();
                }
            }
        });
        boton_mostrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Query q=bbdd.orderByChild(getString(R.string.campo_receta)).equalTo("a");
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int cont=0;
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            cont++;
                            Toast.makeText(silvido.this, "He encontrado "+cont, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        boton_modificar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String autor1= autor.getText().toString();
                if(!TextUtils.isEmpty(autor1)){
                    Query q=bbdd.orderByChild(getString(R.string.campo_autor)).equalTo(autor1);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                String clave=datasnapshot.getKey();
                                bbdd.child(clave).child(getString(R.string.campo_receta)).setValue(nombre_Receta.getText().toString());
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    //  Toast.makeText(silvidothis, "El año del disco "+titulo+" se ha modificado con éxito", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(silvido.this, "Debes de introducir un título", Toast.LENGTH_LONG).show();
                }
            }
        });
        boton_borrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String autor1 = autor.getText().toString();
                if(!TextUtils.isEmpty(autor1)){
                    Query q=bbdd.orderByChild(getString(R.string.campo_autor)).equalTo(autor1);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                String clave=datasnapshot.getKey();
                                DatabaseReference ref = bbdd.child(clave);
                                ref.removeValue();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    Toast.makeText(silvido.this, "La receta de  "+autor1+" se ha borrado con éxito", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(silvido.this, "Debes de introducir un título", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
