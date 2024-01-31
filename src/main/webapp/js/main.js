
// import "../page/search-bar";
// import "page/multiple-select";
// import "lib/bootstrap/bootstrap";
// import "lib/bootstrap/bootstrap.bundle";
// import "lib/bootstrap/bootstrap.esm";

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
    console.log("test")
    initSortable();
    initFilter();
    initSortableSelect();
});

function initSortableSelect() {
    const selects = document.querySelectorAll('.sortable-select')
    selects.forEach((select) => {
        select.addEventListener('change', () => {
            const selectedOption = select.querySelector('option:checked');
            if (selectedOption) {
                window.location.href = selectedOption.getAttribute('data-filter-url');
            }
        });
        const options = select.querySelectorAll('option');
        options.forEach((option) => {
            if (window.location.search.includes(option.value)) {
                option.setAttribute('selected', 'selected');
            }
        });
    });
}

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