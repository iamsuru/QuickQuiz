$(document).ready(function () {
    function checkPasswordEquality() {
        var password = $('#password').val();
        var confirmPassword = $('#confirm_password').val();

        if (password !== confirmPassword) {
            // Passwords do not match, add red border to confirm password field
            $('#confirm_password').css('border', '2px solid red');
        } else {
            // Passwords match, remove red border from confirm password field
            $('#confirm_password').css('border', '2px solid green');
        }
    }
    $('#confirm_password').on('input', checkPasswordEquality);

    $('#registrationForm').submit(function (event) {
        event.preventDefault();
        let formData = $(this).serialize();
        $.ajax({
            type: 'POST',
            url: "RegisterServlet",
            data: formData,
            success: function (data, textStatus, jqXHR) {
                if (jqXHR.status === 200) {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        showConfirmButton: false,
                        text: data,
                        timer: 3000,
                        didOpen: () => {
                            timerInterval = setInterval(() => {
                            }, 100);
                        },
                        willClose: () => {
                            clearInterval(timerInterval);
                        }
                    }).then((result) => {
                        if (result.dismiss === Swal.DismissReason.timer) {
                            window.location = "/QuickQuiz";
                        }
                    })
                } else {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "error",
                        showConfirmButton: false,
                        text: data,
                        timer: 3000,
                        didOpen: () => {
                            timerInterval = setInterval(() => {

                            }, 100);
                        },
                        willClose: () => {
                            clearInterval(timerInterval);
                        }
                    })
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 11000) {
                    if (jqXHR.responseText === "email") {
                        $('#email').css('border', '2px solid red');
                        $('#emailDuplicateLabel').css('display', 'flex')
                    } else {
                        $('#email').css('border', '');
                        $('#emailDuplicateLabel').css('display', 'none')
                    }
                    if (jqXHR.responseText === "username") {
                        $('#username').css('border', '2px solid red');
                        $('#usernameDuplicateLabel').css('display', 'flex')
                    } else {
                        $('#username').css('border', '');
                        $('#usernameDuplicateLabel').css('display', 'none')
                    }
                } else if (jqXHR.status === 400) {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "error",
                        showConfirmButton: false,
                        text: jqXHR.responseText,
                        timer: 3000,
                        didOpen: () => {
                            timerInterval = setInterval(() => {

                            }, 100);
                        },
                        willClose: () => {
                            clearInterval(timerInterval);
                        }
                    })
                } else if (jqXHR.status === 500) {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "error",
                        showConfirmButton: false,
                        text: jqXHR.responseText,
                        timer: 3000,
                        didOpen: () => {
                            timerInterval = setInterval(() => {
                            }, 100);
                        },
                        willClose: () => {
                            clearInterval(timerInterval);
                        }
                    })
                }
            }
        })
    })
})