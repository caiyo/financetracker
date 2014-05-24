
//global variables for storing folder information
var folders;
var selectedFolderObj;


//add listeners
$(function(){
	$('#transaction-table').on('click', '#delete',deleteTransCallback);
	$('#transaction-table').on('click', '#update', updateTransCallback);
	$('#transaction-table').on('click', '#add', addTransCallback);
	$('#transaction-table').on('click', '.save',  saveUpdate);
	$('#transaction-table').on('click', '.cancel',  removeUpdateView);
	$('#addFolder').on('click', addFolder);
	$('#deleteFolder').on('click', deleteFolder);
	$('#updateFolder').on('click', updateFolder);
	$('#folder-list').on('click', '.folder', selectFolder);
	$('#folder-list').on('focusout keyup', '#newFolder', saveFolder);
});


//Javascript for listing all folders that a Account has created
$(function(){
	jsRoutes.controllers.FinanceFolderController.listFolders().ajax({
		 success : function(data){			
			 addFoldersNode(data); 
			folders = data;
	}});
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
							//$('.updateRow').css('display', 'none');
							//add transaction to selected folder object
							selectedFolderObj.transactions.push(data);
							selectedFolderObj.total += data.amount;
							updateSelectedFolderTotal();
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


//event callback for adding input field to list for creating a new folder
var addFolder = function(event){
	if($('#newFolder').length ==0){
		$('#folder-list').append("<li><input type='text' name='name' id='newFolder' value='new folder" 
				+ $('#folder-list').children().length +"' class='form-control'></li>");
		$('#newFolder').focus();
	}
	else {
		alert("Currently adding new folder. Must save it first");
	}
}

var updateFolder = function(event){
	
}

var deleteFolder = function(event){
	var folderNode = $('.selected');
	var folderId = parseInt(folderNode.attr('data-id'));
	var folderNodeIndex = folderNode.index();
	var newSelected;
	
	if(folderNodeIndex != folders.length-1)
		newSelected=$('#folder-list').children()[folderNodeIndex+1];
	else
		newSelected=$('#folder-list').children()[folderNodeIndex-1];
	
	

	//remove from db, folders obj, and list. set new selected
	jsRoutes.controllers.FinanceFolderController.deleteFolder(folderId).ajax({
		success: function(data){

			//remove from folders obj
			folders.splice(folderNodeIndex,1);
			//remove folder from UI
			folderNode.remove();
			
			//set new selected
			newSelected.click()
		},
		error: function(data){
			alert("Could not delete Folder");
		}
	});
}

//event callback for saving new folder
var saveFolder = function(e){
	if(e.keyCode=='13'){
		$(this).blur();
	}
	else if(e.type=='focusout'){
		var folderName = $(this).val();
		jsRoutes.controllers.FinanceFolderController.addFolder().ajax({
			data : {
				name : folderName
			},
			success : function(data){
				$('#newFolder').parent().remove();
				folders.push(data);
				addFoldersNode(data);
				$('#folder-list li[data-id='+data.id+']').click();
				
			},
			error: function(data) {
				alert('error adding folder');
			}
		});
	}
	
}

//helper function for  adding folders to DOM
var addFoldersNode = function(data){
	var html =[];
	if(Array.isArray(data)){
		$.each(data, function(i, folder){
			html.push("<li class='folder' data-id='" + folder.id +"'>"
					+"<a class ='folder-name' >"+ folder.name + "</a>"
			+"</li>");
		});
	}
	else{
		html.push("<li class='folder' data-id='" + data.id +"'>"
				+"<a class ='folder-name' >"+ data.name  + "</a>"
		+"</li>");
	}
	$('#folder-list').append(html.join(''));
	
};

//javascript for setting a selected folder
//and display transactions for selected folder
var selectFolder = function(event){
	var selected = $('.selected');
	if(this!=selected[0]){
		selected.removeClass("selected");
		$(this).addClass("selected");
		var folderId = $('.selected').attr('data-id');
		//set selected folder
		$.each(folders,function(i,folder){
			if(folder.id == folderId)
				selectedFolderObj = folder;
		});
		displayTransactions(selectedFolderObj);
								
		//display table after folder is selected
		$('#transaction-table').css('display', '');
		$('#stats').css('display', '');
	}
}

//helper function to add transactions to DOM
//
var displayTransactions= function(folder){
	var html=[];
	$.each(folder.transactions, function(i,transaction){
		html.push(generateTransactionNode(transaction));
	});
	$('#transaction-table tbody').html(html.join(''));
	//$('.updateRow').css('display', 'none');
	updateSelectedFolderTotal();

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
		+ transaction.amount.toFixed(2)
		+"</td></tr>"
		//update row
		
		returnTransaction += generateTransFormNode(transaction)
		/*+"<tr data-id='" +transaction.id + "' class='updateRow' style='display: none'>"
		+"<td> <div class='btn-group btn-group-sm'>" 
		+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
		+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
		+"</div></td>"
		+"<td class='transDate' >"
			+"<input type='text' size='15' class='form-control ' value='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()+"'>"
		+ "</td> <td class='transDescript'>"
		+ "<input type='text' class='form-control ' value='" +transaction.shortDescription+"'>"
		+"</td><td class='transAmount'>"
		+ "<input type='text' size='10' class='form-control ' value='" +transaction.amount.toFixed(2)+"'>"
		+"</td></tr>";*/
	return returnTransaction;
}

var generateTransFormNode=function(transaction){
	var node;
	
	//for Updating rows
	if(transaction != null){
		var date = new Date(transaction.creationDate);
		node =
		"<tr data-id='" +transaction.id + "' class='updateRow' style='display: none'>"
		+"<td> <div class='btn-group btn-group-sm'>" 
		+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
		+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
		+"</div></td>"
		+"<td class='transDate' >"
			+"<input type='text' size='15' class='form-control ' value='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()+"'>"
		+ "</td> <td class='transDescript'>"
		+ "<input type='text' class='form-control ' value='" +transaction.shortDescription+"'>"
		+"</td><td class='transAmount'>"
		+ "<input type='text' size='10' class='form-control ' value='" +transaction.amount.toFixed(2)+"'>"
		+"</td></tr>";
	}
	//for adding rows
	else{
		node =
			"<tr class='addRow'>"
			+"<td> <div class='btn-group btn-group-sm'>" 
			+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
			+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
			+"</div></td>"
			+"<td class='transDate' >"
				+"<input type='text' size='15' class='form-control '>"
			+ "</td> <td class='transDescript'>"
			+ "<input type='text' class='form-control '>"
			+"</td><td class='transAmount'>"
			+ "<input type='text' size='10' class='form-control '>"
			+"</td></tr>";
	}
	
	return node;
}

//callback function for creating form for adding transactions
//
var addTransCallback =function(event){
	
}

//callback function for deleting 1 or more transactions
var deleteTransCallback = function(event){
	var transactionIds = [];
	
	//get all transactions selected for deletion
	$('#transaction-table input[type=checkbox]:checked').each(function(i, checkbox){
		transactionIds.push($(checkbox).closest('tr').attr('data-id'));	
	});
	if(transactionIds.length>0){
		var confirmed = confirm("Delete " + transactionIds.length + " transaction(s)?");
		
		//if user confirms deletion for all selected items, call ajax function for
		//deleting transaction
		if(confirmed){
			for(var i=0; i<transactionIds.length; i++){
				jsRoutes.controllers.TransactionController.deleteTransaction(transactionIds[i]).ajax({
					success : function(data){
						var removedIndexes=[];
						$("#transaction-table tr[data-id='" + data.id +"']").remove();
						//update selectedFolderObj to reflect deleted transaction
						selectedFolderObj.total -= data.amount;
						$.each(selectedFolderObj.transactions, function(i, transaction){
							if(transaction.id == data.id)
								removedIndexes.push(i);
						});
						$.each(removedIndexes, function(i, index){
							selectedFolderObj.transactions.splice(index, 1);
						});
						updateSelectedFolderTotal();
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
			
			//update selectedFolderObj
			$.each(selectedFolderObj.transactions, function(i, transaction){
				if(transaction.id == data.id){
					transaction.creationDate=data.creationDate;
					transaction.shortDescription=data.shortDescription;
					transaction.amount = data.amount;
				}
			});
			selectedFolderObj.total += (data.amount-oldAmount);
			updateSelectedFolderTotal();
			//switch views
			removeUpdateView(event, updatedRow);
		},
		error : function(data){
			alert("cannot update transaction");
		}
	});

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

//updates ui to reflect total for selected folder
var updateSelectedFolderTotal = function(){
	$("#folderTotal")[0].innerHTML = selectedFolderObj.total.toFixed(2);
}


