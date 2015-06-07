call mvn install:install-file  -Dfile=generator-0.0.1-RELEASE.jar  -DgroupId=mygenerator -DartifactId=generator -Dversion=0.0.1-RELEASE -Dpackaging=jar -DgeneratePom=true
call mvn install:install-file  -Dfile=api-util-0.0.1-SNAPSHOT-classes.jar  -DgroupId=api -DartifactId=api-util -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dclassifier=classes -DgeneratePom=true
call mvn install:install-file  -Dfile=api-util-0.0.1-SNAPSHOT.war  -DgroupId=api -DartifactId=api-util -Dversion=0.0.1-SNAPSHOT -Dpackaging=war -DgeneratePom=true
pause