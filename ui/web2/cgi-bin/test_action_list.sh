#!/bin/sh
echo -n "$0..."

export QUERY_STRING="process_filename=test.dat&start=false"
export REQUEST_METHOD=GET
export REMOTE_USER=test

create_testtable
action_list.cgi > output

if !(grep '<h1>Action List</h1>' output > /dev/null)
then
  echo
  echo Failed Action List header.
  echo
fi
if !(grep 'Process Id' output > /dev/null)
then
  echo
  echo Failed Process Id.
  echo
fi
if !(grep 'Action Name' output > /dev/null)
then
  echo
  echo Failed Action Name.
  echo
fi
if !(grep 'State' output > /dev/null)
then
  echo
  echo Failed State.
  echo
fi
if !(grep 'Check to Indicate Done' output > /dev/null)
then
  echo
  echo Failed checkbox header.
  echo
fi
if !(grep 'test1' output > /dev/null)
then
  echo
  echo Failed test1.
  echo
fi

if !(grep 'test2' output > /dev/null)
then
  echo
  echo Failed test2.
  echo
fi


if !(grep 'READY' output > /dev/null)
then
  echo
  echo Failed test1 not ready.
  echo
fi

if !(grep 'NONE' output > /dev/null)
then
  echo
  echo Failed test2 not none.
  echo
fi


if !(grep '0\$test1' output > /dev/null)
then
  echo
  echo Failed checkbox value 1.
  echo
fi


if !(grep '0\$test2' output > /dev/null)
then
  echo
  echo Failed checkbox value 2.
  echo
fi


rm output
rm test.dat


#test whether process state gets updated after a resource becomes available
export QUERY_STRING="process_filename=test.dat&act_name=test2&pid=0&resource_type=requires&r3=r3val"
export REQUEST_METHOD=GET
                                                                               
create_testtable
change_resource_bindings.cgi > /dev/null

export QUERY_STRING="process_filename=test.dat&start=false"
touch r3val

action_list.cgi > output


if !(grep '<h1>Action List</h1>' output > /dev/null)
then
  echo
  echo Failed Action List header.
  echo
fi
if !(grep 'Process Id' output > /dev/null)
then
  echo
  echo Failed Process Id.
  echo
fi
if !(grep 'Action Name' output > /dev/null)
then
  echo
  echo Failed Action Name.
  echo
fi
if !(grep 'State' output > /dev/null)
then
  echo
  echo Failed State.
  echo
fi
if !(grep 'Check to Indicate Done' output > /dev/null)
then
  echo
  echo Failed checkbox header.
  echo
fi
if !(grep 'test1' output > /dev/null)
then
  echo
  echo Failed test1.
  echo
fi

if !(grep 'test2' output > /dev/null)
then
  echo
  echo Failed test2.
  echo
fi
if !(grep 'READY' output > /dev/null)
then
  echo
  echo Failed test1 not ready.
  echo
fi
if !(grep 'AVAILABLE' output > /dev/null)
then
  echo
  echo Failed test2 not Available.
  echo
fi
if !(grep '0\$test1' output > /dev/null)
then
  echo
  echo Failed checkbox value 1.
  echo
fi

if !(grep '0\$test2' output > /dev/null)
then
  echo
  echo Failed checkbox value 2.
  echo
fi

rm output
rm r3val
rm test.dat

echo "done"
