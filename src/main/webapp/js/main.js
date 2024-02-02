
// import "../page/search-bar";
// import "page/multiple-select";
// import "lib/bootstrap/bootstrap";
// import "lib/bootstrap/bootstrap.bundle";
// import "lib/bootstrap/bootstrap.esm";

function initDropDown() {
    const dropdowns = document.querySelectorAll('.dropdown-toggle');
    const pray = document.querySelectorAll('.pray');
    for (const dropdown of dropdowns) {
        if(dropdown.matches(':hover')) {
            console.log("test")
            dropdown.classList.toggle('show')
            console.log(dropdown)
            if (dropdown.hasChildNodes()) {
                const children = dropdown.childNodes;

                for (const child of children) {
                    child.classList.toggle('show')
                }
            }
        }
    }
}

function initSortable() {
    const sortableItems = document.getElementsByTagName('a');
    const pray = document.getElementsByTagName('a');
    const pray3 = document.querySelectorAll('.game-card');
    for (const sortableItem of sortableItems) {
        if(sortableItem.matches(':hover')) {
            console.log("test")
            for (const pray2 of pray3) {
                pray2.classList.toggle('d-none')
                console.log(pray2)
            }
        }
        if (window
            .location
            .search
            .includes(sortableItem.getAttribute('data-my-sortable'))
        ) {
            sortableItem.classList.toggle('active');
        }
    }
}

window.addEventListener('mouseover', () => {
    // initSortable();
    initDropDown();
    initFilter();
    initSortableSelect();
    // for (const sortableItem of sortableItems) {
    // }
});
window.addEventListener('mouseout', () => {
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