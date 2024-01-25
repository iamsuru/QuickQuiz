const jQuery = document.createElement('script');
jQuery.src = "https://code.jquery.com/jquery-3.7.1.min.js";
document.head.appendChild(jQuery);

$(document).ready(function () {
    $('#fullScreenLoader').css('display', 'none');
    $('#LoginForm').submit(function (event) {
        event.preventDefault();
        $('#paragraph').text("");
        $('#fullScreenLoader').css('display', 'flex');

        var formData = $(this).serialize(); // Serialize form data
        $.ajax({
            type: 'POST',
            url: 'LoginServlet',
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
                                window.location = "home-panel";
                            }, 1000);
                        }
                    })
                } else {
                    sweetAlerts("error", data);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#fullScreenLoader').css('display', 'none');
                if (jqXHR.status === 401) {
                    sweetAlerts("error", jqXHR.responseText);
                } else if (jqXHR.status === 404) {
                    sweetAlerts("warning", jqXHR.responseText);
                } else if (jqXHR.status === 500) {
                    sweetAlerts("error", jqXHR.responseText);
                }
            }
        });
    });
});

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