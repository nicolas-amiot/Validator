$(document).ready(function() {
	
	$("[name]").not(":disabled").not("[readonly]").blur(function() {
		var form = $(this).parents("form[data-validation]");
		var element = $(this);
		if(form.length != 0) {
			$.ajax({
				type: "GET",
				url: form.data("validation"),
				data: {field: $(element).attr("name"), value: $(element).val()},
				success: function (error) {
					if(error == "") {
						$(element).addClass("is-valid");
						$(element).removeClass("is-invalid");
						$(element).siblings(".invalid-feedback").text("");
					} else {
						/* Without Jackson */
						if(typeof error == "string") {
							error = JSON.parse(error);
						}
						$(element).removeClass("is-valid");
						$(element).addClass("is-invalid");
						$(element).siblings(".invalid-feedback").text(error.message);
					}
				}
			});
		}
	});
	
	$("form[data-validation]").submit(function(event) {
		form = $(this);
		var data = {};
		$("[name]").each(function() {
			unflatten(data, $(this).attr("name"), $(this).val());
		});
		$.ajax({
			type: "POST",
			url: form.data("validation"),
			data: JSON.stringify(data),
			async: false,
			contentType : "application/json",
			dataType : 'json',
			success: function (errors) {
				/* Without Jackson */
				if(errors != "" && typeof errors == "string") {
					errors = JSON.parse(errors);
				}
				if(errors.length != 0) {
					event.preventDefault();
					$("[name]").each(function() {
						$(this).addClass("is-valid");
						$(this).removeClass("is-invalid");
						$(this).siblings(".invalid-feedback").text("");
					});
					errors.forEach((error) => {
						var element = $(form).find('[name="' + error.field + '"]');
						$(element).removeClass("is-valid");
						$(element).addClass("is-invalid");
						$(element).siblings(".invalid-feedback").text(error.message);
					});
				}
			}
		});
	});
	
	unflatten = function(object, flatten, value) {
		var attributes = flatten.split('.');
		var regex = /\[([0-9]+)\]/g;
		var temp = object;
		for (index in attributes) {
			if(temp != undefined) {
				var attribute = attributes[index];
				var array = regex.exec(attribute);
				if(array) {
					attribute = attribute.replace(regex, "");
					if(temp[attribute] == undefined) {
						temp[attribute] = [];
					}
					temp = temp[attribute];
				}
				if(index == attributes.length - 1) {
					if(array == null) {
						temp[attribute] = value;
					} else {
						temp[array[1]] = value;
					}
				} else {
					if(array == null) {
						if(temp[attribute] == undefined) {
							temp[attribute] = {};
						}
						temp = temp[attribute];
					} else {
						if(temp[array[1]] == undefined) {
							temp[array[1]] = {};
						}
						temp = temp[array[1]];
					}
				}
			}
		}
	    return object;
	};

});