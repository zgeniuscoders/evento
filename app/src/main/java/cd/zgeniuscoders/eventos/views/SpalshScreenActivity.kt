package cd.zgeniuscoders.eventos.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cd.zgeniuscoders.eventos.R

class SpalshScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spalsh_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharePref = this.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val isLogged = sharePref.getBoolean("isLogged", false)
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({

            if(isLogged){
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }else{
                Intent(this, WelcomeActivity::class.java).apply {
                    startActivity(this)
                }
            }

            finish()

        },1000)
    }
}