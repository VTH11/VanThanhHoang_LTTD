//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    print("Nhap ten sinh vien: ")
    val ten = readln()
    print("Nhap ma sinh vien: ")
    val msv = readln()
    print("Nhap diem toan: ")
    val math = readln().toDouble()
    print("Nhap diem programming: ")
    val programming = readln().toDouble()
    print("Nhap diem database: ")
    val database = readln().toDouble()

    val tong = math + programming + database
    val diemtb = tong/3
    var diemmax = math
    if (programming > diemmax) {
        diemmax = programming
    }
    if (database > diemmax) {
        diemmax = database
    }
    var kq = "rot mon"
    if (diemtb >= 5.0) {
        kq = "qua mon"
    }
    println("~~~KQ Hoc tap~~~")
    println("Sinh viên $ten ($msv) - Tổng điểm: $tong")
    println("Sinh viên $ten ($msv) - Điểm trung bình: $diemtb")
    println("Sinh viên $ten ($msv) - Điểm cao nhất: $diemmax")
    println("Sinh viên $ten ($msv) - Tình trạng: $kq")
}