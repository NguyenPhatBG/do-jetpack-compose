  Khái niệm cơ bản trong Kotlin:  <br/>
   1. Biến (Variable): Một biến là một vùng bộ nhớ được đặt tên để lưu trữ và thay đổi giá trị. <br/>
   Trong Kotlin, bạn có thể khai báo biến bằng từ khóa var.  <br/>
   Ví dụ: var x: Int = 5  <br/>
    <br/>
   2. Hằng số (Constant): Một hằng số là một giá trị không thay đổi trong suốt thời gian chạy của chương trình. <br/>
   Trong Kotlin, bạn có thể khai báo hằng số bằng từ khóa val. <br/>
   Ví dụ: val PI: Double = 3.14159 <br/>
  <br/>
   3. Kiểu dữ liệu (Data Types): Kiểu dữ liệu xác định loại giá trị mà biến hoặc hằng số có thể chứa. <br/>
   Ví dụ, Int là kiểu dữ liệu của các số nguyên, String là kiểu dữ liệu của chuỗi ký tự. <br/>
  <br/>
   4. Hàm (Function): Một hàm là một khối mã thực hiện một tác vụ cụ thể. <br/>
   Trong Kotlin, bạn có thể khai báo hàm bằng từ khóa fun <br/>
  <br/>
   5. Trong Kotlin, để khai báo một danh sách (list), bạn có thể sử dụng kiểu dữ liệu List hoặc MutableList. <br/>
   - Khai báo List không thay đổi (Immutable List) => Để khai báo một danh sách không thay đổi, bạn có thể sử dụng kiểu List<T>, <br/>
   trong đó T là kiểu dữ liệu của các phần tử trong danh sách. <br/>
   VD: val numbers: List<Int> = listOf(1, 2, 3, 4, 5) <br/>
   - Khai báo MutableList có thể thay đổi (Mutable List) => Để khai báo một danh sách có thể thay đổi, bạn có thể sử dụng kiểu MutableList<T>, <br/>
   trong đó T là kiểu dữ liệu của các phần tử trong danh sách. <br/>
   VD: val names: MutableList<String> = mutableListOf("John", "Jane", "Alice") <br/>
 <br/>
  1. "Internal" là một từ khóa trong Kotlin được sử dụng để khai báo một thành phần (ví dụ: lớp, phương thức hoặc thuộc tính) có phạm vi truy cập nội bộ trong cùng một module. <br/>
    Khi một thành phần được khai báo là "internal", nó chỉ có thể truy cập được từ các thành phần khác trong cùng một module, nhưng không thể truy cập từ bên ngoài module đó. <br/>
    Ví dụ, nếu bạn khai báo một lớp là "internal class MyClass" trong một module Kotlin, thì lớp này chỉ có thể truy cập được bởi các thành phần khác trong cùng module đó. <br/>
    Nếu bạn cố gắng truy cập lớp này từ một module khác, bạn sẽ nhận được lỗi biên dịch. <br/>
    Từ khóa "internal" rất hữu ích khi bạn muốn giới hạn phạm vi truy cập của một thành phần trong module của mình, nhưng không muốn cho phép truy cập từ bên ngoài module đó. <br/>
  <br/>
  2. "internal set" thường được sử dụng khi bạn muốn giới hạn phạm vi gán giá trị của một thuộc tính chỉ cho các thành phần trong cùng module, <br/>
     nhưng không muốn cho phép gán giá trị từ bên ngoài module. <br/>
  <br/>
  3. "Data class" là một từ khóa trong Kotlin được sử dụng để định nghĩa một lớp dùng để lưu trữ dữ liệu (data) với một số tính năng tự động được tạo ra bởi Kotlin Compiler. <br/>
   Khi một lớp được khai báo là data class, Kotlin sẽ tự động tạo ra một số thành phần cơ bản cho lớp đó, bao gồm: <br/>
   A. Các thuộc tính (properties): Kotlin Compiler tự động tạo các thuộc tính dựa trên các tham số được khai báo trong constructor chính của data class. <br/>
      Các thuộc tính này được tự động tạo getter và setter (nếu cần thiết), và cũng được tự động thêm các phương thức equals(), hashCode(), và toString(). <br/>
      Điều này giúp ta có thể dễ dàng so sánh và in ra thông tin của các đối tượng data class. <br/>
   B. Phương thức equals() và hashCode(): Kotlin Compiler tự động tạo ra phương thức equals() và hashCode() dựa trên các thuộc tính trong data class. <br/>
      Điều này giúp ta có thể so sánh dữ liệu giữa hai đối tượng data class bằng cách so sánh từng thuộc tính. <br/>
   C. Phương thức toString(): Kotlin Compiler tự động tạo ra phương thức toString() để in ra thông tin chi tiết của đối tượng data class. <br/>
   D. Phương thức copy(): Kotlin Compiler tự động tạo ra phương thức copy() cho data class, cho phép ta tạo ra một bản sao (copy) của đối tượng data class với một số <br/>
      thuộc tính có thể được thay đổi. <br/>
      Data class rất hữu ích khi làm việc với các đối tượng chỉ chứa dữ liệu và không có các logic phức tạp. Các tính năng tự động được tạo bởi Kotlin Compiler <br/>
      giúp giảm thiểu việc viết mã lặp đi lặp lại và giúp ta dễ dàng làm việc với các đối tượng data class. <br/>
  <br/>
  4. "staticCompositionLocalOf" là một phương pháp trong Compose UI của Kotlin được sử dụng để tạo một giá trị CompositionLocal có phạm vi tĩnh. <br/>
     "staticCompositionLocalOf" giúp chia sẻ dữ liệu theo cách tiện lợi và linh hoạt trong Compose UI, đồng thời giúp giảm thiểu việc truyền dữ liệu qua các <br/>
      tham số giữa các composable. <br/>
      Sau khi tạo staticCompositionLocalOf, ta có thể truy cập nó từ bất kỳ composable nào trong cây composable bằng cách sử dụng current <br/>
      val myLocalValue = staticCompositionLocalOf { defaultValue } <br/>
      val value = myLocalValue.current <br/>
  <br/>
  5. "private set" là một cách để định nghĩa một thuộc tính (property) chỉ có thể được đặt giá trị bên trong cùng một lớp hoặc đối tượng trong Kotlin. <br/>
      - Khi một thuộc tính được khai báo với private set, nghĩa là bạn chỉ có thể gán giá trị cho thuộc tính đó từ bên trong cùng một lớp hoặc đối tượng. <br/>
        Bên ngoài lớp hoặc đối tượng đó, thuộc tính sẽ không thể được gán giá trị. <br/>
        Ví dụ, xem xét lớp Person trong Kotlin: <br/><br/>
        
        class Person {
            var name: String
                private set

            constructor(name: String) {
                this.name = name
            }
         }
        
      - Trong ví dụ trên, thuộc tính name của lớp Person được khai báo với private set. <br/>
        Điều này có nghĩa là bạn chỉ có thể gán giá trị cho name từ bên trong lớp Person, nhưng không thể từ bên ngoài. Ví dụ: <br/>
        val person = Person("John") <br/>
        person.name = "Jane" // Không hợp lệ, không thể gán giá trị từ bên ngoài <br/>
      - Trong trường hợp này, chỉ có constructor của lớp Person mới có thể đặt giá trị cho name. <br/>
        Các thành phần khác trong và ngoài lớp Person chỉ có thể đọc giá trị của name mà không thể thay đổi nó. <br/>
 
