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
				jsRoutes.controllers.TransactionController.addTransaction(selectedFolder[0].innerHTML).ajax({
					data : 
						{
							amount : parseFloat($("input[name=amount]", form).val()),
							shortDescription : $("input[name=shortDescription]", form).val(),
							creationDate : $("input[name=date]", form).val()
						},
					success : function(data){					
								var html="<tr data-id='" +data.id + "'>"+
											"<td class='transDate'>"
												+ $("input[name=date]", form).val()
											+ "</td> <td class='transDescript'>"
											+ data.shortDescription
											+"</td><td class='transAmount'>$"
											+ data.amount
										+"</td></tr>";
								$('#transaction-table tbody').append(html);
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
						var html = 
							"<table class='table' id='transaction-table'>"
								+"<tr>"
								+"	<th>Date</th>"
								+"	<th>Description</th>"
								+"	<th>Amount</th>"
								+" </tr>"
							+"</table>"	;
						$('#transaction-list').html(html);
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
			html.push("<li class='folder' data-id='" + folder.id +"'>"
					+"<a class ='folder-name' >"+ folder.name + "</a>"
			+"</li>");
		});
	}
	else if (inputType=='POST'){
	
		html.push("<li class='folder' data-id='" + data.id +"'>"
				+"<a class ='folder-name' >"+ $("input[name=name]", form).val()  + "</a>"
		+"</li>");
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
					html.push("<tr data-id='" +transaction.id + "'>"+
						"<td class='transDate' >"
							+ (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()
						+ "</td> <td class='transDescript'>"
						+ transaction.shortDescription
						+"</td><td class='transAmount'>$"
						+ transaction.amount
						+"</td></tr>"
					);
				});
				$('#transaction-table tbody').append(html.join(''));			
			 }
		});
	});
	
};


