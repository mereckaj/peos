#include "StartedProcesses.h"
#include "../rsc/AppResources.h"
#include <stdlib.h>
#include <string.h>
#include <events.h>
#include <process_table.h>

extern char * selection;

char ** list_instances (UInt16 * size)
{
	UInt16 i;

	/*char ** listElements2 = (char**) malloc (4*2);
	(char*) listElements2 [0] = "process1";
	(char*) listElements2 [1] = NULL;
	list = listElements2;
	*/
	
	//malloc last element to null
	char ** list = peos_list_instances ();
	for (i=0; list[i]; i++)
	{
		*size=i;
	}
	*size = i;
	
	/*if (i==10000)
		peos_list_instances();
	*/
	return list;
}


Boolean StartedProcessHandler (EventType * pEvent)
{
	Boolean 	handled = false;
	FormType * 	pForm;
	ControlType* ctl;
	ListType *	list2;
	UInt16 numChoices=0;
	char ** listElements2;
	
	
	switch (pEvent->eType) {
	case menuEvent:
		switch (pEvent->data.menu.itemID)
		{
		case 1801:
			pForm = FrmInitForm(1100);
			FrmDoDialog(pForm);					// Display the About Box.
			FrmDeleteForm(pForm);
			handled = true;
			break;
		case 1802:
			pForm = FrmInitForm (MainForm);
			FrmGotoForm (MainForm);
			FrmDeleteForm (pForm);
			handled=true;
			break;
		//available processes form
		case 1803:
			pForm = FrmInitForm(AvailableProcessesForm);			
			FrmGotoForm (AvailableProcessesForm);
			FrmDeleteForm(pForm);
			handled = true;
			break;
		default: break;
		}
			
		
	case frmOpenEvent:
		pForm = FrmGetActiveForm();
		list2 = FrmGetObjectPtr (pForm, FrmGetObjectIndex (pForm, StartedProcessesList));
		listElements2 = list_instances (&numChoices);
		LstSetListChoices (list2, listElements2, numChoices);
		LstSetSelection (list2, -1);
		FrmDrawForm(pForm);
		handled = true;
		break;
	
	case lstSelectEvent:
		//remember selection
		pForm = FrmGetActiveForm();
		list2 = FrmGetObjectPtr (pForm, FrmGetObjectIndex (pForm, StartedProcessesList));
		selection = LstGetSelectionText (list2, LstGetSelection (list2));
			
		ctl = FrmGetObjectPtr (pForm, FrmGetObjectIndex (pForm, 1301));
		CtlSetLabel (ctl, selection);	
		break;
		
	case ctlSelectEvent:
		switch (pEvent->data.ctlSelect.controlID)
		{
		case 1301:  //create and go to process
			pForm = FrmGetActiveForm();
			list2 = FrmGetObjectPtr (pForm, FrmGetObjectIndex (pForm, StartedProcessesList));
			if (LstGetSelection (list2)==noListSelection)
			{
				//alert
				FrmCustomAlert (CheckSelection, "a started process", NULL, NULL);	
			}
			else 
			{
				pForm = FrmInitForm(CurrentProcessForm);			
				FrmGotoForm (CurrentProcessForm);
				FrmDeleteForm(pForm);
				handled = true;
			}
				break;
			}
			break;
		
		default:
			break;
	}
	
	return handled;
}