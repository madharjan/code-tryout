# code-tryout

## Log File
The sample time-series dataset in [nasa_19950801.tsv](nasa_19950801.tsv) comes from [public 1995 NASA Apache web logs](http://ita.ee.lbl.gov/html/contrib/NASA-HTTP.html).

## Find Top 10 Host or IP

### Java
```
cd java
javac App.java
java App ../nasa_19950801.tsv
```

### Bash Script
```
cd bash
chmod +x run.sh
./run.sh ../nasa_19950801.tsv
```

### Perl Script
```
cd perl
perl run.pl ../nasa_19950801.tsv
```
