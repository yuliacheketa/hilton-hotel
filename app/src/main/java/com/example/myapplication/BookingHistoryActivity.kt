package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityBookinghistoryBinding

class BookingHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookinghistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookinghistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadBookingHistory()
    }

    private fun loadBookingHistory() {

        binding.tvBookingHistory1.text = "Скоро буде :)"

    }
}
