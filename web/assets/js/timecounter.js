function countdown() {
    var i = document.getElementById('time');
    if (parseInt(i.innerHTML) <= 0) {
        location.href = 'HomePage';
    }
    if (parseInt(i.innerHTML) != 0) {
        i.innerHTML = parseInt(i.innerHTML) - 1;
    }
}
setInterval(function () {
    countdown();
}, 1000);

