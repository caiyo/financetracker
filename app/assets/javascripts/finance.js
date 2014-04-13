/**
 * adds folders with total to dom
 * TODO : add transactions underneath each folder
 */
(function (){
	
	//Javascript for listing all folders that a user has created
	$(function(){
		jsRoutes.controllers.FinanceFolderController.listFolders().ajax({
			 success : function(data){			
				addFolders(data, 'GET'); 
		}});
	});
	
	//ajax for creating new folders
	$(function(){
		var form = $("#newFolder");
		form.on("submit", function(e){
			e.preventDefault();
			//$('input[type=submit', form).attr("disabled", true);
			jsRoutes.controllers.FinanceFolderController.addFolder().ajax({
				data : form.serialize(),
				success : function(data){
					addFolders(data, 'POST', form);
				},
				error: function(data) {
					alert('error adding folder');
				}
			});
		});
		
	});
	
	//ajax to add new transaction
	$(function(){
		var form = $("#newTransaction");
		form.on("submit", function(e){
			e.preventDefault();
			var selectedFolder = $('.selected .folder-name');
			if(selectedFolder.length !=0){
				var date = new Date($("input[name=month]", form).val()
						+'/'
						+$("input[name=day]", form).val()
						+'/'
						+$("input[name=year]", form).val()
						+'/');
				jsRoutes.controllers.TransactionController.addTransaction(selectedFolder[0].innerHTML).ajax({
					data : 
						{
							amount : parseFloat($("input[name=amount]", form).val()),
							shortDescription : $("input[name=shortDescription]", form).val(),
							creationDate : (date.getUTCMonth()+1) +'/' + date.getUTCDate() +'/' +date.getUTCFullYear()
						},
					success : function(data){
								var html = "<div class='transaction'>"+
											"<span class='transDate'>"
												+(date.getUTCMonth()+1) +'/' + date.getUTCDate() +'/' +date.getUTCFullYear()
											+ "</span> <span class='transDescrip'>"
											+ data.shortDescription
											+"</span><span class='transAmount'>"
											+ data.amount
											+"</span></div>";
								$('#transaction-list').append(html);
								for(var i=0; i<form.length; i++){
									form[i].reset();
								}
							},
					error : function(data){
							alert("cannot add transaction");
						}
				});
			}
			else{
				alert("A folder must be selected before adding transactions");
			}
		});
	});
	
	//javascript for setting a selected folder
	//	to be used later for when adding transactions
	$(function(){
		$('#folder-list').on('click', '.folder',  
				function(event){
					var selected = $('.selected');
					if(this!=selected[0]){
						selected.removeClass("selected");
						$(this).addClass("selected");
						addTransactions($('.selected .folder-name')[0].innerHTML);
					}
				}
		);
	});
	
}).call(this);

//helper function for getting/posting folders
var addFolders = function(data, inputType, form){
	var html =[];
	if(inputType == 'GET'){
		$.each(data, function(i, folder){
			html.push( "<div class='folder'> " +
						"<span class='folder-name'>" + 
							folder.name + 
						"</span>" +
						"<span class='folder-total'>"+
							folder.total +
					"</span> </div>");
		});
	}
	else if (inputType=='POST'){
		html.push(
			"<div class='folder'> " +
			"<span class='folder-name'>" + 
			$("input[name=name]", form).val() + 
			"</span>" +
			"<span class='folder-total'>"+
			0 +
			"</span> </div>");
		form[0].reset();
	}
	$('#folder-list').append(html.join(''));
};

//helper function to add transactions to DOM
var addTransactions= function(folder){
	$(function(){
		jsRoutes.controllers.TransactionController.listTransactions(folder).ajax({
			 success : function(data){
				var html = [];
				$.each(data, function(i, transaction){
					var date = new Date(transaction.creationDate);
					html.push("<div class='transaction'>"+
						"<span class='transDate'>"
							+ (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()
						+ "</span> <span class='transDescrip'>"
						+ transaction.shortDescription
						+"</span><span class='transAmount'>"
						+ transaction.amount
						+"</span></div>"
					);
				});
				$('#transaction-list').html(html.join(''));			
			 }
		});
	});
	
};


