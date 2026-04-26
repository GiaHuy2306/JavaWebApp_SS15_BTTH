# Phần 1 - Giải thích API Mobile (ngắn gọn)

## 1. Hiện tượng

API `/mobile` trả về dữ liệu dạng:

- Có `content` (danh sách sản phẩm)
- Có thêm các thông tin như:
  - totalPages
  - size
  - number
  - first, last

---

## 2. Nguyên nhân

Do Controller đang trả về kiểu:

Page<ProductView>

Spring Data JPA sẽ tự động convert thành JSON gồm:
- content: dữ liệu chính
- thông tin phân trang

---

## 3. Đánh giá

- Phần `content` là đúng yêu cầu (chỉ có productName và price)
- Các field còn lại là metadata của phân trang

---

## 4. Cách xử lý

Nếu chỉ cần dữ liệu gọn nhẹ cho mobile:

- Trả về:
  service.mobile(...).getContent()

→ Kết quả chỉ còn danh sách sản phẩm

---

## 5. Kết luận

- Không phải lỗi hệ thống
- Là hành vi mặc định của Spring Data JPA khi trả về Page
- Có thể tối ưu bằng cách chỉ lấy content để giảm dữ liệu trả về
