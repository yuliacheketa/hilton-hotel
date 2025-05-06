package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRoomsBinding

class RoomsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Виводимо повідомлення "Скоро буде :)"
        binding.tvRooms.text = "Скоро буде :)"
    }
}
