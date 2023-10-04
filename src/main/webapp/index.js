$(document).ready(function () {
    const pageSize = 2;
    let curPage = 0;
    let data, table, sortCol;
    let sortAsc = false;

    document.querySelectorAll('#transaction-table thead tr th').forEach(t => {
        t.addEventListener('click', sort, false);
    });
    document.querySelector('#nextButton').addEventListener('click', nextPage, false);
    document.querySelector('#prevButton').addEventListener('click', previousPage, false);
    // Fetch and display parent data using AJAX
    function fetchParentData() {
        $.ajax({
            url: `/transaction-manager/transaction?page=${curPage}&size=${pageSize}`,
            method: 'GET',
            success: function (response) {
                data = response;
                renderTable();
            },
            error: function (error) {
                console.error(error);
            }
        });
    }

    // Implement pagination and sorting logic here

    // Call fetchParentData to load initial data
    fetchParentData();

    function renderTable() {
        // Select the table (well, tbody)
        let table = document.querySelector('#transaction-table tbody');
        // create html
        let result = '';
        data.forEach(transaction => {
            result += `<tr>
                                  <td>${transaction.id}</td>
                                  <td>${transaction.sender}</td>
                                  <td>${transaction.receiver}</td>
                                  <td>${transaction.totalAmount}</td>
                                  <td><a href="/transaction-manager/payment/${transaction.id}">${transaction.totalPaidAmount}</a></td>
                                  </tr>`;
        });
        table.innerHTML = result;
    }

    function sort(e) {
        let currentOrder = e.target.parentElement.getAttribute("aria-sort");
        if(currentOrder === 'descending') {
            e.target.parentElement.setAttribute("aria-sort",'ascending');
        }
        else {
            e.target.parentElement.setAttribute("aria-sort",'descending');
        }
        let thisSort = e.target.dataset.sort;
        if(sortCol === thisSort) sortAsc = !sortAsc;
        sortCol = thisSort;
        data.sort((a, b) => {
            if(a[sortCol] < b[sortCol]) return sortAsc?1:-1;
            if(a[sortCol] > b[sortCol]) return sortAsc?-1:1;
            return 0;
        });
        renderTable();
    }

    function previousPage() {
        if(curPage > 0) {
            --curPage;
            fetchParentData();
        }
    }

    function nextPage() {
        if(data.length == pageSize) {
            ++curPage;
            fetchParentData();
        }
    }
});