<script> 
F.ADD_ITEM.onChange (fnSAMORB);
 
var relArr = [];
var loadFirstTime = 'X';
var grcReportDiv, cntORB = 1, recPerPage = 20, supplemText;

var myDirectfn = function (opts, fn, proxy)
{
	var start = opts.start,
	end = opts.limit + opts.start,
	data = [];
	if (end > relArr.length) 
		end = relArr.length;
	for (var i = start; i < end; i ++ )
	data.push(relArr[i]);
	data.total = relArr.length;
	fn(data,
	{
		status : true,
		result : data
	});
};

var relStore = new Ext.data.GroupingStore(
{
	data : relArr,
	pageSize : recPerPage,
	sortInfo :
	{
		field : 'ObjectName',
		direction : "ASC"
	},
	proxy : new Ext.data.DirectProxy(
	{
		directFn : myDirectfn
	}),
	groupField : 'ObjectType',
	reader : new Ext.data.ArrayReader(
	{
		id : 'ObjectID'
	}, 
	['ObjectType', 'ObjectName', 'ObjectID', 'Comments', 'Valid_From', 'Valid_Until', 'Row_Count', 'Location', 'applicableTo', 'additionalDetails', 'formUrl'])
});
/*
relStore.on('load', function(store, recs, options) {
		if(recs.length==0){
				//bbar.bindStore([]);
				Ext.getCmp('CmbID').disable();
			}
			else{
				//bbar.bindStore(store);
				Ext.getCmp('CmbID').enable();
			}
});
*/
// Adds the ability for single level grouping to the grid
var gridGroupView = new Ext.grid.GroupingView(
{
	forceFit : true,
	groupTextTpl : '{text} [{[values.rs.length]} {[values.rs.length> 1 ? "Item(s)" : "Item"]}]'
});

// Start of extReady function
function extReady()
{
	Ext.QuickTips.init();
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	Ext.ux.grid.filter.StringFilter.prototype.icon = '@WEBROOT@/ext/GridFilters/images/find.png';
	Ext.ux.menu.RangeMenu.prototype.icons =
	{
		gt : 'images/greater_than.png',
		lt : 'images/less_than.png',
		eq : 'images/equals.png'
	};
	// configure whether filter query is encoded or not (initially)
	var encode = false;
	// configure whether filtering is performed locally or remotely (initially)
	var local = true;
	var filters = new Ext.ux.grid.GridFilters(
	{
		encode : encode, // json encode the filter query
		local : local,
		filters : [
			{
				type : 'string',  dataIndex : 'ObjectName'
			}
		]
	});
	var sm = new Ext.grid.CheckboxSelectionModel(
	{
		listeners :
		{
			beforerowselect : function(sm, rowIndex, keep, rec)
			{
				if ((edit_flag == 'N') || ((edit_flag == 'Y') && (invokedFromDataBrowser === true)))
				{
					Ext.getCmp('btnDelete').disable();
					return false;
				}
			}
		}
	});
	// declaring template
	var expander = new Ext.ux.grid.RowExpander(
	{
		
		tpl : new Ext.Template(
		'<p>&nbsp;&nbsp;&nbsp;ID:&nbsp;<i>{ObjectID}</i></p>',
		'<p><i>&nbsp;&nbsp;{additionalDetails}</i></p>')
	});
	
	// below function is to change cell color fo applicable to based on library type
	var RenderColor = function(value, meta, record, rowIndex)
	{
		var color ;

		if (record.data.ObjectType == 'Applies to Organizations' )
		{
			cm.setHidden(9,false); 
		}

		if (record.data.ObjectType != 'Applies to Organizations')
		{
			color = '#D8D8D8';
			try
			{
				meta.attr = 'style="background-color:'+ color +';"';
			}
			catch(err)
			{
			}
		}
		// below block is hardcoded to return combobox value as user selected
		else if ((record.data.ObjectType == 'Applies to Organizations' ) && value !== "")
		{	 
			if (value == 1)
			{
				return 'Selected Org';
			}
			else if (value == 2)
			{
				return 'Selected Org & Child Orgs';
			}
		}
	};
	
	// below function is to change cell color of Location based on library type
	var RenderColorForLoc = function(value, meta, record, rowIndex)
	{
		var color ;
		if ((record.data.ObjectType == 'Applies to Organizations') && (F.DD_OBJECT_TYPE.read() == 'MS_GRC_CONTROL'))
		{
			cm.setHidden(8,false); 
		}

		if ((record.data.ObjectType != 'Applies to Organizations') || ( F.DD_OBJECT_TYPE.read() != 'MS_GRC_CONTROL'))
		{
			color = '#D8D8D8';
			try
			{
				meta.attr = 'style="background-color:'+ color +';"';
			}
			catch(err)
			{
			}
		}
		// below block is hardcoded to return combobox value as user selected
		else if ((record.data.ObjectType == 'Applies to Organizations' ) && value !== "")
		{	 
			if (value == 1)
			{
				return 'Location 1';
			}
			else if (value == 2)
			{
				return 'Location 2';
			}
			else if (value == 3)
			{
				return 'Location 3';
			}
			else if (value == 4)
			{
				return 'Location 4';
			}
		}
	};
   
	// tool tip for showing number of characters can be entered
	var InputTip = new Ext.Tip(
	{
		id : 'InputTip',
		maxWidth : 500,
		items : [
			{
				xtype : 'box',
				autoEl :
				{
					tag : 'div',
					html : '',
					autoWidth : true,
					autoHeight : true
				}
			}
		]
	});
   
	// to fade the tool tip
	InputTip.HideTask = new Ext.util.DelayedTask(function ()
	{
		InputTip.el.fadeOut(
		{
			endOpacity : 0,
			// can be any value between 0 and 1 (e.g. .5)
			easing : 'easeOut',
			callback : function ()
			{
				InputTip.hide();
			},
			scope : InputTip,
			duration : 0.5,
			remove : false,
			useDisplay : true
		});
	});
	
	// for pagination
	myDirectfn.directCfg =
	{
		method :
		{
			len : 1
		}
	};
	// for pagination
	// below renderer is to show the selected display value in combobox.
	// This is to override default behaviour of combo box if showing stored value
	Ext.util.Format.comboRenderer = function (combo)
	{
		return function (value)
		{
			var record = combo.findRecord(combo.valueField, value);
			return record ? record.get(combo.displayField) : combo.valueNotFoundText;
		};
	};
	var comboLoc = new Ext.form.ComboBox(
	{
		store : new Ext.data.ArrayStore(
		{
			fields : ['dispVal', 'storedVal'],
			data : [
			['Location 1', '1'],
			['Location 2', '2'],
			['Location 3', '3'],
			['Location 4', '4']
			]
		}),
		displayField : 'dispVal',
		valueField : 'storedVal',
		mode : 'local',
		typeAhead : false,
		triggerAction : 'all',
		lazyRender : true,
		emptyText : 'Select Relation'
	});
	var comboApp = new Ext.form.ComboBox(
	{
		store : new Ext.data.ArrayStore(
		{
		fields : ['dispVal', 'storedVal'],
		data : [
		['Selected Org', '1'],
		['Selected Org & Child Orgs', '2']
		]
		}
		),
		displayField : 'dispVal',
		valueField : 'storedVal',
		mode : 'local',
		typeAhead : false,
		triggerAction : 'all'
	});
	var comboPage = new Ext.form.ComboBox(
	{
	    //id:'CmbID',
		name : 'perpage',
		width : 60,
		store : new Ext.data.SimpleStore(
		{
			fields : ['id'],
			data : [
			['20'],
			['40'],
			['80'],
			['All']
			]
		}),
		mode : 'local',
		value : recPerPage,
		listWidth : 40,
		triggerAction : 'all',
		displayField : 'id',
		valueField : 'id',
		editable : false,
		forceSelection : true
	});
	
	var cm = new Ext.grid.ColumnModel(
	{
		columns : [
		expander, sm,
		{
			header : "$Type",
			width : 110,
			sortable : true,
			hidden : true,
			dataIndex : 'ObjectType'
		},
		{
			header : "$Name",
			width : 380,
			sortable : true,
			hideable : false,
			dataIndex : 'ObjectName',
			renderer : function formLink(value, metaData, record, rowIndex, colIndex, store)
			{
				var linkVal = record.get('formUrl');
				
				if ((record.get('ObjectType') != 'Applies to Organizations') && (record.get('ObjectType') != 'Assertions'))
				{
					return '<a target="_blank" href=' + linkVal + ' style ="color:#003377">' + value  + '</a>';
				}
				else
				{
					return value;
				}
			}
		},
		{
			header : 'Library ID',
			width : 10,
			sortable : true,
			dataIndex : 'ObjectID',
			hidden : true /* , editor : {xtype : 'textfield', allowBlank : false} */
		},
		{
			header : "$Valid_From",
			width : 1,
			sortable : true,
			hideable : false,
			hidden : true,
			renderer : Ext.util.Format.dateRenderer('d/m/Y'),
			dataIndex : 'Valid_From',
			editor :
			{
				xtype : 'datefield',
				allowBlank : true
			}
		},
		{
			header : "$Valid_Until",
			width : 1,
			sortable : true,
			hideable : false,
			hidden : true,
			renderer : Ext.util.Format.dateRenderer('d/m/Y'),
			dataIndex : 'Valid_Until',
			editor :
			{
				xtype : 'datefield',
				allowBlank : true
			}
		},
		{
			header : "$Row_Number",
			width : 5,
			sortable : true,
			dataIndex : 'Row_Count',
			hidden : true,
			xtype : 'numbercolumn',
			format : 0
		},
		{
			header : "$Location",
			width : 70,
			sortable : true,
			hideable : false,
			dataIndex : 'Location',
			editor : comboLoc,
			//renderer : Ext.util.Format.comboRenderer(comboLoc),	// This is to override default behaviour of combo box if showing stored value
			renderer : RenderColorForLoc
		},
		{
			header : "$Applicable_To",
			width : 400,
			sortable : true,
			hidden : false,
			dataIndex : 'applicableTo',
			editor : comboApp,
			//renderer : Ext.util.Format.comboRenderer(comboApp), // This is to override default behaviour of combo box if showing stored value
			renderer : RenderColor
		},{
			header : "$Additional_Details",
			width : 150,
			height : 100,
			sortable : false,
			hidden : true,
			dataIndex : 'additionalDetails'
		},{
			header : "$Comments",
			width : 250,
			sortable : true,
			dataIndex : 'Comments',
			// below rendered to show comments text as tooltip if any when on mouseover
			renderer : function renderDescription(value, metaData, record, rowIndex, colIndex, store)
			{
				try
				{
				
				metaData.attr = 'ext:qtip="' + value + '"';
				return value;
				}
				catch(err)
				{
				}
			},
			editor :
			{
				xtype : 'textarea',
				boxMaxHeight : 46,
				maxLength : 4000,
				allowBlank : true,
				maxLengthText : 'Comments exceeded maximum length 4000',
				enableKeyEvents : true,
				listeners :
				{
					// imposes a max length on this textarea even if the value is pasted in.
					'keyup' : function ()
					{
					var value = this.getValue();
					if ((this.maxLength - value.length) < 100)
					{
						InputTip.showBy(this.id, 'tl-bl');
						InputTip.body.update('Comments field accomodates ' + (this.maxLength - value.length) + ' characters');
						InputTip.HideTask.delay(2000);
					}
					if (value.length >= this.maxLength) this.setValue(value.slice(0, value.length - (value.length - this.maxLength)));
					}
				}
			}
		}
		],
		isCellEditable : function (col, row)
		{
      var record;
			var field = relGrid.getColumnModel().getDataIndex(col);
			if (field === 'Location')
			{
				record = relStore.getAt(row);
				if (F.DD_OBJECT_TYPE.read() != 'MS_GRC_CONTROL')
				{
					return false;
				}
				else if
				(	(record.get('ObjectType') != 'Applies to Organizations') ||
				((record.get('ObjectType') == 'Applies to Organizations') &&
				((edit_flag == 'N') || ((edit_flag == 'Y') && (invokedFromDataBrowser === true))))
				)
				{
					return false;
				}
			}
			if (field === 'applicableTo')
			{
				record_app = relStore.getAt(row);
				if (	(record_app.get('ObjectType') != 'Applies to Organizations') ||
				((record_app.get('ObjectType') == 'Applies to Organizations') &&
				((edit_flag == 'N') || ((edit_flag == 'Y') && (invokedFromDataBrowser === true))))
				)
				{
					return false;
				}
			}
			// below block is to disable comments field when it is in published mode
			if (field === 'Comments')
			{
				record = relStore.getAt(row);
				if ((edit_flag == 'N') || ((edit_flag == 'Y') && (invokedFromDataBrowser === true)))
				{
					return false;
				}
			}
			return Ext.grid.ColumnModel.prototype.isCellEditable.call(this, col, row);
		}
	});
	var bbar = new Ext.PagingToolbar(
	{
		store : relStore,
		pageSize : recPerPage,
		displayInfo : true,
		items : ['-', "$Objects_per_page", comboPage],
		// displayMsg : '$Displaying_objects {0} - {1} $of {2}',
		emptyMsg : "$No_objects_to_show"
	}
	);
	comboPage.on('select', function (comboPage, record)
	{
		if ('All' == record.get('id'))
		{
			bbar.pageSize = this.relStore.getTotalCount();
		}
		else
		{
			bbar.pageSize = parseInt(record.get('id'), 10);
		}
		bbar.pageSize=Math.max(bbar.pageSize,1);
		recPerPage = bbar.pageSize;
		bbar.doLoad(0);
	},this);
   // create the Grid to generate the grid's user interface
   var relGrid = new Ext.grid.EditorGridPanel(
   {
      store : relStore,
      id : 'relGrid', // used in priniting option to call printer
      clicksToEdit : 1,
    //  autoScroll : true,
      plugins : [expander, filters],
      groupField : 'ObjectType',
      cm : cm,
      sm : sm,
      animCollapse : false,
      autoExpandColumn : 'ObjectType',
      tbar : [
      {
         text : '<span style="padding-left:15px;padding-top:15px;">${delete_rows}<span>',
         id : 'btnDelete',
         tooltip : 'Remove the selected item',
         iconCls : 'msai_delete',
         handler : function ()
         {
            // to remove selected records
            var sm1 = relGrid.getSelectionModel();
            var sel = sm1.getSelections();
            var selectedRowIndexes = [];
            var new_index1;
            // for(i = 0; i < sel.length; i ++ ){
            Ext.iterate(sel, function (banner, index)
            {
               new_index1 = relGrid.getStore().indexOf(banner);
               // push the row indexes into your array
               var rec = relGrid.getStore().getAt(new_index1);
               // calling platform function to mark a row as selected for deletion
               setMarkedDeleteRow(F.ORB.getID(), rec.get('Row_Count'), rec.get('Row_Count'));
         //      F.log('Object ' + F.TGT_OBJ_NAME.read(rec.get('Row_Count')) + ' deleted succesfully');
               // below for loop is to remove record from array
               for (var i = 0; i < relArr.length; i ++ )
               {
                  // use === to check for Matches. ie., identical ( === ), ;
                  if (relArr[i][2] ==  rec.get('ObjectID'))
                  {
                     // relArr[i][2] -- 2 indicates ObjectId position
                     relStore.removeAt(new_index1);
                     relArr.splice(i, 1);
                     break;
                  }
               }
               // for loop to decrement row number. This came up when tried to delete a record, submit & update a value after opening from assignments
               for (var cnt1 = rec.get('Row_Count') + 1; cnt1 <= F.ORB.getRowCount();
               cnt1 ++ )
               {
                  if ( ! F.ORB.isMarkedForDeletion(cnt1))
                  {
                     F.ROW_NUM.write(F.ROW_NUM.read(cnt1) - 1, cnt1);
                  }
               }
            }
            );
            // below loadData has to be out of iterate loop as it is disturbing current array if it is given
            // inside for loop
            relStore.loadData(relArr);
            // }
         },scope : this
      },
	  {
         xtype : 'tbseparator'
      },
	  {
         text   : "$Expand_Collapse",
         menu :
         {
            xtype : 'menu',
            items : [
            {
               text : "$Expand_All",
               iconCls : 'msai_expandall',
               tooltip : 'Expand Rows',
               handler : function()
               {
                  for(i = 0; i < relGrid.getStore().getCount();
                  i ++ )
                  {
                     expander.expandRow(i);					   
                  }
               }
            },
			{
               text : "$Collapse_All",
               iconCls : 'msai_collpaseall',
               tooltip : 'Collapse All Rows',
               handler : function()
               {
                  for(i = 0; i < relGrid.getStore().getCount();
                  i ++ )
                  {
				  
                     expander.collapseRow(i);
                     relGrid.getView().refresh(true);
					 
                  }
               }
            }
            ]
         }
      },
	  {
         xtype : 'tbseparator'
      },
	  {
         xtype : 'tbseparator'
      },
	  {
         id : 'clearFilter',
         text   : "$Clear_Filters",
         tooltip : 'Clear Filtered Data',
         iconCls : 'msai_filter',
         handler : function()
         {
            relGrid.filters.clearFilters();
         }
      }
      ],	  
      autoHeight : false,
//	   layout : 'fit',
      width : 740,
	  height : 240,
      // config options for stateful behavior
      stateful : true,
      // The grid is stateful so you can move or hide columns, reload the page, and come back to the grid in the same state you left it in.
      region : 'center',
      stateId : 'grid',
      
      bbar : bbar,
      view : gridGroupView,
      // forceFit : true,
      // Add a listener to load the data only after the grid is rendered :
      listeners :
      {
         render : function ()
         {
		 
            relGrid.getView().refresh(true);
            var initParams = Ext.apply(
            {
            },
            {
               start : 0,
               limit : recPerPage
            }
            );
            this.store.load(
            {
               params : initParams
            }
            );
         },
		 'beforerender' : function(grid) {
            cm.setHidden(8,true);
			cm.setHidden(9,true);
			},
         afteredit : function (e)
         {
            /* below code is changed from selectionmodel to getStore on 08 - FEb - 2012
            // for issue : user entered comments in 1st row.. but, without completing user selected other row
            // in the grid
            var sm = relGrid.getSelectionModel();
            sel = sm.getSelected();
            */
            // e.row returns row number
            var recUpd = relGrid.getStore().getAt(e.row);
            // alert(recUpd.get('ObjectName') + ': ' + recUpd.get('Comments'));
            F.REL_COMMENTS.write(recUpd.get('Comments'), recUpd.get('Row_Count'));
            F.REL_VALID_FROM.write(recUpd.get('Valid_From'), recUpd.get('Row_Count'));
            F.REL_VALID_UNTIL.write(recUpd.get('Valid_Until'), recUpd.get('Row_Count'));
            F.SELF_REL_TYPE.write(recUpd.get('Location'), recUpd.get('Row_Count'));
            F.APPLICABLE_TO.write(recUpd.get('applicableTo'), recUpd.get('Row_Count'));
            // below array modification is to modify the store array so as to get modified values when user tried to refresh grid
            for (var i = 0; i < relArr.length; i ++ )
            {
               // use === to check for Matches. ie., identical ( === ), ;
               if (relArr[i][2] ==  recUpd.get('ObjectID'))
               {
                  // relArr[i][2] -- 2 indicates ObjectId position
                  relArr[i][3] = recUpd.get('Comments');
                  relArr[i][4] = recUpd.get('Valid_From');
                  relArr[i][5] = recUpd.get('Valid_Until');
                  relArr[i][7] = recUpd.get('Location');
                  relArr[i][8] = recUpd.get('applicableTo');
                  e.record.commit();
                  break;
               }
            }
         }
      }
   }
   );
   relGrid.render('MSAI_MULTI_ORB');
}

// End of extReady function
// functionality to report link id
/*
function getReportLinkId()
{
   var className = 'msai_attached_report_name';
   var hasClassName = new RegExp("(?:^|\\s)" + className + "(?:$|\\s)"); 
   var allElements = document.getElementsByTagName("span");
   var results = [];
   var element;
   for (var i = 0;
   (element = allElements[i]) !== null;
   i ++ )
   {
   
  
      var elementClass = element.className;
      if (elementClass && elementClass.indexOf(className) != - 1 && hasClassName.test(elementClass))
      {
         results[element.innerHTML] = element.parentNode;
      }
	 
   }
   return results;
}*/

 function getReportLinkId() {
	 alert("in getReportLinkId");
            var className = 'msai_attached_report_name';
            var hasClassName = new RegExp("(?:^|\\s)" + className + "(?:$|\\s)");
            var allElements = document.getElementsByTagName("span");
            var results = [];
            var element;
            for (var i = 0;
                (element = allElements[i]) !== null && (element = allElements[i]) !==undefined; i++){
                var elementClass = element.className;
                if (elementClass && elementClass.indexOf(className) !== -1 && hasClassName.test(elementClass)) {
                    results[element.innerHTML] = element.parentNode;
                }
            }
            return results;
        }
        

// end of functionality to report link id
// Start of functionality to get div id of a linked report
function getReportDiv(repId)
{
	
	console.log("Giving the repID");
   grcReportDiv = grcReportDiv || getReportLinkId();
   for (var i in grcReportDiv)
   {
	   console.log(repId);
      if (repId == i) return grcReportDiv[i];
   }
   return false;
}
// end of functionality to get div id of a linked report
// Function to show / hide report links based on selected configuration


function hideReportLinks()
{
//addObjId = 'MSAI_134';
//F.getObject(addObjId).style.display="none";


 	
// alert('calling hideReportLinks');
  
   divelem = getReportDiv(('$Add_Risks')); 
   if (divelem !== null && divelem) { divelem.style.display = 'none';}
     divelem = getReportDiv(('$Add_Asset_Classes'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('$Add_Financial_Accounts'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('$Add_Assets'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('$Add_References'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('$Add_Requirements'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Framework References'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Areas of Compliance'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Objectives'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Functions'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Regulatory Bodies'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Processes'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Controls'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Organizations'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Assertions'));
   if (divelem !== null && divelem) divelem.style.display = 'none';
   divelem = getReportDiv(('Add Standards'));
   if (divelem !== null && divelem) divelem.style.display = 'none';

}
function showHideReportLinks()
{
//alert('showHideReportLinks 1');
   var divelem;
   hideReportLinks();
   var objType = F.ADD_ITEM.readValue().toUpperCase();
   //alert (edit_flag+'   '+invokedFromDataBrowser);

   supplemText = '';
 
   //alert('showHideReportLinks 2');
   // hideReportLinks();
   //alert('showHideReportLinks 3');
   // below if block is to not to show report links if edit is read only mode
   /*if ((edit_flag == 'N') || ((edit_flag == 'Y') && (invokedFromDataBrowser == true)))
   {
   //alert('showHideReportLinks 4'); 
     hideReportLinks();
   //alert('showHideReportLinks 5');
   }
   else
   {*/
   
      if (objType == 'RISKS')
      {
	//alert("Inside risks.");
         divelem = getReportDiv('$Add_Risks');
		 alert(divelem);
		 if(null !== divelem && divelem !== false)
			divelem.style.display = '';
			
         //divelem = getHyperLinkDiv(('Add Risks'));
      }
      else if (objType == 'REFERENCES')
      {	  
         divelem = getReportDiv('$Add_References');
		if(null !== divelem && divelem !== false)
         divelem.style.display = '';		 
       //  supplemText = 'Reference Type: \n';
      }
      else if (objType == 'REQUIREMENTS')
      {
		  
         divelem = getReportDiv(('$Add_Requirements'));
		 if(null !== divelem && divelem !== false)
         divelem.style.display = '';
		 // supplemText = 'Reference Type: \n';
	  
      }
      else if (objType == 'FRAMEWORK REFERENCES')
      {
         divelem = getReportDiv(('$Add_Framework_References'));
		if(null !== divelem && divelem !== false)
         divelem.style.display = '';
         //supplemText = 'Framework Reference Type: \n';
      }
      else if (objType == 'AREAS OF COMPLIANCE')
      {
         divelem = getReportDiv(("$Add_Areas_of_Compliance"));
		 if(null !== divelem && divelem !== false)
         divelem.style.display = '';
         supplemText = 'Requirement(s): \n';
      }
      else if (objType == 'OBJECTIVES')
      {
         divelem = getReportDiv(('$Add_Objectives'));
		if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'FUNCTIONS')
      {
         divelem = getReportDiv(('$Add_Functions'));
		 if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'ASSETS')
      {
         divelem = getReportDiv(('$Add_Assets'));
		if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'REGULATORY BODIES')
      {
         divelem = getReportDiv(('$Add_Regulatory_Bodies'));
		 if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'PROCESSES')
      {
         divelem = getReportDiv(('$Add_Processes'));
		if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'CONTROLS')
      {
		  alert("$Add_Controls");
         //divelem = getReportDiv(('Add Controls'));
		 divelem = getReportDiv('$Add_Controls');
		 if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'APPLIES TO ORGANIZATIONS')
      {
         divelem = getReportDiv(('$Add_Organizations'));
		if(null !== divelem && divelem !== false)
         divelem.style.display = '';
         //supplemText = 'Owner(s): \n';
      }
      else if (objType == 'FINANCIAL ACCOUNTS')
      {
         divelem = getReportDiv(('$Add_Financial_Accounts'));
		if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'ASSET CLASSES')
      {
         divelem = getReportDiv(('$Add_Asset_Classes'));
		 if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
      else if (objType == 'ASSERTIONS')
      {
         divelem = getReportDiv(('$Add_Assertions'));
	if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }
	  else if (objType == 'STANDARDS')
      {
         divelem = getReportDiv(('$Add_Standards'));
		 if(null !== divelem && divelem !== false)
         divelem.style.display = '';
      }

 //  }
}
// End of Function to show / hide report links based on selected configuration
// function called from the report to push the selected values
function callParentORB(storedValues, displayValues)
{
	
   F.log('received stored values' + storedValues);
   F.log('received display values' + displayValues);
   var dispVal = displayValues.split("~");
   var origstorVal = storedValues.split("~");
   var supplemVal1 = '';
   var supplemId1 = '';
   var objExistsAlertFlag = false;
   var objExists = 'N';
   var objExistsName = '';
   var j;
   F.log('length:' + origstorVal.length);
   F.log('origstorVal[i-1]' + origstorVal[i - 1]);
   if (origstorVal[0] !== "")
   {

      if ((loadFirstTime == 'X') && (Number(F.ORB.getRowCount()) === 0))
      {
         loadFirstTime = 'Y';
      }
      else
      {        
 // below block is to assign max row number + 1 to the new record
         // this allows to make all newly added records with sequential row number
         var cntUnMarkedForDel = 0;
         // alert ('in else' + F.ORB.getRowCount());
         for (idx = 1; idx <= F.ORB.getRowCount(); idx ++ )
         { 
            if( ! F.ORB.isMarkedForDeletion(idx))
            {
               // alert('record not marked for deletion for idx' + F.TGT_OBJ_NAME.read(idx));
               cntUnMarkedForDel = cntUnMarkedForDel + 1;
            }
         }
         // alert('cntUnMarkedForDel: ' + cntUnMarkedForDel);
         cntORB = cntUnMarkedForDel + 1;
         loadFirstTime = 'N';
      }
      for (var i = 1; i <= origstorVal.length; i ++ )
      {
         var tgtObjName;
         var tgtObjId;
         F.log('dispVal[i - 1] ' + dispVal[i - 1]);
         var supplemLink = dispVal[i - 1].split('$#$');

         F.log('supplemLink[1]: ' + supplemLink[1]);
         var supplemArr = supplemLink[0].split('^*^');
         if (
         (F.ADD_ITEM.readValue() != 'Applies to Organizations')  && (F.ADD_ITEM.read() != 'Areas of Compliance'))
         {
            // block to execute for other than AOC, Organizations
            for (var x = 1; x <= supplemArr.length; x ++ )
            {
               if (x == 1)
               {
                  tgtObjName = supplemArr[x - 1];
               }
               else if (x == 2)
               {
                  supplemVal1 = supplemArr[x - 1];
	        }
            }
            if (loadFirstTime == 'N')
            {
               objExists = 'N';
               for (j = 0; j < relArr.length; j ++ )
               {
                  // use === to check for Matches. ie., identical ( === ), ;
                  if (relArr[j][2] ==  origstorVal[i - 1])
                  {
                     objExists = 'Y';
                     objExistsAlertFlag = true;
                     if (objExistsName === "")
                     {
                        objExistsName = tgtObjName;
                     }
                     else
                     {
                        objExistsName = objExistsName + ', ' + tgtObjName;//alert(objExistsName);
                     }
                     break;
                  }
               }
               if (objExists == 'N')
               {
                  //console.log('callling itemdisplayorb from pos 1');
                  itemDisplayORB(cntORB, origstorVal[i - 1], tgtObjName, supplemVal1, '', supplemLink[1]);
                  cntORB = cntORB + 1;
               }
            }
            else
            {
               F.TGT_OBJ_ID.write(origstorVal[i - 1], cntORB);
               F.TGT_OBJ_NAME.write(tgtObjName, cntORB);
               F.ROW_NUM.write(cntORB, cntORB);
               //console.log('callling itemdisplayorb from pos 2');
               itemDisplayORB(cntORB, origstorVal[i - 1], tgtObjName, supplemVal1, '', supplemLink[1]);
               cntORB = cntORB + 1;
            }
         }
         else
         {
            // in else block if grc library type selected is either AOC or Organizations
            objExists = 'N';
            if (loadFirstTime == 'N')
            {
               // this condition passes when same org / aoc is selected from the report
               for (j = 0; j < relArr.length; j ++ )
               {
                  F.log('relArr[j][2] : ' + relArr[j][2]);
                  F.log('relArr[j][6] : ' + relArr[j][6]);
                  F.log('supplemArr[1] : ' + supplemArr[1]);
                  var objFoundAt = '';
                  // if (relArr[j][2] == supplemArr[1] && ((F.TGT_OBJ_TYPE.read(j) == 'Applies to Organization(s)') || (F.TGT_OBJ_TYPE.read(j) == 'Area(s) of Compliance'))) {
                  if (relArr[j][2] == supplemArr[1] && ((F.ADD_ITEM.readValue() == 'Applies to Organizations') || (F.ADD_ITEM.read() == 'Areas of Compliance')))
                  {
                     F.log('inside object exists');
                     objExists = 'Y';
                     objFoundAt = relArr[j][6];
                     // 6 gives row number from relArr
                     F.log('additional column1' + F.ADDITIONAL_DETAILS_WITH_ID.read(objFoundAt));
                     // var existValCheckArray = F.ADDITIONAL_DETAILS.read(j).split(',');
                     var existValCheckArray = F.ADDITIONAL_DETAILS_WITH_ID.read(objFoundAt).split(',');
                     F.log('existValCheckArray: ' + existValCheckArray);
                     F.log('origstorVal[i - 1]: ' + origstorVal[i - 1]);
                     var valExists = 'N';
                     valExists = 'N';
                     for (l = 0; l <= existValCheckArray.length;
                     l ++ )
                     {
                        F.log('existValCheckArray[l]: ' + existValCheckArray[l]);
                        if (existValCheckArray[l] == origstorVal[i - 1])
                        {
                           objExistsAlertFlag = true;
                           if (objExistsName === "")
                           {
                              objExistsName = supplemArr[0] + ': [' + supplemArr[2];
                           }
                           else
                           {
                              objExistsName = objExistsName + ', ' + supplemArr[2];
                           }
                           valExists = 'Y';
                           break;
                        }
                     }
                     if ((valExists != 'Y') && (origstorVal[i - 1] != '-1'))
                     {
                        // F.ADDITIONAL_DETAILS.write(F.ADDITIONAL_DETAILS.read(j) + ',' + origstorValDup[k], j);
                        F.ADDITIONAL_DETAILS.write(F.ADDITIONAL_DETAILS.read(objFoundAt) + ', ' + supplemArr[2], relArr[j][6]);
                        F.ADDITIONAL_DETAILS_WITH_ID.write(F.ADDITIONAL_DETAILS_WITH_ID.read(objFoundAt) + ',' + origstorVal[i - 1], relArr[j][6]);
                        // below code is to update modified value in the grid
                        var record = relStore.getAt(j);
                        record.set('additionalDetails', F.ADDITIONAL_DETAILS.read(objFoundAt));
                        // updating
                        relArr[j][9] = supplemText+F.ADDITIONAL_DETAILS.read(objFoundAt);
                        record.commit();
						
                     }
                  }
               }
            }
			
            if (objExists == 'N')
            {
               if (i == 1)
               {
                  tgtObjName = supplemArr[0];
                  tgtObjId = supplemArr[1];
               }
               F.log('Additional Name' + supplemArr[2]);
               if (origstorVal[i - 1] != '-1')
               {
                  // supplemArr[2] is for additional name
                  if (supplemVal1 === "")
                  {
                     // supplemVal1 = origstorVal[i - 1];
                     supplemVal1 = supplemArr[2];
                     supplemId1 = origstorVal[i - 1];
                  }
                  else
                  {
                     // supplemVal1 = supplemVal1 + ',' + origstorVal[i - 1];
                     supplemVal1 = supplemVal1 + ', ' + supplemArr[2];
                     supplemId1 = supplemId1 + ',' + origstorVal[i - 1];
                  }
               }
               if (i == origstorVal.length)
               {
                  //console.log('callling itemdisplayorb from pos 3');
                  itemDisplayORB(cntORB, tgtObjId, tgtObjName, supplemVal1, supplemId1, supplemLink[1]);
                  cntORB = cntORB + 1;
               }
            }
         }
      }
   }
   if (objExistsAlertFlag)
   {
      if ((F.ADD_ITEM.readValue() != 'Applies to Organizations') &&
      (F.ADD_ITEM.read() != 'Area(s) of Compliance'))
      {
         msgbox1(F.ADD_ITEM.readValue() + ': [' + objExistsName + '] is/are ignored; as they are already associated in the current content creation.');
      }
   /*   else if (F.ADD_ITEM.readValue() == 'Applies to Organization(s)')
      {
         msgbox1(objExistsName + '] is/are ignored; as they are already associated with the organization.');
      } */
      else if (F.ADD_ITEM.read() == 'Areas of Compliance')
      {
         msgbox1(objExistsName + '] is/are ignored; as they are already associated with Area of Compliance.');
      }
   }
   try{
   relStore.reload();
   }catch(e){
	
   }
}
// end of function callParentORB
F.ADD_ITEM.onChange = function ()
{


   showHideReportLinks();
};
// function to push records into multi - rows and data store
function itemDisplayORB(row, storedval, dispval, supplemVal1, supplemId1, form_url)
{

	//console.log(" row " + row + " storedval " + storedval + " dispval " + dispval + " supplemVal1 " + supplemVal1 + " supplemId1 " + supplemId1 );
   var orbRowCnt;
   F.ORB.addRow(true, true);
   orbRowCnt = F.ORB.getRowCount();
   F.TGT_OBJ_TYPE.write(F.ADD_ITEM.readValue(), orbRowCnt);
   F.TGT_OBJ_ID.write(storedval, orbRowCnt);
   F.TGT_OBJ_NAME.write(dispval, orbRowCnt);
   //F.REL_CONFIG_ID.write(F.ADD_ITEM.read(), orbRowCnt);
     F.REL_CONFIG_ID.write(orbRowCnt, orbRowCnt);
   F.ROW_NUM.write(row, orbRowCnt);
   F.ADDITIONAL_DETAILS.write(supplemVal1, orbRowCnt);
   F.ADDITIONAL_DETAILS_WITH_ID.write(supplemId1, orbRowCnt);
   F.ADDITIONAL_COLUMN5.write(F.ADD_ITEM.read(), orbRowCnt);
   F.ORB_FORM_URL.write(form_url, orbRowCnt);      
   if(F.ADD_ITEM.read() != 'Areas of Compliance')
		supplemVal1="";
   var relEntry = [F.TGT_OBJ_TYPE.read(orbRowCnt), // Library Type
   dispval, // Library Name
   storedval, // Library ID
   '', // Comments
   '', // Valid From
   '', // Valid Until
   orbRowCnt, // F.ORB.getRowCount(), // row Number
   '', // Relationship
   '', // Applicable to 
   supplemText + supplemVal1, // Additional Details
   form_url];
   relArr.push(relEntry);
   relStore.loadData(relArr);
   Ext.getCmp('relGrid').getStore().reload();
   // alert(F.ADDITIONAL_DETAILS_WITH_ID.read(row));
}
// end of function to push records into multi - rows and data store
function fnSAMORB()
{ 

  showHideReportLinks();
}
function GRC_OnLoad()
{	
    //variable to find emd value if form is opened via link in ORB grid
	parvalue=F.getFormParameter('emd');
	//alert(parvalue);
//	F.log("location.href.indexOf('wrapper=no')"+location.href.indexOf('wrapper=no'));
//	F.log("edit_flag  = " + edit_flag);
//	F.log("accessCode =  " + accessCode);
	//F.log("invokedfromDB = " + glob_invokedFromDataBrowser);
	//displaytoolbar();
	//F.OBJECT_NAME.onChange();     	
	//InitiateNewObject(); 
	//status_updation();
	//not calling function if form is opened via link in ORB grid
	//if (parvalue  !='3'){
//	showHideComments();}
	//objectCreationInfo();
	//objectModificationInfo();
	//getConfigParams();
	//approvercheck();
	//restrictedaccess();
	//defaultRestriction();
	//enableMultiRows();
	
	if (F.ORB)
	{	
	includeORB();	
	}
}


function includeORB()
{
alert("$Add_Controls");
	Ext.onReady(extReady);
	showHideReportLinks();	
	var mrDivId='MSAI_MULTI_DIV_'+F.ORB.getID();
	F.getObject(mrDivId).style.display='none';
	//alert(" F.ORB.getRowCount() " + F.ORB.getRowCount());
	if (F.REL_SOURCE_ID.read() === "")
	{
	F.REL_SOURCE_ID.write('NONE');
	F.REL_INST_ID.write('NONE',1);
	F.REL_SOURCE_OBJECT_ID.write('NONE'); //defaulting as if there is any field without any value, field is not appeared in xml
	}
	for (i=1;i<=F.ORB.getRowCount();i++)
	{
			//alert(" F.TGT_OBJ_ID.read(i) " + F.TGT_OBJ_ID.read(i));
			if(F.TGT_OBJ_ID.read(i)!== ""){
			//var OrgEntry = [F.TGT_OBJ_TYPE.read(i),F.TGT_OBJ_NAME.read(i),F.TGT_OBJ_ID.read(i),F.REL_COMMENTS.read(i),F.REL_VALID_FROM.read(i),F.REL_VALID_UNTIL.read(i),F.ROW_NUM.read(i),F.SELF_REL_TYPE.read(i),F.APPLICABLE_TO.read(i),F.ADDITIONAL_DETAILS.read(i)];
			//instead of row num value, write i value. Issue fixed for bug id 49199
			//console.log(" F.TGT_OBJ_TYPE.read(i) " +decodeHtmlContent( F.TGT_OBJ_TYPE.read(i)));
			//supplemText=
			var supplemTextTxt='';
			var ttttype=decodeHtmlContent(F.TGT_OBJ_TYPE.read(i)).toUpperCase();
			/* if (ttttype == 'REFERENCES')
			  {	  
				 supplemTextTxt = 'Reference Type: \n';
			  }
				else if (ttttype == 'FRAMEWORK REFERENCES')
			  {
				 supplemTextTxt = 'Framework Reference Type: \n';
			  } else*/ if (ttttype == 'AREAS OF COMPLIANCE')
			  {
				 supplemTextTxt = 'Requirements: \n';
			  }
			/*	else if (ttttype == 'APPLIES TO ORGANIZATIONS')
			  {
				 supplemTextTxt = 'Owners: \n';
			  }*/
	 // supplemTextTxt=
		var OrgEntry="";
			if (ttttype == 'AREAS OF COMPLIANCE')
				OrgEntry = [decodeHtmlContent(F.TGT_OBJ_TYPE.read(i)),F.TGT_OBJ_NAME.read(i),F.TGT_OBJ_ID.read(i),F.REL_COMMENTS.read(i),F.REL_VALID_FROM.read(i),F.REL_VALID_UNTIL.read(i),i,F.SELF_REL_TYPE.read(i),F.APPLICABLE_TO.read(i),supplemTextTxt+F.ADDITIONAL_DETAILS.read(i),F.ORB_FORM_URL.read(i)];
			 else
				 OrgEntry = [decodeHtmlContent(F.TGT_OBJ_TYPE.read(i)),F.TGT_OBJ_NAME.read(i),F.TGT_OBJ_ID.read(i),F.REL_COMMENTS.read(i),F.REL_VALID_FROM.read(i),F.REL_VALID_UNTIL.read(i),i,F.SELF_REL_TYPE.read(i),F.APPLICABLE_TO.read(i),supplemTextTxt,F.ORB_FORM_URL.read(i)];
			relArr.push(OrgEntry);
			relStore.loadData(relArr);
			}
	}
	
}

</script>
