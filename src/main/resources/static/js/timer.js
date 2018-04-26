var isBreak = false;
var isWork = false;

function init() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/getname", true);
    function process() {
        if (request.readyState === 4 && request.status === 200) {
            var greeting = document.getElementById("greeting");
            greeting.innerHTML = "Учет рабочего времени пользователя - " + request.responseText;
        }

    }
    request.onreadystatechange = process;
    request.send(null);
    checkIsBreak();
    checkIsWork();
    setComeTime();
}

function checkIsWork() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/isonwork", true);
    function process() {
        isWork = (request.responseText == 'true');
        changeStatus();
    }
    request.onreadystatechange = process;
    request.send(null);
}

function checkIsBreak() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/isonbreak", true);
    function process() {
        isBreak = (request.responseText == 'true');
        changeStatus();
    }
    request.onreadystatechange = process;
    request.send(null);
}

function come() {
    if (!isWork) {
        comeToJob();
    } else {
        if (isBreak) {
            comeFromBreak();
        }
    }
}

function comeToJob() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/beginwork", true);
    function process() {
        if (request.readyState === 4 && request.status === 200) {
            isWork = (request.responseText == 'true');
            changeStatus();
            setComeTime();
        }
    }
    request.onreadystatechange = process;
    request.send(null);
    var downtime = document.getElementById("downtime");
    downtime.innerHTML = "";
    var worktime = document.getElementById("worktime");
    worktime.innerHTML = "";
}

function comeFromBreak() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/endbreak", true);
    function process() {
        if (request.readyState === 4 && request.status === 200) {
            isBreak = !(request.responseText == 'true');
            changeStatus();
            setTimeBreak();
        }
    }
    request.onreadystatechange = process;
    request.send(null);
}

function setTimeBreak() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/downtime", true);
    function process() {
        if (request.readyState === 4 && request.status === 200) {
            var downtime = document.getElementById("downtime");
            var time = +request.responseText / 1000;
            downtime.innerHTML = "Общее время простоя - " + Math.round(time) + " секунд";
        }
    }
    request.onreadystatechange = process;
    request.send(null);
}

function changeStatus() {
    var status = document.getElementById("status");
    if (isWork) {
        if (!isBreak) {
            status.innerHTML = "На работе";
        } else {
            status.innerHTML = "Отсутствует";
        }
    } else {
        status.innerHTML = "Ушел с работы";
    }
}

function setComeTime() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/timebeginwork", true);
    function process() {
        if (request.readyState === 4 && request.status === 200) {
            var comeTime = document.getElementById("comeTime");
            var time = +request.responseText;
            if (time != 0) {
                var date = new Date(time);
                comeTime.innerHTML = "Время прихода - " + date.toString();
            }
        }
    }
    request.onreadystatechange = process;
    request.send(null);
}

function goAway() {
    if (isWork && !isBreak) {
        var request = new XMLHttpRequest();
        request.open("GET", "timer/beginbreak", true);
        function process() {
            if (request.readyState === 4 && request.status === 200) {
                isBreak = (request.responseText == 'true')
                changeStatus();
            }
        }
        request.onreadystatechange = process;
        request.send(null);
    }
}

function goHome() {
    if (isWork) {
        var request = new XMLHttpRequest();
        request.open("GET", "timer/endwork", true);
        function process() {
            if (request.readyState === 4 && request.status === 200) {
                isWork = !(request.responseText == 'true')
                if (isWork) {
                    isBreak = false;
                }
                changeStatus();
                setWorkTime();
            }
        }
        request.onreadystatechange = process;
        request.send(null);
    }
}

function setWorkTime() {
    var request = new XMLHttpRequest();
    request.open("GET", "timer/worktime", true);
    function process() {
        if (request.readyState === 4 && request.status === 200) {
            var goHomeTime = document.getElementById("worktime");
            var time = +request.responseText / 1000;
            goHomeTime.innerHTML = "Общее время работы - " + Math.round(time) + " секунд за день";
        }
    }
    request.onreadystatechange = process;
    request.send(null);
}


window.onload = init;