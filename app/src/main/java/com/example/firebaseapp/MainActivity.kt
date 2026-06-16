package com.example.firebaseapp // Asegúrate de que este nombre coincida con tu proyecto

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var userListView: ListView
    private lateinit var statusTextView: TextView
    private lateinit var userInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inicializar Firebase
        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()

        // 2. Vincular elementos de la interfaz
        val usernameET = findViewById<EditText>(R.id.usernameEditText)
        val passwordET = findViewById<EditText>(R.id.passwordEditText)
        val loginBtn = findViewById<Button>(R.id.loginButton)
        val addUserBtn = findViewById<Button>(R.id.addUserButton)
        val editUserBtn = findViewById<Button>(R.id.editUserButton)
        val deleteUserBtn = findViewById<Button>(R.id.deleteUserButton)

        statusTextView = findViewById(R.id.statusTextView)
        userInfoTextView = findViewById(R.id.userInfoTextView)
        userListView = findViewById(R.id.userListView)

        // Verificar conexión inicial
        checkFirestoreConnection()
        loadUserList()

        // 3. Configurar botones
        addUserBtn.setOnClickListener {
            val user = usernameET.text.toString()
            val pass = passwordET.text.toString()
            if (user.isNotEmpty() && pass.isNotEmpty()) {
                addUser(user, pass.toLong())
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        loginBtn.setOnClickListener {
            loginUser(usernameET.text.toString(), passwordET.text.toString().toLong())
        }

        editUserBtn.setOnClickListener {
            editUser(usernameET.text.toString(), passwordET.text.toString().toLong())
        }

        deleteUserBtn.setOnClickListener {
            deleteUser(usernameET.text.toString())
        }
    }

    private fun addUser(nombre: String, contrasenia: Long) {
        val datos = hashMapOf("nombre" to nombre, "contrasenia" to contrasenia)
        db.collection("Usuarios").add(datos)
            .addOnSuccessListener {
                Toast.makeText(this, "Usuario añadido", Toast.LENGTH_SHORT).show()
                loadUserList()
            }
            .addOnFailureListener { Toast.makeText(this, "Error al añadir", Toast.LENGTH_SHORT).show() }
    }

    private fun loginUser(nombre: String, contrasenia: Long) {
        db.collection("Usuarios")
            .whereEqualTo("nombre", nombre)
            .whereEqualTo("contrasenia", contrasenia)
            .get()
            .addOnSuccessListener { docs ->
                if (!docs.isEmpty) {
                    userInfoTextView.text = "¡Bienvenido, $nombre!"
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun editUser(nombre: String, nuevaContrasenia: Long) {
        db.collection("Usuarios").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { docs ->
                for (doc in docs) {
                    db.collection("Usuarios").document(doc.id).update("contrasenia", nuevaContrasenia)
                }
                Toast.makeText(this, "Contraseña actualizada", Toast.LENGTH_SHORT).show()
                loadUserList()
            }
    }

    private fun deleteUser(nombre: String) {
        db.collection("Usuarios").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { docs ->
                for (doc in docs) {
                    db.collection("Usuarios").document(doc.id).delete()
                }
                Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                loadUserList()
            }
    }

    private fun loadUserList() {
        db.collection("Usuarios").get().addOnSuccessListener { docs ->
            val userList = mutableListOf<String>()
            for (doc in docs) {
                userList.add("${doc.getString("nombre")} - ${doc.get("contrasenia")}")
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList)
            userListView.adapter = adapter
        }
    }

    private fun checkFirestoreConnection() {
        db.collection("Usuarios").limit(1).get()
            .addOnSuccessListener { statusTextView.text = "Conectado a Firestore" }
            .addOnFailureListener { statusTextView.text = "Error de conexión" }
    }
}