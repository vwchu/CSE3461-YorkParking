/* Load this script using conditional IE comments if you need to support IE 7 and IE 6. */

window.onload = function() {
	function addIcon(el, entity) {
		var html = el.innerHTML;
		el.innerHTML = '<span style="font-family: \'MyIcons\'">' + entity + '</span>' + html;
	}
	var icons = {
			'icon-arrow-left' : '&#xe000;',
			'icon-untitled' : '&#xe001;',
			'icon-erase' : '&#xe002;',
			'icon-arrow-left-2' : '&#xe003;',
			'icon-arrow-right' : '&#xe004;',
			'icon-arrow-up' : '&#xe005;',
			'icon-checkmark-circle' : '&#xe006;',
			'icon-cancel-circle' : '&#xe007;',
			'icon-plus-alt' : '&#xe008;',
			'icon-info' : '&#xe009;',
			'icon-house' : '&#xe00a;',
			'icon-user' : '&#xe00b;',
			'icon-car' : '&#xe00c;',
			'icon-gauge' : '&#xe00d;',
			'icon-envelope' : '&#xe00e;',
			'icon-question' : '&#xe00f;',
			'icon-info-2' : '&#xe010;',
			'icon-pencil' : '&#xe011;',
			'icon-remove' : '&#xe012;',
			'icon-signout' : '&#xe013;',
			'icon-file-add' : '&#xe014;',
			'icon-files' : '&#xe015;',
			'icon-spam' : '&#xe016;',
			'icon-keyboard' : '&#xe017;',
			'icon-key' : '&#xe018;',
			'icon-checkmark' : '&#xe019;',
			'icon-close' : '&#xe01a;',
			'icon-shield' : '&#xe01b;',
			'icon-coin' : '&#xe01c;'
		},
		els = document.getElementsByTagName('*'),
		i, attr, c, el;
	for (i = 0; ; i += 1) {
		el = els[i];
		if(!el) {
			break;
		}
		attr = el.getAttribute('data-icon');
		if (attr) {
			addIcon(el, attr);
		}
		c = el.className;
		c = c.match(/icon-[^\s'"]+/);
		if (c && icons[c[0]]) {
			addIcon(el, icons[c[0]]);
		}
	}
};