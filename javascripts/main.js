'use strict';

var wrap = document.querySelector('.wrap');
var select = document.querySelector('.select');
var marks = Array.prototype.slice.call(wrap.querySelectorAll('p'));
var impression_num = document.querySelector('#impression_num');
impression_num.textContent = marks.length;

var stringArr = [];
marks.forEach(function(item) {
	stringArr.push(JSON.stringify(item.outerHTML));
})

wrap.innerHTML = '';

var ul = document.createElement('ul');
var start = 0;
var length = 20;
var num = Math.ceil(stringArr.length / 20);
var selectData = stringArr.slice(start, length);

var draw = function draw(data) {
	var html = '';
	data.forEach(function (item, i) {
		html += JSON.parse(item);
	});
	wrap.innerHTML = html;
};

for (var i = 1; i < num + 1; i++) {
	var option = document.createElement('option');
	option.setAttribute('value', i);
	option.textContent = '第' + i + '页';
	select.appendChild(option);
}

select.addEventListener('change', function (e) {
	var start = e.target.value - 1;
	selectData = stringArr.slice(start * length, (start + 1) * length);
	draw(selectData);
});

draw(selectData);
