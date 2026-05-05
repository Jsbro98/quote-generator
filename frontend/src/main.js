const quoteHolder = document.querySelector('.quote-box');
const requestButton = document.querySelector('.request-quote-button');

let isAnimating = false;
requestButton.addEventListener('click', (e) => {
    if (isAnimating) return;

    isAnimating = true;
    getSavedQuote()
        .then(quote => {
            quoteHolder.classList.remove('show');

            setTimeout(() => {
                quoteHolder.innerHTML = quote.h;
                quoteHolder.classList.add('show');

                if (!quoteHolder.classList.contains('adjust-height')) {
                    quoteHolder.classList.add('adjust-height');
                }

                isAnimating = false;
            }, 750)
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