const bar = document.getElementById("bar");
const close = document.getElementById("close");
const nav = document.getElementById("navbar");

bar.addEventListener("click", () => {
  nav.classList.add("active");
});

close.addEventListener("click", () => {
  nav.classList.remove("active");
});

let pro = Array.from(document.querySelectorAll(".pro"));

pro.forEach((e) => {
  e.addEventListener("click", function () {
    window.location.href = "sproduct.html";
  });
});

///
// Thêm sự kiện click cho các nút danh mục
const categoryLinks = document.querySelectorAll('#page-Category a[data-category-id]');
categoryLinks.forEach(link => {
  link.addEventListener('click', (event) => {
    event.preventDefault(); // Ngăn chặn hành vi mặc định khi nhấp vào liên kết
    const categoryId = link.getAttribute('data-category-id'); // Lấy giá trị data-category-id
    scrollToCategory(categoryId); // Cuộn đến danh sách sản phẩm danh mục tương ứng
  });
});

// Hàm cuộn đến danh sách sản phẩm danh mục tương ứng
function scrollToCategory(categoryId) {
  const section = document.querySelector(`#product1[data-category-id="${categoryId}"]`);
  if (section) {
    // Sử dụng phương thức scrollIntoView để cuộn trang đến phần tử có data-category-id tương ứng
    section.scrollIntoView({ behavior: 'smooth' });
  }
}
