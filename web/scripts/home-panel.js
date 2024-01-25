const jQuery = document.createElement('script');
jQuery.src = "https://code.jquery.com/jquery-3.7.1.min.js";
document.head.appendChild(jQuery);

let timer;
let showResult;
let main_display;

let dataSet;

let updateIdx;
let score = 0;
let current_ques = 0;
let total_ques = 5;

let previousScore = Number($('#previousScore').text());
let highestScore = Number($('#highestScore').text());
let username = $('#currentUsername').text();

jQuery.onload = () => {
    $('#fullScreenLoader').css('display', 'none');
    $('#paragraph').text("");
    //info box elements
    const info_box = $('.info_box')
    const exitBtn = $('#exitBtn')
    const hideRuleBoxBtn = $('#hideRuleBoxBtn');

    //category elements
    const category = $('.category')
    const btnn = $('.btnn')

    //main display element
    main_display = $('.main-display')

    //score table
    const btnToHomePanel = $('#btnToHomePanel')
    showResult = $('.result_box')

    //show rule box and hide category tab



    btnn.click(function () {
        $('#paragraph').text("Creating Quiz Ready...").append("<br>Please Wait")
        $('#fullScreenLoader').css('display', 'flex');
        let categoryChoice = $(this).text();
        $.ajax({
            url: "FetchCategoryFromDatabaseServlet",
            type: 'POST',
            data: {categoryChoice: categoryChoice},
            success: function (response) {
                setTimeout(function () {
                    info_box.addClass('activeInfo');
                    category.css('display', 'none')
                    main_display.css('display', 'flex');
                    $('#fullScreenLoader').css('display', 'none');
                    $('#paragraph').text("");
                }, 5000);
                dataSet = response;
                displayQuestion(response[getIndex()]);
            },
            error: function (error) {
                $('#fullScreenLoader').css('display', 'none');
                $('#paragraph').text("");
                console.error(error);
            }
        })

    });


    exitBtn.click(() => {
        $('#fullScreenLoader').css('display', 'flex');
        $('#paragraph').text('Please Wait.').append("<br>Redirecting Back...");
        setTimeout(function () {
            $('#fullScreenLoader').css('display', 'none');
            $('#paragraph').text("");
            info_box.removeClass('activeInfo');
            category.css('display', 'flex')
            main_display.css('display', 'none');
        }, 2000);

    })


    hideRuleBoxBtn.click(() => {
        $('#fullScreenLoader').css('display', 'flex');
        $('#paragraph').text('Please Wait.');
        setTimeout(function () {
            $('#fullScreenLoader').css('display', 'none');
            $('#paragraph').text("");
            info_box.removeClass('activeInfo');
            startTimer();
        }, 2000);

    })

    btnToHomePanel.click(() => {
        updateScore(score, previousScore, highestScore, username);
    })
}

startTimer = () => {
    let timeLeft = 30;
    $('#countDown').text(`Timer - ${timeLeft}s remaining`)

    timer = setInterval(() => {
        timeLeft--;
        if (timeLeft <= 10) {
            $('#countDown').css('color', 'red')
        }
        if (timeLeft >= 10 && timeLeft <= 20) {
            $('#countDown').css('color', '#E9950C')
        }
        if (timeLeft >= 0) {
            $('#countDown').text(`Timer - ${timeLeft}s remaining`)
        } else {
            clearInterval(timer)
            $('#countDown').html(`Time's Up!<br>Wait for the result`)
            $('.options-btn').prop('disabled', true);
            setTimeout(function () {
                showScore();
            }, 1000);
        }
    }, 1000);
}

showScore = () => {
    if (score > highestScore) {
        $('#sup').css('display', 'flex');
        $('#highestScore').text(score);
    }
//    updateScore(score, previousScore, highestScore, username);
    showResult.addClass('activeInfo');
    main_display.css('display', 'none');
    $('#currentScore').text(score);
}

function getIndex() {
    let usedIndex = [];
    let idx;
    do {
        idx = Math.floor(Math.random() * 10);
    } while (usedIndex.includes(idx));

    usedIndex.push(idx);
    return idx;
}

function displayQuestion(questionData) {
    $('#question').text(questionData.question);

    let shuffledChoices = [...questionData.choices];
    shuffleChoices(shuffledChoices);

    for (let i = 0; i < shuffledChoices.length && i < 4; i++) {
        $(`#btn${i}`).text(shuffledChoices[i]);
        // Attach a click event to each button
        $(`#btn${i}`).off('click').on('click', function () {
            let selectedAnswer = $(this).text();
            let selectedButton = $(this);
            checkCorrectAndUpdate(selectedAnswer, questionData.correct_answer, selectedButton);
        });

        $('#progress').text(`Question ${current_ques + 1} of ${total_ques}`);
    }
}

function shuffleChoices(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function checkCorrectAndUpdate(selectedAnswer, correct_answer, selectedButton) {
    current_ques = current_ques + 1;

    if (selectedAnswer === correct_answer) {
        selectedButton.css('background-color', 'green');
        score = score + 10;
    } else {
        selectedButton.css('background-color', 'red');
    }

    if (current_ques < total_ques) {
        setTimeout(function () {
            selectedButton.css('background-color', '#3a90df');
            displayQuestion(dataSet[getIndex()]);
        }, 500);
    } else {
        $('.options-btn').prop('disabled', true);
        clearInterval(timer);
        setTimeout(function () {
            selectedButton.css('background-color', '#3a90df');
            showScore();
        }, 500);
    }
}

function updateScore(score, previousScore, highestScore, username) {
    $('#fullScreenLoader').css('display', 'flex');
    $('#paragraph').text('Updating Score').append('<br>Have Patience...');
    previousScore = score;
    var formData = new FormData();
    formData.append('score', score);
    formData.append('previousScore', previousScore);
    formData.append('highestScore', highestScore);
    formData.append('username', username)
    $.ajax({
        url: "UpdateScoreServlet",
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function (response) {
            setTimeout(function () {
                $('#fullScreenLoader').css('display', 'none');
                $('#paragraph').text("");
                window.location = 'home-panel'
            }, 5000);
        },
        error: function (error) {
            $('#fullScreenLoader').css('display', 'none');
            $('#paragraph').text("");
            let timerInterval;
            Swal.fire({
                position: "center",
                icon: "error",
                showConfirmButton: false,
                text: error,
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
    })
}