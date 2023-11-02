$(document).ready(function() {
	$("#scrape_button").click(function() {
		const search_query = $("#search_query").val();
		const max_items = $("#max_items").val();

		const titleDiv = $('<div></div>').text(`Title: ${search_query}`);
		const progressBar = $('<div></div>').text('진행중...');

		$("#results").append(titleDiv).append(progressBar);

		$.ajax({
			url: 'https://flask.kag0330.duckdns.org/cu',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ 'search_query': search_query, 'max_items': max_items }),
			success: function(response) {
				progressBar.text('완료');
				const dropdown = $('<select></select>');
				response.data.forEach(video => {
					dropdown.append(new Option(video.title, video.url));
				});
				titleDiv.append(dropdown);
			},
			error: function() {
				progressBar.text('오류 발생');
			}
		});
	});

	$(".file-item").click(function() {
		loadFileData($(this));
	});
});

function loadFileData($element) {
	const filename = $element.data("filename");

	fetch('/json/' + filename)
		.then(response => response.json())
		.then(data => {
			const $tableBody = $("#dataTable tbody");
			$tableBody.empty();

			data.forEach(item => {
				let row = $("<tr>");
				for (let key in item) {
					if (item.hasOwnProperty(key)) {
						let cell;
						switch (key) {
							case 'url':
								cell = `<a href="${item[key]}">${item[key]}</a>`;
								break;
							case 'iframe_url':
								cell = `<iframe width="300" src="${item[key]}"></iframe>`;
								break;
							case 'img_url':
								cell = `<img src="${item[key]}" width="300">`;
								break;
							default:
								cell = item[key];
						}
						row.append($("<td>").html(cell));
					}
				}
				row.append('<td><select><option value="a"></option></td>');
				row.append('<td><input type="checkbox" name="dbupload"></td>');
				$tableBody.append(row);
			});
		});
}

function submitSelectedData() {
	let selectedItems = [];

	$("#dataTable tbody tr").each(function() {
		const $row = $(this);
		const isChecked = $row.find('input[name="dbupload"]').is(':checked');

		if (isChecked) {
			let rowData = {};
			$row.find('td').each(function(index, cell) {
				switch (index) {
					case 0:
						rowData.seq = $(cell).text();
						break;
					case 1:
						rowData.title = $(cell).text();
						break;
					case 2:
						rowData.url = $(cell).find('a').attr('href');
						break;
					case 3:
						rowData.iframe_url = $(cell).find('iframe').attr('src');
						break;
					case 4:
						rowData.img_url = $(cell).find('img').attr('src');
						break;
					case 5:
						rowData.uploader = $(cell).text();
						break;
					case 6:
						rowData.type = $(cell).text();
						break;
					case 7:
						rowData.DBUpload = $(cell).find('select').val();
						break;
					case 8:
						break;
					default:
						break;
				}
			});
			selectedItems.push(rowData);
		}
	});

	// 서버에 데이터 전송
	$.ajax({
		url: '/admin/youtubeDBUpload_ok', // API 엔드포인트
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(selectedItems),
		success: function(response) {
			alert("Data uploaded successfully!");
			location.replace('/')
		},
		error: function() {
			alert("Error occurred while uploading data.");
		}
	});
}