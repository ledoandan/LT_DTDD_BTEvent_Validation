package com.example.bt_event_validation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bt_event_validation.databinding.ActivityChiTietBinding

class ChiTietActivity : AppCompatActivity() {

    // Khai báo ViewBinding
    private lateinit var binding: ActivityChiTietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo ViewBinding
        binding = ActivityChiTietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ MainActivity qua Intent
        val hoTen = intent.getStringExtra(
            MainActivity.EXTRA_HO_TEN
        ).orEmpty()

        val email = intent.getStringExtra(
            MainActivity.EXTRA_EMAIL
        ).orEmpty()

        val sdt = intent.getStringExtra(
            MainActivity.EXTRA_SDT
        ).orEmpty()

        val msv = intent.getStringExtra(
            MainActivity.EXTRA_MSV
        ).orEmpty()

        val lop = intent.getStringExtra(
            MainActivity.EXTRA_LOP
        ).orEmpty()

        // Hiển thị thông tin sinh viên lên TextView
        binding.tvChiTietHoTen.text = hoTen
        binding.tvChiTietEmail.text = email
        binding.tvChiTietSdt.text = sdt
        binding.tvChiTietMsv.text = msv
        binding.tvChiTietLop.text = lop

        // Thông báo đã nhận dữ liệu
        Toast.makeText(
            this,
            "Đã nhận thông tin sinh viên!",
            Toast.LENGTH_SHORT
        ).show()

        // Xử lý sự kiện nút Quay lại
        binding.btnQuayLai.setOnClickListener {

            // Intent chuyển về MainActivity
            val intentQuayLai = Intent(
                this,
                MainActivity::class.java
            )

            // Giữ lại MainActivity cũ
            intentQuayLai.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
            )

            startActivity(intentQuayLai)

            // Đóng màn hình Chi tiết
            finish()
        }
    }
}
