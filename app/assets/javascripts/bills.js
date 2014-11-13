$(function(){
var bills={};	
var beginDate; //populated by setCookie
var endDate;	//populated byb setCookie	

var populateBills = function(){
	jsRoutes.controllers.BillController.listBills().ajax({
		success: function(data){
			setCookie();
			var html=[];
			console.log(beginDate + "  " + endDate);
			$.each(data, function(i, bill){
				bills[bill.id]=bill;
			});

			updateTableDisplay();
			addTableSorter();

		}
	});
	
};

//
var displayBills = function(){
	var html=[];
	$.each(bills, function(i, bill){
		if((!beginDate && !endDate) || (bill.dueDate>=beginDate && bill.dueDate <endDate))
			html.push(generateBillRow(bill));
	});
	$('#bills-table tbody').html(html.join(''));
	updateTableSort(true);
	$('.datepicker').datepicker();
	
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
			if($.isArray(data)){
				$.each(data, function(i, bill){
					bills[bill.id] = bill;
				});
			}
			else{
				bills[data.id] = data;
			}
			displayBills();

			
			//add datepicker to newly added bills
			//updates tale but doesnt sort
			updateTableSort(false);
		}
	});
};

var addBillCallback = function(e){
	//generates form html for adding a row
	var row = generateBillFormRow();
	$('#bills-table tbody').prepend(row);
	//add date picker to the data box
	$('.addRow .datepicker').datepicker();
};

var generateBillRow = function(bill){
	var date = new Date(bill.dueDate);
	var paid='';
	if(bill.paid){
		paid = 'billPaid'
	}
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
	if(bill.isRecuring){
		returnBill+=' checked';
	}
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

var updateBill = function(paid){
	var data = {};
	if (paid){
		data.paid = true;
		data.financeFolder = $('#payBillFolder').val();
		console.log(data);
	}
	else {
		data.paid = false;
	}
	data.title = $('#payBillTitle').val();
	data.dueDate = $('#payBillDate').val();
	data.amount = $('#payBillAmount').val();
	data.id = $('#payBillId').val();
	
	jsRoutes.controllers.BillController.updateBill(data.id).ajax({
		data: data,
		success: function(data){
			//update data in bills table
		}
	});
		
}
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
			updateTableSort(false);
			
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
 * Pay Bill
 */
var openPayBill = function(){
	
	var row,title, date, amount, id;
	//get data from bill row being paid
	row = $('#bills-table .actionBox input[type=checkbox]:checked:first').closest('tr');
	if(row.length != 0){
		title = row.find('.billTitle').html();
		date = row.find('.billDate').html();
		amount = row.find('.billAmount').html();
		id = row.attr('data-id');
		
		//populate form with data from bill
		console.log(title+ " " + date + " " + amount);
		$('#payBillTitle').val(title);
		$('#payBillDate').val(date);
		$('#payBillAmount').val(amount);
		$('#payBillId').val(id);
		
		//get list of folders to insert new bill expense into
		jsRoutes.controllers.FinanceFolderController.listFolders().ajax({
			success : function(data) {
				$('#payBillFolder').empty();
				$.each(data, function(i, folder) {
					$('#payBillFolder').append(new Option(folder.name, folder.name));
				});
			}
		});
		
		dialog.dialog('open');
	}
	
};
var payBill= function(){
	updateBill(true);
	dialog.dialog("close");
};

var dialog = $('#bill-pay-form').dialog({
	autoOpen: false,
	height: 400,
	width: 500,
	modal: true,
	buttons: {
		"Pay Bill":  {
			click: payBill,
			text: 'Pay Bill',
			'class': 'btn btn-default'
		},
		Cancel: {
			click: function(){
				dialog.dialog("close");
			},
			text: 'Cancel',
			'class': 'btn btn-default'
		}
	}
});

/**
*MISC FUNCTIONS
*/

var setCookie = function(selectBox){
	if ($(selectBox).attr('id') == 'tableMonth'){
		$.cookie('billMonth', $(selectBox).val());
	}
	else if ($(selectBox).attr('id') == 'tableYear'){
		$.cookie('billYear', $(selectBox).val());
	}
	//If month selected is not all but year is, then set year to current year
	if($.cookie('billMonth')!=="all" && $.cookie('billYear')=="all" ){
		$('#tableYear').val((new Date()).getFullYear());
		$('#tableYear').change();
	}
	
	updateTableDisplay();
};

var setTableDateRange = function(){
	var monthCookie =$.cookie("billMonth");
	var yearCookie = $.cookie("billYear");
	console.log(monthCookie + " " +yearCookie);
	if(monthCookie=='all' && yearCookie == 'all'){
		beginDate = null;
		endDate = null ;
	}
	else if(monthCookie=='all' && yearCookie!='all'){
		beginDate = new Date(yearCookie, 0 ,1).valueOf();
		endDate = new Date(parseInt(yearCookie)+1,0,1).valueOf();
	}
	else{
		beginDate = new Date(yearCookie, monthCookie).valueOf();
		monthCookie==11 ? endDate = new Date(parseInt(yearCookie)+1, 0).valueOf() : endDate = new Date(yearCookie, parseInt(monthCookie)+1).valueOf();
	}
	
	
};

var updateTableDisplay= function(){
	setTableDateRange();
	displayBills();
};

var updateTableDateRange = function(){
	var selectBox = this;
	setCookie(selectBox);
	
}

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

var updateTableSort= function(resort){
	var table = $('#bills-table');
	//tells table sorter that table has been updated and resorts it if resort == true
	table.trigger("update", [ resort ]);
};




$('#bills-table').on('click', '#delete',deleteBillCallback);
$('#bills-table').on('click', '#updateButton', updateBillCallback);
$('#bills-table').on('click', '#add', addBillCallback);
$('#bills-table').on('click', '.save', saveRow);
$('#bills-table').on('click', '#payButton', openPayBill);
$('#tableMonth').on('change', updateTableDateRange);
$('#tableYear').on('change', updateTableDateRange);
//set select box to previous settings
$('#tableYear').val($.cookie('billYear'));
$('#tableMonth').val($.cookie('billMonth'));
populateBills();


});