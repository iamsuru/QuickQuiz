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
jQuery.onload = () => {
    console.log('on')
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
        let categoryChoice = $(this).text();
        console.log(categoryChoice);
        $.ajax({
            url: "FetchCategoryFromDatabase",
            type: 'POST',
            data: {categoryChoice: categoryChoice},
            success: function (response) {
                dataSet = response;
                displayQuestion(response[getIndex()]);
            },
            error: function (error) {
                console.error(error);
            }
        })

        info_box.addClass('activeInfo');
        category.css('display', 'none')
        main_display.css('display', 'flex');

    });


    exitBtn.click(() => {
        info_box.removeClass('activeInfo');
        category.css('display', 'flex')
        main_display.css('display', 'none');
    })


    hideRuleBoxBtn.click(() => {
        info_box.removeClass('activeInfo');
        startTimer();
    })

    btnToHomePanel.click(() => {
        window.location.href = 'home-panel'
    })
}

startTimer = () => {
    let timeLeft = 11;
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
            setTimeout(showScore, 3000);
        }
    }, 1000);
}

showScore = () => {
    showResult.addClass('activeInfo');
    main_display.css('display', 'none');
    console.log('final score ', score);
    $('#currentScore').text(score);
}

function getIndex() {
    let usedIndex = [];
    let idx;
    do {
        idx = Math.floor(Math.random() * 10);
    } while (usedIndex.includes(idx));

    usedIndex.push(idx);
    console.log(usedIndex);
    return idx;
}

function displayQuestion(questionData) {
    $('#question').text(questionData.question);

    for (let i = 0; i < questionData.choices.length && i < 4; i++) {
        $(`#btn${i}`).text(questionData.choices[i]);
        // Attach a click event to each button
        $(`#btn${i}`).off('click').on('click', function () {
            let selectedAnswer = $(this).text();
            let selectedButton = $(this);
            checkCorrectAndUpdate(selectedAnswer, questionData.correct_answer, selectedButton);
        });

        $('#progress').text(`Question ${current_ques + 1} of ${total_ques}`);
    }
}

function checkCorrectAndUpdate(selectedAnswer, correct_answer, selectedButton) {
    current_ques = current_ques + 1;

    if (selectedAnswer === correct_answer) {
        selectedButton.css('background-color', 'green');
        score = score + 10;
        console.log(score);
    } else {
        selectedButton.css('background-color', 'red');
        console.log(score);
    }

    if (current_ques < total_ques) {
        setTimeout(function () {
            selectedButton.css('background-color', '#3a90df');
            displayQuestion(dataSet[getIndex()]);
        }, 1000);
    } else {
        $('.options-btn').prop('disabled', true);
        clearInterval(timer);
        setTimeout(function () {
            selectedButton.css('background-color', '#3a90df');
            showScore();
        }, 1000);
    }
}
