const jQuery = document.createElement('script');
jQuery.src = "https://code.jquery.com/jquery-3.7.1.min.js";
document.head.appendChild(jQuery);

$(document).ready(function () {
    $('#fullScreenLoader').css('display', 'none');
    $('input').attr('autocomplete', 'off');
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

    $('#forgotPasswordForm').submit(function (event) {
        event.preventDefault();
        $('#fullScreenLoader').css('display', 'flex');
        let formData = $(this).serialize();
        $.ajax({
            type: 'POST',
            url: "ForgotPasswordServlet",
            data: formData,
            success: function (data, textStatus, jqXHR) {
                $('#fullScreenLoader').css('display', 'none');
                if (jqXHR.status === 200) {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        showConfirmButton: false,
                        text: data,
                        timer: 5000,
                        didOpen: () => {
                            timerInterval = setInterval(() => {
                            }, 100);
                        },
                        willClose: () => {
                            clearInterval(timerInterval);
                        }
                    }).then((result) => {
                        if (result.dismiss === Swal.DismissReason.timer) {
                            $('#paragraph').text("Redirecting...");
                            $('#fullScreenLoader').css('display', 'flex');
                            setTimeout(function () {
                                $('#fullScreenLoader').css('display', 'none');
                                $('#paragraph').text("");
                                window.location = "/QuickQuiz";
                            }, 3000);
                        }
                    })
                } else {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "error",
                        showConfirmButton: false,
                        text: data,
                        timer: 5000,
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
                $('#fullScreenLoader').css('display', 'none');
                if (jqXHR.status === 400) {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "error",
                        showConfirmButton: false,
                        text: jqXHR.responseText,
                        timer: 5000,
                        didOpen: () => {
                            timerInterval = setInterval(() => {

                            }, 100);
                        },
                        willClose: () => {
                            clearInterval(timerInterval);
                        }
                    })
                } else if (jqXHR.status === 404) {
                    let timerInterval;
                    Swal.fire({
                        position: "center",
                        icon: "warning",
                        showConfirmButton: false,
                        text: jqXHR.responseText,
                        timer: 5000,
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
                        timer: 5000,
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