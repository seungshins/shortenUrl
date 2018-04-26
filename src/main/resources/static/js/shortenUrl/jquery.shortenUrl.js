$(function() {
	shortenUrl.init();
});

var grid, dialog;
var shortenUrl = {
		init : function() {
			grid = $('#jqGrid').grid({
                primaryKey: 'ID',
                dataSource: '/api/shortenUrl/retrieveShortenUrlList',
                uiLibrary: 'bootstrap',
                columns: [
                    { field: 'index', width: 60, sortable: true },
                    { field: 'originUrl', title: 'Origin URL' },
                    { field: 'shortenUrl', title: 'Shorten URL'},
                    { title: '', field: 'Delete', width: 34, type: 'icon', icon: 'glyphicon-remove', tooltip: 'Delete', events: { 'click': shortenUrl.Delete } }
                ],
                sortname:'index',
                sortorder:"desc",
                pager:{ limit: 5, sizes: [2, 5, 10, 20] }
            });
            
			dialog = $('#dialog').dialog({
                uiLibrary: 'bootstrap',
                autoOpen: false,
                resizable: false,
                modal: true
            });
            
            $('#btnAdd').on('click', function () {
                $('#txtOriginUrl').val('');
                $('#txtShortenUrl').val('');
                $('#inOriginUrl').val('');
                dialog.open('Create new shortenUrl');
            });
            
            $('#btnSave').on('click', shortenUrl.Save);
            
            $('#btnCancel').on('click', function () {
                dialog.close();
            });
            
            $('#btnSearch').on('click', function () {
                grid.reload({ originUrl: $('#txtOriginUrl').val(), shortenUrl: $('#txtShortenUrl').val() });
            });
            
            $('#btnClear').on('click', function () {
                $('#txtOriginUrl').val('');
                $('#txtShortenUrl').val('');
                grid.reload({ originUrl: '', shortenUrl: '' });
            });
		},
		Save : function(e) {
			console.log("create new url");
			var record = {
					originUrl: $('#inOriginUrl').val()
	            };
            $.ajax({ url: '/api/shortenUrl/', data: { originUrl: record.originUrl }, method: 'POST' })
                .done(function () {
                    dialog.close();
                    grid.reload();
                })
                .fail(function () {
                    alert('Failed to save.');
                    dialog.close();
                });
		},

		
		Delete : function(e) {
			console.log("delete url", e);
			if (confirm('Are you sure?')) {
                $.ajax({ 
                	url: '/api/shortenUrl/deleteShortenUrl', 
                	type: 'POST',
                	data: { index: e.data.record.index, originUrl: e.data.record.originUrl }
                	})
                    .done(function () {
                        grid.reload();
                    })
                    .fail(function () {
                        alert('Failed to delete.');
                    });
            }
		}
}
