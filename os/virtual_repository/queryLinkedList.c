/* 
**	Senior Design Project - PEOS Virtual Repository
**	Author : TASK4ONE
**	Filename : queryLinkedList.c
*/

#include "form.h"
#include "variables.h"
#include "queryLinkedList.h"
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h>
#include <stdbool.h>
#include <unistd.h>

void printQueryList( queryList *listpointer )
{
	if ( listpointer == NULL )
		printf ( "\nqueue list is empty!\n" ) ;
	else
	{
    		while ( listpointer != NULL )
		{
			printf( "\nquery is %s %s %s", 	listpointer -> oneQuery -> myClauses[0].attribute ,
							listpointer -> oneQuery -> myClauses[0].operator, 
							listpointer -> oneQuery -> myClauses[0].value ) ;
			listpointer = ( queryList* ) listpointer -> link ;
		}
	}
	printf ( "\n" ) ;
}

queryList *addQueryItem( queryList *listpointer, const query *data )
{
	queryList *lp = listpointer ;
	
	if ( listpointer != NULL )
	{
		while ( listpointer -> link != NULL )
			listpointer = ( queryList* ) listpointer -> link ;

		listpointer -> link = ( struct queryList * ) malloc ( sizeof ( queryList ) ) ;
		listpointer = ( queryList * ) listpointer -> link;
		listpointer -> link = NULL;
		listpointer -> oneQuery = ( query* ) data ;
		listpointer -> oneQuery -> numFound = 0 ;
		listpointer -> oneQuery -> removeTag = 0 ;
		listpointer -> oneQuery -> results = NULL ;
		return lp ;
    	}
	else
	{
		listpointer = ( queryList * ) malloc ( sizeof ( queryList ) ) ;
		listpointer -> link = NULL;
		listpointer -> oneQuery = ( query* ) data ;
		listpointer -> oneQuery -> numFound = 0 ;
		listpointer -> oneQuery -> removeTag = 0 ;
		listpointer -> oneQuery -> results = NULL ;
		return listpointer ;
    	}
}

queryList *filterQueryList( queryList *listpointer )
{
	queryList *previous, *current, *temp ;
	previous = current = listpointer ;
	
	while( current != NULL )
	{
		if( current -> oneQuery -> removeTag )
			if( current == listpointer )
			{
				listpointer = ( queryList * ) listpointer -> link ;
				free( current -> oneQuery -> myClauses[0].attribute ) ;
				free( current -> oneQuery -> myClauses[0].operator ) ;
				free( current -> oneQuery -> myClauses[0].value ) ;
				clearResultList( current -> oneQuery -> results ) ;
				free( current ) ;
				previous = current = listpointer ;
			}
			else
			{
				previous -> link = current -> link ;
				free( current -> oneQuery -> myClauses[0].attribute ) ;
				free( current -> oneQuery -> myClauses[0].operator ) ;
				free( current -> oneQuery -> myClauses[0].value ) ;
				clearResultList( current -> oneQuery -> results ) ;
				free( current ) ;
				current = ( queryList * ) previous -> link ;
			}
		else
		{
			previous = current ;
			current = ( queryList * ) current -> link ;
		}
	}
	return listpointer ;
}

