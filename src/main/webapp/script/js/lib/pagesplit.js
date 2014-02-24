function showPages(name) {
	this.name = name;
	this.page = 1;
	this.pageCount = 1;
	this.argName = 'page';
	this.showTimes = 1;
}

showPages.prototype.getPage = function() {
	var args = location.search;
	var reg = new RegExp('[\?&]?' + this.argName + '=([^&]*)[&$]?', 'gi');
	var chk = args.match(reg);
	this.page = RegExp.$1;
}

showPages.prototype.checkPages = function() {
	if (isNaN(parseInt(this.page))) this.page = 1;
	if (isNaN(parseInt(this.pageCount))) this.pageCount = 1;
	if (this.page < 1) this.page = 1;
	if (this.pageCount < 1) this.pageCount = 1;
	if (this.page > this.pageCount) this.page = this.pageCount;
	this.page = parseInt(this.page);
	this.pageCount = parseInt(this.pageCount);
}

showPages.prototype.createHtml = function() {
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1;
    strHtml += '<span class="number">';
    if (prevPage < 1) {
    	strHtml += '<span title="First Page">首页</span>';
    	strHtml += '<span title="Prev Page">上一页</span>';
    } else {
    	strHtml += '<span title="First Page"><a href="javascript:' +
    	    this.name + '.toPage(1);">首页</a></span>';
    	strHtml += '<span title="Prev Page"><a href="javascript:' +
    	    this.name + '.toPage(' + prevPage + ');">上一页</a></span>';
    }
    if (this.page != 1)
        strHtml += '<span title="Page 1"><a href="javascript:' +
            this.name + '.toPage(1);">[1]</a></span>';
    if (this.page >= 3)
        strHtml += '<span>...</span>';
    if (this.pageCount > this.page + 2) {
    	var endPage = this.page + 2;
    } else {
    	var endPage = this.pageCount;
    }
    for (var i = this.page - 2; i <= endPage; i++) {
    	if (i > 0) {
    		if (i == this.page) {
    			strHtml += '<span title="Page ' + i + '">[' + i + ']</span>';
    		} else {
    			if (i != 1 && i != this.pageCount) {
    				strHtml += '<span title="Page ' + i + '"><a href="javascript:' +
    				    this.name + '.toPage(' + i + ');">[' + i + ']</a></span>';
    			}
    		}
    	}
    }
    if (this.page + 3 < this.pageCount)
        strHtml += '<span>...</span>';
    if (this.page != this.pageCount)
        strHtml += '<span title="Page ' + this.pageCount + '"><a href="javascript:' +
            this.name + '.toPage(' + this.pageCount + ');">[' + this.pageCount + ']</a></span>';

    if (nextPage > this.pageCount) {
    	strHtml += '<span title="Next Page">下一页</span>';
    	strHtml += '<span title="Last Page">尾页</span>';
    } else {
    	strHtml += '<span title="Next Page"><a href="javascript:' +
    	    this.name + '.toPage(' + nextPage + ');">下一页</a></span>';
    	strHtml += '<span title="Last Page"><a href="javascript:' +
    	    this.name + '.toPage(' + this.pageCount + ');">尾页</a></span>';
    }
    strHtml += '</span>';
    return strHtml;
}

showPages.prototype.createUrl = function(page) {
	if (isNaN(parseInt(page))) page = 1;
	if (page < 1) page = 1;
	if (page > this.pageCount) page = this.pageCount;
	var url = location.protocol + '//' + location.host + location.pathname;
	var args = location.search;
	var reg = new RegExp('([\?&]?)' + this.argName + '=[^&]*[&$]?', 'gi');
	args = args.replace(reg,'$1');
	if (args == '' || args == null) {
		args += '?' + this.argName + '=' + page;
	} else if (args.substr(args.length - 1,1) == '?' || args.substr(args.length - 1,1) == '&') {
			args += this.argName + '=' + page;
	} else {
			args += '&' + this.argName + '=' + page;
	}
	return url + args;
}

showPages.prototype.toPage = function(page) {
	var turnTo = 1;
	if (typeof(page) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
	} else {
		turnTo = page;
	}
	self.location.href = this.createUrl(turnTo);
}

showPages.prototype.printHtml = function() {
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	document.write('<div id="pages_' + this.name + '_' + this.showTimes + '" class="pages"></div>');
	document.getElementById('pages_' + this.name + '_' + this.showTimes).innerHTML = this.createHtml();
}

showPages.prototype.formatInputPage = function(e){
	var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
	if(!ie) var key = e.which;
	else var key = event.keyCode;
	if (key == 8 || key == 46 || (key >= 48 && key <= 57)) return true;
	return false;
}