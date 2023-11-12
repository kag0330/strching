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