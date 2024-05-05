
window.onload = function() {
    document.getElementById('username').focus();
};

document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("login-form");
    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Ngăn chặn form submit mặc định
		
        const usernameInput = document.getElementById("username");
        const passwordInput = document.getElementById("password");

        const usernameValue = usernameInput.value.trim();
        const passwordValue = passwordInput.value.trim();

        if (usernameValue === "") {
            alert("Vui lòng nhập tên tài khoản");
            return;
        }

        if (passwordValue === "") {
            alert("Vui lòng nhập mật khẩu");
            return;
        }

        // Nếu không có lỗi validate, bạn có thể submit form tại đây
        form.submit();
    });
});

