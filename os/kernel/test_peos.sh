#!/bin/sh

echo ..testing peos ...
#create the pml program

echo "process p {" > peos_test.pml
echo "action a {}" >> peos_test.pml
echo "action b {}" >> peos_test.pml
echo "action c {}" >> peos_test.pml
echo "}" >> peos_test.pml

if test -s proc_table.dat.xml 
then
mv proc_table.dat.xml old_proc_table.dat.xml 
fi

if test -s proc_table.dat 
then 
mv proc_table.dat new_proc_table.dat
fi

#create a process
peos -c peos_test.pml > output


if !(grep '<process pid=0 model=./peos_test.pml status=2>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed process creation process tag.
  echo
fi

if !(grep '<action name=a state=READY>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed process creation action a tag.
  echo
fi

if !(grep '<action name=b state=NONE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed process creation action b tag.
  echo
fi

if !(grep '<action name=c state=NONE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed process creation action c tag.
  echo
fi

#start an action
peos -n 0 a start > output


if !(grep '<process pid=0 model=./peos_test.pml status=2>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed start action process tag.
  echo
fi

if !(grep '<action name=a state=RUN>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed start action action a tag.
  echo
fi

if !(grep '<action name=b state=NONE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed start action action b tag.
  echo
fi

if !(grep '<action name=c state=NONE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed start action action c tag.
  echo
fi

#suspend an action
peos -n 0 a suspend > output


if !(grep '<process pid=0 model=./peos_test.pml status=2>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed suspend action process tag.
  echo
fi

if !(grep '<action name=a state=SUSPEND>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed suspend action action a tag.
  echo
fi

if !(grep '<action name=b state=NONE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed suspend action action b tag.
  echo
fi

if !(grep '<action name=c state=NONE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed suspend action action c tag.
  echo
fi

#abort action

peos -n 0 a start > output
peos -n 0 a abort > output


if !(grep '<process pid=0 model=./peos_test.pml status=2>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed abort action process tag.
  echo
fi

if !(grep '<action name=a state=AVAILABLE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed abort action action a tag.
  echo
fi

if !(grep '<action name=b state=AVAILABLE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed abort action action b tag.
  echo
fi

if !(grep '<action name=c state=AVAILABLE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed abort action action c tag.
  echo
fi


#finish action
peos -n 0 a finish > output


if !(grep '<process pid=0 model=./peos_test.pml status=2>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed start action process tag.
  echo
fi

if !(grep '<action name=a state=DONE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed finish action action a tag.
  echo
fi

if !(grep '<action name=b state=READY>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed finish action action b tag.
  echo
fi

if !(grep '<action name=c state=AVAILABLE>' proc_table.dat.xml > /dev/null)
then
  echo
  echo Failed finish action action c tag.
  echo
fi

rm proc_table.dat.xml
rm proc_table.dat
rm peos_test.pml 


if test -s old_proc_table.dat.xml 
then
mv old_proc_table.dat.xml proc_table.dat.xml 
fi

if test -s old_proc_table.dat 
then 
mv old_proc_table.dat proc_table.dat
fi

echo .. done.
