/**
 * adds folders with total to dom
 * TODO : add transactions underneath each folder
 */
(function (){
	$(function(){
		return $.get("/folders/list", function(data){
			var html = [];
			$.each(data, function(i, folder){
				html.push( "<div class='folder'> " +
							"<span class='folder-name'>" + 
								folder.name + 
							"</span>" +
							"<span class='folder-total'>"+
								folder.total +
						"</span> </div>");
			});
			$('#folder-list').html(html.join(''));
		});
	});
	
}).call(this);