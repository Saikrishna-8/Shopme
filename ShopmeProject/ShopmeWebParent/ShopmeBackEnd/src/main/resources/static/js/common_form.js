$(document)
	.ready(
		function() {
			$("#fileImage")
				.change(
					function() {

						fileSize = this.files[0].size;
						file = this.files[0];
						str = file.name;
						//alert("File Name is "+str+"  file name length "+str.length+"  "+fileSize);
						if (fileSize > 1048576
							|| str.length > 127) {

							this
								.setCustomValidity("File Size  Should be less than 1Mb OR File Name Should be less than 127 Charecters.");
							this.reportValidity();
						} else {
							this
								.setCustomValidity("");
							showImageThumbNail(this);
						}

					})

		});

function showImageThumbNail(fileInput) {
	var file = fileInput.files[0];
	var reader = new FileReader();

	reader.onload = function(e) {
		$("#thumbnail").attr("src", e.target.result);
	}

	reader.readAsDataURL(file);
}


function showModalDialog(title, message) {

	$("#modalTitle").text(title);
	$("#modalBody").text(message);
	$("#modalDialog").modal();
}