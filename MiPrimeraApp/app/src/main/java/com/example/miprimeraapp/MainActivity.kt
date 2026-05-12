// ... (tus imports actuales)
import android.widget.EditText // Asegúrate de tener este import

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Buscamos los componentes por el ID que pusiste en el XML
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val etEmail = findViewById<EditText>(R.id.etUsername) // Cambia etUsername por el ID de tu XML si es distinto
        val etPass = findViewById<EditText>(R.id.etPassword)

        btnIngresar.setOnClickListener {
            // 2. Extraemos el texto de los inputs
            val emailUsuario = etEmail.text.toString()

            // 3. Mostramos un mensaje personalizado usando interpolación ($)
            Toast.makeText(this, "Hola $emailUsuario, ¡bienvenido!", Toast.LENGTH_SHORT).show()
        }
    }
}