/************************************************************************
 * Senior Design Project - PEOS Virtual Repository			*
 * Author : TASK4ONE							*
 * Filename : vrepo.c							*
 ************************************************************************/

#include "form.h"
#include "debug.h"
#include "variables.h"
#include "vrepo.h"
#include "FSseeker.h"
#include "resultLinkedList.h"
#include "queryLinkedList.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/************************************************************************
 * Function:	query_wait						*
 *									*
 * Description:	Tokenizes the  queryString into clauses consisting of  *
 *		Id, attribute and value. Checks for the validity of 	*
 *		queryString then makes a new query and register it	*
 * 		in the list "myQuery".					*
 ************************************************************************/

void query_wait( char *queryString, void ( *cback )( int, resultList *, int * ), int *d )
{
	bool isValidAttribute( char * ) ; 
	bool isValidOperator( char *, char * ) ; 
	bool isValidValue( char * ) ;
	bool isValidConjecture( char *) ;
	
	char *word, *toParse ; 			// tokens during string tokenizations 	
	int numParses, numClauses, numTokens ;	// keeps track of the token in the tokenizing phase
						// numClauses stores the number of clauses in the queryString
					 
	query *newQuery ; 			// stores the new query
	
	newQuery = ( query * ) malloc ( sizeof ( query ) ) ;
	numParses = numClauses = numTokens = 0 ;
	word = toParse = NULL ;
	
	toParse = strtok( queryString, "\n" ) ;	

	if( toParse != NULL )
		word = strtok( toParse, " " ) ;

	
	while( word != NULL )
	{
		numTokens++;
		
		if(numParses == 4)
		{
			numParses = 0;
			numClauses++;
		}
		
		switch( numParses )
		{
			case 0 :	if (isValidAttribute( word) )
					{
						newQuery -> myClauses[numClauses].attribute = strdup( word ) ;
						numParses++ ;
						_debug( __FILE__, __LINE__,2, "Attribute is %s", word ) ;
					}
					break ;
						
					
			case 1 :	if( isValidOperator( word, newQuery -> myClauses[numClauses].attribute ) )
					{
						newQuery -> myClauses[numClauses].operator = strdup( word ) ;
						numParses++ ;
						_debug( __FILE__, __LINE__, 2, "Operator is %s", word ) ;
					}						
					break ;
					
			case 2 : 	if( isValidValue( word ) )
					{
						newQuery -> myClauses[numClauses].value = strdup( word ) ;
						numParses++ ;
						_debug( __FILE__, __LINE__, 2, "Value is %s", word ) ;
					}
					break ;
			
			case 3 :	if( isValidConjecture( word ) )
					{
						newQuery -> myClauses[numClauses].conjecture = strdup( word ) ;
						numParses++ ;
						_debug( __FILE__, __LINE__, 2, "Conjecture is %s", word ) ;
					}
					else
					{
						_debug(__FILE__,__LINE__,2,"invalid conjecture");
					}
					break ;
		}
		
		_debug(__FILE__,__LINE__,5,"parsing word %s number of parses is : %d number of tokens: %d",word,numParses,numTokens);
		word = strtok( NULL, " " ) ;
	}
	
	if( ( ( numClauses * 4 + numParses ) == numTokens ) && ( numParses == 3 ) )
	{
		_debug( __FILE__, __LINE__, 5, "Storing Clause" ) ;
		newQuery -> callback = cback;
		newQuery -> data = d ;
		newQuery -> numFound = 0 ;
		newQuery -> removeTag = 0 ;
		newQuery -> numClauses = numClauses ;
		newQuery -> results = NULL ;
		newQuery -> myClauses[numClauses].conjecture = NULL ;
		myQueries = addQueryItem( myQueries, newQuery ) ;
		numParses = 0 ;
	}
	else
	{
		if( numParses != 0 )
		{
			int k ;
								
			for( k = 0 ; k != numClauses; k++ )
			{
				free( newQuery -> myClauses[k].attribute );
				free( newQuery -> myClauses[k].operator ) ;
				free( newQuery -> myClauses[k].value ) ;
				free( newQuery -> myClauses[k].conjecture ) ;
			}
			
			for( k = 0 ; k < numParses ; k++ )
			{
				switch( k )
				{
					case 0 : free( newQuery -> myClauses[numClauses].attribute ) ;
						 break ;
					
					case 1 : free( newQuery -> myClauses[numClauses].operator ) ;
						 break ;
					
					case 2 : free( newQuery -> myClauses[numClauses].value ) ;
						 break ;
						 
					case 3 : free( newQuery -> myClauses[numClauses].conjecture ) ;
						 break ;
				}
			}
			free( newQuery ) ;
			printf( "invalid query...\n" ) ;
		}
		else
			printf( "empty query...\n" ) ;			
	}
}


/************************************************************************
 * Function:	poll_vr							*
 *									*
 * Description:	Goes through the repo_list and calls the queryTool 	*
 *		function for each repository.  It also calls the 	*
 *		callback function for the satisfied queries.		*
 ************************************************************************/

void poll_vr( ) 
{
	queryList *tempQueries ;	// temporary variable to store myQuery
	int tag = 0 ;			// tag is one if a query is satisfied in myQueries
	int i=0;			// used in for loop

	if( myQueries != NULL )
	{
		
		for(i=0; i<repos_ctr ; i++)
			myQueries = repos_list[i].queryTool(myQueries);
		
		tempQueries = myQueries ;
	
		while( tempQueries != NULL )			
		{
			if( tempQueries -> oneQuery -> numFound )
			{
				tag = 1 ;
				tempQueries -> oneQuery -> callback( tempQueries -> oneQuery -> numFound,
							             tempQueries -> oneQuery -> results,
							             tempQueries -> oneQuery -> data ) ;
				tempQueries -> oneQuery -> removeTag = 1 ;
			}
			
			else if( tempQueries -> oneQuery -> removeTag )
			{
				tag = 1 ;
			}
			
			tempQueries = ( queryList* ) tempQueries -> link ;
		}
	
		if ( tag )
			myQueries = filterQueryList( myQueries ) ;
	
	}
}

/************************************************************************
 * Function:	isValidAttribute					*
 *									*
 * Description:	Returns true if the attribute in the query is an  	*
 *		attribute of the repository. 				*
  ************************************************************************/

bool isValidAttribute( char *attr )
{
	int i ;						// used in for loop
	char *attributes[3] = { "ID","DATE", "NAME" } ;	// array that stores repository attributes
		
	if( attr == NULL )
		return false;
		
	for( i = 0 ; i < 3 ; i++ )
	{
		if( ( strcmp( attributes[i], attr ) == 0 ) )
			return true ;
	}     
	return false ;
}

/************************************************************************
 * Function:	isValidOperator						*
 *									*
 * Description:	Returns true if the operator in the query is a  	*
 *		operator of the repository. 				*
 ************************************************************************/

bool isValidOperator( char *op, char *attr )
{
	int i ;							// used in for loop
	char *operators[4] = { "EQ", "~", "LT", "GT" } ;	// array that stores repository operators
		
	if( op == NULL )
		return false;
	else if (  (strcmp( "ID", attr ) == 0) && ( strcmp( operators[1], op ) == 0 ) )  
		return false ;
	else if ( ( strcmp ( "ID", attr ) == 0 ) || ( strcmp ( attr, "NAME" ) == 0 ) )
		if ( ( strcmp( operators[2], op ) == 0 ) || ( strcmp( operators[3], op ) == 0 ) )
			return false;
		
	for( i = 0 ; i < 4 ; i++ )
		if( ( strcmp( operators[i], op ) == 0 ) )
			return true ;
	return false;
}


/************************************************************************
 * Function:	isValidValue						*
 *									*
 * Description:	Returns false if the value is NULL		  	*
 ************************************************************************/

bool isValidValue( char *val )
{
	if ( val == NULL )
		return false ;
	return true ;
}

/************************************************************************
 * Function:	isValidConjecture					*
 *									*
 * Description:	Returns true if the operator in the query is a  	*
 *		operator of the repository. 				*
 ************************************************************************/

bool isValidConjecture( char *con )
{
	int i ;						// used in for loop
	char *conjecture[2] = { "AND","OR" } ;	// array that stores repository operators
	
	if( con == NULL ) 
		return false;
	
	for( i = 0 ; i < 2; i++ )
	{
		_debug(__FILE__,__LINE__,5,"in conjecture con is %s conjecture[i] is %s ", con, conjecture[i]);
		if( ( strcmp( conjecture[i], con ) == 0 ) )
			return true ;
	}
	return false ;
}
