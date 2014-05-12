/**
 * adds folders with total to dom
 * TODO : add transactions underneath each folder
 */
(function (){
	
	//Javascript for listing all folders that a Account has created
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
								var date = new Date(data.creationDate);
								var html="<tr data-id='" +data.id + "'>"
											+"<td><input type='checkbox'></td>"
											+"<td class='transDate'>"
											+ (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()
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
								+"  <th>" 
								+"		<div class='btn-group'>" 
								+"			<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'>" 
								+"				Action <span class='caret'></span>"
								+"			</button>"
								+"			<ul class='dropdown-menu' role='menu'>"
								+"				<li><a href='#' id='update'>Update</a><li>"
								+"				<li><a href='#' id='delete'>Delete</a><li>"
								+"			</ul>"
								+"		</div>"
								+"</th>"
								+"	<th>Date</th>"
								+"	<th>Description</th>"
								+"	<th>Amount</th>"
								+" </tr>"
							+"</table>"	;
						$('#transaction-list').html(html);
						$('#delete').on('click',deleteTransCallback);
						$('#update').on('click',updateTransCallback);
						
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
				+"<a class ='folder-name' >"+ ("input[name=name]", form).val()  + "</a>"
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
					//add 2 rows for each transaction. 1 is the view row and 1 is update
					//show view when viewing transactions if user wants to update a row,
					//hide it and show update row
					html.push(
						//view row
						"<tr data-id='" +transaction.id + "' class='viewRow'>"
						+"<td><input type='checkbox'></td>"
						+"<td class='transDate' >"
							+ (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()
						+ "</td> <td class='transDescript'>"
						+ transaction.shortDescription
						+"</td><td class='transAmount'>"
						+ transaction.amount
						+"</td></tr>"
						//update row
						+"<tr data-id='" +transaction.id + "' class='updateRow'>"
						+"<td><button class='btn btn-primary save' type='button'>Save </button></td>"
						+"<td class='transDate' >"
							+"<input type='text' class='form-control ' value='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()+"'>"
						+ "</td> <td class='transDescript'>"
						+ "<input type='text' class='form-control ' value='" +transaction.shortDescription+"'>"
						+"</td><td class='transAmount'>"
						+ "<input type='text' class='form-control ' value='" +transaction.amount+"'>"
						+"</td></tr>"
					);
				});
				$('#transaction-table tbody').append(html.join(''));
				$('.updateRow').css('display', 'none');
				$('.save').on('click', saveUpdate);
			 }
		});
	});
	
};

//callback function for deleting 1 or more transactions
var deleteTransCallback = function(event){
	var transactionIds = [];
	$('#transaction-table input[type=checkbox]:checked').each(function(i, checkbox){
		transactionIds.push($(checkbox).closest('tr').attr('data-id'));	
	});
	if(transactionIds.length>0){
		var confirmed = confirm("Delete " + transactionIds.length + " transaction(s)?");
		if(confirmed){
			for(var i=0; i<transactionIds.length; i++){
				jsRoutes.controllers.TransactionController.deleteTransaction(transactionIds[i]).ajax({
					success : function(data){
						$("#transaction-table tr[data-id='" + data.id +"']")[0].remove();
					}
				});
			}
		}
	}
}

//callback function for displaying update transactions 
//hides the view rows and shows update rows with
//input boxes to edit
var updateTransCallback = function(event){
	var transactionIds = [];
	$('#transaction-table input[type=checkbox]:checked').each(function(i, checkbox){
		transactionIds.push($(checkbox).closest('tr').attr('data-id'));	
	});
	
	if(transactionIds.length>0){
		$.each(transactionIds, function(i, transaction){
			console.log(i);
			$(".viewRow[data-id='"+transaction +"']").css('display', 'none');
			$(".updateRow[data-id='"+transaction +"']").css('display', '');
		});
	}
}

//callback for saving update
var saveUpdate = function(event){
	var updatedRow = $(this).parent().parent();
	var transactionId = updatedRow.attr('data-id');
	
	// ADD AJAX CALL TO UPDATE BACKEND AND UPDATE VIEW ROW
	$(updatedRow).css('display', 'none');
	$(".viewRow[data-id='"+transactionId +"']").css('display', '');
	$(".viewRow[data-id='"+transactionId +"'] input[type=checkbox]").prop('checked', false);
}


