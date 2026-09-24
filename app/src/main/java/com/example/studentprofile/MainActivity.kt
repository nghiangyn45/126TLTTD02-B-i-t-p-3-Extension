package com.example.studentprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofile.databinding.ActivityMainBinding
import com.example.studentprofile.utils.* // Import các extension functions từ Bài 3[cite: 17, 18]

class MainActivity : AppCompatActivity() {

    // Khai báo biến ViewBinding (Bài 1)[cite: 1, 3]
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị thông tin sinh viên mẫu ban đầu giống như trên màn hình giả lập[cite: 19]
        displayStudentProfile()

        // Xử lý sự kiện khi bấm nút "Cập Nhật Điểm GPA"[cite: 19]
        binding.btnUpdateGpa.setOnClickListener {
            // Sử dụng extension trimmedText() để lấy chuỗi đã cắt khoảng trắng thừa[cite: 17, 18]
            val inputStr = binding.edtGpaInput.trimmedText()

            // Sử dụng Scope Function 'let' và 'takeIf' để kiểm tra dữ liệu an toàn[cite: 8, 10]
            inputStr.takeIf { it.isNotEmpty() }?.let { validStr ->
                val gpaValue = validStr.toDouble()

                // Sử dụng extension toAcademicRanking() để lấy xếp loại học lực[cite: 16, 17, 18]
                val ranking = gpaValue.toAcademicRanking()

                // Sử dụng Scope Function 'with' để gom nhóm cập nhật giao diện[cite: 9, 10]
                with(binding) {
                    tvStudentDetails.text = "MSSV: 2415053122129 | Lớp: 126TLTTD02\nEmail: 2415053122129@st.edu.vn\n$gpaValue GPA ($ranking)"
                }

                // Hiển thị thông báo nhanh bằng extension toast()[cite: 17, 18]
                toast("Đã cập nhật GPA thành công!")
            } ?: run {
                // Xử lý khi để trống ô nhập liệu
                toast("Vui lòng nhập điểm GPA!")
                binding.edtGpaInput.error = "Không được để trống"
            }
        }
    }

    /**
     * Hàm hiển thị thông tin chi tiết sinh viên lên giao diện,
     * áp dụng Scope Function 'with' để code gọn gàng, sạch sẽ.[cite: 9, 10]
     */
    private fun displayStudentProfile() {
        with(binding) {
            tvStudentName.text = "Nguyễn Minh Nghĩa"
            tvStudentDetails.text = "MSSV: 2415053122129 | Lớp: 126TLTTD02\nEmail: 2415053122129@st.edu.vn"

            // Đặt sẵn giá trị mẫu 2.89 vào EditText như trong hình[cite: 19]
            edtGpaInput.setText("2.89")
        }
    }
}