function togglePassword(inputId, button) {
    const input = document.getElementById(inputId);

    if (!input) {
        return;
    }

    if (input.type === "password") {
        input.type = "text";
        button.textContent = "Ẩn";
    } else {
        input.type = "password";
        button.textContent = "Hiện";
    }
}

function validateLoginForm() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (username === "" || password === "") {
        alert("Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
        return false;
    }

    return true;
}

function validateRegisterForm() {
    const fullname = document.getElementById("fullname").value.trim();
    const username = document.getElementById("username").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword =
            document.getElementById("confirmPassword").value;

    if (
        fullname === ""
        || username === ""
        || email === ""
        || password === ""
        || confirmPassword === ""
    ) {
        alert("Vui lòng nhập đầy đủ thông tin đăng ký.");
        return false;
    }

    if (password.length < 6) {
        alert("Mật khẩu phải có ít nhất 6 ký tự.");
        return false;
    }

    if (password !== confirmPassword) {
        alert("Mật khẩu xác nhận không khớp.");
        return false;
    }

    return true;
}

function validateStudentForm() {
    const hoTen = document.getElementById("hoTen").value.trim();
    const lop = document.getElementById("lop").value.trim();
    const mssv = document.getElementById("mssv").value.trim();
    const email = document.getElementById("email").value.trim();

    if (
        hoTen === ""
        || lop === ""
        || mssv === ""
        || email === ""
    ) {
        alert("Vui lòng nhập đầy đủ thông tin sinh viên.");
        return false;
    }

    return true;
}

function confirmDelete() {
    return confirm("Bạn có chắc chắn muốn xóa sinh viên này không?");
}