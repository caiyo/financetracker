var folders;
var selectedFolderObj;
(function (){
	
	
	
	//Javascript for listing all folders that a Account has created
	$(function(){
		jsRoutes.controllers.FinanceFolderController.listFolders().ajax({
			 success : function(data){			
				addFolders(data, 'GET'); 
				folders = data;
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
					folders.push(data);
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
								var html=generateTransactionNode(data);
								$('#transaction-table tbody').append(html);
								for(var i=0; i<form.length; i++){
									form[i].reset();
								}
								$('.updateRow').css('display', 'none');
								//add transaction to selected folder object
								selectedFolderObj.transactions.push(data);
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
						var folderId = $('.selected').attr('data-id');
						var html = 
							"<table class='table' id='transaction-table'>"
								+"<thead><tr>"
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
								+" </tr></thead><tbody></tbody>"
							+"</table>"	;
						$('#transaction-list').html(html);
						$('#delete').on('click',deleteTransCallback);
						$('#update').on('click',updateTransCallback);
						$('#transaction-list').on('click', '.save',  saveUpdate);
						$('#transaction-list').on('click', '.cancel',  removeUpdateView);
						addTransactions($('.selected .folder-name')[0].innerHTML);
						
						//set selected folder
						$.each(folders,function(i,folder){
							if(folder.id == folderId)
								selectedFolderObj = folder;
						});
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
				+"<a class ='folder-name' >"+ data.name  + "</a>"
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
					
					html.push(generateTransactionNode(transaction));
				});
				$('#transaction-table tbody').append(html.join(''));
				$('.updateRow').css('display', 'none');
				//$('.save').on('click', saveUpdate);
			 }
		});
	});
	
};

//generates node for adding/displaying transactions in table
var generateTransactionNode = function (transaction){
	var date = new Date(transaction.creationDate);
	
	//add 2 rows for each transaction. 1 is the view row and 1 is update
	//show view when viewing transactions if user wants to update a row,
	//hide it and show update row
	var returnTransaction = 
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
		+"<td> <div class='btn-group btn-group-sm'>" 
		+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
		+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
		+"</div></td>"
		+"<td class='transDate' >"
			+"<input type='text' size='15' class='form-control ' value='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()+"'>"
		+ "</td> <td class='transDescript'>"
		+ "<input type='text' class='form-control ' value='" +transaction.shortDescription+"'>"
		+"</td><td class='transAmount'>"
		+ "<input type='text' size='10' class='form-control ' value='" +transaction.amount+"'>"
		+"</td></tr>";
	return returnTransaction;
}

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
						$("#transaction-table tr[data-id='" + data.id +"']").remove();
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
			$(".viewRow[data-id='"+transaction +"']").css('display', 'none');
			$(".updateRow[data-id='"+transaction +"']").css('display', '');
		});
	}
}

//callback for saving update
var saveUpdate = function(event){
	var updatedRow = $(this).parent().parent().parent();
	var transactionId = updatedRow.attr('data-id');
	var viewRow = $(".viewRow[data-id='"+transactionId +"']");
	
	jsRoutes.controllers.TransactionController.updateTransaction(transactionId).ajax({
		data: 
			{
				amount : parseFloat($('.transAmount input', updatedRow).val()),
				shortDescription : $('.transDescript input', updatedRow).val(),
				creationDate : $('.transDate input', updatedRow).val()
			},
		 success : function(data){	
			 
			 //updates viewrow to what was entered in updated row
			var oldAmount = $('.transAmount ', viewRow)[0].innerHTML;
		 	var date = new Date(data.creationDate);
			$('.transAmount ', viewRow)[0].innerHTML = data.amount;
			$('.transDescript', viewRow)[0].innerHTML = data.shortDescription;
			$('.transDate ', viewRow)[0].innerHTML = (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear();
			
			/*$(updatedRow).css('display', 'none');
			$(viewRow).css('display', '');
			$("input[type=checkbox]", viewRow).prop('checked', false);*/
			removeUpdateView(event, updatedRow);
		},
		error : function(data){
			alert("cannot update transaction");
		}
	})

}

//changes transaction row back to the view row and hides the update row
var removeUpdateView = function(event, row){
	if (row != null)
		var updatedRow = row;
	else
		var updatedRow = $(this).parent().parent().parent();
	var transactionId = updatedRow.attr('data-id');
	var viewRow = $(".viewRow[data-id='"+transactionId +"']");
	
	$(updatedRow).css('display', 'none');
	$(viewRow).css('display', '');
	$("input[type=checkbox]", viewRow).prop('checked', false);
	
	
}



