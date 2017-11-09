#!/usr/bin/python

import csv;
import sys;

i = 0;
f = open(sys.argv[1], 'rb')
reader = csv.reader(f, delimiter='\t')
dict = {}
for row in reader:
  i = i + 1
  if i == 1:
    continue
  host = row[0]
  time = row[2]
  url = row[4]
  response = row[5]

  found = dict.get(host, 'none')
  if found != 'none':
    dict[host] += 1
  else:
    dict[host] = 1;
f.close()

print "-- Top 10 Host/IP ---"
i = 0
for host, count in sorted(dict.iteritems(), key=lambda (k,v): (v,k), reverse=True):
  i = i + 1
  if i >= 11:
    break
  print "Host: %s Count: %d" % (host, count)
