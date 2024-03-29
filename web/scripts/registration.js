const jQuery = document.createElement('script');
jQuery.src = "https://code.jquery.com/jquery-3.7.1.min.js";
document.head.appendChild(jQuery);

$(document).ready(function () {
    $('#fullScreenLoader').css('display', 'none');
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
        $('#paragraph').text("");
        $('#fullScreenLoader').css('display', 'flex');
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
                    sweetAlerts("error", data);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#fullScreenLoader').css('display', 'none');
                if (jqXHR.status === 11000) {
                    if (jqXHR.responseText === "email") {
                        showError("email");
                    } else {
                        hideError("email");
                    }
                    if (jqXHR.responseText === "username") {
                        showError("username");
                    } else {
                        hideError("username");
                    }
                } else if (jqXHR.status === 400) {
                    sweetAlerts("error", jqXHR.responseText);
                } else if (jqXHR.status === 500) {
                    sweetAlerts("error", jqXHR.responseText);
                }
            }
        })
    })
})


function showError(inputType) {
    $(`#${inputType}`).css('border', '2px solid red');
    $(`#${inputType}DuplicateLabel`).css('display', 'flex');
}

function  hideError(inputType) {
    $(`#${inputType}`).css('border', '');
    $(`#${inputType}DuplicateLabel`).css('display', 'none')
}

function sweetAlerts(icon, msg) {
    let timerInterval;
    Swal.fire({
        position: "center",
        icon: icon,
        showConfirmButton: false,
        text: msg,
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