
// import "../page/search-bar";
// import "../page/multiple-select";

function initSortable() {
    const sortableItems = document.querySelectorAll('[data-my-sortable]');
    for (const sortableItem of sortableItems) {
        if (window
            .location
            .search
            .includes(sortableItem.getAttribute('data-my-sortable'))
        ) {
            sortableItem.classList.toggle('active');
        }
    }
}

window.addEventListener('load', () => {
    initSortable();
});