package com.example.bt_event_validation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bt_event_validation.ChiTietActivity
import com.example.bt_event_validation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_HO_TEN = "extra_ho_ten"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_SDT = "extra_sdt"
        const val EXTRA_MSV = "extra_msv"
        const val EXTRA_LOP = "extra_lop"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sự kiện click nút Đăng ký
        binding.btnDangKi.setOnClickListener {
            dangKi()
        }
    }

    private fun dangKi() {
        val hoTen = binding.edtHoTen.text.toString()
            .trim().replace(Regex("\\s+"), " ")

        val email = binding.edtEmail.text.toString().trim()
        val sdt = binding.edtSdt.text.toString().trim()
        val msv = binding.edtMsv.text.toString().trim()
        val lop = binding.edtLop.text.toString().trim()
        //HoTen
        if (hoTen.isEmpty()) {
            hienThiLoi(binding.edtHoTen, "Vui lòng nhập họ tên!")
            return
        }

        val regexHoTen = Regex("""^[\p{L}]+(?: [\p{L}]+)+$""")
        if (!regexHoTen.matches(hoTen)) {
            hienThiLoi(
                binding.edtHoTen,
                "Họ tên phải có ít nhất 2 từ và chỉ chứa chữ cái!"
            )
            return
        }
        //Email
        if (email.isEmpty()) {
            hienThiLoi(binding.edtEmail, "Vui lòng nhập Email!")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            hienThiLoi(
                binding.edtEmail,
                "Email không đúng định dạng! Ví dụ: abc@gmail.com"
            )
            return
        }

        // SDT
        if (sdt.isEmpty()) {
            hienThiLoi(binding.edtSdt, "Vui lòng nhập số điện thoại!")
            return
        }
        val regexSdt = Regex("""^(0[35789]\d{8}|\+84[35789]\d{8})$""")

        if (!regexSdt.matches(sdt)) {
            hienThiLoi(
                binding.edtSdt,
                "SĐT không hợp lệ! Nhập 10 số, ví dụ: 0912345678"
            )
            return
        }

        // MÃ SINH VIÊN
        if (msv.isEmpty()) {
            hienThiLoi(binding.edtMsv, "Vui lòng nhập mã sinh viên!")
            return
        }
        val regexMsv = Regex("""^\d{10,15}$""")

        if (!regexMsv.matches(msv)) {
            hienThiLoi(
                binding.edtMsv,
                "MSV phải gồm 10 đến 15 chữ số!"
            )
            return
        }

        //Lớp
        if (lop.isEmpty()) {
            hienThiLoi(binding.edtLop, "Vui lòng nhập lớp!")
            return
        }
        val regexLop = Regex("""^[\p{L}0-9]+(?:[ ._/-][\p{L}0-9]+)*$""")
        if (lop.length !in 2..30 || !regexLop.matches(lop)) {
            hienThiLoi(
                binding.edtLop,
                "Lớp từ 2-30 ký tự, chỉ gồm chữ, số và ký tự hợp lệ!"
            )
            return
        }


        Toast.makeText(
            this,
            "Đăng ký thành công!",
            Toast.LENGTH_SHORT
        ).show()

        //Intent chuyển sang màn hình Chi tiết
        val intent = Intent(this, ChiTietActivity::class.java)

        intent.putExtra(EXTRA_HO_TEN, hoTen)
        intent.putExtra(EXTRA_EMAIL, email)
        intent.putExtra(EXTRA_SDT, sdt)
        intent.putExtra(EXTRA_MSV, msv)
        intent.putExtra(EXTRA_LOP, lop)

        startActivity(intent)
    }

    private fun hienThiLoi(editText: EditText, thongBao: String) {
        editText.error = thongBao
        editText.requestFocus()

        Toast.makeText(
            this,
            thongBao,
            Toast.LENGTH_SHORT
        ).show()
    }
}
