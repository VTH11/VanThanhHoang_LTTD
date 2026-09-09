import java.util.Scanner

data class Student(
    val id: String,
    val name: String,
    val age: Int,
    val major: String,
    val gpa: Double
)
val students = mutableListOf<Student>(
    Student("UTCNTT24101", "Nguyễn Mạnh Hùng", 20, "Công nghệ thông tin", 8.5),
    Student("UTCDT22205", "Trương Tú Tài", 22, "Cơ Điện Tử", 4.8),
    Student("UTCK25312", "Đỗ Thành Trung", 19, "Cơ khí", 7.5),
    Student("UTKTHH23419", "Nguyễn Thị Huyền My", 21, "Công nghệ kỹ thuật hoá học", 9.0),
    Student("UTXD26555", "Đoàn Kim Anh", 18, "Xây dựng", 6.5)
)

fun inDanhSach(danhSach: List<Student> = students) {
    if (danhSach.isEmpty()) {
        println("Không có sinh viên nào.")
        return
    }
    println("--------------------------------------------------------------------------------------")
    println(String.format("%-15s | %-25s | %-5s | %-30s | %-5s", "ID", "Họ và Tên", "Tuổi", "Ngành học", "GPA"))
    println("--------------------------------------------------------------------------------------")
    for (sv in danhSach) {
        println(String.format("%-15s | %-25s | %-5d | %-30s | %-5.2f", sv.id, sv.name, sv.age, sv.major, sv.gpa))
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n~~~~~~~~~~ STUDENT MANAGEMENT ~~~~~~~~~~")
        println("1. Add student (Thêm sinh viên)")
        println("2. Display all students (Hiển thị tất cả sinh viên)")
        println("3. Search student (Tìm kiếm sinh viên theo tên)")
        println("4. Calculate average GPA (Tính GPA trung bình theo ngành)")
        println("5. Find student with highest GPA (Tìm sinh viên có GPA cao nhất)")
        println("6. Remove student (Xoá sinh viên)")
        println("7. Count by GPA (Đếm số sinh viên GPA >= 8.0 và < 5.0)")
        println("8. Find oldest student (Tìm sinh viên lớn tuổi nhất)")
        println("9. Find GPA 7.0 - 8.5 (Tìm SV có GPA từ 7.0 -> 8.5)")
        println("10. Search by major (Tìm tất cả sinh viên thuộc một ngành)")
        println("11. Sort by GPA (Sắp xếp sinh viên theo GPA giảm dần)")
        println("12. Top 3 GPA (Hiển thị 3 sinh viên có GPA cao nhất)")
        println("13. Sort by Age (Sắp xếp sinh viên theo tuổi tăng dần)")
        println("14. Sort by Name (Sắp xếp sinh viên theo tên)")
        println("0. Exit (Thoát)")
        println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        print("Choose: ")

        when (scanner.nextLine()) {
            "1" -> {
                print("Nhập ID: ")
                val id = scanner.nextLine()
                print("Nhập Họ và Tên: ")
                val name = scanner.nextLine()
                print("Nhập Tuổi: ")
                val age = scanner.nextLine().toIntOrNull() ?: 0
                print("Nhập Ngành học: ")
                val major = scanner.nextLine()
                print("Nhập GPA: ")
                val gpa = scanner.nextLine().toDoubleOrNull() ?: 0.0
                students.add(Student(id, name, age, major, gpa))
                println("Đã thêm sinh viên thành công!")
            }
            "2" -> inDanhSach()
            "3" -> {
                print("Nhập tên hoặc Mã sinh viên (ID) cần tìm: ")
                val tuKhoa = scanner.nextLine().trim()

                if (tuKhoa.isEmpty()) {
                    println("Từ khóa tìm kiếm không được để trống!")
                } else {
                    val ketQua = students.filter {
                        it.name.contains(tuKhoa, ignoreCase = true) ||
                                it.id.contains(tuKhoa, ignoreCase = true)
                    }
                    if (ketQua.isNotEmpty()) {
                        println("Tìm thấy ${ketQua.size} sinh viên phù hợp:")
                        inDanhSach(ketQua)
                    } else {
                        println("Không tìm thấy sinh viên nào khớp với từ khóa '$tuKhoa'.")
                    }
                }
            }
            "4" -> {
                print("Nhập ngành cần tính trung bình GPA: ")
                val nganh = scanner.nextLine()
                val svNganh = students.filter { it.major.equals(nganh, ignoreCase = true) }
                if (svNganh.isNotEmpty()) {
                    val tb = svNganh.map { it.gpa }.average()
                    println("GPA trung bình của ngành $nganh là: ${String.format("%.2f", tb)}")
                } else {
                    println("Không tìm thấy sinh viên ngành này.")
                }
            }
            "5" -> {
                val maxGpa = students.maxByOrNull { it.gpa }
                println("Sinh viên có GPA cao nhất:")
                if (maxGpa != null) inDanhSach(listOf(maxGpa))
            }
            "6" -> {
                print("Nhập ID sinh viên cần xoá: ")
                val id = scanner.nextLine()
                if (students.removeIf { it.id.equals(id, ignoreCase = true) }) {
                    println("Đã xoá thành công.")
                } else {
                    println("Không tìm thấy sinh viên với ID này.")
                }
            }
            "7" -> {
                println("Số sinh viên có GPA >= 8.0: ${students.count { it.gpa >= 8.0 }}")
                println("Số sinh viên có GPA < 5.0: ${students.count { it.gpa < 5.0 }}")
            }
            "8" -> {
                val maxAge = students.maxByOrNull { it.age }
                println("Sinh viên lớn tuổi nhất:")
                if (maxAge != null) inDanhSach(listOf(maxAge))
            }
            "9" -> {
                println("Danh sách sinh viên có GPA từ 7.0 đến 8.5:")
                inDanhSach(students.filter { it.gpa in 7.0..8.5 })
            }
            "10" -> {
                print("Nhập ngành cần tìm: ")
                val nganh = scanner.nextLine()
                inDanhSach(students.filter { it.major.equals(nganh, ignoreCase = true) })
            }
            "11" -> inDanhSach(students.sortedByDescending { it.gpa })
            "12" -> inDanhSach(students.sortedByDescending { it.gpa }.take(3))
            "13" -> inDanhSach(students.sortedBy { it.age })
            "14" -> {
                inDanhSach(students.sortedBy { it.name.substringAfterLast(" ") })
            }
            "0" -> {
                println("Đang thoát chương trình...")
                return
            }
            else -> println("Lựa chọn không hợp lệ, vui lòng thử lại!")
        }
    }
}