$("#pagination")
    .append("<div id=\"pages\" style=\"margin-top: 10px;\">")
    .append("<span id=\"spanFirst\">&nbsp;首页&nbsp;</span>")
    .append("<span id=\"spanPre\">&nbsp;上一页&nbsp;</span>")
    .append("<span id=\"spanNext\">&nbsp;下一页&nbsp;</span>")
    .append("<span id=\"spanLast\">&nbsp;尾页&nbsp;</span>")
    .append("&nbsp;第&nbsp;<strong id=\"spanPageNum\"></strong>&nbsp;页&nbsp;/&nbsp;")
    .append("&nbsp;共&nbsp;<strong id=\"spanTotalPage\"></strong>&nbsp;页&nbsp;");

var theUL = document.getElementById("paginationContent");
var totalPage = document.getElementById("spanTotalPage");
var pageNum = document.getElementById("spanPageNum"); //获取当前页<span>
var spanPre = document.getElementById("spanPre"); //获取上一页<span>
var spanNext = document.getElementById("spanNext"); //获取下一页<span>
var spanFirst = document.getElementById("spanFirst"); //获取第一页<span>
var spanLast = document.getElementById("spanLast"); //获取最后一页<span>
var numberRowsInTable = theUL.getElementsByTagName("tr").length; //记录总条数
var pageSize = 10; //每页显示的记录条数
var page = 1; //当前页，默认第一页

//下一页
function next() {
    hideTable();
    currentRow = pageSize * page;
    maxRow = currentRow + pageSize;
    if (maxRow > numberRowsInTable) maxRow = numberRowsInTable;
    for (var i = currentRow; i < maxRow; i++) {
        try {
            $("#paginationContent tr:eq(" + i + ")").attr("style", "display:");
        } catch (err) {
            theUL.getElementsByTagName("tr")[i].style.display = '';
        }
    }
    page++;
    if (maxRow == numberRowsInTable) {
        nextText();
        lastText();
    }
    showPage();
    preLink();
    firstLink();
}


//上一页
function pre() {
    hideTable();
    page--;
    currentRow = pageSize * page;
    maxRow = currentRow - pageSize;
    if (currentRow > numberRowsInTable) currentRow = numberRowsInTable;
    for (var i = maxRow; i < currentRow; i++) {
        try {
            $("#paginationContent tr:eq(" + i + ")").attr("style", "display:");
        } catch (err) {
            theUL.getElementsByTagName("tr")[i].style.display = '';
        }
    }
    if (maxRow == 0) {
        preText();
        firstText();
    }
    showPage();
    nextLink();
    lastLink();
}


//第一页
function first() {
    hideTable();
    page = 1;
    for (var i = 0; i < pageSize; i++) {
        try {
            $("#paginationContent tr:eq(" + i + ")").attr("style", "display:");
        } catch (err) {
            theUL.getElementsByTagName("tr")[i].style.display = '';
        }
    }
    showPage();
    firstText();
    preText();
    nextLink();
    lastLink();
}


//最后一页
function last() {
    hideTable();
    page = pageCount();
    currentRow = pageSize * (page - 1);
    for (var i = currentRow; i < numberRowsInTable; i++) {
        try {
            $("#paginationContent tr:eq(" + i + ")").attr("style", "display:");
        } catch (err) {
            theUL.getElementsByTagName("tr")[i].style.display = '';
        }
    }
    showPage();
    preLink();
    nextText();
    firstLink();
    lastText();
}


function hideTable() {
    $("#paginationContent tr").attr("style", "display:none;");
    for (var i = 0; i < numberRowsInTable; i++) {
        try {
            $("#paginationContent tr:eq(" + i + ")").attr("style", "display:none;");
        } catch (err) {
            theUL.getElementsByTagName("tr")[i].style.display = 'none';
        }
    }
}


function showPage() {
    pageNum.innerHTML = page;
}


//总共页数
function pageCount() {
    return Math.ceil(numberRowsInTable / pageSize);
}
//显示链接
function preLink() {
    spanPre.innerHTML = "<a href='javascript:pre();'>&nbsp;上一页&nbsp;</a>";
}
function preText() {
    spanPre.innerHTML = "&nbsp;上一页&nbsp;";
}
function nextLink() {
    spanNext.innerHTML = "<a href='javascript:next();'>&nbsp;下一页&nbsp;</a>";
}
function nextText() {
    spanNext.innerHTML = "&nbsp;下一页&nbsp;";
}
function firstLink() {
    spanFirst.innerHTML = "<a href='javascript:first();'>&nbsp;首页&nbsp;</a>";
}
function firstText() {
    spanFirst.innerHTML = "&nbsp;首页&nbsp;";
}
function lastLink() {
    spanLast.innerHTML = "<a href='javascript:last();'>&nbsp;尾页&nbsp;</a>";
}
function lastText() {
    spanLast.innerHTML = "&nbsp;尾页&nbsp;";
}


//隐藏
function hide() {
    for (var i = pageSize; i < numberRowsInTable; i++) {
        try {
            $("#paginationContent tr:eq(" + i + ")").attr("style", "display:none;");
        } catch (err) {
            theUL.getElementsByTagName("tr")[i].style.display = 'none';
        }
    }
    totalPage.innerHTML = pageCount();
    pageNum.innerHTML = '1';
    var index = parseInt(totalPage.innerHTML);
    if (index == 0)
        pageNum.innerHTML = '0';

    if (parseInt(page) >= index) {
        nextText();
        lastText();
    } else {
        nextLink();
        lastLink();
    }
    $("#paginationContent").show();
}
hide();