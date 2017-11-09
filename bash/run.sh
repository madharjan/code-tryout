#!/bin/sh
if [ $# -ne 1 ]; then
  echo "Usage: $0 <filename>"
  exit 1;
fi
FILENAME=$1
TMP=`mktemp -t tmp`
rm -f $TMP
OLDIFS=$IFS
IFS=$' \t\n'
old=""
count=0
# skip header and sort by 1st column
tail -n +2 $FILENAME | sort -k1 | while read host logname time method url response bytes referer useragent
do
   if [ "$old" = "" ]; then
      old=$host
   fi
   if [ "$host" = "$old" ]; then
     (( count++))
   else
     echo "$old $count" >> $TMP
     old=$host
     count=1
   fi
done
i=0
echo "--- Top 10 Host/IP ---"
# sort by 2nd column reverse numeric
cat $TMP | sort -k2 -n -r | while read host count
do
  (( i++))
  [ $i -gt 10 ] && break
  echo "Host: $host Count: $count"
done
IFS=$OLDIFS
rm -f $TMP
