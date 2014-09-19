
//global variables for storing folder information
var folders={};
var selectedFolderObj;


//add listeners
$(function(){
	$('#transaction-table').on('click', '#delete',deleteTransCallback);
	$('#transaction-table').on('click', '#updateButton', updateTransCallback);
	$('#transaction-table').on('click', '#add', addTransCallback);
	$('#transaction-table').on('click', '.save',  saveRow);
	$('#transaction-table').on('click', '.cancel',  removeFormView);
	$('#folder-list').on('click', '.cancel', removeFormView);
	$('#addFolder').on('click', addFolder);
	$('#deleteFolder').on('click', deleteFolder);
	$('#updateFolder').on('click', updateFolder);
	$('#folder-list').on('click', '.folder', selectFolder);
	$('#folder-list').on('click', '#saveFolder', saveFolder);	
});


//Javascript for listing all folders that a Account has created
$(function(){
	jsRoutes.controllers.FinanceFolderController.listFolders().ajax({
		 success : function(data){			
			 addFoldersNode(data); 
			$.each(data, function(i,folder){
				folders[folder.id]=folder;
			});
	}});
});

/**
 * FOLDER AJAX AND HELPER FUNCTIONS
 */

	//event callback for adding input field to list for creating a new folder
	var addFolder = function(event){
		if($('#newFolder').length ==0){
			$('#folder-list').append(
					"<li>" 
					+"<div class='input-group btn-group'>"
					+"<input type='text' name='name' id='newFolder' value='new folder" 
					+ $('#folder-list').children().length +"' class='form-control'>" 
					+"	<span class='input-group-btn'><button id='saveFolder' class='btn btn-info ' type='button'><span class='glyphicon glyphicon-ok'></span></button>"
					+" <button id='cancelFolder' class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"		
					+"</span>"	
					+"</div>"
					+"</li>");
			$('#newFolder').focus();
		}
		else {
			alert("Currently adding new folder. Must save it first");
		}
	}
	
	//event callback for saving new folder
	//When input field loses focus, the folder
	//is saved with name in input box and is selected
	var saveFolder = function(e){
		var folderName = $('#newFolder').val();
		jsRoutes.controllers.FinanceFolderController.addFolder().ajax({
			data : {
				name : folderName
			},
			success : function(data){
				$('#newFolder').parent().parent().remove();
				//add to folders object
				folders[data.id]=data;
				addFoldersNode(data);
				//selects next folder in list
				$('#folder-list li[data-id='+data.id+']').click();
				
			},
			error: function(data) {
				alert('error adding folder');
			}
		});
	}
	
	//helper function for  adding folders to DOM
	var addFoldersNode = function(data){
		var html =[];
		if(Array.isArray(data)){
			$.each(data, function(i, folder){
				html.push("<li class='folder' data-id='" + folder.id +"'>"
						+"<a class ='folder-name' >"+ folder.name 
						+	"<span class='folderTotal'>" + "  &ndash; $" +folder.total + "</span>"
						+"</a>"
				+"</li>");
			});
		}
		else{
			html.push("<li class='folder' data-id='" + data.id +"'>"
					+"<a class ='folder-name' >"+ data.name  
					+ 	"<span class='folderTotal'>" + "  &ndash; $" + data.total + "</span>"
					+ "</a>"
			+"</li>");
		}
		$('#folder-list').append(html.join(''));
		
	};
	
	var updateFolder = function(event){
		
	}
	
	var deleteFolder = function(event){
		var folderNode = $('.selected');
		var folderId = parseInt(folderNode.attr('data-id'));
		var folderNodeIndex = folderNode.index();
		var newSelected=$('#folder-list').children()[folderNodeIndex+1];
		
		//sets new selected folder for after selected folder is deleted
		if(!newSelected)
			newSelected=$('#folder-list').children()[folderNodeIndex-1];
		
		
	
		//remove from db, folders obj, and list. set new selected
		jsRoutes.controllers.FinanceFolderController.deleteFolder(folderId).ajax({
			success: function(data){
				//remove from folders obj
				delete folders.folderNodeIndex;
				//remove folder from UI
				folderNode.remove();
				
				//set new selected
				if(newSelected !=null)
					newSelected.click();
				else{
					//hide transactions table
					$('#transaction-table').css('display', 'none');
					//redisplay instructions
					$('#expense-descript').css('display', '');
				}
			},
			error: function(data){
				alert("Could not delete Folder");
			}
		});
	}
	
	//javascript for setting a selected folder
	//and display transactions for selected folder
	var selectFolder = function(event){
		var selected = $('.selected');
		if(this!=selected[0]){
			//hide instruction text
			$('#expense-descript').css('display', 'none');
			
			selected.removeClass("selected");
			$(this).addClass("selected");
			var folderId = $('.selected').attr('data-id');
			//set selected folder
			$.each(folders,function(i,folder){
				if(folder.id == folderId)
					selectedFolderObj = folder;
			});
			displayTransactions(selectedFolderObj);
			
			//add sorting to table
			//display table after folder is selected
			$('#transaction-table').css('display', '');
			
			addTableSorter();
			
		}
	}

/**
 * EXPENSES(TRANSACTIONS) AJAX AND HELPER FUNCTIONS
 * 
 */

	//helper function to add transactions to DOM
	//
	var displayTransactions= function(folder){
		var html=[];
		$.each(folder.transactions, function(i,transaction){
			html.push(generateTransactionNode(transaction));
		});
		$('#transaction-table tbody').html(html.join(''));
		$('.datepicker').datepicker();
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
		return returnTransaction;
	}
	
	//Generates a row tag containing input fields for adding/updating rows.
	var generateTransFormNode=function(transaction){
		var node;
		
		//form row for Updating rows
		if(transaction != null){
			var date = new Date(transaction.creationDate);
			node =
			"<tr data-id='" +transaction.id + "' class='updateRow' style='display: none'>"
			+"<td> <div class='btn-group btn-group-sm'>" 
			+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
			+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
			+"</div></td>"
			//Add custom key to be the same as form value so that update row and view row stay in the same place
			+"<td class='transDate' sorttable_customkey='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear() +"'>"
				+"<input type='text' size='15' class='form-control datepicker ' value='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()+"'>"
			+ "</td> <td class='transDescript' sorttable_customkey='" +transaction.shortDescription+"'>"
			+ "<input type='text' class='form-control ' value='" +transaction.shortDescription+"'>"
			+"</td><td class='transAmount' sorttable_customkey='" +transaction.amount.toFixed(2)+"'>"
			+ "<input type='text' size='10' class='form-control ' value='" +transaction.amount.toFixed(2)+"'>"
			+"</td></tr>";
		}
		//form row for adding rows
		else{
			node =
				"<tr class='addRow'>"
				+"<td> <div class='btn-group btn-group-sm'>" 
				+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
				+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
				+"</div></td>"
				+"<td class='transDate' >"
					+"<input type='text' size='15' class='form-control datepicker'>"
				+ "</td> <td class='transDescript'>"
				+ "<input type='text' class='form-control '>"
				+"</td><td class='transAmount'>"
				+ "<input type='text' size='10' class='form-control '>"
				+"</td></tr>";
		}
		
		return node;
	}
	
	//callback function for creating form for adding transactions
	var addTransCallback =function(event){
		var row = generateTransFormNode();
		$('#transaction-table tbody').prepend(row);
		//add datepicker to addTransaction row
		$('.addRow .datepicker').datepicker();
	}
	
	var addTransaction =function(row){
		jsRoutes.controllers.TransactionController.addTransaction(selectedFolderObj.name).ajax({
			data : {
				amount : parseFloat($('.transAmount input', row).val()),
				shortDescription : $('.transDescript input', row).val(),
				creationDate : $('.transDate input', row).val()
			},
			success : function(data){		
						var html=generateTransactionNode(data);
						$('#transaction-table tbody').prepend(html);
						//add transaction to selected folder object
						updateFolderTransactions(data);
						removeFormView(null, row);
						
						//add datepicker to newly added transactions
						$(".updateRow[data-id='"+data.id+"'] .datepicker").datepicker();
						//updates tale but doesnt sort
						updateTableSort(false);
					},
			error : function(data){
					alert("cannot add transaction");
				}
		});
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
							//updates table for sorting, but doesnt resort
							updateTableSort(false);
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
	
	//calls addRow/updateRow depending on the action.
	var saveRow = function(event){
		var row = $(this).parent().parent().parent();
		if(row.hasClass('updateRow')){
			updateTransaction(row);
		}
		else if (row.hasClass('addRow')){
			addTransaction(row);
		}
	}
	
	//updates transaction with new data
	var updateTransaction = function(updatedRow){
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
				 console.log('updated amount: ' + data.amount);
				var oldAmount = $('.transAmount ', viewRow)[0].innerHTML;
			 	var date = new Date(data.creationDate);
				$('.transAmount', viewRow)[0].innerHTML = data.amount.toFixed(2);
				$('.transDescript', viewRow)[0].innerHTML = data.shortDescription;
				$('.transDate ', viewRow)[0].innerHTML = (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear();
				
				//update custom key in updated row for sorting
				$('.transAmount', updatedRow).attr('sorttable_customkey', data.amount.toFixed(2));
				$('.transDescript', updatedRow).attr('sorttable_customkey', data.shortDescription);
				$('.transDate', updatedRow).attr('sorttable_customkey', (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear());
				
				//update selectedFolderObj
				updateFolderTransactions(data);
				
				//switch views
				removeFormView(event, updatedRow);
				
				//updates table for sorting and resorts
				updateTableSort(true);
			},
			error : function(data){
				alert("cannot update transaction");
			}
		});
	}
	
	//changes transaction row back to the view row and hides the update row
	var removeFormView = function(event, row){
		//gets Row being removed from cancel button
		if (row ==null)
			row = $(this).parent().parent().parent();
		
		if(row.hasClass('updateRow')){
			var transactionId = row.attr('data-id');
			var viewRow = $(".viewRow[data-id='"+transactionId +"']");
			
			//revert updateRow form values back
			$(".transDate input", row).val($(".transDate", viewRow).html());
			$(".transAmount input", row).val($(".transAmount", viewRow).html());
			$(".transDescript input", row).val($(".transDescript", viewRow).html());
			
			//switch back to view Row
			$(row).css('display', 'none');
			$(viewRow).css('display', '');
			$("input[type=checkbox]", viewRow).prop('checked', false);
		}
		
		else{
			row.remove();
		}
		
	}

/**
 * MISC HELPER FUNCTIONS
 */

	//updates selectedFolderObj with updated/new transaction
	var updateFolderTransactions = function (newTransaction){
		var oldAmount=0;
		var containsTrans=false;
		$.each(selectedFolderObj.transactions, function(i, transaction){
			if(transaction.id == newTransaction.id){
				oldAmount =transaction.amount;
				transaction.creationDate=newTransaction.creationDate;
				transaction.shortDescription=newTransaction.shortDescription;
				transaction.amount = newTransaction.amount;
				
				containsTrans=true;
			}
		});
		
		if(!containsTrans)
			selectedFolderObj.transactions.push(newTransaction);
		
		selectedFolderObj.total += (newTransaction.amount-oldAmount);
		updateSelectedFolderTotal();
	}
	
	//updates ui to reflect total for selected folder
	var updateSelectedFolderTotal = function(){
		$(".selected .folderTotal")[0].innerHTML = "  &ndash; $" + selectedFolderObj.total.toFixed(2);
	}
	
	//Add table sorter to table of selected folder
	var addTableSorter = function(){
		
		$('#transaction-table').tablesorter({ 
	        // define a custom text extraction function 
	        textExtraction: function(node) { 
	        	var customKey= $(node).attr('sorttable_customkey');
	        	return customKey || node.innerHTML;
	        },
	        headers : {
	        	0 : {
	        		sorter: false
	        	}
	        },
	        sortList : [[1,0]]
	    });
	}
	
	var updateTableSort= function(resort){
		var table = $('#transaction-table');
		//tells table sorter that table has been updated and resorts it if resrot == true
		table.trigger("update", [resort]);
	}