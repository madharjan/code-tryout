#!/usr/bin/perl
use strict;
use warnings;
use Data::Dumper;

my $file = $ARGV[0] or die "Usages: run.pl <filename>\n";
open(my $data, '<', $file) or die "Could not open $file\n";
my %map = ();
my $dummy = <$data>; #skip header
while (my $line = <$data>){
  chomp $line;
  my @fields = split "\t" , $line;
  my $host = $fields[0];
  my $time = $fields[2];
  my $url = $fields[4];
  my $response = $fields[5];
  if (exists $map{$host}){
     $map{$host} += 1;
  } else {
     $map{$host} = 1;
  }
}
close($data);
my @sorted = sort { $map{$b} <=> $map{$a} } keys %map;
my $i = 0;
print "-- Top 10 Host/IP --\n";
foreach my $host (@sorted) {
  $i++;
  if ($i > 10 ){
    last;
  }
  print "Host: $host Count: $map{$host}\n";
}
