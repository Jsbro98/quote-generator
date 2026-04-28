const quoteHolder = document.querySelector('.quote-box');
const requestButton = document.querySelector('.request-quote-button');

requestButton.addEventListener('click', (e) => {
    getSavedQuote()
        .then(quote => {
            console.log({quote});
            quoteHolder.innerHTML = quote.h;
        })
})

function getRandomQuote() {
    return fetch('/quotes/random')
        .then(res => res.json())
        .catch(err => console.error(err));
}

function getSavedQuote() {
    return fetch('quotes/single-quote')
        .then(res => res.json())
        .catch(err => console.error(err));
}