let wrap = document.querySelector('.wrap');
let select = document.querySelector('.select');
let marks = Array.from(wrap.querySelectorAll('p'));
let impression_num = document.querySelector('#impression_num');
impression_num.textContent = marks.length;
wrap.innerHTML = '';

let ul = document.createElement('ul');
let start = 0;
let length = 20;
let num = Math.ceil(marks.length / 20);
let selectData = marks.slice(start, length);

console.log(marks.length)

let draw = function(data) {
	let html = '';
	data.forEach((item, i) => {
		html += item.outerHTML;
	});
	wrap.innerHTML = html;
}

for(let i = 1; i < num+1; i++) {
	let option = document.createElement('option');
	option.setAttribute('value', i);
	option.textContent = '第' + i + '页';
	select.appendChild(option);
}

select.addEventListener('change', (e) => {
	let start = e.target.value - 1;
	selectData = marks.slice(start*length, (start+1)*length);
	draw(selectData);
});

draw(selectData);

800
20
40



