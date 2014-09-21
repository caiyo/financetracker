//add table listeners
$(function(){
	$('#bills-table').on('click', '#delete',deleteBillCallback);
	$('#bills-table').on('click', '#updateButton', updateBillCallback);
	$('#bills-table').on('click', '#add', addBillCallback);
	$('#bills-table').on('click', '.save', saveRow);
	
	populateBillsTable();
	addTableSorter();
	
	
});

var populateBillsTable = function(){
	jsRoutes.controllers.BillController.listBills().ajax({
		success: function(data){
			var html = [];
			$.each(data, function(i, bill){
				html.push(generateBillRow(bill));
			});
			$('#bills-table tbody').html(html.join(''));
			$('.datepicker').datepicker();
		}
	});
	addTableSorter();
};

/**
 * BILL ADD
*/

var addBill = function(row){
	jsRoutes.controllers.BillController.addBill().ajax({
		data:{
			title : $('.billTitle input', row).val(),
			dueDate: $('.billDate input', row).val(),
			amount: parseFloat($('.billAmount input', row).val()),
			isRecuring: $('.billRecuring input', row).is(':checked')
		},
		success: function(data){
			var html = generateBillRow(data);
			$('#bills-table tbody').prepend(html);
			row.remove();
			
			//add datepicker to newly added bills
			$(".updateRow[data-id='"+data.id+"'] .datepicker").datepicker();
			//updates tale but doesnt sort
			updateTableSort($('#bill-table'),false);
		}
	});
}

var addBillCallback = function(e){
	//generates form html for adding a row
	var row = generateBillFormRow();
	$('#bills-table tbody').prepend(row);
	//add date picker to the data box
	$('.addRow .datepicker').datepicker();
};

var generateBillRow = function(bill){
	var date = new Date(bill.dueDate);
	//add 2 rows for each bill. 1 is the view row and 1 is update
	//show view when viewing bill if user wants to update a row,
	//hide it and show update row
	var returnBill = 
		//view row
		"<tr data-id='" +bill.id + "' class='viewRow'>"
		+"<td class='actionBox'><input type='checkbox'></td>"
		+"<td class='billDate' >"
			+ (date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()
		+ "</td> <td class='billTitle'>"
		+ bill.title
		+"</td><td class='billAmount'>"
		+ bill.amount.toFixed(2)
		+"</td><td class='billRecuring'>"
		+"<input type='checkbox' disabled";
	if(bill.isRecuring)
		returnBill+=' checked';
		returnBill+="></td></tr>";
		//update row
		returnBill += generateBillFormRow(bill)
	return returnBill;
};

var generateBillFormRow = function(bill){
	var row;
	
	//adding a row with form in bill table for updating bill
	if(bill){
		var date = new Date(bill.dueDate);
		row =
		"<tr data-id='" +bill.id + "' class='updateRow' style='display: none'>"
		+"<td> <div class='btn-group btn-group-sm'>" 
		+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
		+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
		+"</div></td>"
		//Add custom key to be the same as form value so that update row and view row stay in the same place
		+"<td class='billDate' sorttable_customkey='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear() +"'>"
			+"<input type='text' size='15' class='form-control datepicker ' value='" +(date.getUTCMonth()+1) + '/' + date.getUTCDate() + '/' + date.getUTCFullYear()+"'>"
		+ "</td> <td class='billTitle' sorttable_customkey='" +bill.title+"'>"
		+ "<input type='text' class='form-control ' value='" +bill.title+"'>"
		+"</td><td class='billAmount' sorttable_customkey='" +bill.amount.toFixed(2)+"'>"
		+ "<input type='text' size='10' class='form-control ' value='" +bill.amount.toFixed(2)+"'>"
		+"</td><td class='billRecuring'>"
		+"<input type='checkbox'";
	if(bill.isRecuring)
		row+=" checked";
		row+="></tr>";
	}
	//adding a row with form to add bill to table
	else{
		row =
			"<tr class='addRow'>"
			+"<td> <div class='btn-group btn-group-sm'>" 
			+"	<button class='btn btn-info save' type='button'><span class='glyphicon glyphicon-ok'></span></button>" 
			+"	<button class='btn btn-info cancel' type='button'><span class='glyphicon glyphicon-remove'></span></button>"
			+"</div></td>"
			+"<td class='billDate' >"
				+"<input type='text' size='15' class='form-control datepicker'>"
			+ "</td> <td class='billTitle'>"
			+ "<input type='text' class='form-control '>"
			+"</td><td class='billAmount'>"
			+ "<input type='text' size='10' class='form-control '>"
			+"</td>"
			+"<td class='billRecuring'>"
			+"<input type='checkbox'>"
			+"</td></tr>";
	}
	return row;
};

var saveRow = function(event){
	//gets <tr> element that form is in
	var row = $(this).parent().parent().parent();
	if(row.hasClass('updateRow')){
		updateBill(row);
	}
	else if (row.hasClass('addRow')){
		addBill(row);
	}
};
/**
 * END BILL ADD
 */

/**
 * BILL UPDATE
 */

var updateBillCallback = function(e){
	
};
/**
 * END BILL UPDATE
 */

/**
 * BILL DELETE
 */
var deleteBillCallback = function(e){
	var billIds = [];
	//get all transactions selected for deletion
	$('#bills-table .actionBox input[type=checkbox]:checked').each(function(i, checkbox){
		billIds.push($(checkbox).closest('tr').attr('data-id'));	
	});
	if(billIds.length>0){
		var confirmed = confirm("Remove " + billIds.length + " bill(s)?");
		if(confirmed){
			$.each(billIds, function (i, billId){
				deleteBill(billId);
				$("#bills-table tr[data-id='" + billId +"']").remove();
			});

			//updates table for sorting, but doesnt resort
			updateTableSort($('#bill-table'),false);
			
		}	
	}
};

var deleteBill = function(billId){
	jsRoutes.controllers.BillController.deleteBill(billId).ajax({
		success : function(data){
			$("#bill-table tr[data-id='" + billId +"']").remove();

		}
	});
};
/**
 * END BILL DELETE
*/
/**
*MISC FUNCTIONS
*/

var addTableSorter = function(){
	
	$('#bills-table').tablesorter({ 
        // define a custom text extraction function 
        textExtraction: function(node) { 
        	var customKey= $(node).attr('sorttable_customkey');
        	return customKey || node.innerHTML;
        },
        headers : {
        	0 : {
        		sorter: false
        	},
        	4 : {
        		sorter: false
        	}
        },
        sortList : [[1,0]]
    });
}

var updateTableSort= function(table, resort){
	//tells table sorter that table has been updated and resorts it if resrot == true
	table.trigger("update", [resort]);
}