/**
 * adds folders with total to dom
 * TODO : add transactions underneath each folder
 */
(function (){
	$(function(){
		jsRoutes.controllers.FinanceFolderController.listFolders().ajax({
			 success : function(data){			
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
		}});
	});
	
	$(function(){
		$("#newFolder").on("submit", function(e){
			e.preventDefault();
			var form = $("#newFolder");
			jsRoutes.controllers.FinanceFolderController.addFolder().ajax({
				data : form.serialize(),
				success : function(data){
					var html = "<div class='folder'> " +
							"<span class='folder-name'>" + 
							$("input[name=name]", form).val() + 
							"</span>" +
							"<span class='folder-total'>"+
							0 +
							"</span> </div>";
					$('#folder-list').append(html);
					form[0].reset();
				},
				error: function(data) {
					alert('error adding folder');
				}
			});
		});
		
	});
	
	$(function(){
		$('#folder-list').on('click', '.folder',  
				function(event){
					$('.selected').removeClass("selected");
					$(this).addClass("selected");
				}
		);
	});
	
}).call(this);



(function(){
	
}).call(this);