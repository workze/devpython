
git diff --name-only 2fac01e c751 | while read line

do
	if [[ $line =~ "dropwizard" ]] ; then
  	  echo line is $line;
  	  break;
	fi
done


mvn org.qunix:structure-maven-plugin:modules

