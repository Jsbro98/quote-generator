const quoteHolder = document.querySelector('.main-quote');
const requestButton = document.querySelector('.request-quote-button');

requestButton.addEventListener('click', (e) => {
    fetch('/quotes/random')
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(err => console.error(err));
})