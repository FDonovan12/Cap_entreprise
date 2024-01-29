
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
    initFilter();
});

function initFilter() {
    const select = document.querySelector("[data-multiple-select]");
    if (select) {
        if (input) {
            select.addEventListener('change', () => {
                for (const option of select.options) {
                    if (option.selected) {
                        launchSearch(option);
                    }
                }
                // input.value = "";
                // for (const opt of opts) {
                //     input.value += opt.text + " | ";
                // }
            });
        }
    }
}

function launchSearch(value) {
    if (value.trim()) {
        location.href = "/review/game=" + value;
    }
}